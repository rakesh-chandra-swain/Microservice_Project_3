package com.bluepal.ai.services;



import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AiProductServiceImpl implements AiProductService {

    @Value("${gemini.api.key}")
    private static String API_KEY;


    @Override
    public String simpleChat(String prompt) {
        return "";
    }
}
