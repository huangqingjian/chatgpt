<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <#include "/common/head.ftl"/>
    <link href="/static/admin/css/loader.css" rel="stylesheet"/>
    <link href="/static/admin/css/admin.css" rel="stylesheet"/>
    <style type="text/css">
        .layui-icon-triangle-d:before {
            content: "";
        }
        .layui-icon-triangle-d:after {
            content: "\e625";
        }
        .layui-nav-child dd {
            text-align: center;
        }
    </style>
</head>
<!-- 结 构 代 码 -->
<body class="layui-layout-body pear-admin">
<!-- 布 局 框 架 -->
<div class="layui-layout layui-layout-admin">
    <!-- 顶 部 样 式 -->
    <div class="layui-header">
        <!-- 菜 单 顶 部 -->
        <div class="layui-logo">
            <!-- 图 标 -->
            <img class="logo"></img>
            <!-- 标 题 -->
            <span class="title"></span>
        </div>
        <!-- 顶 部 左 侧 功 能 -->
        <ul class="layui-nav layui-layout-left">
            <li class="collapse layui-nav-item"><a class="layui-icon layui-icon-shrink-right" href="#"></a></li>
            <li class="refresh layui-nav-item"><a class="layui-icon layui-icon-refresh-1" href="#" loading=600></a></li>
        </ul>
        <!-- 多 系 统 菜 单 -->
        <div class="layui-layout-control" id="control"></div>
        <!-- 顶 部 右 侧 菜 单 -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide-xs"><a class="fullScreen layui-icon layui-icon-screen-full" href="#"></a></li>
            <li class="layui-nav-item layui-hide-xs message"></li>
            <li class="layui-nav-item user">
                <!-- 头 像 -->
                <a class="layui-icon layui-icon-username" href="javascript:;"></a>
                <!-- 功 能 菜 单 -->
                <dl class="layui-nav-child">
                    <dd><a user-menu-id="5555" user-menu-title="基本资料" user-menu-url="/system/user/center">基本资料</a></dd>
                    <dd><a class="logout" href="javascript:void(0);">注销登录</a></dd>
                </dl>
            </li>
            <!-- 主 题 配 置 -->
            <li class="layui-nav-item setting"><a class="layui-icon layui-icon-more-vertical" href="#"></a></li>
        </ul>
    </div>
    <!-- 侧 边 区 域 -->
    <div class="layui-side layui-bg-black">
        <!-- 菜 单 顶 部 -->
        <div class="layui-logo">
            <!-- 图 标 -->
            <img class="logo">
            <!-- 标 题 -->
            <span class="title"></span>
        </div>
        <!-- 菜 单 内 容 -->
        <div class="layui-side-scroll">
            <div id="sideMenu"></div>
        </div>
    </div>
    <!-- 视 图 页 面 -->
    <div class="layui-body">
        <!-- 内 容 页 面 -->
        <div id="content"></div>
    </div>
    <!-- 页脚 -->
    <div class="layui-footer layui-text">
        <span class="left">
            <span>
            </span>
        </span>
        <span class="center"></span>
        <span class="right"></span>
    </div>
    <!-- 遮 盖 层 -->
    <div class="pear-cover"></div>
    <!-- 加 载 动 画 -->
    <div class="loader-main">
        <!-- 动 画 对 象 -->
        <div class="loader"></div>
    </div>
</div>
<!-- 移 动 端 便 捷 操 作 -->
<div class="pear-collapsed-pe collapse">
    <a href="#" class="layui-icon layui-icon-shrink-right"></a>
</div>
<!-- 依 赖 脚 本 -->
<#include "/common/footer.ftl"/>
<script th:inline="javascript">
    layui.use(['admin', 'jquery', 'layer'], function () {
        var admin = layui.admin;
        var layer = layui.layer;
        var $ = layui.jquery;

        $('.logout').click(function(){
            $.ajax({
                url: '/logout',
                dataType: 'json',
                contentType: 'application/json',
                type: 'post',
                success: function (result) {
                    if (result.code == 0) {
                        location.href="/";
                    } else {
                        layer.msg(result.message, {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            })
        });

        // 框架初始化时会读取 根目录下 pear.config.yml 文件配置
        // 你可以通过 admin.setConfigPath 方法修改配置文件位置
        // 你可以通过 admin.setConfigType 方法修改配置文件类型
        admin.setConfigType("yml");
        admin.setConfigPath("/static/pear.config.yml");
        admin.render();

    })
</script>
</body>
</html>
