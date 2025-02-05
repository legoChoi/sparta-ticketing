package com.sparta.ticketing.dto.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardRequest {

    private final String title;
    private final String contents;

    public BoardRequest(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @JsonCreator
    public static BoardRequest from(
            @JsonProperty("title")String title,
            @JsonProperty("contents")String contents
    ) {
        return new BoardRequest(title, contents);
    }
}
