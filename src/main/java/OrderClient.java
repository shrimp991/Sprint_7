import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    private static final String POST_AND_GET_ORDER_PATH = "/api/v1/orders";

    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(POST_AND_GET_ORDER_PATH)
                .then();
    }

    public ValidatableResponse get() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(POST_AND_GET_ORDER_PATH)
                .then();
    }
}
