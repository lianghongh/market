package com.netease.web;

import com.netease.dao.impl.BusinessmanDaoImpl;
import com.netease.dao.impl.UserDaoImpl;
import com.netease.domain.Businessman;
import com.netease.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller("loginController")
public class LoginController {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDaoImpl userDao;

    @Autowired
    @Qualifier("businessmanDaoImpl")
    private BusinessmanDaoImpl businessmanDao;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/login")
    public String getLoginPage()
    {
        return "login.ftl";
    }

    @RequestMapping("/register")
    public String getRegisterPage()
    {
        return "register.ftl";
    }


    @Transactional
    @RequestMapping("/api/login")
    public String login(String username, String password, String role, HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException
    {
        HttpSession session=request.getSession();
        Map<String, String> user = new HashMap<>();
        user.put("name", username);
        user.put("role", role);
        modelMap.addAttribute("user", user);
        if(role.equals("user"))
        {
            User u=userDao.getUserByNickname(username);
            if(u==null)
            {
                logger.error("用户{}不存在！",username);
                return "login_error.ftl";
            }
            if(!u.getPassword().equals(password))
            {
                logger.error("用户{}的密码错误！",username);
                return "login_error.ftl";
            }
            if(session.getAttribute("user")!=null)
            {
                logger.error("用户{}已经登录！",username);
                return "redirect:/";
            }
            session.setAttribute("user",user);
            u.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
            userDao.updateInfo(u);
            return "redirect:/";
        }
        else if(role.equals("businessman"))
        {
            Businessman businessman = businessmanDao.getBusinessmanByNickname(username);
            if(businessman==null)
            {
                logger.error("商家{}不存在！",username);
                return "login_error.ftl";
            }
            if(!businessman.getPassword().equals(password))
            {
                logger.error("商家{}的密码错误！",username);
                return "login_error.ftl";
            }
            if(session.getAttribute("user")!=null)
            {
                logger.error("商家{}已经登录！",username);
                return "redirect:/";
            }
            session.setAttribute("user",user);
            businessman.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
            businessmanDao.updateInfo(businessman);
            return "redirect:/";
        }
        else
        {
            logger.error("无法识别的角色{}！",role);
            return "login_error.ftl";
        }
    }

    @Transactional
    @RequestMapping("/api/register")
    public String register(String username,String password,String role,HttpServletRequest request,ModelMap modelMap) throws UnsupportedEncodingException
    {
        HttpSession session = request.getSession();
        Map<String, String> user = new HashMap<>();
        user.put("name", username);
        user.put("role", role);
        modelMap.addAttribute("user",user);
        if(role.equals("user"))
        {
            User u = userDao.getUserByNickname(username);
            if(u!=null)
            {
                logger.error("用户{}已经存在！注册失败",username);
                return "register_error.ftl";
            }
            session.setAttribute("user",user);
            u = new User();
            u.setNickname(username);
            u.setUserId(UUID.nameUUIDFromBytes(username.getBytes()).toString().replace("-",""));
            u.setPassword(password);
            u.setCart(new ArrayList<>());
            u.setShoppingInfoList(new ArrayList<>());
            u.setBalance(0);
            u.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
            u.setLogoutTime(Timestamp.valueOf(LocalDateTime.now()));
            userDao.insertUser(u);
            return "redirect:/";
        }
        else if(role.equals("businessman"))
        {
            Businessman businessman = businessmanDao.getBusinessmanByNickname(username);
            if(businessman!=null)
            {
                logger.error("商家{}已经存在！注册失败",username);
                return "register_error.ftl";
            }
            session.setAttribute("user",user);
            businessman = new Businessman();
            businessman.setNickname(username);
            businessman.setUserId(UUID.nameUUIDFromBytes(username.getBytes()).toString().replace("-",""));
            businessman.setPassword(password);
            businessman.setInventoryList(new ArrayList<>());
            businessman.setBalance(0);
            businessman.setLoginTime(Timestamp.valueOf(LocalDateTime.now()));
            businessman.setLogoutTime(Timestamp.valueOf(LocalDateTime.now()));
            businessmanDao.insertBusinessman(businessman);
            return "redirect:/";
        }
        else
        {
            logger.error("无法识别的角色{}",role);
            return "register_error.ftl";
        }
    }

    @Transactional
    @RequestMapping("/api/logout")
    public String logout(HttpServletRequest request, ModelMap modelMap)
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null)
        {
            logger.error("用户没有登录！");
            return "redirect:/";
        }
        Map<String,String> user = (Map<String, String>) session.getAttribute("user");
        session.invalidate();
        modelMap.remove("user");
        if("user".equals(user.get("role")))
        {
            User u = userDao.getUserByNickname(user.get("name"));
            u.setLogoutTime(Timestamp.valueOf(LocalDateTime.now()));
            userDao.updateInfo(u);
        }
        else if("businessman".equals(user.get("role")))
        {
            Businessman businessman = businessmanDao.getBusinessmanByNickname(user.get("name"));
            businessman.setLogoutTime(Timestamp.valueOf(LocalDateTime.now()));
            businessmanDao.updateInfo(businessman);
        }
        else
            logger.error("无法识别的角色{}",user.get("role"));

        return "redirect:/";
    }

    @Transactional
    @RequestMapping("/api/unregister")
    public String unregister(HttpSession session,ModelMap modelMap)
    {
        if(session==null || session.getAttribute("user")==null)
        {
            logger.error("您没有登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if("user".equals(user.get("role")))
            userDao.deleteUserByNickname(user.get("name"));
        else if("businessman".equals(user.get("role")))
            businessmanDao.deleteByNickname(user.get("name"));
        else
            logger.error("无法识别的角色{}", user.get("role"));
        session.invalidate();
        modelMap.remove("user");
        return "redirect:/";
    }

    @Transactional
    @RequestMapping("/api/changeprofile")
    public String changeProfile(HttpSession session, ModelMap modelMap, @RequestParam("username") String new_name, @RequestParam("password") String new_password)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还没有登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if("user".equals(user.get("role")))
        {
            userDao.updateNickname(user.get("name"),new_name);
            userDao.updatePassword(new_name,new_password);
            session.invalidate();
            modelMap.remove("user");
            return "redirect:/login";
        }
        else if("businessman".equals(user.get("role")))
        {
            businessmanDao.updateNickname(user.get("name"),new_name);
            businessmanDao.updatePassword(new_name,new_password);
            session.invalidate();
            modelMap.remove("user");
            return "redirect:/login";
        }
        else
        {
            logger.error("无法识别的角色{}",user.get("role"));
            return "redirect:/";
        }
    }

    @RequestMapping("/changeprofile")
    public String change()
    {
        return "changeprofile.ftl";
    }
}
