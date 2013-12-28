$(function() {
	renderResultTable();
});

function renderResultTable() {
	$.post(
			basepath+'/class/query',
			function(data,status) {
				renderClass(data);
			},'json'
	);
	function renderClass(data) {
		var htmlg1 = '<tr><th class="label">科目\\班级</th>';
		var htmlg2 = '<tr><th class="label">科目\\班级</th>';
		var htmlg3 = '<tr><th class="label">科目\\班级</th>';
		for(var i=0;i<data.length;i+=1) {
			if(data[i].classid.substring(0,1)=='1') {
				htmlg1 += '<th id="'+data[i].classid+'">'+data[i].classname+'</th>';
			}
			if(data[i].classid.substring(0,1)=='2') {
				htmlg2 += '<th id="'+data[i].classid+'">'+data[i].classname+'</th>';
			}
			if(data[i].classid.substring(0,1)=='3') {
				htmlg3 += '<th id="'+data[i].classid+'">'+data[i].classname+'</th>';
			}
		}
		htmlg1 += '</tr>';
		htmlg2 += '</tr>';
		htmlg3 += '</tr>';
		for(var i=0;i<subjectList.length;i+=1) {
			htmlg1 += '<tr><td id="'+subjectList[i].id+'_1">'+subjectList[i].name+'</td>';
			htmlg2 +='<tr><td id="'+subjectList[i].id+'_2">'+subjectList[i].name+'</td>';
			htmlg3 +='<tr><td id="'+subjectList[i].id+'_3">'+subjectList[i].name+'</td>';
			for(var j=0;j<data.length;j+=1) {
				if(data[j].classid.substring(0,1)=='1') {
					htmlg1 += '<td id="score割'+subjectList[i].id+'割'+data[j].classid+'" align="center" onmouseover=mouseInCell(this) onmouseout=mouseOutCell(this)>0</td>';
				}
				if(data[j].classid.substring(0,1)=='2') {
					htmlg2 +=  '<td id="score割'+subjectList[i].id+'割'+data[j].classid+'" align="center" onmouseover=mouseInCell(this) onmouseout=mouseOutCell(this)>0</td>';
				}
				if(data[j].classid.substring(0,1)=='3') {
					htmlg3 +=  '<td id="score割'+subjectList[i].id+'割'+data[j].classid+'" align="center" onmouseover=mouseInCell(this) onmouseout=mouseOutCell(this)>0</td>';
				}
			}
			htmlg1 += '</tr>';
			htmlg2 += '</tr>';
			htmlg3 += '</tr>';
		}
		$('#g1').append(htmlg1);
		$('#g2').append(htmlg2);
		$('#g3').append(htmlg3);
		//renderData();
	}
}

function mouseInCell(obj) {
	var idClass = obj.id.split('割')[2];
    var idSubject = obj.id.split('割')[1]+'_'+idClass.substring(0,1);
    $('#'+idSubject).addClass('mouseIn');
    $('#'+idClass).addClass('mouseIn');
}
function mouseOutCell(obj) {
	var idClass = obj.id.split('割')[2];
    var idSubject = obj.id.split('割')[1]+'_'+idClass.substring(0,1);
    $('#'+idSubject).removeClass('mouseIn');
    $('#'+idClass).removeClass('mouseIn');
}

function renderData() {
	$.post(
			basepath+'/statistic/census',
			function(data,status) {
				renderSocre(data);
			},'json'
	);
	function renderSocre(data) {
		for(var i=0;i<data.length;i+=1) {
			$('#score割'+data[i].courseid+'割'+data[i].classid).text(data[i].avgScore);
		}
	}
	
}

function renderExtraComment(start,total) {
	$.post(
			basepath+'/extra/query/'+start+'/'+total,
			function(data,status) {
				console.log(data)
				renderToPage(data,start);
			},'json'
	);
	function renderToPage(d,start) {
		var html = '';
		var data = d.data;
		for(var i=0;i<data.length;i+=1) {
			if(data[i].comment) {
				html += '<tr><td align="center">'+data[i].classname+'</td><td align="center">'+data[i].comment+'</td></tr>';
			}else {
				html += '<tr><td align="center">'+data[i].classname+'</td><td align="center">无</td></tr>';
			}
		}
		$('#g4').html(html);
		if(total==-1) {
			var pagingHtml = renderPageDropdown(parseInt(d.total));
			$('#pagingg4').html(pagingHtml);
		}
	}
}
var isRefreshing = false;

function renderTeacherScore(flag) {
	if(isRefreshing) {alert('统计中，请勿重复提交');return;}else {
		$('#courseselrate').attr('disabled','true');
		isRefreshing = true;
	}
	var courseid = $('#courseselrate').val();
	var param = {courseid:''};
	if(courseid!='-1') {param = {courseid:courseid};}
	$.post(
			basepath+'/statistic/censusTeacher',
			param,
			function(data,status) {
				renderToPage(data);
			},'json'
	);
	function renderToPage(data) {
		var html = '';
		for(var i=0;i<data.length;i+=1) {
			html += '<tr><td align="center">'+data[i].teacherId+'</td><td align="center">'+data[i].teacherName+'</td><td align="center">'+data[i].courseName+'</td><td align="center">'+data[i].avgScore+'</td><td align="center">'+data[i].countStudent+'</td></tr>';
		}
		$('#g5').html(html);
		if(flag&&flag==1)alert('刷新成功');
		isRefreshing = false;
		$('#courseselrate').removeAttr('disabled');
	}
}

function renderPageDropdown(totalCount) {
	var totalPage = parseInt(totalCount/15)+1;
	var html = '<select class="label" onchange="renderExtraComment(this.value,'+totalCount+')">';
	for(var i=0;i<totalPage;i+=1) {
		html += '<option value="'+(i+1)+'">第'+(i+1)+'页</option>';
	}
	html += '</select>';
	return html;
}

function cencusCommCount() {
	$.post(
			basepath+'/statistic/censusCommCount',
			{},
			function(data,status) {
				renderCommCount(data);
			},'json'
	);
}
function renderCommCount(data) {
	var html = '';
	for(var i=0;i<data.length;i+=1){
		html += '<tr>';
		html += '<td align="center">'+data[i].alreadyCount+'</td>';
		html += '<td align="center">'+data[i].className+'</td>';
		html += '<td align="center">'+data[i].totalCount+'</td>';
		html += '</tr>';
	}
	$('#g6').html(html);
}
