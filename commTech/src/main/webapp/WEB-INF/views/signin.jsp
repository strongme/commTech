<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> 				 <html class="no-js lt-ie9" data-ng-app="tecmgrApp"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" data-ng-app="tecmgrApp"> <!--<![endif]-->
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
<title>登录</title>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/foundation.css">
  <script src="<%=request.getContextPath() %>/resources/vendor/custom.modernizr.js"></script>
  <style type="text/css">
   body{
  	background-image: url("<%=request.getContextPath()%>/resources/images/navy_blue.png");
  }
 </style>
</head>
<body>

<div style="width: 30%;height: auto;margin: 13% auto;">
        <div class="row">
            <h2 class="columns large-12"><font face="楷体" color="white">成成中学评教系统</font></h2>
        </div>
		<form action="<%=request.getContextPath() %>/signin" method="post" name="signin">
	        <font style="color: red;"><%=request.getAttribute("tips")==null?"": request.getAttribute("tips")%></font>
	        <div class="row">
	            <label class="label">学号</label>
	            <input type="text" placeholder="请输入学号" name="studentid" id="studentid" onchange="isExist(this.value)" value="<%= request.getParameter("studentid")==null?"":request.getParameter("studentid")%>">
	        </div>
	        <div class="row">
	            <label class="label">密码</label>
	            <input type="password" placeholder="请输入密码" name="studentpassword" id="p1" onkeydown="javascript: if(event.keyCode==13) sign();">
	        </div>
	        <div class="row">
				<a class="small button large-4 columns" onclick="sign()">登录</a>
	            <a class="small button large-4 columns" onclick="reset_()">清空</a>
	            <a class="small button large-4 columns" href="<%=request.getContextPath()%>/sign/signup">注册</a>
	        </div>
	</form>
</div>


  <script>
  document.write('<script src=<%=request.getContextPath() %>/resources/' +
  ('__proto__' in {} ? 'vendor/zepto' : 'vendor/jquery') +
  '.js><\/script>')
  </script>
  <script type="text/javascript">var basepath="<%=request.getContextPath()%>";</script>
  <script src="<%=request.getContextPath() %>/resources/scripts/foundation.min.js"></script>
  <script src="<%=request.getContextPath() %>/resources/scripts/signin.js"></script>
</body>
</html>