var id;
var iwindows;
function auditVicePass(id){
	if(confirm("是否审核通过？")){
		$.post($("#ctx").val()+'/khpj/auditScore/vicepass/'+id+'.html',function(data){
			if(data == 'ok')
			{
				$("#"+id+"panel").remove();
				$("#"+id+"statue").html("副书记审核通过");
				alert("审核操作成功！");
			}
			else
			{
				alert("审核操作失败！");
			}
		});
	}
}

function auditPass(id){
	if(confirm("是否审核通过？")){
		$.post($("#ctx").val()+'/khpj/auditScore/pass/'+id+'.html',function(data){
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

function viceRefuse(id)
{
	iwindows = $.layer({
	    type : 1,
	    title : false,
	    offset:['150px' , ''],
	    border : false,
	    area : ['403px','245px'],
	    page : {dom : '#viceRefuse'}
	});
	$("#viceSid").val(id);
}

function auditViceRefuse(){
	var id = $("#viceSid").val();
	if(confirm("是否审核拒绝？")){
		layer.close(iwindows);//关闭弹出层
		$.post($("#ctx").val()+'/khpj/auditScore/vicerefuse/'+id+'.html',{"refuseInfo":$("#viceRefuseInfo").val()}, function(data){
			if(data == 'ok')
			{
				$("#"+id+"panel").remove();
				$("#"+id+"statue").html("副书记审核拒绝");
				alert("拒绝操作成功！");
			}
			else
			{
				alert("拒绝操作失败！");
			}
		});
	}
}

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
		$.post($("#ctx").val()+'/khpj/auditScore/refuse/'+id+'.html',{"refuseInfo":$("#refuseInfo").val()},function(data){
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

/**
 * 书记年度审核分值发布
 */
var message;
var herf;
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