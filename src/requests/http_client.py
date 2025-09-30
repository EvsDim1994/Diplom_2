
import requests


class HttpClient:
    def __init__(self, base_url):
        self.base_url = base_url.rstrip('/')

    def request(self, method, path, **kwargs):
        url = f"{self.base_url}/{path.lstrip('/')}"
        response = requests.request(method, url, **kwargs)
        return response
    