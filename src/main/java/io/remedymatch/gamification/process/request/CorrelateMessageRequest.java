package io.remedymatch.gamification.process.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorrelateMessageRequest {

    @NotBlank
    private String prozessKey;

    private String businessKey;

    @NotBlank
    private String messageKey;

    private Map<String, Object> localVariablesEqual;

    private Map<String, Object> variables;
}
