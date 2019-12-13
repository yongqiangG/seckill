<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>文件上传</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="panel panel-default text-center">
            <form action="${pageContext.request.contextPath}/file/fileupload"
                  method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="panel-heading">
                    <label for="exampleInputFile">请上传你的固件</label>
                    </div>
                    <div class="panel-body">
                    <input type="file" id="exampleInputFile" name="uploadFile">
                    </div>
                    <p class="help-block">点击上方按钮选择需要升级的固件</p>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</body>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</html>