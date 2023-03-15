<div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
    <div class="profile-sidebar">
        <div class="widget-profile pro-widget-content">
            <div class="profile-info-widget">
                <a href="javascript:void(0);" class="profile-img">
                    <img src="<#if currentUser?? && currentUser.face?? && currentUser.face!=''>${currentUser.face!''}<#else>/static/img/human.jpeg</#if>" alt="用户头像">
                </a>
                <div class="profile-det-info">
                    <h3><#if currentUser??><#if currentUser.name?? && currentUser.name!=''>${currentUser.name!''}<#else>${currentUser.mobile!''}</#if></#if></h3>
                </div>
            </div>
        </div>
        <div class="dashboard-widget">
            <nav class="dashboard-menu">
                <ul>
                    <li>
                        <a href="/dashboard.html">
                            <i class="fas fa-columns"></i>
                            <span>充值中心</span>
                        </a>
                    </li>
                    <li>
                        <a href="profile.html">
                            <i class="fas fa-user-cog"></i>
                            <span>我的资料</span>
                        </a>
                    </li>
                    <li>
                        <a href="change-password.html">
                            <i class="fas fa-lock"></i>
                            <span>修改密码</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="logout">
                            <i class="fas fa-sign-out-alt"></i>
                            <span>退出登录</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>