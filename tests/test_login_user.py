import token
import allure

from src.config import Config
from src.errors import Errors
from src.path import Path
from src.requests.requests import Request


class TestLoginUser:

    def test_successfull_login_user(self, create_fake_user, register_user):
            with allure.step("формирование тела запроса"):
                payload = {
                    "email": create_fake_user["email"],
                    "password": create_fake_user["password"],
                }
            with allure.step("отправляем запрос на логин пользователя и сохраняем ответ в переменную response"): 
                client = Request(Config.URL)
                token = register_user
                response = client.post(path=Path.LOGIN, json=payload, auth_token=token)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 200
                assert response.json()["accessToken"] is not None

    def test_unsuccessfull_login_user_with_incorrect_password(self, create_fake_user, register_user):
            with allure.step("формирование тела запроса"):
                payload = {
                    "email": create_fake_user["email"],
                    "password": "111",
                }
            with allure.step("отправляем запрос на логин пользователя и сохраняем ответ в переменную response"): 
                client = Request(Config.URL)
                token = register_user
                response = client.post(path=Path.LOGIN, json=payload, auth_token=token)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 401
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.LOGIN_ERROR

    def test_unsuccessfull_login_user_with_incorrect_email(self, create_fake_user, register_user):
            with allure.step("формирование тела запроса"):
                payload = {
                    "email": "111",
                    "password": create_fake_user["password"],
                }
            with allure.step("отправляем запрос на логин пользователя и сохраняем ответ в переменную response"): 
                client = Request(Config.URL)
                token = register_user
                response = client.post(path=Path.LOGIN, json=payload, auth_token=token)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 401
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.LOGIN_ERROR
                