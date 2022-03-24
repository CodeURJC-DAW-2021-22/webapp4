package es.codeurjc.wallypop.security;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class DatabaseConfig {
    @Value("${DATABASE_URL:null}")
    private String databaseUrl;
    @Bean
    @ConditionalOnProperty("DATABASE_URL")
    public DataSource dataSource() throws URISyntaxException {
        HikariConfig config = new HikariConfig();
        URI uri = new URI(databaseUrl);
        String url = "jdbc:" + new URI("postgresql", null, uri.getHost(), uri.getPort(), uri.getPath(),
                uri.getQuery(), uri.getFragment()).toString();
        String[] userInfoParts = uri.getUserInfo().split(":");
        String username = userInfoParts[0];
        String password = userInfoParts[1];
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setAutoCommit(false);
        return new HikariDataSource(config);
    }
}
