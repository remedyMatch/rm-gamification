package io.remedymatch.gamification.karma.controller;

import io.remedymatch.gamification.karma.domain.service.KarmaService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/karma")
public class KarmaController {

    private final KarmaService deliveryService;

    /**
     *
     */
    @GetMapping("/test")
    public ResponseEntity<Void> loadAllDeliveries() {
        return ResponseEntity.ok().build();
    }

}