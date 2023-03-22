package com.suraj.elasticsearch;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.NonNull;
import org.elasticsearch.action.ingest.GetPipelineRequest;
import org.elasticsearch.action.ingest.GetPipelineResponse;
import org.elasticsearch.action.ingest.PutPipelineRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.PutIndexTemplateRequest;
import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Repository for ElasticSearch operations.
 *
 * @author suraj kumar
 */
@Component(ElasticSearchRepository.BEAN_NAME)
public class ElasticSearchRepository {

  /**
   * Bean name.
   */
  public static final String BEAN_NAME = "commonElasticSearchRepository";

  /**
   * {@link RestHighLevelClient} instance.
   */
  private final RestHighLevelClient restHighLevelClient;

  @Autowired
  public ElasticSearchRepository(
      @Qualifier("commonElasticSearchClient") final RestHighLevelClient restHighLevelClient) {
    this.restHighLevelClient = restHighLevelClient;
  }

  /**
   * Get pipeline from ElasticSearch.
   *
   * @param pipelineName pipeline name
   * @return {@link GetPipelineResponse}
   * @throws IOException {@link IOException}
   */
  public GetPipelineResponse getPipeline(@NonNull String pipelineName)
      throws IOException {
    GetPipelineRequest getPipelineRequest = new GetPipelineRequest(pipelineName);
    return restHighLevelClient.ingest().getPipeline(getPipelineRequest, RequestOptions.DEFAULT);
  }

  /**
   * Put pipeline to ElasticSearch.
   *
   * @param pipelineName   pipeline name
   * @param pipelineString pipeline string
   * @return {@link AcknowledgedResponse}
   * @throws IOException {@link IOException}
   */
  public AcknowledgedResponse putPipeline(@NonNull String pipelineName,
      @NonNull String pipelineString)
      throws IOException {
    PutPipelineRequest putPipelineRequest = new PutPipelineRequest(pipelineName,
        new BytesArray(pipelineString.getBytes(StandardCharsets.UTF_8)), XContentType.JSON);
    return restHighLevelClient.ingest().putPipeline(putPipelineRequest, RequestOptions.DEFAULT);
  }

  /**
   * Put index template to Elasticsearch.
   *
   * @param indexTemplateRequest the index template request
   * @return the acknowledged response
   * @throws IOException the io exception.
   */
  public AcknowledgedResponse putIndexTemplate(
      @NonNull final PutIndexTemplateRequest indexTemplateRequest) throws IOException {
    return restHighLevelClient.indices().putTemplate(indexTemplateRequest, RequestOptions.DEFAULT);
  }

}
