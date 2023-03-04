<meta charset="utf-8">
<link rel="stylesheet" href="/static/component/pear/css/pear.css" />
<style type="text/css">
    body .tox-editor-container{
        background-color: #ffffff;
    }
    .layui-upload-list .upload-multi-item{
        display: inline-block;
        position: relative;
        margin-right: 10px;
    }
    .layui-upload-list .upload-multi-item .close{
        position: absolute;
        top: -10px;
        right: -10px;
        width: 20px;
        height: 20px;
    }
</style>
<script type="text/javascript">
    Date.prototype.format = function(format) {
        var o = {
            "M+": this.getMonth() + 1, //month
            "d+": this.getDate(), //day
            "h+": this.getHours(), //hour
            "m+": this.getMinutes(), //minute
            "s+": this.getSeconds(), //second
            "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
            "S": this.getMilliseconds() //millisecond
        }
        if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
            (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(format))
                format = format.replace(RegExp.$1,
                    RegExp.$1.length == 1 ? o[k] :
                        ("00" + o[k]).substr(("" + o[k]).length));
        return format;
    }
</script>