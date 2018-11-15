package com.tp.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class I18nConfig {
    private static Logger logger = LoggerFactory.getLogger(I18nConfig.class);

    @Bean(name = "localeResolver")
    public MyLocaleResolver myLocaleResolver() {
        logger.info("#####cookieLocaleResolver---create");

        MyLocaleResolver myLocaleResolver = new MyLocaleResolver();
        myLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        logger.info("#####cookieLocaleResolver:");
        return myLocaleResolver;
    }

    public static class MyLocaleResolver extends AcceptHeaderLocaleResolver {
        private Locale myLocal;
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            return myLocal==null?request.getLocale():myLocal;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
            myLocal = locale;
        }
    }
}