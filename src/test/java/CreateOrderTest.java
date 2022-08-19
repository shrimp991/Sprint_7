import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private Order order;
    private OrderClient orderClient;
    private String[] colors;

    public CreateOrderTest(String[] colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {index}")
    public static Object[][] getColors() {
        return new Object[][]{
                {new String[]{"GRAY", "BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Проверка создания заказа")
    @Description("проверка POST-запроса для ручки /api/v1/orders несколькими тестами, с разными вариациями color")
    public void orderCanBeCreated() {
        order = OrderGenerator.getDefault();
        orderClient = new OrderClient();
        ValidatableResponse response = orderClient.create(order);
        int statusCode = response.extract().statusCode();
        assertEquals("Status is incorrect", SC_CREATED, statusCode);
    }

}
