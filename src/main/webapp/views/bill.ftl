<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>账单</title>
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
        <h2>已购买的内容</h2>
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
            <th>购买时间</th>
            <th>购买数量</th>
            <th>购买价格</th>
        </tr>
        </thead>
        <tbody>
            <#assign total=0>
            <#list billList as bill>
                <tr>

                    <td><a href="/show/${bill.productId?c}">
                        <#if image_map[bill.productName]??>
                            <img src="${image_map[bill.productName]}" alt="">
                        <#else>
                            <img src="/images/broken.jpeg" alt="">
                        </#if>
                    </a></td>
                    <td><h4><a href="/show/${bill.productId?c}">${bill.productName}</a></h4></td>
                    <td><span class="v-time">${bill.shoppingTime}</span></td>
                    <td><span class="v-num">${bill.shoppingCount?c}</span></td>
                    <td><span class="v-unit">¥</span><span class="value">${bill.shoppingPrice?c}</span></td>
                </tr>
                <#assign total=total+bill.shoppingPrice*bill.shoppingCount>
            </#list>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="total">总计：</div>
            </td>
            <td><span class="v-unit">¥</span><span class="v-value">${total?c}</span></td>
        </tr>
        </tfoot>
    </table>
</div>
</html>