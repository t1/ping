package test;

import com.github.t1.testcontainers.jee.JeeContainer;
import com.github.t1.testcontainers.jee.WildflyContainer;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import static com.github.t1.testcontainers.jee.NamedAsMod.namedAs;
import static org.assertj.core.api.BDDAssertions.then;

@Testcontainers
class PingIT {
    @Container static JeeContainer CONTAINER = WildflyContainer.create()
        .withDeployment("target/ping.war", namedAs("ROOT.war"));

    HttpClient client = HttpClient.newHttpClient();

    @Test
    void shouldPing() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(CONTAINER.baseUri())
            .build();

        var response = client.send(request, BodyHandlers.ofString());

        then(response.statusCode()).isEqualTo(200);
        then(response.body()).isEqualTo("pong");
    }
}
