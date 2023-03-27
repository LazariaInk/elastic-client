package com.lazari.elasticclient.config.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogData {
    @JsonProperty("file")
    private File file;
    @JsonIgnore()
    private String flags;
    @JsonProperty("offset")
    private long offset;
}
