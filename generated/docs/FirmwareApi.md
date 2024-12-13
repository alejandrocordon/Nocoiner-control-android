# FirmwareApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**firmwareRemove**](FirmwareApi.md#firmwareRemove) | **POST** /firmware/remove | Remove firmware and boot from stock |
| [**firmwareUpdate**](FirmwareApi.md#firmwareUpdate) | **POST** /firmware/update | Update firmware |


<a id="firmwareRemove"></a>
# **firmwareRemove**
> RebootAfter firmwareRemove()

Remove firmware and boot from stock

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = FirmwareApi()
try {
    val result : RebootAfter = apiInstance.firmwareRemove()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FirmwareApi#firmwareRemove")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FirmwareApi#firmwareRemove")
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

<a id="firmwareUpdate"></a>
# **firmwareUpdate**
> RebootAfter firmwareUpdate(file, keepSettings)

Update firmware

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = FirmwareApi()
val file : java.io.File = BINARY_DATA_HERE // java.io.File | 
val keepSettings : SchemaBoolEnum =  // SchemaBoolEnum | String \\\"true\\\" or \\\"false\\\"
try {
    val result : RebootAfter = apiInstance.firmwareUpdate(file, keepSettings)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FirmwareApi#firmwareUpdate")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FirmwareApi#firmwareUpdate")
    e.printStackTrace()
}
```

### Parameters
| **file** | **java.io.File**|  | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **keepSettings** | [**SchemaBoolEnum**](SchemaBoolEnum.md)| String \\\&quot;true\\\&quot; or \\\&quot;false\\\&quot; | [optional] [enum: true, false] |

### Return type

[**RebootAfter**](RebootAfter.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

