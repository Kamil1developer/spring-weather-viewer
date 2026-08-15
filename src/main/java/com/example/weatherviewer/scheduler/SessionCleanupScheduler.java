package com.example.weatherviewer.scheduler;

import com.example.weatherviewer.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionCleanupScheduler {
    private final SessionService sessionService;


    private void cleanupExpiredSessions(){
        sessionService.deleteExpiredSessions();
    }

}
