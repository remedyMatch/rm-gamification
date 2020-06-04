package io.remedymatch.gamification.process.adapter;

import com.sun.istack.NotNull;
import io.remedymatch.gamification.process.model.*;
import io.remedymatch.gamification.process.request.*;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Map;

@Component
@AllArgsConstructor
public class RMEngineAdapter {

    private RMEngineClient rmEngineClient;

    private Keycloak keycloak;

    /**
     * Calls rm-engine and starts a new instance of the logistic-process
     */
    public ProcessInstanceID startProcess(
            final @NotNull @Valid ProcessKey prozessKey,
            final @NotNull @Valid BusinessKey businessKey,
            final @NotNull @Valid Map<String, Object> variables
    ) {
        ProcessStartRequest processStartRequest = buildProcessStartRequest(prozessKey, businessKey, variables);
        String accessToken = "Bearer " + getAccessTokenFromKeycloak();
        ResponseEntity<String> response = rmEngineClient.startInstanceOfLogisticProcess(accessToken, processStartRequest);

        if (response.getStatusCode().isError()) {
            throw new RuntimeException("Beim Starten des Prozesses ist etwas fehlgeschlagen");
        }

        return new ProcessInstanceID(response.getBody());
    }


    /**
     * Calls rm-engine and deletes an existing instance of the logistic-process
     */
    public void deleteProcessInstance(final @NotNull String processInstanceId) {
        String accessToken = "Bearer " + getAccessTokenFromKeycloak();
        ResponseEntity<Void> response = rmEngineClient.deleteInstanceOfLogisticProcess(accessToken, processInstanceId);

        if (response.getStatusCode().isError()) {
            throw new RuntimeException("Beim Stoppen des Prozesses ist etwas fehlgeschlagen!");
        }
    }


    /**
     * POST a bpmn-message-event (delivery-status-change) to rm-engine
     */
    public void correlateMessage(
            final @NotNull @Valid ProcessKey processKey,
            final @NotNull @Valid BusinessKey businessKey,
            final @NotNull @Valid MessageKey messageKey
    ) {
        CorrelateMessageRequest correlateMessageRequest = buildCorrelateMessageRequest(processKey, messageKey, businessKey);
        String accessToken = "Bearer " + getAccessTokenFromKeycloak();
        ResponseEntity<Void> response = rmEngineClient.correlateMessage(accessToken, correlateMessageRequest);

        if (response.getStatusCode().isError()) {
            throw new RuntimeException("Beim korrelieren ist etwas fehlgeschlagen: " + response.getStatusCodeValue());
        }
    }

    /** ------------------------------ private helper methods ------------------------------ */

    private String getAccessTokenFromKeycloak() {
        return "Bearer " + keycloak.tokenManager().getAccessTokenString();
    }

    private ProcessStartRequest buildProcessStartRequest(ProcessKey processKey, BusinessKey businessKey, Map<String, Object> variables) {
        return ProcessStartRequest.builder()
                .prozessKey(processKey.getValue())
                .businessKey(businessKey.getValue().toString())
                .variables(variables)
                .build();
    }

    private CorrelateMessageRequest buildCorrelateMessageRequest(ProcessKey processKey, MessageKey messageKey, BusinessKey businessKey) {
        return CorrelateMessageRequest.builder()
                .prozessKey(processKey.getValue())
                .messageKey(messageKey.getValue())
                .businessKey(businessKey.getValue().toString())
                .build();
    }

}
