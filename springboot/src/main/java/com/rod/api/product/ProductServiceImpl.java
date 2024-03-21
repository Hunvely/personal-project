package com.rod.api.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl {

    private ProductRepository productRepo;
}
