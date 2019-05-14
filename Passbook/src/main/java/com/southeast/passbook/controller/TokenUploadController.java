package com.southeast.passbook.controller;

import com.southeast.passbook.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1> PassTemplate Token Upload</h1>
 * Created by 18351 on 2019/5/13.
 */
@Slf4j
@Controller
public class TokenUploadController {

    private final StringRedisTemplate redisTemplate; // redis 客户端

    @Autowired
    public TokenUploadController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 2
    // f8e97f46b58f54edfcdbfe59258ca437
    @GetMapping("/upload")
    public String upload() {
        return "upload"; //文件上传页面
    }

    @PostMapping("/token")
    public String tokenFileUpload(@RequestParam("merchantsId") String merchantsId,
                                  @RequestParam("passTemplateId") String passTemplateId,
                                  @RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes) {
        if(null == passTemplateId || file.isEmpty()){  //校验
            redirectAttributes.addFlashAttribute("message",
                    "passTemplateId is null or file is empty");
            return "redirect:/uploadStatus";
        }

        try{
            //根据本地路径和 passTemplateId 在本地创建文件
            File cur = new File(Constants.TOKEN_DIR + passTemplateId);
            if(!cur.exists()){
                log.info("Create File: {}", cur.mkdir()); //该文件不存在，就创建新文件夹
            }
            Path path = Paths.get(Constants.TOKEN_DIR, merchantsId, passTemplateId);
            Files.write(path, file.getBytes());

            if (!writeTokenToRedis(path, passTemplateId)) { // token 未能写入 redis
                redirectAttributes.addFlashAttribute("message",
                        "write token error");
            } else {
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus"; //上传结果页面
    }

    /**
     * 将 token 写入 redis
     * @param path token文件路径对象
     * @param key redis key
     * @return true/false
     */
    private boolean writeTokenToRedis(Path path, String key) {

        Set<String> tokens; //需要存入 redis 的 token 的集合

        try{
            /*Stream<String> stream = Files.lines(path);
            tokens =stream.collect(Collectors.toSet());*/
            tokens = Files.lines(path).collect(Collectors.toSet());
        }catch (IOException e){
            e.printStackTrace();
            return false; //发生异常肯定返回 false
        }

        if(!CollectionUtils.isEmpty(tokens)){ // 判断 tokens!=null && tokens.size()!=0
            redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
                for (String token : tokens) { // 将 token 存入 redis 中，注意是以键值对形式
                    connection.sAdd(key.getBytes(), token.getBytes());
                }
                return null;
            });
            return true;
        }
        return false;
    }
}
