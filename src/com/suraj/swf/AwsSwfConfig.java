
package com.suraj.swf;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClientBuilder;
import com.amazonaws.services.simpleworkflow.flow.spring.SpringActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.spring.SpringWorkflowWorker;
import com.amazonaws.services.simpleworkflow.flow.spring.WorkflowScope;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * AWS SWF configuration.
 *
 * @author suraj kumar
 */
@Configuration
public class AwsSwfConfig {
  /**
   * AWS region.
   */
  @Value("${aws.region}")
  public String region;

  /**
   * AWS accessKey.
   */
  @Value("${aws.accesskey}")
  private String accessKey;

  /**
   * AWS secret key.
   */
  @Value("${aws.secretkey}")
  private String secretKey;

  /*
   * SWF domain.
   */
  @Value("${aws.swf.domain}")
  private String domain;

  /**
   * SWF workflow task list.
   */
  @Value("${aws.swf.workflow.tasklist}")
  private String workflowTaskList;

  /**
   * SWF activities task list.
   */
  @Value("${aws.swf.activity.tasklist}")
  private String activitiesTaskList;

  /**
   * SWF max WorkflowThreads.
   */
  @Value("${aws.swf.workflow.threads.max}")
  private int maxWorkflowThreads;

  /**
   * SWF max max ActivityThreads.
   */
  @Value("${aws.swf.activity.threads.max}")
  private int maxActivityThreads;

  /**
   * SWF socket timeout.
   */
  @Value("${aws.swf.socketTimeout}")
  private int swfSocketTimeout;

  /**
   * SWF connection timeout.
   */
  @Value("${aws.swf.connectionTimeout}")
  private int swfConnectionTimeout;

  /**
   * SWF client connection Time to live.
   */
  @Value("${aws.swf.connectionTTL}")
  private int swfConnectionTtl;

  /**
   * Creates {@link AWSStaticCredentialsProvider} bean.
   *
   * @return {@link AWSStaticCredentialsProvider}
   */
  @Bean
  public AWSStaticCredentialsProvider awsCredentials() {
    return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
  }

  /**
   * Creates {@link SpringWorkflowWorker} bean.
   *
   * @return {@link SpringWorkflowWorker}
   */
  @Bean
  public SpringWorkflowWorker workflowWorker(final AmazonSimpleWorkflow amazonSimpleWorkflow) {
    try {
      final SpringWorkflowWorker workflowWorker =
          new SpringWorkflowWorker(amazonSimpleWorkflow, domain, workflowTaskList);
      List workflowImplementations = new ArrayList<>();
      workflowImplementations.add(setDeviceConfigWorkflow());
      workflowImplementations.add(saveDeviceConfigWorkflow());
      workflowWorker.setWorkflowImplementations(workflowImplementations);
      workflowWorker.setPollThreadCount(maxWorkflowThreads);
      workflowWorker.setDomainRetentionPeriodInDays(1);
      workflowWorker.setRegisterDomain(true);
      workflowWorker.setDisableServiceShutdownOnStop(true);
      return workflowWorker;
    } catch (final InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("unable to initialize workflow worker", e);
    }
  }

  /**
   * Creates {@link SpringActivityWorker} bean.
   *
   * @return {@link SpringActivityWorker}
   */
  @Bean
  public SpringActivityWorker activityWorker(final AmazonSimpleWorkflow amazonSimpleWorkflow, //
      final SetDeviceConfigActivities setDeviceConfigActivities,
      final SaveDeviceConfigActivities saveDeviceConfigActivities) {
    try {
      final SpringActivityWorker renderingActivitiesWorker =
          new SpringActivityWorker(amazonSimpleWorkflow, domain, activitiesTaskList);
      renderingActivitiesWorker.addActivitiesImplementation(setDeviceConfigActivities);
      renderingActivitiesWorker.addActivitiesImplementation(saveDeviceConfigActivities);
      renderingActivitiesWorker.setTaskExecutorThreadPoolSize(maxActivityThreads);
      renderingActivitiesWorker.setRegisterDomain(true);
      renderingActivitiesWorker.setDomainRetentionPeriodInDays(7);
      renderingActivitiesWorker.setDisableServiceShutdownOnStop(true);
      return renderingActivitiesWorker;
    } catch (final InstantiationException | IllegalAccessException | SecurityException
        | NoSuchMethodException e) {
      throw new RuntimeException("unable to initialize activity worker", e);
    }
  }

  /**
   * Creates {@link AmazonSimpleWorkflow} bean.
   *
   * @return {@link AmazonSimpleWorkflow}
   */
  @Bean(destroyMethod = "shutdown")
  public AmazonSimpleWorkflow amazonSimpleWorkflow() {
    final ClientConfiguration clientConfig = new ClientConfiguration()
        .withConnectionTimeout(swfConnectionTimeout)
        .withSocketTimeout(swfSocketTimeout)
        .withConnectionTTL(swfConnectionTtl)
        .withMaxConnections(maxWorkflowThreads + maxActivityThreads  + 10); // 10 buffer
    return AmazonSimpleWorkflowClientBuilder.standard().withRegion(region)
        .withCredentials(awsCredentials()).withClientConfiguration(clientConfig).build();
  }

  /**
   * Creates {@link SetDeviceConfigWorkflowClientExternalFactory} bean.
   *
   * @return {@link SetDeviceConfigWorkflowClientExternalFactoryImpl}
   */
  @Bean
  public SetDeviceConfigWorkflowClientExternalFactory setConfigWorkflowClientExternalFactory() {
    return new SetDeviceConfigWorkflowClientExternalFactoryImpl(amazonSimpleWorkflow(), domain);
  }

  /**
   * Creates {@link SetDeviceConfigWorkflow} bean.
   *
   * @return {@link SetDeviceConfigWorkflow}
   */
  @Bean
  @Scope(value = WorkflowScope.NAME)
  public SetDeviceConfigWorkflow setDeviceConfigWorkflow() {
    return new SetDeviceConfigWorkflowImpl();
  }

  /**
   * Creates {@link SetDeviceConfigActivitiesClient} bean.
   *
   * @return {@link SetDeviceConfigActivitiesClient}
   */
  @Bean
  @Scope(value = WorkflowScope.NAME)
  public SetDeviceConfigActivitiesClient setDeviceConfigActivitiesClient() {
    return new SetDeviceConfigActivitiesClientImpl();
  }

  /**
   * Creates {@link SetDeviceConfigActivities} bean.
   *
   * @return {@link SetDeviceConfigActivities}
   */
  @Bean
  public SetDeviceConfigActivities setDeviceConfigActivities() {
    return new SetDeviceConfigActivitiesImpl();
  }

  /**
   * Creates {@link SaveDeviceConfigWorkflowClientExternalFactory} bean.
   *
   * @return {@link SaveDeviceConfigWorkflowClientExternalFactoryImpl}
   */
  @Bean
  public SaveDeviceConfigWorkflowClientExternalFactory saveConfigWorkflowClientExternalFactory() {
    return new SaveDeviceConfigWorkflowClientExternalFactoryImpl(amazonSimpleWorkflow(), domain);
  }

  /**
   * Creates {@link SaveDeviceConfigWorkflow} bean.
   *
   * @return {@link SaveDeviceConfigWorkflow}
   */
  @Bean
  @Scope(value = WorkflowScope.NAME)
  public SaveDeviceConfigWorkflow saveDeviceConfigWorkflow() {
    return new SaveDeviceConfigWorkflowImpl();
  }

  /**
   * Creates {@link SaveDeviceConfigActivitiesClient} bean.
   *
   * @return {@link SaveDeviceConfigActivitiesClient}
   */
  @Bean
  @Scope(value = WorkflowScope.NAME)
  public SaveDeviceConfigActivitiesClient saveDeviceConfigActivitiesClient() {
    return new SaveDeviceConfigActivitiesClientImpl();
  }

  /**
   * Creates {@link SaveDeviceConfigActivities} bean.
   *
   * @return {@link SaveDeviceConfigActivities}
   */
  @Bean
  public SaveDeviceConfigActivities saveDeviceConfigActivities() {
    return new SaveDeviceConfigActivitiesImpl();
  }

  /**
   * Creates {@link CustomScopeConfigurer} bean.
   *
   * @return {@link CustomScopeConfigurer}
   */
  @Bean
  public static CustomScopeConfigurer customScopeConfigurer() {
    final CustomScopeConfigurer configurer = new CustomScopeConfigurer();
    configurer.addScope(WorkflowScope.NAME, new WorkflowScope());
    return configurer;
  }
}
