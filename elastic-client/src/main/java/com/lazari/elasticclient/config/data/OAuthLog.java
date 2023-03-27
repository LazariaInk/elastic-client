package com.lazari.elasticclient.config.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OAuthLog {
    @JsonProperty("@timestamp")
    private Date timestamp;
    @JsonProperty("@version")
    private String version;
    @JsonProperty("agent")
    private Agent agent;
    @JsonProperty("ecs")
    private Ecs ecs;
    @JsonProperty("event")
    private Event event;
    @JsonProperty("host")
    private Host host;
    @JsonProperty("log")
    private LogData log;
    @JsonProperty("message")
    private String message;
    @JsonIgnore()
    private String tags;
}
