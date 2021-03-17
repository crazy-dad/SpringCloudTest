package com.fist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*@Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String url = "http://httpbin.org:80";
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(url))
                .route(p -> p.host("*.hystrix.com")
                        .filters(f -> f.hystrix(config -> config
                                .setName("mycmd")
                                .setFallbackUri("forward:/fallback")))
                        .uri(url))
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }*/

//    /**
//     * Filters Test
//     * @param builder
//     * @return
//     */
//    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        String url = "http://httpbin.org:80";
//        return builder.routes()
//                .route(p -> p.path("/customer/**")
//                        .filters(f -> f.filter(new RequestTimeFilter())
//                                .addRequestHeader("X-Response-Default-Foo", "Default-Bar"))
//                        .uri(url)
//                        .order(0)
//                        .id("customer_filter_router"))
//                .build();
//    }

    /**
     * Filters Test
     */
    /*@Bean
    public RequestTimeGatewayFilterFactory getRequestTimeFactory() {
        return new RequestTimeGatewayFilterFactory();
    }*/

    /**
     * GlobalFilter Test
     */
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
}
