package com.sparta.ticketing.dto.hall;

import com.sparta.ticketing.controller.hall.HallController;
import lombok.Getter;

import java.util.List;

@Getter
public class HallListResponse {
    private final List<HallResponse> hallResponses;

    public HallListResponse(List<HallResponse> hallResponses) {
        this.hallResponses = hallResponses;
    }

    public static HallListResponse from(List<HallResponse> hallResponses) {
        return new HallListResponse(hallResponses);
    }
}
