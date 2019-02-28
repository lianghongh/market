<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>编辑商品信息</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head><body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div><div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            <a href="/businessman/profile">欢迎商家！${Session["user"].name}</a>
            <a href="/api/logout">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="/businessman/publish">发布</a></li>
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div><div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>内容编辑</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post" onsubmit="return false;" action="/businessman/edit?id=${productId}">
            <div class="fmitem">
                <label class="fmlab">商品名称：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="title" placeholder="2-80字符"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要介绍：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="summary" placeholder="2-140字符"／>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">商品图片：</label>
                <div class="fmipt" id="uploadType">
                    <input name="pic" type="radio" value="url" checked /> 图片地址
                    <input name="pic" type="radio" value="file" /> 本地上传
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input class="u-ipt ipt"  name="image" placeholder="图片地址"/>
                </div>
                <div class="fmipt" id="fileUpload"  style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="fileUp"/>
                    <button class="u-btn u-btn-primary" id="upload">上传</button>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">商品描述：</label>
                <div class="fmipt">
                    <textarea class="u-ipt" name="detail" rows="10" placeholder="2-1000个字符"></textarea>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">商品价格：</label>
                <div class="fmipt">
                    <input class="u-ipt price" name="price"/>元
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button type="submit" class="u-btn u-btn-primary u-btn-lg">保 存</button>
                </div>
            </div>
        </form>
        <span class="imgpre"><img src="fdsaf" alt="" id="imgpre"></span>
    </div>
</div>

<script>
    (function(w,d,u){
        var form = document.getElementById("form");
        if(!form){
            return;
        }
        var title = form['title'];
        var summary = form['summary'];
        var image = form['image'];
        var detail = form['detail'];
        var price = form['price'];
        var uploadInput = form['file'];
        var isSubmiting = false;
        var imgpre = document.getElementById('imgpre');
        var imageUrl;
        var imageMode = "urlUpload";



        var page = {
            init:function(){
                var $ = function(id){
                    return document.getElementById(id);
                };

                $('uploadType').onclick = function(e){
                    e = window.event || e;
                    o = e.srcElement || e.target;
                    if(o.nodeName==="INPUT"){
                        var s,h;
                        o.value==='url'?(s='urlUpload',h='fileUpload'):(s='fileUpload',h='urlUpload');
                        imageMode = o.value==='url'?"urlUpload":"fileUpload";
                        image.classList.remove("z-err");
                        uploadInput.classList.remove("z-err");
                        $(s).style.display='block';
                        $(h).style.display='none';
                    }
                };

                $('upload').addEventListener('click', function (){

                    uploadInput.addEventListener('change', function() {
                        console.log(uploadInput.files) // File listing!
                    });
                    for (var i = 0, fileCount = uploadInput.files.length; i < fileCount; i++) {
                        console.log(uploadInput.files[i]);
                    }
                    var maxAllowedSize = 1000000;
                    var file = uploadInput.files[0];

                    if(uploadInput.files[0].size > maxAllowedSize) {
                        alert("超过文件上传大小限制");
                    }else{
                        var form = new FormData();
                        form.append('file', file, file.name);
                        form.enctype = "multipart/form-data";

                        var xhr = new XMLHttpRequest();
                        xhr.open("post", "/businessman/upload?id=${productId}", true);
                        xhr.onload = function () {
                            if (xhr.status === 200) {
                                alert("文件上传成功！");
                                var o = JSON.parse(xhr.responseText);
                                imageUrl = o && o.url;
                                var name= o && o.name;
                                image.value = imageUrl;
                                imgpre.src = imageUrl;
                                document.getElementById("form").action="/businessman/edit?id=${productId}&name="+name+"&url="+imageUrl;
                            } else {
                                alert('An error occurred!');
                            }
                        };
                        xhr.send(form);
                    }
                });
                form.addEventListener('submit',function(e){
                    if(!isSubmiting && this.check()){
                        price.value = Number(price.value);
                        isSubmiting = true;
                        form.submit();
                    }
                }.bind(this),false);
                [title,summary,image,detail,price].forEach(function(item){
                    item.addEventListener('input',function(e){
                        item.classList.remove('z-err');
                    }.bind(this),false);
                }.bind(this));
                image.addEventListener('input',function(e){
                    var value = image.value.trim();
                    if(value != ''){
                        imgpre.src = value;
                    }
                }.bind(this),false);
            },
            check:function(){
                var result = true;
                [
                    [title,function(value){return value.length<2 || value.length>80}],
                    [summary,function(value){return value.length<2 || value.length>140}],
                    [image,function(value){return imageMode == "urlUpload" && value == ''}],
                    [detail,function(value){return value.length<2 || value.length>1000}],
                    [price,function(value){return value == '' || !Number(value)}]
                ].forEach(function(item){
                    var value = item[0].value.trim();
                    if(item[1](value)){
                        item[0].classList.add('z-err');
                        result = false;
                    }
                    item[0].value = value;
                });
                if(imageMode == "fileUpload" && !imageUrl){
                    uploadInput.classList.add('z-err');
                    result = false;
                }
                return result;
            }
        };
        page.init();
    })(window,document);
</script>
</body>
</html>