package config;

import java.util.Properties;
import java.util.Random;
import javax.sql.DataSource;

import ca.rlm.orlando.core.entity.PackageInfo;
import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * Java based application context configuration class. <BR>
 * Including data source and transaction manager configuration.
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@ComponentScan(basePackageClasses = {
  ca.rlm.orlando.core.service.PackageInfo.class, ca.rlm.orlando.core.component.PackageInfo.class})
@Configuration
@EnableJpaRepositories(basePackageClasses = ca.rlm.orlando.core.dao.PackageInfo.class)
@EnableTransactionManagement
@Slf4j
public class ApplicationContext {

  private static final String AUTO_COMMIT = "hibernate.connection.autocommit";

  private static final String FORMAT_SQL = "hibernate.format_sql";

  private static final String AUTO_DDL = "hibernate.hbm2ddl.auto";

  private static final String SHOW_SQL = "hibernate.show_sql";

  private static final String CONTEXT_CLASS = "hibernate.current_session_context_class";

  private static final String DIALECT = "hibernate.dialect";

  //Entity package
  public static final String PACKAGE_TO_SCAN = PackageInfo.class.getPackage().getName();

  @Value("${" + AUTO_COMMIT + "}")
  private String autocommit;

  @Value("${" + FORMAT_SQL + "}")
  private String formatSql;

  @Value("${" + AUTO_DDL + "}")
  private String autoDdl;

  @Value("${" + SHOW_SQL + "}")
  private String showSql;

  @Value("${" + CONTEXT_CLASS + "}")
  private String contextClass;

  @Value("${" + DIALECT + "}")
  private String dialect;

  @Value("${jdbc.username}")
  private String username;

  @Value("${jdbc.password}")
  private String password;

  @Value("${jdbc.driverClassName}")
  private String driverClassName;

  @Value("${jdbc.url}")
  private String url;

  /**
   * Placeholder configurer.
   *
   * @return bean
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  /**
   * Gson.
   *
   * @return bean
   */
  @Bean
  public Gson gson() {
    return new Gson();
  }

  /**
   * Random generator.
   *
   * @return bean
   */
  @Bean
  public Random random() {
    return new Random();
  }

  //<editor-fold defaultstate="collapsed" desc="HikariCP Data Source Configuration">

  /**
   * Create data source.
   *
   * @return data source
   */
  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    final HikariDataSource dataSource = new HikariDataSource();
    dataSource.setUsername(this.username);
    dataSource.setPassword(this.password);
    dataSource.setJdbcUrl(this.url);
    dataSource.setDriverClassName(this.driverClassName);
    dataSource.setConnectionTestQuery("SELECT 1;");
    dataSource.setMaximumPoolSize(3);
    dataSource.setAutoCommit(false);
    return dataSource;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Hibernate Session factory configuration">

  /**
   * Create hibernate properties.
   *
   * @return properties
   */
  @Bean
  public Properties hibernateProperties() {
    final Properties hibernateProperties = new Properties();
    hibernateProperties.put(DIALECT, dialect);
    hibernateProperties.put(CONTEXT_CLASS, contextClass);
    hibernateProperties.put(AUTO_COMMIT, autocommit);
    hibernateProperties.put(FORMAT_SQL, formatSql);
    hibernateProperties.put(AUTO_DDL, autoDdl);
    hibernateProperties.put(SHOW_SQL, showSql);
    return hibernateProperties;
  }

  /**
   * Create session factory.
   *
   * @param dataSource          source
   * @param hibernateProperties hibernate
   * @return bean
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource,
                                                            final Properties hibernateProperties) {

    final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.POSTGRESQL);
    vendorAdapter.setGenerateDdl(true);

    final LocalContainerEntityManagerFactoryBean factory
      = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan(PACKAGE_TO_SCAN);
    factory.setDataSource(dataSource);
    factory.setJpaProperties(hibernateProperties);

    return factory;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Hibernate transaction manager">

  /**
   * Create transaction manager.
   *
   * @param entityManagerFactory factory
   * @return manager
   */
  @Bean
  public PlatformTransactionManager transactionManager(
    final LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    final JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory.getObject());
    return txManager;
  }
  //</editor-fold>
}

