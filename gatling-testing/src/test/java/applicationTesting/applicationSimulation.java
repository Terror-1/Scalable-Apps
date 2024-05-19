package applicationTesting;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;

import static io.gatling.javaapi.http.HttpDsl.*;
import  java.time.Duration;

public class applicationSimulation extends Simulation {

private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8222/api").
        acceptHeader("application/json");

private static final  int USER_COUNT = Integer.parseInt(System.getProperty("USERS","20000"));
private static final  int RAMP_DURATION = Integer.parseInt(System.getProperty("USERS","5"));


@Override
public  void before(){
    System.out.printf("Running test with %d users %n" ,USER_COUNT);
    System.out.printf("Running test with %d seconds %n" ,RAMP_DURATION);

}
private  ScenarioBuilder scn = scenario("iOffer Stress Test")
        .exec(http("Get all products")
        .get("/products"));

{
    setUp(
            scn.injectOpen(
                    nothingFor(5),
                    rampUsers(USER_COUNT).during(Duration.ofMinutes(RAMP_DURATION)))
    ).protocols(httpProtocol);

    }
}
