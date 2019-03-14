# Working with Send Message

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration) ]


## Overview

The following operations allow you to send  messages to viber users who subscribe to the account.
 Click an operation name to see details on how to use it.



| Operation | Description |
| ------------- |-------------|
| [sendCarouselContentMessage](#Send-carousel-content-message)   |Send a carousel content message.|
| [sendContactMessage](#Send-contact-message) | Send a contact message.|
| [sendFileMessage](#Send-file-message) |Send a file message.|
| [sendLocationMessage](#Send-location-message) |Send a location message.|
| [sendPictureMessage](#Send-picture-message) |Send a picture message.|
| [sendStickerMessage](#Send-sticker-message) |Send a sticker message.|
| [sendTextMessage](#Send-text-message) |Send a text message.|
| [sendUrlMessage](#Send-url-message) |Send a url message.|
| [sendVideoMessage](#Send-video-message) |Send a video message.|


## Operation details

This section provides details on each of the operations.


#### Send carousel content message

This method allow you to send a Carousel Content Message.

##### sendCarouselContentMessage

```xml
    <viber.sendCarouselContentMessage>
        <receiver>{$ctx:receiver}</receiver>
        <type>{$ctx:type}</type>
        <richMediaButtons>{$ctx:richMediaButtons}</richMediaButtons>
        <backGroundColor>{$ctx:backGroundColor}</backGroundColor>
        <buttonsGroupColumns>{$ctx:buttonsGroupColumns}</buttonsGroupColumns>
        <buttonsGroupRows>{$ctx:buttonsGroupRows}</buttonsGroupRows>
   </viber.sendCarouselContentMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* richMediaButtons :- Array of rich media buttons.
* backGroundColor :- Color of the background.
* buttonsGroupColumns :- Number of rows per carousel content block.
* buttonsGroupRows :- Number of columns per carousel content block.

##### Sample request

following is a sample JSON request that can be handled by the  sendCarouselContentMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "rich_media",
    "backGroundColor":"#2db9b9",
    "buttonsGroupRows":6,
    "buttonsGroupColumns":6,
    "richMediaButtons": [
        {
            "Columns": 6,
            "Rows": 2,
            "Text": "<font color=#323232><b>Headphones with Microphone, On-ear Wired earphones</b></font><font color=#777777><br>Sound Intone </font><font color=#6fc133>$17.99</font>",
            "ActionType": "open-url",
            "ActionBody": "https://www.google.com",
            "TextSize": "medium",
            "TextVAlign": "middle",
            "TextHAlign": "left"
        }
    ]
}
``````

Given below is a sample response for the sendCarouselContentMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5268755696075582411,
    "chat_hostname": "SN-CHAT-27_"
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#carousel-content-message

#### Send contact message

This method allow you to send a contact message.

**Validation :**<br></br>
   contactName - Max 28 characters.<br></br>
   contactNumber - Max 18 characters.

##### sendContactMessage

```xml
  <viber.sendContactMessage>
    <receiver>{$ctx:receiver}</receiver>
    <senderName>{$ctx:senderName}</senderName>
    <type>{$ctx:type}</type>
    <contactName>{$ctx:contactName}</contactName>
    <contactNumber>{$ctx:contactNumber}</contactNumber>
    <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
    <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
    <trackingData>{$ctx:trackingData}</trackingData>
  </viber.sendContactMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* senderName :- Sender's name.
* type :- The type of the message.
* contactName :- Name of the contact.
* contactNumber :- Number of the contact.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

##### Sample request

following is a sample JSON request that can be handled by the sendContactMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "john martin",
    "type": "contact",
    "sender": "http://avatar.example.com",
    "contactName": "tom alis",
    "contactNumber": "07106352",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data"
}
```
Given below is a sample response for the sendContactMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5268755696075582411,
    "chat_hostname": "SN-CHAT-27_"
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#contact-message
 #### Send file message

   This method allow you to send a file  message.
   
**Validation :**<br></br>
   mediaUrlFile -  Max size 50 MB.<br></br>
   fileName -  File name should include extension. Max 256 characters.
##### sendFileMessage

```xml
    <viber.sendFileMessage>
      <receiver>{$ctx:receiver}</receiver>
      <senderName>{$ctx:senderName}</senderName>
      <type>{$ctx:type}</type>
      <mediaUrlFile>{$ctx:mediaUrlFile}</mediaUrlFile>
      <size>{$ctx:size}</size>
      <fileName>{$ctx:fileName}</fileName>
      <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
      <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
      <trackingData>{$ctx:trackingData}</trackingData>
    </viber.sendFileMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* senderName :- Sender's name.
* type :- The type of the message.
* mediaUrlFile :- The url of the file.
* size :- Size of the file.
* fileName :- Name of the file.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.


##### Sample request

following is a sample JSON request that can be handled by the sendFileMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "John macklen",
    "type": "file",
    "mediaUrlFile": "http://www.images.com/file.doc",
    "size": 10000,
    "fileName": "name_of_file.doc",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data"
}
```
Given below is a sample response for the sendFileMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52687596075582411,
    "chat_hostname": "SN-CHAT-27_"
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#file-message

#### Send location  message

   This method allow you to send a location message.
   
**Validation :**<br></br>
   latitude -  (±90°) within valid ranges<br></br>
   longitude -  (±180°) within valid ranges).
##### sendLocationMessage

```xml
     <viber.sendLocationMessage>
        <receiver>{$ctx:receiver}</receiver>
        <senderName>{$ctx:senderName}</senderName>
        <type>{$ctx:type}</type>
        <latitude>{$ctx:latitude}</latitude>
        <longitude>{$ctx:longitude}</longitude>
        <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
        <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
        <trackingData>{$ctx:trackingData}</trackingData>
     </viber.sendLocationMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* senderName :- Sender;s name.
* type :- The type of the message.
* latitude :- Latitude of the location
* longitude :- Longitude of the location.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

##### Sample request

following is a sample JSON request that can be handled by the sendLocationMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "John McClane",
    "type": "location",
    "latitute": "7.896224,",
    "longitude": "80.6736774",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data"
}
```
Given below is a sample response for the sendLocationMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52687556960755821,
    "chat_hostname": "SN-CHAT-27_"
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#location-message

#### Send  picture  message

   This method allow you to send a picture message.
   
**Validation :**<br></br>
   mediaUrlPicture -  Max size 1 MB. Only JPEG format is supported.
##### sendPictureMessage
```xml
      <viber.sendPictureMessage>
        <receiver>{$ctx:receiver}</receiver>
        <senderName>{$ctx:senderName}</senderName>
        <type>{$ctx:type}</type>
        <text>{$ctx:text}</text>
        <mediaUrlPicture>{$ctx:mediaUrlPicture}</mediaUrlPicture>
        <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
        <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
        <trackingData>{$ctx:trackingData}</trackingData>
        <thumbnailUrl>{$ctx:thumbnailUrl}</thumbnailUrl>
      </viber.sendPictureMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* senderName :- sender's name.
* type :- The type of the message.
* text :- Discription of the picture.
* mediaUrlPicture :-Url of the picture.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.
* thumbnailUrl :- Url of a reduced size image.

##### Sample request

following is a sample JSON request that can be handled by the sendPictureMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "John McClane",
    "type": "picture",
    "text": "Photo description",
    "mediaUrlPicture": "http://www.images.com/img.jpg",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "thumbnailUrl": "http://www.images.com/thumb.jpg"
}
```
Given below is a sample response for the sendPictureMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52687556965582411,
    "chat_hostname": "SN-CHAT-27_"
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#picture-message

#### Send  sticker  message

   This method allow you to send a sticker message.
   
##### sendStickerMessage

```xml
   <viber.sendStickerMessage>
      <receiver>{$ctx:receiver}</receiver>
      <senderName>{$ctx:senderName}</senderName>
      <type>{$ctx:type}</type>
      <stickerId>{$ctx:stickerId}</stickerId>
      <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
      <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
      <trackingData>{$ctx:trackingData}</trackingData>
   </viber.sendStickerMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* senderName :- sender's name.
* type :- The type of the message.
* stickerId :- Id of the sticker.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

##### Sample request

following is a sample JSON request that can be handled by the sendStickerMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "john kumar",
    "type": "sticker",
    "stickerId": 1225,
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data"
}
```
Given below is a sample response for the sendStickerMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52755696075582411,
    "chat_hostname": "SN-CHAT-27_"
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#sticker-message

#### Send  text message

   This method allow you to send a text message.
   
##### sendTextMessage

```xml
    <viber.sendTextMessage>
       <receiver>{$ctx:receiver}</receiver>
       <senderName>{$ctx:senderName}</senderName>
       <type>{$ctx:type}</type>
       <text>{$ctx:text}</text>
       <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
       <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
       <trackingData>{$ctx:trackingData}</trackingData>
    </viber.sendTextMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* senderName :- sender's name.
* type :- The type of the message.
* text :- Text of the message.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

##### Sample request

following is a sample JSON request that can be handled by the sendTextMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "John macklen",
    "type": "text",
    "text": "Hello world!",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data"
}
```
Given below is a sample response for the sendTextMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5268755696075582411,
    "chat_hostname": "SN-CHAT-27_"
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#text-message


#### Send url message

   This method allow you to send a url message.
   
   **Validation :**<br></br>
      mediaUrl -  Max 2,000 characters.

##### sendUrlMessage

```xml
    <viber.sendUrlMessage>
       <receiver>{$ctx:receiver}</receiver>
       <senderName>{$ctx:senderName}</senderName>
       <type>{$ctx:type}</type>
       <mediaUrl>{$ctx:mediaUrl}</mediaUrl>
       <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
       <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
       <trackingData>{$ctx:trackingData}</trackingData>
    </viber.sendUrlMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* senderName :- sender's name.
* type :- The type of the message.
* mediaUrl :- The url.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

###### Sample request

following is a sample JSON request that can be handled by the sendUrlMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "John macklen",
    "type": "url",
    "mediaUrl": "http://www.google.com",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data"
}
`````
Given below is a sample response for the sendUrlMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52687556075582411,
    "chat_hostname": "SN-CHAT-27_"
}
```
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#url-message

#### Send  video message

   This method allow you to send a video message.
   
   **Validation :**<br></br>
      mediaUrlVideo - Max size 50 MB. Only MP4 and H264 are supported.<br></br>
      duration - Max 180 seconds.

##### sendVideoMessage

```xml
    <viber.sendVideoMessage>
       <receiver>{$ctx:receiver}</receiver>
       <senderName>{$ctx:senderName}</senderName>
       <type>{$ctx:type}</type>
       <mediaUrlVideo>{$ctx:mediaUrlVideo}</mediaUrlVideo>
       <size>{$ctx:size}</size>
       <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
       <trackingData>{$ctx:trackingData}</trackingData>
       <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
       <thumbnailUrl>{$ctx:thumbnailUrl}</thumbnailUrl>
       <duration>{$ctx:duration}</duration>
    </viber.sendVideoMessage>
```

##### Properties

* receiver :- Unique Viber user Id.
* senderName :- sender's name.
* type :- The type of the message.
* size :- size of the video.
* mediaUrlVideo :- The url of the video.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.
* thumbnailUrl :- Url of a reduced size image.
* duration :- duration of the video.

##### Sample request

following is a sample JSON request that can be handled by the sendVideoMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "john alis",
    "type": "video",
    "mediaUrlVideo": "http://www.images.com/video.mp4",
    "size": 10000,
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "thumbnailUrl": "http://www.images.com/thumb.jpg",
    "duration": 60
}
````````
**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#video-messag

**Sample response**

Given below is a sample response for the sendVideoMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5268755696075582411,
    "chat_hostname": "SN-CHAT-27_"
}
```
## Sample configuration

Following example illustrates how to connect to Viber  with the init operation and sendVideoMessage operation.

 Sample proxy
 
 ````<?xml version="1.0" encoding="UTF-8"?>
     <proxy xmlns="http://ws.apache.org/ns/synapse"
            name="testSendVideoMessage"
            startOnLoad="true"
            statistics="disable"
            trace="disable"
            transports="http,https">
        <target>
           <inSequence>
              <property expression="json-eval($.appKey)" name="appKey"/>
              <property expression="json-eval($.apiUrl)" name="apiUrl"/>
              <property expression="json-eval($.receiver)" name="receiver"/>
              <property expression="json-eval($.senderName)" name="senderName"/>
              <property expression="json-eval($.type)" name="type"/>
              <property expression="json-eval($.mediaUrlVideo)" name="mediaUrlVideo"/>
              <property expression="json-eval($.size)" name="size"/>
              <property expression="json-eval($.senderAvatarUrl)" name="senderAvatarUrl"/>
              <property expression="json-eval($.trackingData)" name="trackingData"/>
              <property expression="json-eval($.minApiVersion)" name="minApiVersion"/>
              <property expression="json-eval($.thumbnailUrl)" name="thumbnailUrl"/>
              <property expression="json-eval($.duration)" name="duration"/>
              <viber.init>
                 <appKey>{$ctx:appKey}</appKey>
                 <apiUrl>{$ctx:apiUrl}</apiUrl>
              </viber.init>
              <viber.sendVideoMessage>
                 <receiver>{$ctx:receiver}</receiver>
                 <senderName>{$ctx:senderName}</senderName>
                 <type>{$ctx:type}</type>
                 <mediaUrlVideo>{$ctx:mediaUrlVideo}</mediaUrlVideo>
                 <size>{$ctx:size}</size>
                 <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
                 <trackingData>{$ctx:trackingData}</trackingData>
                 <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
                 <thumbnailUrl>{$ctx:thumbnailUrl}</thumbnailUrl>
                 <duration>{$ctx:duration}</duration>
              </viber.sendVideoMessage>
              <respond/>
           </inSequence>
           <outSequence/>
           <faultSequence/>
        </target>
        <description/>
     </proxy>                                   
```` 
2. Create a json file named sendVideoMessage.json and copy the configurations given below to it:
  
  ```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "senderName": "john alis",
    "type": "video",
    "size": 10000,
    "mediaUrlVideo": "http://www.images.com/video.mp4",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "thumbnailUrl": "http://www.images.com/thumb.jpg",
    "duration": 60
}
  ```
  3. Replace the credentials with your values.
  
  4. Execute the following curl command:
  
  ```bash
  $ curl --include --request POST --url 'http://sarangika-ThinkPad-T530:8280/services/testSendVideoMessage' --header 'Content-Type: application/json" -d @sendVideoMessage.json
  ```
  5. Viber returns a json response similar to the one shown below:
  ``````
"{"status":0,"status_message":"ok","message_token":5270000620594975192,"chat_hostname":"SN-CHAT-37_"}