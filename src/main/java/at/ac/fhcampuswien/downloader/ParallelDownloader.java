package at.ac.fhcampuswien.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader {

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) {
        // TODO implement download function using multiple threads
        // Hint: use ExecutorService with Callables
       /*
          https://www.baeldung.com/java-runnable-callable
         */

        int amountOfDownloads = 0;
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        List<Callable<String>> callables = new ArrayList<>();

        for (String url : urls) {
            Callable<String> download = () -> saveUrl2File(url);
            callables.add(download);
        }

        try {
            List<Future<String>> totalFutureDownloads = threadPool.invokeAll(callables);
            for (Future<String> future : totalFutureDownloads) {
                if (future.get() != null) {
                    amountOfDownloads++;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        threadPool.shutdown();
        return amountOfDownloads;
    }
}
