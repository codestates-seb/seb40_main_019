package com.backend.global.config;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

public class TimeZoneConfig {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
