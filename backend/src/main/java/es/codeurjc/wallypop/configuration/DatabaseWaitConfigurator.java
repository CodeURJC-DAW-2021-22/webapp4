package es.codeurjc.wallypop.configuration;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.stream.Stream;

@SpringBootConfiguration
public class DatabaseWaitConfigurator {
    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf ->
        {
// Let beans that need the database depend on the DatabaseStartupValidator
// like the JPA EntityManagerFactory
            String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
            Stream.of(jpa)
                    .map(bf::getBeanDefinition)
                    .forEach(it -> it.setDependsOn("databaseStartupValidator"));
        };
    }

    @Bean
    public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
        DatabaseStartupValidator dsv = new DatabaseStartupValidator();
        dsv.setDataSource(dataSource);
// Configuration delay and timeout
        dsv.setInterval(5);
        dsv.setTimeout(120);
        return dsv;
    }
}
