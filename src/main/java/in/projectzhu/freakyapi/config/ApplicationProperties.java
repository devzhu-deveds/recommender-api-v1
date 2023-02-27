package in.projectzhu.freakyapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private String configPath;

    public String getConfigPath() {
        return configPath;
    }

  public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
}
