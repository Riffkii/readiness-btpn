package com.egg.iot.app.iot;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class EggIot {
    private EggDatabase database;

    //Anggap saja IOT
    @Scheduled(cron = "*/5 * * * * *")
    public void eggIot() {
        database.setEggAmount(database.getEggAmount() + new Random().nextInt(10));
    }
}
