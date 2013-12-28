<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> 				 <html class="no-js lt-ie9" data-ng-app="tecmgrApp"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" data-ng-app="tecmgrApp"> <!--<![endif]-->
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
<title>注册</title>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/foundation.css">
  <script src="<%=request.getContextPath() %>/resources/vendor/custom.modernizr.js"></script>
   <style type="text/css">
   body{
  	background-image: url("<%=request.getContextPath()%>/resources/images/navy_blue.png");
  }
 </style>
</head>
<body>

<div style="width: 30%;height: auto;margin: 10% auto;">
		 <div class="row">
            <h2 class="columns large-12" style="text-align: center;"><font face="楷体" color="white">注册</font></h2>
        </div>
		<form action="<%=request.getContextPath() %>/signup" method="post" name="signup">
	        <div class="row">
	            <label class="label">学号</label>
	            <input type="text" placeholder="请输入学号" name="studentid" id="studentid" onchange="isExist(this.value)">
	        </div>
	        <div class="row">
	            <label class="label">密码</label>
	            <input type="password" placeholder="请输入密码" name="studentpassword" id="p1">
	            <input type="password" placeholder="请再次输入密码" id="p2">
	        </div>
	        <div class="row">
	            <label class="label">姓名</label>
	            <input type="text" placeholder="请输入姓名" name="studentname" id="studentname">
	        </div>
	        <div class="row">
	            <label class="label">班级</label>
				<select id="studentclass" name="classid" class="label">
					<option value="-1">请选择班级</option>
				</select>	            
	        </div>
	        <div class="row">
	            <a class="small button large-4 columns" onclick="sign()">注册</a>
	            <a class="small button large-4 columns" onclick="reset()">清空</a>
	            <a class="small button large-4 columns" href="<%=request.getContextPath()%>/sign/signin">登录</a>
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
  <script src="<%=request.getContextPath() %>/resources/scripts/signup.js"></script>
  <script type="text/javascript">
  initClassSel('studentclass');
  </script>
</body>
</html>