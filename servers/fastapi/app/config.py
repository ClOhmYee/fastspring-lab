from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    
    host: str = "127.0.0.1"
    port: int = 8000
    app_name: str = "FastAPI from fastspring-lab"
    
    class Config:
        env_file = ".env"
        env_file_encoding = "utf-8"


settings = Settings()

