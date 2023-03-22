
package com.suraj.mongo;

import com.mongodb.MongoClientSettings;
import com.tarana.tcc.common.exceptions.SystemException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Client configuration for MongoDb connection.
 *
 * @author suraj kumar
 */
@Log4j2
@Configuration
public class MongoClientConfiguration {

  /**
   * Enable SSL connection to mongoDb.
   */
  @Value("${mongo.ssl.enabled}")
  private boolean sslEnabled = true;

  /**
   * Maximum connection idle time in second.
   */
  @Value("${mongo.connection.maxidletime.seconds:120}")
  private int maxConnectionIdleTimeSecond;

  /**
   * Retry writes flag.
   */
  @Value("${mongo.settings.retryWrites:false}")
  private boolean retryWritesEnabled;

  /**
   * Retry reads flag.
   */
  @Value("${mongo.settings.retryReads:false}")
  private boolean retryReadsEnabled;

  /**
   * Create mongoClient settings bean.
   *
   * @return {@link MongoClientSettings} bean
   */
  @Bean
  public MongoClientSettings mongoClientSettings() {
    return MongoClientSettings.builder()
        .applyToSslSettings(builder -> {
          if (sslEnabled) {
            builder.enabled(true);
            builder.invalidHostNameAllowed(true);
            try {
              builder.context(getContext());
            } catch (SystemException | IOException e) {
              log.error("Exception setting SSL context", e);
            }
          } else {
            builder.enabled(false);
          }
        })
        .applyToConnectionPoolSettings(builder ->
            builder.maxConnectionIdleTime(maxConnectionIdleTimeSecond, TimeUnit.SECONDS)
        )
        .retryWrites(retryWritesEnabled)
        .retryReads(retryReadsEnabled)
        .build();
  }


  /**
   * Build SSL context for MongoDb connection.
   *
   * @return {@link SSLContext}
   * @throws SystemException {@link SystemException}
   * @throws IOException     {@link IOException}
   */
  private SSLContext getContext() throws SystemException, IOException {
    FileInputStream fileInputStream = null;
    try {
      KeyStore keystore = KeyStore.getInstance("JKS");
      InputStream resourceAsStream = getClass().getClassLoader()
          .getResourceAsStream("rds-combined-ca-2019.jks");
      keystore.load(resourceAsStream, "changeit".toCharArray());

      TrustManagerFactory trustFactory = TrustManagerFactory
          .getInstance(TrustManagerFactory.getDefaultAlgorithm());
      trustFactory.init(keystore);

      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, trustFactory.getTrustManagers(), null);
      return sslContext;
    } catch (KeyStoreException | IOException | NoSuchAlgorithmException | KeyManagementException
        | CertificateException e) {
      throw new SystemException("Exception initializing SSL context", e);
    } finally {
      if (fileInputStream != null) {
        fileInputStream.close();
      }
    }
  }

}
