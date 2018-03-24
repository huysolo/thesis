/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend;

import hcmut.thesis.backend.modelview.UserSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import hcmut.thesis.backend.services.UserService;

/**
 *
 * @author MinBui
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService loginService;
    
    @Autowired
    UserSession userSession;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("token");
        if (token != "" && token != null) {
            userSession.setUsername(loginService.parseJWT(token).getSubject());
            userSession.setIsStudent((loginService.parseJWT(token).getIssuer()== "true")? true: false);
            userSession.setIsProf(!userSession.isStudent());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object object, ModelAndView model)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object object, Exception arg3)
            throws Exception {
    }
}
