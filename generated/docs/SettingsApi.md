# SettingsApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**settingsBackup**](SettingsApi.md#settingsBackup) | **POST** /settings/backup | Settings backup |
| [**settingsFactoryReset**](SettingsApi.md#settingsFactoryReset) | **POST** /settings/factory-reset | Settings factory reset |
| [**settingsGet**](SettingsApi.md#settingsGet) | **GET** /settings | Get all miner settings |
| [**settingsRestore**](SettingsApi.md#settingsRestore) | **POST** /settings/restore | Settings restore |
| [**settingsSave**](SettingsApi.md#settingsSave) | **POST** /settings | Save miner settings |


<a id="settingsBackup"></a>
# **settingsBackup**
> kotlin.collections.List&lt;kotlin.Int&gt; settingsBackup()

Settings backup

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = SettingsApi()
try {
    val result : kotlin.collections.List<kotlin.Int> = apiInstance.settingsBackup()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SettingsApi#settingsBackup")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SettingsApi#settingsBackup")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.collections.List&lt;kotlin.Int&gt;**

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream

<a id="settingsFactoryReset"></a>
# **settingsFactoryReset**
> RebootAfter settingsFactoryReset()

Settings factory reset

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = SettingsApi()
try {
    val result : RebootAfter = apiInstance.settingsFactoryReset()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SettingsApi#settingsFactoryReset")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SettingsApi#settingsFactoryReset")
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

<a id="settingsGet"></a>
# **settingsGet**
> ViewConfig settingsGet()

Get all miner settings

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = SettingsApi()
try {
    val result : ViewConfig = apiInstance.settingsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SettingsApi#settingsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SettingsApi#settingsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ViewConfig**](ViewConfig.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="settingsRestore"></a>
# **settingsRestore**
> RebootAfter settingsRestore(file)

Settings restore

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = SettingsApi()
val file : java.io.File = BINARY_DATA_HERE // java.io.File | 
try {
    val result : RebootAfter = apiInstance.settingsRestore(file)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SettingsApi#settingsRestore")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SettingsApi#settingsRestore")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **file** | **java.io.File**|  | |

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

<a id="settingsSave"></a>
# **settingsSave**
> SaveConfigResult settingsSave(inputConfig)

Save miner settings

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = SettingsApi()
val inputConfig : InputConfig =  // InputConfig | 
try {
    val result : SaveConfigResult = apiInstance.settingsSave(inputConfig)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SettingsApi#settingsSave")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SettingsApi#settingsSave")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **inputConfig** | [**InputConfig**](InputConfig.md)|  | |

### Return type

[**SaveConfigResult**](SaveConfigResult.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

