from fastapi import APIRouter
from pydantic import BaseModel
import logging

logger = logging.getLogger(__name__)

router = APIRouter()


class MessageRequest(BaseModel):

    message: str


class MessageResponse(BaseModel):

    message: str


@router.post("/messages", response_model=MessageResponse)
async def receive(request: MessageRequest) -> MessageResponse:

    logger.info(f"Spring Boot로부터 메시지 수신: {request.message}")
    response_message = f"Received: {request.message}"
    logger.info(f"Spring Boot로 응답 전송: {response_message}")
    
    return MessageResponse(message=response_message)

