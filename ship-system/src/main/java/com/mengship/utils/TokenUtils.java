package com.mengship.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mengship.entity.User;
import com.mengship.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.soap.Addressing;
import java.util.Date;

/**
 * @Description
 * @Author meng
 * @Data 2022/12/30 11:48
 */
@Component
public class TokenUtils {

    private static IUserService staticUserService;

    @Resource
    private IUserService userService;



    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

    /**
     * @Description 生成token
     * @Param null
     * @Return {@link null}
     * @Author meng
     * @Date 2022/12/30 12:43
     */
    public static String getToken(String userId,String sign){
       return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetMinute(new Date(),30))  // 设置过期时间
                .sign(Algorithm.HMAC256(sign));  // 以 password 作为 token 的密钥
    }
    /***
     * @Description 获取用户信息
     * @Param null
     * @Return {user对象}
     * @Author meng
     * @Date 2022/12/30 12:44
     */
    public static User getCurrentUser(){
        try {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)){
            String userId = JWT.decode(token).getAudience().get(0);
            return staticUserService.getById(Integer.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
