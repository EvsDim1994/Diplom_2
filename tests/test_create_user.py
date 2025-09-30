import allure

from src.errors import Errors
from src.path import Path


class TestCreateUser:

    def test_successfull_create_user(self, create_fake_user, client):
            with allure.step("формирование тела запроса"):
                payload = {
                    "email": create_fake_user["email"],
                    "password": create_fake_user["password"],
                    "name": create_fake_user["name"]
                }
            with allure.step("отправляем запрос на регистрацию пользователя и сохраняем ответ в переменную response"): 
                response = client.post(path=Path.REGISTER_USER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 200
                assert response.json()["accessToken"] is not None

    def test_unsuccessfull_create_user_with_the_same_data(self, create_fake_user, client):
            with allure.step("формирование тела запроса"):
                payload = {
                    "email": create_fake_user["email"],
                    "password": create_fake_user["password"],
                    "name": create_fake_user["name"]
                }
            with allure.step("отправляем запрос на регистрацию пользователя"): 
                client.post(path=Path.REGISTER_USER, json=payload)
            with allure.step("отправляем повторный запрос на регистрацию пользователя и сохраняем ответ в переменную repeat_response"): 
                repeat_response = client.post(path=Path.REGISTER_USER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert repeat_response.status_code == 403
                assert repeat_response.json()["success"] == False
                assert repeat_response.json()["message"] == Errors.USER_EXIST_ERROR

    def test_unsuccessfull_create_user_without_email(self, create_fake_user, client):
            with allure.step("формирование тела запроса"):
                payload = {
                    "password": create_fake_user["password"],
                    "name": create_fake_user["name"]
                }
            with allure.step("отправляем запрос на регистрацию пользователя и сохраняем ответ в переменную response"): 
                response = client.post(path=Path.REGISTER_USER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 403
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.USER_DATA_ERROR

    def test_unsuccessfull_create_user_without_name(self, create_fake_user, client):
            with allure.step("формирование тела запроса"):
                payload = {
                    "email": create_fake_user["email"],
                    "password": create_fake_user["password"]
                }
            with allure.step("отправляем запрос на регистрацию пользователя и сохраняем ответ в переменную response"): 
                response = client.post(path=Path.REGISTER_USER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 403
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.USER_DATA_ERROR

    def test_unsuccessfull_create_user_without_password(self, create_fake_user, client):
            with allure.step("формирование тела запроса"):
                payload = {
                    "email": create_fake_user["email"],
                    "name": create_fake_user["name"]
                }
            with allure.step("отправляем запрос на регистрацию пользователя и сохраняем ответ в переменную response"): 
                response = client.post(path=Path.REGISTER_USER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 403
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.USER_DATA_ERROR
