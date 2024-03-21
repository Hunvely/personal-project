package com.rod.api.account;

import com.rod.api.enums.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@RestController
public class AccountController {

    private AccountServiceImpl accountService;
}
