package webcache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WebParser {

    private final Cache cache;
    private final String root;

    public WebParser(Cache cache, String root) {
        this.root = root;
        this.cache = cache;
    }

    public void scrape(String path) {
        cache.addUrl(path);
        ExecutorService pool = Executors.newFixedThreadPool(8);//creating a pool of 8 threads  
        while (((ThreadPoolExecutor)pool).getCompletedTaskCount()!=cache.getUrlHistory().size() || !cache.getUrlQueue().isEmpty()) {
            path = cache.getNextUrl();
            if (path!=null) {
                Runnable worker = new WebParserThread(root, path, cache);  
                pool.execute(worker);//calling execute method of ExecutorService  
            }
        }  
        pool.shutdown();  
        while (!pool.isTerminated()) {   }  


    }


}
