<!DOCTYPE html>
<html lang="en">
<head>
    <title>消费记录</title>
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
                    <div class="row">
                        <div class="col-md-12">
                            <div class="cost-tab">
                                <div class="tab-content">
                                    <div class="tab-pane show active">
                                        <div class="card card-table">
                                            <div class="card-body">
                                                <div class="table-responsive">
                                                    <table class="table table-hover table-center mb-0">
                                                        <thead>
                                                            <tr>
                                                                <th>名称</th>
                                                                <th>充值单号</th>
                                                                <th>充值时间</th>
                                                                <th class="text-center">金额</th>
                                                                <th>支付时间</th>
                                                                <th>支付状态</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="costs">
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="load-more load-more-cost text-center">
                                            <a class="btn btn-primary btn-sm" href="javascript:void(0);">点击加载更多</a>
                                        </div>
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