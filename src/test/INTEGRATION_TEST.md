##Integration tests for WSO2 ESB Viber connector

#####Pre-requisites:

    - Maven 3.x
    - Java 1.8
    

#####Tested Platform:

    - Ubuntu 16.04
    - WSO2 EI 6.4.0
    - Java 1.8
##### Steps to follow in setting the Viber public account and derive the user id.


1. To work with the Viber connector, you need to have a Viber account. If you do not have a Viber account, [installing the Viber software](https://www.viber.com/download/) and create a [Viber public  account](https://viber.github.io/docs/general/get-started/) and derive the app key.

       The app key is generated upon public account creation and can be viewed by the account’s admin in the “edit info” screen of public account.

2.Before an account can send messages to a user, the user will need to subscribe to the account.Subscribing can take place in one of two ways:
<br></br>

     i) when a user sends it's first message to public account the user will be automatically subscribed to the account.
    ii) By pressing the subscribe button user can subscribed to the account.
3.By running accountInfo method you can derive the user's id who are subscribed to the public account  and user's name, admin's avatar also.
#####Steps to follow in setting integration test.
1. Download EI 6.4.0  by navigating to the following [URL](http://wso2.com/products/enterprise-service-bus/#).

2. Copy the EI 6.4.0 zip to the location `Connector_Home/repository/`.

3. Import the valid certificate to the  file system as follows.

    i) navigate to https://chatapi.viber.com/ in your browser, and click the HTTPS trust icon on the address bar.

    ii) View the certificate details  and then export the trust certificate to the location `Connector_Home/repository/.
    
4. Create two users (user1, user2) as described above(Steps to follow in setting the Viber public account and derive the
 user id).

5. Update the 'esb-connector-viber.properties' file at the location "{VIBER_CONNECTOR_HOME}/repository" as below .
  
     i)    apiUrl           -   Api URL for Viber.
     ii)   appKey           -   Account authentication token.

 6. Update the Viber properties file at location `<Connector_Home>/src/test/resources/artifacts/ESB/connector/config` as below.
      <br/></br>
      
 | Properties | Description  |
 |-----------|----------------|
 |text          |  The text of the message.| 
 |pictureText  | Discription of the picture. |
 |minApiVersion |  Minimal API version required by clients for the message.|
 |trackingData  |  Allow the account to track messages and user’s replies.|
 |mediaUrlPicture | The url of the image (Max size 1 MB. Only JPEG format is supported).|
 |mediaUrlVideo  |  The url of the video (Max size 50 MB. Only MP4 and H264 are supported).|
 |thumbnailUrl  | Url of a reduced size image(Max size 100 kb.Only JPEG format is supported).|
 |mediaUrlFile   | The url of the file.|
 |mediaUrl     |  The url (Max 2,000 characters).|
 |duration  |  Duration of the video (Max 180 seconds).|
 |size      |  Size of the video in bytes.|
 |fileSize | Size of the file in bytes.|
 |contactNumber | Contact phone number (Max 18 characters).|
 |contactName | Name of the contact (Max 28 characters).|
 |fileName  | Name of the file (File name should include extension. Max 256 characters).|
 |latitude  | Latitude of location ((±90°) within valid ranges).|
 |longitude  | Longitude of location ((±180°) within valid ranges).|
 |stickerId  |  Id of the sticker.|
 |backGroundColor | Color of the background in rich media.|
 |bgColor  |  Color of the background in keyboard.|
 |defaultHeight  | Maximal height will be native keyboard height.|
 |customDefaultHeigh  | How much percent of free screen space in chat should be taken by keyboard.|
 |buttonsGroupColumns | Number of columns per carousel content block.|
 |buttonsGroupRows  | Number of rows per carousel content block.|
 |buttons  |  Array of buttons in keyboard .|
 |richMediaButtons  | Array of buttons in rich media.|
  
 7.Navigate to "{EI_Connector_HOME}" and  run the following command.
 
          $ mvn clean install -Dskip-tests=false     
  > NOTE: 
  
  UserDetails with mandatory parameters request can be send twice during a 12 hours period for each user ID. If you send request more than twice during 12 hours
  it will gives "maximum get user info requests exceeded" response and for some methods Viber back-end will gives 504 GATEWAY_TIMEOUT error.This error is coming from Viber back-end.Because when we send multiple request
  to the back-end at the same time back-end will be dropped some requests.
