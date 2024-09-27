let id = document.getElementById("userId").getAttribute("data-message");
WebSocketUrl = "ws://localhost:8888/project/api/webSocket/" + id
const ws = new WebSocket(WebSocketUrl)
ws.onopen = function () {
    console.log(id + "连接建立")
}

ws.onmessage = function () {
    console.log("接受消息")
}

ws.onclose = function () {
    console.log("close")
}

