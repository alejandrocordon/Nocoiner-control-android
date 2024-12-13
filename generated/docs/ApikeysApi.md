# ApikeysApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**apikeysAdd**](ApikeysApi.md#apikeysAdd) | **POST** /apikeys | Add api key |
| [**apikeysDelete**](ApikeysApi.md#apikeysDelete) | **POST** /apikeys/delete | Delete api key |
| [**apikeysGet**](ApikeysApi.md#apikeysGet) | **GET** /apikeys | Get apikeys |


<a id="apikeysAdd"></a>
# **apikeysAdd**
> AddApiKeyRes apikeysAdd(body)

Add api key

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = ApikeysApi()
val body : ApiKeysJsonItem =  // ApiKeysJsonItem | 
try {
    val result : AddApiKeyRes = apiInstance.apikeysAdd(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ApikeysApi#apikeysAdd")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ApikeysApi#apikeysAdd")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **ApiKeysJsonItem**|  | |

### Return type

[**AddApiKeyRes**](AddApiKeyRes.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="apikeysDelete"></a>
# **apikeysDelete**
> apikeysDelete(deleteApikeyQuery)

Delete api key

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = ApikeysApi()
val deleteApikeyQuery : DeleteApikeyQuery =  // DeleteApikeyQuery | 
try {
    apiInstance.apikeysDelete(deleteApikeyQuery)
} catch (e: ClientException) {
    println("4xx response calling ApikeysApi#apikeysDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ApikeysApi#apikeysDelete")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **deleteApikeyQuery** | [**DeleteApikeyQuery**](DeleteApikeyQuery.md)|  | |

### Return type

null (empty response body)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="apikeysGet"></a>
# **apikeysGet**
> kotlin.collections.List&lt;ApiKeysJsonItem&gt; apikeysGet()

Get apikeys

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = ApikeysApi()
try {
    val result : kotlin.collections.List<ApiKeysJsonItem> = apiInstance.apikeysGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ApikeysApi#apikeysGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ApikeysApi#apikeysGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;ApiKeysJsonItem&gt;**](ApiKeysJsonItem.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

