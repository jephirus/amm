/**
 * 
 */
package com.dabizi.amm.receiver;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Jephirus
 *
 */
public class QueryJob extends QuartzJobBean{
	
	Logger log = Logger.getLogger(getClass());
	
	public void query(){
		log.info(" log "+new Date());
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		query();
	}
}