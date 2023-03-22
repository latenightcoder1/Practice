
package com.suraj.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * Config class for writing messages to Kafka.
 *
 * @author suraj kumar
 */
@Configuration
public class KafkaProducerConfig extends KafkaConfig {
  /**
   * Creates a factory to create device events to be sent to kafka.
   *
   * @return Factory object
   */
  @Bean
  public ProducerFactory<String, DeviceEvent> deviceEventProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBrokers());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  /**
   * Creates Kafka temple to send messages to kafka.
   *
   * @return Kafka temple object
   */
  @Bean
  public KafkaTemplate<String, DeviceEvent> deviceEventKafkaTemplate() {
    return new KafkaTemplate<>(deviceEventProducerFactory());
  }

  /**
   * Creates a factory to create config attribute events to be sent to kafka.
   *
   * @return Factory object
   */
  @Bean
  public ProducerFactory<String, ConfigAttributeEvent> configAttributeEventProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBrokers());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  /**
   * Creates Kafka template to send messages to kafka.
   *
   * @return Kafka temple object
   */
  @Bean
  public KafkaTemplate<String, ConfigAttributeEvent> configAttributeEventKafkaTemplate() {
    return new KafkaTemplate<>(configAttributeEventProducerFactory());
  }

  /**
   * Creates a factory to create site events to be sent to kafka.
   *
   * @return Factory object
   */
  @Bean
  public ProducerFactory<Integer, SiteEvent> siteEventProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBrokers());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  /**
   * Creates Kafka temple to send messages to kafka.
   *
   * @return Kafka temple object
   */
  @Bean
  public KafkaTemplate<Integer, SiteEvent> siteEventKafkaTemplate() {
    return new KafkaTemplate<>(siteEventProducerFactory());
  }

  /**
   * Creates a factory to create operator events to be sent to kafka.
   *
   * @return Factory object
   */
  @Bean
  public ProducerFactory<Integer, OperatorEvent> operatorEventProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBrokers());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  /**
   * Creates Kafka temple to send messages to kafka.
   *
   * @return Kafka temple object
   */
  @Bean
  public KafkaTemplate<Integer, OperatorEvent> operatorEventKafkaTemplate() {
    return new KafkaTemplate<>(operatorEventProducerFactory());
  }

  /**
   * Creates a factory to create network config events to be sent to kafka.
   *
   * @return Factory object
   */
  @Bean
  public ProducerFactory<String, NetworkConfigEvent> networkEventProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBrokers());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    configProps.put(ProducerConfig.RETRIES_CONFIG, getMaxRetryCount());
    configProps.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, getRetryDelay());
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  /**
   * Creates Kafka temple to send messages to kafka.
   *
   * @return Kafka temple object
   */
  @Bean
  public KafkaTemplate<String, NetworkConfigEvent> networkEventKafkaTemplate() {
    return new KafkaTemplate<>(networkEventProducerFactory());
  }

  /**
   * Creates a factory to create auto upgrade events to be sent to kafka.
   *
   * @return Factory object
   */
  @Bean
  public ProducerFactory<String, AutoUpgradePolicyEvent> autoUpgradePolicyProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBrokers());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  /**
   * Creates Kafka temple to send messages to kafka.
   *
   * @return Kafka temple object
   */
  @Bean
  public KafkaTemplate<String, AutoUpgradePolicyEvent> autoUpgradePolicyEventKafkaTemplate() {
    return new KafkaTemplate<>(autoUpgradePolicyProducerFactory());
  }
}
