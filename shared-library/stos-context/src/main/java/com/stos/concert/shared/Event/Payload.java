package com.stos.concert.shared.Event;

import java.time.LocalDateTime;

public interface Payload<T> {
    T getData();
    LocalDateTime getCurrentAt();
}
