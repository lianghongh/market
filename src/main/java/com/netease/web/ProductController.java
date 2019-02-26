package com.netease.web;

import com.netease.domain.Product;
import com.netease.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("productController")
public class ProductController {

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductServiceImpl productService;

    @RequestMapping("/")
    public String listProduct(ModelMap modelMap)
    {
        List<Product> list = productService.listProducts();
        modelMap.addAttribute("productList", list);
        return "main.ftl";
    }

    @RequestMapping("/show/{id}")
    public String showProduct(@PathVariable("id") int productId,ModelMap modelMap)
    {
        Product p = productService.getProduct(productId);
        modelMap.addAttribute("product", p);
        return "product.ftl";
    }

    @RequestMapping("/notbuy")
    public ModelAndView showNotBuy(HttpSession session)
    {
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products=productService.notBuy(user.get("name"));
        modelAndView.addObject("productList", products);
        modelAndView.setViewName("main.ftl");
        return modelAndView;
    }

}
