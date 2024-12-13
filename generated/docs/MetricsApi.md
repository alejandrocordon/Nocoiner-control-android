# MetricsApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**metrics**](MetricsApi.md#metrics) | **GET** /metrics | Get metrics |


<a id="metrics"></a>
# **metrics**
> MetricsReply metrics(timeSlice, step, until)

Get metrics

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = MetricsApi()
val timeSlice : kotlin.Int = 56 // kotlin.Int | Amount of seconds until now. Max is 3 days (3 * 24 * 60 * 60) Default is 1 day (24 * 60 * 60)
val step : kotlin.Int = 56 // kotlin.Int | Resample step in seconds to count average, default is 15 min (15 * 60)
val until : kotlin.Int = 56 // kotlin.Int | Pet id
try {
    val result : MetricsReply = apiInstance.metrics(timeSlice, step, until)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling MetricsApi#metrics")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling MetricsApi#metrics")
    e.printStackTrace()
}
```

### Parameters
| **timeSlice** | **kotlin.Int**| Amount of seconds until now. Max is 3 days (3 * 24 * 60 * 60) Default is 1 day (24 * 60 * 60) | |
| **step** | **kotlin.Int**| Resample step in seconds to count average, default is 15 min (15 * 60) | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **until** | **kotlin.Int**| Pet id | |

### Return type

[**MetricsReply**](MetricsReply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

