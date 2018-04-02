function connectQuotation(uid, callback) {
    // 链接行情server
    socket = io.connect('http://localhost:10015');

    // 如果用户在web端登陆，那么发送握手请求
    if (uid) {
        // 连接上server后
        socket.on('connect', function() {
            // 发送握手请求
            var jsonObject = {
                uid : parseInt(uid),
                message : "hello"
            };
            this.emit('helloevent', jsonObject);

            this.on('hellopush', function(data, ackServerCallback, arg1) {
                // base64转码的数据，可忽视
                YUNM.session = {
                    sessionId : $.base64.atob(data.sessionId),
                    time : $.base64.atob(data.time)
                };
                if (ackServerCallback) {
                    ackServerCallback('server message was delivered to client!');
                }
            });
        });
    }

    // 如果web端session超时，socket断开，10分钟扫描一次
    int = window.setInterval(function() {
        // 我是通过ajax判断session超时的，你也可以通过其他方式
        $.ajax({
            type : 'POST',
            url : common.ctx + "/getSessionTimeout",
            dataType : "json",
            cache : false,
            success : function(json) {
                var timeout = parseInt(json.message);
                // session超时后，socket断开，服务端就可以监听到释放资源
                if (timeout == 0) {
                    socket.disconnect();
                }
            },
            error : function() {
                socket.disconnect();
                // 清除
                window.clearInterval(int);
            }

        });
    }, YUNM._set.interval);

    callback();
}

$(function() {
	connectQuotation($("#global_uid").val(), function() {
	    socket.on("pushQuotation", function(message) {

	        if (message.type == "dealOrder") {
	            var msg = message.response.result;
	            // 输出服务端消息
	            YUNM.debug(msg);
	        }
	    });
	});
});