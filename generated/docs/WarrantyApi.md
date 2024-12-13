# WarrantyApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**warrantyActivate**](WarrantyApi.md#warrantyActivate) | **POST** /activate-warranty | Warranty activate |
| [**warrantyCancel**](WarrantyApi.md#warrantyCancel) | **POST** /cancel-warranty | Warranty cancel |


<a id="warrantyActivate"></a>
# **warrantyActivate**
> WarrantyStatus warrantyActivate()

Warranty activate

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = WarrantyApi()
try {
    val result : WarrantyStatus = apiInstance.warrantyActivate()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WarrantyApi#warrantyActivate")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WarrantyApi#warrantyActivate")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**WarrantyStatus**](WarrantyStatus.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="warrantyCancel"></a>
# **warrantyCancel**
> WarrantyStatus warrantyCancel()

Warranty cancel

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = WarrantyApi()
try {
    val result : WarrantyStatus = apiInstance.warrantyCancel()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling WarrantyApi#warrantyCancel")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling WarrantyApi#warrantyCancel")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**WarrantyStatus**](WarrantyStatus.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

