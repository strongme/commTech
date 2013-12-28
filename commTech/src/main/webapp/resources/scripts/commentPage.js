/**
 * Created with JetBrains WebStorm.
 * User: walter
 * Date: 13-11-23
 * Time: 上午11:48
 * To change this template use File | Settings | File Templates.
 */
var ifComm =false;
var curQuestionId = -1;
function renderSubject(subjectList,renderTarget) {
    var html = '';
    for(var i=0;i<subjectList.length;i+=1) {
        html += '<td id="'+subjectList[i].id+'" class="subjectHead" align="center">'+subjectList[i].name+'</td>';
    }
    renderTarget.append(html);
}


function renderQuestion(questionList,subjectList,renderTarget) {

    var html = '';
    for(var i=0;i<questionList.length;i+=1) {
        html += '<tr>';
        html += '<td class="text_left label">'+questionList[i].name.left+'</td>';
        html += '<td colspan="16" class="text_right">'+questionList[i].name.right+'</td>';
        html += '</tr>';
        for(var j=0;j<questionList[i].question.length;j+=1) {
            html += '<tr id="row_'+questionList[i].question[j].id+'">';
            html += '<td id="'+questionList[i].question[j].id+'">'+questionList[i].question[j].id+' '+questionList[i].question[j].content+'</td>';
            for(var k=0;k<subjectList.length;k+=1) {
                html += '<td id="'+questionList[i].question[j].id+'_'+subjectList[k].id+'" onmouseover=mouseInCell(this) onmouseout=mouseOutCell(this) onclick=selClick(this) onchange=calScore(this)>'+generateDropdown(questionList[i].question[j].min,questionList[i].question[j].max)+'</td>';
            }
            html += '</tr>';
        }
    }
    html += '<tr><td>你认为哪学科教学中存在什么问题？应该怎样改进？</td><td colspan="16"><textarea id="extraIssue" ></textarea></td></tr>';
    renderTarget.append(html);

}

function renderTotalScore(subjectList,renderTarget) {
    var html = '';
    for(var i=0;i<subjectList.length;i+=1) {
        html += '<td  align="center"><input class="forResetTotalScore" type="text" id="score_'+subjectList[i].id+'" value="90" readonly></td>';
    }
    renderTarget.after(html);
}

function generateDropdown(min,max) {
    var html = '<select class="forReset">';
    for(var i=min;i<=max;i+=1) {
    	if(i==(max-1)) {
    		html += '<option value="'+i+'" selected>'+i+'</option>'
    	}else {
    		html += '<option value="'+i+'">'+i+'</option>'
    	}
    }
    html += '</select>';
    return html;
}

function selClick(obj) {
	if(ifComm)return;
    var idSubject = obj.id.split('_')[1];
    var idQuestion = obj.id.split('_')[0];
    if(curQuestionId==idQuestion) return;
    curQuestionId=idQuestion;
    $('#subject').remove().insertBefore($('#row_'+idQuestion));
}

function mouseInCell(obj) {
    var idSubject = obj.id.split('_')[1];
    var idQuestion = obj.id.split('_')[0];
    $('#'+idSubject).addClass('mouseIn');
    $('#'+idQuestion).addClass('mouseIn');
}
function mouseOutCell(obj) {
    var idSubject = obj.id.split('_')[1];
    var idQuestion = obj.id.split('_')[0];
    $('#'+idSubject).removeClass('mouseIn');
    $('#'+idQuestion).removeClass('mouseIn');
}

function calScore(obj) {
    var idSubject = obj.id.split('_')[1];
    var idQuestion = obj.id.split('_')[0];
    var total = 0;
    for(var i=1;i<=questionCount;i+=1) {
        total += parseInt($('#'+i+'_'+idSubject).find('select').val())
    }
    $('#score_'+idSubject).val(total);
}

