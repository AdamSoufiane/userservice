package infrastructure.adapters.out.messaging;

import application.ports.out.EventPublisherPort;
import domain.exceptions.EventPublishingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaEventPublisherAdapter implements EventPublisherPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    @Autowired
    public KafkaEventPublisherAdapter(KafkaTemplate<String, String> kafkaTemplate, String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    public void publishEvent(String event) {
        try {
            kafkaTemplate.send(topicName, event);
        } catch (Exception e) {
            throw new EventPublishingException("Failed to publish event to Kafka", e);
        }
    }
}