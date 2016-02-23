var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea', {
		resizeType : 1,
		autoHeightMode : true,
		fullscreenMode : true,
		themeType : 'simple',
		readonlyMode : false,
		items : ['print']
	});
});