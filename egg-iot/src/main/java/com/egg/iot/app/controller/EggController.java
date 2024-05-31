package com.egg.iot.app.controller;

import com.egg.iot.app.dto.EggResponse;
import com.egg.iot.app.iot.EggDatabase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/egg")
@AllArgsConstructor
public class EggController {
    private EggDatabase database;


    /*
    Pada kode ini saya membuat sistem scheduler, anggap saja sebagai IOT yang akan menghitung
    telur yang dihasilkan oleh ayam. Scheduler saya set setiap 5 detik akan increment sejumlah
    random int 10.
     */
    @GetMapping
    private ResponseEntity<EggResponse> getEggData() {
        return ResponseEntity.ok(new EggResponse(database.getEggAmount()));
    }
}
