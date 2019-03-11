# Working with get user details

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration) ]


## Overview

This method allow you to fetch the details of a specific Viber user based on his unique user ID.This request can be sent twice during a 12 hours period for each user ID.
## Operation details

##### getUserDetails

```xml
    <viber.getUserDetails>
      <id>{$ctx:id}</id>
    </viber.getUserDetails>
```

##### Properties

* id :- Unique Viber user Id.

##### Sample request

following is a sample JSON request that can be handled by the userDetails operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "id":"E4Ux8N0j3HcXEbrzF+nK5Q=="
}
```
**Sample response**

Given below is a sample response for the userDetails operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "chat_hostname": "SN-CHAT-03_",
    "user": {
        "id": "E4Ux8N0j3HcXEbrzF+nK5Q==",
        "name": "Sarangika Ethugala",
        "avatar": "https://media-direct.cdn.viber.com/download_photo?dlid=Z0h3a73XJ78G6elIfG-nfTQ2CKe4cFQxuZPF5lZw4xu1J7Y6x6RmufH5nnA2IG00r6K8ul7u_juvAqeVhMfXPyaorQrvpiY3C8kiSuShHESHTfl39rL4jVi5cGAq0CmTYTtQfg&fltp=jpg&imsz=0000",
        "language": "en",
        "country": "LK",
        "primary_device_os": "Android 5.1",
        "api_version": 7,
        "viber_version": "9.8.5.20",
        "mcc": 413,
        "mnc": 1,
        "device_type": "HUAWEI LUA-U22"
    }
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#get-user-details

## Sample configuration

Following example illustrates how to connect to Viber  with the init operation and userdetails operation.

 Sample proxy

````
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="testUserDetails"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.appKey)" name="appKey"/>
         <property expression="json-eval($.apiUrl)" name="apiUrl"/>
         <property expression="json-eval($.id)" name="id"/>
         <viber.init>
            <appKey>{$ctx:appKey}</appKey>
            <apiUrl>{$ctx:apiUrl}</apiUrl>
         </viber.init>
         <viber.getUserDetails>
            <id>{$ctx:id}</id>
         </viber.getUserDetails>
         <respond/>
      </inSequence>
      <outSequence/>
      <faultSequence/>
   </target>
   <description/>
</proxy>
 ````
  2. Create a json file named getUserDetails.json and copy the configurations given below to it:
   
   ```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "id":    
           "8iGPPECtQNw6KzwG1rnQQg=="
}
```
   3. Replace the credentials with your values.
       
   4. Execute the following curl command:

    
    $ curl --include --request POST --url 'http://sarangika-ThinkPad-T530:8280/services/testUserDetails' --header 'Content-Type: application/json" -d @getUserDetails.json
     
   5. Viber returns a json response similar to the one shown below:
 ``````
 "{"status":0,"status_message":"ok","chat_hostname":"SN-CHAT-04_","user":{"id":"8iGPPECtQNw6KzwG1rnQQg==","name":"bhathiya","language":"en","country":"LK","primary_device_os":"Android 9","api_version":7,"viber_version":"9.8.5.20","mcc":413,"mnc":1,"device_type":"Mi A1"}}"