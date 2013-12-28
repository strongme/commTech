/**
 * Created with JetBrains WebStorm.
 * User: walter
 * Date: 13-11-23
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */

$(function() {
    $(document).foundation();
    renderSubject(subjectList,$('#subject'));
    renderSubject(subjectList,$('#subjectTotal'));
    renderQuestion(questionData,subjectList,$('#questionArea'));
    renderTotalScore(subjectList,$('#subjectTotal'));
    
    
});

function checkIfComm(studentid) {
	$.post(
			basepath+'/comm/ifComm',
			{studentid:studentid},
			function(data,status) {
				if(data) {
					alert('您已经提交评教，无法再次提交');
					renderAlready(studentid);
					disableBtn();
				}
			},'json'
	);
}

function renderAlready(studentid) {
	$.post(
			basepath+'/comm/query',
			{studentid:studentid},
			function(data,status) {
				if(data) {
					renderDataAlready(data);
				}
			},'json'
	);
}

function renderDataAlready(data) {
	for(var i=0;i<data.length;i+=1) {
		$('#score_'+data[i].courseid).val(data[i].score);
	}
}

function submit(studentid) {
	if(ifComm==true)return;
	if(!confirm('确定提交？提交后无法修改'))return;
	var param = {
			extra:{studentid:studentid,comment:$('#extraIssue').val()},
			scs:[]
	};
	for(var i=0;i<subjectList.length;i+=1) {
		param.scs.push({studentid:studentid,courseid:subjectList[i].id,score:$('#score_'+subjectList[i].id).val()});
	}
	
	$.ajax({
        url :basepath+'/comm/save',
        type : "POST",
        contentType: "application/json; charset=utf-8",
        datatype:"json",
        data : $.toJSON(param),
        success : function(data, stats) {
        	if(data=='保存成功')disableBtn();
        	alert(data);
        }
	});
	
}

function _reset() {
	if(ifComm==true)return;
	$('#subject').remove().appendTo($('#commTableHead'));
    $('.forReset').each(function() {
        $(this).find('option:nth-child(1)').attr('selected','selected');
    });
    $('.forResetTotalScore').each(function() {
        $(this).val('0');
    });
    $('#extraIssue').val('');
}

function disableBtn() {
	ifComm = true;
	$('#subject').remove().appendTo($('#commTableHead'));
	$('#extraIssue').attr('disabled','disabled');
	$('#submit').addClass('disabled');
	$('#reset').addClass('disabled');
	 $('.forReset').each(function() {
	        $(this).attr('disabled','disabled');
    });
}