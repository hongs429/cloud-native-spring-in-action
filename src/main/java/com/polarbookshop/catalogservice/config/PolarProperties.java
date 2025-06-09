package com.polarbookshop.catalogservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    private String greeting; // polar.greeting 속성을 문자열이 문자열로 인식되는 필드
}
