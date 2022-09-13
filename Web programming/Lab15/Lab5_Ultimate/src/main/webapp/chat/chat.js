var websocket;
var lang;

function init() {
    websocket = new WebSocket('ws://library6lab.herokuapp.com/Lab5_Ultimate_war_exploded/chat');
    websocket.onopen = function (event) {
        websocketOpen(event);
    };
    websocket.onmessage = function (event) {
        websocketMessage(event);
    };
    websocket.onerror = function (event) {
        websocketError(event);
    };
}

function websocketOpen(event) {
    console.log("webSocketOpen invoked");
    websocket.send("[web_socket_message]:=:" + document.getElementById('senderId').value + ':=:'
        + document.getElementById('role').value );
}

function websocketMessage(event) {
    console.log("websocketMessage invoked");
    document.getElementById('chatWindow').value += '\n' + event.data;
}

function websocketError(event) {
    console.log("websocketError invoked");
}

function sendMessage() {
    let msg = document.getElementById('chatInput');
    if (msg.value === "") return;
    websocket.send(msg.value);
    if (lang === 'ru') {
        document.getElementById('chatWindow').value += '\n' + 'Я: ' + msg.value;
    } else {
        document.getElementById('chatWindow').value += '\n' + 'Me: ' + msg.value;
    }

    msg.value = "";
}

function closeConnection() {
    websocket.close();
}

window.addEventListener("load", init);
window.addEventListener("unload", closeConnection);