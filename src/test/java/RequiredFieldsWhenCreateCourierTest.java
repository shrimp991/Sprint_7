import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class RequiredFieldsWhenCreateCourierTest {
    private Courier courier;
    private CourierClient courierClient;

    @Before
    @Step("Инициализация courierClient")
    public void setUpTest() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Проверка невозможности создания курьера без логина")
    @Description("Осуществляется POST-запрос для ручки /api/v1/courier, должен вернуться код 400")
    @Step("Присвоение курьеру данных для авторизации")
    public void shouldNotCreateCourierWithoutLoginTest() {
        courier = CourierGenerator.getWithoutLogin();
    }

    @Test
    @DisplayName("Проверка невозможности создания курьера без пароля")
    @Description("Осуществляется POST-запрос для ручки /api/v1/courier/login, должен вернуться код 400")
    @Step("Присвоение курьеру данных для авторизации")
    public void shouldNotCreateCourierWithoutPasswordTest() {
        courier = CourierGenerator.getWithoutPassword();
    }

    @After
    @Step("Попытка создать курьера без логина или пароля")
    public void generalStepsTest() {
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
    }
}
