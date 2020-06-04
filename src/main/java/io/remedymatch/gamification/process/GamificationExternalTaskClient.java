package io.remedymatch.gamification.process;

import io.remedymatch.gamification.karma.domain.service.KarmaService;
import io.remedymatch.gamification.process.adapter.RMEngineAdapter;
import io.remedymatch.gamification.process.model.ProcessKey;
import io.remedymatch.gamification.properties.EngineProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.backoff.ExponentialBackoffStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Slf4j
@Component
public class GamificationExternalTaskClient {
    private final EngineProperties properties;
    private final KarmaService karmaService;
    private final RMEngineAdapter rmEngineAdapter;

    final static String VAR_ANFRAGE_ID = "request_id";
    final static ProcessKey PROZESS_KEY = new ProcessKey("gamification_process");

    @PostConstruct
    public void doSubscribe() {

        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl(properties.getExternalTaskUrl())
                .backoffStrategy(new ExponentialBackoffStrategy(3000, 2, 3000))
                .build();

        /*client.subscribe("angebot_anfrage_logistik_erstellen_topic")
                .lockDuration(2000)
                .handler(this::startProcessFromExternalTask).open();
        */
    }

}
