<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>WebSocket/SockJS Echo Sample (Adapted from Tomcat's echo
	sample)</title>
<style type="text/css">
#connect-container {
	float: left;
	width: 400px
}

#connect-container div {
	padding: 5px;
}

#console-container {
	float: left;
	margin-left: 15px;
	width: 400px;
}

#console {
	border: 1px solid #CCCCCC;
	border-right-color: #999999;
	border-bottom-color: #999999;
	height: 170px;
	overflow-y: scroll;
	padding: 5px;
	width: 100%;
}

#console p {
	padding: 0;
	margin: 0;
}
</style>

<script src="${ctx}/resource/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/resource/websocket/sockjs.min.js" type="text/javascript"></script>

<script type="text/javascript">  
        var ws = null;  
        var url = null;  
        var transports = [];  
  
        function setConnected(connected) {  
            
            $('#connect').attr("disabled",connected);
            $('#echo').attr("disabled",!connected);
        }  
  
        function connect() {
           	ws = new WebSocket('ws://127.0.0.1:888/amm/websocket');
            
            ws.onopen = function () {  
                setConnected(true);  
                log('Info: connection opened.');  
            };  
            ws.onmessage = function (event) {  
                log('Received: ' + event.data);  
            };  
            ws.onclose = function (event) {  
                setConnected(false);  
                log('Info: connection closed.');  
                log(event);  
            };  
        }  
  
        function echo() {  
            if (ws != null) {  
                var message = $('#message').val();  
                log('Sent: ' + message);
                ws.send(message);  
            } else {  
                alert('connection not established, please connect.');  
            }  
        }  
  
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
    </script>
</head>
<body>
	<noscript>
		<h2 style="color: #ff0000">Seems your browser doesn't support
			Javascript! Websockets rely on Javascript being enabled. Please
			enable Javascript and reload this page!</h2>
	</noscript>
	<div>
		<div id="connect-container">
			<div>
				<button id="connect" onclick="connect();">Connect</button>
			</div>
			<div>
				<textarea id="message" style="width: 350px">Here is a message!</textarea>
			</div>
			<div>
				<button id="echo" onclick="echo();" disabled="disabled">Echo
					message</button>
			</div>
		</div>
		<div id="console-container">
			<div id="console"></div>
		</div>
	</div>
</body>
</html>