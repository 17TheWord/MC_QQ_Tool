package com.github.theword.mcqq.returnBody;

import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class MessageReturnBody {

    @SerializedName("message_list")
    private List<MyTextComponent> messageList;

}
