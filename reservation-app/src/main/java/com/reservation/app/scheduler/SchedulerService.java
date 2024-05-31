package com.reservation.app.scheduler;

import com.reservation.app.database.ReservationDatabase;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class SchedulerService {
    private ReservationDatabase database;

    @Scheduled(cron = "0 0 0 * * ?")
    public void performTaskAtMidnight() {
        this.database.setPersonTodayCounter(0);
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    public void performTaskAtMidnightOnSunday() {
        this.database.setData(new HashMap<>());
    }
}
