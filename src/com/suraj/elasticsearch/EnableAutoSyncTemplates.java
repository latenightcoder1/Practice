package com.suraj.elasticsearch;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * The annotation to enable auto put of templates in Elasticsearch.
 *
 * @author suraj kumar
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TemplateProperties.class, ElasticSearchConfig.class})
public @interface EnableAutoSyncTemplates {

}
