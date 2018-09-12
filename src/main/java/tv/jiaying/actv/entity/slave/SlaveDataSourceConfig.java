package tv.jiaying.actv.entity.slave;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "asset.slave.datasource")
@EnableJpaRepositories(
        entityManagerFactoryRef = "slaveEntityManagerFactory",
        transactionManagerRef = "slaveTransactionManager",
        basePackages = {"tv.jiaying.actv.entity.slave"}
)
public class SlaveDataSourceConfig {

    //private HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

    @Bean
    public PlatformTransactionManager slaveTransactionManager() {
        return new JpaTransactionManager(slaveEntityManagerFactory().getObject());
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setPersistenceUnitName("slave");


        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", hbmddlAuto);
        //properties.put("hibernate.naming.physical-strategy","org.hibernate.boot.model.naming.SpringPhysicalNamingStrategy");
        //properties.put("hibernate.naming.physical-strategy","org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        factoryBean.setJpaProperties(properties);
        factoryBean.setDataSource(slaveDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(SlaveDataSourceConfig.class.getPackage().getName());


        return factoryBean;

    }

    @Bean
    public DataSource slaveDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }


    private String hbmddlAuto;
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public String getHbmddlAuto() {
        return hbmddlAuto;
    }

    public void setHbmddlAuto(String hbmddlAuto) {
        this.hbmddlAuto = hbmddlAuto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

}