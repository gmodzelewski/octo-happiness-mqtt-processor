package org.acme.amqp.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Quote {
    public String id;
    public int price;
    public long requestStartTime;
    public long requestTimeInMs;
    public long quoteStartTime;

    /**
     * Default constructor required for Jackson serializer
     */
    public Quote() {
    }

    public Quote(String id, int price, long requestStartTime, long requestTimeInMs, long quoteStartTime) {
        this.id = id;
        this.price = price;
        this.requestStartTime = requestStartTime;
        this.requestTimeInMs = requestTimeInMs;
        this.quoteStartTime = quoteStartTime;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", requestStartTime=" + requestStartTime +
                ", requestTimeInMs=" + requestTimeInMs +
                ", quoteStartTime=" + quoteStartTime +
                '}';
    }
}