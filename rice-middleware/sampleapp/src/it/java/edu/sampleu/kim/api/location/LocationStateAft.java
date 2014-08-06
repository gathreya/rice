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
package edu.sampleu.kim.api.location;

import edu.sampleu.admin.AdminTmplMthdAftNavBlanketAppBase;
import org.junit.Test;
import org.kuali.rice.testtools.selenium.AutomatedFunctionalTestUtils;
import org.kuali.rice.testtools.selenium.WebDriverUtils;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class LocationStateAft extends AdminTmplMthdAftNavBlanketAppBase {

    /**
     * ITUtil.PORTAL + "?channelTitle=State&channelUrl=" 
     * + WebDriverUtils.getBaseUrlString() + ITUtil.KNS_LOOKUP_METHOD + "org.kuali.rice.location.impl.state.StateBo&docFormKey=88888888&returnLocation=" +
     * ITUtil.PORTAL_URL + ITUtil.HIDE_RETURN_LINK;
     */
    public static final String BOOKMARK_URL = AutomatedFunctionalTestUtils.PORTAL + "?channelTitle=State&channelUrl="
            + WebDriverUtils.getBaseUrlString() + AutomatedFunctionalTestUtils.KNS_LOOKUP_METHOD +
            "org.kuali.rice.location.impl.state.StateBo&docFormKey=88888888&returnLocation=" +
            AutomatedFunctionalTestUtils.PORTAL_URL + AutomatedFunctionalTestUtils.HIDE_RETURN_LINK + AutomatedFunctionalTestUtils.SHOW_MAINTENANCE_LINKS;

    @Override
    protected String getBookmarkUrl() {
        return BOOKMARK_URL;
    }

    /**
     * {@inheritDoc}
     * State
     * @return
     */
    @Override
    protected String getLinkLocator() {
        return "State";
    }

    protected void createNewEnterDetails() throws InterruptedException {
        inputDetails();

        jiraAwareTypeByName("document.newMaintainableObject.countryCode", "US");
    }

    private void inputDetails() throws InterruptedException {
        waitAndTypeByName("document.documentHeader.documentDescription", getDescriptionUnique());
        jiraAwareTypeByName("document.newMaintainableObject.name", "name" + uniqueString);
        jiraAwareTypeByName("document.newMaintainableObject.code", uniqueString.substring(5, 7));
    }

    @Override
    protected void createNewLookupDetails() throws InterruptedException {
        inputDetails();

        String countryLookUp = "//input[@name='methodToCall.performLookup.(!!org.kuali.rice.location.impl.country.CountryBo!!).(((code:document.newMaintainableObject.countryCode,))).((`document.newMaintainableObject.countryCode:code,`)).((<>)).(([])).((**)).((^^)).((&&)).((//)).((~~)).(::::;"
                + getBaseUrlString() + "/kr/lookup.do;::::).anchor4']";
        waitAndClickByXpath(countryLookUp);
        waitAndClickSearch();
        waitAndClickReturnValue();
    }

    @Test
    public void testEditCancelBookmark() throws Exception {
        testEditCancel();
        passed();
    }

    @Test
    public void testEditCancelNav() throws Exception {
        testEditCancel();
        passed();
    }
}