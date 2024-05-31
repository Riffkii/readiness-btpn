package com.reservation.app.database;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
public class ReservationDatabase {
    private Integer personTodayCounter = 0;
    private Map<String, List<Person>> data = new HashMap<>();
}
