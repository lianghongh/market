<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>登录失败！</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="m-form m-form-ht n-login">
    <#if user.role=="user">
        <p class="fmitem">登录失败！用户<strong>${user.name}</strong>不存在或密码错误！</p>
    <#elseif user.role=="businessman">
        <p class="fmitem">登录失败！商家<strong>${user.name}</strong>不存在或密码错误！</p>
    <#else>
        <p>角色<strong>${user.role}不存在！</strong></p>
    </#if>
</div>

</body>
</html>