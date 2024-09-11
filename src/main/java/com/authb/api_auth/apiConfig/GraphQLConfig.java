package com.authb.api_auth.apiConfig;

import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {
    @Bean
    public GraphQLScalarType dateScalar() {
        return DateScalar.DATE; // Register your custom scalar here
    }
}
