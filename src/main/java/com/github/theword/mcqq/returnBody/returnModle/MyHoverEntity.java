package com.github.theword.mcqq.returnBody.returnModle;

import lombok.Data;

import java.util.List;

@Data
public class MyHoverEntity {
    String type;
    String id;
    List<? extends MyBaseComponent> name;
}
