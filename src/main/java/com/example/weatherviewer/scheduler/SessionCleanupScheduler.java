package com.example.weatherviewer.scheduler;

import com.example.weatherviewer.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class SessionCleanupScheduler {
    private final SessionService sessionService;

    @Scheduled(fixedRate = 3_600000)
    public void cleanupExpiredSessions(){

        sessionService.deleteExpiredSessions();
    }

}
