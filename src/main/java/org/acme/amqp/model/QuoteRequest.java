package org.acme.amqp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class QuoteRequest {
    public QuoteRequest() {
    }

    public QuoteRequest(String id) {
        this.id = id;
        this.startTime = 1L;
    }

    public QuoteRequest(String id, long startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    @JsonProperty("id")
    public String id;
    @JsonProperty("start_time")
    public long startTime;

    @Override
    public String toString() {
        return "QuoteRequest{" + "id='" + id + '\'' + ", start_time=" + startTime + '}';
    }
}
