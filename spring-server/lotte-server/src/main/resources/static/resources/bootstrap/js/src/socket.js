/*
 * Sock JS 이용해서 서버와 통신하는 js파일
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
	WebSocket.init();
});

var WebSocket = (function() {
	const SERVER_SOCKET_API = "/gs-guide-websocket";
	const ENTER_KEY = 13;
	var stompClient;
	
	function connect() {
	    var socket = new SockJS('/gs-guide-websocket');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        console.log('Connected finish');
	        stompClient.subscribe('/topic/greetings', function (greeting) {
	        	event();
	        });
	    });
	}
	
	function init() {
		console.log("init");
		connect();
	}
	
	function event() {
		$("#test > h1").text(greeting.body);
	}
	
	return {
		init : init
	}
	
})();