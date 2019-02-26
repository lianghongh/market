<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>商品</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div><div class="n-head">
    <div class="n-head">
        <div class="g-doc f-cb">
            <div class="user">
                <a href="/login">[登录]</a>
            </div>
            <div class="user">
                <a href="/register">[注册]</a>
            </div>
            <ul class="nav">
                <li><a href="/">首页</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img">
            <img src="${product.images[0].url}" alt="${product.productName}" >
        </div>
        <div class="cnt">
            <h2>${product.productName}</h2>
            <p class="summary">${product.productAbstract}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${product.productPrice}</span>
            </div>
            <div class="num">
                购买数量：<button id="remove" type="button" class="lessNum" onclick="remove()"><a>-</a></button>
                <span class="totalNum" id="allNum">1</span>
                <button type="button" id="add" class="moreNum" onclick="add()"><a>+</a></button>
            </div>
            <#if Session?? && Session["user"]?? && Session["user"].role=="user">
                <div class="oprt f-cb">
                    <button class="u-btn u-btn-primary" id="addCart" onclick="addcart()">
                        加入购物车</button>
                </div>
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
        var num=document.getElementById("allNum").textContent;
        num++;
        document.getElementById("allNum").innerHTML=num;
    }

    function remove() {
        var num = document.getElementById("allNum").textContent;
        if(num>0){
            num--;
            document.getElementById("allNum").innerHTML=num;
        }
        else{
            alert("您还没有购买任何商品！");
        }
    }

    function addcart() {
        var count=document.getElementById("allNum").textContent;
        if(confirm("您将添加"+count+"件该商品到购物车，是否继续？"))
            window.location.href="/user/addcart/${product.productId}?count="+count;
    }
</script>
</body>
</html>