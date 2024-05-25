package applicationTesting;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;

import static io.gatling.javaapi.http.HttpDsl.*;
import  java.time.Duration;

public class productsServiceSimulation extends Simulation {

private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8222/api").
        acceptHeader("application/json")
        .contentTypeHeader("application/json");

private static FeederBuilder.FileBased<Object> reviewFeeder = jsonFile("data/reviews.json").random();
private static FeederBuilder.FileBased<Object> userFeeder = jsonFile("data/users4.json").queue();

private static final  int USER_COUNT = Integer.parseInt(System.getProperty("USERS","2"));
private static final  int RAMP_DURATION = Integer.parseInt(System.getProperty("RAMP_DURATION","1"));
private static final  int WAIT = Integer.parseInt(System.getProperty("WAIT","0"));
private static final  String PRODUCT_ID = "10";


@Override
public  void before(){
    System.out.printf("Running test with %d users %n" ,USER_COUNT);
    System.out.printf("Running test with %d seconds %n" ,RAMP_DURATION);

}

    public static int randNum(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static ChainBuilder getAllProducts =
            exec(http("get all products").get(""));
    private static ChainBuilder getPopularProducts =
            exec(http("get popular products").get("/get-popular-products"));
    private static ChainBuilder getMenProducts =
            exec(http("get all men products").get("/all-man"));
    private static ChainBuilder searchProduct =
            exec(http("search for a blue product").get("/search").queryParam("query","blue"));
    private static ChainBuilder getProductById =
            exec(http("get product by Id")
                    .get("/product")
                    .body(StringBody(PRODUCT_ID)));
    private static ChainBuilder getProductReviews =
            exec(http("get product reviews")
                    .get("/product/get-reviews")
                    .body(StringBody(PRODUCT_ID)));
    private static ChainBuilder addReview =
            feed(reviewFeeder)
                    .exec(http("add review to product")
                    .post("/products/product/add-review")
                    .body(ElFileBody("bodies/reviewTemplate.json")).asJson()
            );

    private static ChainBuilder register =
            feed(userFeeder)
                    .exec(http("register new user")
                            .post("/customers/register")
                            .body(ElFileBody("bodies/newUserTemplate.json")).asJson());
    private static ChainBuilder login =
            exec(http("login user")
                    .post("/customers/login")
                    .body(ElFileBody("bodies/loginUserTemplate.json")).asJson());
    private static ChainBuilder logout=
            exec(http("logout user")
                .post("/customers/logout"));

    private ScenarioBuilder addReviewScn = scenario("add reviews to products")
            .exec(register)
            .exec(login)
            .exec(addReview)
            .exec(logout);
private ScenarioBuilder allProducts = scenario("all products test")
        .exec(getAllProducts);
private ScenarioBuilder getPopular = scenario("get popular test")
        .exec(getPopularProducts);
private ScenarioBuilder getMen = scenario("get men test")
        .exec(getMenProducts);
private ScenarioBuilder getById = scenario("getProductById test")
        .exec(getProductById);
private ScenarioBuilder getReviews = scenario("get Reviews test")
        .exec(getProductReviews);
private ScenarioBuilder search = scenario("searchProduct test")
        .exec(searchProduct);

//private ScenarioBuilder addReview = scenario("all products test")
//        .exec(getAllProducts);

//private ScenarioBuilder deleteReview = scenario("all products test")
//        .exec(getAllProducts);
//private ScenarioBuilder addToCart = scenario("all products test")
//        .exec(getAllProducts);

private  ScenarioBuilder scn = scenario("Product service Test")
        .exec(getAllProducts)
        .pause(2)
        .exec(getPopularProducts)
        .pause(2)
        .exec(getMenProducts)
        .pause(2)
        .exec(searchProduct)
        .pause(2)
        .exec(getProductById);
{
    setUp(
            addReviewScn.injectOpen(
                    nothingFor(WAIT),
                    rampUsers(USER_COUNT).during(RAMP_DURATION)
            )

//            allProducts.injectOpen(
//                    nothingFor(WAIT),
//                    atOnceUsers(USER_COUNT)
//            )
//            .andThen(
//                    getPopular.injectOpen(
//                            nothingFor(WAIT),
//                            atOnceUsers(USER_COUNT)
//
//                    ))
//            .andThen(
//                    getMen.injectOpen(
//                            nothingFor(WAIT),
////                            rampUsers(USER_COUNT).during(RAMP_DURATION)
//                            atOnceUsers(USER_COUNT)
//
//
//                    )
//                    )
//            .andThen(
//                    search.injectOpen(
//                            nothingFor(WAIT),
//                            atOnceUsers(USER_COUNT)
//
//                    ))
//            .andThen(
//                    getById.injectOpen(
//                            nothingFor(WAIT),
//                            atOnceUsers(USER_COUNT)
//
//                    )
//            )
//            .andThen(
//                    getReviews.injectOpen(
//                            nothingFor(WAIT),
//                            atOnceUsers(USER_COUNT)
//
//                    )
//            )

    ).protocols(httpProtocol);

    }
    }

