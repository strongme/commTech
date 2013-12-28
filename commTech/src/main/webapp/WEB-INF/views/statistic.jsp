<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> 				 <html class="no-js lt-ie9" data-ng-app="tecmgrApp"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>学生评教结果</title>
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
      select{
      	height: 32px;
      	text-align:center;
      }
  </style>
</head>
<body>

   <div style="width:96%;margin-left: 2%;margin-right: 2%;">
        <nav class="top-bar fixed" data-topbar>
            <ul class="title-area">
                <li class="name">
                    <h1><a>太原成成中学『学生评教』结果汇总</a></h1>
                </li>
            </ul>
            <section class="top-bar-section">
                <!-- Right Nav Section -->
                <ul class="right">
                    <li><a title="点击以统计各个年级学生提交的评分" onclick="renderData();">统计</a></li>
                    <li class="active"><a>欢迎：<strong class="label">管理员</strong></a></li>
                    <li><a href="<%=request.getContextPath()%>">注销</a></li>
                </ul>
            </section>
        </nav>
		
		<div class="section-container auto" data-section>
			<section class="active">
				<p class="title" data-section-title>
					<a>高一年级</a>
				</p>
				<div class="content" data-section-content>
					<table id="g1" style="width:100%;"></table>
				</div>
			</section>
			<section>
				<p class="title" data-section-title>
					<a>高二年级</a>
				</p>
				<div class="content" data-section-content>
					<table id="g2" style="width:100%;"></table>
				</div>
			</section>
			<section>
				<p class="title" data-section-title>
					<a>高三年级</a>
				</p>
				<div class="content" data-section-content>
					<table id="g3" style="width:100%;table-layout: auto;"></table>
				</div>
			</section>
			<section>
				<p class="title" data-section-title>
					<a onclick="cencusCommCount()">已统计学生数统计</a>
				</p>
				<div class="content" data-section-content>
					<table  style="width:100%;table-layout: auto;">
							<tr>
								<th align="center">已评教人数</th>
								<th align="center">班级名称</th>
								<th align="center">班级总人数</th>
							</tr>
							<tbody id="g6"></tbody>
					</table>
				</div>
			</section>
			<section>
				<p class="title" data-section-title>
					<a onclick="renderTeacherScore(0)">教师评分排名</a>
				</p>
				<div class="content" data-section-content>
					<a class="button large-12 columns" onclick="renderTeacherScore(1);">刷新评教排名</a>
					<table  style="width:100%;table-layout: auto;">
							<tr>
								<th align="center">教职工编号</th>
								<th align="center">教职工名称</th>
								<th align="center"><select class="label" id="courseselrate" onchange="renderTeacherScore(1)"  title="选择相应科目查看此科目老师评教分数排名"></select></th>
								<th align="center">评教平均分</th>
								<th align="center">已评教学生数</th>
							</tr>
							<tbody id="g5"></tbody>
					</table>
				</div>
			</section>
			<section>
				<p class="title" data-section-title>
					<a>教师代课信息</a>
				</p>
				<div class="content" data-section-content>
					<hr>
					<div class="row">
						<div class="large-3 columns">
							<label class="label">教职工编号</label> <input type="text"
								placeholder="请输入教职工编号" name="teacherid" id="teacherid">
						</div>
						<div class="large-3 columns">
							<label class="label">教师姓名</label> <input type="text"
								placeholder="请输入教师姓名" name="teachername" id="teachername">
						</div>
						<div class="large-2 columns">
							<label class="label">科目</label> <select id="coursesel"></select>
						</div>
						<div class="large-4 columns">
							<a class="button" onclick="queryTeacher()" title="保存修改内容">查询</a> 
							<!-- <a class="button" onclick="addNewRow()" title="勾选相应的行，添加相应老师信息">添加</a>  -->
							<a class="button" onclick="reset()">清空</a>
						</div>
					</div>
					<div class="row">
						<div class="large-12 column">
							<table style="width: 100%; table-layout: auto;">
								<thead>
									<tr>
										<td>教职工编号</td>
										<td>教师姓名</td>
										<td>教授科目</td>
										<td>所在年级</td>
										<!-- <td>操作</td> -->
									</tr>
								</thead>
								<tbody id="qResult"></tbody>
							</table>
						</div>
					</div>
				</div>
			</section>
			<section>
				<p class="title" data-section-title>
					<a onclick="renderExtraComment(1,-1)">意见建议</a>
				</p>
				<div class="content" data-section-content>
					<a href="<%=request.getContextPath()%>/extra/download" class="button small">导出为文件</a>	
					<span id="pagingg4" style="position: inherit;float:right; ">
					</span>
					<table style="width:100%;table-layout: auto;">
						<tr><th align="center">班级</th><th align="center">意见建议</th><tr>
						<tbody id="g4"></tbody>
						<tr></tr>
					</table>
				</div>
			</section>
		</div>
	</div>
  <script src="<%=request.getContextPath() %>/resources/scripts/jquery-1.8.3.js"></script>
  <script src="<%=request.getContextPath() %>/resources/scripts/jquery.json-2.4.js"></script>
  <script src="<%=request.getContextPath() %>/resources/scripts/foundation.min.js"></script>
   <script src="<%=request.getContextPath() %>/resources/scripts/questionData.js"></script>
  <script type="text/javascript">var basepath="<%=request.getContextPath()%>";$(document).foundation();</script>
  <script src="<%=request.getContextPath() %>/resources/scripts/statistic.js"></script>
   <script src="<%=request.getContextPath() %>/resources/scripts/deployteacher.js"></script>
</body>
</html>

