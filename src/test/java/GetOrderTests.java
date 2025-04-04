import Response.Ingredients;
import Response.Orders;
import io.qameta.allure.junit4.DisplayName;
import model.Order;
import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


public class GetOrderTests extends BaseTest {
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
    @DisplayName("Успешное получения заказа для авторизованного пользователя")
    public void getOrderTest() {
        var positions = new Order(List.of(ingredients.getData().get(0).getId()));
        int orderNumber = requests.createOrder(positions, userToken)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("order.number");

        var response = requests.getOrders(userToken)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Orders.class);
        assertTrue(response.getOrders().size() > 0);
        assertEquals(orderNumber, response.getOrders().get(0).getNumber());
    }

    @Test
    @DisplayName("Ошибка при получения заказа для неавторизованного пользователя")
    public void getOrderErrorTest() {
        var positions = new Order(List.of(ingredients.getData().get(0).getId()));
        int orderNumber = requests.createOrder(positions, userToken)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("order.number");

        requests.getOrders("null")
                .then()
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("You should be authorised"));
    }


}
