var course =  getDropdownData('COURSE');
var class_ =  getDropdownData('CLASS');

function initQuerySel() {
	$('#coursesel').append(setCourseSelect(course,''));
}
function initRateSel() {
	$('#courseselrate').append(setCourseSelect(course,''));
}

$(function() {
	initQuerySel();
	initRateSel();
});


function queryTeacher() {
	var teacherid = $('#teacherid').val();
	var teachername = $('#teachername').val();
	var course = $('#coursesel').val();
	if(!teacherid&&!teachername&&course=='-1') {
		$('#qResult').html('');
		alert('请至少填写一项查询条件');return;
	}
	course=(course=='-1'?'':course);
	$.post(
			basepath+'/teacherCourse/query',
			{teacherid:teacherid,teachername:teachername,courseid:course},
			function(data,status) {
				if(data) {
					if(data.length==0) {$('#qResult').html('<tr><td colspan="5" align="center">无结果</td></tr>');return;};
					if(data) {renderTc(data);}
				}
			},'json'
	);
}

function deleteTc(index,id) {
	if(id=='0') {alert('此数据暂无对应教师科目班级关系，无需删除');return;};
	$.post(
			basepath+'/teacherCourse/deleteTc',
			{id:id},
			function(data,status) {
				if(data) {alert('删除成功');$('#row'+index).remove();}
				else alert('删除失败');
			},'json'
	);
}

function renderTc(data) {

	var html = '';
	for(var i=0;i<data.length;i+=1) {
		var courseid = data[i].courseId||'';
		var classid = data[i].grade||'';
		var value = i+','+data[i].teacherId+','+data[i].teacherName+','+courseid;
		html += '<tr id="row'+i+'"><td><input type="radio" name="forAdd" value="'+value+'">'+data[i].teacherId+'</td>'
				  +'<td>'+data[i].teacherName+'</td>'
				  +'<td><select id="course'+i+'" disabled>'+setCourseSelect(course,courseid)+'</select></td>'
				  +'<td><select id="class'+i+'" disabled>'+setClassSelect(class_,classid)+'</select></td>'
				 // +'<td align="center"><a class="button radius tiny" onclick=save('+i+','+data[i].id+',"'+data[i].teacherId+'","'+data[i].teacherName+'","'+courseid+'","'+classid+'")>保存</a>'
				  //+'&nbsp;&nbsp;<a class="button radius tiny" onclick=deleteTc('+i+','+data[i].id+')>删除</a>'
				  //+'</td>'
				  ;
	}
	$('#qResult').html(html);
}

function addNewRow() {
	if($(':radio:checked').val()) {
		var value = $(':radio:checked').val().split(',');
		var html = getNewColumn(value[1],value[2],value[3]);
		$('#row'+value[0]).after(html);
	}else {
		alert('请先勾选要添加的老师');
	}
}

function getNewColumn(teacherid,teachername,courseid) {
	html = '<tr><td>'+teacherid+'</td>'
	  +'<td>'+teachername+'</td>'
	  +'<td><select id="coursenew">'+setCourseSelect(course,courseid)+'</select></td>'
	  +'<td><select id="classnew">'+setClassSelect(class_,'')+'</select></td>'
	  +'<td align="center"><a class="button tiny" onclick=save("new",'+0+',"'+teacherid+'","'+teachername+'","'+courseid+'","")>保存</a></td></tr>';
	return html;	
}

function save(rowid,id,teacherid,teachername,courseid,grade) {
	var newcourse = $('#course'+rowid).val();
	var newclass = $('#class'+rowid).val();
	if(newcourse=='-1'||newclass=='-1') {alert('请选择科目和班级');return;}
	if(newcourse==courseid&&newclass==grade) {return;}
	if(checkExist(newcourse,newclass)) {alert('此班此科目已经有老师任教，确认后重新选择');return;}
	var form = {
			id:id,
			teacherId:teacherid,
			teacherName:teachername,
			courseId:newcourse,
			grade:newclass
	};
	$.post(
			basepath+'/teacherCourse/updateTc',
			form,
			function(data,status) {
				if(data)alert('保存成功');
				else alert('保存失败');
			},'json'
	);
	
}


function setCourseSelect(selData,selected) {
	var html = '<option value="-1">请选择科目</option>';
	for(var i=0;i<selData.length;i+=1) {
		if(selected==selData[i].courseid) {
			html += '<option value="'+selData[i].courseid+'" selected="selected">'+selData[i].coursename+'</option>';
		}else {
			html += '<option value="'+selData[i].courseid+'">'+selData[i].coursename+'</option>';
		}
	}
	return html;
}
function setClassSelect(selData,selected) {
	var html = '<option value="-1">请选择班级</option>';
	for(var i=0;i<selData.length;i+=1) {
		if(selData[i].classid&&selected==selData[i].classid) {
			html += '<option value="'+selData[i].classid+'" selected="selected">'+selData[i].classname+'</option>';
		}else {
			html += '<option value="'+selData[i].classid+'">'+selData[i].classname+'</option>';
		}
	}
	return html;
}

function getDropdownData(classOrCourse) {
	var path = '';
	if(classOrCourse=='CLASS') path = '/class/query';
	if(classOrCourse=='COURSE') path = '/course/query';
	var result = null;
	$.ajax({
		type: 'POST',
		url: basepath+path,
		async:false,
		success:function(data) {
			result = data;
		}
	});
	return result;
}

function checkExist(courseid,classid) {
	var result = false;
	$.ajax({
		type: 'POST',
		url: basepath+'/teacherCourse/isExist',
		async:false,
		data:'courseid='+courseid+'&classid='+classid,
		success:function(data) {
			result = data;
		}
	});
	return result;
}

function reset() {
	$('#teacherid').val('');
	$('#teachername').val('');
	$('#qResult').html('');
	$('#coursesel').find('option[value="-1"]').attr('selected','selected');
}