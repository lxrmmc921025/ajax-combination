package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CombinationService {
    List<String> letterCombinations(String digits, int pagenum, int size);
}
