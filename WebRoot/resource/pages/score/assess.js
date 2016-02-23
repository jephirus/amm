var href;
var wtitle;
function open(wtitle, href) {
	$.layer({
		type : 2,
		title : wtitle,
		iframe : {
			src : href
		},
		area : [ '1020px', '600px' ],
		offset : [ '20px', '' ]
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