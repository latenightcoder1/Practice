
package com.suraj.kafka;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides config for kafka.
 *
 * @author suraj kumar
 */
@Data
@Configuration
public class KafkaConfig {

  @Value("${kafka.brokers}")
  private String kafkaBrokers;

  @Value("${event.retry.count:3}")
  private int maxRetryCount;

  @Value("${event.retry.delay.milliseconds:5000}")
  private int retryDelay;

  @Value("${kafka.group-id}")
  private String kafkaGroup;

  @Value("${kafka.commit.batchsize:50}")
  private int commitBatchSize;

  @Value("${kafka.poll.max.interval:20000}")
  private int maxPollIntervalMs;

  @Value("${kafka.poll.max.records:100}")
  private int maxPollRecords;

  @Value("${kafka.commit.interval-milliseconds:5000}")
  private int commitIntervalMilliseconds;

  @Value("${kafka.commit.async:true}")
  private boolean asyncCommits;
}
