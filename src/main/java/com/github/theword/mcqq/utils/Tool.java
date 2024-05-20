package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.constant.BaseConstant;
import com.github.theword.mcqq.eventModels.base.BaseEvent;
import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.websocket.WsClient;
import com.github.theword.mcqq.websocket.WsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    public static Logger logger = null;
    public static Config config = null;
    public static List<WsClient> wsClientList = new ArrayList<>();
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

    /**
     * unicode编码转为字符串
     *
     * @param unicodeString unicode编码
     * @return 字符串
     */
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

    /**
     * 初始化
     *
     * @param logName                       日志名称
     * @param isModServer                   是否为模组服务器
     * @param handleWebsocketMessageService websocket消息处理
     */
    public static void initTool(String logName, boolean isModServer, HandleWebsocketMessage handleWebsocketMessageService) {
        logger = LoggerFactory.getLogger(logName);
        config = new Config(isModServer);
        handleWebsocketMessage = handleWebsocketMessageService;
        logger.info(BaseConstant.INITIALIZED);
    }

    /**
     * 发送消息
     *
     * @param event 事件
     */
    public static void sendMessage(BaseEvent event) {
        if (config.isEnableMcQQ()) {
            event.setServerName(config.getServerName());
            wsClientList.forEach(wsClient -> wsClient.send(event.getJson()));
            if (wsServer != null) {
                wsServer.broadcast(event.getJson());
            }
        }
    }

    /**
     * 命令返回
     *
     * @param commandReturner 命令返回
     * @param message         消息
     */
    public static void commandReturn(Object commandReturner, String message) {
        if (commandReturner != null)
            handleWebsocketMessage.handleCommandReturnMessage(commandReturner, message);
    }

    /**
     * 获取前缀
     *
     * @return 前缀
     */
    public static MyBaseComponent getPrefixComponent() {
        MyBaseComponent myBaseComponent = new MyBaseComponent();
        myBaseComponent.setText("[MC_QQ]");
        myBaseComponent.setColor("gold");
        return myBaseComponent;
    }
}
