package com.github.theword.mcqq.utils;

public interface HandleWebsocketMessage {
    void handleWebSocketJson(String message);

    void handleCommandReturnMessage(Object commandReturner, String message);
}
