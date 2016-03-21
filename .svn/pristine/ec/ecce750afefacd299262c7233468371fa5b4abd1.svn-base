var href;
function open(href) {
	$.layer({
		type : 2,
		title : '考核数据查看',
		iframe : {
			src : href
		},
		area : [ '600px', '500px' ],
		offset : [ '120px', '' ]
	});
}

var id;
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