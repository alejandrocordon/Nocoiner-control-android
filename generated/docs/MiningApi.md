# MiningApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**miningPause**](MiningApi.md#miningPause) | **POST** /mining/pause | Mining pause |
| [**miningRestart**](MiningApi.md#miningRestart) | **POST** /mining/restart | Mining restart |
| [**miningResume**](MiningApi.md#miningResume) | **POST** /mining/resume | Mining resume |
| [**miningStart**](MiningApi.md#miningStart) | **POST** /mining/start | Mining start |
| [**miningStop**](MiningApi.md#miningStop) | **POST** /mining/stop | Mining stop |
| [**miningSwitchPool**](MiningApi.md#miningSwitchPool) | **POST** /mining/switch-pool | Mining switch pool |


<a id="miningPause"></a>
# **miningPause**
> miningPause()

Mining pause

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = MiningApi()
try {
    apiInstance.miningPause()
} catch (e: ClientException) {
    println("4xx response calling MiningApi#miningPause")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MiningApi#miningPause")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="miningRestart"></a>
# **miningRestart**
> miningRestart()

Mining restart

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = MiningApi()
try {
    apiInstance.miningRestart()
} catch (e: ClientException) {
    println("4xx response calling MiningApi#miningRestart")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MiningApi#miningRestart")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="miningResume"></a>
# **miningResume**
> miningResume()

Mining resume

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = MiningApi()
try {
    apiInstance.miningResume()
} catch (e: ClientException) {
    println("4xx response calling MiningApi#miningResume")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MiningApi#miningResume")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="miningStart"></a>
# **miningStart**
> miningStart()

Mining start

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = MiningApi()
try {
    apiInstance.miningStart()
} catch (e: ClientException) {
    println("4xx response calling MiningApi#miningStart")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MiningApi#miningStart")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="miningStop"></a>
# **miningStop**
> miningStop()

Mining stop

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = MiningApi()
try {
    apiInstance.miningStop()
} catch (e: ClientException) {
    println("4xx response calling MiningApi#miningStop")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MiningApi#miningStop")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="miningSwitchPool"></a>
# **miningSwitchPool**
> miningSwitchPool(switchPoolQuery)

Mining switch pool

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = MiningApi()
val switchPoolQuery : SwitchPoolQuery =  // SwitchPoolQuery | 
try {
    apiInstance.miningSwitchPool(switchPoolQuery)
} catch (e: ClientException) {
    println("4xx response calling MiningApi#miningSwitchPool")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MiningApi#miningSwitchPool")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **switchPoolQuery** | [**SwitchPoolQuery**](SwitchPoolQuery.md)|  | |

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

