<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>广播式WebSocket</title>
    
   	<script src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></script>  
    <script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>  
</head>
<body onload="disconnect()">
<noscript><h2 style="color: #e80b0a;">Sorry，浏览器不支持WebSocket</h2></noscript>
<div>
    <div>
        <button id="connect" onclick="connect();">连接</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
    </div>

    <div id="conversationDiv">
        <label>输入你的名字</label><input type="text" id="name"/>
        <button id="sendName" onclick="sendName();">发送</button>
        <p id="response"></p>
    </div>
</div>

<script type="text/javascript">
    jQuery(function($){
    	var websocket;
        if ('WebSocket' in window) {
            console.log("WebSocket");
            websocket = new WebSocket("ws://localhost:8080/veg");
        } else if ('MozWebSocket' in window) {
            console.log("MozWebSocket");
            websocket = new MozWebSocket("ws://veg");
        } else {
            console.log("SockJS");
            websocket = new SockJS("http://localhost:8080/com/veg");
        }
        
        websocket.onopen = function (evnt) {
            $("#head").html("连接服务器成功!")
            send();
        };
        websocket.onmessage = function (evnt) {
            $("#msg").html($("#msg").html() + "<br/>" + evnt.data);
        };
        websocket.onerror = function (evnt) {
        };
        websocket.onclose = function (evnt) {
            $("#head").html("与服务器断开了链接!")
        }
        function send(){
            if (websocket != null) {

                websocket.send("客户端请求");
            } else {
                alert('未与服务器链接.');
            }
        }
    });
</script>
</body>
</html>