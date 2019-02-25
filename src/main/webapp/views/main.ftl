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
    <#if user?? && Session[user.name]??>
        <#if user.role=="user">
            <div class="user">
                <a href="/user/show">欢迎用户！${Session[user.name].name}</a>
            </div>
        <#elseif user.role=="businessman">
            <div class="user">
                <a href="/businessman/show">欢迎商家！${Session[user.name].name}</a>
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
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li class="z-sel" ><a href="/">所有内容</a></li>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <#list productList as p>
                <li id=${"p-"+p.productId}>
                    <a href="/show/${p.productId}" class="link">
                        <div class="img">
                            <img src="${p.images[0].url}" alt="${p.productName}">
                        </div>
                        <h3>${p.productName}</h3>
                        <div class="price">
                            <span class="v-unit">￥</span>
                            <span class="v-value">${p.productPrice}</span>
                        </div>
                    </a>
                </li>
            </#list>
        </ul>
    </div>
</div>
</body>
</html>