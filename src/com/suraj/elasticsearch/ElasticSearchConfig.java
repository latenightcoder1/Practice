package com.suraj.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for ElasticSearch.
 *
 * @author suraj kumar
 */
@Configuration
public class ElasticSearchConfig {

  /**
   * Elastic Search Username.
   */
  @Value("${elasticsearch.username}")
  private String elasticSearchUsername;

  /**
   * Elastic Search Password.
   */
  @Value("${elasticsearch.password}")
  private String elasticSearchPassword;

  /**
   * Elastic Search Hostname.
   */
  @Value("${elasticsearch.hostname}")
  private String elasticSearchHostname;

  /**
   * Elastic Search Port.
   */
  @Value("${elasticsearch.port}")
  private int elasticSearchPort;

  /**
   * HTTP/HTTPS Scheme.
   */
  @Value("${elasticsearch.scheme}")
  private String elasticSearchScheme;

  /**
   * Configures {@link RestHighLevelClient} bean.
   *
   * @return {@link RestHighLevelClient}
   */
  @Bean(name = "diagnosticElasticsearchClient")
  public RestHighLevelClient getElasticsearchClient() {
    CredentialsProvider credentialProvider = new BasicCredentialsProvider();
    Credentials credentials = new UsernamePasswordCredentials(elasticSearchUsername,
        elasticSearchPassword);
    credentialProvider.setCredentials(AuthScope.ANY, credentials);
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(
                new HttpHost(elasticSearchHostname, elasticSearchPort, elasticSearchScheme))
            .setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                  httpAsyncClientBuilder.disableAuthCaching();
                  return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialProvider);
                }
            ));
    return client;
  }

}