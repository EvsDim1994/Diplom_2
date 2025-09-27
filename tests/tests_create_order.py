import allure

from src.config import Config
from src.path import Path
from src.requests.requests import Request


class TestCreateUser:

    def test_successfull_create_user(self, create_fake_user):
            with allure.step("формирование тела запроса"):
                payload = {
                    "email": create_fake_user["email"],
                    "password": create_fake_user["password"],
                    "name": create_fake_user["name"]
                }
            with allure.step("отправляем запрос на регистрацию пользователя и сохраняем ответ в переменную response"): 
                client = Request(Config.URL)
                response = client.post(path=Path.REGISTER_USER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 200
                assert response.json()["accessToken"] is not None
                