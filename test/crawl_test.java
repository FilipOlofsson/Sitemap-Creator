import java.util.List;

/*
  Crawls, starting from the page that's sent as a parameter to the crawler.
*/
public class crawl_test {
    public static void main(String[] args) {
        Crawler crawler = new Crawler("https://www.reddit.com/r/leagueoflegends/");
        List<String> = crawler.getLinks();
    }
}
