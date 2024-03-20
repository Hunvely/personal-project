package com.rod.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class HomeService {
     private final HomeRepository homeRepository;

    String test() {
        return "test code";
    }
}
