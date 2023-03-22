package com.suraj.elasticsearch;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.ingest.GetPipelineResponse;
import org.elasticsearch.ingest.PipelineConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Pipeline sync component for syncing elasticsearch pipelines.
 *
 * @author suraj kumar
 */
@Log4j2
@Component
public class PipelineSyncComponent {

  /**
   * Extension for pipeline files.
   */
  private static final String PIPELINE_FILE_EXTENSION = ".json";

  /**
   * {@link ElasticSearchRepository} instance.
   */
  @Resource(name = ElasticSearchRepository.BEAN_NAME)
  private ElasticSearchRepository elasticSearchRepository;

  /**
   * {@link PipelineConfig} instance.
   */
  @Resource
  private PipelineConfig pipelineConfig;

  /**
   * ObjectMapper instance.
   */
  private ObjectMapper objectMapper = new ObjectMapper()
      .configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

  /**
   * AutoSync pipelines as configured.
   */
  @PostConstruct
  public void run() {
    List<String> pipelines = pipelineConfig.getPipelines();
    log.info("Processing {} pipelines using PipelineSyncComponent", pipelines);
    for (String pipeline : pipelines) {
      try {
        PipelineConfiguration pipelineConfiguration = getElasticSearchPipeline(pipeline);
        if (pipelineConfiguration == null || comparePipeline(pipeline, pipelineConfiguration)) {
          syncPipeline(pipeline);
        }
      } catch (SystemException e) {
        log.warn("Skipping sync for pipeline {} due to exception", pipeline, e);
      }
    }
  }

  /**
   * Get Pipeline from ElasticSearch.
   *
   * @return {@link PipelineConfiguration} for pipeline, returns null if pipeline is not present.
   * @throws SystemException {@link SystemException}
   */
  private PipelineConfiguration getElasticSearchPipeline(String pipelineName)
      throws SystemException {
    try {
      GetPipelineResponse getPipelineResponse = elasticSearchRepository
          .getPipeline(pipelineName);
      if (getPipelineResponse.pipelines().size() > 0) {
        return getPipelineResponse.pipelines().get(0);
      }
      return null;
    } catch (HttpStatusCodeException e) {
      if (e.getStatusCode().is4xxClientError()) {
        log.info("Pipeline {} does not exist in elasticsearch", pipelineName);
        return null;
      } else {
        throw new SystemException("Exception fetching pipeline from elasticsearch", e);
      }
    } catch (IOException e) {
      log.error("IOException while fetching details of pipeline");
      throw new SystemException("Exception fetching pipeline from elasticsearch", e);
    }
  }

  /**
   * Sync the pipeline in ElasticSearch with local pipeline.
   *
   * @param pipelineName name of pipeline.
   * @throws SystemException {@link SystemException}
   */
  private void syncPipeline(String pipelineName) throws SystemException {
    log.info("Syncing pipeline {}", pipelineName);
    try {
      String source = getPipelineJsonString(pipelineName);
      elasticSearchRepository.putPipeline(pipelineName, source);
    } catch (Exception e) {
      log.error("Exception while syncing pipeline", e);
    }
  }

  /**
   * Compares the json from ElasticSearch - Version in ElasticSearch with version in deployed Code.
   *
   * @param pipelineName name of the pipeline.
   * @param elasticSearchPipelineConfiguration {@link PipelineConfiguration}
   * @return true if comparision of pipeline passes, false otherwise
   */
  private boolean comparePipeline(String pipelineName,
      PipelineConfiguration elasticSearchPipelineConfiguration) {
    try {
      String pipelineString = getPipelineJsonString(pipelineName);
      JsonNode pipelineJsonNode = objectMapper.readTree(pipelineString);
      String localPipelineDescription = pipelineJsonNode.get("description").textValue();
      Object elasticSearchPipelineDescription = elasticSearchPipelineConfiguration
          .getConfigAsMap()
          .get("description");
      return !StringUtils.isEmpty(localPipelineDescription)
          && Objects.nonNull(elasticSearchPipelineDescription)
          && !StringUtils.isEmpty(elasticSearchPipelineDescription.toString())
          && !localPipelineDescription.equals(elasticSearchPipelineDescription);
    } catch (Exception e) {
      log.error("Exception when comparing pipelines", e);
      return false;
    }
  }

  /**
   * Get pipeline JSON String.
   *
   * @param pipelineName name of the pipeline.
   * @return {@link String} JSON String of the pipeline.
   * @throws SystemException {@link SystemException}
   */
  private String getPipelineJsonString(String pipelineName)
      throws SystemException {
    try {
      String fileName = pipelineConfig.getPipelineDirectory() + pipelineName
          + PIPELINE_FILE_EXTENSION;
      log.info("Get local pipeline from file : {}", fileName);
      InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
      StringWriter writer = new StringWriter();
      IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8);
      inputStream.close();
      return writer.toString();
    } catch (Exception e) {
      log.error(String.format("Exception reading pipeline from local %s", pipelineName), e);
      throw new SystemException(e);
    }
  }

}
