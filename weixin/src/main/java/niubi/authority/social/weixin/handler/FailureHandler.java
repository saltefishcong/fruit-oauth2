package niubi.authority.social.weixin.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author congcong
 * @className FailureHandler
 * @date 2019/3/13 16:01
 * 类描述
 */
@Configuration
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        //转换成JSON字符串
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("error",e.getMessage());
        httpServletResponse.getWriter().write(jsonObject.toJSONString());
    }
}
