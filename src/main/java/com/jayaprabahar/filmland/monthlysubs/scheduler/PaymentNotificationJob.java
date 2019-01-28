/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : PaymentNotificationJob.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 27, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Slf4j
public class PaymentNotificationJob extends QuartzJobBean {
	/*
	 * (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.
	 * JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("PaymentNotificationJob is executed");
		sendPaymentNotification();
	}

	/**
	 * 
	 */
	private void sendPaymentNotification() {
		// TODO Auto-generated method stub

	}
}
