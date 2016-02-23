function openDepartment() {
	$.layer({
		type : 2,
		title : '单位概况信息维护',
		iframe : {
			src : $("#ctx").val() + '/base/department/details.html'
		},
		area : [ '600px', '500px' ],
		offset : [ '20px', '' ]
	});
}
var id;
function openScore(id) {
	$.layer({
		type : 2,
		title : '单位得分情况',
		iframe : {
			src : $("#ctx").val() + '/khpj/score/view/'+id+'.html'
		},
		area : [ '980px', '580px' ],
		offset : [ '50px', '' ]
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


var href;
var wtitle;
function open(href, wtitle) {
	$.layer({
		type : 2,
		title : wtitle,
		iframe : {
			src : href
		},
		area : [ '900px', '500px' ],
		offset : [ '50px', '' ]
	});
}