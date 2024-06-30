package com.datmt.tools.messagesource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

public class CustomLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        if (StringUtils.hasText(lang)) {
            return Locale.forLanguageTag(lang);
        }

        String acceptLanguageHeader = request.getHeader("Accept-Language");
        if (StringUtils.hasText(acceptLanguageHeader)) {
            return request.getLocale();
        }

        return Locale.getDefault();
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
