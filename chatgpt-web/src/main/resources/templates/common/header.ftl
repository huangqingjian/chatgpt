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
                    <a class="dropdown-item" href="/user/dashboard.html">充值中心</a>
                    <a class="dropdown-item" href="/user/profile.html">个人设置</a>
                    <a class="dropdown-item logout" href="javascript:void(0);">登出</a>
                </div>
            </li>
        </ul>
    </nav>
</header>