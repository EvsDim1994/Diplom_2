import allure

from src.errors import Errors
from src.helpers.validatate_json import assertion_validate_structure
from src.models.order import OrdersApiResponse
from src.path import Path


class TestGetOrderByUser:

    def test_successfull_get_order(self, client, register_user):
            with allure.step("Получение списка ингредиентов"):
                ingredient_response = client.get(path=Path.GET_INGREDIENTS)
                ingredient_first = ingredient_response.json()["data"][0]["_id"]
                ingredient_second = ingredient_response.json()["data"][1]["_id"]
            with allure.step("формирование тела запроса"):
                payload = {
                    "ingredients": [ingredient_first, ingredient_second]
                }
            with allure.step("отправляем запрос на создание заказа"): 
                token = register_user 
                client.post(path=Path.CREATE_ORDER, json=payload, auth_token=token)
            with allure.step("отправляем запрос на получение заказа"):
                response = client.get(path=Path.CREATE_ORDER, auth_token=token)
                assert response.status_code == 200
                assertion_validate_structure(response.json(), OrdersApiResponse)

    def test_unsuccessfull_get_order(self, client):
            with allure.step("отправляем запрос на получение заказа"):
                response = client.get(path=Path.CREATE_ORDER)
            with allure.step("проверка кода и тела ответа"): 
                assert response.status_code == 401
                assert response.json()["success"] == False
                assert response.json()["message"] == Errors.AUTH_ERROR
