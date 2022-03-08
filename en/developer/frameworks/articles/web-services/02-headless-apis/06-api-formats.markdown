---
header-id: rest-api-formats-and-content-negotiation
---

# API Formats and Content Negotiation

[TOC levels=1-4]

The responses in the preceding examples use a standard JSON format, which is the 
default response format for @product@'s headless REST APIs. You can also use 
other formats like XML. Formats typically differ in the resource metadata's 
structure or semantics. There's no best format; use the one that best fits your 
use case. 

You use *content negotiation* to specify different formats for use. Content 
negotiation is how the client and server establish the format they use to 
exchange messages. The client tells the server its preferred format via the HTTP 
headers `Accept` and `Content-Type`. Each format has a string identifier (its 
MIME type) that you can use in the HTTP headers to specify the format. The 
following table lists the MIME type for each supported format. 

| API Format | &nbsp;MIME Type | 
| --------- | ----------------------- | 
| application/json | [application/json](https://www.iana.org/assignments/media-types/application/json) |
| application/xml | [application/xml](https://www.iana.org/assignments/media-types/application/xml) |

When you send a request without specifying the API format, the server responds 
with the default JSON. For example, here's such a request for a list of folders 
from the Site with the ID `20124`: 

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/document-folders" -u 'test@example.com:test'
```

```json
{
  "items": [
    {
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T10:21:20Z",
      "dateModified": "2019-04-22T10:21:20Z",
      "id": 59319,
      "name": "REST APIs Documentation",
      "numberOfDocumentFolders": 0,
      "numberOfDocuments": 0,
      "siteId": 20124
    }
  ],
  "lastPage": 1,
  "page": 1,
  "pageSize": 20,
  "totalCount": 1
}
```

If you request the headers, the `Content-Type` response attribute lists the 
content type's format (JSON, in this case): 


```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/document-folders" -u 'test@example.com:test' --head
```

    HTTP/1.1 200 
    X-Content-Type-Options: nosniff
    X-Frame-Options: SAMEORIGIN
    X-XSS-Protection: 1
    Set-Cookie: JSESSIONID=9F61AEB8721DD9149BD577ECBC31AE3F; Path=/; HttpOnly
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Cache-Control: private, no-cache, no-store, must-revalidate
    Pragma: no-cache
    Set-Cookie: COOKIE_SUPPORT=true; Max-Age=31536000; Expires=Tue, 21-Apr-2020 10:23:57 GMT; Path=/; HttpOnly
    Set-Cookie: GUEST_LANGUAGE_ID=en_US; Max-Age=31536000; Expires=Tue, 21-Apr-2020 10:23:57 GMT; Path=/; HttpOnly
    Date: Mon, 22 Apr 2019 10:23:57 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked

To get the response in XML instead, specify `application/xml` in the request's 
`Accept` header. Note that the XML response includes the same information as 
JSON, but is structured differently: 

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/documents/59203"  -H 'Accept: application/xml'  -u 'test@example.com:test'
```

```xml
    <Page>
        <items>
            <items>
                <creator>
                    <familyName>Test</familyName>
                    <givenName>Test</givenName>
                    <id>20130</id>
                    <name>Test Test</name>
                    <profileURL>/web/test</profileURL>
                    </creator>
                <dateCreated>2019-04-22T10:21:20Z</dateCreated>
                <dateModified>2019-04-22T10:21:20Z</dateModified>
                <id>59319</id>
                <name>REST APIs Documentation</name>
                <numberOfDocumentFolders>0</numberOfDocumentFolders>
                <numberOfDocuments>0</numberOfDocuments>
                <siteId>20124</siteId>
            </items>
        </items>
        <lastPage>1</lastPage>
        <page>1</page>
        <pageSize>20</pageSize>
        <totalCount>1</totalCount>
    </Page>
```

Requesting the headers, you can see that the response is in XML 
(`application/xml`): 

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/documents/59203"  -H 'Accept: application/xml'  -u 'test@example.com:test' --head
```

    HTTP/1.1 200 
    X-Content-Type-Options: nosniff
    X-Frame-Options: SAMEORIGIN
    X-XSS-Protection: 1
    Expires: Thu, 01 Jan 1970 00:00:00 GMT
    Cache-Control: private, no-cache, no-store, must-revalidate
    Pragma: no-cache
    Date: Mon, 22 Apr 2019 10:26:21 GMT
    Content-Type: application/xml
    Transfer-Encoding: chunked

## Language Negotiation

The same mechanism used for requesting another response format (content 
negotiation) is used for requesting content in another language. 

APIs that are available in different languages return the options in a block 
called `availableLanguages`. For example, this block in the following response 
lists U.S. English (`en-US`) and Spain/Castilian Spanish (`es-ES`): 

```json
{
  "availableLanguages": [
    "en-US",
    "es-ES"
  ],
  "contentFields": [
    {
      "dataType": "html",
      "name": "content",
      "repeatable": false,
      "value": {
        "data": "<p>The main reason is because Headless APIs have been designed with real use cases in mind...</p>"
      }
    }
  ],
  "contentStructureId": 36801,
  "creator": {
    "familyName": "Test",
    "givenName": "Test",
    "id": 20130,
    "name": "Test Test",
    "profileURL": "/web/test"
  },
  "dateCreated": "2019-04-22T10:29:40Z",
  "dateModified": "2019-04-22T10:30:31Z",
  "datePublished": "2019-04-22T10:28:00Z",
  "friendlyUrlPath": "why-headless-apis-are-better-than-json-ws-services-",
  "id": 59325,
  "key": "59323",
  "numberOfComments": 0,
  "renderedContents": [
    {
      "renderedContentURL": "http://localhost:8080/o/headless-delivery/v1.0/structured-contents/59325/rendered-content/36804",
      "templateName": "Basic Web Content"
    }
  ],
  "siteId": 20124,
  "title": "Why Headless APIs are better than JSON-WS services?",
  "uuid": "e1c4c152-e47c-313f-2d16-2ee4eba5cd26"
}
```

To request the content in another language, specify your desired locale in the 
request's `Accept-Language` header: 

```bash
curl "http://localhost:8080/o/headless-delivery/v1.0/structured-contents/59325"  -H 'Accept-Language: es-ES'  -u 'test@example.com:test'
```

```json
    {
      "availableLanguages": [
        "en-US",
        "es-ES"
      ],
      "contentFields": [
        {
          "dataType": "html",
          "name": "content",
          "repeatable": false,
          "value": {
            "data": "<p>La principal razón es porque las APIs Headless se han diseñado pensando en casos de uso reales...</p>"
          }
        }
      ],
      "contentStructureId": 36801,
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T10:29:40Z",
      "dateModified": "2019-04-22T10:30:31Z",
      "datePublished": "2019-04-22T10:28:00Z",
      "friendlyUrlPath": "%C2%BFpor-qu%C3%A9-las-apis-headless-son-mejores-que-json-ws-",
      "id": 59325,
      "key": "59323",
      "numberOfComments": 0,
      "renderedContents": [
        {
          "renderedContentURL": "http://localhost:8080/o/headless-delivery/v1.0/structured-contents/59325/rendered-content/36804",
          "templateName": "Contenido web básico"
        }
      ],
      "siteId": 20124,
      "title": "¿Por qué las APIs Headless son mejores que JSON-WS?",
      "uuid": "e1c4c152-e47c-313f-2d16-2ee4eba5cd26"
    }
```

### Creating Content with Different Languages

By default, when sending a POST/PUT request, the `Accept-Language` header is
used as the content's language. However, there is one exception. Some entities
require the first POST to be in the Site's default language. In such 
cases, a POST request for a different language results in an error. 

After creating a new resource, PUT requests in a different language adds that 
translation. PATCH requests return an error (you are expected to update, 
not create, in a PATCH request). 

## Related Topics

[Get Started: Discover the API](/docs/7-2/frameworks/-/knowledge_base/f/get-started-discover-the-api)

[Get Started: Invoke a Service](/docs/7-2/frameworks/-/knowledge_base/f/get-started-invoke-a-service)
