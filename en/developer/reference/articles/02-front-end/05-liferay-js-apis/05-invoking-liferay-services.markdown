---
header-id: invoking-liferay-services
---

# Invoking Liferay Services

[TOC levels=1-4]

@product@ provides many web services out-of-the-box. To see a comprehensive list 
of the available web services, navigate to `http://localhost:8080/api/jsonws` 
(assuming your localhost is running on port 8080). 

<!--Uncomment once registering-json-web-services#mapping-and-naming-conventions article is available
If you've deployed your own 
Service Builder-generated JSON web services, 
follow these guidelines
for invoking them. These services are useful for creating single page 
applications and can even be used to create custom front-ends in @product@. 
-->

This reference covers how to invoke these web services using JavaScript. 

## Invoking Web Services via JavaScript

@product-ver@ contains a global JavaScript object called `Liferay` that has many 
useful utilities. One method is `Liferay.Service`, which invokes JSON web 
services. 

The `Liferay.Service` method takes four possible arguments:

**service {string|object}:** Specify the service name or an object with the 
keys as the service to call, and the value as the service configuration object. 
(Required) 

**data {object|node|string}:** Specify the data to send to the service. If the 
object passed is the ID of a form or a form element, the form fields will be 
serialized and used as the data. 

**successCallback {function}:** A function to execute when the server returns a 
response. It receives a JSON object as its first parameter. 

**exceptionCallback {function}:** A function to execute when the response from 
the server contains a service exception. It receives an exception message as 
its first parameter. 

One of the benefits of using the `Liferay.Service` method versus using 
a standard AJAX request is that it handles the authentication for you. 

Below is an example configuration of the `Liferay.Service` method:

```javascript
Liferay.Service(
        '/user/get-user-by-email-address',
        {
                companyId: Liferay.ThemeDisplay.getCompanyId(),
                emailAddress: 'test@example.com'
        },
        function(obj) {
                console.log(obj);
        }
);
```

The example above retrieves information about a user by passing its `companyId` 
and `emailAddress`. The response data resembles the following JSON object:

```json
{
        "agreedToTermsOfUse": true,
        "comments": "",
        "companyId": "20116",
        "contactId": "20157",
        "createDate": 1471990639779,
        "defaultUser": false,
        "emailAddress": "test@example.com",
        "emailAddressVerified": true,
        "facebookId": "0",
        "failedLoginAttempts": 0,
        "firstName": "Test",
        "googleUserId": "",
        "graceLoginCount": 0,
        "greeting": "Welcome Test Test!",
        "jobTitle": "",
        "languageId": "en_US",
        "lastFailedLoginDate": null,
        "lastLoginDate": 1471996720765,
        "lastLoginIP": "127.0.0.1",
        "lastName": "Test",
        "ldapServerId": "-1",
        "lockout": false,
        "lockoutDate": null,
        "loginDate": 1472077523149,
        "loginIP": "127.0.0.1",
        "middleName": "",
        "modifiedDate": 1472077523149,
        "mvccVersion": "7",
        "openId": "",
        "portraitId": "0",
        "reminderQueryAnswer": "test",
        "reminderQueryQuestion": "what-is-your-father's-middle-name",
        "screenName": "test",
        "status": 0,
        "timeZoneId": "UTC",
        "userId": "20156",
        "uuid": "c641a7c9-5acb-aa68-b3ea-5575e1845d2f"
}
```

Now that you know how to send an individual request, you're ready to run batch 
requests. 

## Batching Requests

Another way to invoke the `Liferay.Service` method is by passing an object with 
the keys of the service to call and the value of the service configuration 
object. 

Below is an example configuration for a batch request:

```javascript
Liferay.Service(
        {
                '/user/get-user-by-email-address': {
                        companyId: Liferay.ThemeDisplay.getCompanyId(),
                        emailAddress: 'test@example.com'
                }
        },
        function(obj) {
                console.log(obj);
        }
);
```

You can invoke multiple services with the same request by passing in an array of 
service objects. Here's an example:

```javascript
Liferay.Service(
        [
                {
                        '/user/get-user-by-email-address': {
                                companyId: Liferay.ThemeDisplay.getCompanyId(),
                                emailAddress: 'test@example.com'
                        }
                },
                {
                        '/role/get-user-roles': {
                                userId: Liferay.ThemeDisplay.getUserId()
                        }
                }
        ],
        function(obj) {
                // obj is now an array of response objects
                // obj[0] == /user/get-user-by-email-address data
                // obj[1] == /role/get-user-roles data

                console.log(obj);
        }
);
```

Next you can learn how to nest your requests. 

## Nesting Requests

Nested service calls bind information from related objects together in a JSON 
object. You can call other services in the same HTTP request and conveniently 
nest returned objects. 

You can use variables to reference objects returned from service calls. Variable 
names must start with a dollar sign (`$`). 

The example in this section retrieves user data with `/user/get-user-by-id` and 
uses the `contactId` returned from that service to then invoke 
`/contact/get-contact` in the same request. 

| **Note:** You must flag parameters that take values from existing variables. To 
| flag a parameter, insert the `@` prefix before the parameter name. 

Below is an example configuration that demonstrates these concepts:

```javascript
Liferay.Service(
        {
                "$user = /user/get-user-by-id": {
                        "userId": Liferay.ThemeDisplay.getUserId(),
                        "$contact = /contact/get-contact": {
                                "@contactId": "$user.contactId"
                        }
                }
        },
        function(obj) {
                console.log(obj);
        }
);
```

Here is what the response data would look like for the request above:

```json
{
        "agreedToTermsOfUse": true,
        "comments": "",
        "companyId": "20116",
        "contactId": "20157",
        "createDate": 1471990639779,
        "defaultUser": false,
        "emailAddress": "test@example.com",
        "emailAddressVerified": true,
        "facebookId": "0",
        "failedLoginAttempts": 0,
        "firstName": "Test",
        "googleUserId": "",
        "graceLoginCount": 0,
        "greeting": "Welcome Test Test!",
        "jobTitle": "",
        "languageId": "en_US",
        "lastFailedLoginDate": null,
        "lastLoginDate": 1472231639378,
        "lastLoginIP": "127.0.0.1",
        [...]
        "screenName": "test",
        "status": 0,
        "timeZoneId": "UTC",
        "userId": "20156",
        "uuid": "c641a7c9-5acb-aa68-b3ea-5575e1845d2f",
        "contact": {
                "accountId": "20118",
                "birthday": 0,
                [...]
                "createDate": 1471990639779,
                "emailAddress": "test@example.com",
                "employeeNumber": "",
                "employeeStatusId": "",
                "facebookSn": "",
                "firstName": "Test",
                "lastName": "Test",
                "male": true,
                "middleName": "",
                "modifiedDate": 1471990639779,
                [...]
                "userName": ""
        }
}
```
 
Now that you know how to process requests, you can learn how to filter the 
results. 

## Filtering Results

If you don't want all the properties returned by a service, you can define a 
whitelist of properties. This returns only the specific properties you request 
in the object. 

Below is an example of whitelisting properties:

```javascript
Liferay.Service(
        {
                '$user[emailAddress,firstName] = /user/get-user-by-id': {
                        userId: Liferay.ThemeDisplay.getUserId()
                }
        },
        function(obj) {
                console.log(obj);
        }
);
```

To specify whitelist properties, place the properties in square brackets 
(e.g., `[whiteList]`) immediately following the name of your variable. The 
example above requests only the `emailAddress` and `firstName` of the user. 

Below is the filtered response:

```json
{
        "firstName": "Test",
        "emailAddress": "test@example.com"
}
```

Next you can learn how to populate the inner parameters of the request. 
 
## Inner Parameters

When you pass in an object parameter, you'll often need to populate its inner 
parameters (i.e., fields). 

Consider a default parameter `serviceContext` of type `ServiceContext`. To make 
an appropriate call to JSON web services you might need to set `serviceContext` 
fields such as `scopeGroupId`, as shown below:

```javascript
Liferay.Service(
        '/example/some-web-service',
        {
                serviceContext: {
                        scopeGroupId: 123
                }
        },
        function(obj) {
                console.log(obj);
        }
);
```
