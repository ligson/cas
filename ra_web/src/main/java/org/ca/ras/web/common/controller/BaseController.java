package org.ca.ras.web.common.controller;

import org.ligson.fw.core.facade.web.vo.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ligson on 2016/4/26.
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected ServletContext applicationContext;
    protected WebResult webResult = new WebResult();
    protected static Logger logger;

    public BaseController() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @ModelAttribute
    private void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        this.applicationContext = request.getServletContext();
    }
}
