<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>商品</title>
    <link rel="stylesheet" href="/css/style.css"/>
    <script type="text/javascript">
        document.getElementById("add").onclick(
            function(e){
                var num=document.getElementById("allNum").textContent;
                num++;
                document.getElementById("allNum").innerHTML=num;
            }
        );
        document.getElementById("remove").onclick(
            function(e){
                var num=document.getElementById("allNum").textContent;
                if(num>0)
                {
                    num--;
                    document.getElementById("allNum").innerHTML=num;
                }
                else
                    alert("您还没有购买此产品");
            }
        );

    </script>
</head><body>
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
                购买数量：<span id="remove" class="lessNum"><a>-</a></span>
                <span class="totalNum" id="allNum"></span>
                <span id="add" class="moreNum"><a>+</a></span></div>
            <div class="oprt f-cb">
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${product.productDescription}
    </div>
</div>

</body>
</html>