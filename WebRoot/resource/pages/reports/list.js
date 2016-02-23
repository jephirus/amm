var href;
var title;
function open(href, title) {
	$.layer({
		type : 2,
		title : title,
		iframe : {
			src : href
		},
		area : [ '980px', '500px' ],
		offset : [ '50px', '' ]
	});
}