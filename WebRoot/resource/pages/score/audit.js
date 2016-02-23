var id;
function auditPass(id){
	if(confirm("是否审核通过？")){
		$.post($("#ctx").val()+'/khpj/score/pass/'+id+'.html',function(data){
			if(data == 'ok')
			{
				$("#"+id+"panel").remove();
				$("#"+id+"statue").html("审核通过");
				alert("审核操作成功！");
			}
			else
			{
				alert("审核操作失败！");
			}
		});
	}
}
var iwindows;
function refuse(id)
{
	iwindows = $.layer({
	    type : 1,
	    title : false,
	    offset:['150px' , ''],
	    border : false,
	    area : ['403px','245px'],
	    page : {dom : '#refuse'}
	});
	$("#sid").val(id);
}

function auditRefuse(){
	var id = $("#sid").val();
	if(confirm("是否审核拒绝？")){
		layer.close(iwindows);//关闭弹出层
		$.post($("#ctx").val()+'/khpj/score/refuse/'+id+'.html',{"refuseInfo":$("#refuseInfo").val()},function(data){
			if(data == 'ok')
			{
				$("#"+id+"panel").remove();
				$("#"+id+"statue").html("审核拒绝");
				alert("拒绝操作成功！");
			}
			else
			{
				alert("拒绝操作失败！");
			}
		});
	}
}

var href;
var wtitle;
function open(wtitle,href) {
	$.layer({
		type : 2,
		title : ""+wtitle,
		iframe : {
			src : href
		},
		area : [ '1020px', '600px' ],
		offset : [ '120px', '' ]
	});
}

function openChangePw() {
	$.layer({
		type : 2,
		title : '修改密码',
		iframe : {
			src : $("#ctx").val() + '/base/user/changePw.html'
		},
		area : [ '500px', '350px' ],
		offset : [ '100px', '' ]
	});
}

function tips(id)
{
	var content = "未提交";
	$.post($("#ctx").val()+"/base/department/viewDetails.html",{"deptId":id},function(data){
		if(data != '')
		{
			layer.alert(data,1,"单位详情");
		}
		else
		{
			layer.alert("未提交",0,"单位详情");
		}
	},"html");
}

var message;
function publish(href, message)
{
	//询问框
	$.layer({
	    shade : [1], //不显示遮罩
	    area : ['auto','auto'],
	    dialog : {
	        msg:message,
	        btns : 2, 
	        type : 4,
	        btn : ['确定','取消'],
	        yes : function(){
	        	var l = layer.load(0);
	        	$.post(href,function(data){
	        		if(data != '' && data == 'ok')
	        		{
	        			layer.alert("操作成功",1,"系统提示");
	        		}
	        		else
	        		{
	        			layer.alert("操作失败！请重试",0,"系统提示");
	        		}
	        	},"text");
	        	layer.close(l);
	        }
	    }
	});
}

function auditScoreBatch(href, message)
{
	//询问框
	$.layer({
	    shade : [1], //不显示遮罩
	    area : ['auto','auto'],
	    dialog : {
	        msg:message,
	        btns : 2, 
	        type : 4,
	        btn : ['确定','取消'],
	        yes : function(){
	        	var l = layer.load(0);
	        	window.location.href=href;
	        	layer.close(l);
	        }
	    }
	});
}