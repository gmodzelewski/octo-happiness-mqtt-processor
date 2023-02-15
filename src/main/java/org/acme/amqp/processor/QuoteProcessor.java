package org.acme.amqp.processor;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.vertx.core.json.JsonObject;
import org.acme.amqp.model.Quote;
import org.acme.amqp.model.QuoteRequest;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A bean consuming data from the "request" AMQP queue and giving out a random quote.
 * The result is pushed to the "quotes" AMQP queue.
 */
@ApplicationScoped
public class QuoteProcessor {

    private final Logger logger = Logger.getLogger("QuoteProcessor.java");
    private final Random random = new Random();

    @Incoming("requests")       // <1>
    @Outgoing("quotes")         // <2>
    @Blocking                   // <3>
    public Quote process(JsonObject jsonObject) {
        final String id = jsonObject.getString("id", "-");
        final long startTime = jsonObject.getLong("start_time", 1L);
        final long now = System.currentTimeMillis();
        final long quoteRequestTimeInMs = now - startTime;
        final QuoteRequest quoteRequest = new QuoteRequest(id, startTime);
        logger.log(Level.INFO, "QuoteRequest ran " + quoteRequestTimeInMs + "ms");
        return new Quote(quoteRequest.id, random.nextInt(100), quoteRequest.startTime, quoteRequestTimeInMs, now);
    }
}