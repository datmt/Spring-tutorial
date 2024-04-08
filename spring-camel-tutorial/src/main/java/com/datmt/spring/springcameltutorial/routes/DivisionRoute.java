package com.datmt.spring.springcameltutorial.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DivisionRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .handled(true)
                .to("direct:handleException");

        from("direct:handleException")
                .process(exchange -> {
                    Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    // Create a response object representing the error
                    ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getMessage());
                    exchange.getIn().setBody(errorResponse);
                })
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"));

        from("direct:divide")
                .log("Received message: ${body}")
                .bean("divisionService", "divide")
                .log("Result: ${body}");
    }

    public static record ErrorResponse(String message, String details) {
        @Override
        public String toString() {
            return "{" +
                    "message='" + message + '\'' +
                    ", details='" + details + '\'' +
                    '}';
        }
    }
}
