package applicationTesting;//package applicationTesting;
//import io.gatling.javaapi.core.*;
//import io.gatling.javaapi.http.*;
//
//import static io.gatling.javaapi.core.CoreDsl.*;
//
//import static io.gatling.javaapi.http.HttpDsl.*;
//public class CustomerServiceSimulation extends Simulation  {
//    private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8222/api/customers").
//            acceptHeader("application/json")
//            .contentTypeHeader("application/json");
//    private static FeederBuilder.FileBased<Object> userFeeder = jsonFile("data/users4.json").queue();
//
//
//    private static final  int USER_COUNT = Integer.parseInt(System.getProperty("USERS","500"));
//    private static final  int RAMP_DURATION = Integer.parseInt(System.getProperty("RAMP_DURATION","10"));
//    private static final  int WAIT = Integer.parseInt(System.getProperty("WAIT","0"));
//
//    @Override
//    public  void before(){
//
//        System.out.println("Customer Service tests");
//        System.out.printf("Running test with %d users %n" ,USER_COUNT);
//        System.out.printf("ramping users over %d seconds %n" ,RAMP_DURATION);
//
//    }
//
//    private static ChainBuilder register =
//            feed(userFeeder)
//                    .exec(http("register new user")
//                    .post("/register")
//                    .body(ElFileBody("bodies/newUserTemplate.json")).asJson());
//    private static ChainBuilder login =
//    exec(http("login user")
//            .post("/login")
//            .body(ElFileBody("bodies/loginUserTemplate.json")).asJson());
//    private static ChainBuilder logout=
//            exec(http("logout user")
//                .post("/logout"));
//    private static ChainBuilder addCard =
//            exec(
//                    http("add card")
//                    .post("/add-card")
//                    .body(StringBody("tok_visa"))
//                    .check(bodyString().saveAs("cardId"))
//            );
//    private static ChainBuilder setDefaultMethod=
//         exec(
//                http("set default payment method")
//                        .post("/set-default-payment-method")
//                        .body(StringBody("#{cardId}"))
//        );
//    private static ChainBuilder addAddress =
//            exec(
//                    http("add address for user")
//                            .post("/add-address")
//                            .body(ElFileBody("bodies/addressTemplate.json"))
//            );
//    private static ChainBuilder getAddress =
//            exec(
//                    http("get address for user")
//                            .get("/get-address")
//                            .check(jmesPath("city").isEL("#{city}"))
//            );
//
//
//
//
//    private static ScenarioBuilder registerLoginLogout = scenario("register login logout test")
//            .exec(register)
////            .pause(2)
//            .exec(login)
////            .pause(2)
//            .exec(logout);
////            .exec(session -> {
////                CardId = session.getString("cardId").split(" ")[2];
////                System.out.println("Extracted Card ID: " + CardId);
////                System.out.println("session: " + session);
////                return session;
////            })
////            .exec(setDefaultMethod)
////            .pause(2)
////            .exec(addAddress)
////            .pause(2)
////            .exec(getAddress);
//    private static ScenarioBuilder loginScn = scenario("login test")
//            .exec(login);
//
//    {
//        setUp(
//                registerLoginLogout.injectOpen(
//                    nothingFor(WAIT),
//                    rampUsers(USER_COUNT).during(RAMP_DURATION)
//                )
//
//
//
//        ).protocols(httpProtocol);
//    }
//
//
//}
