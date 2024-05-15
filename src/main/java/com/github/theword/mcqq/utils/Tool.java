package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.eventModels.base.BaseEvent;
import com.github.theword.mcqq.websocket.WsClient;
import com.github.theword.mcqq.websocket.WsServer;
import org.slf4j.Logger;

import java.util.List;

public class Tool {
    public static Logger logger = null;
    public static Config config = null;
    public static List<WsClient> wsClientList = null;
    public static HandleWebsocketMessage handleWebsocketMessage = null;
    public static WsServer wsServer = null;

    /**
     * 字符串转为 unicode 编码
     *
     * @param string 字符串
     * @return unicode编码
     */
    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }

    public static String unicodeDecode(String unicodeString) {
        StringBuilder string = new StringBuilder();
        String[] hex = unicodeString.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // Convert hex to decimal
            int data = Integer.parseInt(hex[i], 16);
            // Convert the decimal to character
            string.append((char) data);
        }
        return string.toString();
    }

    public static void sendMessage(BaseEvent event) {
        if (config.isEnableMcQQ()) {
            event.setServerName(config.getServerName());
            wsClientList.forEach(wsClient -> wsClient.send(event.getJson()));
            if (wsServer != null) {
                wsServer.broadcast(event.getJson());
            }
        }
    }
}
