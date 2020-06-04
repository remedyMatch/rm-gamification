package io.remedymatch.gamification.process.adapter;

import io.remedymatch.gamification.process.request.CorrelateMessageRequest;
import io.remedymatch.gamification.process.request.ProcessStartRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "rmEngineClient", url = "${io.remedymatch.engine.remedyRestApiUrl}")
public interface RMEngineClient {

    /**
     * Calls rm-engine and starts a new instance of the logistic-process
     */
    @PostMapping(path = "/prozess/start")
    ResponseEntity<String> startInstanceOfLogisticProcess(
            @RequestHeader(name = "Authorization") String bearerToken,
            @RequestBody ProcessStartRequest request
    );

    /**
     * Calls rm-engine and deletes an existing instance of the logistic-process
     */
    @PostMapping(path = "/prozess/stop")
    ResponseEntity<Void> deleteInstanceOfLogisticProcess(
            @RequestHeader(name = "Authorization") String bearerToken,
            @RequestBody String processInstanceID
    );

    /**
     * POST a bpmn-message-event (delivery-status-change) to rm-engine
     */
    @PostMapping(path = "/message/korrelieren")
    ResponseEntity<Void> correlateMessage(
            @RequestHeader(name = "Authorization") String bearerToken,
            @RequestBody CorrelateMessageRequest request
    );

}

