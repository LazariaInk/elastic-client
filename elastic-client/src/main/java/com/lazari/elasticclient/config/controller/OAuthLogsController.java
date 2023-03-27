package com.lazari.elasticclient.config.controller;

import com.lazari.elasticclient.config.data.LogResponse;
import com.lazari.elasticclient.config.service.ElasticService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/logs")
public class OAuthLogsController {
    private final ElasticService elasticService;

    public OAuthLogsController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    @GetMapping("/all")
    public List<LogResponse> search() throws IOException {
        return elasticService.getAllLogs();
    }

    @GetMapping("/{field}/{value}")
    public List<LogResponse> printSpecificValues(@PathVariable(value="field") String filed,
                                                 @PathVariable String value) throws IOException {
        return elasticService.getFilteredLogsByField(filed,value);
    }
}
