package com.springlibrabryapi.librarycontrol.services;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

import org.springframework.stereotype.Service;


import java.time.Duration;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class RatedLimitServices {

    final Bucket bucket;


    public RatedLimitServices() {
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        //greedy , recarrega o token o mais rapido possivel .
        //intervally , recarrega o token no final do ciclo.
        this.bucket = Bucket4j.builder().addLimit(limit).build();
    }


    public Bucket getBucket() {
        LOGGER.info(this.bucket.getAvailableTokens() + " Tokens left");
        return this.bucket;
    }


}
