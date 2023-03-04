window.rootPath = (function (src) {
    src = document.scripts[document.scripts.length - 1].src;
    return src.substring(0, src.lastIndexOf("/") + 1);
})();

layui.config({
    base: rootPath + "module/",
    version: "3.9.7"
}).extend({
    admin: "admin",
    menu: "menu",
    frame: "frame",
    tab: "tab",
    card: "card",
    convert: "convert",
    tinymce: "tinymce/tinymce",
    echarts: "echarts",
    encrypt: "encrypt",
    echartsTheme: "echartsTheme",
    document: "document",
    select: "select",
    xmSelect: "xm-select",
    drawer: "drawer",
    notice: "notice",
    step: "step",
    tag: "tag",
    popup: "popup",
    treetable: "treetable",
    dtree: "dtree",
    area: "area",
    count: "count",
    topBar: "topBar",
    button: "button",
    design: "design",
    common: "common",
    eleTree: "eleTree",
    dictionary: 'dictionary',
    json: 'json',
    cropper: "cropper",
    yaml: "yaml",
    theme: "theme",
    message: "message",
    toast: "toast",
    iconPicker: "iconPicker",
    tableSelect: "tableSelect",
    easymde: "easymde/easymde"
}).use(['layer', 'theme'], function () {
    layui.theme.changeTheme(window, false);
});
