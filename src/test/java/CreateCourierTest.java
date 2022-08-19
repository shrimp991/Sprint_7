import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.*;

public class CreateCourierTest extends BeforeAndAfterCreateCourierTest {

    @Test
    @DisplayName("Проверка что курьер успешно создается")
    @Description("В ходе теста проверяется что при POST-запросе для ручки /api/v1/courier при создании возвращается \"ok\":\"true\", код 201 и что при логине возвращается id")
    public void checkCourierCanBeCreatedTest() {
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);

        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);

        //В этом тесте статус код не нужен. Но нужен в тесте логина???
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        // assert

        courierId = loginResponse.extract().path("id");
        assertNotNull("Id is not null", courierId);
    }
}

