<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <#include "/common/head.ftl"/>
    <style type="text/css">
        .layui-table-cell{
            height: auto;
        }
    </style>
</head>
<body class="pear-container">
<div class="layui-row layui-col-space10">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form" action="">
                    <div class="layui-form-item">
                        <div class="layui-form-item layui-inline">
                            <label class="layui-form-label">搜索关键字</label>
                            <div class="layui-input-inline">
                                <input type="text" name="q" placeholder="" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item layui-inline">
                            <button class="pear-btn pear-btn-md pear-btn-primary" lay-submit lay-filter="user-query">
                                <i class="layui-icon layui-icon-search"></i>
                                查询
                            </button>
                            <button type="reset" class="pear-btn pear-btn-md">
                                <i class="layui-icon layui-icon-refresh"></i>
                                重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-body">
                <table id="user-table" lay-filter="user-table"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="user-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
</script>
<script type="text/html" id="user-bar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i></button>
    <button class="pear-btn pear-btn-warming pear-btn-sm" lay-event="reset"><i
                class="layui-icon layui-icon-set"></i>
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove"><i class="layui-icon layui-icon-delete"></i></button>
</script>
<#include "/common/footer.ftl"/>
<script type="text/javascript">
    layui.use(['table', 'form', 'jquery'], function() {
        var table = layui.table;
        var form = layui.form;
        var $ = layui.jquery;

        var MODULE_PATH = "/user";

        var cols = [];
        var temp = {};
        temp.title = 'ID';
        temp.field = 'id';
        temp.width = 80;
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '头像';
        temp.field = 'face';
        temp.templet = function(data) {
            if(data) {
                return '<img src="'+data.face+'">';
            }
            return "";
        };
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '姓名';
        temp.field = 'name';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '手机';
        temp.field = 'mobile';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '性别';
        temp.field = 'sexStr';
        temp.align = 'center';
        cols.push(temp);
        var temp = {};
        temp.title = '创建时间';
        temp.field = 'createTime';
        temp.align = 'center';
        cols.push(temp);
        var btns = {};
        btns.title = '操作'
        btns.toolbar = '#user-bar'
        btns.align = 'center';
        btns.width = 180;
        cols.push(btns);
        var $cols = [];
        $cols.push(cols);

        table.render({
            elem: '#user-table',
            url: '/user/list',
            page: true,
            cols: $cols,
            skin: 'line',
            toolbar: '#user-toolbar',
            defaultToolbar: [],
            page: true,
            response: {
                statusCode: 0
            },
            parseData: function(res){
                return {
                    "code": res.code,
                    "msg": res.message,
                    "count": res.data.total,
                    "data": res.data.list
                };
            }
        });

        table.on('tool(user-table)', function(obj) {
            if (obj.event === 'remove') {
                window.remove(obj);
            } else if (obj.event === 'edit') {
                window.edit(obj);
            } else if (obj.event === 'reset') {
                window.reset(obj);
            }
        });

        table.on('toolbar(user-table)', function(obj) {
            if (obj.event === 'add') {
                window.add();
            }
        });

        form.on('submit(user-query)', function(data) {
            table.reload('user-table', {
                where: data.field
            })
            return false;
        });

        window.add = function() {
            layer.open({
                type: 2,
                title: '用户新增',
                shade: 0.1,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: MODULE_PATH + '/add.html'
            });
        }

        window.edit = function(obj) {
            layer.open({
                type: 2,
                title: '用户编辑',
                shade: 0.1,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: MODULE_PATH + '/' + obj.data['id'] + '.html'
            });
        }

        window.remove = function(obj) {
            layer.confirm('确定要删除该条数据？', {
                icon: 3,
                title: '提示'
            }, function(index) {
                layer.close(index);
                var loading = layer.load();
                $.ajax({
                    url: MODULE_PATH + "/delete/" + obj.data['id'],
                    dataType: 'json',
                    type: 'post',
                    success: function(result) {
                        layer.close(loading);
                        if (result.code == 0) {
                            layer.msg('操作成功～', {
                                icon: 1,
                                time: 1000
                            }, function() {
                                obj.del();
                            });
                        } else {
                            layer.msg(result.message, {
                                icon: 2,
                                time: 1000
                            });
                        }
                    }
                })
            });
        }

        window.reset = function(obj) {
            layer.confirm('确定重置密码？', {
                icon: 3,
                title: '提示'
            }, function(index) {
                layer.close(index);
                var loading = layer.load();
                $.ajax({
                    url: MODULE_PATH + '/' +obj.data['id'] + '/resetPassword',
                    dataType: 'json',
                    type: 'post',
                    success: function(result) {
                        layer.close(loading);
                        if (result.code == 0) {
                            layer.msg('操作成功～', {
                                icon: 1,
                                time: 1000
                            });
                        } else {
                            layer.msg(result.message, {
                                icon: 2,
                                time: 1000
                            });
                        }
                    }
                })
            });
        }

        window.refresh = function(param) {
            table.reload('user-table');
        }
    })
</script>
</body>
</html>
