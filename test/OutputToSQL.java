public class OutputToSQL {
    public static void main(String[] args) throws OutputException {
        SitemapCreator sitemapCreator = new SitemapCreator("DOMAIN", "localhost",
                "root", "", "DB", "TABLE");
        sitemapCreator.startCrawling();
    }
}
