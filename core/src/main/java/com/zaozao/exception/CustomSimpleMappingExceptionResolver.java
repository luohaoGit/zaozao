package com.zaozao.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2015/2/4.
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver{

    public static final Logger logger = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);
    public static final Logger error = LoggerFactory.getLogger("ERROR");

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        error.error(ex.getMessage(), ex);

        //expose ModalAndView for chosen error view
        String viewName = determineViewName(ex, request);
        if(viewName != null){//for jsp
            if((request.getHeader("accept").indexOf("text/html") > -1 ||
                    (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))){
                Integer statusCode = determineStatusCode(request, viewName);
                if(statusCode != null){
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                request.setAttribute("errorMsg", ex.getMessage());
                return getModelAndView(viewName, ex, request);
            }else{//ajax
                try{
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    writer.write("{\"errormsg\":\"" + ex.getMessage() + "\"}");
                    writer.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
                return null;
            }
        }else{
            return null;
        }

    }
}
