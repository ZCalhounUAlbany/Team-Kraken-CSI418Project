import java.util.List;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class WebScraper {
	public static void main(String args[]) {
		UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
		Elements elements = null;
		try {
			userAgent.visit("https://www.nasdaq.com/topic/options");
			elements = userAgent.doc.findEach("<section class=\"article-category-section\">");
		} catch(JauntException error) {
			System.out.println("error occured" + error);
		}
		for(Element e: elements) {
			List<String> list = e.findAttributeValues("<a href>");
			for(String string: list) {
				if(string.contains("https://www.nasdaq.com/article/")) {
					try {
						userAgent.visit(string);
						Element paragraph = userAgent.doc.findFirst("<div id=\"articleText\">");
						String content = paragraph.findFirst("<p>").getTextContent().replaceAll(" +", " ").
								replaceAll("\\( ", "\\(").replaceAll(" \\)","\\)").replaceAll(" ,", ",");
						System.out.println(content);
						Element date = userAgent.doc.findFirst("<span itemprop=\"datePublished\">");
						System.out.println(date.getChildText());
					} catch (JauntException error) {
						System.out.println("error occured" + error);
					}
					System.out.println(string);
				} else if(string.contains("https://www.nasdaq.com/symbol/")) {
					System.out.println("Symbol: " + string.replace("https://www.nasdaq.com/symbol/", ""));
				}
			}
		}
	}
}
