package application.ports.out;

import domain.exceptions.EventPublishingException;

/**
 * Defines the port for publishing events, such as user registration events, to external systems or messaging queues like Kafka.
 */
public interface EventPublisherPort {

    /**
     * Publishes a given event to an external system or messaging queue.
     * @param event The event to be published.
     * @throws EventPublishingException if there is a failure in publishing the event.
     */
    void publishEvent(String event) throws EventPublishingException;
}