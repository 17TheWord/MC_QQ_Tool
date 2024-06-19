package com.github.theword.mcqq.returnBody.returnModle;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SendTitle {

    private List<MyBaseComponent> title;

    private List<MyBaseComponent> subtitle;

    private int fadein;

    private int stay;

    private int fadeout;

    public String toTitleString() {
        return title.stream()
                .map(MyBaseComponent::getText)
                .collect(Collectors.joining());
    }

    public String toSubtitleString() {
        return subtitle.stream()
                .map(MyBaseComponent::getText)
                .collect(Collectors.joining());
    }

}
