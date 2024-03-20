package com.rod.api.crawler;

import java.io.IOException;
import java.util.Map;

public class CrawlerServiceImpl implements CrawlerService {

    private static CrawlerServiceImpl instance = new CrawlerServiceImpl();

    private CrawlerRepository crawlerRepo;

    private CrawlerServiceImpl() {
        this.crawlerRepo = CrawlerRepository.getInstance();
    }

    public static CrawlerServiceImpl getInstance() { // 정적 생성 메서드
        return instance;
    }

    @Override
    public Map<String, ?> fineNameFromBugs(Map<String, String> paramMap) throws IOException {

        return crawlerRepo.save(paramMap);

    }

    @Override
    public Map<String, ?> fineNameFromMelon(Map<String, String> paramMap) throws IOException {
        return crawlerRepo.save(paramMap);
    }
}
