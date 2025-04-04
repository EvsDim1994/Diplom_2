import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class CreateUsersTests extends BaseTest {

    @Test
    @DisplayName("Успешное создание пользователя")
    public void createUserTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        assertNotNull(userToken);
    }

    @Test
    @DisplayName("Ошибка при создание существующего пользователя")
    public void createUserWithTheSameDateTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(403)
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Ошибка при создание пользователя без email")
    public void createUserWithOutEmail() {
        var user = User.random();
        user.setEmail(null);
        requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Ошибка при создание пользователя без password")
    public void createUserWithOutPassword() {
        var user = User.random();
        user.setPassword(null);
        requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Ошибка при создание пользователя без name")
    public void createUserWithOutName() {
        var user = User.random();
        user.setName(null);
        requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }

}
