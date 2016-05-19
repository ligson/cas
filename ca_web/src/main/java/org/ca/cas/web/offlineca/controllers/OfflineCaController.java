package org.ca.cas.web.offlineca.controllers;

import org.ca.cas.offlineca.api.OfflineCaApi;
import org.ligson.fw.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/19.
 */
@Controller
@RequestMapping("/offlineCa")
public class OfflineCaController extends BaseController {
    @Resource
    private OfflineCaApi offlineCaApi;

    @RequestMapping("/index.html")
    public String index() {
        logger.info("funck..............{}", offlineCaApi);
        return "offlineCa/ca/index";
    }
}
