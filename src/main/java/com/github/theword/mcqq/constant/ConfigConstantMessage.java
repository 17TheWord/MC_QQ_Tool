package com.github.theword.mcqq.constant;

public class ConfigConstantMessage {
    public static final String CONFIG_NOT_EXIST_AND_CREATE = "配置文件不存在，已自动创建！";
    public static final String CONFIG_COPY_FIELD = "生成配置文件失败";
    public static final String CONFIG_READ_FAILED = "配置文件读取错误，使用默认配置！";
    public static final String CONFIG_READ_WEBSOCKET_SERVER_FILED = "读取 websocket_server 失败，将不启用 Websocket Server";
    public static final String CONFIG_READ_WEBSOCKET_CLIENT_URL_LIST_IS_EMPTY = "websocket_client url_list 为空，将不启用 Websocket Client";
    public static final String CONFIG_WEBSOCKET_CLIENT_URL_IS_NOT_STRING = "websocket_client url_list 中的元素不是字符串，将不启用 Websocket Client：";
    public static final String CONFIG_WEBSOCKET_CLIENT_URL_IS_NOT_LIST = "websocket_client url_list 不是列表类型，将不启用 Websocket Client";
}
