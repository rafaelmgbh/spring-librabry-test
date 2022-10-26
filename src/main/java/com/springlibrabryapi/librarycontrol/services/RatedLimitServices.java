//package com.springlibrabryapi.librarycontrol.services;
//
//
//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Refill;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.IOException;
//import java.time.Duration;
//
//@Service
//public class RatedLimitServices implements javax.servlet.Filter {
//
//    private final Bucket bucket;
//
//    @Autowired
//    public RatedLimitServices(Bucket bucket) {
//        this.bucket = bucket;
//    }
//
//    private Bucket createNewBucket() {
//        long overdraft = 50;
//        Refill refill = Refill.greedy(10, Duration.ofSeconds(1));
//        Bandwidth limit = Bandwidth.classic(overdraft, refill);
//        return Bucket.builder().addLimit(limit).build();
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//    }
//}
