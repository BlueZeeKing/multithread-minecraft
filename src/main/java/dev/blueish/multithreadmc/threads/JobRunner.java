package dev.blueish.multithreadmc.threads;

import dev.blueish.multithreadmc.MultithreadMC;
import java.util.concurrent.LinkedBlockingQueue;

public class JobRunner extends Thread {
    private LinkedBlockingQueue<Job> jobs;

    public JobRunner(LinkedBlockingQueue<Job> jobs) {
        this.jobs = jobs;
    }

    public void run() {
        try {
            while (true) {
                MultithreadMC.LOGGER.info(jobs.take().info);
            }
        }
        catch (Exception e) {
            MultithreadMC.LOGGER.error("One of the threads has encountered an error and is shutting down");
            System.out.println(e);
        }
    }
}
