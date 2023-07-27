package com.example.foodrecipeapp.Listener;

import com.example.foodrecipeapp.Models.InstructionResponse;

import java.util.List;

public interface InstructionResponseListner {
    void didFatch(List<InstructionResponse> response, String message);
    void didError (String message);
}
