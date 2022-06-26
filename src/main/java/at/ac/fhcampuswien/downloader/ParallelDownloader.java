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
        int threadCount = Runtime.getRuntime().availableProcessors();     // returns number of available Threads.
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount); // Creates a Thread Pool that reuses a fixed number of Threads.

        List<Callable<String>> callables = new ArrayList<>();  // Create a List that holds Future results.

        for (String url : urls) {
            Callable<String> download = () -> saveUrl2File(url);  // pass asynchronous function as a lambda.
            callables.add(download);  // pool submit returns Future objects -> add all Future objects to array.
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
