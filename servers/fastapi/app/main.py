import logging
from fastapi import FastAPI, Request, status
from fastapi.responses import JSONResponse
from fastapi.exceptions import RequestValidationError
from app.config import settings
from app.routers import receive
import uvicorn

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S"
)
logger = logging.getLogger(__name__)

app = FastAPI(
    title=settings.app_name,
    description="FastAPI server for communication with Spring Boot",
    version="1.0.0"
)


# Global exception handlers
@app.exception_handler(RequestValidationError)
async def validation_exception_handler(request: Request, exc: RequestValidationError):
    logger.warning(f"Validation error on {request.url.path}: {exc.errors()}")
    return JSONResponse(
        status_code=status.HTTP_400_BAD_REQUEST,
        content={
            "error": "Validation Error",
            "message": "Request validation failed",
            "status": "error",
            "details": str(exc.errors())
        }
    )


@app.exception_handler(Exception)
async def general_exception_handler(request: Request, exc: Exception):
    logger.error(f"Unhandled exception on {request.url.path}: {str(exc)}", exc_info=True)
    return JSONResponse(
        status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
        content={
            "error": "Internal Server Error",
            "message": "An unexpected error occurred",
            "status": "error"
        }
    )


# Register routers
app.include_router(receive.router, prefix="/api", tags=["receive"])


@app.get("/")
async def root():
    return {"message": "FastAPI Server is running"}


@app.get("/health")
async def health():
    return {"status": "ok"}


if __name__ == "__main__":
    logger.info(f"Starting FastAPI server on {settings.host}:{settings.port}")
    uvicorn.run(app, host=settings.host, port=settings.port)
