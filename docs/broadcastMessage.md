# Working with Broadcast Message

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration) ]


## Overview

The following operations allow you to broadcast a message.

Validation - Maximum total JSON size of the request is 30kb. The maximum list length is 300 receivers. 

Click an operation name to see details on how to use it.

**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#broadcast-message


| Operation | Description |
| ------------- |-------------|
| [broadcastCarouselContentMessage](#Broadcast-carousel-content-message)|Broadcast a carousel content message.|
| [broadcastContactMessage](#Broadcast-contact-message) | Broadcast a contact.|
| [broadcastFileMessage](#Broadcast-file-message) | Broadcast a file.|
| [broadcastLocationMessage](#Broadcast-location-message) |Broadcast a location.|
| [broadcastPictureMessage](#Broadcast-picture-message) |Broadcast a picture.|
| [broadcastStickerMessage](#Broadcast-sticker-message) | Broadcast a sticker.|
| [broadcastTextMessage](#Broadcast-text-message) | Broadcast a text message.|
| [broadcastUrlMessage](#Broadcast-url-message) | Broadcast a url.|
| [broadcastVideoMessage](#Broadcast-video-message) |Broadcast a video.|


## Operation details

This section provides details on each of the operations.



#### Broadcast carousel content message

This method allow you to send Carousel Content messages to multiple viber users who subscribe to the account.


##### broadcastCarouselContentMessage

```xml
    <viber.broadcastCarouselContentMessage>
       <ids>{$ctx:ids}</ids>
       <senderName>{$ctx:senderName}</senderName>
       <type>{$ctx:type}</type>
       <richMediaButtons>{$ctx:richMediaButtons}</richMediaButtons>
       <backGroundColor>{$ctx:backGroundColor}</backGroundColor>
       <buttonsGroupColumns>{$ctx:buttonsGroupColumns}</buttonsGroupColumns>
       <buttonsGroupRows>{$ctx:buttonsGroupRows}</buttonsGroupRows>
    </viber.broadcastCarouselContentMessage>
```



##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* richMediaButtons :- Array of rich media buttons.
* backGroundColor :- Color of the background.
* buttonsGroupColumns :- Number of rows per carousel content block.
* buttonsGroupRows :- Number of columns per carousel content block.


##### Sample request

following is a sample JSON request that can be handled by the broadcastCarouselContentMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97b11a4effa-255ca5dfb8545d80",
    "senderName": "martin lutar",
    "backGroundColor":"#2db9b9",
    "buttonsGroupRows":6,
    "buttonsGroupColumns":6,
    "type": "rich_media",
    "richMediaButtons": [
        {
            "ActionBody": "https://www.google.com",
            "ActionType": "open-url",
            "Text": "Hello world"
        },
        {
            "ActionBody": "https://www.google.com",
            "ActionType": "open-url",
            "Text": "hai all"
        }
    ],
    "ids": [
        "E4Ux8N0j3HcXnK5Q==",
        "3524386515ro12078",
        "hur36r735e6214879u9"
    ]
}
```
**Sample response**

Given below is a sample response for the broadcastCarouselContentMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 528329699203523147,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```


#### Broadcast contact message

This method allow you to send contact details to multiple viber users who subscribe to the account.

**Validation :**<br></br>
   contactName - Max 28 characters.<br></br>
   contactNumber - Max 18 characters.

##### broadcastContactMessage

```xml
   <viber.broadcastContactMessage>
      <ids>{$ctx:ids}</ids>
      <senderName>{$ctx:senderName}</senderName>
      <type>{$ctx:type}</type>
      <contactName>{$ctx:contactName}</contactName>
      <contactNumber>{$ctx:contactNumber}</contactNumber>
      <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
      <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
      <trackingData>{$ctx:trackingData}</trackingData>
   </viber.broadcastContactMessage>
```

##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* contactName :- Name of the contact.
* contactNumber :- Number of the contact.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.


##### Sample request

following is a sample JSON request that can be handled by the broadcastContactMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97b11a4effa-255ca5dfb8545d80",
    "senderName": "john maclane",
    "type": "contact",
    "contactName": "den mark",
    "contactNumber": "071093663773",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
     "ids": [
           "E4Ux8N0j3HcXEb+nK5Q==",
           "3524386515ro12078",
           "hur36r735e6214879u9"
       ]
}
```
**Sample response**

Given below is a sample response for the broadcastContactMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 528329699203527314,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```
#### Broadcast file message

This method allow you to send a file to multiple viber users who subscribe to the account.

**Validation :**<br></br>
   mediaUrlFile -  Max size 50 MB.<br></br>
   fileName -  File name should include extension. Max 256 characters.
##### broadcastFileMessage

```xml
    <viber.broadcastFileMessage>
       <ids>{$ctx:ids}</ids>
       <senderName>{$ctx:senderName}</senderName>
       <type>{$ctx:type}</type>
       <mediaUrlFile>{$ctx:mediaUrlFile}</mediaUrlFile>
       <size>{$ctx:size}</size>
       <fileName>{$ctx:fileName}</fileName>
       <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
       <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
       <trackingData>{$ctx:trackingData}</trackingData>
    </viber.broadcastFileMessage>
```

##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* mediaUrlFile :- The url of the file.
* size :- Size of the file.
* fileName :- Name of the file.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.



##### Sample request

following is a sample JSON request that can be handled by the broadcastFiletMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97b11a4effa-255ca5dfb8545d80",
    "senderName": "john maclane",
    "type": "file",
    "mediaUrlFile": "http://www.images.com/file.doc",
    "size": 1000,
    "fileName": "name_of_file.doc",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "ids": [
          "E4Ux8N0j3HcXzF+nK5Q==",
          "3524386515ro12078",
          "hur36r735e6214879u9"
      ]
}
```
**Sample response**

Given below is a sample response for the broadcastFileMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 528329492035273147,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```
#### Broadcast location message

This method allow you to send a location details  to multiple viber users who subscribe to the account.

**Validation :**<br></br>
   latitude -  (±90°) within valid ranges<br></br>
   longitude -  (±180°) within valid ranges).
##### broadcastLocationMessage

```xml
    <viber.broadcastLocationMessage>
      <senderName>{$ctx:senderName}</senderName>
      <ids>{$ctx:ids}</ids>
      <type>{$ctx:type}</type>
      <latitude>{$ctx:latitude}</latitude>
      <longitude>{$ctx:longitude}</longitude>
      <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
      <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
      <trackingData>{$ctx:trackingData}</trackingData>
    </viber.broadcastLocationMessage>
```

##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* latitude :- Latitude of the location
* longitude :- Longitude of the location.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

##### Sample request

following is a sample JSON request that can be handled by the broadcastLocationtMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97b11a4effa-255ca5dfb8545d80",
    "senderName": "john maclane",
    "type": "location",
    "latitude": "87.45",
    "longitude": "-122.345",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "ids": [
          "E4Ux8N0j3HcrzF+nK5Q==",
          "3524386515ro12078",
          "hur36r735e6214879u9"
      ]
}
```
**Sample response**

Given below is a sample response for the broadcastLocationMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52832969925273147,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```
#### Broadcast picture message

This method allow you to send a picture to multiple viber users who subscribe to the account.

**Validation :**<br></br>
   mediaUrlPicture -  Max size 1 MB. Only JPEG format is supported.

##### broadcastPictureMessage

```xml
      <viber.broadcastPictureMessage>
        <senderName>{$ctx:senderName}</senderName>
        <ids>{$ctx:ids}</ids>
        <text>{$ctx:text}</text>
        <type>{$ctx:type}</type>
        <mediaUrlPicture>{$ctx:mediaUrlPicture}</mediaUrlPicture>
        <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
        <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
        <trackingData>{$ctx:trackingData}</trackingData>
        <thumbnailUrl>{$ctx:thumbnailUrl}</thumbnailUrl>
      </viber.broadcastPictureMessage>
```

##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* text :- Discription of the picture.
* mediaUrlPicture :-Url of the picture.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.
* thumbnailUrl :- Url of a reduced size image.

##### Sample request

following is a sample JSON request that can be handled by the broadcastPictureMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97b11a4effa-255ca5dfb8545d80",
    "senderName": "malki sandamini",
    "type": "picture",
    "text": "my photo",
    "mediaUrlPicture": "http://www.images.com/img.jpg",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "thumbnailUrl": "http://www.images.com/thumb.jpg",
    "ids": [
          "E4Ux8N0j3HcXzF+nK5Q==",
          "3524386515ro12078",
          "hur36r735e6214879u9"
      ]
}
```
**Sample response**

Given below is a sample response for the broadcastPictureMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52832969035273145,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```

#### Broadcast sticker message

This method allow you to send a sticker to multiple viber users who subscribe to the account.


##### broadcastStickerMessage

```xml
  <viber.broadcastStickerMessage>
     <ids>{$ctx:ids}</ids>
     <senderName>{$ctx:senderName}</senderName>
     <type>{$ctx:type}</type>
     <stickerId>{$ctx:stickerId}</stickerId>
     <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
     <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
     <trackingData>{$ctx:trackingData}</trackingData>
  </viber.broadcastStickerMessage>
```
##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* stickerId :- Id of the sticker.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

##### Sample request

following is a sample JSON request that can be handled by the broadcastStickerMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97b11a4effa-255ca5dfb8545d80",
    "senderName": "john dias",
    "type": "sticker",
    "stickerId": 46102,
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "ids": [
          "E4Ux8N0j3HcrzF+nK5Q==",
          "3524386515ro12078",
          "hur36r735e6214879u9"
      ]
}
```
**Sample response**

Given below is a sample response for the broadcastStickerMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5283296992035273,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```
#### Broadcast text message

This method allow you to send a text to multiple viber users who subscribe to the account.


##### broadcastTextMessage

```xml
  <viber.broadcastTextMessage>
    <ids>{$ctx:ids}</ids>
    <senderName>{$ctx:senderName}</senderName>
    <text>{$ctx:text}</text>
    <type>{$ctx:type}</type>
    <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
    <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
    <trackingData>{$ctx:trackingData}</trackingData>
  </viber.broadcastTextMessage>
```

##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* text :- Text of the message.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

##### Sample request

following is a sample JSON request that can be handled by the broadcastTextMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97b11a4effa-255ca5dfb8545d80",
    "senderName": "martin lutar",
    "type": "text",
    "text": "hello",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "ids": [
          "E4Ux8N0j3HcXEbF+nK5Q==",
          "3524386515ro12078",
          "hur36r735e6214879u9"
      ]
}
```
**Sample response**

Given below is a sample response for the broadcastTextMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5283296992073147,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```

#### Broadcast url message

This method allow you to send a url to multiple viber users who subscribe to the account.

**Validation :**<br></br>
   mediaUrl -  Max 2,000 characters.
##### broadcastUrlMessage

```xml
  <viber.broadcastUrlMessage>
    <ids>{$ctx:idsr}</ids>
    <senderName>{$ctx:senderName}</senderName>
    <type>{$ctx:type}</type>
    <mediaUrl>{$ctx:mediaUrl}</mediaUrl>
    <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
    <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
    <trackingData>{$ctx:trackingData}</trackingData>
  </viber.broadcastUrlMessage>
```

##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* mediaUrl :- The url.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.

##### Sample request

following is a sample JSON request that can be handled by the broadcastUrlMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97ba4effa-255ca5dfb8545d80",
    "senderName": "john alis",
    "type": "url",
    "mediaUrl": "http://www.website.com/go_here",
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
     "ids": [
           "E4Ux8N0j3brF+nK5Q==",
           "3524386515ro12078",
           "hur36r735e6214879u9"
       ]
}
```
**Sample response**

Given below is a sample response for the broadcastUrlMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52832962035273432,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```

### Broadcast video message

This method allow you to send a video to multiple Viber users who subscribe to the account.

**Validation :**<br></br>
   mediaUrlVideo - Max size 50 MB. Only MP4 and H264 are supported.<br></br>
   duration - Max 180 seconds.
   
##### broadcastVideoMessage

```xml
   <viber.broadcastVideoMessage>
     <ids>{$ctx:ids}</ids>
     <senderName>{$ctx:senderName}</senderName>
     <size>{$ctx:size}</size>
     <type>{$ctx:type}</type>
     <mediaUrlVideo>{$ctx:mediaUrlVideo}</mediaUrlVideo>
     <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
     <trackingData>{$ctx:trackingData}</trackingData>
     <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
     <thumbnailUrl>{$ctx:thumbnailUrl}</thumbnailUrl>
     <duration>{$ctx:duration}</duration>
   </viber.broadcastVideoMessage>
```

##### Properties

* ids :- Unique Viber user Ids.
* senderName :- The sender's name.
* type :- The type of the message.
* size :- size of the video.
* mediaUrlVideo :- The url of the video.
* minApiVersion :- Minimal API version required by clients for this message.
* senderAvatarUrl :- The sender’s avatar Url.
* trackingData :- Allow the account to track messages and user’s replies.
* thumbnailUrl :- Url of a reduced size image.
* duration :- duration of the video.

##### Sample request

following is a sample JSON request that can be handled by the broadcastVideoMessage operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97ba4effa-255ca5dfb8545d80",
    "senderName": "martin lutar",
    "type": "video",
    "mediaUrlVideo": "http://www.images.com/video.mp4",
    "size": 1000,
    "minApiVersion": 1,
    "senderAvatarUrl": "http://avatar.example.com",
    "trackingData": "tracking data",
    "thumbnailUrl": "http://www.images.com/thumb.jpg",
    "duration": 60,
    "ids": [
          "E4Ux8N0j3brzF+nK5Q==",
          "3524386515ro12078",
          "hur36r735e6214879u9"
      ]
}
```
**Sample response**

Given below is a sample response for the broadcastVideoMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 528329699203547,
    "failed_list": [
        {
            "receiver": "3524386515ro12078",
            "status": 5,
            "status_message": "Not found"
        },
        {
            "receiver": "hur36r735e6214879u9",
            "status": 5,
            "status_message": "Not found"
        }
    ],
    "chat_hostname": "SN-CHAT-47_"
}
```

## Sample configuration

Following example illustrates how to connect to Viber  with the init operation and broadcastVideoMessage operation.

 Sample proxy

   ``````
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="testVideobroadcastMessage"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.appKey)" name="appKey"/>
         <property expression="json-eval($.apiUrl)" name="apiUrl"/>
         <property expression="json-eval($.ids)" name="ids"/>
         <property expression="json-eval($.senderName)" name="senderName"/>
         <property expression="json-eval($.size)" name="size"/>
         <property expression="json-eval($.type)" name="type"/>
         <property expression="json-eval($.mediaUrlVideo)" name="mediaUrlVideo"/>
         <property expression="json-eval($.senderAvatarUrl)" name="senderAvatarUrl"/>
         <property expression="json-eval($.trackingData)" name="trackingData"/>
         <property expression="json-eval($.minApiVersion)" name="minApiVersion"/>
         <property expression="json-eval($.thumbnailUrl)" name="thumbnailUrl"/>
         <property expression="json-eval($.duration)" name="duration"/>
         <viber.init>
            <appKey>{$ctx:appKey}</appKey>
            <apiUrl>{$ctx:apiUrl}</apiUrl>
         </viber.init>
         <viber.broadcastVideoMessage>
            <ids>{$ctx:ids}</ids>
            <senderName>{$ctx:senderName}</senderName>
            <size>{$ctx:size}</size>
            <type>{$ctx:type}</type>
            <mediaUrlVideo>{$ctx:mediaUrlVideo}</mediaUrlVideo>
            <senderAvatarUrl>{$ctx:senderAvatarUrl}</senderAvatarUrl>
            <trackingData>{$ctx:trackingData}</trackingData>
            <minApiVersion>{$ctx:minApiVersion}</minApiVersion>
            <thumbnailUrl>{$ctx:thumbnailUrl}</thumbnailUrl>
            <duration>{$ctx:duration}</duration>
         </viber.broadcastVideoMessage>
         <respond/>
      </inSequence>
      <outSequence/>
      <faultSequence/>
   </target>
   <description/>
</proxy>                             
```````
2. Create a json file named broadcastVideo.json and copy the configurations given below to it:
  
  ```json
 {
     "apiUrl": "https://chatapi.viber.com",
     "appKey": "48e9e906bf27d7bd-97ba4effa-255ca5dfb8545d80",
     "senderName": "john",
     "type": "video",
     "mediaUrlVideo": "http://www.images.com/video.mp4",
     "size": 1000,
     "minApiVersion": 1,
     "senderAvatarUrl": "http://avatar.example.com",
     "trackingData": "tracking data",
     "thumbnailUrl": "http://www.images.com/thumb.jpg",
     "duration": 60,
     "ids": [
         "E4Ux8N0j3HczF+nK5Q==",
         "8iGPPECtQNw6KzwG1rnQQg==",
         "/8v+V5APa/jLsiywMa1HOA=="
     ]
 }
  ```
  3. Replace the credentials with your values.
  
  4. Execute the following curl command:
  
  ```bash
  $ curl  --include   --request POST  --url 'http://sarangika-ThinkPad-T530:8280/services/testVideobroadcastMessage  --header 'Content-Type: application/json'  -d @broadcastVideoMessage.json
  ```
  5. Viber returns a json response similar to the one shown below:
  ``````
{"status":0,"status_message":"ok","message_token":5283323893449643777,"failed_list":[{"receiver":"/8v+V5APa/jLsiywMa1HOA==","status":6,"status_message":"Not subscribed"}],"chat_hostname":"SN-CHAT-06_"}