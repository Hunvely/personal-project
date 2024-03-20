package com.rod.api.crawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CrawlerController {

    private CrawlerServiceImpl servie = CrawlerServiceImpl.getInstance();

    public Map<String, ?> findBugsMusic(Scanner input) throws IOException {

        System.out.println("크롤링 할 주소를 입력하세요.");
        String url = input.next();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("URL", url);
        return servie.fineNameFromBugs(paramMap);
    }

    public Map<String, ?> findMelonMusic(Scanner input) throws IOException {

        System.out.println("크롤링 할 주소를 입력하세요.");
        String url = input.next();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("URL", url);
        return servie.fineNameFromMelon(paramMap);
    }
}
