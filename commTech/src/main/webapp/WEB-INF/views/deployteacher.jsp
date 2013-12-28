<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> 				 <html class="no-js lt-ie9" data-ng-app="tecmgrApp"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>教师-科目-年级</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/foundation.css">
  <script src="<%=request.getContextPath() %>/resources/vendor/custom.modernizr.js"></script>
   <style>
   	body{
   		background-color: rgba(0,0,0,0.1);
   	}
   	.td{text-align: center;vertical-align: baseline;}
      .text_left {text-align: left;}
        .mouseIn {
          background-color: lightgreen;
      }
  </style>
</head>
<body>

   <div style="width:96%;margin-left: 2%;margin-right: 2%;">
        <nav class="top-bar fixed" data-topbar>
            <ul class="title-area">
                <li class="name">
                    <h1><a>太原成成中学『教师科目年级分配』系统</a></h1>
                </li>
            </ul>
            <section class="top-bar-section">
                <!-- Right Nav Section -->
                <ul class="right">
                    <li class="active"><a>欢迎：<strong class="label">管理员</strong></a></li>
                </ul>
            </section>
        </nav>
        <hr>
        <div class="row">
        	<div class="large-4 columns">
        		 <label class="label">教职工编号</label>
	            <input type="text" placeholder="请输入教职工编号" name="teacherid" id="teacherid">        	
            </div>
        	<div class="large-4 columns">
        		 <label class="label">教师姓名</label>
	            <input type="text" placeholder="请输入教师姓名" name="teachername" id="teachername">        	
            </div>
        	<div class="large-3 columns">
				<a class="button" onclick="queryTeacher()">查询</a>				        		 
				<a class="button" onclick="reset()">清空</a>				        		 
            </div>
        </div>
		<hr>
		<div class="row">
				<div class="large-12 column">
					<table style="width:100%;table-layout: auto;" >
						<thead>
							<tr>
								<td>教职工编号</td>
								<td>教师姓名</td>
								<td>教授科目</td>
								<td>所在年级</td>
							</tr>
						</thead>
						<tbody id="qResult"></tbody>
					</table>
				</div>					
		</div>
		
	</div>

  <script src="<%=request.getContextPath() %>/resources/scripts/jquery-1.8.3.js"></script>
  <script src="<%=request.getContextPath() %>/resources/scripts/jquery.json-2.4.js"></script>
  <script src="<%=request.getContextPath() %>/resources/scripts/foundation.min.js"></script>
  <script type="text/javascript">var basepath="<%=request.getContextPath()%>";$(document).foundation();</script>
   <script src="<%=request.getContextPath() %>/resources/scripts/deployteacher.js"></script>
</body>
</html>

