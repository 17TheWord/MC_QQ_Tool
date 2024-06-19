package com.github.theword.mcqq.returnBody;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ActionbarReturnBody {
    @SerializedName("message_list")
    private List<MyBaseComponent> messageList;

    @Override
    public String toString() {
        return messageList.stream()
                .map(MyBaseComponent::getText)
                .collect(Collectors.joining());
    }
}
