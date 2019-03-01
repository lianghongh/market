<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户详细信息</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div><div class="n-head">
    <div class="n-head">
        <div class="g-doc f-cb">
            <ul class="nav">
                <li><a href="/">首页</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="cnt">
            <table>
                <tr>
                    <td><h3>用户名：</h3></td>
                    <td><h2>${profile.nickname}</h2></td>
                </tr>
                <tr>
                    <td><h3>用户ID：</h3></td>
                    <td><h2>${profile.userId}</h2></td>
                </tr>
                <tr>
                    <td><h3>余额：</h3></td>
                    <td><div class="price">
                        <span class="v-unit">¥</span><span class="v-value">${profile.balance?c}</span>
                    </div></td>
                </tr>
                <tr>
                    <td><h3>最近一次登录时间：</h3></td>
                    <td><h2>${profile.loginTime}</h2></td>
                </tr>
                <tr>
                    <td><h3>最近一次登出时间：</h3></td>
                    <td><h2>${profile.logoutTime}</h2></td>
                </tr>
            </table>
            <div class="oprt f-cb">
                <button class="u-btn u-btn-primary" id="unregister" onclick="unregister()">
                    注销用户</button>
                <a href="/changeprofile"><button class="u-btn u-btn-primary" id="profile">
                    修改个人信息</button></a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function unregister() {
        if(confirm("注销后您的所有个人信息都将被清空且无法恢复，是否继续？"))
            window.location.href="/api/unregister";
    }

</script>

</body>
</html>