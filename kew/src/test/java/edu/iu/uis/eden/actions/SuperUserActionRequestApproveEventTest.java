/*
 * Copyright 2005-2007 The Kuali Foundation.
 *
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.iu.uis.eden.actions;

import java.util.List;

import org.junit.Test;
import org.kuali.workflow.test.KEWTestCase;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.actionrequests.ActionRequestValue;
import edu.iu.uis.eden.actions.BlanketApproveTest.NotifySetup;
import edu.iu.uis.eden.clientapp.WorkflowDocument;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.routeheader.DocumentRouteHeaderValue;
import edu.iu.uis.eden.user.AuthenticationUserId;
import edu.iu.uis.eden.user.WorkflowUser;

/**
 * Tests the super user actions available on the API.
 */
public class SuperUserActionRequestApproveEventTest extends KEWTestCase {

    protected void loadTestData() throws Exception {
        loadXmlFile("ActionsConfig.xml");
    }

    @Test public void testSuperUserFyi() throws Exception {
        WorkflowDocument document = new WorkflowDocument(new NetworkIdVO("ewestfal"), NotifySetup.DOCUMENT_TYPE_NAME);
        document.appSpecificRouteDocumentToUser(EdenConstants.ACTION_REQUEST_FYI_REQ, "", new NetworkIdVO("rkirkend"), "", true);
        document.appSpecificRouteDocumentToUser(EdenConstants.ACTION_REQUEST_APPROVE_REQ, "", new NetworkIdVO("jhopf"), "", true);
        document.routeDocument("");

        document = new WorkflowDocument(new NetworkIdVO("rkirkend"), document.getRouteHeaderId());
        assertTrue("rkirkend should have an FYI request.", document.isFYIRequested());

        WorkflowUser rkirkend = KEWServiceLocator.getUserService().getWorkflowUser(new AuthenticationUserId("rkirkend"));
        List<ActionRequestValue> actionRequests = KEWServiceLocator.getActionRequestService().findAllValidRequests(rkirkend, document.getRouteHeaderId(), EdenConstants.ACTION_REQUEST_FYI_REQ);
        assertEquals("There should only be 1 fyi request to rkirkend.", 1, actionRequests.size());
        document = new WorkflowDocument(new NetworkIdVO("rkirkend"), document.getRouteHeaderId());
        document.superUserActionRequestApprove(actionRequests.get(0).getActionRequestId(), "");

        // FYI should no longer be requested
        document = new WorkflowDocument(new NetworkIdVO("rkirkend"), document.getRouteHeaderId());
        assertFalse("rkirkend should no longer have an FYI request.", document.isFYIRequested());

        // doc should still be enroute
        assertTrue("Document should still be ENROUTE", document.stateIsEnroute());

    }

    @Test public void testSuperUserFyiOnFinal() throws Exception {
        WorkflowDocument document = new WorkflowDocument(new NetworkIdVO("ewestfal"), "SuperUserApproveActionRequestFyiTest");
        document.appSpecificRouteDocumentToUser(EdenConstants.ACTION_REQUEST_FYI_REQ, "", new NetworkIdVO("rkirkend"), "", true);
        document.routeDocument("");

        // doc should still be final
        assertEquals("Document should be FINAL", EdenConstants.ROUTE_HEADER_FINAL_CD, document.getRouteHeader().getDocRouteStatus());

        document = new WorkflowDocument(new NetworkIdVO("rkirkend"), document.getRouteHeaderId());
        assertTrue("rkirkend should have an FYI request.", document.isFYIRequested());

        WorkflowUser rkirkend = KEWServiceLocator.getUserService().getWorkflowUser(new AuthenticationUserId("rkirkend"));
        List<ActionRequestValue> actionRequests = KEWServiceLocator.getActionRequestService().findAllValidRequests(rkirkend, document.getRouteHeaderId(), EdenConstants.ACTION_REQUEST_FYI_REQ);
        assertEquals("There should only be 1 fyi request to rkirkend.", 1, actionRequests.size());
        document = new WorkflowDocument(new NetworkIdVO("ewestfal"), document.getRouteHeaderId());
        document.superUserActionRequestApprove(actionRequests.get(0).getActionRequestId(), "");

        // FYI should no longer be requested
        document = new WorkflowDocument(new NetworkIdVO("rkirkend"), document.getRouteHeaderId());
        assertFalse("rkirkend should no longer have an FYI request.", document.isFYIRequested());
    }
    
    @Test public void testSuperUserActionRoutesDocument() throws Exception {
        WorkflowDocument document = new WorkflowDocument(new NetworkIdVO("ewestfal"), "SuperUserApproveActionRequestFyiTest");
        document.saveDocument("");
        // doc should saved
        assertEquals("Document should be SAVED", EdenConstants.ROUTE_HEADER_SAVED_CD, document.getRouteHeader().getDocRouteStatus());

        document = new WorkflowDocument(new NetworkIdVO("ewestfal"), document.getRouteHeaderId());
        assertTrue("ewestfal should have Complete request", document.isCompletionRequested());

        document = new WorkflowDocument(new NetworkIdVO("rkirkend"), document.getRouteHeaderId());
        assertFalse("rkirkend should not have Complete request", document.isCompletionRequested());
        assertFalse("rkirkend should not have Approve request", document.isApprovalRequested());
        assertTrue("rkirkend should be a super user of the document", document.isSuperUser());
        WorkflowUser ewestfal = KEWServiceLocator.getUserService().getWorkflowUser(new AuthenticationUserId("ewestfal"));
        List<ActionRequestValue> actionRequests = KEWServiceLocator.getActionRequestService().findAllValidRequests(ewestfal, document.getRouteHeaderId(), EdenConstants.ACTION_REQUEST_COMPLETE_REQ);
        assertEquals("There should only be 1 complete request to ewestfal as result of the save.", 1, actionRequests.size());
        document.superUserActionRequestApprove(actionRequests.get(0).getActionRequestId(), "");

        // Complete should no longer be requested
        document = new WorkflowDocument(new NetworkIdVO("ewestfal"), document.getRouteHeaderId());
        assertFalse("ewestfal should not have Complete request", document.isCompletionRequested());

        // doc should now be final
        assertEquals("Document should be FINAL", EdenConstants.ROUTE_HEADER_FINAL_CD, document.getRouteHeader().getDocRouteStatus());
    }

    @Test public void testSavedDocumentSuperUserAdhocActionsApprove() throws Exception {
        WorkflowDocument document = new WorkflowDocument(new NetworkIdVO("ewestfal"), "SuperUserApproveActionRequestFyiTest");
        String adhocActionRequestCode = EdenConstants.ACTION_REQUEST_APPROVE_REQ;
        String adhocActionUserNetworkId = "jhopf";
        document.appSpecificRouteDocumentToUser(adhocActionRequestCode, "", new NetworkIdVO(adhocActionUserNetworkId), "", true);
        document.saveDocument("");
        // doc should be saved
        assertEquals("Document should be SAVED", EdenConstants.ROUTE_HEADER_SAVED_CD, document.getRouteHeader().getDocRouteStatus());

        document = new WorkflowDocument(new NetworkIdVO("ewestfal"), document.getRouteHeaderId());
        assertTrue("ewestfal should have Complete request", document.isCompletionRequested());

        document = new WorkflowDocument(new NetworkIdVO("rkirkend"), document.getRouteHeaderId());
        assertFalse("rkirkend should not have Complete request", document.isCompletionRequested());
        assertFalse("rkirkend should not have Approve request", document.isApprovalRequested());
        assertTrue("rkirkend should be a super user of the document", document.isSuperUser());
        WorkflowUser adhocActionRequestUser = KEWServiceLocator.getUserService().getWorkflowUser(new AuthenticationUserId(adhocActionUserNetworkId));
        List<ActionRequestValue> actionRequests = KEWServiceLocator.getActionRequestService().findAllValidRequests(adhocActionRequestUser, document.getRouteHeaderId(), adhocActionRequestCode);
        assertEquals("There should only be 1 approve request to " + adhocActionUserNetworkId + ".", 1, actionRequests.size());
        document.superUserActionRequestApprove(actionRequests.get(0).getActionRequestId(), "");

        // approve should no longer be requested
        document = new WorkflowDocument(new NetworkIdVO(adhocActionUserNetworkId), document.getRouteHeaderId());
        assertFalse(adhocActionRequestUser + " should not have approve request", document.isApprovalRequested());

        // doc should still be saved
        assertEquals("Document should be SAVED", EdenConstants.ROUTE_HEADER_SAVED_CD, document.getRouteHeader().getDocRouteStatus());
    }

}
