package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.core.constant.DataBaseConstant;
import com.jxgis.ffireserver.core.util.*;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.*;
import com.jxgis.ffireserver.web.service.SystemService;
import com.jxgis.ffireserver.web.system.manager.ClientManager;
import com.jxgis.ffireserver.web.system.pojo.base.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by YanWei on 2017/6/5.
 * 登录控制器
 *
 * @author 言伟
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {
    @Autowired
    private SystemService systemService;

    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }


    @RequestMapping(params = "login")
    @ResponseBody
    public ReturnBody login(TSUser user, HttpServletRequest req) throws Exception {
        ReturnBody out = new ReturnBody();
        TSUser user_db = systemService.findUniqueByProperty(TSUser.class, "account", user.getAccount());
        if (!StringUtil.isNotEmpty(user_db)) {
            out.setStatus(Glossary.Return_State_User_Not_Exist);
            out.setMsg("用户不存在");
            return out;
        }
        if (!StringUtil.isNotEmpty(user_db.getState()) || user_db.getState() == 1) {
            out.setStatus(Glossary.Return_State_User_Not_Power);
            out.setMsg("Insufficient user permissions.");
            return out;
        }
        //下面开始校验密码
        String password = "";
        try {
            password = PasswordUtil.decrypt(user_db.getPassword(), user.getPassword(), PasswordUtil.getSalt());
        } catch (Exception e) {
            out.setStatus(Glossary.Return_State_User_Password_Error);
            out.setMsg("密码错误");
            return out;
        }

        if (!user_db.getAccount().equals(password)) {
            out.setStatus(Glossary.Return_State_User_Password_Error);
            out.setMsg("密码错误");
            return out;
        }


        //返回用户数据
        out.setObj(user_db.loginReturn());

        return out;
    }

    /**
     * 注册帐号
     * @return
     */
    @RequestMapping(params = "register")
    @ResponseBody
    public ReturnBody register(TSUser user, HttpServletRequest req) throws Exception {
        ReturnBody out = new ReturnBody();
        TSUser user_db = systemService.findUniqueByProperty(TSUser.class, "account", user.getAccount());
        if (StringUtil.isNotEmpty(user_db)) {
            out.setStatus(Glossary.Return_State_User_Not_Exist);
            out.setMsg("用户已存在");
            return out;
        }
        if (user.getAccountType() == null) {
            out.setStatus(Glossary.Return_State_User_Not_Power);
            out.setMsg("Setting the user's permissions");
            return out;
        }
        systemService.saveOrUpdate(user);
        TSUser user_db1 = systemService.findUniqueByProperty(TSUser.class, "account", user.getAccount());

        //返回用户数据
        out.setObj(user_db1.loginReturn());

        return out;

    }

    @RequestMapping(params = "reLogin")
    public ModelAndView reLogin(HttpServletRequest req) {
        return new ModelAndView("login/login");
    }


    /**
     * 退出系统
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "logout")
    public ModelAndView logout(HttpServletRequest request) {
        try {
            HttpSession session = ContextHolderUtils.getSession();
            ClientManager.getInstance().removeClinet(session.getId());
            session.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }

}
