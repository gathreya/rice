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
package org.kuali.rice.ksb.messaging.remotedservices;

import java.io.Serializable;

import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.ksb.messaging.service.KSBJavaService;


/**
 * A service that throws exceptions
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class TestHarnessExplodingQueue implements KSBJavaService {
	
	public static int NUM_CALLS = 0;

	public void invoke(Serializable payLoad) {
		NUM_CALLS++;
		throw new RiceRuntimeException("I'm the exploder!!!  Face the explosion!!!");
	}

}
