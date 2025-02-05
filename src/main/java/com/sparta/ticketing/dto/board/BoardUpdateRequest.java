package com.sparta.ticketing.dto.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardUpdateRequest {
    private final long boardId;
    private final String title;
    private final String contents;

    public BoardUpdateRequest(long boardId, String title, String contents) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
    }

    @JsonCreator
    public static BoardUpdateRequest from(
            @JsonProperty("boardId") long boardId,
            @JsonProperty("title") String title,
            @JsonProperty("contents") String contents
    ) {
        return new BoardUpdateRequest(boardId, title, contents);
    }
}
