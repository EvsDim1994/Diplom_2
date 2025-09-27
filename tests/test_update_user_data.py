import allure

from src.config import Config
from src.errors import Errors
from src.helpers.faker import fake_user
from src.path import Path
from src.requests.requests import Request


class TestUpdateUser:

    def test_successfull_update_user_name(self, create_fake_user, register_user):
            with allure.step("формирование тела запроса"):
                new_name = fake_user()["name"]
                payload = {
                    "email": create_fake_user["email"],
                    "name": new_name
                }
            with allure.step("отправляем запрос на обновление данных пользователя и сохраняем ответ в переменную response"):
                token = register_user 
                client = Request(Config.URL)
                response = client.patch(path=Path.UPDATE_USER, json=payload, auth_token=token)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 200
                assert response.json()["success"] == True
                assert response.json()["user"]["name"] == new_name


    def test_successfull_update_user_email(self, create_fake_user, register_user):
            with allure.step("формирование тела запроса"):
                new_email =  fake_user()["email"]
                payload = {
                    "email": new_email,
                    "name": create_fake_user["name"]
                }
            with allure.step("отправляем запрос на обновление данных пользователя и сохраняем ответ в переменную response"):
                token = register_user 
                client = Request(Config.URL)
                response = client.patch(path=Path.UPDATE_USER, json=payload, auth_token=token)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 200
                assert response.json()["success"] == True
                assert response.json()["user"]["email"] == new_email

    def test_unsuccessfull_update_user_name(self, create_fake_user, register_user):
            with allure.step("формирование тела запроса"):
                new_name = fake_user()["name"]
                payload = {
                    "email": create_fake_user["email"],
                    "name": new_name
                }
            with allure.step("отправляем запрос на обновление данных пользователя и сохраняем ответ в переменную response"):
                token = register_user 
                client = Request(Config.URL)
                response = client.patch(path=Path.UPDATE_USER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 401
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.AUTH_ERROR

    def test_unsuccessfull_update_user_email(self, create_fake_user, register_user):
            with allure.step("формирование тела запроса"):
                new_email =  fake_user()["email"]
                payload = {
                    "email": new_email,
                    "name": create_fake_user["name"]
                }
            with allure.step("отправляем запрос на обновление данных пользователя и сохраняем ответ в переменную response"):
                token = register_user 
                client = Request(Config.URL)
                response = client.patch(path=Path.UPDATE_USER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 401
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.AUTH_ERROR
