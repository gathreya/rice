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
package org.kuali.rice.kew.documentlink.service;

import org.kuali.rice.kew.documentlink.DocumentLink;

import java.util.List;

/**
 * This is a description of what this class does - g1zhang don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public interface DocumentLinkService {

    List<DocumentLink> getLinkedDocumentsByDocId(String docId);
    
    List<DocumentLink> getOutgoingLinkedDocumentsByDocId(String docId);

    DocumentLink saveDocumentLink(DocumentLink link);

    void deleteDocumentLink(DocumentLink link);

    DocumentLink getDocumentLink(String documentLinkId);

}
