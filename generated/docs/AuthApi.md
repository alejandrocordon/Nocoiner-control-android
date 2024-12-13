# AuthApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**authCheck**](AuthApi.md#authCheck) | **GET** /auth-check | Auth Check |
| [**lock**](AuthApi.md#lock) | **POST** /lock | Lock miner |
| [**lockOthers**](AuthApi.md#lockOthers) | **POST** /lock/others | Lock other miner sessions |
| [**unlock**](AuthApi.md#unlock) | **POST** /unlock | Auth Check |


<a id="authCheck"></a>
# **authCheck**
> authCheck()

Auth Check

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = AuthApi()
try {
    apiInstance.authCheck()
} catch (e: ClientException) {
    println("4xx response calling AuthApi#authCheck")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#authCheck")
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
 - **Accept**: Not defined

<a id="lock"></a>
# **lock**
> lock()

Lock miner

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = AuthApi()
try {
    apiInstance.lock()
} catch (e: ClientException) {
    println("4xx response calling AuthApi#lock")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#lock")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a id="lockOthers"></a>
# **lockOthers**
> lockOthers(unlockScreenBody)

Lock other miner sessions

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = AuthApi()
val unlockScreenBody : UnlockScreenBody =  // UnlockScreenBody | 
try {
    apiInstance.lockOthers(unlockScreenBody)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#lockOthers")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#lockOthers")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **unlockScreenBody** | [**UnlockScreenBody**](UnlockScreenBody.md)|  | |

### Return type

null (empty response body)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a id="unlock"></a>
# **unlock**
> UnlockSuccess unlock(unlockScreenBody)

Auth Check

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = AuthApi()
val unlockScreenBody : UnlockScreenBody =  // UnlockScreenBody | 
try {
    val result : UnlockSuccess = apiInstance.unlock(unlockScreenBody)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#unlock")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#unlock")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **unlockScreenBody** | [**UnlockScreenBody**](UnlockScreenBody.md)|  | |

### Return type

[**UnlockSuccess**](UnlockSuccess.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

