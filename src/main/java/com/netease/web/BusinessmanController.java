package com.netease.web;

import com.netease.domain.Businessman;
import com.netease.domain.Image;
import com.netease.domain.Inventory;
import com.netease.domain.Product;
import com.netease.service.impl.BusinessmanServiceImpl;
import com.netease.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/businessman")
@Controller("businessmanController")
public class BusinessmanController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessmanController.class);

    @Autowired
    @Qualifier("businessmanServiceImpl")
    private BusinessmanServiceImpl businessmanService;

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductServiceImpl productService;

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

    @RequestMapping("/product")
    public String modify(@RequestParam("id") int id,ModelMap modelMap){
        modelMap.addAttribute("productId", id);
        return "modify.ftl";
    }

    @RequestMapping("/publishpage")
    public String publishPage(){
        return "publish.ftl";
    }

    @RequestMapping("/edit")
    public String editProduct(HttpServletRequest request, @RequestParam("id") int productId)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null)
        {
            logger.error("您没有登录！");
            return "redirect:/";
        }

        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        Businessman businessman = businessmanService.getBusinessmanByName(user.get("name"));

        Product p = productService.getProduct(productId);
        p.setProductName(request.getParameter("title"));
        p.setProductAbstract(request.getParameter("summary"));
        p.setProductDescription(request.getParameter("detail"));
        p.setProductPrice(Double.parseDouble(request.getParameter("price")));
        Image image = new Image();
        image.setProductId(productId);
        image.setProductName(p.getProductName());
        if(request.getParameter("image")!=null)
        {
            String[] urlp=request.getParameter("image").split("/");
            image.setName(urlp[urlp.length-1]);
            image.setUrl(request.getParameter("image"));
        }
        else
        {
            image.setName(request.getParameter("name"));
            image.setUrl(request.getParameter("url"));
        }

        p.setImages(Arrays.asList(image));
        for(Inventory inventory:businessman.getInventoryList())
        {
            if(inventory.getProductId()==p.getProductId())
            {
                inventory.setProductId(p.getProductId());
                businessmanService.updateInventory(businessman.getNickname(),p.getProductId(),inventory);
                break;
            }
        }
        productService.updateInfo(p);
        return "redirect:/";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Image upload(@RequestParam("id") int productId, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还没有登录！");
            return null;
        }
        if(productService.getProduct(productId)!=null)
        {
            logger.error("已经存在相同的商品！");
            return null;
        }
        if(file!=null&&!file.isEmpty())
        {
            String name=file.getOriginalFilename();
            String url="images/"+name;
            file.transferTo(new File(session.getServletContext().getRealPath("")+url));
            Image image = new Image();
            image.setProductId(productId);
            image.setName(name);
            image.setUrl("/"+url);
            return image;
        }
        return null;
    }


    @RequestMapping("/publish")
    public String publishProduct(HttpServletRequest request,ModelMap modelMap)
    {
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null)
        {
            logger.error("您没有登录！");
            return "redirect:/";
        }

        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        Businessman businessman = businessmanService.getBusinessmanByName(user.get("name"));
        Product p = new Product();
        p.setProductId(Integer.parseInt(request.getParameter("id")));
        if(productService.getProduct(p.getProductId())!=null)
        {
            logger.error("已经存在相同的商品！");
            modelMap.addAttribute("productName", request.getParameter("title"));
            return "publish_error.ftl";
        }
        p.setProductName(request.getParameter("title"));
        p.setProductAbstract(request.getParameter("summary"));
        p.setProductDescription(request.getParameter("detail"));
        p.setProductPrice(Double.parseDouble(request.getParameter("price")));
        Image image = new Image();
        image.setProductId(p.getProductId());
        image.setProductName(p.getProductName());
        if(request.getParameter("image")!=null)
        {
            String[] images=request.getParameter("image").split("/");
            image.setName(images[images.length-1]);
            image.setUrl(request.getParameter("image"));
        }
        else
        {
            image.setName(request.getParameter("name"));
            image.setUrl(request.getParameter("url"));
        }
        p.setImages(Arrays.asList(image));
        productService.addProduct(p);
        businessmanService.addInventory(businessman.getNickname(), p, Integer.parseInt(request.getParameter("count")));
        return "redirect:/";
    }

    @RequestMapping("/inventory")
    public String showInventory(HttpSession session,ModelMap modelMap)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还没有登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        Businessman businessman = businessmanService.getBusinessmanByName(user.get("name"));
        for(Inventory inventory:businessman.getInventoryList())
            map.put(inventory.getProductId()+"",productService.getProduct(inventory.getProductId()));
        modelMap.addAttribute("inventories", businessman.getInventoryList());
        modelMap.addAttribute("map", map);
        System.out.println(map);
        return "inventory.ftl";
    }

    @RequestMapping("/removeinventory")
    public String removeInventory(HttpSession session,@RequestParam("id") int id)
    {
        if(session==null||session.getAttribute("user")==null)
        {
            logger.error("您还未登录！");
            return "redirect:/";
        }
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if(!"businessman".equals(user.get("role")))
        {
            logger.error("您没有权限进行该操作！");
            return "redirect:/";
        }
        businessmanService.removeFromInventory(user.get("name"),id);
        return "redirect:/businessman/inventory";
    }
}
