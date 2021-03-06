/**
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.ksb.quartz;

import org.junit.Test;
import org.kuali.rice.ksb.service.KSBServiceLocator;
import org.kuali.rice.ksb.test.KSBTestCase;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Test basic sanity check of quartz implementation
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 * 
 */
public class QuartzTest extends KSBTestCase {

    @Test
    public void testSchedulingJob() throws Exception {
	Scheduler scheduler = KSBServiceLocator.getScheduler();
	JobDataMap datMap = new JobDataMap();
	datMap.put("yo", "yo");
	JobDetailImpl jobDetail = new JobDetailImpl("myJob", null, TestJob.class);
	jobDetail.setJobDataMap(datMap);

    TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger();
    triggerBuilder.startAt(new Date());
    triggerBuilder.withIdentity("i'm a trigger puller");
    triggerBuilder.usingJobData(datMap);
    triggerBuilder.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(1).withIntervalInMilliseconds(1L));

    Trigger trigger = triggerBuilder.build();

	scheduler.scheduleJob(jobDetail, trigger);
	
	
	synchronized (TestJob.LOCK) {
	    TestJob.LOCK.wait(30 * 1000);    
	}
	
	assertTrue("job never fired", TestJob.EXECUTED);
    }

}
