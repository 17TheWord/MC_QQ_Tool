package com.github.theword.mcqq.websocket;

import com.github.theword.mcqq.utils.Tool;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.github.theword.mcqq.utils.Tool.config;
import static com.github.theword.mcqq.utils.Tool.logger;

class WsServerTest {

    @Test
    void getUnicodeServerName() throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("服务器", StandardCharsets.UTF_8.toString()));
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