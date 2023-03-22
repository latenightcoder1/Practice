
package com.suraj.kafka;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Template for executing high level Event operations.
 *
 * @author nagesh.salunke
 */
@Log4j2
public class EventProducerTemplate implements EventProducerOperations<String, Event> {

  /**
   * Default topic name.
   */
  @Getter
  private String defaultTopic;

  /**
   * Defining if the template is enabled.
   */
  private boolean enabled;

  /**
   * {@link KafkaTemplate} instance.
   */
  private KafkaTemplate<String, Event> kafkaTemplate;

  /**
   * Constructor.
   * @param defaultTopic - default topic.
   * @param kafkaTemplate - {@link KafkaTemplate}
   */
  public EventProducerTemplate(@NonNull String defaultTopic,
      boolean enabled,
      KafkaTemplate<String, Event> kafkaTemplate) {
    this.defaultTopic = defaultTopic;
    this.kafkaTemplate = kafkaTemplate;
    this.kafkaTemplate.setDefaultTopic(defaultTopic);
    this.enabled = enabled;
  }

  @Override
  public ListenableFuture<SendResult<String, Event>> send(Event event) {
    if (enabled) {
      log.debug("Sending (event={}) to kafka", event);
      try {
        return kafkaTemplate.sendDefault(event);
      } catch (Exception e) {
        //We are ignoring the exceptions here.
        log.error("Exception sending event to kafka", e);
        return null;
      }
    } else {
      log.debug("Skipping send to Kafka since EventAudit is not enabled.");
    }
    return null;
  }

  @Override
  public ListenableFuture<SendResult<String, Event>> send(String key, Event event) {
    if (enabled) {
      log.debug("Sending (event={}) to kafka", event);
      try {
        return kafkaTemplate.sendDefault(key, event);
      } catch (Exception e) {
        //We are ignoring the exceptions here.
        log.error("Exception sending event to kafka", e);
        return null;
      }
    } else {
      log.debug("Skipping send to Kafka since EventAudit is not enabled.");
    }
    return null;
  }

  @Override
  public ListenableFuture<SendResult<String, Event>> send(EventRecord<String, Event> record) {
    if (enabled) {
      log.debug("Sending (eventRecord={}) to kafka", record);
      try {
        return kafkaTemplate.send(record.getProducerRecord());
      } catch (Exception e) {
        //We are ignoring the exceptions here.
        log.error("Exception sending event to kafka", e);
        return null;
      }
    } else {
      log.debug("Skipping send to Kafka since EventAudit is not enabled.");
    }
    return null;
  }

}
