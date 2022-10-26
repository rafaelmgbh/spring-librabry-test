package com.springlibrabryapi.librarycontrol.services;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Duration;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class RatedLimitServices {

    final Bucket bucket;
    //Melhorar a visibilidade as vezes o controle da variavel esta em conflito com o bucket

    public RatedLimitServices() {
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder().addLimit(limit).build();
    }


    public Bucket getBucket() {
        LOGGER.info("Token consumption performed successfully !");
        LOGGER.info(bucket.getAvailableTokens() + " Tokens left");
        return bucket;
    }


}
