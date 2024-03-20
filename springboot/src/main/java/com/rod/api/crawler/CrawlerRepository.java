package com.rod.api.crawler;

import com.rod.api.common.AbstractRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CrawlerRepository extends AbstractRepository {

    private static CrawlerRepository instance = new CrawlerRepository();
    private Map<String, Iterator<Element>> map;
    Map<String, Iterator<Element>> music;

    private CrawlerRepository() {
        map = new HashMap<String, Iterator<Element>>();
        music = new HashMap<String, Iterator<Element>>();
    }

    public static CrawlerRepository getInstance() { // 정적 생성 메서드
        return instance;
    }


    @Override
    public Map<String, ?> save(Map<String, ?> paramMap) throws IOException {
        Document doc = Jsoup.connect("https://music.bugs.co.kr/chart").timeout(10 * 1000).get();
        Elements elems = doc.select("table.byChart");
        Iterator<Element> title = elems.select("p.title").iterator();
        Iterator<Element> artist = elems.select("p.artist").iterator();
        Iterator<Element> rank = elems.select("strong").iterator();

        map = music;
        map.put("title", title);
        map.put("artist", artist);
        map.put("rank", rank);

        return map;
    }

    @Override
    public Map<String, ?> save2(Map<String, ?> paramMap) throws IOException {
        Document doc = Jsoup.connect("https://www.melon.com/chart/index.htm").timeout(10 * 1000).get();
        Elements elems = doc.select("tbody");
        Iterator<Element> title = elems.select("div.ellipsis.rank01 > span").iterator();
        Iterator<Element> artist = elems.select("div.ellipsis.rank02 span").iterator();
        Iterator<Element> rank = elems.select("td span.rank").iterator();

        map = music;
        map.put("title", title);
        map.put("artist", artist);
        map.put("rank", rank);
        return map;
    }
}
