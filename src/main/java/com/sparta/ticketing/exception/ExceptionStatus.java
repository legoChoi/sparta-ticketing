package com.sparta.ticketing.exception;

public enum ExceptionStatus {
    NOTFOUND_ADMIN("관리자계정을 찾을수 없습니다."),
    NOTFOUND_USER("유저계정을 찾을수 없습니다."),
    WRONG_PASSWORD("잘못된 비밀번호 입니다."),
    NOTFOUND_BOARD("게시판을 찾을수 없습니다."),
    UNAUTHORIZED_ACCESS("접근 권한이 없습니다."),
    NOTFOUND_LIKE("좋아요가 없습니다."),
    NOTFOUND_COMMENT("댓글을 찾을 수 없습니다."),
    NOTFOUND_CONCERT("콘서트를 찾을 수 없습니다."),
    NOTFOUND_HALL("홀을 찾을 수 없습니다."),
    NOTFOUND_RESERVATION("예매내역을 찾을수 없습니다."),
    NOTFOUND_REVIEW("리뷰를 찾을 수 없습니다."),
    NOTFOUND_SEAT("좌석을 찾을 수 없습니다."),
    NOTFOUND_SESSION("공연정보를 찾을수 없습니다.");

    private final String message;

    ExceptionStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
