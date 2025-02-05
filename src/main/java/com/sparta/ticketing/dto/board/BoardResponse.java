package com.sparta.ticketing.dto.board;

import com.sparta.ticketing.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponse {
    private final Long boardId;
    private final String title;
    private final String contents;


    public BoardResponse(Long boardId, String title, String contents) {
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(board.getId(),board.getTitle(), board.getContents());
    }
}
