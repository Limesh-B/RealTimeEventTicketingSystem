package lk.limesh.ticketingapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ThreadManagementService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadManagementService.class);

    private Thread workerThread; // Thread instance to manage

    public synchronized void startThread() {
        if (workerThread != null && workerThread.isAlive()) {
            logger.warn("Thread is already running!");
            return;
        }

        workerThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    logger.info("Thread is running...");
                    Thread.sleep(1000); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                logger.info("Thread was interrupted!");
            }
        });
        workerThread.start();
        logger.info("Thread started successfully.");
    }

    public synchronized void stopThread() {
        if (workerThread == null || !workerThread.isAlive()) {
            logger.warn("No thread is running to stop.");
            return;
        }
        workerThread.interrupt();
        logger.info("Thread has been stopped.");
    }

    public synchronized boolean isThreadRunning() {
        return workerThread != null && workerThread.isAlive();
    }
}
