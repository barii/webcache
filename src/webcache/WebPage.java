package webcache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class WebPage {
    private final String url;
    private final String root;
    private final String html;

    WebPage(String root, String path) throws PageCantBeParsedException {
        this.root = root;
        this.url = root + path;
        this.html=getHTML();
    }


    public String getHTML() throws PageCantBeParsedException {
        URL urlObj;
        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            throw new PageCantBeParsedException("The url was malformed!");
        }
        URLConnection urlCon;
        BufferedReader in;
        StringBuilder outputText = new StringBuilder();
        try {
            urlCon = urlObj.openConnection();
            in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                outputText.append(line);
            }
            in.close();
        } catch (IOException e) {
            throw new PageCantBeParsedException("There was an error connecting to the URL");
        }
        return outputText.toString();
    }

    public List<String> getLinks() {
        List<String> subpages = new ArrayList<>();

        for (Element link : Jsoup.parse(html).select("a[href]")) {
                subpages.add(link.attr("href").replace(root, ""));
        }

        return subpages;
    }

    String getText() {
        return Jsoup.parse(html).text();
    }

}
