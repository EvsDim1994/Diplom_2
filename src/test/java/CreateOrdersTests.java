import Response.Ingredients;
import io.qameta.allure.junit4.DisplayName;
import Model.Order;
import Model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class CreateOrdersTests extends BaseTest {
    private Ingredients ingredients;

    @Before
    public void setUp(){
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        ingredients = requests.getIngredients(userToken)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Ingredients.class);
    }

    @Test
    @DisplayName("Успешное создание заказа c ingredients")
    public void createOrderTest() {
        var positions = new Order(List.of(ingredients.getData().get(0).getId()));
        int orderNumber = requests.createOrder(positions, userToken)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("order.number");
        assertTrue(orderNumber > 0);
    }

    @Test
    @DisplayName("Ошибка при создание заказа без ingredients")
    public void createOrderWithOutIngredientsTest() {
        var positions = new Order(null);
        requests.createOrder(positions, userToken)
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Ошибка при создание заказа без token")
    public void createErrorOrderTest() {
        var positions = new Order(List.of(ingredients.getData().get(0).getId()));
        requests.createOrder(positions, "null")
                .then()
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Ошибка при создание заказа с неверным хешем ingredients")
    public void createErrorWithIncorrectHashTest() {
        String hash = "61c0c5a71d1f82001bdaaa6d22";
        var positions = new Order(List.of(hash));
        requests.createOrder(positions, userToken)
                .then()
                .assertThat()
                .statusCode(500);
    }
}
