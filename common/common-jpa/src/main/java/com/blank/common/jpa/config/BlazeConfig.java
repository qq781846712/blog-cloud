package com.blank.common.jpa.config;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@Slf4j
public class BlazeConfig {

    @Bean
    public CriteriaBuilderFactory createCriteriaBuilderFactory(EntityManagerFactory entityManagerFactory) {
        CriteriaBuilderFactory criteriaBuilderFactory = Criteria.getDefault().createCriteriaBuilderFactory(entityManagerFactory);
        return criteriaBuilderFactory;
    }

}
