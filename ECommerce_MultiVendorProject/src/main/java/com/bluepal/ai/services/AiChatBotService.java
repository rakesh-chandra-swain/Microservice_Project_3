package com.bluepal.ai.services;

import com.bluepal.exception.ProductException;
import com.bluepal.response.ApiResponse;

public interface AiChatBotService {

    ApiResponse aiChatBot(String prompt,Long productId,Long userId) throws ProductException;
}
