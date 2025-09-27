# Diplom_2
API учебного сервиса  Stellar Burgers(https://stellarburgers.nomoreparties.site/) . Его [документация](https://code.s3.yandex.net/qa-automation-engineer/python-full/diploma/api-documentation.pdf?etag=3403196b527ca03259bfd0cb41163a89)

## Запуск тестов

Для запуска всех тестов с подробным выводом используйте команду:

```bash
pytest -v  --alluredir=allure_results 
```

Для установки необходимых библиотек используете команду: 

```bash
pip3 install -r requirements.txt
```

Для запуска allure отчета: 

```bash
allure serve allure_results
```