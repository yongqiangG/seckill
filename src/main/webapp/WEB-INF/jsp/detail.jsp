<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>${secKill.name}</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="panel-heading">
                <h1>${secKill.name}</h1>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <%--显示time图标--%>
                    <span class="glyphicon glyphicon-time"></span>
                    <span class="glyphicon" id="secKill-box"></span>
                </h2>
            </div>
        </div>
    </div>

    <%--电话注册模态框--%>
    <div id="killPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="killPhone" id="killPhone" placeholder="请输入您的手机号" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="killPhoneMessage" class="glyphicon"></span>
                    <button type="button" id="killPhoneBtn" class="btn btn-success">
                        <span class="glyphicon glyphcon-phone"></span>
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<%--cookie操作插件--%>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--倒计时插件--%>
<script src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script src="/seckill/resources/script/secKill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        secKill.detail.init({
            secKillId : ${secKill.secKillId},
            startTime: ${secKill.startTime.time},
            endTime: ${secKill.endTime.time}
        })
    })
</script>
</html>