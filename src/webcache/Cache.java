package webcache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Cache {
    Set<String> urlHistory = new HashSet<>();
    Map<String, List<String>> words = new HashMap<>();
    Queue<String> urlQueue = new LinkedList<>();
    
    public Set<String> getUrlHistory() {
        return urlHistory;
    }  
    
    public Queue<String> getUrlQueue() {
        return urlQueue;
    }
    
    public List<String> search(String word) {
        return words.get(word);
    }
    
    synchronized void store(String[] wordList, String url) {
        for(String word : wordList) {
            if (!words.containsKey(word)) words.put(word, new LinkedList<>());
            if (!words.get(word).contains(url)) words.get(word).add(url);
        }
    }
    
    synchronized void addUrl(String url) {
        if(!urlHistory.contains(url)) {
            System.out.println("add:"+ url);
            urlHistory.add(url);
            urlQueue.add(url);
        }
    }
    
    synchronized List<String> getUrls(String word) {
        return words.get(word);
    }

    synchronized String getNextUrl() {
        String next = urlQueue.peek();
        if (next!= null ) {
        System.out.println("out: " + next);
        urlQueue.remove();
        }
        return next;
    }


}
