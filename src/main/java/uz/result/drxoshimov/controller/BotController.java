package uz.result.drxoshimov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.result.drxoshimov.bot.ApplyNotifierBot;
import uz.result.drxoshimov.utils.ApiConstants;

@RestController
@RequestMapping(ApiConstants.PREFIX + "/webhook")
@RequiredArgsConstructor
public class BotController {

    private final ApplyNotifierBot applyNotifierBot;

    @PostMapping
    public void onUpdateReceived(@RequestBody Update update) {
        applyNotifierBot.onWebhookUpdateReceived(update);
    }

}
