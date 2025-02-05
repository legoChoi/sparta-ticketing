package com.sparta.ticketing.dto.board;

import com.sparta.ticketing.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponse {
    private String title;
    private String contents;


    public BoardResponse(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getTitle(), board.getContents());
    }
}
