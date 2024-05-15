package com.github.theword.mcqq.utils;

import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class Config {
    private boolean enableMcQQ;
    private boolean enableChatMessage;
    private boolean enableReconnectMessage;
    private int reconnectMaxTimes;
    private int reconnectInterval;
    private boolean enableWebsocketServer;
    private String websocketServerHost;
    private int websocketServerPort;
    private boolean enableWebsocketClient;
    private List<String> websocketUrlList;
    private String sayWay;
    private boolean enableCommandMessage;
    private boolean enableDeathMessage;
    private boolean enableJoinMessage;
    private boolean enableQuitMessage;
    private String serverName;

    public Config(boolean isModServer) {
        String configFolder;
        if (isModServer) {
            configFolder = "mods";
        } else {
            configFolder = "plugins";
        }
        Path configMapFilePath = Paths.get("./" + configFolder, "MC_QQ", "config.yml");
        if (!Files.exists(configMapFilePath)) {
            Tool.logger.info("[MC_QQ] 配置文件不存在，将自动生成");
            try {
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.yml");
                assert inputStream != null;
                FileUtils.copyInputStreamToFile(inputStream, configMapFilePath.toFile());
            } catch (IOException e) {
                Tool.logger.warn("[MC_QQ] 生成配置文件失败");
            }
        }
        try {
            Yaml yaml = new Yaml();
            Reader reader = Files.newBufferedReader(configMapFilePath);
            Map<String, Object> configMap = yaml.load(reader);
            loadConfigValues(configMap);
        } catch (Exception e) {
            Tool.logger.warn("[MC_QQ] 读取配置文件失败，将使用默认配置");
            setDefaultValues();
        }
    }

    private void loadWebsocketServer(Object websocketServerObject) {
        if (websocketServerObject instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> websocketServer = (Map<String, Object>) websocketServerObject;
            enableWebsocketServer = (boolean) websocketServer.get("enable");
            websocketServerHost = (String) websocketServer.get("host");
            websocketServerPort = (int) websocketServer.get("port");
        } else {
            enableWebsocketServer = false;
            Tool.logger.warn("[MC_QQ] 未读取到 websocket_server 配置，将不启用 Websocket Server");
        }
    }

    private void loadWebsocketUrlList(Object websocketUrlListObject) {
        if (websocketUrlListObject instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) websocketUrlListObject;
            if (list.isEmpty()) {
                Tool.logger.warn("[MC_QQ] 配置项 websocket_url_list 为空，将不启用 Websocket Client");
            } else {
                enableWebsocketClient = true;
                websocketUrlList = new ArrayList<>();
                for (Object websocketUrl : list) {
                    if (websocketUrl instanceof String) {
                        websocketUrlList.add((String) websocketUrl);
                    } else {
                        Tool.logger.warn("Non-string websocketUrl found in websocket_url_list: " + websocketUrl);
                    }
                }
            }
        } else {
            Tool.logger.warn("[MC_QQ] websocket_url_list 配置错误，将不启用 Websocket Client");
        }
        enableWebsocketClient = false;
    }

    private void loadConfigValues(Map<String, Object> configMap) {
        enableMcQQ = (boolean) configMap.get("enable_mc_qq");
        enableChatMessage = (boolean) configMap.get("enable_chat_message");
        enableReconnectMessage = (boolean) configMap.get("enable_reconnect_msg");
        reconnectMaxTimes = (int) configMap.get("reconnect_max_times");
        reconnectInterval = (int) configMap.get("reconnect_interval");
        enableWebsocketClient = (boolean) configMap.get("websocket_url_list");
        sayWay = (String) configMap.get("say_way");
        enableCommandMessage = (boolean) configMap.get("enable_command_message");
        enableDeathMessage = (boolean) configMap.get("enable_death_message");
        enableJoinMessage = (boolean) configMap.get("enable_join_message");
        enableQuitMessage = (boolean) configMap.get("enable_quit_message");
        serverName = (String) configMap.get("server_name");
        loadWebsocketUrlList(configMap.get("websocket_url_list"));
        loadWebsocketServer(configMap.get("websocket_server"));
    }

    private void setDefaultValues() {
        enableMcQQ = true;
        enableChatMessage = true;
        enableReconnectMessage = false;
        reconnectMaxTimes = 5;
        reconnectInterval = 5000;
        enableWebsocketServer = false;
        websocketServerHost = "127.0.0.1";
        websocketServerPort = 8080;
        enableWebsocketClient = true;
        websocketUrlList = new ArrayList<String>() {{
            add("ws://127.0.0.1:8080/minecraft/ws");
        }};
        sayWay = "说：";
        enableCommandMessage = false;
        enableDeathMessage = true;
        enableJoinMessage = true;
        enableQuitMessage = true;
        serverName = "Server";
    }
}
