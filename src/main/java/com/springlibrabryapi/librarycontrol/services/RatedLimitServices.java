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
    final int CAPACITY = 10;
    //CAPACITY é a quantidade de requisições que o bucket pode armazenar
    final int INTERVALLY = 10;
    //INTERVALLY é o tempo que o bucket ira esperar para adicionar mais tokens
    final int DURATION_OF_MINUTES = 1;
    //DURATION_OF_MINUTES é o tempo que o bucket ira esperar para adicionar mais tokens

    public RatedLimitServices() {
        Bandwidth limit = Bandwidth.classic(CAPACITY, Refill.intervally(INTERVALLY, Duration.ofMinutes(DURATION_OF_MINUTES)));
        //greedy , recarrega o token o mais rapido possivel .
        //intervally , recarrega o token no final do ciclo.
        this.bucket = Bucket4j.builder().addLimit(limit).build();
    }


    public Bucket getBucket() {

        return this.bucket;
    }


}
