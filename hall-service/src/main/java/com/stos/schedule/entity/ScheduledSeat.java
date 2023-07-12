package com.stos.schedule.entity;

public class ScheduledSeat {
    private final Long seatId;
    private final SeatStatus seatStatus;

    public ScheduledSeat(Long seatId, SeatStatus seatStatus) {
        this.seatId = seatId;
        this.seatStatus = seatStatus;
    }

    enum SeatStatus {
        FREE,
        TEMPORARY_OCCUPIED,
        RESERVED,
        CLOSED,
    }

    public boolean isReservable(){
        return this.seatStatus.equals(SeatStatus.FREE);
    }
}
