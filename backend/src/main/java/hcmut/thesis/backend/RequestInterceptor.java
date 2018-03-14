/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 *
 * @author MinBui
 */

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
 @Override
 public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object object) throws Exception {
	System.out.println("Im am MinBui Interceptor");
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

