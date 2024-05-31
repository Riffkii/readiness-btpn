package com.reservation.app.controller;

import com.reservation.app.database.Person;
import com.reservation.app.database.ReservationDatabase;
import com.reservation.app.dto.ReservationReq;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
Pada kode ini saya membuat sistem reservasi restoran yang dimana setiap hari maksimal 2 orang
dan setiap hari jumat dan rabu tidak bisa reservasi. Disini saya menggunakan scheduler untuk
mereset counter person per hari dan mereset data person selama 1 minggu yang direset setiap hari minggu
jam 12 malam.
 */
@RestController
@RequestMapping(path = "/reservation")
@AllArgsConstructor
public class ReservationController {
    private ReservationDatabase database;

    @PostMapping
    private ResponseEntity<String> setReservation(@RequestBody ReservationReq req) {
        String today = LocalDate.now().getDayOfWeek().toString();
        if(!Objects.equals(today, "FRIDAY") && !Objects.equals(today, "WEDNESDAY") && database.getPersonTodayCounter() != 2) {
            database.setPersonTodayCounter(database.getPersonTodayCounter() + req.getPersons().size());
            database.getData().putIfAbsent(today, new ArrayList<>());
            database.getData().get(today).addAll(req.getPersons());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(404).body("Reservation are no longer available");
        }
    }

    @GetMapping
    private ResponseEntity<Map<String, List<Person>>> getReservationData() {
        return ResponseEntity.ok(database.getData());
    }
}
