from datetime import datetime
from typing import List, Optional
from pydantic import BaseModel

class Order(BaseModel):
    _id: str
    ingredients: List[str]  # список ID ингредиентов
    status: str  # "done", "pending", "created" и т.д.
    name: str  # название бургера
    createdAt: datetime  # ISO формат даты
    updatedAt: datetime  # ISO формат даты
    number: int  # номер заказа

# Основная модель для всего ответа
class OrdersApiResponse(BaseModel):
    success: bool
    orders: List[Order]  # список заказов
    total: int  # общее количество заказов
    totalToday: int  # количество заказов за сегодня
    
    