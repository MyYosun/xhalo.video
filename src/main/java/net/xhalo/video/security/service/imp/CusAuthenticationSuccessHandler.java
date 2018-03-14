package net.xhalo.video.security.service.imp;

import net.xhalo.video.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class CusAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserDao userDao;

    //使用AOP分离更新登录时间，见UserAop
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.sendRedirect("/index?loginSuccess=true");
    }
}
