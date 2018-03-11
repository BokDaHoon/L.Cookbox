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
	        	var obj = JSON.parse(greeting.body);
	        	console.log(obj.slots.recipeName.value);
	        	
	        	$("#anc_search").trigger("click");
	        	$("#search_bar").val(obj.slots.recipeName.value);
	        	$("#search_button").trigger("click");
	        });
	    });
	}
	
	function init() {
		console.log("init");
		connect();
	}
	
	return {
		init : init
	}
	
})();