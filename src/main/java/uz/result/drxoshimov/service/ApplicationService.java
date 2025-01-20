package uz.result.drxoshimov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.result.drxoshimov.bot.ApplyNotifierBot;
import uz.result.drxoshimov.model.Application;
import uz.result.drxoshimov.repository.ApplicationRepository;
import uz.result.drxoshimov.response.ApiResponse;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplyNotifierBot applyNotifierBot;

    public ResponseEntity<ApiResponse<Application>> save(Application application) {
        ApiResponse<Application> response = new ApiResponse<>();
        Application saved = applicationRepository.save(application);
        applyNotifierBot.handleSendApplication(saved);
        response.setData(saved);
        response.setMessage("Application has been saved successfully");
        return ResponseEntity.status(201).body(response);
    }


}
