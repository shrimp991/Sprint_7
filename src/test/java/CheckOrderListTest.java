import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class CheckOrderListTest {

    private Order order;
    private OrderClient orderClient;

    @Test
    @DisplayName("Проверка, что возвращаемые список заказов не пустой")
    @Description("В теста осуществляется проверка, что возвращается не пустой список заказов для ручки /api/v1/orders")
    public void checkOrderListTestNotNullTest() {
        orderClient = new OrderClient();
        ValidatableResponse response = orderClient.get();
        response.assertThat().body("orders", notNullValue());
    }
}
