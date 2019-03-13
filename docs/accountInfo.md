# Working with get account info

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration)]

## Overview

This method allow you to  fetch the account’s details as registered in Viber.

## Operation details

##### getAccountInfo

```xml
   <viber.getAccountInfo/>
```
##### Sample request

following is a sample JSON request that can be handled by the accountInfo operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80"
}
```
**Sample response**

Given below is a sample response for the accountInfo operation.

```json

{
    "status": 0,
    "status_message": "ok",
    "id": "pa:5253986655490529213",
    "chat_hostname": "SN-CHAT-50_",
    "name": "viberbot94",
    "uri": "viberbot94",
    "icon": "https://media-direct.cdn.viber.com/pg_download?pgtp=icons&dlid=0-04-01-eacaa33aac6c2f1f673b065b5e72dfcf7e658df5d2f87f564f35f179f92be42e&fltp=jpg&imsz=0000",
    "background": "https://media-direct.cdn.viber.com/pg_download?pgtp=backgrounds&dlid=9ad40c8d792af53a03871434d432cf15e1b4ac59426c048ce8b335426d1c70f0&fltp=jpg&imsz=0000",
    "category": "Companies, Brands & Products",
    "subcategory": "IT",
    "location": {
        "lat": 7.896224,
        "lon": 80.6736774
    },
    "country": "LK",
    "webhook": "https://deskun.com/viber/48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "event_types": [
        "subscribed",
        "unsubscribed",
        "webhook",
        "conversation_started",
        "action",
        "delivered",
        "failed",
        "message",
        "seen"
    ],
    "members": [
        {
            "id": "/8v+V5APa/jLsiywMa1HOA==",
            "name": "Nirubikaa",
            "avatar": "https://media-direct.cdn.viber.com/download_photo?dlid=Z0h3a73XJ78G6elIfG-nfTQ2CqexdFQ-45afsFd2tkq1J7k5wfFj76X5y3M2I21lpfS-7lHp-W7-VfHGh5WBPCep_V6_-XNgW5BwSr-kTkXTEP9w1R7q8WIWl5XeVCDPsYFFKQ&fltp=jpg&imsz=0000",
            "role": "admin"
        },
        {
            "id": "E4Ux8N0j3HcXEbrzF+nK5Q==",
            "name": "Sarangika Ethugala",
            "avatar": "https://media-direct.cdn.viber.com/download_photo?dlid=Z0h3a73XJ78G6elIfG-nfTQ2CKe4cFQxuZPF5lZw4xu1J7Y6x6RmufH5nnA2IG00r6K8ul7u_juvAqeVhMfXPyaorQrvpiY3C8kiSuShHESHTfl39rL4jVi5cGAq0CmTYTtQfg&fltp=jpg&imsz=0000",
            "role": "admin"
        },
        {
            "id": "8iGPPECtQNw6KzwG1rnQQg==",
            "name": "bhathiya",
            "role": "admin"
        },
        {
            "id": "/Etuco5vE0CJjo1IWTaDIQ==",
            "name": "Ajay",
            "role": "admin"
        }
    ],
    "subscribers_count": 8,
    "chat_flags": []
}
```

**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#get-account-info

## Sample configuration

Following example illustrates how to connect to Viber  with the init operation and accountInfo operation.

 Sample proxy

  ````
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="testAccountInfo"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.appKey)" name="appKey"/>
         <property expression="json-eval($.apiUrl)" name="apiUrl"/>
         <viber.init>
            <appKey>{$ctx:appKey}</appKey>
            <apiUrl>{$ctx:apiUrl}</apiUrl>
         </viber.init>
         <viber.getAccountInfo/>
         <respond/>
      </inSequence>
      <outSequence/>
      <faultSequence/>
   </target>
   <description/>
</proxy>
 ```` 
  2. Create a json file named accountInfo.json and copy the configurations given below to it:
  
  ```json
 {
     "apiUrl": "https://chatapi.viber.com",
     "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80"
 }
  ```
  3. Replace the credentials with your values.
  
  4. Execute the following curl command:
  
  ```bash
  curl http://sarangika-ThinkPad-T530:8280/services/testAccountInfo -H "Content-Type: application/json" -d @accountInfo.json
  ```
  5. Viber returns a json response similar to the one shown below:
  ``````
{"status":0,"status_message":"ok","id":"pa:5253986655490529213","chat_hostname":"SN-CHAT-29_","name":"viberbot94","uri":"viberbot94","icon":"https://media-direct.cdn.viber.com/pg_download?pgtp=icons&dlid=0-04-01-eacaa33aac6c2f1f673b065b5e72dfcf7e658df5d2f87f564f35f179f92be42e&fltp=jpg&imsz=0000","background":"https://media-direct.cdn.viber.com/pg_download?pgtp=backgrounds&dlid=9ad40c8d792af53a03871434d432cf15e1b4ac59426c048ce8b335426d1c70f0&fltp=jpg&imsz=0000","category":"Companies, Brands & Products","subcategory":"IT","location":{"lat":7.896224,"lon":80.6736774},"country":"LK","webhook":"https://deskun.com/viber/48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80","event_types":["subscribed","unsubscribed","conversation_started","delivered","failed","message","seen"],"members":[{"id":"E4Ux8N0j3HcXEbrzF+nK5Q==","name":"Sarangika Ethugala","avatar":"https://media-direct.cdn.viber.com/download_photo?dlid=Z0h3a73XJ78G6elIfG-nfTQ2CKexdlQ46pbFv1J_7EbjdLE7wKU36vCqmSNnJD8zr6W4ugrq822pVvrB1sfRZiCjrVm7-yI1X8lySbegGRGBGvhxqOkrifVu4z2ld1w72-eTzw&fltp=jpg&imsz=0000","role":"admin"},{"id":"8iGPPECtQNw6KzwG1rnQQg==","name":"bhathiya","role":"admin"},{"id":"/Etuco5vE0CJjo1IWTaDIQ==","name":"Ajay","role":"admin"}],"subscribers_count":7,"chat_flags":[]}