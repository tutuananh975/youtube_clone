package personal.project.youtube.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import personal.project.youtube.anotation.currentUser.CurrentUser.CurrentUser;
import personal.project.youtube.entity.User;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.payload.response.ResponseObject;
import personal.project.youtube.service.SubscribeService;
import personal.project.youtube.test.Message;

@RestController
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscribeService subscribeService;

    @MessageMapping("/subscribe/{channelId}")
    @SendTo("/channel/{channelId}")
    public ResponseEntity<?> subscribing(
            @DestinationVariable @NumberFormat @NonNull Long channelId,
            @CurrentUser User currentUser
    ) throws AppException {
        return ResponseEntity.ok(
                new ResponseObject<>(subscribeService.subscribing(currentUser, channelId))
        );
    }

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        return new Message(message.getFrom(), message.getMessage());
    }
}
