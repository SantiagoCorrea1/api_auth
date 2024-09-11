package com.authb.api_auth.component;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateScalar{
    public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar()
            .name("Date")
            .description("A custom scalar that handles Date without time")
            .coercing(new Coercing<LocalDate, String>() {
                @Override
                public String serialize(Object dataFetcherResult) {
                    // Convierte LocalDate a String
                    return ((LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE);
                }
                @Override
                public LocalDate parseValue(Object input) {
                    // Convierte el input a LocalDate en una mutación
                    return LocalDate.parse(input.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
                }
                @Override
                public LocalDate parseLiteral(Object input) {
                    // Convierte el literal GraphQL a LocalDate
                    if (input instanceof StringValue) {
                        return LocalDate.parse(((StringValue) input).getValue(), DateTimeFormatter.ISO_LOCAL_DATE);
                    }
                    return null;
                }
            })
            .build();
}
