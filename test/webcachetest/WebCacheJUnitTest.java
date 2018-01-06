package webcachetest;


import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import webcache.Cache;
import webcache.WebParser;

public class WebCacheJUnitTest {
    Cache gocardlessCache;
    WebParser parser;
    
    public WebCacheJUnitTest() {
        gocardlessCache = new Cache();
        parser = new WebParser(gocardlessCache, "https://gocardless.com");
    }
    
    @Before
    public void setUp() {
        parser.scrape("/");
    }
    
    @Test
    public void testAdd(){
        assertTrue(gocardlessCache.search("Overview").contains("/faq/merchants/"));

        assertTrue(gocardlessCache.search("individuals").contains("/stories/"));

        assertTrue(gocardlessCache.search("increasingly").contains("/guides/posts/add-on-solutions-for-accountants/"));

        assertTrue(gocardlessCache.search("multinational").contains("/about/"));
                        
   }
}