<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>文件上传</title>
    <%@include file="head.jsp"%>
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script type="text/javascript">
        function a(){
            var macCode = $("#macCode").val();
            var listenPort = $("#listenPort").val();
            $.post('${pageContext.request.contextPath}/file/macReset',{macCode:macCode,listenPort:listenPort},function(result){
                if(result.success){
                    alert(result.msg);
                    return;
                }
                alert(result.msg);
            });
        }
    </script>
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
            <div class="form-group">
                <div class="panel-heading">
                    <h1>固件升级测试</h1>
                </div>
                <div class="panel-body">
                    机器码<input type="text" placeholder="请输入需要监听的机器码" id="macCode" name="macCode"/><br>
                    本机监听端口<input type="text" placeholder="请输入本机监听端口号" id="listenPort" name="listenPort"/><br>
                    <input type="button" value="开始监听70指令" id="startListen" onclick="a()"/>
                </div>
            </div>
    </div>
</div>
</body>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</html>