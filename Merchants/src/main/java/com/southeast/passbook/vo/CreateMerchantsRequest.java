package com.southeast.passbook.vo;

import com.southeast.passbook.constants.ErrorCode;
import com.southeast.passbook.dao.MerchantsDao;
import com.southeast.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>创建商户的请求对象</h1>
 * Created by 18351 on 2019/5/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {
    private String name; //商户名

    private String logoUrl; //商户 logo

    private String businessLicenseUrl; //商户营业执照

    private String phone; //商户联系电话

    private String address; //商户地址

    /**
     * 验证请求的有效性
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(merchantsDao.findByName(name) != null){
            return ErrorCode.DUPLICATE_NAME;
        }
        if(null == this.logoUrl){
            return ErrorCode.EMPTY_LOGO;
        }
        if(null == this.businessLicenseUrl){
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        }
        if(null == this.address){
            return ErrorCode.EMPTY_ADDRESS;
        }
        if(null == this.phone){
            return ErrorCode.ERROR_PHONE;
        }
        return ErrorCode.SUCCESS;
    }

    /**
     * 将请求对象转换为商户对象
     * @return {@link Merchants}
     */
    public Merchants toMerchants(){
        Merchants merchants = new Merchants();

        merchants.setName(name);
        merchants.setLogoUrl(logoUrl);
        merchants.setBusinessLicenseUrl(businessLicenseUrl);
        merchants.setAddress(address);
        merchants.setPhone(phone);

        return merchants;
    }
}
