var href;
var wtitle;
function open(wtitle, href) {
	$.layer({
		type : 2,
		title : wtitle,
		iframe : {
			src : href
		},
		area : [ '1000px', '550px' ],
		offset : [ '20px', '' ]
	});
}