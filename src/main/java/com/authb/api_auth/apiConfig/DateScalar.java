package com.authb.api_auth.apiConfig;

import graphql.schema.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateScalar {
    public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar()
            .name("Date")
            .description("A custom scalar for handling dates in the format YYYY-MM-DD")
            .coercing(new Coercing<LocalDate, String>() {
                @Override
                public String serialize(Object dataFetcherResult) {
                    return ((LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE);
                }

                @Override
                public LocalDate parseValue(Object input) {
                    return LocalDate.parse((String) input);
                }

                @Override
                public LocalDate parseLiteral(Object input) {
                    return LocalDate.parse((String) input);
                }
            })
            .build();
}
