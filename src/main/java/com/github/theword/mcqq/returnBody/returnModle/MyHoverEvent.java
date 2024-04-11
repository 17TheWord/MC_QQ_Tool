package com.github.theword.mcqq.returnBody.returnModle;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class MyHoverEvent {
    private String action;
    @SerializedName("base_component_list")
    private List<MyBaseComponent> baseComponentList;
    private MyHoverItem item;
    private MyHoverEntity entity;
}
