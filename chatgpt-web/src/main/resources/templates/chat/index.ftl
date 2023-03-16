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
                        <div class="chat-cont-left">
                            <div class="chat-header">
                                <span>聊天</span>
                                <a href="javascript:void(0)" class="chat-compose">
                                    <i class="material-icons">control_point</i>
                                </a>
                            </div>
                            <form class="chat-search">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <i class="fas fa-search"></i>
                                    </div>
                                    <input type="text" id="chat-search" class="form-control" placeholder="搜索">
                                </div>
                            </form>
                            <div class="chat-list">
                                <div class="chat-scroll">
                                </div>
                            </div>
                        </div>
                        <div class="chat-cont-right">
                            <div class="chat-header hide">
                                <a id="back_chat_list" href="javascript:void(0)" class="back-chat-list">
                                    <i class="material-icons">chevron_left</i>
                                </a>
                                <div class="media">
                                </div>
                                <div class="chat-options dropdown">
                                    <a href="javascript:void(0)" role="button" data-toggle="dropdown">
                                        <i class="material-icons">more_vert</i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item d-flex justify-content-between align-items-center chat-delete" href="javascript:void(0);">删除会话<i class="far fa-trash-alt"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="chat-body">
                                <div class="chat-scroll hide">
                                    <input type="hidden" id="chat-date">
                                    <ul class="list-unstyled chat-list" id="chat-list">
                                    </ul>
                                </div>
                                <div class="chat-card-group">
                                    <div class="card-group m-5">
                                        <div class="card-group-header">
                                            <h2 class="card-group-title text-center">ChatGpt</h2>
                                        </div>
                                        <div class="row">
                                            <div class="col-12 col-md-6 col-lg-4 d-flex">
                                                <div class="card flex-fill">
                                                    <div class="card-header">
                                                        <h5 class="card-title text-center mb-2">AI 创作</h5>
                                                    </div>
                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item text-center"><a talk="写一首赞美祖国的诗，要求50字以上" href="javascript:void(0);" class="chat-talk">写一首赞美祖国的诗，要求50字以上 →</a></li>
                                                        <li class="list-group-item text-center"><a talk="写一个系统升级的公告" href="javascript:void(0);" class="chat-talk">写一个系统升级的公告 →</a></li>
                                                        <li class="list-group-item text-center"><a talk="写一份周报，50字以上" href="javascript:void(0);" class="chat-talk">写一份周报，50字以上 →</a></li>
                                                    </ul>
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-4 d-flex">
                                                <div class="card flex-fill">
                                                    <div class="card-header">
                                                        <h5 class="card-title text-center mb-2">有趣的问题</h5>
                                                    </div>
                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item text-center"><a talk="在什么情况下，老鼠可以吃掉大象" href="javascript:void(0);" class="chat-talk">在什么情况下，老鼠可以吃掉大象 →</a></li>
                                                        <li class="list-group-item text-center"><a talk="Ai 会替代人类工作吗？" href="javascript:void(0);" class="chat-talk">Ai 会替代人类工作吗？ →</a></li>
                                                        <li class="list-group-item text-center"><a talk="为什么有三八妇女节，却没有男人节？" href="javascript:void(0);" class="chat-talk">为什么有三八妇女节，却没有男人节？ →</a></li>
                                                    </ul>
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-4 d-flex">
                                                <div class="card flex-fill">
                                                    <div class="card-header">
                                                        <h5 class="card-title text-center mb-2">Ai百科</h5>
                                                    </div>
                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item text-center"><a talk="中国只有唯一一位女皇帝，你觉得这是为什么？" href="javascript:void(0);" class="chat-talk">中国只有唯一一位女皇帝，你觉得这是为什么？ →</a></li>
                                                        <li class="list-group-item text-center"><a talk="糖醋鲤鱼的做法" href="javascript:void(0);" class="chat-talk">糖醋鲤鱼的做法 →</a></li>
                                                        <li class="list-group-item text-center"><a talk="唐僧的老婆是谁" href="javascript:void(0);" class="chat-talk">唐僧的老婆是谁 →</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="chat-footer">
                                <div class="input-group">
                                    <input type="text" name="msg" class="input-msg-send form-control" placeholder="发送消息给AI...">
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
    <!-- 充值VIP Modal -->
    <div class="modal custom-modal fade none-border" id="vip">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header border-0">
                    <h4 class="modal-title text-center w-100">提示</h4>
                </div>
                <div class="modal-body">
                    <div class="text-center font-big-1-3 font-orange">
                        您没有使用次数，请开通VIP获得更多体验
                    </div>
                </div>
                <div class="modal-footer border-0 text-center">
                    <div class="submit-section">
                        <button class="btn btn-outline-secondary forward-btn" data-dismiss="modal">残忍拒绝</button>
                        <button class="btn btn-primary forward-btn" onclick="javascript:location.href='/vip.html'">去开通</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>