package com.trendit.scheduler;

import com.trendit.api.service.RecentNewsService;
import com.trendit.api.service.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@AllArgsConstructor
public class PipelineScheduler {

    private final WebClientService webClientService;
    private final RecentNewsService recentNewsService;

    @Scheduled(cron = "0 43 0 * * *", zone = "Asia/Seoul")
    public void sendRequestExecutesPipeline() {
        LocalDate target = LocalDate.now(ZoneId.of("Asia/Seoul")).minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedTarget = target.format(formatter);
        webClientService.executeDataPipeline(formatedTarget);
        log.info("execute pipeline - {}", formatedTarget);

        recentNewsService.truncateRecentNews();
        recentNewsService.cacheRecentNews();
    }
}
