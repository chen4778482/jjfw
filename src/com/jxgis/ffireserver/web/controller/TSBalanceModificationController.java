package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.core.util.StringUtil;
import com.jxgis.ffireserver.util.Glossary;
import com.jxgis.ffireserver.web.bean.TSUser;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 测试余额 的 增删改查
 */
@Controller
@RequestMapping(value = "/balanceModificationController")
public class TSBalanceModificationController {

    @Autowired
    private SystemService systemService;

    //余额修改
    @RequestMapping(params = "increase" )
    @ResponseBody
    public ReturnBody increase(TSUser user){
        ReturnBody out = new ReturnBody();
        TSUser user_db = systemService.findUniqueByProperty(TSUser.class, "account", user.getAccount());

        if( !StringUtil.isNotEmpty(user_db) ){

            out.setStatus( Glossary.Return_State_User_Not_Exist );
            out.setMsg( "用户不存在" );
            return out;
        }

        systemService.updateEntitie(user);
        List<TSUser> list = systemService.loadAll(TSUser.class);
        out.setObj(list);
        return out;
    }

}
