package webcache;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

class WebParserThread implements Runnable {

    private final Cache cache;
    private final String root;
    private final String path;


    WebParserThread(String root, String path, Cache cache) {
        this.cache = cache;
        this.root = root;
        this.path = path;
    }


    @Override
    public void run() {

        try {
            WebPage webPage = new WebPage(root, path);

            cache.store(webPage.getText().replaceAll("[^a-zA-Z0-9]", " ").split(" "), path);
            
            for (String link : webPage.getLinks()) {
                if (link.startsWith("/") && !link.startsWith("/cdn-cgi/")) {
                    cache.addUrl(link.replace(root, ""));
                }
            }

        } catch (PageCantBeParsedException e) {
            System.out.println(e.toString() + ": " + path);
        }
    }
    
}
