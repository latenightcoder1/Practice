
package com.suraj.elasticsearch;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author suraj kumar
 */
@ConfigurationProperties(prefix = "elasticsearch.indices.templates")
@Configuration
@Getter
@ToString
public class TemplateProperties {

  @Setter
  private String directory = "templates";

  @Value("#{'${elasticsearch.indices.templates.names}'.split(',')}")
  private List<String> names;
}
