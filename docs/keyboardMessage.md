# Working with Keyboard Message

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration) ]


## Overview

The following operations allow you to send a keyboard attaching to any message type.The keyboard supply the user with a
set of predefined replies or actions.

Validation - Maximum total JSON size of the request is 30kb.

 Click an operation name to see details on how to use it.

**Related Viber documentation**
https://developers.viber.com/docs/api/rest-bot-api/#keyboards


| Operation | Description |
| ------------- |-------------|
| [sendKeyboardCarouselContent](#Send-keyboard-with-carousel-content-message) |  Send  a keyboard with carousel content message.|
| [sendKeyboardContact](#Send-keyboard-with-contact-message) | Send a keyboard with contact message.|
| [sendKeyboardFile](#Send-keyboard-with-file-message) |Send a keyboard with file message.|
| [sendKeyboardLocation](#Send-keyboard-with-location-Message) |Send a keyboard with location message.|
| [sendKeyboardPicture](#Send-keyboard-with-picture-message) |Send a keyboard with picture message.|
| [sendKeyboardSticker](#Send-keyboard-with-sticker-message) |Send a keyboard with sticker message.|
| [sendKeyboardText](#Send-keyboard-with-text-message) |Send a keyboard with text message.|
| [sendKeyboardUrl](#Send-keyboard-with-url-message) |Send a keyboard with url message.|
| [sendKeyboardVideo](#Send-keyboard-with-video-message) |Send a keyboard with video message.|


## Operation details

This section provides details on each of the operations.


#### Send keyboard with carousel content message

This method allow you to send a keyboard with Carousel Content Message.

##### sendKeyboardCarouselContent

```xml
     <viber.sendKeyboardCarouselContent>
        <receiver>{$ctx:receiver}</receiver>
        <type>{$ctx:type}</type>
        <richMediaButtons>{$ctx:richMediaButtons}</richMediaButtons>
        <buttons>{$ctx:buttons}</buttons>
        <bgColor>{$ctx:bgColor}</bgColor>
        <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
        <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
        <backGroundColor>{$ctx:backGroundColor}</backGroundColor>
        <buttonsGroupRows>{$ctx:buttonsGroupRows}</buttonsGroupRows>
        <buttonsGroupColumns>{$ctx:buttonsGroupColumns}</buttonsGroupColumns>
     </viber.sendKeyboardCarouselContent>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* richMediaButtons :- Array of rich media buttons.
* buttons :- Array of buttons.
* backGroundColor :- Color of the background.
* buttonsGroupColumns :- Number of rows per carousel content block.
* buttonsGroupRows :- Number of columns per carousel content block.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.


##### Sample request

following is a sample JSON request that can be handled by the  sendKeyboardCarouselContent operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "rich_media",
    "backGroundColor": "#FF0000",
    "buttonsGroupRows": 7,
    "buttonsGroupColumns": 6,
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "richMediaButtons": [
        {
            "ActionBody": "https://www.google.com",
            "ActionType": "open-url",
            "Text": "hai all"
        },
        {
            "ActionBody": "https://www.google.com",
            "ActionType": "open-url",
            "Text": "Hello world"
        }
    ],
    "buttons": [
        {
            "ActionType": "reply",
            "ActionBody": "reply to me",
            "Text": "Key text",
            "TextSize": "regular"
        }
    ]
}
```
**Sample response**

Given below is a sample response for the sendKeyboardCarouselContent operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52699926104738692,
    "chat_hostname": "SN-CHAT-25_"
}
```

#### Send keyboard with contact message

This method allow you to send a keyboard with contact message.

**Validation :**<br></br>
   contactName - Max 28 characters.<br></br>
   contactNumber - Max 18 characters.

##### sendKeyboardContact

```xml
   <viber.sendKeyboardContact>
     <receiver>{$ctx:receiver}</receiver>
     <type>{$ctx:type}</type>
     <contactName>{$ctx:contactName}</contactName>
     <contactNumber>{$ctx:contactNumber}</contactNumber>
     <buttons>{$ctx:buttons}</buttons>
     <bgColor>{$ctx:bgColor}</bgColor>
     <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
     <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
   </viber.sendKeyboardContact>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* contactName :- Name of the contact.
* contactNumber :- Number of the contact.
* buttons :- Array of buttons.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.

##### Sample request

following is a sample JSON request that can be handled by the sendKeyboardContact operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "contact",
    "contactName": "malki",
    "contactNumber": "028899847484",
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "buttons": [
        {
            "ActionType": "reply",
            "ActionBody": "reply to me",
            "Text": "Key text",
            "TextSize": "regular"
        }
    ]
}
```
**Sample response**

Given below is a sample response for the keyboardContactMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52699926104738692,
    "chat_hostname": "SN-CHAT-25_"
}
```

#### Send keyboard with file  message

   This method allow you to send a keyboard with file  message.
   
**Validation :**<br></br>
   mediaUrlFile -  Max size 50 MB.<br></br>
   fileName -  File name should include extension. Max 256 characters.

##### sendKeyboardFile

```xml
   <viber.sendKeyboardFile>
     <receiver>{$ctx:receiver}</receiver>
     <type>{$ctx:type}</type>
     <mediaUrlFile>{$ctx:mediaUrlFile}</mediaUrlFile>
     <size>{$ctx:size}</size>
     <fileName>{$ctx:fileName}</fileName>
     <buttons>{$ctx:buttons}</buttons>
     <bgColor>{$ctx:bgColor}</bgColor>
     <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
     <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
   </viber.sendKeyboardFile>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* mediaUrlFile :- The url of the file.
* size :- Size of the file.
* fileName :- Name of the file.
* buttons :- Array of buttons.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.

##### Sample request

following is a sample JSON request that can be handled by the sendKeyboardFile operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "file",
    "mediaUrlFile": "http://www.images.com/file.doc",
    "size": 100,
    "fileName": "name_of_file.doc",
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "buttons": [
        {
            "ActionType": "reply",
            "ActionBody": "reply to me",
            "Text": "Key text",
            "TextSize": "regular"
        }
    ]
}
```
**Sample response**

Given below is a sample response for the keyboardFileMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52692626104738692,
    "chat_hostname": "SN-CHAT-25_"
}
```
#### Send keyboard with location  message

   This method allow you to send a keyboard with location message.
   
**Validation :**<br></br>
   latitude -  (±90°) within valid ranges<br></br>
   longitude -  (±180°) within valid ranges).
##### sendKeyboardLocation

```xml
  <viber.sendKeyboardLocation>
     <receiver>{$ctx:receiver}</receiver>
     <type>{$ctx:type}</type>
     <latitude>{$ctx:latitude}</latitude>
     <longitude>{$ctx:longitude}</longitude>
     <buttons>{$ctx:buttons}</buttons>
     <bgColor>{$ctx:bgColor}</bgColor>
     <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
     <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
  </viber.sendKeyboardLocation>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* latitude :- Latitude of the location
* longitude :- Longitude of the location.
* buttons :- array of buttons.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.

##### Sample request

following is a sample JSON request that can be handled by the sendKeyboardLocation operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "location",
    "latitude": "76.987",
    "longitude": "-122.89",
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "buttons": [
        {
            "ActionType": "reply",
            "ActionBody": "reply to me",
            "Text": "Key text",
            "TextSize": "regular"
        }
    ]
}
```
**Sample response**

Given below is a sample response for the keyboardLocationMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 69992626104738692,
    "chat_hostname": "SN-CHAT-25_"
}
```
#### Send keyboard with picture  message

   This method allow you to send a keyboard with picture message.
   
**Validation :**<br></br>
   mediaUrlPicture -  Max size 1 MB. Only JPEG format is supported.

##### sendKeyboardPicture

```xml
  <viber.sendKeyboardPicture>
     <receiver>{$ctx:receiver}</receiver>
     <text>{$ctx:text}</text>
     <type>{$ctx:type}</type>
     <mediaUrlPicture>{$ctx:mediaUrlPicture}</mediaUrlPicture>
     <buttons>{$ctx:buttons}</buttons>
     <bgColor>{$ctx:bgColor}</bgColor>
     <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
     <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
  </viber.sendKeyboardPicture>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* text :- Discription of the picture.
* mediaUrlPicture :-Url of the picture.
* buttons :- Array of buttons.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.

##### Sample request

following is a sample JSON request that can be handled by the sendKeyboardPicture operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "picture",
    "text": "discription",
    "mediaUrlPicture": "http://www.images.com/img.jpg",
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "buttons": [
        {
            "ActionType": "reply",
            "ActionBody": "reply to me",
            "Text": "Key text",
            "TextSize": "regular"
        }
    ]
}
```
**Sample response**

Given below is a sample response for the keyboardPictureMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 52692626104738692,
    "chat_hostname": "SN-CHAT-25_"
}
```

#### Send keyboard with sticker  message

   This method allow you to send a keyboard with sticker message


##### sendKeyboardSticker

```xml
   <viber.sendKeyboardSticker>
      <receiver>{$ctx:receiver}</receiver>
      <type>{$ctx:type}</type>
      <stickerId>{$ctx:stickerId}</stickerId>
      <buttons>{$ctx:buttons}</buttons>
      <bgColor>{$ctx:bgColor}</bgColor>
      <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
      <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
   </viber.sendKeyboardSticker>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* stickerId :- Id of the sticker.
* buttons :- Array of buttons.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.

##### Sample request

following is a sample JSON request that can be handled by the sendKeyboardSticker operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "sticker",
    "BgColor": "#FFFFFF",
    "stickerId": 1036,
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "buttons": [
        {
            "ActionBody": "www.tut.by",
            "Image": "www.tut.by/img.jpg",
            "Text": "Key text",
            "TextVAlign": "middle",
            "TextHAlign": "center",
            "TextOpacity": 60,
            "TextSize": "regular"
        }
    ]
}
```
**Sample response**

Given below is a sample response for the keyboardStickerMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5269992604738692,
    "chat_hostname": "SN-CHAT-25_"
}
```
#### Send keyboard with text message

   This method allow you to send a keyboard with text message.


##### sendKeyboardText

```xml
  <viber.sendKeyboardText>
     <receiver>{$ctx:receiver}</receiver>
     <text>{$ctx:text}</text>
     <type>{$ctx:type}</type>
     <buttons>{$ctx:buttons}</buttons>
     <bgColor>{$ctx:bgColor}</bgColor>
     <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
     <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
  </viber.sendKeyboardText>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* text :- Text of the message.
* buttons :- Array of buttons.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.

##### Sample request

following is a sample JSON request that can be handled by the sendKeyboardText operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "text",
    "text": "hello",
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
     "buttons": [
          {
              "ActionBody": "www.tut.by",
              "Image": "www.tut.by/img.jpg",
              "Text": "Key text",
              "TextVAlign": "middle",
              "TextHAlign": "center",
              "TextOpacity": 60,
              "TextSize": "regular"
          }
      ]
}
```
**Sample response**

Given below is a sample response for the keyboardTextMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5269992626104738692,
    "chat_hostname": "SN-CHAT-25_"
}
```

#### Send keyboard with url message

   This method allow you to send a keyboard with url message.
   
**Validation :**<br></br>
   mediaUrl -  Max 2,000 characters.

##### sendKeyboardUrl

```xml
     <viber.sendKeyboardUrl>
       <receiver>{$ctx:receiver}</receiver>
       <buttons>{$ctx:buttons}</buttons>
       <type>{$ctx:type}</type>
       <mediaUrl>{$ctx:mediaUrl}</mediaUrl>
       <bgColor>{$ctx:bgColor}</bgColor>
       <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
       <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
     </viber.sendKeyboardUrl>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* mediaUrl :- The url.
* buttons :- Array of buttons.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.

##### Sample request

following is a sample JSON request that can be handled by the sendKeyboardUrl operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "picture",
    "mediaUrl": "http://www.website.com/go_here",
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "buttons": [
        {
            "ActionType": "reply",
            "ActionBody": "reply to me",
            "Text": "Key text",
            "TextSize": "regular"
        }
    ]
}
````
**Sample response**

Given below is a sample response for the keyboardUrlMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 526999262610492,
    "chat_hostname": "SN-CHAT-25_"
}
```
#### Send keyboard with Vvdeo message

   This method allow you to send a keyboard with video message.
   
**Validation :**<br></br>
   mediaUrlVideo - Max size 50 MB. Only MP4 and H264 are supported.<br></br>
   duration - Max 180 seconds.

##### sendKeyboardVideo

```xml
   <viber.sendKeyboardVideo>
      <receiver>{$ctx:receiver}</receiver>
      <buttons>{$ctx:buttons}</buttons>
      <type>{$ctx:type}</type>
      <mediaUrlVideo>{$ctx:mediaUrlVideo}</mediaUrlVideo>
      <size>{$ctx:size}</size>
      <bgColor>{$ctx:bgColor}</bgColor>
      <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
      <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
   </viber.sendKeyboardVideo>
```

##### Properties

* receiver :- Unique Viber user Id.
* type :- The type of the message.
* size :- size of the video.
* mediaUrlVideo :- The url of the video.
* buttons :- Array of buttons.
* bgColor :- Background color of the keyboard.
* defaultHeight :- Maximal height will be native keyboard height.
* customDefaultHeigh :- How much percent of free screen space in chat should be taken by keyboard.

##### Sample request

following is a sample JSON request that can be handled by the sendKeyboardVideo operation.
```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "video",
    "mediaUrlVideo": "http://www.images.com/video.mp4",
    "size": 1000,
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "buttons": [
        {
            "ActionType": "reply",
            "ActionBody": "reply to me",
            "Text": "Key text",
            "TextSize": "regular"
        }
    ]
}
```
**Sample response**

Given below is a sample response for the keyboardVideoMessage operation.

```json
{
    "status": 0,
    "status_message": "ok",
    "message_token": 5269992626104738692,
    "chat_hostname": "SN-CHAT-25_"
}
```

### Sample configuration

Following example illustrates how to connect to Viber  with the init operation and sendKeyboardVideo operation.

 Sample proxy

   ````
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="sendVideoKeyboardMessage"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.appKey)" name="appKey"/>
         <property expression="json-eval($.apiUrl)" name="apiUrl"/>
         <property expression="json-eval($.receiver)" name="receiver"/>
         <property expression="json-eval($.type)" name="type"/>
         <property expression="json-eval($.mediaUrlVideo)" name="mediaUrlVideo"/>
         <property expression="json-eval($.size)" name="size"/>
         <property expression="json-eval($.buttons)" name="buttons"/>
         <property expression="json-eval($.bgColor)" name="bgColor"/>
         <property expression="json-eval($.defaultHeight)" name="defaultHeight"/>
         <property expression="json-eval($.customDefaultHeigh)" name="customDefaultHeigh"/>
         <viber.init>
            <appKey>{$ctx:appKey}</appKey>
            <apiUrl>{$ctx:apiUrl}</apiUrl>
         </viber.init>
         <viber.sendKeyboardVideo>
            <receiver>{$ctx:receiver}</receiver>
            <type>{$ctx:type}</type>
            <mediaUrlVideo>{$ctx:mediaUrlVideo}</mediaUrlVideo>
            <size>{$ctx:size}</size>
            <buttons>{$ctx:buttons}</buttons>
            <bgColor>{$ctx:bgColor}</bgColor>
            <defaultHeight>{$ctx:defaultHeight}</defaultHeight>
            <customDefaultHeigh>{$ctx:customDefaultHeigh}</customDefaultHeigh>
         </viber.sendKeyboardVideo>
         <respond/>
      </inSequence>
      <outSequence/>
      <faultSequence/>
   </target>
   <description/>
</proxy>
                                
````
2. Create a json file named keyboardVideo.json and copy the configurations given below to it:
  
  ```json
{
    "apiUrl": "https://chatapi.viber.com",
    "appKey": "48e9e906bf27d7bd-97e6c6eb11a4effa-255ca5dfb8545d80",
    "receiver": "E4Ux8N0j3HcXEbrzF+nK5Q==",
    "type": "video",
    "mediaUrlVideo": "http://www.images.com/video.mp4",
    "size": 1000,
    "bgColor": "#2db9b9",
    "defaultHeight": true,
    "customDefaultHeigh": 50,
    "buttons": [
        {
            "ActionType": "reply",
            "ActionBody": "reply to me",
            "Text": "Key text",
            "TextSize": "regular"
        }
    ]
}
  ```
  3. Replace the credentials with your values.
  
  4. Execute the following curl command:
  
  ```bash
  $ curl --include --request POST --url 'http://sarangika-ThinkPad-T530:8280/services/sendVideoKeyboardMessage' --header 'Content-Type: application/json" -d @sendKeyboardVideo.json
  ```
  5. Viber returns a json response similar to the one shown below:
  ``````
"{"status":0,"status_message":"ok","message_token":5283350555147023152,"chat_hostname":"SN-CHAT-29_"}