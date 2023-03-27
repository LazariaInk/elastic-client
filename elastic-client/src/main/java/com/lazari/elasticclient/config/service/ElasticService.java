package com.lazari.elasticclient.config.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.lazari.elasticclient.config.data.LogResponse;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Service
public class ElasticService {
    private static final String INDEX_NAME = "auth-server-logs-2023.03.21";
    private static final String FIELD_MESSAGE = "msg";
    private static final String FIELD_LEVEL = "level";
    private static final String FIELD_TIMESTAMP = "timestamp";
    private final ElasticsearchClient client;

    public ElasticService(RestClient restClient) {
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport);
    }

    RestClient restClient = RestClient.builder(
            new HttpHost("localhost", 9200)).build();

    ElasticsearchTransport transport = new RestClientTransport(
            restClient, new JacksonJsonpMapper());


    public List<LogResponse> getAllLogs() throws IOException {
        SearchResponse<Object> search = client.search(s -> s.index(INDEX_NAME), Object.class);
        return convertHitsToLogResponses(search.hits().hits());
    }

    public List<LogResponse> getFilteredLogsByField(String fieldName, String fieldValue) throws IOException {
        SearchResponse<Object> search = client.search(s -> s.index(INDEX_NAME).query(q -> q
                .term(t -> t.field(fieldName).value(v -> v.stringValue(fieldValue)))), Object.class);
        return convertHitsToLogResponses(search.hits().hits());
    }

    private List<LogResponse> convertHitsToLogResponses(List<Hit<Object>> hits) {
        List<LogResponse> response = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        for (Hit<Object> hit : hits) {
            assert hit.source() != null;
            String message = ((LinkedHashMap<?, ?>) hit.source()).get(FIELD_MESSAGE).toString();
            String level = ((LinkedHashMap<?, ?>) hit.source()).get(FIELD_LEVEL).toString();
            LocalDateTime time = LocalDateTime.parse(((LinkedHashMap<?, ?>) hit.source()).get(FIELD_TIMESTAMP).toString(), formatter);
            response.add(new LogResponse(message, level, time));
        }

        return response;
    }

}