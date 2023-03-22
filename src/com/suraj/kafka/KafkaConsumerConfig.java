package com.suraj.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

/**
 * Config class for consuming messages from Kafka.
 *
 * @author suraj kumar
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig extends KafkaConfig {

  /**
   * Creates consumer factory to obtain messages from kafka.
   *
   * @return Factory object
   */
  @Bean
  public ConsumerFactory<String, String> netsuiteMessageFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBrokers());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, getKafkaGroup());
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        NetsuiteMessageDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        NetsuiteMessageDeserializer.class);

    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
        new StringDeserializer());
  }

  /**
   * Creates Listener factory to listen to messages from kafka.
   *
   * @return Factory object
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> netsuiteMessageContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(netsuiteMessageFactory());
    factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
    return factory;
  }

  /**
   * Netsuite message deserializer.
   *
   * @return NetsuiteMessageDeserializer object
   */
  @Bean
  public NetsuiteMessageDeserializer netsuiteMessageDeserializer() {
    return new NetsuiteMessageDeserializer();
  }
}
