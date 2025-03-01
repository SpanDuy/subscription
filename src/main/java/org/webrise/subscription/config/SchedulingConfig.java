package org.webrise.subscription.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.webrise.subscription.service.CustomerSubscriptionService;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfig {
    
    private final CustomerSubscriptionService customerSubscriptionService;
    
    @Scheduled(cron = "0 0 * * * *") // Выполнять каждый час в 00 минут
    public void checkExpiredSubscriptions() {
        customerSubscriptionService.checkExpiredSubscriptions();
    }
} 