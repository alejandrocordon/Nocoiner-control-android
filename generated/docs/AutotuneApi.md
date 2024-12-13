# AutotuneApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**autotunePresets**](AutotuneApi.md#autotunePresets) | **GET** /autotune/presets | Get autotune preset list |
| [**autotuneReset**](AutotuneApi.md#autotuneReset) | **POST** /autotune/reset | Autotune reset list of profiles |
| [**autotuneResetAll**](AutotuneApi.md#autotuneResetAll) | **POST** /autotune/reset-all | Autotune reset all profiles |


<a id="autotunePresets"></a>
# **autotunePresets**
> kotlin.collections.List&lt;AutotunePresetDto&gt; autotunePresets()

Get autotune preset list

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = AutotuneApi()
try {
    val result : kotlin.collections.List<AutotunePresetDto> = apiInstance.autotunePresets()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AutotuneApi#autotunePresets")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AutotuneApi#autotunePresets")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;AutotunePresetDto&gt;**](AutotunePresetDto.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="autotuneReset"></a>
# **autotuneReset**
> autotuneReset(autotuneReset)

Autotune reset list of profiles

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = AutotuneApi()
val autotuneReset : AutotuneReset =  // AutotuneReset | 
try {
    apiInstance.autotuneReset(autotuneReset)
} catch (e: ClientException) {
    println("4xx response calling AutotuneApi#autotuneReset")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AutotuneApi#autotuneReset")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **autotuneReset** | [**AutotuneReset**](AutotuneReset.md)|  | |

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

<a id="autotuneResetAll"></a>
# **autotuneResetAll**
> autotuneResetAll(autotuneResetAll)

Autotune reset all profiles

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = AutotuneApi()
val autotuneResetAll : AutotuneResetAll =  // AutotuneResetAll | 
try {
    apiInstance.autotuneResetAll(autotuneResetAll)
} catch (e: ClientException) {
    println("4xx response calling AutotuneApi#autotuneResetAll")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AutotuneApi#autotuneResetAll")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **autotuneResetAll** | [**AutotuneResetAll**](AutotuneResetAll.md)|  | |

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

