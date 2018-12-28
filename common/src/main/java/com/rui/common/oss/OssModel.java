package com.rui.common.oss;


import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * Created by linet on 16/3/31.
 */
@Keep
public class OssModel implements Serializable {
    /**
     * accessKeyId : STS.NKT7TwdD6vC36NBfwr3rRDqk4
     * accessKeySecret : Br6KrkdG9PLH8N63z6qAFHz4uZKqZEutBtxYbsWroN9o
     * endpoint : oss-cn-beijing.aliyuncs.com
     * expiration : 2018-10-08 06:35:06
     * host : http://kf-img.oss-cn-beijing.aliyuncs.com
     * securityToken : CAIShwJ1q6Ft5B2yfSjIr4jhfO7DiZsXwYHYNGjzgncnP/1+q7TA1jz2IHpEe3JuB+0WtPw/mmxZ6PgTlqV6U4cAQlffKNZr444PJr5CmAeF6aKP9rUhpMCPKwr6UmzGvqL7Z+H+U6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj+wIDLkQRRLqL0AFZrFsKxBltdUROFbIKP+pKWSKuGfLC1dysQcO7gEa4K+kkMqH8Uic3h+oiM1t/tyrfMT4PpUzY8YjCo3qjNYbLPSRjHRijDFR77pzgaB+/jPKg8qQGVE54W/dabqLqYMxcV8jPvhkR/8a8qnm8fRgoqnUjJ+y1g1AJ/Gjy8AT4lkhqRqAATQ0OzJTSfCEZjdTWfFHKamgjY0ZcmsdYnaa/UP7VOZC89hvJugSn9LUCbQnfyruZU/uWvNCr+1kecsQJ26rg5shNB0L4hJsyw0EqIMaJ1J3tud64IbYzTtSsVIae/ATDa1Pu1DbJJOmlqy4bJt4sBGB/DcOV3A4wanKlBo3yGh8
     */

    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String expiration;
    private String host;
    private String securityToken;

    public String getAccessKeyId() {
        return accessKeyId == null ? "" : accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret == null ? "" : accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint == null ? "" : endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getExpiration() {
        return expiration == null ? "" : expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getHost() {
        return host == null ? "" : host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSecurityToken() {
        return securityToken == null ? "" : securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

//    private String host;                           //图片服务器地址
//    private String policy;                         //上传策略
//    private String accessid;                       //id
//    private String expire;                         //过期时间
//    private String dir;                            //上传的相对路径
//    private String signature;                      //签名
//
//
//    private String endpoint;                           //图片服务器地址
//    private String accessKeyId;                         //上传策略
//    private String accessKeySecret;                       //id
//    private String expiration;                         //过期时间
//    private String securityToken;



}
