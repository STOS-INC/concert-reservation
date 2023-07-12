package com.stos.schedule.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Schedule {
    private final Long id;
    private final int turn;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final List<ScheduledSeat> scheduledSeats;

    public Schedule(Long id, int turn, LocalDateTime startDateTime, LocalDateTime endDateTime, List<ScheduledSeat> scheduledSeats) {
        this.id = id;
        this.turn = turn;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.scheduledSeats = scheduledSeats;
    }

    //TODO : 정책정의 - 반드시 예약완료인 좌석상태만 soldout에 영향을 줄지, 예약가능한 좌석이 없다면 soldout으로 볼지 (e.g: 해당 일정의 모든 좌석이 임시점유중이라면 그건 soldout인가?)
    public boolean isSoldOut(){
        return scheduledSeats.stream()
                .noneMatch(ScheduledSeat::isReservable);
    }



}
