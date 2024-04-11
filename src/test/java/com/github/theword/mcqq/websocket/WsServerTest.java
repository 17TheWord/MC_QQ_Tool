package com.github.theword.mcqq.websocket;

import com.github.theword.mcqq.utils.Tool;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

class WsServerTest {

    @Test
    void getUnicodeServerName() {
        System.out.println(Tool.unicodeEncode("中文"));
    }

    @Test
    void onOpen() {
        Logger logger = LoggerFactory.getLogger(WsServerTest.class);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
    }

}