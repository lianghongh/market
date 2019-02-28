<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>购物车</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
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
        <ul class="nav">
            <li><a href="/">首页</a></li>
            <#if Session?? && Session["user"]?? && Session["user"].role=="user">
                <li><a href="/user/bill">账单</a></li>
                <li><a href="/user/cart">购物车</a></li>
            </#if>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已添加到购物车的内容</h2>
    </div>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup>
            <col class="img"/>
            <col/>
            <col class="time"/>
            <col/>
            <col class="num"/>
            <col/>
            <col class="price"/>
            <col/>
        </colgroup>
        <thead>
        <tr>
            <th>商品图片</th>
            <th>商品名称</th>
            <th>购买数量</th>
            <th>购买价格</th>
            <th>购买时间</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <#assign totalPrice=0>
            <#list cartInfo as cart>
                <tr>
                    <td><a href="/show/${cart.productId}"><img src="${image_map[cart.productName]}" alt=""></a></td>
                    <td><h4><a href="/show/${cart.productId}">${cart.productName}</a></h4></td>
                    <td><span class="v-num">${cart.cartCount}</span></td>
                    <td><span class="v-unit">¥</span><span class="value">${cart.cartPrice}</span></td>
                    <td><span class="v-time">${cart.cartTime}</span></td>
                    <td><button class="u-btn u-btn-primary" id="delete-${cart.id}" onclick="del(${cart.id})">删除</button></td>
                </tr>
                <#assign totalPrice=totalPrice+cart.cartPrice*cart.cartCount>
            </#list>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="total">总计：</div>
            </td>
            <td><span class="v-unit">¥</span><span class="v-value">${totalPrice}</span></td>
        </tr>
        </tfoot>
    </table>
    <div id="act-btn"><a href="/"><button class="u-btn u-btn-primary" id="back">退出</button></a>
        <button class="u-btn u-btn-primary" id="shop" onclick="confirmBuy()">购买</button></div>
</div>
<script type="text/javascript">
    function confirmBuy() {
        if(confirm("您是否要购买这些商品？"))
        {
            var total="${totalPrice}";
            var balance="${uu.balance}";
            if(parseFloat(balance)<parseFloat(total))
                alert("您的余额不足！"+total+" balance:"+balance);
            else if(parseFloat(total)==0)
                alert("您的购物车是空的！请添加商品后再购买");
            else
                window.location.href = "/user/purchase";
        }
    }

    function del(id) {
        if(confirm("您确定从购物车中删除该商品?"))
            window.location.href="/user/removecart/"+id;
    }
</script>
</body>
</html>