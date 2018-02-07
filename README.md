# Sitemap-Creator

## USAGE

```java
Crawler crawler = new Crawler("https://www.reddit.com/");
List<String> list = crawler.getLinks();     // Crawler.getLinks() returns a List containing all the crawled pages.
```

## TODO

* Robots.txt?
* Output to SQL, FTP, TEXT
* It is currently unable to start the crawling in the directory of a webpage.
