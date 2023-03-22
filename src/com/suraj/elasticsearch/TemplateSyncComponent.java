package com.suraj.elasticsearch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.PutIndexTemplateRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Used to create/update index template into Elasticsearch.
 *
 * @author suraj kumar
 */
@Component
@Log4j2
@ConditionalOnProperty(name = "elasticsearch.indices.templates.sync.enable", havingValue = "true")
public class TemplateSyncComponent {

  private static final String FILE_EXTENSION = ".json";
  private final ElasticSearchRepository elasticSearchRepository;
  private final TemplateProperties templateProperties;

  @Autowired
  public TemplateSyncComponent(final ElasticSearchRepository repository,
      final TemplateProperties properties) {
    this.elasticSearchRepository = repository;
    this.templateProperties = properties;
  }

  @PostConstruct
  public void init() {

    final List<PutTemplateResponse> results = templateProperties.getNames().stream().map(name -> {
      try {
        final AcknowledgedResponse acknowledgedResponse = execute(name);
        return PutTemplateResponse.builder().name(name)
            .acknowledged(acknowledgedResponse.isAcknowledged())
            .build();
      } catch (IOException | SystemException e) {
        log.error(e.getMessage());
        return PutTemplateResponse.builder().name(name).acknowledged(false).build();
      }
    }).collect(Collectors.toList());
    final Set<String> successfulTemplates = results.stream()
        .filter(PutTemplateResponse::isAcknowledged)
        .map(PutTemplateResponse::getName)
        .collect(Collectors.toSet());
    log.info("The following templates were successfully put into Elasticsearch (names: {})",
        successfulTemplates);
    final Set<String> failedTemplates = results.stream()
        .filter(result -> !result.isAcknowledged())
        .map(PutTemplateResponse::getName)
        .collect(Collectors.toSet());
    log.warn("The following templates were failed to put into Elasticsearch (names: {})",
        failedTemplates);

  }

  /**
   * Gets acknowledged response.
   *
   * @param name the name
   * @return the acknowledged response
   * @throws IOException the io exception
   */
  AcknowledgedResponse execute(@NonNull final String name)
      throws IOException, SystemException {
    final PutIndexTemplateRequest request = new PutIndexTemplateRequest(name.trim());
    request.source(getContent(name), XContentType.JSON);
    return elasticSearchRepository.putIndexTemplate(request);
  }

  /**
   * Gets content of the file.
   *
   * @param templateName the json file
   * @return the content
   * @throws SystemException the system exception
   */
  private String getContent(final String templateName) throws SystemException {
    final String fileName = templateProperties.getDirectory().concat(File.separator)
        .concat(templateName)
        .concat(FILE_EXTENSION);
    try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
      StringWriter writer = new StringWriter();
      IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8);
      return writer.toString();
    } catch (IOException | NullPointerException e) {
      throw new SystemException("Could not find file with name " + fileName, e);
    }
  }
}
