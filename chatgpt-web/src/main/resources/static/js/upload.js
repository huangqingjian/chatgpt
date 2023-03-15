(function($) {
    $.fn.filesUpload = function(opts) {
        var defaults = {
            url: '',
            multiple: false,
            accept: '',
            fileTypes: 'png,jpg,jpeg,gif',
            onUploadStart: function() {},
            onUploadSuccess: function(result) {console.log(result)},
            onUploadComplete: function(result) {console.log(result)},
            onUploadError: function(result) {console.log(result)},
            onInit: function() {},
        };
        var option = $.extend(defaults, opts);
        var _self = this;
        var obj = {
            init: function() {
                this.fileInput = _self.find(".fileinput")[0];
                if(option.accept && option.accept != '') {
                    _self.find(".fileinput").attr("accept", option.accept)
                }
                if(option.multiple) {
                    _self.find(".fileinput").attr("multiple", "multiple")
                }
                this.onChange();
            },
            onChange: function() {
                var that = this
                this.fileInput.addEventListener("change", function(e) {
                    var files = e.target.files || e.dataTransfer.files;
                    var filterFile = that.filter(files);
                    that.upload(filterFile);
                }, false);
            },
            upload: function(files) {
                var that = this;
                var data;
                for(var j = 0; j < files.length; j++) {
                    var formData = new FormData();
                    formData.append("file", files[j]); //加入文件对象
                    data = formData;
                    that.send(j,data);
                }
            },
            send:function(index,file){
                var xhr = new XMLHttpRequest();
                if(xhr.upload) {
                    xhr.upload.addEventListener("progress", function(e) {
                        // 进度条
                    }, false);
                    xhr.addEventListener("load", function(e){
                        option.onUploadComplete(JSON.parse(xhr.response));
                    }, false);
                    xhr.addEventListener("error", function(e){
                        option.onUploadError(e);
                    }, false);
                    option.onUploadStart();
                    xhr.open("POST", option.url, true);
                    xhr.send(file);
                }
            },
            getSize: function(size) {
                var s;
                if(size < 1024) {
                    s = size + "B";
                } else {
                    s = size / 1024 >= 1024 ? (size / 1048576).toFixed(2) + 'M' : (size / 1024).toFixed(2) + "KB";
                }
                return s;
            },
            filter: function(files) {
                var fileType = option.fileTypes ? option.fileTypes.split(",") : '',
                    result = [];
                if(fileType) {
                    for(var i = 0; i < files.length; i++) {
                        if(fileType.indexOf(files[i].type.split("/")[1]) > -1) {
                            result.push(files[i])
                        } else {
                            result = [];
                            break;
                            return false;
                        }
                    }
                } else {
                    result = files;
                }
                return result;
            }
        };
        obj.init();
    }
})(jQuery);