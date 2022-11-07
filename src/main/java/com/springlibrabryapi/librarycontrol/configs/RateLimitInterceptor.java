package com.springlibrabryapi.librarycontrol.configs;


import com.springlibrabryapi.librarycontrol.services.RatedLimitServices;
import io.github.bucket4j.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private RatedLimitServices ratedLimitServices;


    final int TOKENS_CONSUME = 1;
    //TOKENS_CONSUME é a quantidade de tokens que o bucket ira consumir

    public RateLimitInterceptor(RatedLimitServices ratedLimitServices) {
        this.ratedLimitServices = ratedLimitServices;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Bucket bucket = ratedLimitServices.getBucket();
        //verifica se o bucket esta cheio , se estiver cheio retorna um erro 429
        if (bucket.tryConsume(TOKENS_CONSUME)) {
            log.info("Request Consumed . " + bucket.getAvailableTokens() + " available tokens");
            return true;
        } else {
            log.info("Request Rejected ." + bucket.getAvailableTokens() + " available tokens");
            response.sendError(429, "Too Many Requests ");
            return false;
        }
    }



}
