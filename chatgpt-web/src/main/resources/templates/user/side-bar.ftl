<div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
    <div class="profile-sidebar">
        <div class="widget-profile pro-widget-content">
            <div class="profile-info-widget">
                <a href="javascript:void(0);" class="profile-img">
                    <img src="/static/img/human.jpeg" alt="用户头像">
                </a>
                <div class="profile-det-info">
                    <h3></h3>
                    <div class="profile-details">
                        <h5 class="mb-0">BDS, MDS - Oral &amp; Maxillofacial Surgery</h5>
                    </div>
                </div>
            </div>
        </div>
        <div class="dashboard-widget">
            <nav class="dashboard-menu">
                <ul>
                    <li>
                        <a href="/vip.html">
                            <i class="fas fa-align-center"></i>
                            <span>充值中心</span>
                            <small class="forward"><i class="fas fa-angle-right"></i></small>
                        </a>
                    </li>
                    <li <#if requestURL=='/user/cost.html'>class="active"</#if>>
                        <a href="/user/cost.html">
                            <i class="fas fa-columns"></i>
                            <span>我的充值</span>
                            <small class="forward"><i class="fas fa-angle-right"></i></small>
                        </a>
                    </li>
                    <li <#if requestURL=='/user/profile.html'>class="active"</#if>>
                        <a href="/user/profile.html">
                            <i class="fas fa-user-cog"></i>
                            <span>我的资料</span>
                            <small class="forward"><i class="fas fa-angle-right"></i></small>
                        </a>
                    </li>
                    <li <#if requestURL=='/user/change-password.html'>class="active"</#if>>
                        <a href="/user/change-password.html">
                            <i class="fas fa-lock"></i>
                            <span>修改密码</span>
                            <small class="forward"><i class="fas fa-angle-right"></i></small>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="logout">
                            <i class="fas fa-sign-out-alt"></i>
                            <span>退出登录</span>
                            <small class="forward"><i class="fas fa-angle-right"></i></small>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
