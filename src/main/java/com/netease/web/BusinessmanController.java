package com.netease.web;

import com.netease.domain.Businessman;
import com.netease.service.impl.BusinessmanServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;


@RequestMapping("/businessman")
@Controller("businessmanController")
public class BusinessmanController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessmanController.class);

    @Autowired
    @Qualifier("businessmanServiceImpl")
    private BusinessmanServiceImpl businessmanService;

    @RequestMapping("/profile")
    public String showProfile(HttpSession session, ModelMap modelMap)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您没有登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        Businessman businessman=businessmanService.getBusinessmanByName(user.get("name"));
        modelMap.addAttribute("profile", businessman);
        return "profile.ftl";
    }
}
