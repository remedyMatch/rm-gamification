package io.remedymatch.gamification.process.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * imported from rm-engine (to start a process instance)
 * do not change the variable-names
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessStartRequest {
    private String prozessKey;
    private String businessKey;
    private Map<String, Object> variables;
}
