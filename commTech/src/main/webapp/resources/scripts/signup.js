function sign() {
	if(checkform()) {
		document.forms['signup'].submit();
	}else {
		alert('信息填写不正确，请重新填写');
	}
}

function reset() {
	document.forms['signup'].reset();
}

function checkform() {
	var studentid  = document.getElementById('studentid').value;
	var studentname  = document.getElementById('studentname').value;
	var classid  = document.getElementById('studentclass').value;
	var p1 = document.getElementById('p1').value;
	var p2 = document.getElementById('p2').value;
	if(studentid&&studentname&&classid!='-1'&&p1&&p2&&p1==p2) {
		return true;
	}else{
		return false;
	}
}

function isExist(studentid) {
	if(!studentid)return;
	$.post(
			basepath+'/isExist',
			{studentid:studentid},
			function(data,status) {
				if(data) {
					alert(studentid+'已经注册过，可直接登录');
					document.location=basepath+'/sign/signin?studentid='+studentid;
				}
			},'json'
	);
}


function initClassSel(selId) {
	$.post(
			basepath+'/class/query',
			function(data,status) {
				renderSel(selId, data);
			},'json'
	);
}

function renderSel(selId,data) {
	if(data) {
		var html = '';
		for(var  i=0;i<data.length;i+=1) {
			html += '<option value="'+data[i].classid+'">'+data[i].classname+'</option>';
		}
		$('#'+selId).append(html);
	}
}