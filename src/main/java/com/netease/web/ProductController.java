package com.netease.web;

import com.netease.domain.*;
import com.netease.service.impl.BusinessmanServiceImpl;
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

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("productController")
public class ProductController {

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductServiceImpl productService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    @Qualifier("businessmanServiceImpl")
    private BusinessmanServiceImpl businessmanService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping("/")
    public String listProduct(HttpSession session,ModelMap modelMap)
    {
        List<Product> list = productService.listProducts();
        modelMap.addAttribute("productList", list);
        if(session==null||session.getAttribute("user")==null)
            return "main.ftl";
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if("user".equals(user.get("role")))
        {
            Map<String, String> isBuy = new HashMap<>();
            User u = userService.getUserByName(user.get("name"));
            for(ShoppingInfo info:u.getShoppingInfoList())
                isBuy.put(info.getProductId()+"", "true");
            modelMap.addAttribute("isbuy", isBuy);
        }
        else if("businessman".equals(user.get("role")))
        {
            List<User> userList=userService.getAllUsers();
            Businessman businessman = businessmanService.getBusinessmanByName(user.get("name"));
            Map<String,String> isSold=new HashMap<>();
            for(Inventory inventory:businessman.getInventoryList())
            {
                for(User uu:userList)
                {
                    for(ShoppingInfo info:uu.getShoppingInfoList())
                    {
                        if(info.getProductId()==inventory.getProductId())
                            isSold.put(""+inventory.getProductId(),"true");
                    }
                }
            }
            modelMap.addAttribute("issold", isSold);
        }
        return "main.ftl";
    }

    @RequestMapping("/show/{id}")
    public String showProduct(@PathVariable("id") int productId,ModelMap modelMap,HttpSession session)
    {
        Product p = productService.getProduct(productId);
        if(p==null)
        {
            logger.error("商品已被移除！");
            return "redirect:/";
        }
        modelMap.addAttribute("product", p);
        Map<String, Integer> inv = new HashMap<>();
        List<Businessman> businessmanList=businessmanService.getAllBusinessmans();
        boolean found=false;
        for(Businessman businessman:businessmanList)
        {
            for(Inventory inventory:businessman.getInventoryList())
            {
                if(inventory.getProductId()==productId)
                {
                    inv.put(""+inventory.getProductId(),inventory.getCount()-inventory.getHasSold());
                    found=true;
                    break;
                }
            }
            if(found)
                break;
        }
        modelMap.addAttribute("inv", inv);
        if(session!=null&&session.getAttribute("user")!=null)
        {
            Map<String, String> user = (Map<String, String>) session.getAttribute("user");
            if("businessman".equals(user.get("role")))
            {
                Map<String, String> canEdit = new HashMap<>();
                Businessman businessman = businessmanService.getBusinessmanByName(user.get("name"));
                for(Inventory inventory:businessman.getInventoryList())
                    canEdit.put("" + inventory.getProductId(), "true");
                modelMap.addAttribute("canEdit", canEdit);
            }
        }
        return "product.ftl";
    }

    @RequestMapping("/notbuy")
    public String showNotBuy(HttpSession session,ModelMap modelMap)
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
        List<Product> products=productService.notBuy(user.get("name"));
        modelMap.addAttribute("productList", products);
        return "main.ftl";
    }

}
