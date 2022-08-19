import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class RequiredFieldsWhenLoginCourierTest {

    private CourierClient courierClient;

    @Before
    @Step("Инициализация courierClient")
    public void setUpTest() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Проверка невозможности авторизации курьера без логина")
    @Description("Осуществляется POST-запрос для ручки /api/v1/courier/login, должен вернуться код 400")
    @Step("Попытка авторизоваться курьером без логина")
    public void checkLoginWithoutLoginTest() {
        ValidatableResponse response = courierClient.login(CourierCredentials.from(CourierGenerator.getWithoutLogin()));
        int statusCode = response.extract().statusCode();
        assertEquals("Status in incorrect", SC_BAD_REQUEST, statusCode);
    }

    @Test
    @DisplayName("Проверка невозможности авторизации курьера без пароля")
    @Description("Осуществляется POST-запрос для ручки /api/v1/courier/login, должен вернуться код 400")
    @Step("Попытка авторизоваться курьером без пароля")
    public void checkLoginWithoutPasswordTest() {
        ValidatableResponse response = courierClient.login(CourierCredentials.from(CourierGenerator.getWithoutPassword()));
        int statusCode = response.extract().statusCode();
        assertEquals("Status in incorrect", SC_BAD_REQUEST, statusCode);
    }

}
