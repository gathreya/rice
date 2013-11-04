/**
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.rice.testtools.common;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class JiraAwareFailureUtilsTest implements JiraAwareFailable {

    String message;

    @Ignore // TODO fix with mvn
    @Test
    public void testJiraAwareFailureRegex() {
        JiraAwareFailureUtils.regexJiraMatches.put("TEST.*NullPointerException.*TEST", "KULRICE-TEST");
        JiraAwareFailureUtils.failOnMatchedJira("TEST NullPointerException InputField.performFinalize TEST", this);
        Assert.assertTrue(message.contains("KULRICE-TEST"));
    }

    @Ignore // TODO fix with mvn
    @Test
    public void testJiraAwareFailureContains() {
        JiraAwareFailureUtils.jiraMatches.put("TEST\u0020IndexOutOfBoundsExceptionException\u0020TEST", "KULRICE-TEST2");
        JiraAwareFailureUtils.failOnMatchedJira("TEST IndexOutOfBoundsExceptionException TEST", this);
        Assert.assertTrue(message.contains("KULRICE-TEST2"));
    }

    @Override
    public void fail(String message) {
        this.message = message;
    }

    @Override
    public void jiraAwareFail(String message) {
        this.message = message;
    }

    @Override
    public void jiraAwareFail(String contents, String message) {
        this.message = message;
    }

    @Override
    public void jiraAwareFail(String contents, String message, Throwable throwable) {
        this.message = message;
    }
}