import java.util.List;

public class SitemapCreator {
    /**
     *
     */
    private OutputHandler handler;
    private Crawler crawler;

    private String domain;

    private OutputMethod method;

    /**
        Constructor if using SQL as output method.
     @param CRAWL_DOMAIN The domain to start the crawler at.
     @param SQL_DATABASE The database name
     @param SQL_PASSWORD The password for the database
     @param SQL_SERVER The database server
     @param SQL_TABLE The database table
     @param SQL_USERNAME The username for the database
     */
    public SitemapCreator(String CRAWL_DOMAIN, String SQL_SERVER, String SQL_USERNAME,
                   String SQL_PASSWORD, String SQL_DATABASE, String SQL_TABLE) {
        this.crawler = new Crawler(CRAWL_DOMAIN);
        this.handler = new OutputHandler(SQL_SERVER, SQL_USERNAME, SQL_PASSWORD, SQL_DATABASE, SQL_TABLE);
        this.method = OutputMethod.SQL;
        this.domain = CRAWL_DOMAIN;
    }

    /**
        Constructor if using TEXT as output method.
        @param CRAWL_DOMAIN The domain to start the crawler at.
        @param FILE_EXTENSION The extension for the output file.
        @param FILE_NAME The name of the file to output to.
     */
    public SitemapCreator(String CRAWL_DOMAIN, String FILE_NAME, String FILE_EXTENSION) {
        this.crawler = new Crawler(CRAWL_DOMAIN);
        this.handler = new OutputHandler(FILE_NAME, FILE_EXTENSION);
        this.method = OutputMethod.TEXT;
        this.domain = CRAWL_DOMAIN;
    }

    /**
        Start the crawling process, also outputs using the method specified in the method variable.
     */
    public void startCrawling() {
        List<String> links = crawler.getLinks();
        for(String link : links) {
            if(method == OutputMethod.TEXT) {
                try {
                    handler.outputToFile(domain, link);
                } catch (OutputException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    handler.outputToDatabase(domain, link);
                } catch (OutputException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
