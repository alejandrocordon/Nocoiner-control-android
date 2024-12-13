# LogsApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**logsClear**](LogsApi.md#logsClear) | **POST** /logs/{log_type}/clear | Clear logs |
| [**logsGet**](LogsApi.md#logsGet) | **GET** /logs/{log_type} | Read log file |


<a id="logsClear"></a>
# **logsClear**
> logsClear(logType)

Clear logs

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = LogsApi()
val logType : LogType =  // LogType | Log type
try {
    apiInstance.logsClear(logType)
} catch (e: ClientException) {
    println("4xx response calling LogsApi#logsClear")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LogsApi#logsClear")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **logType** | [**LogType**](.md)| Log type | [enum: status, miner, autotune, system, messages, api, *] |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="logsGet"></a>
# **logsGet**
> logsGet(logType)

Read log file

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = LogsApi()
val logType : LogType =  // LogType | Log type name. All logs `*` are not implemented for this route
try {
    apiInstance.logsGet(logType)
} catch (e: ClientException) {
    println("4xx response calling LogsApi#logsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LogsApi#logsGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **logType** | [**LogType**](.md)| Log type name. All logs &#x60;*&#x60; are not implemented for this route | [enum: status, miner, autotune, system, messages, api, *] |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

