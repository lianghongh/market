package com.netease;

import com.netease.dao.impl.ProductDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args)
    {
        ApplicationContext context=new ClassPathXmlApplicationContext("market-context.xml");
        ProductDaoImpl productDao=context.getBean("productDaoImpl",ProductDaoImpl.class);
        productDao.deleteProductById(23);
    }
}
