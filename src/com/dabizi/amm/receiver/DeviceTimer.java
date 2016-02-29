package com.dabizi.amm.receiver;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Jephirus
 *
 */
public class DeviceTimer {

	@Scheduled(cron = "0 0 03 ? * *")  // 每在凌晨3点定时取HR中间库数据。
	// @Scheduled(cron = "0 49 09 ? * *")
	public void readFromMeddleDB() {
		
	}

}
