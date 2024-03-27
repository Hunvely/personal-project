package com.rod.api.article;

import com.rod.api.common.AbstractService;
import com.rod.api.enums.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl  implements ArticleService {

    private final ArticleRepository articleRepo;


    @Override
    public List<?> findAll() {
        return articleRepo.findAll();
    }
}
