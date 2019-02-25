<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>用户登录</title>
    <link rel="stylesheet" href="/css/style.css"/>
    <script type="text/javascript" src="/js/util.js"></script>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
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
<form class="m-form m-form-ht n-login" id="loginForm" action="/api/login" method="post" onsubmit="return check()">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" name="username" id="username" type="text"/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" name="password" id="password"/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">角色：</label>
        <div class="fmipt">
            <input checked="checked" type="radio" name="role" value="user">&nbsp;&nbsp;用户
            <input type="radio" name="role" value="businessman">&nbsp;&nbsp;商家
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">登 录</button>
        </div>
    </div>
</form>

</body>
</html>