/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * you may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.connector.integration.test.viber;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Viber connector integration test.
 */
public class ViberConnectorIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> eiRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();

    private String apiUrlEndPoint;

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        String connectorName = System.getProperty("connector_name") + "-connector-" +
            System.getProperty("connector_version") + ".zip";
        init(connectorName);
        getApiConfigProperties();
        eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");

        apiRequestHeadersMap.put("Content-Type", "application/json");
        apiRequestHeadersMap.put("X-Viber-Auth-Token", connectorProperties.getProperty("appKey"));
        apiUrlEndPoint = connectorProperties.getProperty("apiUrl") + "/pa";
    }

    /**
     * Positive test case for accountInfo method with mandatory parameters.
     */
    @Test(priority = 1, groups = {"wso2.esb"},
        description = "viber {accountInfo} integration test with mandatory parameters.")
    public void testAccountInfoMandatory() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getAccountInfo");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_accountInfo_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/get_account_info";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
            apiRequestHeadersMap, null, null);

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));

            Assert.assertEquals(eiRestResponse.getBody().getJSONArray("members").getJSONObject(1).
                    getString("name"),
                apiRestResponse.getBody().getJSONArray("members").getJSONObject(1).
                    getString("name"));
        }

        String user1 = apiRestResponse.getBody().getJSONArray("members").getJSONObject(0).getString("id");
        connectorProperties.setProperty("user1", user1);

        String user2 = apiRestResponse.getBody().getJSONArray("members").getJSONObject(1).getString("id");
        connectorProperties.setProperty("user2", user2);

        String senderAvatarUrl = apiRestResponse.getBody().getJSONArray("members").getJSONObject(0).
            getString("avatar");
        connectorProperties.setProperty("senderAvatarUrl", senderAvatarUrl);

        String senderName = apiRestResponse.getBody().getJSONArray("members").getJSONObject(0).
            getString("name");
        connectorProperties.setProperty("senderName", senderName);

    }

    /**
     * Positive test case for broadcastCarouselContentMessage method with mandatory parameters.
     */
    @Test(priority = 2, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastCarouselContentMessage} integration test with mandatory parameters.")
    public void testBroadcastCarouselContentMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastCarouselContentMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastCarouselContentMessage_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastCarouselContentMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));

            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastCarouselContentMessage method with optional parameters.
     */
    @Test(priority = 3, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastCarouselContentMessage} integration test with optional parameters.")
    public void testBroadcastCarouselContentMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastCarouselContentMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastCarouselContentMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastCarouselContentMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Test negative case for broadcastCarouselContentMessage.
     */
    @Test(priority = 4, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastCarouselContentMessage} integration test with negative case.")
    public void testBroadcastCarouselContentMessageNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastCarouselContentMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastCarouselContentMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastCarouselContentMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request " +
                "to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastContactMessage method with mandatory parameters.
     */
    @Test(priority = 5, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastContactMessage} integration test with mandatory parameters.")
    public void testBroadcastContactMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastContactMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastContactMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastContactMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This  504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastContactMessage method with optional parameters.
     */
    @Test(priority = 6, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastContactMessage} integration test with optional parameters.")
    public void testBroadcastContactMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastContactMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastContactMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastContactMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Negative test case for broadcastContactMessage
     */
    @Test(priority = 7, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastContactMessage} integration test with negative case.")
    public void testBroadcastContactMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastContactMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastContactMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastContactMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastFileMessage method with mandatory parameters.
     */
    @Test(priority = 8, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastFileMessage} integration test with mandatory parameters.")
    public void testBroadcastFileMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastFileMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastFileMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastFileMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastFileMessage method with optional parameters.
     */

    @Test(priority = 9, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastFileMessage} integration test with mandatory parameters.")
    public void testBroadcastFileMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastFileMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastFileMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastFileMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Negative case for broadcastFileMessage.
     */
    @Test(priority = 10, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastFileMessage} integration test negative case.")
    public void testBroadcastFileMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastFileMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastFileMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastFileMessage_negative.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastLocationMessage method with mandatory parameters.
     */
    @Test(priority = 11, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastLocationMessage} integration test with mandatory parameters.")
    public void testBroadcastLocationMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastLocationMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastLocationMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastLocationMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastLocationMessage method with optional parameters.
     */
    @Test(priority = 12, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastLocationMessage} " +
            "integration test with optional parameters.")
    public void testBroadcastLocationMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastLocationMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastLocationMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastLocationMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Negative test case for broadcastLocationMessage.
     */
    @Test(priority = 13, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastLocationMessage} integration test with negative case.")
    public void testBroadcastLocationMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastLocationMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastLocationMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastLocationMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastPictureMessage method with mandatory parameters.
     */
    @Test(priority = 14, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastPictureMessage integration test with mandatory parameters.")
    public void testBroadcastPictureMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastPictureMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastPictureMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastPictureMessage_mandatory.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastPictureMessage method with optional parameters.
     */
    @Test(priority = 15, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastPictureMessage integration test with optional parameters.")
    public void testBroadcastPictureMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastPictureMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastPictureMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastPictureMessage_optional.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Negative test case for broadcastPictureMessage.
     */
    @Test(priority = 16, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastPictureMessage} integration test with negative case.")
    public void testBroadcastPictureMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastPictureMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastPictureMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastPictureMessage_negative.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastStickerMessage method with mandatory parameters.
     */
    @Test(priority = 17, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastStickerMessage integration test with mandatory parameters.")
    public void testBroadcastStickerMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastStickerMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastStickerMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastStickerMessage_mandatory.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastStickerMessage method with optional parameters.
     */
    @Test(priority = 18, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastStickerMessage integration test with optional parameters.")
    public void testBroadcastStickerMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastStickerMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastStickerMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastStickerMessage_optional.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Negative test case for broadcastStickerMessage.
     */
    @Test(priority = 19, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastStickerMessage} integration test with negative case.")
    public void testBroadcastStickerMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastStickerMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastStickerMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastStickerMessage_negative.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastTextMessage method with mandatory parameters.
     */
    @Test(priority = 20, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastTextMessage integration test with mandatory parameters.")
    public void testBroadcastTextMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastTextMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastTextMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastTextMessage_mandatory.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastTextMessage method with optional parameters.
     */
    @Test(priority = 21, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastTextMessage integration test with mandatory parameters.")
    public void testBroadcastTextMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastTextMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastTextMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastTextMessage_optional.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Negative test case for broadcastTextMessage.
     */
    @Test(priority = 22, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastTextMessage} integration test with negative case.")
    public void testBroadcastTextMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastTextMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastTextMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastTextMessage_negative.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastUrlMessage method with mandatory parameters.
     */
    @Test(priority = 23, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastUrlMessage} integration test with mandatory parameters.")
    public void testBroadcastUrlMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastUrlMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastUrlMessage_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastUrlMessage_mandatory.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastUrlMessage method with optional parameters.
     */
    @Test(priority = 24, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastUrlMessage} integration test with optional parameters.")
    public void testBroadcastUrlMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastUrlMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastUrlMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastUrlMessage_optional.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Negative test case for broadcastUrlMessage.
     */
    @Test(priority = 25, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastUrlMessage} integration test " +
            "with negative case.")
    public void testBroadcastUrlMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastUrlMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastUrlMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";

        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastUrlMessage_negative.json");

        if (apiRestResponse.getHttpStatusCode() == 504 && apiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastVideoMessage method with mandatory parameters.
     */
    @Test(priority = 26, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastVideoMessage integration test with mandatory parameters.")
    public void testBroadcastVideoMessageWithMandatoryParameters() throws Exception {

        ;
        eiRequestHeadersMap.put("Action", "urn:broadcastVideoMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastVideoMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastVideoMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for broadcastVideoMessage method with optional parameters.
     */
    @Test(priority = 27, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastVideoMessage integration test with optional parameters.")
    public void testBroadcastVideoMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastVideoMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastVideoMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastVideoMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Negative test case for broadcastVideoMessage.
     */
    @Test(priority = 28, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {broadcastVideoMessage} integration test with negative case.")
    public void testBroadcastVideoMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:broadcastVideoMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_broadcastVideoMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/broadcast_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_broadcastVideoMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for sendTextMessage method with mandatory parameters.
     */
    @Test(priority = 29, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendTextMessage} integration test with mandatory parameters.")
    public void testSendTextMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendTextMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendTextMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendTextMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendTextMessage method with optional parameters.
     */
    @Test(priority = 30, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendTextMessage} integration test with optional parameters.")
    public void testSendTextMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendTextMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendTextMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendTextMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative test case for sendTextMessage.
     */
    @Test(priority = 31, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendTextMessage} integration test with negative case.")
    public void testSendTextMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendTextMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendTextMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendTextMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendPictureMessage method with mandatory parameters.
     */
    @Test(priority = 32, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendPictureMessage} integration test with mandatory parameters.")
    public void testSendPictureMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendPictureMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendPictureMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendPictureMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendPictureMessage method with optional parameters.
     */
    @Test(priority = 33, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendPictureMessage} integration test with optional parameters.")
    public void testSendPictureMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendPictureMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendPictureMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendPictureMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative test case for sendPictureMessage.
     */
    @Test(priority = 34, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendPictureMessage} integration test with negative case.")
    public void testSendPictureMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendPictureMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendPictureMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendPictureMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendContactMessage method with optional parameters.
     */
    @Test(priority = 35, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendContactMessage integration test with optional parameters.")
    public void testSendContactMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendContactMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendContactMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendContactMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative case for sendContactMessage method with  parameters.
     */
    @Test(priority = 36, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendContactMessage integration test with negative case.")
    public void testSendContactMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendContactMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendContactMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendContactMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendVideoMessage method with mandatory parameters.
     */
    @Test(priority = 37, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendVideoMessage} integration test with mandatory parameters.")
    public void testSendVideoMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendVideoMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendVideoMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendVideoMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendVideoMessage method with optional parameters.
     */
    @Test(priority = 38, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendVideoMessage} integration test with optional parameters.")
    public void testSendVideoMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendVideoMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendVideoMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendVideoMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative test case for sendVideoMessage.
     */
    @Test(priority = 39, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendVideoMessage} integration test with negative case.")
    public void testSendVideoMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendVideoMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendVideoMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendVideoMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendUrlMessage method with mandatory parameters.
     */
    @Test(priority = 40, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendUrlMessage} integration test with mandatory parameters.")
    public void testSendUrlMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendUrlMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendUrlMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendUrlMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendUrlMessage method with optional parameters.
     */
    @Test(priority = 41, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendUrlMessage} integration test with optional parameters.")
    public void testSendUrlMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendUrlMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendUrlMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendUrlMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative test case for sendUrlMessage.
     */
    @Test(priority = 42, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendUrlMessage} integration test with negative case.")
    public void testSendUrlMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendUrlMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendUrlMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendUrlMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendStickerMessage method with mandatory parameters.
     */
    @Test(priority = 43, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendStickerMessage} integration test with mandatory parameters.")
    public void testSendStickerMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendStickerMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendStickerMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendStickerMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendStickerMessage method with optional parameters.
     */
    @Test(priority = 44, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendStickerMessage} integration test with optional parameters.")
    public void testSendStickerMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendStickerMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendStickerMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendStickerMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative test case for sendStickerMessage.
     */
    @Test(priority = 45, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendStickerMessage} integration test with negative case.")
    public void testSendStickerMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendStickerMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendStickerMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendStickerMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendLocationMessage method with mandatory parameters.
     */
    @Test(priority = 46, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendLocationMessage} integration test with mandatory parameters.")
    public void testSendLocationMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendLocationMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendLocationMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendLocationMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendLocationMessage method with optional parameters.
     */
    @Test(priority = 47, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendLocationMessage} integration test with optional parameters.")
    public void testSendLocationMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendLocationMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendLocationMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendLocationMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative test case for sendLocationMessage.
     */
    @Test(priority = 48, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendLocationMessage} integration test with negative case.")
    public void testSendLocationMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendLocationMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendLocationMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendLocationMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendFileMessage method with mandatory parameters.
     */
    @Test(priority = 49, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendFileMessage} integration test with mandatory parameters.")
    public void testSendFileMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendFileMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendFileMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendFileMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendFileMessage method with optional parameters.
     */
    @Test(priority = 50, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendFileMessage} integration test with optional parameters.")
    public void testSendFileMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendFileMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendFileMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendFileMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative test case for sendFileMessage.
     */
    @Test(priority = 51, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendFileMessage} integration test with negative case.")
    public void testSendFileMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendFileMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendFileMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendFileMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendCarouselContentMessage method with mandatory parameters.
     */
    @Test(priority = 52, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendCarouselContentMessage integration test with mandatory parameters.")
    public void testSendCarouselContentMessageWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendCarouselContentMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendCarouselContentMessage_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendCarouselContentMessage_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendCarouselContentMessage method with optional parameters.
     */
    @Test(priority = 53, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendCarouselContentMessage integration test with optional parameters.")
    public void testSendCarouselContentMessageWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendCarouselContentMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendCarouselContentMessage_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendCarouselContentMessage_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));

        }
    }

    /**
     * Negative test case for sendCarouselContentMessage..
     */
    @Test(priority = 54, groups = {"wso2.esb"},
        description = "viber {sendCarouselContentMessage} integration test with negative case.")
    public void testSendCarouselContentMessageWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendCarouselContentMessage");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendCarouselContentMessage_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendCarouselContentMessage_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for getOnline method with mandatory parameters.
     */
    @Test(priority = 55, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {getOnline} integration test with mandatory parameters.")
    public void testOnlineWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getOnline");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_getOnline_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/get_online";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_getOnline_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getJSONArray("users").getJSONObject(0).getString("id"),
                apiRestResponse.getBody().getJSONArray("users").getJSONObject(0).getString("id"));
        }
    }

    /**
     * Negative test case for online.
     */
    @Test(priority = 56, groups = {"wso2.esb"},
        description = "viber {getOnline} integration test with negative case.")
    public void testOnlineWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getOnline");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_getOnline_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/get_online";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_getOnline_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for sendKeyboarVideo method with mandatory parameters.
     */
    @Test(priority = 57, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardVideo} integration test with mandatory parameters.")
    public void testSendKeyboardVideoWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardVideo");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardVideo_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardVideo_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboarVideo method with optional parameters.
     */
    @Test(priority = 58, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardVideo} integration test with optional parameters.")
    public void testSendKeyboardVideoWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardVideo");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardVideo_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardVideo_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }

    }

    /**
     * negative test case for sendKeyboardVideo.
     */
    @Test(priority = 59, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardVideo} integration test negative case.")
    public void testSendKeyboardVideoWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardVideo");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardVideo_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardVideo_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardUrl method with mandatory parameters.
     */
    @Test(priority = 60, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardUrl} integration test with mandatory parameters.")
    public void testSendKeyboardUrlWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardUrl");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardUrl_mandatory.json");
        ;
        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardUrl_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardUrl method with optional parameters.
     */
    @Test(priority = 61, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardUrl} integration test with optional parameters.")
    public void testSendKeyboardUrlWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardUrl");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardUrl_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardUrl_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * negative test case for sendKeyboardUrl.
     */
    @Test(priority = 62, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardUrl} integration test negative case.")
    public void testSendKeyboardUrlWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardUrl");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardUrl_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardUrl_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardSticker method with mandatory parameters.
     */
    @Test(priority = 63, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardSticker integration test with mandatory parameters.")
    public void testSendKeyboardStickerWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardSticker");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardSticker_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardSticker_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardSticker method with optional parameters.
     */
    @Test(priority = 64, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardSticker integration test with optional parameters.")
    public void testSendKeyboardStickerWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardSticker");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardSticker_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardSticker_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * negative test case for sendKeyboardsticker.
     */
    @Test(priority = 65, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardSticker integration test negative case.")
    public void testSendKeyboardStickerWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardSticker");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardSticker_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardSticker_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardPicture method with mandatory parameters.
     */
    @Test(priority = 66, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardPicture integration test with mandatory parameters.")
    public void testSendKeyboardPictureWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardPicture");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardPicture_mandatory.json");
        ;
        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardPicture_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardPicture method with optional parameters.
     */
    @Test(priority = 67, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardPicture integration test with optional parameters.")
    public void testSendKeyboardPictureWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardPicture");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardPicture_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardPicture_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * negative test case for sendKeyboardPicture.
     */
    @Test(priority = 68, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardPicture} integration test " +
            "negative case.")
    public void testSendKeyboardPictureWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardPicture");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardPicture_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardPicture_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardLocation method with mandatory parameters.
     */
    @Test(priority = 69, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardLocation} " +
            "integration test with mandatory parameters.")
    public void testSendKeyboardLocationWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardLocation");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardLocation_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardLocation_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardLocation method with optional parameters.
     */
    @Test(priority = 70, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardLocation integration test with optional parameters.")
    public void testSendKeyboardLocationWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardLocation");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardLocation_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardLocation_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * negative test case for sendKeyboardLocation.
     */
    @Test(priority = 71, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardLocation} integration test" +
            " negative case.")
    public void testSendKeyboardLocationWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardLocation");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardLocation_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardLocation_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardFile method with mandatory parameters.
     */
    @Test(priority = 72, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardFile} integration test with mandatory parameters.")
    public void testSendKeyboardFileWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardFile");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardFile_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardFile_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardFile method with optional parameters.
     */
    @Test(priority = 73, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardFile} integration test with optional parameters.")
    public void testSendKeyboardFileWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardFile");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardFile_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardFile_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * negative test case for sendKeyboardFile.
     */
    @Test(priority = 74, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardFile} integration test negative case.")
    public void testSendKeyboardFileWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardFile");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardFile_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardFile_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardContact method with mandatory parameters.
     */
    @Test(priority = 75, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardContact integration test with mandatory parameters.")
    public void testSendKeyboardContactWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardContact");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardContact_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardContact_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardContact method with optional parameters.
     */
    @Test(priority = 76, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardText} integration test with optional parameters.")
    public void testSendKeyboardContactWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardContact");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardContact_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardContact_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * negative test case for sendKeyboardContact.
     */
    @Test(priority = 77, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardContact} integration test negative case.")
    public void testSendKeyboardContactWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardContact");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardContact_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardContact_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardText method with mandatory parameters.
     */
    @Test(priority = 78, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardText} integration test with mandatory parameters.")
    public void testSendKeyboardTextWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardText");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardText_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardText_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardText method with optional parameters.
     */
    @Test(priority = 79, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardText} integration test with optional parameters.")
    public void testSendKeyboardTextWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardText");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardText_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardText_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * negative test case for sendKeyboardText.
     */
    @Test(priority = 80, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardText} integration test negative case.")
    public void testSendKeyboardTextWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardText");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardText_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardText_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardCarouselContent method with mandatory parameters.
     */
    @Test(priority = 81, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardCarouselContent}" +
            " integration test with mandatory parameters.")
    public void testSendKeyboardCarouselContentWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardCarouselContent");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardCarouselContent_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardCarouselContent_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for sendKeyboardCarouselContent method with optional parameters.
     */
    @Test(priority = 82, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardCarouselContent integration test with optional parameters.")
    public void testSendKeyboardCarouselContentWithOptionalParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardCarouselContent");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardCarouselContent_optional.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardCarouselContent_optional.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Negative case for sendKeyboardCarouselContent method with  parameters.
     */
    @Test(priority = 83, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {sendKeyboardCarouselContent integration test with negative case.")
    public void testSendKeyboardCarouselContentWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:sendKeyboardCarouselContent");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_sendKeyboardCarouselContent_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/send_message";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_sendKeyboardCarouselContent_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

    /**
     * Positive test case for userDetails  with mandatory parameters.
     */
    @Test(priority = 84, groups = {"wso2.esb"}, dependsOnMethods = {"testAccountInfoMandatory"},
        description = "viber {getUserDetails integration test with mandatory parameters.")
    public void testGetUserDetailsWithMandatoryParameters() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getUserDetails");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_getUserDetails_mandatory.json");

        String apiEndPoint = apiUrlEndPoint + "/get_user_details";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_getUserDetails_mandatory.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));

            Assert.assertEquals(eiRestResponse.getBody().getString("status"),
                apiRestResponse.getBody().getString("status"));
        }
    }

    /**
     * Positive test case for userDetails  with negative case.
     */
    @Test(priority = 85, groups = {"wso2.esb"},
        description = "viber {getUserDetails integration test with negative case.")
    public void testGetUserDetailsWithNegativeCase() throws Exception {

        eiRequestHeadersMap.put("Action", "urn:getUserDetails");

        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
            "esb_getUserDetails_negative.json");

        String apiEndPoint = apiUrlEndPoint + "/get_user_details";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
            apiRequestHeadersMap, "api_getUserDetails_negative.json");

        if (eiRestResponse.getHttpStatusCode() == 504 && eiRestResponse.getBody() == null) {
            log.info("This 504 GATEWAY_TIMEOUT error is coming from Viber back-end.When we send multiple request" +
                " to the back-end at the same time, back-end will be dropped some requests.");
        } else {
            Assert.assertEquals(eiRestResponse.getBody().getString("status_message"),
                apiRestResponse.getBody().getString("status_message"));
        }
    }

}
