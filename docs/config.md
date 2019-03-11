# Configuring Viber Operations
[[Prerequisites]](#Prerequisites) [[Initializing the connector]](#initializing-the-connector)

## Prerequisites

> NOTE: To work with the Viber connector, you need to have a Viber account. If you do not have a Viber account, [installing the Viber software](https://www.viber.com/download/) and create a [Viber public  account](https://viber.github.io/docs/general/get-started/) and derive the app key.
 <br></br>
 
    The app key is generated upon public account creation and can be viewed by the account’s admin in the “edit info” screen of public account.
  
   Before an account can send messages to a user, the user will need to subscribe to the account.Subscribing can take place in one of two ways: 
  
    i) when a user sends it's first message to public account the user will be automatically subscribed to the account.
   
    ii) By pressing the subscribe button user can subscribed to the account.
  
 By running accountInfo method you can derive the user's id who are subscribed to the public account and user's name, admin's avatar also.
 
Once you have your token you will be able to set your account’s webhook. This webhook will be used for receiving callbacks and user messages from Viber.
So first you should set your account's webhook.For security reasons only URLs with valid and official SSL certificate from a trusted CA will be allowed. For more reference follow https://codeburst.io/whats-a-webhook-1827b07a3ffa.

To use the Viber connector, add the `<viber.init>` element to your proxy configuration before using any other Viber
 connector operations.This Viber configuration authenticates with  access token.
 For more reference follow https://developers.viber.com/docs/api/rest-bot-api/#authentication-token.
<br/>

## Initializing the connector
Add the following <viber.init> method in your configuration:

#### init
```xml
<viber.init>
    <appKey>{$ctx:appKey}</appKey>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
</viber.init>
```

##### Properties

* appKey:  Account authentication token
* apiUrl: The application URL of Viber.

Now that you have connected to viber, use the information in the following topics to perform various operations with the connector.

* To work with webhook, See [Working with webhook](webhook.md).
* To work with broadcastMessage, See [Working with broadcast message](broadcastMessage.md).
* To work with accountInfo, See [Working with  account info](accountInfo.md).
* To work with onlineStatus, see [Working with online status](onlineStatus.md).
* To work with userDeails, See [Working with  user details](userDetails.md).
* To work with keyboardMessage, See [Working with Keyboard message](keyboardMessage.md).
* To work with message, See [Working with message ](message.md).