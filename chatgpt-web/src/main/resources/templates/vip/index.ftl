<!DOCTYPE html>
<html lang="en">
<head>
    <title>充值中心</title>
    <#include "/common/head.ftl"/>
</head>
<body>

<div class="main-wrapper">
    <#include "/common/header.ftl"/>
    <div class="content">
        <div class="container vip-container">
            <div class="row">
                <div class="card flex-fill">
                    <div class="card-header border-0">
                        <h4 class="card-title text-center">订阅会员，获取AI聊天权限</h4>
                        <div class="font-weight-light small mt-3 text-center">不同套餐，发送的消息次数不同。</div>
                    </div>
                    <div class="card-body">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item border-0">
                                <div class="row">
                                    <#if vips?? && vips?size gt 0>
                                    <#list vips as vip>
                                        <div class="col-12 col-md-4 col-lg-4 d-flex">
                                            <div class="card vip-card flex-fill">
                                                <div class="card-body">
                                                    <input type="hidden" name="vipId" value="${vip.id!''}">
                                                    <h5 class="card-title text-center"><span class="price font-orange font-big-1-5" price="${vip.price!''}">¥ ${vip.price!''}</span><span class="small ml-1">/ ${vip.name!''}</span></h5>
                                                    <p class="card-text font-weight-light text-center">${vip.desc!''}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                    </#if>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-12 col-md-4 col-lg-4 d-flex">
                                        <div class="card flex-fill border-0">
                                            <div class="card-body">
                                                <ul class="list-unstyled mb-0 text-center font-weight-light">
                                                    <li class="mb-3">1、更快的响应速度</li>
                                                    <li class="mb-3">2、优先使用新功能</li>
                                                    <li class="mb-3">3、回答问题更智能</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-4 col-lg-4 d-flex">
                                        <div class="card flex-fill border-0">
                                            <div class="card-body">
                                                <ul class="list-group list-group-flush">
                                                    <li class="list-group-item pt-0 border-0">
                                                        <span class="font-weight-bold font-big-1-5">应付金额：</span>
                                                        <span class="font-orange font-big-1-3">¥ <span class="pay-amount"></span></span>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="row">
                                                            <div class="col-12 d-flex">
                                                                <button type="button" class="btn btn-primary submit-btn pay-btn col-12">支付宝支付</button>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include "/common/footer.ftl"/>
</div>
</body>
</html>