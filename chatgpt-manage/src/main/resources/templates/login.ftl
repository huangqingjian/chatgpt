<!DOCTYPE html>
<html>
<head>
    <#include "/common/head.ftl"/>
    <link rel="stylesheet" href="/static/admin/css/other/login.css" />
</head>
<body style="background-size: cover;">
<form class="layui-form" action="javascript:void(0);">
    <div class="layui-form-item" style="text-align: center;">
        <img class="logo" src="/static/admin/images/logo.png" />
        <div class="title">博客后端管理系统</div>
    </div>
    <input type="hidden" name="token" value="${token}"/>
    <div class="layui-form-item">
        <input type="text" placeholder="手机号 : " name="username" lay-verify="required" lay-reqText="请输入登录手机号" class="layui-input"  />
    </div>
    <div class="layui-form-item">
        <input type="password" placeholder="密 码 : "  name="password" lay-verify="required" lay-reqText="请输入密码" class="layui-input"  />
    </div>
    <div class="layui-form-item">
        <input placeholder="验证码 : " lay-verify="required" lay-reqText="请输入验证码" class="code layui-input layui-input-inline" name="captcha"/>
        <img src="/captcha/${token}/generate" id="captchaImage" class="codeImage" />
    </div>
    <div class="layui-form-item">
        <button type="button" class="pear-btn pear-btn-success login" lay-submit lay-filter="login">
            登 入
        </button>
    </div>
</form>
<#include "/common/footer.ftl"/>
<script type="text/javascript">
    layui.use(['form', 'button', 'popup'], function() {
        var $ = layui.jquery;
        var form = layui.form;
        var button = layui.button;
        var layer = layui.layer;
        var popup = layui.popup;
        var captchaPath = "/captcha/${token}/generate";

        // 登 录 提 交
        form.on('submit(login)', function(data) {
            var loader = layer.load();
            var btn = button.load({elem: '.login'});
            $.ajax({
                url: '/login',
                data: data.field,
                type: "post",
                dataType: 'json',
                success: function (result) {
                    layer.close(loader);
                    btn.stop(function () {
                        if (result.code == 0) {
                            location.href = "/";
                        } else {
                            popup.failure(result.message, function () {
                                $("#captchaImage").trigger('click');
                            });
                        }
                    })
                }
            });
            return false;
        });

        $("#captchaImage").click(function () {
            document.getElementById("captchaImage").src = captchaPath + "?" + Math.random();
        })
    })
</script>
</body>
</html>