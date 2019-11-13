package com.github.t1.ping;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
@Slf4j
public class Ping {
    @GET public String ping() {
        log.info("got pinged");
        return "ping";
    }
}
