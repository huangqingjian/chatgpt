<!DOCTYPE html>
<html lang="en">
<head>
    <title>用户新增</title>
    <#include "/common/head.ftl"/>
</head>
<body>
<form class="layui-form" action="">
    <div class="mainBox">
        <div class="main-container">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="required" lay-reqText="请输入姓名" autocomplete="off" placeholder="请输入姓名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <input type="text" class="layui-hide" name="face" id="face" lay-verify="required" lay-reqText="请上传头像">
                <label class="layui-form-label">头像</label>
                <div class="layui-input-block">
                    <div class="layui-upload">
                        <button type="button" class="layui-btn" id="upload" lay-filter="upload" lay-showpercent="yes">上传头像</button>
                        <div class="layui-word-aux ">图片尺寸 100*100</div>
                        <div class="layui-upload-list layui-hide" id="upload-list">
                            <img class="layui-upload-img" id="upload-img" style="width: 94px;">
                        </div>
                        <div class="layui-hide" style="width: 94px;" id="process-bar">
                            <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="process">
                                <div class="layui-progress-bar" lay-percent=""></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="2" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-block">
                    <input type="text" name="mobile" lay-verify="required" lay-reqText="请输入手机" autocomplete="off" placeholder="请输入手机" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <textarea name="desc" placeholder="请输入描述" lay-verify="required" lay-reqText="请输入描述" class="layui-textarea"></textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit="" lay-filter="admin-save">
                <i class="layui-icon layui-icon-ok"></i>
                提交
            </button>
            <button type="reset" class="pear-btn pear-btn-sm">
                <i class="layui-icon layui-icon-refresh"></i>
                重置
            </button>
        </div>
    </div>
</form>
<#include "/common/footer.ftl"/>
<script type="text/javascript">
    layui.use(['form', 'jquery', 'upload', 'element'], function() {
        var form = layui.form;
        var $ = layui.jquery;
        var upload = layui.upload;
        var element = layui.element;

        form.on('submit(admin-save)', function(data) {
            $.ajax({
                url: '/manager/save',
                data: JSON.stringify(data.field),
                dataType: 'json',
                contentType: 'application/json',
                type: 'post',
                success: function(result) {
                    if (result.code == 0) {
                        layer.msg("操作成功～", {
                            icon: 1,
                            time: 1000
                        }, function() {
                            parent.layer.close(parent.layer.getFrameIndex(window.name));
                            parent.layui.table.reload("admin-table");
                        });
                    } else {
                        layer.msg(result.message, {
                            icon: 2,
                            time: 1000
                        });
                    }
                }
            })
            return false;
        });

        $('#upload-img').click(function(){
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                content: '<img style="display: inline-block; width: 100%; height: 100%;" src="' + $(this).attr('src') + '">'
            });
        });

        var upload = upload.render({
            elem: '#upload'
            ,url: '/upload/img'
            ,before: function(obj){
                obj.preview(function(index, file, result){
                    $('#upload-img').attr('src', result);
                });
                $("#upload-list, #process-bar").removeClass('layui-hide');
                element.progress('process', '0%');
            }
            ,done: function(res){
                //如果上传失败
                if(res.code != 0){
                    return layer.msg('上传失败~');
                }
                layui.$('#face').val(res.data.src);
            }
            ,error: function(){
                layer.msg('上传失败~');
            }
            //进度条
            ,progress: function(n, elem, e){
                $("#upload-list, #process-bar").removeClass('layui-hide');
                element.progress('process', n + '%');
                if(n == 100){
                    layer.msg('上传完毕', {icon: 1});
                }
            }
        });
    })
</script>
</body>
</html>
