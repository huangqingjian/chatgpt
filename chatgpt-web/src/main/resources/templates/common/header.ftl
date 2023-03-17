<header class="header">
    <nav class="navbar navbar-expand-lg header-nav">
        <div class="navbar-header">
            <a id="mobile_btn" href="javascript:void(0);">
                <span class="bar-icon">
                    <span></span>
                    <span></span>
                    <span></span>
                </span>
            </a>
            <a href="/" class="navbar-brand logo">
                <img src="/static/img/logo.png" class="img-fluid" alt="Logo">
            </a>
        </div>
        <div class="main-menu-wrapper">
            <div class="menu-header">
                <a href="index.html" class="menu-logo">
                    <img src="/static/img/logo.png" class="img-fluid" alt="Logo">
                </a>
                <a id="menu_close" class="menu-close" href="javascript:void(0);">
                    <i class="fas fa-times"></i>
                </a>
            </div>
            <ul class="main-nav" id="menus">
                <li>
                    <a href="/">首页</a>
                </li>
                <li>
                    <a href="/chat.html">聊天窗口</a>
                </li>
                <li>
                    <a href="/vip.html">充值中心</a>
                </li>
            </ul>
        </div>
        <ul class="nav header-navbar-rht">
            <#-- 未登录 -->
            <li class="nav-item no-log-item">
                <a class="nav-link header-login">登录/注册</a>
            </li>
            <#-- 已登录 -->
            <li class="nav-item dropdown has-arrow logged-item hide">
                <a href="javascript:void(0);" class="dropdown-toggle nav-link" data-toggle="dropdown">
                    <span class="user-img">
                        <img class="rounded-circle" src="/static/img/human.jpeg" width="31" alt="用户头像">
                    </span>
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <div class="user-header">
                        <div class="avatar avatar-sm">
                            <img src="/static/img/human.jpeg" alt="用户头像" class="avatar-img rounded-circle">
                        </div>
                        <div class="user-text">
                            <h6></h6>
                        </div>
                    </div>
                    <a class="dropdown-item" href="/vip.html">充值中心</a>
                    <a class="dropdown-item" href="/user/dashboard.html">我的面板</a>
                    <a class="dropdown-item logout" href="javascript:void(0);">登出</a>
                </div>
            </li>
        </ul>
    </nav>
</header>