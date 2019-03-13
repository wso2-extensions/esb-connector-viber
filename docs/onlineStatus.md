# Working with online status

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration)]


## Overview
This method allow you to fetch the online status of a given subscribed account members.This method support up to 100 user id
per request and those users must be subscribed to the account.

## Operation details

##### getOnline

```xml
     <viber.getOnline>
       <ids>{$ctx:ids}</ids>
     </viber.getOnline>
```
##### Properties

* ids :- Unique Viber user Id.

##### Sample request

following is a sample JSON request that can be handled by the onlineStatus operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "ids": [
       "E4Ux8N0j3HcXEbrzF+nK5Q==",
       "8iGPPECtQNw6KzwG1rnQQg==",
       "/8v+V5APa/jLsiywMa1HOA=="     
    ]
}
```
**Sample response**

Given below is a sample response for the onlineStatus operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "users": [
        {
            "id": "E4Ux8N0j3HcXEbrzF+nK5Q==",
            "online_status": 1,
            "online_status_message": "offline",
            "last_online": 1549466821367
        },
        {
            "id": "8iGPPECtQNw6KzwG1rnQQg==",
            "online_status": 1,
            "online_status_message": "offline",
            "last_online": 1549427099386
        },
        {
            "id": "/8v+V5APa/jLsiywMa1HOA==",
            "online_status": 4,
            "online_status_message": "unavailable",
            "last_online": 0
        }
    ]
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#get-online

## Sample configuration

Following example illustrates how to connect to Viber  with the init operation and getOnline operation.

 Sample proxy

 ````  
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="testUserOnline"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.appKey)" name="appKey"/>
         <property expression="json-eval($.apiUrl)" name="apiUrl"/>
         <property expression="json-eval($.ids)" name="ids"/>
         <viber.init>
            <appKey>{$ctx:appKey}</appKey>
            <apiUrl>{$ctx:apiUrl}</apiUrl>
         </viber.init>
         <viber.getOnline>
            <ids>{$ctx:ids}</ids>
         </viber.getOnline>
         <respond/>
      </inSequence>
      <outSequence/>
      <faultSequence/>
   </target>
   <description/>
</proxy>
 ````
 2. Create a json file named getOnline.json and copy the configurations given below to it:
   
   ```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "ids": [
        "E4Ux8N0j3HcXEbrzF+nK5Q==",
        "8iGPPECtQNw6KzwG1rnQQg==",
        "/8v+V5APa/jLsiywMa1HOA=="
    ]
}
   ```
   3. Replace the credentials with your values.
   
   4. Execute the following curl command:
   
   ```bash
   $ curl --include --request POST --url 'http://sarangika-ThinkPad-T530:8280/services/testUserOnline' --header 'Content-Type: application/json" -d @getOnline.json
   ```
   5. Viber returns a json response similar to the one shown below:
   ``````
 "{"status":0,"status_message":"ok","users":[{"id":"E4Ux8N0j3HcXEbrzF+nK5Q==","online_status":2,"online_status_message":"undisclosed","last_online":0},{"id":"8iGPPECtQNw6KzwG1rnQQg==","online_status":1,"online_status_message":"offline","last_online":1551686893588},{"id":"/8v+V5APa/jLsiywMa1HOA==","online_status":4,"online_status_message":"unavailable","last_online":0}]}"