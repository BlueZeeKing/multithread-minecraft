package dev.blueish.multithreadmc;

import dev.blueish.multithreadmc.threads.Job;
import dev.blueish.multithreadmc.threads.JobRunner;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class MultithreadMC implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("multithreadMC");
	public Thread[] threads;
	private final int numThreads = 10;
	public LinkedBlockingQueue<Job> jobs;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		threads = new Thread[numThreads];
		jobs = new LinkedBlockingQueue<Job>();

		for (int i = 0; i < numThreads; i++) {
			threads[i] = new JobRunner(jobs);
			threads[i].start();
		}

		try {
			this.jobs.put(new Job("test"));
		} catch (Exception e) {
			LOGGER.error("failed to add job");
		}

		LOGGER.info("Initialized Multithread Minecraft");
	}
}
