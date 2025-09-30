import allure

from src.errors import Errors
from src.path import Path

class TestCreateOrder:

    def test_successfull_create_order(self, client, register_user):
            with allure.step("Получение списка ингредиентов"):
                ingredient_response = client.get(path=Path.GET_INGREDIENTS)
                ingredient_first = ingredient_response.json()["data"][0]["_id"]
                ingredient_second = ingredient_response.json()["data"][1]["_id"]
            with allure.step("формирование тела запроса"):
                payload = {
                    "ingredients": [ingredient_first, ingredient_second]
                }
            with allure.step("отправляем запрос на создание заказа и сохраняем ответ в переменную response"): 
                token = register_user 
                response = client.post(path=Path.CREATE_ORDER, json=payload, auth_token=token)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 200
                assert response.json()["order"]["number"] > 0

    def test_unsuccessfull_create_order_without_auth(self, client):
            with allure.step("Получение списка ингредиентов"):
                ingredient_response = client.get(path=Path.GET_INGREDIENTS)
                ingredient_first = ingredient_response.json()["data"][0]["_id"]
                ingredient_second = ingredient_response.json()["data"][1]["_id"]
            with allure.step("формирование тела запроса"):
                payload = {
                    "ingredients": [ingredient_first, ingredient_second]
                }
            with allure.step("отправляем запрос на создание заказа и сохраняем ответ в переменную response"): 
                response = client.post(path=Path.CREATE_ORDER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 401
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.AUTH_ERROR

    def test_unsuccessfull_create_order_without_ingredient(self, client):
            with allure.step("формирование тела запроса"):
                payload = {
                    "ingredients": []
                }
            with allure.step("отправляем запрос на создание заказа и сохраняем ответ в переменную response"): 
                response = client.post(path=Path.CREATE_ORDER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 400
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.INGREDIENT_ERROR

    def test_unsuccessfull_create_order_with_incorrect_ingredient(self, client):
            with allure.step("формирование тела запроса"):
                payload = {
                    "ingredients": ["0"]
                }
            with allure.step("отправляем запрос на создание заказа и сохраняем ответ в переменную response"): 
                response = client.post(path=Path.CREATE_ORDER, json=payload)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 500
                