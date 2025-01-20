package uz.result.drxoshimov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.result.drxoshimov.bot.ApplyNotifierBot;
import uz.result.drxoshimov.enumurations.Button;
import uz.result.drxoshimov.model.Counter;
import uz.result.drxoshimov.repository.ApplicationRepository;
import uz.result.drxoshimov.repository.CounterRepository;
import uz.result.drxoshimov.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final ApplyNotifierBot applyNotifierBot;

    private final CounterRepository counterRepository;

    private final ApplicationRepository applicationRepository;

    public ResponseEntity<ApiResponse<?>> addCallNumber(Button button) {
        ApiResponse<?> response = new ApiResponse<>();
        Counter counter = Counter.builder()
                .section(button)
                .countCall(1L)
                .build();
        counterRepository.save(counter);
        response.setMessage("Added Call Number");
        return ResponseEntity.status(201).body(response);
    }

    public void checkAndSendCounter() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oldTime = now.minusWeeks(1);

        List<Counter> counterList = counterRepository.findAllByCreatedDateBetween(oldTime, now);

        Map<Button, Long> aggregatedCounters = new HashMap<>();
        for (Counter counter : counterList) {
            aggregatedCounters.put(
                    counter.getSection(),
                    aggregatedCounters.getOrDefault(counter.getSection(), 0L) + counter.getCountCall()
            );
        }

        List<Counter> savedCounters = new ArrayList<>();
        for (Map.Entry<Button, Long> entry : aggregatedCounters.entrySet()) {
            Counter aggregatedCounter = Counter.builder()
                    .section(entry.getKey())
                    .countCall(entry.getValue())
                    .build();
            savedCounters.add(aggregatedCounter);
        }

        counterRepository.deleteAll(counterList);
        Long applicationCount = applicationRepository.countApplicationInPeriod(oldTime, now);
        applyNotifierBot.sendCounter(savedCounters, applicationCount);
    }

}
