package org.ca.ras.web.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ligson on 2016/4/26.
 */
@Controller
public class PubController extends BaseController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
