package test;

import com.github.t1.jaxrsclienttest.JaxRsTestExtension;
import com.github.t1.ping.Ping;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;
import static org.assertj.core.api.Assertions.assertThat;

public class PingRestBehavior {

    @RegisterExtension JaxRsTestExtension jaxRs = new JaxRsTestExtension(new Ping());

    @Test void shouldGetPing() {
        Response response = jaxRs.GET("/");

        assertThat(response.getStatusInfo()).isEqualTo(OK);
        assertThat(response.readEntity(String.class)).isEqualTo("ping");
    }
}
