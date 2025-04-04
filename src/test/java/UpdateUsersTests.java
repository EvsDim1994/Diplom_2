import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UpdateUsersTests extends BaseTest {

    @Test
    @DisplayName("Успешное обновлении email пользователя")
    public void updateUserEmailTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        // Обновление email пользователя
        var credentials = new User("test@mail", user.getPassword(), user.getName());

        String newEmail = requests.updateUser(userToken, credentials)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("user.email");
        assertEquals("test@mail", newEmail);
    }

    @Test
    @DisplayName("Успешное обновлении name пользователя")
    public void updateNameTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        // Обновление name пользователя
        var credentials = new User(user.getEmail(), user.getPassword(), "test");

        String newName = requests.updateUser(userToken, credentials)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("user.name");
        assertEquals("test", newName);
    }

    @Test
    @DisplayName("Успешное обновлении email пользователя")
    public void updateErrorUserEmailTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        // Обновление email пользователя
        var credentials = new User("test@mail", user.getPassword(), user.getName());

        requests.updateUser("null", credentials)
                .then()
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Ошибка при обновлении name пользователя")
    public void updateErrorNameTest() {
        var user = User.random();
        userToken = requests.createUser(user)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
        // Обновление name пользователя
        var credentials = new User(user.getEmail(), user.getPassword(), "test");

        requests.updateUser("null", credentials)
                .then()
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("You should be authorised"));
    }
}
