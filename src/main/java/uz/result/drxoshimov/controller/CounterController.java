package uz.result.drxoshimov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.result.drxoshimov.enumurations.Button;
import uz.result.drxoshimov.response.ApiResponse;
import uz.result.drxoshimov.service.CounterService;
import uz.result.drxoshimov.utils.ApiConstants;

@RestController
@RequestMapping(ApiConstants.PREFIX + "/counter")
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> addCallNum(
            @RequestParam(value = "button") Button button
    ) {
        return counterService.addCallNumber(button);
    }

}
