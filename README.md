# Sitemap-Creator

## USAGE

#### To output using TEXT
```java
SitemapCreator sitemapCreator = new SitemapCreator("DOMAIN", "output", "txt");
sitemapCreator.startCrawling();
```


#### To output using SQL
```java
SitemapCreator sitemapCreator = new SitemapCreator("DOMAIN", "localhost", "root", "", "DB", "TABLE");
sitemapCreator.startCrawling();
```
