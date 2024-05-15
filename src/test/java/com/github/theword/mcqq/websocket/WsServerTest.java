package com.github.theword.mcqq.websocket;

import com.github.theword.mcqq.utils.Tool;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

import static com.github.theword.mcqq.utils.Tool.logger;

class WsServerTest {

    @Test
    void getUnicodeServerName() {
        System.out.println(Tool.unicodeEncode("中文"));
    }

    @Test
    void onOpen() {
        logger = LoggerFactory.getLogger(WsServerTest.class);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 25565);
        WsServer wsServer = new WsServer(inetSocketAddress);
        wsServer.start();
        try {
            Thread.sleep(3000);
            wsServer.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}