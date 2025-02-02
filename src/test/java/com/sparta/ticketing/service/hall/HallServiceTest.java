package com.sparta.ticketing.service.hall;

import com.sparta.ticketing.dto.hall.AddHallRequest;
import com.sparta.ticketing.dto.hall.HallRequest;
import com.sparta.ticketing.dto.hall.HallResponse;
import com.sparta.ticketing.entity.Hall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HallServiceTest {

    @InjectMocks
    HallService hallService;

    @Mock
    HallConnectorInterface hallConnectorInterface;

    @Mock
    Hall hall;

    @Test
    void 홀_생성() {
        //given
        HallRequest from = HallRequest.from(new AddHallRequest("하이", "서울", 100));
        //when
        when(hall.getId()).thenReturn(1L);
        when(hall.getLocation()).thenReturn("서울");
        when(hallConnectorInterface.addHall(from)).thenReturn(hall);
        HallResponse hallResponse = hallService.addHall(from);
        //then
        Assertions.assertEquals(hallResponse.getLocation(), "서울");

    }

}
