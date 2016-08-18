package com.aw.rest.service.schedule;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aw.rest.exception.AwrException;
import com.aw.rest.service.IConfigurationService;
import com.aw.rest.service.IConfigurationService.AwrConfig;
import com.aw.rest.service.utility.Utility;

/**
 * @author Rahul Vishwakarma
 * 
 * A Housekeeping job scheduler:
 * 	
 * 
 */
@Service
public class AwrScheduleService implements Job{
	
	private static final Logger log = LoggerFactory.getLogger(AwrScheduleService.class);
	
	private static AtomicInteger counter = new AtomicInteger(0);
	
	@Autowired
	private IConfigurationService configurationService;	


	/**
	 * Initialize the job and schedule it upon server start
	 */
	@PostConstruct
	public void initializeScheduleJob(){
		try {
			if(configurationService.getConfiguration(AwrConfig.APP_JOB_SCHEDULE_SWITCH.get()).equalsIgnoreCase("YES")){
				//Schedule Job
				log.warn("Housekeeping job is initializing");
				scheduleJob();
			}else{
				log.warn("Housekeeping job is configured not to start");
			}
		}catch (SchedulerException e) {
        	log.error(e.getMessage(),e);
		} catch (Exception e) {
        	log.error(e.getMessage(),e);
		}
	}

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext executionContext) throws JobExecutionException {
		try {
			SchedulerContext schedulerContext = null;
	        try {
	        	schedulerContext = executionContext.getScheduler().getContext();
	        } catch (SchedulerException e1) {
	        	log.error(e1.getMessage(),e1);
	        }
	        AwrScheduleService externalInstance= (AwrScheduleService)schedulerContext.get("awrScheduleService");
			//Wait for the previous job to finish
	        if(counter.get() <= 0){
	        	log.warn(counter.incrementAndGet()+".) Schedule Service running");	
	        	externalInstance.executeJobActivity();
	        	log.warn(counter.get()+".)Completed execution job");
				counter.decrementAndGet();
	        } else {
	        	throw new AwrException("Unable to execute the job as a previous job is still processing. Skipping this execution");
	        }
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

	/**
	 * Schedule a job based on a trigger with interval
	 * 
	 * @throws SchedulerException 
	 */
	public void scheduleJob() throws SchedulerException {
    	//Read properties
    	int interval=3600;
		int startDelay = 0;
		try {
			interval = Integer.parseInt(configurationService.getConfiguration(AwrConfig.APP_JOB_SCHEDULE_INTERVAL.get()));
			startDelay = Integer.parseInt(configurationService.getConfiguration(AwrConfig.APP_JOB_SCHEDULE_STARTDELAY.get()));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    	JobKey jk = new JobKey("scheduleHousekeepingServiceJob", "awr.housekeeping");
    	JobDetail job = JobBuilder.newJob(AwrScheduleService.class)
    						.withIdentity(jk)
    						.storeDurably()
    						.build();
    	// Trigger the job to run on the next round minute
    	TriggerKey tk = new TriggerKey("scheduleHousekeepingServiceTrigger", "awr.housekeeping");
		Trigger trigger = TriggerBuilder.newTrigger()
		    				.withIdentity(tk)
		    				.withSchedule(SimpleScheduleBuilder
		    							.simpleSchedule()
		    							.withIntervalInSeconds(interval)
		    							.repeatForever())
		    				.startNow()
		    				.build();
        // Grab the Scheduler instance from the Factory and schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		if(!scheduler.checkExists(jk)){
	    	log.info("------------------------------------------------------------------------------------------");
        	log.warn("Initializing the Housekeeping Scheduler job "+jk.toString()+" with interval: "+interval+" & start delay: "+startDelay );
        	log.info("------------------------------------------------------------------------------------------");
	    	scheduler.getContext().put("awrScheduleService", this);
			scheduler.startDelayed(startDelay);
			scheduler.scheduleJob(job, trigger);
		}else{
			log.warn("Scheduler already initialized for job "+jk.toString());
		}
    }
	
	/**
	 * Scheduled activity to execute
	 * TODO
	 */
	public void executeJobActivity(){
		log.info("TODO");
	}
	 
	/**
	 * Test Main for Quartz
	 * 
	 * @param args
	 * @throws SchedulerException 
	 */
	public static void main(String[] args) throws SchedulerException {	 
		 log.info("Test Quartz Scheduler");
		 AwrScheduleService ss = new AwrScheduleService();
		 ss.scheduleJob();
	 }
}
