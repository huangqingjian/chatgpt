<!DOCTYPE html>
<html lang="en">
<head>
    <title>聊天</title>
    <#include "/common/head.ftl"/>
</head>
<body class="chat-page">

<div class="main-wrapper">
    <#include "/common/header.ftl"/>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-12">
                    <div class="chat-window">
                        <div class="chat-cont-right">
                            <div class="chat-header">
                                <div class="media">
                                </div>
                                <div class="chat-options">
                                    <div class="coherented-toggle">
                                        <input type="checkbox" id="coherented" class="check" checked="">
                                        <label for="coherented" class="checktoggle"></label>
                                    </div>
                                </div>
                            </div>
                            <div class="chat-body">
                                <div class="chat-scroll">
                                    <input type="hidden" id="chat-date" value="${.now?string('yyyy-MM-dd')}">
                                    <ul class="list-unstyled chat-list" id="chat-list">
                                        <li class="chat-date">${.now?string('yyyy-MM-dd')}</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="chat-footer">
                                <div class="input-group">
                                    <input type="text" name="msg" class="input-msg-send form-control">
                                    <div class="input-group-append">
                                        <button type="button" class="btn msg-send-btn"><i class="fab fa-telegram-plane"></i></button>
                                    </div>
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