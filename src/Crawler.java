import org.jsoup.HttpStatusException;
import org.jsoup    .Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class Crawler {
    
    private List<String> visited = new LinkedList<>();
    
    private String domain;
    
    Crawler(String domain) {
        this.domain = domain;
    }
    
    /*
        Starts the recursion process to crawl through the website.
     */
    List<String> getLinks() {
        try {
            crawl(this.domain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(new LinkedHashSet<>(visited));
    }
    
    /*
        Recursion method to crawl the website.
     */
    private void crawl(String website) throws IOException {
        try {
            Document site = Jsoup.connect(website).get();
            List<String> toVisit = site.select("a").eachAttr("href");
            visited.add(format(website));
            for(String link : toVisit) {
                String formattedLink = format(link);
                if(!visited.contains(formattedLink) && !formattedLink.equals("")) {
                    visited.add(formattedLink);
                    System.out.println(formattedLink);
                    crawl(formattedLink);
                }
            }
        } catch (HttpStatusException e) {

        }
    }
    
    /*
        Really garbage method to format the link to something more workable.
        Eg. /register.php becomes http://domain.com/register.php
        Don't touch it, it's finally working.
        https://www.simplyrecipes.com/wp-content/uploads/2013/04/baked-spaghetti-method-8-600x400.jpg
     */
    private String format(String link) {
        if(link.startsWith(this.domain)) {
            link = link.replace(this.domain, "");
        }
        if(this.domain.endsWith("/")) this.domain = removeLastChar(this.domain);
        String domain = this.domain.replace("http://", "");
        domain = domain.replace("https://", "");
        domain = domain.replace("www.", "");
        if(!((link.startsWith("https://") || link.startsWith("http://")) && !link.contains(domain)) && !link.contains
                ("mailto") && !link.contains("javascript:")) {
            if(!link.startsWith("/")) link = "/" + link;
            link = this.domain + link;
            return link;
        }
        return "";
    }
    
    /*
        Makes the function above a bit less spaghetti.
     */
    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}