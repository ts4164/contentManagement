package com.content.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

@Configuration
public class TestConfigration {

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer
				.setDefinitions(new String[] { "file:src/main/webapp/WEB-INF/tiles/tiles-main.xml" });
		configurer.setCheckRefresh(true);
		return configurer;
	}

}
