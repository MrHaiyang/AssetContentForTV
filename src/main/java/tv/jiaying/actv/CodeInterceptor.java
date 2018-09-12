package tv.jiaying.actv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CodeInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(CodeInterceptor.class);

    @Autowired
    AppTokenConfig appTokenConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String checkcode = request.getParameter("checkcode");
        //logger.info("code:{},checkcode:{}",appTokenConfig.getCode(),checkcode);

        if(checkcode==null || !appTokenConfig.getCode().equals(checkcode)){
            //logger.info("preHandle:false");
            return false;
        }
        //logger.info("preHandle:true");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //logger.info("afterCompletion");
    }

}
