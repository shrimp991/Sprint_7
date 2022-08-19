import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateDuplicateCourierTest extends BeforeAndAfterCreateCourierTest {

    @Test
    @DisplayName("Проверка, что нельзя создать дубль курьера")
    @Description("Перед созданием дубля курьера, осуществляется проверка, что первый курьер был создан, затем осуществляется логин, чтобы получить id для удаления первого курьера")
    public void checkThatImpossibleToCreateDuplicateTest() {
        ValidatableResponse response = courierClient.create(courier);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        courierId = loginResponse.extract().path("id");

        ValidatableResponse duplicateResponse = courierClient.create(courier);
        int statusCode = duplicateResponse.extract().statusCode();
        assertEquals("Duplicate courier has been created", SC_CONFLICT, statusCode);
    }
}
