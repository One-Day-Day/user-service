package com.lovecode.security.constants;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = "classpath:secret/jwt_secret_key.yml")
@Component
public class SecurityConstants {

    /**
     * 获取token地址
     */
    public static final String AUTH_LOGIN_URL = "/auth/token";

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "role";
    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
    public static final long EXPIRATION = 60L * 60L;
    /**
     * rememberMe 为 true 的时候过期时间是7天
     */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    @Value("${jwt_secret_key}")
    private void setJwtSecretKey(String key) {
        JWT_SECRET_KEY = key;
    }

    public static String JWT_SECRET_KEY;

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    private SecurityConstants() {
    }
}
