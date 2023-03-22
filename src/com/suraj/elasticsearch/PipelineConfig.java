package com.suraj.elasticsearch;

import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for pipeline.
 *
 * @author suraj kumar
 */
@Configuration
@Data
public class PipelineConfig {

  /**
   * Pipeline directory to use.
   */
  @Value("${common.elasticsearch.pipeline.directory:pipelines/}")
  private String pipelineDirectory = "pipelines/";

  /**
   * Enable/Disable auto creation of pipeline.
   */
  @Value("${common.elasticsearch.pipeline.autocreate:true}")
  private boolean autoCreatePipeline = true;

  /**
   * List of pipelines to sync.
   */
  @Value("#{'${common.elasticsearch.pipelines}'.split(',')}")
  private List<String> pipelines;

}
