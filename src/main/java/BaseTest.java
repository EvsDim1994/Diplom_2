import org.junit.After;

public class  BaseTest {
    protected final Requests requests = new Requests();

    protected String userToken;

    @After
    public void deleteCourier() {
        if (userToken != null && !userToken.isEmpty()) {
            requests.deleteUser(userToken)
                    .then()
                    .assertThat()
                    .statusCode(202);
        }
    }
}

