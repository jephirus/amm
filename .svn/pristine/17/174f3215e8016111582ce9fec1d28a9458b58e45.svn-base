var href;
var wtitle;
function open(wtitle,href) {
	$.layer({
		type : 2,
		title : ""+wtitle,
		iframe : {
			src : href
		},
		area : [ '800px', '650px' ],
		offset : [ '120px', '' ]
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