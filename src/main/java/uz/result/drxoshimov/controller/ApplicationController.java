package uz.result.drxoshimov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.result.drxoshimov.model.Application;
import uz.result.drxoshimov.response.ApiResponse;
import uz.result.drxoshimov.service.ApplicationService;
import uz.result.drxoshimov.utils.ApiConstants;

@RestController
@RequestMapping(ApiConstants.PREFIX + "/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Application>> create(@RequestBody Application application) {
        return applicationService.save(application);
    }

}
