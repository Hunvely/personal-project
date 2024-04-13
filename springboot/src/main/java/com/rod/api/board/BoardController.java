package com.rod.api.board;

import com.rod.api.board.model.BoardDto;
import com.rod.api.board.service.BoardServiceImpl;
import com.rod.api.common.component.Messenger;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
@Slf4j
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
public class BoardController {

    private final BoardServiceImpl boardService;

    @PostMapping(path = "/save")
    public ResponseEntity<Messenger> save(@RequestBody BoardDto boardDto) {
        log.info("Received request to save board: {}", boardDto);

        return ResponseEntity.ok(boardService.save(boardDto));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Messenger> deleteById(@RequestBody Long id) {
        log.info("Received request to delete Board with ID: {}", id);

        return ResponseEntity.ok(boardService.deleteById(id));
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<BoardDto>> findAll() throws SQLException {
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping(path = "/search/id")
    public ResponseEntity<BoardDto> findById(@RequestParam Long id) {
        log.info("Received request to find Board with id: {}", id);
        Optional<BoardDto> optionalBoardDto = boardService.findById(id);

        return optionalBoardDto.map(i -> ResponseEntity.ok(i)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(boardService.count());
    }
}
