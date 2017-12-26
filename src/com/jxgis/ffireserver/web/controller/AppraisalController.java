package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.core.util.PasswordUtil;
import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.Guide;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 寻鉴定接口
 */
@Controller
@RequestMapping("/appraisalController")
public class AppraisalController {

    @Autowired
    private SystemService systemService;

    //办理指南
    @RequestMapping(params = "guide")
    @ResponseBody
    public ReturnBody guide(Guide guide, HttpServletRequest req) throws Exception {
        ReturnBody out = new ReturnBody();

        return out;
    }

}
