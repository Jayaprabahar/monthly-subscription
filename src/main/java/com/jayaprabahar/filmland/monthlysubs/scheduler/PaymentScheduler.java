/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.scheduler;

import java.util.Date;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jayaprabahar.filmland.monthlysubs.beans.SubscribedServicesDO;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : PaymentScheduler.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 27, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Component
public class PaymentScheduler {

	@Autowired
	private Scheduler scheduler;

	/**
	 * @return 
	 * @throws SchedulerException 
	 * 
	 */
	public Date addPaymentSchedule(SubscribedServicesDO subscribedServicesDO) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(PaymentNotificationJob.class).withIdentity(formJobKey(subscribedServicesDO)).build();

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(subscribedServicesDO.getServicesKey().getEmail(), subscribedServicesDO.getServicesKey().getName()).startNow()
				.withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMonths(1)).build();

		return scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * @param subscribedServicesDO
	 * @return
	 * @throws SchedulerException
	 */
	public boolean deletePaymentSchedule(SubscribedServicesDO subscribedServicesDO) throws SchedulerException {
		return scheduler.deleteJob(formJobKey(subscribedServicesDO));
	}

	/**
	 * @param subscribedServicesDO
	 * @return
	 */
	private JobKey formJobKey(SubscribedServicesDO subscribedServicesDO) {
		return new JobKey(subscribedServicesDO.getServicesKey().getEmail() + subscribedServicesDO.getServicesKey().getName(), subscribedServicesDO.getServicesKey().getName());
	}

}
