/*
 * Copyright 2007 The Kuali Foundation
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
package edu.iu.uis.eden.clientapp.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.eden.docsearch.DocumentSearchResult;

/**
 * This is a virtual object used for the {@link DocumentSearchResult} class.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class DocumentSearchResultRowVO implements Serializable {

    private static final long serialVersionUID = -4512313267985796233L;

    private List<KeyValueVO> fieldValues = new ArrayList<KeyValueVO>();

    public List<KeyValueVO> getFieldValues() {
        return this.fieldValues;
    }

    public void setFieldValues(List<KeyValueVO> fieldValues) {
        this.fieldValues = fieldValues;
    }

}
