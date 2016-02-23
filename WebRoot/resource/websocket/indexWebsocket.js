// 用于实时信故障信息显示
    function connect() {
       	ws = new WebSocket('ws://127.0.0.1/amm/websocket');
        
        ws.onopen = function () {
            log('Info: connection opened.');
        };  
        ws.onmessage = function (event) {
        	var message = event.data.split('*#*');
            log('Received: ' + message[0]);
            
            var storage = window.localStorage;
            if(storage.getItem("areaId") === null || "undefined" != typeof storage.getItem("areaId")){
            	storage.setItem("areaId",message[3]);
            }
            // 刷新控制器页面
            if(message[3]){
        		if(message[3] === storage.getItem("areaId")){
        			navTab.openTab("deviceList","/amm/device/monitorList/"+message[3]+".php");
        		}
        	}
            
        	if (message[2] == "1")  // 报警
        	{
				$('<audio id="alarmAudio" loop="loop"><source src="http://127.0.0.1:888/amm/resource/websocket/alarm.mp3" type="audio/wav"></audio>').appendTo('body');
				$('#alarmAudio')[0].play();
			}
        	else if(message[2] == "2")  // 故障
			{
				$('<audio id="faultAudio" loop="loop"><source src="http://127.0.0.1:888/amm/resource/websocket/fault.mp3" type="audio/wav"></audio>').appendTo('body');
				$('#faultAudio')[0].play();
			}
        };  
        ws.onclose = function (event) {
            log('Info: connection closed.');
            log(event);
        };
    }

	// 用于websocket消息输出
    function log(message) {
        var console = document.getElementById('console');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        console.appendChild(p);
        while (console.childNodes.length > 25) {
            console.removeChild(console.firstChild);
        }
        console.scrollTop = console.scrollHeight;
    }
    
    function shutDown()
    {
    	if ($('#alarmAudio')[0] != null)
    	{
			$('#alarmAudio')[0].pause();
		}
    	else if($('#faultAudio')[0] != null)
   		{
    		$('#faultAudio')[0].pause();
   		}
    }