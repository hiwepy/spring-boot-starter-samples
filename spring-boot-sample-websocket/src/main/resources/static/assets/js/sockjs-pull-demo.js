jQuery(function($){
	
	//===========================定时数据刷新==============================
		
	// 创建 SockJS 连接；SockJS 可以接收相对url；    
	var socket = new SockJS('/metrics');
	var timeSend = null;
	socket.onopen = function () {
		console.log('Info: WebSocket connection opened.');
		if($("#refresh-background").prop("checked")){
			timeSend = setInterval(function() {
				socket.send(JSON.stringify({
					"appKey" 		: $("#query-app-type").val(),
					"bizName" 		: $("#query-biz-type").val()
				}));
				
			}, 60 * 1000);
		}
	};
	socket.onmessage = function (event) {
		var data = JSON.parse(event.data);
		initPieCharts(data);
		initLineCharts(data);
		initTopList(data,true);
		console.log('Received: ' + event.data);
	};
	socket.onclose = function () {
		console.log('Info: WebSocket connection closed.');
		clearInterval(timeSend);
	};
	
})