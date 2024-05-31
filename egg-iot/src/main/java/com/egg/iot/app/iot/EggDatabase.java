package com.egg.iot.app.iot;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EggDatabase {
    private Integer eggAmount = 0;
}
