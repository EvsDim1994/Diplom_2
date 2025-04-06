import io.qameta.allure.Step;
import io.restassured.response.Response;
import Model.Order;
import Model.User;

public class Requests extends Client {
    @Step("Send POST request to /auth/register")
    public Response createUser(User user){
        return spec()
                .body(user)
                .when()
                .post(Endpoints.CREATE_USER);
    }

    @Step("Send DELETE request to /api/auth/user")
    public Response deleteUser(String token){
        return spec()
                .header("Authorization", token)
                .when()
                .delete(Endpoints.UPDATE_USER);
    }

    @Step("Send POST request to /api/auth/login")
        public Response loginUser(String token, User user){
            return spec()
                    .header("Authorization", token)
                    .body(user)
                    .when()
                    .post(Endpoints.LOGIN_USER);
    }

    @Step("Send PATCH request to /api/auth/user")
    public Response updateUser(String token, User user){
        return spec()
                .header("Authorization", token)
                .body(user)
                .when()
                .patch(Endpoints.UPDATE_USER);
    }

    @Step("Send POST request to /api/orders")
    public Response createOrder(Order order, String token){
        return spec()
                .body(order)
                .when()
                .post(Endpoints.ORDER);
    }

    @Step("Send GET request to /api/ingredients")
    public Response getIngredients(String token){
        return spec()
                .header("Authorization", token)
                .when()
                .get(Endpoints.GET_INGREDIENTS);
    }

    @Step("Send GET request to /api/orders")
    public Response getOrders(String token){
        return spec()
                .header("Authorization", token)
                .when()
                .get(Endpoints.ORDER);
    }
}
