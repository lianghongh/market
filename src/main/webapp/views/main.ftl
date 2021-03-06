<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>主页</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
    <#if Session?? && Session["user"]??>
        <#if Session["user"].role=="user">
            <div class="user">
                <a href="/user/profile">欢迎用户！${Session["user"].name}</a>
                <a href="/api/logout">[退出]</a>
            </div>
        <#elseif Session["user"].role=="businessman">
            <div class="user">
                <a href="/businessman/profile">欢迎商家！${Session["user"].name}</a>
                <a href="/api/logout">[退出]</a>
            </div>
        </#if>
    <#else>
        <div class="user">
            <a href="/login">[登录]</a>
        </div>
        <div class="user">
            <a href="/register">[注册]</a>
        </div>
    </#if>
        <ul class="nav">
            <#if Session?? && Session["user"]??>
                <#if Session["user"].role=="user">
                    <li><a href="/user/bill">账单</a></li>
                    <li><a href="/user/cart">购物车</a></li>
                <#elseif Session["user"].role=="businessman">
                    <li><a href="/businessman/inventory">库存管理</a></li>
                    <li><a href="/businessman/publishpage">发布</a></li>
                </#if>
            </#if>
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li><a href="/">所有内容</a></li>
                <#if Session?? && Session["user"]?? && Session["user"].role=="user">
                    <li><a href="/notbuy">未购买的内容</a></li>
                </#if>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <#list productList as p>
                <li id=${"p-"+p.productId?c}>
                    <a href="/show/${p.productId?c}" class="link">
                        <div class="img">
                            <img src="${p.images[0].url}" alt="${p.productName}">
                        </div>
                        <h3>${p.productName}</h3>
                        <div class="price">
                            <span class="v-unit">￥</span>
                            <span class="v-value">${p.productPrice?c}</span>
                        </div>
                        <#if Session?? && Session["user"]?? && Session["user"].role=="user" && isbuy?? && isbuy[p.productId?c]??>
                            <span class="had"><b>已购买</b></span>
                        </#if>
                        <#if Session?? && Session["user"]?? && Session["user"].role=="businessman" && issold?? && issold[p.productId?c]??>
                            <span class="had"><b>已售出</b></span>
                        </#if>
                    </a>
                </li>
            </#list>
        </ul>
    </div>
</div>
</body>
</html>