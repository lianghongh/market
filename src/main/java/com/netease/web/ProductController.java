package com.netease.web;

import com.netease.dao.impl.ProductDaoImpl;
import com.netease.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("productController")
public class ProductController {

    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDaoImpl productDao;

    @RequestMapping("/")
    public String listProduct(ModelMap modelMap)
    {
        List<Product> list = productDao.getAllProducts();
        modelMap.addAttribute("productList", list);
        return "main.ftl";
    }

    @RequestMapping("/show/{id}")
    public String showProduct(@PathVariable("id") int productId,ModelMap modelMap)
    {
        Product p = productDao.getProductById(productId);
//        System.out.println(p);
        modelMap.addAttribute("product", p);
        return "product.ftl";
    }
}
