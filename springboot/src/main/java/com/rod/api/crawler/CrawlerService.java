package com.rod.api.crawler;

import java.io.IOException;
import java.util.Map;

public interface CrawlerService {

    Map<String, ?> fineNameFromBugs(Map<String, String> paramMap) throws IOException;
    Map<String, ?> fineNameFromMelon(Map<String, String> paramMap) throws IOException;
}
