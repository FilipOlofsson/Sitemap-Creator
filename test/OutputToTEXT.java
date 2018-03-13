public class OutputToTEXT {
    public static void main(String[] args) throws OutputException {
        SitemapCreator sitemapCreator = new SitemapCreator("DOMAIN", "output", "txt");
        sitemapCreator.startCrawling();
    }
}
