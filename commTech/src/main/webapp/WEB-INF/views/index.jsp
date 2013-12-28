<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> 				 <html class="no-js lt-ie9" data-ng-app="tecmgrApp"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>学生评教</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/foundation.css">
  <script src="<%=request.getContextPath() %>/resources/vendor/custom.modernizr.js"></script>
   <style>
body {
	background-image:
		url("<%=request.getContextPath()%>/resources/images/ecailles.png");
	background-color: rgba(0, 0, 0, 0.05);
}

.td {
	text-align: center;
	vertical-align: baseline;
}

.text_left {
	text-align: left;
}

.text_right {
	text-align: right;
}

.mouseIn {
	background-color: lightgreen;
}

.subjectHead {
	width: 60px;
}
</style>
</head>
<body onload="checkIfComm('${student.studentid}')">

   <div style="width:96%;margin-left: 2%;margin-right: 2%;">
        <nav class="top-bar fixed" data-topbar>
            <ul class="title-area">
                <li class="name">
                    <h1><a>太原成成中学『学生评教』</a></h1>
                </li>
            </ul>
            <section class="top-bar-section">
                <!-- Right Nav Section -->
                <ul class="right">
                    <li><a>客观</a></li>
                    <li><a>公正</a></li>
                    <li><a>严肃</a></li>
                    <li><a>准确</a></li>
                    <li><a>地评价老师是您成熟的表现！</a></li>
                    <li class="active"><a>欢迎：<strong class="label">${student.studentname}</strong>&nbsp;同学</a></li>
                     <li><a href="<%=request.getContextPath()%>">注销</a></li>
                </ul>
            </section>
        </nav>
        <table style="width:100%;table-layout: auto;">
            <thead id="commTableHead">
            <tr><td colspan="17" class="text_right">（请在下拉框中选择您的分值）</td></tr>
            <tr id="subject">
                <td>评价内容</td>
            </tr>
            </thead>
            <tbody id="questionArea">
            </tbody>
        </table>

        <table style="width:100%;table-layout: auto;">
            <tr><td class="label large-12" colspan="16">评分汇总</td></tr>

            <tr id="subjectTotal">
            </tr>

            <tr>
                <td colspan="16" style="text-align: center"><a class="button" id="submit" onclick="submit('${student.studentid}')">提交</a><a class="button" onclick="_reset()" id="reset">清空</a></td>
            </tr>
        </table>
    </div>



  <script src="<%=request.getContextPath() %>/resources/scripts/jquery-1.8.3.js"></script>
  <script src="<%=request.getContextPath() %>/resources/scripts/jquery.json-2.4.js"></script>
  <script src="<%=request.getContextPath() %>/resources/scripts/foundation.min.js"></script>
  <script type="text/javascript">var basepath="<%=request.getContextPath()%>";var studentid="${student.studentid}";</script>
	  <script src="<%=request.getContextPath() %>/resources/scripts/questionData.js"></script>
	<script src="<%=request.getContextPath() %>/resources/scripts/commentPage.js"></script>
	<script src="<%=request.getContextPath() %>/resources/scripts/run.js"></script>
</body>
</html>

