import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class LoginCourierTest extends BeforeAndAfterCreateCourierTest {

    @Test
    @DisplayName("Проверка, что курьер может авторизоваться")
    @Description("осуществляется POST-запрос для ручки /api/v1/courier/login, в конце проверяется, что возвращается не пустой id")
    public void checkCourierCanLoginTest() {
        ValidatableResponse response = courierClient.create(courier);

        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_OK, statusCode);

        courierId = loginResponse.extract().path("id");
        assertNotNull("Id is not null", courierId);
    }

}
