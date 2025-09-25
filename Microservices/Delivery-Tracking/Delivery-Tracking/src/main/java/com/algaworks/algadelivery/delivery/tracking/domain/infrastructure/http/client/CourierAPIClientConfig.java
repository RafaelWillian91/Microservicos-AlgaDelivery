package com.algaworks.algadelivery.delivery.tracking.domain.infrastructure.http.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CourierAPIClientConfig {

    //restClient que aponta para outro microservico
    @Bean
    public CourierAPIClient courierAPIClient(RestClient.Builder builder){
       RestClient restClient =   builder.baseUrl("http://localhost:8081").build();
       RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
       HttpServiceProxyFactory proxy = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
       return proxy.createClient(CourierAPIClient.class);
    }

    //apos instanciar esses 4 componentes o Spring faz as chamadas do outro microservico
}
