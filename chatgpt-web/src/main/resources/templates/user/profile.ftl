<!DOCTYPE html>
<html lang="en">
<head>
    <title>我的资料</title>
    <#include "/common/head.ftl"/>
</head>
<body>
<div class="main-wrapper">
    <#include "/common/header.ftl"/>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <#include "/user/side-bar.ftl"/>
                <div class="col-md-7 col-lg-8 col-xl-9">
                    <div class="card">
                        <div class="card-body">
                            <form action="">
                                <div class="row form-row">
                                    <div class="col-12 col-md-12">
                                        <div class="form-group">
                                            <div class="change-avatar">
                                                <input type="hidden" value="<#if currentUser?? && currentUser.face?? && currentUser.face!=''>${currentUser.face!''}</#if>" name="face" id="face">
                                                <div class="profile-img">
                                                    <img id="profile-img" src="<#if currentUser?? && currentUser.face?? && currentUser.face!=''>${currentUser.face!''}<#else>/static/img/human.jpeg</#if>" alt="用户头像">
                                                </div>
                                                <div class="upload-img">
                                                    <div class="change-photo-btn">
                                                        <span><i class="fa fa-upload"></i>上传头像</span>
                                                        <input type="file" class="upload fileinput">
                                                    </div>
                                                    <small class="form-text text-muted"> 允许jpg, jpeg, gif 或 png格式</small>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-12">
                                        <div class="form-group">
                                            <label>姓名</label>
                                            <input type="text" class="form-control" name="name" value="<#if currentUser??>${currentUser.name!''}</#if>">
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-12">
                                        <div class="form-group">
                                            <label>手机</label>
                                            <input type="text" class="form-control" name="mobile" value="<#if currentUser??>${currentUser.mobile!''}</#if>">
                                        </div>
                                    </div>
                                </div>
                                <div class="submit-section profile-save">
                                    <button type="button" class="btn btn-primary submit-btn col-12 col-md-1">保 存</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include "/common/footer.ftl"/>
    <script src="/static/js/upload.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $(".upload-img").filesUpload({
                url:'/upload/img',
                onUploadComplete:function(result) {
                    if(result.code == 0) {
                        $("#face").val(result.data.src);
                        $("#profile-img").attr("src", result.data.src);
                    }
                }
            });

        });
    </script>
</div>
</body>
</html>