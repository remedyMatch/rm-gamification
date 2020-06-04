package io.remedymatch.gamification.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "io.remedymatch.backend")
public class BackendProperties {

    /**
     * Process Start - URL
     */
    @NotNull
    @NotBlank
    private String url;

}
