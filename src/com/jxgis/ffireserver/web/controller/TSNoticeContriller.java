package com.jxgis.ffireserver.web.controller;

import com.jxgis.ffireserver.core.util.ReturnBody;
import com.jxgis.ffireserver.web.bean.Notice;
import com.jxgis.ffireserver.web.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 测试公告 的 增删改查
 */
@Controller
@RequestMapping("/noticeContriller")
public class TSNoticeContriller {

    @Autowired
    private SystemService systemService;

    //查询
    @RequestMapping(value = "data")
    @ResponseBody
    public ReturnBody data() {
        return test(1);
    }

    //增加
    @RequestMapping(value = "increase")
    @ResponseBody
    public ReturnBody increase(Notice notice) {
        systemService.saveOrUpdate(notice);
        return test(0);
    }

    //删除
    @RequestMapping(value = "delete")
    @ResponseBody
    public ReturnBody delete(Notice notice) {
        systemService.delete(notice);
        return test(0);
    }

    //修改
    @RequestMapping(value = "modify")
    @ResponseBody
    public ReturnBody modify(Notice notice) {
        systemService.updateEntitie(notice);
        return test(0);
    }

    private ReturnBody test(int i) {
        ReturnBody out = new ReturnBody();
        switch (i){
            case 1:
                List<Notice> list = systemService.loadAll(Notice.class);
                out.setObj(list);
                break;
        }
        return out;
    }
}
