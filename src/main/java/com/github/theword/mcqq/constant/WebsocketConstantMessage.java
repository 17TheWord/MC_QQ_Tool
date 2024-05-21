package com.github.theword.mcqq.constant;

public class WebsocketConstantMessage {
    public static final String PARSE_MESSAGE_ERROR_ON_MESSAGE = "解析来自 %s 的 WebSocket 消息时出现异常";
    public static final String CLOSE_BY_RELOAD = "Websocket 正在重载";



    public static class Client {
        private static final String CONNECT_TO = "连接至：%s 的 ";
        public static final String LAUNCHING = "WebSocket Client 正在启动...";
        public static final String CLOSING = CONNECT_TO + "WebSocket Client 正在关闭。";
        public static final String RECONNECT_MESSAGE = CONNECT_TO + "WebSocket 连接已断开，尝试第 %d 次重连...";
        public static final String ON_OPEN = "已成功连接至 %s 的 WebSocket 服务器！";
        public static final String ON_ERROR = CONNECT_TO + "WebSocket 连接出现异常：%s";
        public static final String IS_NOT_OPEN = CONNECT_TO + "WebSocket 连接未打开";
        public static final String IS_NOT_OPEN_WHEN_SEND_MESSAGE = IS_NOT_OPEN + "，发送消息失败";
        public static final String ERROR_URI_SYNTAX_ERROR = CONNECT_TO + "WebSocket URL 格式错误，无法连接！";
        public static final String RECONNECT_TIMES_REACH = CONNECT_TO + "重连次数达到最大值，将不再自动重连，请手动重连！";
        public static final String CLOSE_BY_RELOAD = "Websocket Client 正在重载";
    }

    public static class Server {
        public static final String CLOSING = "Websocket Server 正在关闭";
        public static final String CLOSING_ERROR = "Websocket Server 正在关闭时出现异常：%s";
        private static final String CONNECT_FROM = "来自：%s 的 ";
        public static final String MISSING_SERVER_NAME_IN_HEADER = CONNECT_FROM + "连接请求头中缺少服务器名，将断开连接";
        public static final String WRONG_CLIENT_ORIGIN_IN_HEADER = CONNECT_FROM + "连接请求头中客户端来源错误，将断开连接";
        public static final String PARSE_SERVER_NAME_FAILED_IN_HEADER = CONNECT_FROM + "连接请求头中服务器名解析失败，将断开连接";

        public static final String CLIENT_CONNECTED_SUCCESSFULLY = CONNECT_FROM + "客户端已连接";
        public static final String CLIENT_DISCONNECTED = CONNECT_FROM + "客户端已断开";
        public static final String CLIENT_HAD_BEEN_DISCONNECTED = CONNECT_FROM + "客户端已被断开";

        public static final String ON_ERROR = CONNECT_FROM + "WebSocket 连接出现异常：%s";

        public static final String ON_START = "WebSocket Server 正在启动...";

    }
}
