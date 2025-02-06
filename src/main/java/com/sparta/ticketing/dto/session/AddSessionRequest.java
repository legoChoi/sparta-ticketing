package com.sparta.ticketing.dto.session;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AddSessionRequest {
    @NotNull
    private final Long concertId;

    @NotNull
    private final Long hallId;

    @NotNull
    @Future(message = "공연 시작 시간은 반드시 미래여야 합니다.")
    private final LocalDateTime startDateTime;

    @NotNull
    @Future(message = "공연 종료 시간은 반드시 미래여야 합니다.")
    private final LocalDateTime endDateTime;

    @NotNull
    @Min(value = 1, message = "좌석개수는 1개이상이어야합니다")
    private final Integer validSeatCount;

    @AssertTrue(message = "공연 종료시간은 공연 시작시간 이후여야합니다")
    public boolean isValidTime() {
        return startDateTime.isBefore(endDateTime);
    }

    @JsonCreator
    public static AddSessionRequest from(
            @JsonProperty("concertId") Long concertId,
            @JsonProperty("hallId") Long hallId,
            @JsonProperty("startDateTime") LocalDateTime startDateTime,
            @JsonProperty("endDateTime") LocalDateTime endDateTime,
            @JsonProperty("validSeatCount") Integer validSeatCount
    ){
        return new AddSessionRequest(concertId, hallId, startDateTime, endDateTime, validSeatCount);
    }
}
