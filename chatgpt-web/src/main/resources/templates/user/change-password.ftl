<!DOCTYPE html>
<html lang="en">
<head>
    <title>修改密码</title>
    <#include "/common/head.ftl"/>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<div class="main-wrapper">
    <#include "/common/header.ftl"/>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <#include "/user/side-bar.ftl"/>
                <div class="col-md-7 col-lg-8 col-xl-9 card-wapper">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12">
                                    <form action="">
                                        <div class="form-group">
                                            <label>旧密码</label>
                                            <input type="password" name="password" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>新密码</label>
                                            <input type="password" name="newPassword" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>确认密码</label>
                                            <input type="password" name="confirmPassword" class="form-control">
                                        </div>
                                        <div class="submit-section change-password">
                                            <button type="button" class="btn btn-primary submit-btn col-12 col-md-1">保 存</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include "/common/footer.ftl"/>
</div>
</body>
</html>