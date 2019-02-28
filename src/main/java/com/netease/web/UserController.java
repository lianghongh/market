package com.netease.web;

import com.netease.domain.CartInfo;
import com.netease.domain.Image;
import com.netease.domain.ShoppingInfo;
import com.netease.domain.User;
import com.netease.service.impl.ProductServiceImpl;
import com.netease.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("userController")
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductServiceImpl productService;

    @RequestMapping("/profile")
    public String showProfile(HttpSession session,ModelMap modelMap)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您没有登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if(!"user".equals(user.get("role")))
        {
            logger.error("您没有权限进行该操作！");
            return "redirect:/";
        }
        User u=userService.getUserByName(user.get("name"));
        modelMap.addAttribute("profile", u);
        return "profile.ftl";
    }


    @RequestMapping("/bill")
    public String showBill(HttpSession session, ModelMap modelMap) {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还未登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if(!"user".equals(user.get("role")))
        {
            logger.error("您没有权限进行该操作！");
            return "redirect:/";
        }
        User u = userService.getUserByName(user.get("name"));
        Map<String, String> image_map = new HashMap<>();
        for(ShoppingInfo info:u.getShoppingInfoList())
        {
            Image image = productService.findImages(info.getProductId()).get(0);
            image_map.put(image.getProductName(), image.getUrl());
        }
        modelMap.addAttribute("image_map",image_map);
        modelMap.addAttribute("billList", u.getShoppingInfoList());
        return "bill.ftl";
    }

    @RequestMapping("/cart")
    public String showCart(HttpSession session,ModelMap modelMap)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还未登录！");
            return "redirect:/";
        }
        Map<String,String> user=(Map<String, String>)session.getAttribute("user");
        if(!"user".equals(user.get("role")))
        {
            logger.error("您没有权限进行该操作！");
            return "redirect:/";
        }
        User u = userService.getUserByName(user.get("name"));
        List<CartInfo> cartInfoList = u.getCart();
        Map<String, String> image_map = new HashMap<>();
        for(CartInfo info:cartInfoList)
        {
            Image image = productService.findImages(info.getProductId()).get(0);
            image_map.put(image.getProductName(), image.getUrl());
        }
        modelMap.addAttribute("cartInfo", cartInfoList);
        modelMap.addAttribute("uu", u);
        modelMap.addAttribute("image_map", image_map);
        return "cart.ftl";
    }

    @RequestMapping("/purchase")
    public String purchase(HttpSession session,ModelMap modelMap)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还未登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if(!"user".equals(user.get("role")))
        {
            logger.error("您没有权限进行该操作！");
            return "redirect:/";
        }
        userService.purchase(user.get("name"));
        return "redirect:/user/cart";
    }

    @RequestMapping("/addcart/{id}")
    public String addToCart(@RequestParam("count") int count,@PathVariable("id") int productId,HttpSession session)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还未登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if(!"user".equals(user.get("role")))
        {
            logger.error("您没有权限进行该操作！");
            return "redirect:/";
        }
        userService.addToCart(user.get("name"),productId,count);
        return "redirect:/";
    }

    @RequestMapping("/removecart/{id}")
    public String removeFromCart(@PathVariable("id") int id,HttpSession session)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还未登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if(!"user".equals(user.get("role")))
        {
            logger.error("您没有权限进行该操作！");
            return "redirect:/";
        }
        userService.removeFromCart(user.get("name"),id);
        return "redirect:/user/cart";
    }
}
