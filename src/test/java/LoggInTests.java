import io.qameta.allure.junit4.DisplayName;
import Model.User;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoggInTests extends BaseTest {

    @Test
    @DisplayName("Успешная авторизация пользователя")
    public void loginUserTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");

        var credentials = new User(user.getEmail(), user.getPassword());

        userToken = requests.loginUser(credentials)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        assertNotNull(userToken);
    }

    @Test
    @DisplayName("Ошибка при авторизация пользователя с неверным паролем")
    public void loginUserWithIncorrectPasswordTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        // Установление некорректного пароля
        user.setPassword("тест");

        var credentials = new User(user.getEmail(), user.getPassword());

        requests.loginUser(credentials)
                .then()
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("email or password are incorrect"));

    }

    @Test
    @DisplayName("Ошибка при авторизация пользователя с неверным email")
    public void loginUserWithIncorrectEmailTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        // Установление некорректного пароля
        user.setEmail("тест");

        var credentials = new User(user.getEmail(), user.getPassword());

        requests.loginUser(credentials)
                .then()
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("email or password are incorrect"));

    }
}
