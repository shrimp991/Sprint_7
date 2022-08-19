import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.Assert.assertEquals;

public class LoginNonExistentCourierTest {

    private CourierClient courierClient;

    @Test
    @DisplayName("Проверка, что система вернёт ошибку, если неправильно указать логин или пароль")
    @Description("Осуществляется POST-запрос для ручки /api/v1/courier/login с неверными логином и паролем, должен вернуться код 404")
    public void checkLoginNonExistentCourierTest () {
        courierClient = new CourierClient();
        ValidatableResponse response = courierClient.login(CourierCredentials.from(CourierGenerator.getNonExistent()));
        int statusCode = response.extract().statusCode();
        assertEquals("Status is incorrect", SC_NOT_FOUND, statusCode);
    }
}
