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
dryRun = false

project {
    // This is the base directory for the project.  All other paths will be relative to this one
    homeDirectory = "/Users/jonathan/dev/projects/rice-20"
    // Directories which will be added to the classpath for the purpose of finding the OJB-mapped classes 
    classpathDirectories = [
        "rice-middleware/krms/impl/target/classes"
        ]
    // Directories which contain jar files.  Each jar file will be added to the classpath
    classpathJarDirectories = []
    // Project source directories.  These directories will be scanned in order to find the source
    // files for the classes which need JPA annotation
    sourceDirectories = [
          "rice-framework/krad-app-framework/src/main/java"
        , "rice-framework/krad-data/src/main/java"
        , "rice-framework/krad-development-tools/src/main/java"
        , "rice-framework/krad-sampleapp/src/main/java"
        , "rice-framework/krad-sampleapp-data-model/src/main/java"
        , "rice-framework/krad-service-impl/src/main/java"
        , "rice-framework/krad-theme-builder/src/main/java"
        , "rice-framework/krad-web-framework/src/main/java"
        , "rice-middleware/client-contrib/src/main/java"
        , "rice-middleware/core/api/src/main/java"
        , "rice-middleware/core/framework/src/main/java"
        , "rice-middleware/core/impl/src/main/java"
        , "rice-middleware/core/web/src/main/java"
        , "rice-middleware/core-service/api/src/main/java"
        , "rice-middleware/core-service/framework/src/main/java"
        , "rice-middleware/core-service/impl/src/main/java"
        , "rice-middleware/core-service/web/src/main/java"
        , "rice-middleware/edl/framework/src/main/java"
        , "rice-middleware/edl/impl/src/main/java"
        , "rice-middleware/impl/src/main/java"
        , "rice-middleware/ken/api/src/main/java"
        , "rice-middleware/kew/api/src/main/java"
        , "rice-middleware/kew/framework/src/main/java"
        , "rice-middleware/kew/impl/src/main/java"
        , "rice-middleware/kim/kim-api/src/main/java"
        , "rice-middleware/kim/kim-framework/src/main/java"
        , "rice-middleware/kim/kim-impl/src/main/java"
        , "rice-middleware/kim/kim-ldap/src/main/java"
        , "rice-middleware/kns/src/main/java"
        , "rice-middleware/krms/api/src/main/java"
        , "rice-middleware/krms/framework/src/main/java"
        , "rice-middleware/krms/impl/src/main/java"
        , "rice-middleware/ksb/api/src/main/java"
        , "rice-middleware/ksb/client-impl/src/main/java"
        , "rice-middleware/ksb/server-impl/src/main/java"
        , "rice-middleware/ksb/web/src/main/java"
        , "rice-middleware/location/api/src/main/java"
        , "rice-middleware/location/framework/src/main/java"
        , "rice-middleware/location/impl/src/main/java"
        , "rice-middleware/location/web/src/main/java"
        , "rice-middleware/sampleapp/src/main/java"
        , "rice-middleware/standalone/src/main/java"
        ]
}
ojb {
    repositoryFiles = [
//          "rice-framework/krad-sampleapp/src/main/resources/OJB-repository-krad-sampleapp.xml"
          "rice-middleware/core-service/impl/src/main/resources/org/kuali/rice/coreservice/config/OJB-repository-core-service.xml"
//        , "rice-middleware/edl/impl/src/main/resources/org/kuali/rice/edl/impl/config/OJB-repository-edl.xml"
//        , "rice-middleware/impl/src/main/resources/org/kuali/rice/kcb/config/OJB-repository-kcb.xml"
//        , "rice-middleware/impl/src/main/resources/org/kuali/rice/ken/config/OJB-repository-ken.xml"
//        , "rice-middleware/impl/src/main/resources/org/kuali/rice/kns/config/OJB-repository-kns.xml"
//        , "rice-middleware/kew/impl/src/main/resources/org/kuali/rice/kew/impl/config/OJB-repository-kew.xml"
//        , "rice-middleware/kim/kim-impl/src/main/resources/org/kuali/rice/kim/impl/config/OJB-repository-kim-hist.xml"
//        , "rice-middleware/kim/kim-impl/src/main/resources/org/kuali/rice/kim/impl/config/OJB-repository-kim.xml"
//        , "rice-middleware/krms/impl/src/main/resources/org/kuali/rice/krms/config/OJB-repository-krms.xml"
//        , "rice-middleware/ksb/client-impl/src/main/resources/org/kuali/rice/ksb/config/OJB-repository-ksb-bam.xml"
//        , "rice-middleware/ksb/client-impl/src/main/resources/org/kuali/rice/ksb/config/OJB-repository-ksb-message.xml"
//        , "rice-middleware/ksb/server-impl/src/main/resources/org/kuali/rice/ksb/config/OJB-repository-ksb-registry.xml"
        , "rice-middleware/location/impl/src/main/resources/org/kuali/rice/location/config/OJB-repository-location.xml"
//        , "rice-middleware/sampleapp/src/main/resources/OJB-repository-sampleapp.xml"        
        ]
    // Mappings between OJB and JPA Type converters.  This contains the base ones known to the project teams.
    // If you have any additional ones you want auto-converted, add them to this map.
    // If the JPA converter value is blank, no converter will be added.  This assumes that there is
    // a default converter in place.
    converterMappings = [
          "OjbCharBooleanConversion" : ""
        , "OjbCharBooleanConversionTF" : "org.kuali.rice.krad.data.jpa.converters.BooleanTFConverter"
        , "OjbKualiDecimalFieldConversion" : ""
        , "OjbKualiEncryptDecryptFieldConversion" : "org.kuali.rice.krad.data.jpa.converters.EncryptionConverter"
        , "OjbKualiHashFieldConversion" : "org.kuali.rice.krad.data.jpa.converters.HashConverter"
        , "OjbKualiIntegerFieldConversion" : ""
        , "OjbKualiPercentFieldConversion" : ""
        , "OjbAccountActiveIndicatorConversion" : "org.kuali.rice.krad.data.jpa.converters.InverseBooleanYNConverter"
        , "OjbPendingBCAppointmentFundingActiveIndicatorConversion" : "org.kuali.rice.krad.data.jpa.converters.InverseBooleanYNConverter"
        , "OjbCharBooleanFieldInverseConversion" : "org.kuali.rice.krad.data.jpa.converters.InverseBooleanYNConverter"
        , "OjbBCPositionActiveIndicatorConversion" : "org.kuali.rice.krad.data.jpa.converters.BooleanAIConverter"
        , "OjbCharBooleanFieldAIConversion" : "org.kuali.rice.krad.data.jpa.converters.BooleanAIConverter"

        ]
}