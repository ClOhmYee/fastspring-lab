import pytest
from fastapi.testclient import TestClient
from app.main import app


class TestMain:
    
    # initialize the test client
    def setup_method(self):
        self.client = TestClient(app)

    # request GET root endpoint and verify sucessful response
    def test_root_endpoint(self):
        response = self.client.get("/")

        assert response.status_code == 200
        assert response.json() == {"message": "FastAPI Server is running"}

    # request GET "/health" endpoint and verify the expected response
    def test_health_endpoint(self):
        response = self.client.get("/health")

        assert response.status_code == 200
        assert response.json() == {"status": "ok"}

    # request POST "/api/receive" endpoint with the data and verify the expected response
    def test_receive_endpoint(self):
        request_data = {"message": "hello"}
        response = self.client.post("/api/receive", json=request_data)

        assert response.status_code == 200
        assert response.json() == {
            "message": "Received: hello"
        }

    # request POST "/api/receive" endpoint with the invalid request and verify the validation error
    def test_receive_endpoint_validation_error(self):
        request_data = {}
        response = self.client.post("/api/receive", json=request_data)
        
        assert response.status_code == 400
        assert response.json()["status"] == "error"
    
    # request POST "/api/receive" endpoint with non-str type message
    def test_receive_endpoint_invalid_type(self):
        request_data = {"message": 123}
        response = self.client.post("/api/receive", json=request_data)
        
        assert response.status_code == 400
        assert response.json()["status"] == "error"
