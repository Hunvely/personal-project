package com.rod.api.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private BoardServiceImpl boardService;
}
