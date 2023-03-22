
package com.suraj.flyway;

import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Holds all the Flyway configuration details.
 *
 * @author suraj kumar
 */
@Configuration
@EnableTransactionManagement
@Log4j2
public class FlywayConfiguration {

  /**
   * Driver details.
   */
  @Value("${spring.datasource.driver}")
  private String dbDriver;
  /**
   * Password for the DB.
   */
  @Value("${spring.datasource.password}")
  private String dbPassword;
  /**
   * URl for the DB.
   */
  @Value("${spring.datasource.url}")
  private String dbUrl;
  /**
   * username of the DB.
   */
  @Value("${spring.datasource.username}")
  private String dbUsername;

  @Value("${flyway.enabled}")
  private boolean flywayEnabled;

  @Value("${flyway.baseline-on-migrate}")
  private boolean flywayBaselineOnMigrate;

  /**
   * Creates {@link Flyway} bean and migrates the database schema.
   *
   * @param dataSource {@link DataSource}
   * @return {@link Flyway}
   */
  @Bean
  public Flyway flyway(DataSource dataSource) {
    // Create the Flyway instance and point it to the database
    final Flyway flyway = new Flyway();
    flyway.setDataSource(dataSource);
    flyway.setBaselineOnMigrate(flywayBaselineOnMigrate);
    // final Start the migration
    if (flywayEnabled) {
      flyway.migrate();
    }
    return flyway;
  }

}
