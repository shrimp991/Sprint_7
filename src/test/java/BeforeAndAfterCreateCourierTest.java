import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;

public class BeforeAndAfterCreateCourierTest {
    protected Courier courier;
    protected CourierClient courierClient;
    protected int courierId;

    @Before
    @Step("Инициализация courier и courierClient")
    public void setCourierAndCourierClientTest () {
        courier = CourierGenerator.getDefault();
        courierClient = new CourierClient();
    }

    @After
    @Step("Удаление курьера")
    public void deleteCourierTest() {
        courierClient.delete(courierId);
    }
}
