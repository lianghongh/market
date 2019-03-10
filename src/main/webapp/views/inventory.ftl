<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>商品库存管理</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            <a href="/businessman/profile">欢迎商家！${Session["user"].name}</a>
            <a href="/api/logout">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="/businessman/inventory">库存管理</a></li>
            <li><a href="/businessman/publishpage">发布</a></li>
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>库存</h2>
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
            <th>售卖数量</th>
            <th>商品剩余库存</th>
            <th>商品价格</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <#assign total=0>
            <#list inventories as inventory>
                <tr>

                    <td><a href="/show/${inventory.productId?c}">
                        <img src="${map[inventory.productId?c].images[0].url}">
                    </a></td>
                    <td><h4><a href="/show/${inventory.productId?c}">${map[inventory.productId?c].productName}</a></h4></td>
                    <td><span class="v-num">${inventory.hasSold?c}</span></td>
                    <#if inventory.count gt inventory.hasSold>
                        <td><span class="v-num">${(inventory.count-inventory.hasSold)?c}</span></td>
                    <#else>
                        <td><span class="v-num">0</span></td>
                    </#if>
                    <td><span class="v-unit">¥</span><span class="value">${map[inventory.productId?c].productPrice?c}</span></td>
                    <td><button class="u-btn u-btn-primary" id="delete-${inventory.productId?c}" onclick="del(${inventory.productId?c})">删除</button></td>
                </tr>
                <#if inventory.count gt inventory.hasSold>
                    <#assign total=total+(inventory.count-inventory.hasSold)*map[inventory.productId?c].productPrice>
                </#if>
            </#list>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="total">商品总价值：</div>
            </td>
            <td><span class="v-unit">¥</span><span class="v-value">${total?c}</span></td>
        </tr>
        </tfoot>
    </table>
</div>
<script>
    function del(id) {
        if(confirm("您确定从仓库中删除该商品?"))
            window.location.href="/businessman/removeinventory?id="+id;
    }
</script>
</body>
</html>