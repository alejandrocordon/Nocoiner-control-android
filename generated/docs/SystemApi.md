# SystemApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**systemReboot**](SystemApi.md#systemReboot) | **POST** /system/reboot | System reboot |


<a id="systemReboot"></a>
# **systemReboot**
> RebootAfter systemReboot()

System reboot

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = SystemApi()
try {
    val result : RebootAfter = apiInstance.systemReboot()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SystemApi#systemReboot")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SystemApi#systemReboot")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**RebootAfter**](RebootAfter.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

