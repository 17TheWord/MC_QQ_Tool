package com.github.theword.mcqq.returnBody;

import com.github.theword.mcqq.returnBody.returnModle.SendTitle;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SendTitleReturnBody {

    @SerializedName("send_title")
    private SendTitle sendTitle;

}
