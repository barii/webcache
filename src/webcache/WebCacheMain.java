package webcache;

public class WebCacheMain {

    public static void main(String[] args) {
        Cache gocardlessCache = new Cache();
        WebParser parser = new WebParser(gocardlessCache, "https://gocardless.com");
        parser.scrape("/");

        for (String s : gocardlessCache.words.get("Overview")) {
            System.out.println(s);
        }
    }

}
