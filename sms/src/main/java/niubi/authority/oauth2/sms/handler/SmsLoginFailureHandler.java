package niubi.authority.oauth2.sms.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author congcong
 * @className LoginFailureHandler
 * @date 2019/3/2 15:42
 * 类描述
 */
@Component
public class SmsLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //设置返回类型为json
        response.setContentType("application/json;charset=UTF-8");
        //转换成JSON字符串
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data", exception.getMessage());
        response.getWriter().write(jsonObject.toJSONString());
       // super.onAuthenticationFailure(request, response, exception);   //调用父类的失败处理
    }
}
