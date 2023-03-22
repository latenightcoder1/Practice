package com.suraj.elasticsearch;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * Enable Auto sync of ElasticSearch Pipeline with configuration as specified with code.
 *
 * <p>
 * With this annotation pipelines configured with common.elasticsearch.pipelines will be synced with
 * elasticsearch
 *</p>
 *
 * @author suraj kumar
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PipelineConfig.class, ElasticSearchConfig.class})
public @interface AutoSyncPipelines {

}
