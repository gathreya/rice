/**
 * Copyright 2005-2017 The Kuali Foundation
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
package org.kuali.rice.kew.actionlist.web;

import org.displaytag.decorator.TableDecorator;
import org.kuali.rice.kew.actionitem.ActionItemBase;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.actionlist.DisplayParameters;


/**
 * Class used to decorate table rows generated by our table tag lib.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class ActionListDecorator extends TableDecorator {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ActionListDecorator.class);
    
    private int rowCounter = 0;
    
    @Override
    public String startRow() {
         return "";
    }

    @Override
    public String finishRow() {
        ActionItemBase actionItem = (ActionItemBase) getCurrentRowObject();
        String returnRow = "";
        try {
        	DisplayParameters displayParameters = actionItem.getDisplayParameters();
            if (displayParameters != null) {
                Integer index = actionItem.getActionListIndex();
                Integer frameHeight = new Integer(290);
                try {
                    if (displayParameters.getFrameHeight() != null && displayParameters.getFrameHeight() > 0) {
                        frameHeight = displayParameters.getFrameHeight();
                    }
                } catch (Exception ex) {
                    LOG.error("Error getting custom action list frame height.", ex);
                }
                StringBuffer newRow = new StringBuffer();
                newRow.append("<tr id='G");
                newRow.append(index.toString());
                newRow.append("' style='display: none;'>");
                newRow.append("<td class='white' height='0' colspan='11' >");
                newRow.append("<iframe name='iframeAL_");
                newRow.append(index.toString());
                newRow.append("' scrolling='yes' width=100% height=");
                newRow.append(frameHeight.toString());
                newRow.append(" hspace=0 vspace=0 frameborder=0></iframe>");
                newRow.append("</td></tr>");
                returnRow += newRow.toString();
            }
        } catch (Exception e) {
            LOG.error("Error with custom action list attribute.", e);
        }
        return returnRow;
    }
    
    /**
     * Adds a CSS class to the current row based on the value of the row object's rowStyleClass attribute.
     * 
     * @see org.displaytag.decorator.TableDecorator#addRowClass()
     */
    @Override
    public String addRowClass() {
    	ActionItemBase actionItem = (ActionItemBase) getCurrentRowObject();
    	return "actionlist_" + KewApiConstants.ACTION_LIST_COLOR_NAMES.get(actionItem.getRowStyleClass());
    }

	/**
	 * Adds a unique ID to this row that allows JavaScript to add mouse event listeners at run-time. This is necessary because the action list's
	 * display:table tag does not allow event handlers to be specified for the row elements.
	 * 
	 * @see org.displaytag.decorator.TableDecorator#addRowId()
	 */
	@Override
	public String addRowId() {
		return "actionlist_tr_" + rowCounter++;
	}

	/**
	 * Resets the row index counter after all rows have been added to the table.
	 * 
	 * @see org.displaytag.decorator.TableDecorator#finish()
	 */
	@Override
	public void finish() {
		super.finish();
		rowCounter = 0;
	}
    
}
