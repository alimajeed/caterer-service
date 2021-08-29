package com.hunzaconsulting.catererservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    public static final String DEFAULT_PAGE_NUMBER = "1";

    public static final String DEFAULT_PAGE_SIZE = "5";

    public static int MAX_PAGE_SIZE = 50;

    @Value("${max_page_size}")
    public void setMaxPageSize(int maxPageSize){
        MAX_PAGE_SIZE = maxPageSize;
    }

}
