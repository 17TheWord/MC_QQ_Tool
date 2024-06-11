package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.constant.BaseConstant;
import com.github.theword.mcqq.eventModels.base.BaseEvent;
import com.github.theword.mcqq.handleMessage.HandleApi;
import com.github.theword.mcqq.handleMessage.HandleCommandReturnMessage;
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
    public static WsServer wsServer = null;
    public static List<WsClient> wsClientList = new ArrayList<>();
    public static WebsocketManager websocketManager = null;
    public static HandleApi handleApi = null;
    public static HandleCommandReturnMessage handleCommandReturnMessage = null;

    /**
     * 初始化
     *
     * @param isModServer                       是否为模组服务器
     * @param handleApiService                  api消息处理
     * @param handleCommandReturnMessageService 命令消息处理
     */
    public static void initTool(boolean isModServer, HandleApi handleApiService, HandleCommandReturnMessage handleCommandReturnMessageService) {
        logger = LoggerFactory.getLogger("MC_QQ");
        logger.info(BaseConstant.LAUNCHING);
        config = new Config(isModServer);
        websocketManager = new WebsocketManager();
        handleApi = handleApiService;
        handleCommandReturnMessage = handleCommandReturnMessageService;
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
            handleCommandReturnMessage.handleCommandReturnMessage(commandReturner, message);
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
