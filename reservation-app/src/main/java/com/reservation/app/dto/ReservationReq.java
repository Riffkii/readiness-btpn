package com.reservation.app.dto;

import com.reservation.app.database.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationReq {
    private List<Person> persons;
}
