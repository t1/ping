package com.github.t1.ping;

import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.security.Principal;

@Path("/")
@Slf4j
public class Ping {
    @GET public String ping() {
        log.info("got pinged");
        return "pong";
    }
}
