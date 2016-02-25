//传输数据

// 百度地图API功能;点击生成和拖动标注
var iscreatr = false;
var map;
var infoBox;
function initialize() {

	map = new BMap.Map("allmap", {
		minZoom : 12,
		maxZoom : 20
	}); // 创建Map实例

	// map.centerAndZoom(new BMap.Point(116.4035,39.915),15);
	// //初始化时，即可设置中心点和地图缩放级别。
	map.centerAndZoom("深圳", 8); // 初始化地图,设置中心点坐标和地图级别。
	map.enableScrollWheelZoom(true);// 鼠标滑动轮子可以滚动

	var menu = new BMap.ContextMenu(); // 给地图添加右键菜单

	// 点击标注后显示信息窗口
	var popWindow = [
					"<div class='pageContent' layoutH='22' style='background:#ffffff;'>",
					"<h2 class='contentTitle' align='center'>东震集团","</h2>",
						"<div class='pageFormContent'>",
							"<p style='width: 310px;'>",
								"<label style='text-align: right;padding:0px;width:90px'>控制器编号：</label>",
								"<input id='deviceCode' class='required' type='text' size='30'  />",
							"</p>",		
							"<p style='width: 310px;' id='proberCountPNode'>",
							"<label style='text-align: right;padding:0px;width:90px'>探测器数量：</label>",
								"<input id='proberCount' class='required' type='text' size='30' />",
							"</p>",	
							"<p style='width: 310px;' id='adminNamePNode'>",
								"<label style='text-align: right;padding:0px;width:90px'>区域名称：</label>",
								"<input id='areaName' class='required' type='text' size='30'  />",
							"</p>",	
							"<p style='width: 310px;' id='adminPhoneNumberPNode'>",
								"<label style='text-align: right;padding:0px;width:90px'>单位名称：</label>",
								"<input id='departmentName' class='required' type='text' size='30'  />",
							"</p>",	
							"<p style='width: 310px;' id='adminFunctionPNode'>",
								"<label style='text-align: right;padding:0px;width:90px'>外控制器数量：</label>",
								"<input id='attachDeviceCount' class='required' type='text' size='30' />",
							"</p>",	
							"<p style='width: 310px;' id='adminAddressPNode'>",
								"<label style='text-align: right;padding:0px;width:90px'>控制器装态：</label>",
								"<input id='status' class='required'  type='text' size='30'  />",
							"</p>",	
						"</div>",
						"<div class='formBar'>",
							"<ul>",
								"<li>",
									"<div class='button'>",
										"<div class='buttonContent'>",
											"<button id='closeBtn' type='button' class='close'>关闭</button>",
										"</div>",
									"</div>",
								"</li>",
							"</ul>",
						"</div>",
				"</div>",
			];
	
	// 添加控制器结点时，弹出窗口
	var addPopWindow = [
				"<div class='pageContent' layoutH='22' style='background:#ffffff;'>",
				"<h2 class='contentTitle' align='center'>东震集团",
				"</h2>",
						"<div class='pageFormContent'>",
							"<label style='margin-left:19px;text-align:right;padding:0px;width:160px'>请选择该结点对应的控制器：</label>",
							"<select id='deviceSelectNode' style='margin-left:19px;margin-top:5px;width:270px;'>",
							"</select>",
						"</div>",
						"<div class='formBar'>",
							"<ul>",
								"<li id='saveLi'>",
									"<div class='buttonActive'>",
										"<div class='buttonContent'>",
											"<button id='saveBtn' type='submit'>保存</button>",
										"</div>",
									"</div>",
								"</li>",
								"<li>",
									"<div class='button'>",
										"<div class='buttonContent'>",
											"<button id='addCloseBtn' type='button' class='close'>关闭</button>",
										"</div>",
									"</div>",
								"</li>",
							"</ul>",
						"</div>",
				"</div>",
			];
	
	// 结点信息展示窗口
	infoBox = new BMapLib.InfoBox(
			map,
			popWindow.join(""),
			{
				boxStyle : {
					width : "660px",
					height : "150px"
				},
				closeIconMargin : "6px 6px 0 0",
				enableAutoPan : true,
				align : INFOBOX_AT_TOP
			});
	
	// 添加控制器结点界面
	var addInfoBox = new BMapLib.InfoBox(
			map,
			addPopWindow.join(""),
			{
				boxStyle : {
					width : "300px",
					height : "100px"
				},
				closeIconMargin : "6px 6px 0 0",
				enableAutoPan : true,
				align : INFOBOX_AT_TOP
			});
	
	var txtMenuItem = [ {
		text : '添加控制器结点',
		callback : function(e) {
			var marker = new BMap.Marker(e, {
            // 指定Marker的icon属性为Symbol
                icon: new BMap.Symbol(BMap_Symbol_SHAPE_POINT, {
                          scale: 0.9,          // 图标缩放大小
                          fillColor: 'green',  // 填充颜色
                          fillOpacity: 1       // 填充透明度
                      }),
                title : '控制器信息'
            }), px = map.pointToPixel(e);
			addClickHandler(marker); // 给该覆盖物添加点击事件
			addInfoBox.open(marker);
			
			// 加载控制器列表
			$.ajax({
				url:'index/PointInfo/getDevices.php',
				type : 'POST',
				success:function(data) {
					//alert(data);
					var options;
					var jsonList = eval("(" + data + ")");
					//alert(jsonList);
					for ( var i = 0; i < jsonList.length; i++) {
						options+= "<option value="+jsonList[i]['deviceId']+">"+jsonList[i]['deviceName']+"</option>";
					} 
					$("#deviceSelectNode").html(options);
				}
			});
				
			// -------------帮定事件--------------------
				
			$('#closeBtn').click(function() {
				infoBox.close();
			});
			
			$('#addCloseBtn').click(function() {
				addInfoBox.close();
			});
			
			$('#saveBtn').click(function() {
				$.ajax({
					url : 'index/PointInfo/savePointInfo.php',
					type : 'POST',
					data : {
						deviceId : $("#deviceSelectNode").val(),
						longitude : e.lng,
						latitude : e.lat
					},
					dataType : 'json',
					success : function(data) {
						alertMsg.correct('您的数据提交成功！');
						map.addOverlay(marker);// 将标注添加到地图中
						setTimeout(function () {
							infoBox.close();
					    }, 800);
						addInfoBox.close();  // 关闭当前窗口
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alertMsg.error('您提交的数据有误，请检查后重新提交！'+jqXHR);
					}
				});
			});
			
			marker.enableDragging();// 可拖拽
			marker.addEventListener("click", overlay_style);// 添加对应的事件
			marker.addEventListener("dragend", infoBox_show);// 添加拖拽事件,显示信息
		}

	} ];

	for ( var i = 0; i < txtMenuItem.length; i++) {
		var menuItem=new BMap.MenuItem(txtMenuItem[i].text,
				txtMenuItem[i].callback, 100);
		menu.addItem(menuItem);
	}
	
	map.addContextMenu(menu);
	
	//添加标注后单击地图删除标注
	 map.addEventListener("click",function(e){
	        	//menu.hide();
	    });
	// 获取marker的属性, 对应于右键弹出菜单项的响应事件
	function overlay_style(e) {
		var p = e.target;
		if (p instanceof BMap.Marker) {
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);// 拖拽位置的坐标
			var marker = new BMap.Marker(point);
			infoBox.open(marker);
		} else {
			alert("无法获知该覆盖物类型");
		}
	}

	function infoBox_show(e) {
		var point = new BMap.Point(e.point.lng, e.point.lat);// 拖拽位置的坐标
		var marker = new BMap.Marker(point);
		infoBox.open(marker);
	}

	var removeMarker = function(e,ee,marker){
		map.removeOverlay(marker);
	}
	 
	// 定义并添加控件
	var top_left_control = new BMap.ScaleControl({
		anchor : BMAP_ANCHOR_TOP_LEFT
	});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();// 左上角，添加默认缩放平移控件
	map.addControl(top_left_control);
	map.addControl(top_left_navigation);

	// 当系启动时，加载已标注的报警器结点
	$.get('index/PointInfo/getPointInfo.php', function(data) {

		var jsonList = eval("(" + data + ")");
		for ( var i = 0; i < jsonList.length; i++) {
			var point = new BMap.Point(jsonList[i]['longitude'], jsonList[i]['latitude']); // 点击位置的坐标
			var marker = new BMap.Marker(point, {
            // 指定Marker的icon属性为Symbol
                icon: new BMap.Symbol(BMap_Symbol_SHAPE_POINT, {
                          scale: 0.9,          // 图标缩放大小
                          fillColor: jsonList[i]['statusColor'],  // 填充颜色
                          fillOpacity: 1       // 填充透明度
                      }),
                title : '控制器信息'
            });   // 创建标注
			map.addOverlay(marker); // 将标注添加到地图中
			addClickHandler(marker);
			var sessMarkName = jsonList[i]['longitude'] + '|' + jsonList[i]['latitude'];
			sessionStorage.setItem(sessMarkName,marker);
			var markerMenu=new BMap.ContextMenu();
			markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(marker)));
			// 创建右键菜单
			marker.addContextMenu(markerMenu);
			
		} // 加载已标注点结束

	});

} // end initiallize mothed

function loadScript() {
	var script = document.createElement("script");
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=FeoRVTzuiEzFowm1en3fx6bC&callback=initialize";
	document.body.appendChild(script);

}
/**
 * 当鼠标左键点击结点时，显示相关信息。
 * @param marker
 */
function addClickHandler(marker) {
	marker.addEventListener("click", function(e) {
		infoBox.close();//在点击标注时关闭其他菜单
		var marketpoint = marker.getPosition(); // 获取一个点
		var lng = marketpoint.lng;
		var lat = marketpoint.lat;
		$.ajax({
			url : "index/PointInfo/getPIByLL.php",
			type : 'POST',
			data : {
				longitude : lng,
				latitude : lat
			},
			success : function(data) {
				infoBox.open(marker);
				$('#closeBtn').click(function() {
					infoBox.close();
				});
				
				$("#deviceCode").val(data.deviceCode),
				$("#proberCount").val(data.proberCount),
				$("#areaName").val(data.areaName),
				$("#departmentName").val(data.departmentName),
				$("#status").val(data.status),
				$("#attachDeviceCount").val(data.attachDeviceCount)
			}
		});
	});
}
