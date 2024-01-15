package data;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static constants.Urls.*;
import static io.restassured.RestAssured.given;

public class UserClient extends Client {

    @Step("Create new user in system")
    public ValidatableResponse createUser(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post(CREATE_NEW_USER_URL)
                .then();
    }

    @Step("Login user in system with email and password")
    public ValidatableResponse loginUser(Authorization authorization) {
        return given().log().all()
                .spec(getSpec())
                .body(authorization)
                .when()
                .post(LOGIN_USER_URL)
                .then();
    }

    @Step("Delete user by token")
    public ValidatableResponse deleteUser(String accessToken) {
        return given().log().all()
                .spec(getSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_DATA_URL)
                .then();
    }
}