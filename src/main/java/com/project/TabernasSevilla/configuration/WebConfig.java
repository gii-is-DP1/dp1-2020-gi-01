package com.project.TabernasSevilla.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.TabernasSevilla.converters.StringToInstantConverter;


public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	GenericIdToEntityConverter idToEntityConverter;
	

	
    /* ******************************************************************* */
    /*  GENERAL CONFIGURATION ARTIFACTS                                    */
    /*  Static Resources, i18n Messages, Formatters (Conversion Service)   */
    /* ******************************************************************* */

    @Override
    public void addFormatters(FormatterRegistry registry) {
    	
        registry.addConverter(idToEntityConverter);
        registry.addConverter(new StringToInstantConverter());
    }


}
    
