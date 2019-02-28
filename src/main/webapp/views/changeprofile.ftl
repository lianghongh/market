<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>修改个人信息</title>
    <link rel="stylesheet" href="/css/style.css"/>
    <script type="text/javascript" src="/js/util.js"></script>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <ul class="nav">
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<form class="m-form m-form-ht n-login" id="change" action="/api/changeprofile" method="post" onsubmit="return sub()">
    <div class="fmitem">
        <label class="fmlab">新用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" name="username" id="username" type="text"/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">新密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" name="password" id="password"/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">重新输入新密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" name="new_password" id="new_password"/>
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">提 交</button>
        </div>
    </div>
</form>

</body>
</html>