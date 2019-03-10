<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>商品</title>
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
                    <li><a href="/businessman/publishpage">发布</a></li>
                </#if>
            </#if>
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img">
            <img src="${product.images[0].url}" alt="${product.productName}">
        </div>
        <div class="cnt">
            <h2>${product.productName}</h2>
            <p class="summary">${product.productAbstract}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${product.productPrice?c}</span>
            </div>
            <div class="price">
                库存剩余：<span class="v-value" id="rest">${inv[product.productId?c]?c}</span>
            </div>
            <div class="num">
                购买数量：
                <button id="remove" type="button" class="lessNum" onclick="remove()"><a>-</a></button>
                <span class="totalNum" id="allNum">1</span>
                <button type="button" id="add" class="moreNum" onclick="add()"><a>+</a></button>
            </div>
            <#if Session?? && Session["user"]??>
                <#if Session["user"].role=="user">
                    <div class="oprt f-cb">
                        <button class="u-btn u-btn-primary" id="addCart" onclick="addcart()">
                            加入购物车
                        </button>
                    </div>
                <#elseif Session["user"].role=="businessman" && canEdit[product.productId?c]??>
                    <div class="oprt f-cb">
                        <a href="/businessman/product?id=${product.productId?c}">
                            <button class="u-btn u-btn-primary" id="addCart">
                                编 辑
                            </button>
                        </a>
                    </div>
                </#if>
            </#if>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
    ${product.productDescription}
    </div>
</div>
<script type="text/javascript">
    function add() {
        var num = parseInt(document.getElementById("allNum").textContent);
        num++;
        document.getElementById("allNum").innerHTML = num;
    }

    function remove() {
        var num = parseInt(document.getElementById("allNum").textContent);
        if (num > 0) {
            num--;
            document.getElementById("allNum").innerHTML = num;
        }
        else {
            alert("您还没有购买任何商品！");
        }
    }

    function addcart() {
        var count = parseInt(document.getElementById("allNum").textContent);
        var rest=parseInt(document.getElementById("rest").textContent);
        if (count>rest)
        {
            alert("商品的库存不足，请重新选择商品数量！");
            return;
        }
        if (confirm("您将添加" + count + "件该商品到购物车，是否继续？"))
            window.location.href = "/user/addcart/${product.productId?c}?count=" + count;
    }
</script>
</body>
</html>