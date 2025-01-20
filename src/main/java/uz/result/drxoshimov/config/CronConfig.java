package uz.result.drxoshimov.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import uz.result.drxoshimov.service.CounterService;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CronConfig {

    private final CounterService counterService;

//    @Scheduled(cron = "0 * * * * *")//every minute
    @Scheduled(cron = "0 0 0 * * MON", zone = "Asia/Tashkent")
    public void autoRunMethod() {
        counterService.checkAndSendCounter();
    }

}
