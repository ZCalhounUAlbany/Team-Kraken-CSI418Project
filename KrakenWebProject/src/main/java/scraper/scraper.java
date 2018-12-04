package scraper;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import servlets.sqlmethods;

import java.util.List;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.SQLException;
import org.jsoup.nodes.Document;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.apache.commons.lang3.StringUtils;

public class scraper{
    public static void main(String[] args)throws SQLException,IOException {
        try {
            String url = "https://www.nasdaq.com/options/";
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)")
                    .referrer("http://www.baidu.com") //proxy
                    .timeout(0)
                    .get();
            Elements div = document.select("div#latest-news-headlines").select(".orange-ordered-list")
                    .select("li");
            //System.out.println(document.select("div#latest-news-headlines").select("h2").text());
            for (Element e : div) {
                String headline = e.select("a").select("b").text();
                if (headline.contains("Notable") && headline.contains("Option") && headline.contains("Activity")) {
                    String symbols = StringUtils.substringAfter(headline, ":");
                    List<String> items = Arrays.asList(symbols.split(","));
                    for (int i = 0; i < items.size(); i++) {
                        String sybmol = (items.get(i).replaceAll("\\s", ""));
                        //System.out.print("headline "+headline+" " + "Symbol "+sybmol + " ");
                        String relHref = e.select("a").first().attr("href");
                        //System.out.println(headline + " "+ relHref);
                        Document document2 = Jsoup.connect(relHref).userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)")
                                .referrer("http://www.baidu.com")
                                .timeout(0)
                                .get();
                        Elements paragraphs = document2.select("div#articleText");
                        for (Element p : paragraphs) {
                            // (\(Symbol: AVGO.*?\:)REGEX
                            Pattern pa = Pattern.compile("(\\(Symbol: " + sybmol + ".*?\\:)");
                            Matcher m = pa.matcher(p.text());
                            while (m.find()) {
                                System.out.println("headline " + headline + " " + "Symbol " + sybmol + " " + "Text " + m.group(1));
                                sqlmethods.insertTable(headline, sybmol, m.group(1)); //sqlexception
                            }
                        }
                    }
                }
            }
        } catch (/**SQL**/Exception e) {  //catch sqlexception
            e.printStackTrace();
        }
    }
}