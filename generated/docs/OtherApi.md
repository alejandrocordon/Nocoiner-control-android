# OtherApi

All URIs are relative to */api/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**findMiner**](OtherApi.md#findMiner) | **POST** /find-miner | Find miner |
| [**getChains**](OtherApi.md#getChains) | **GET** /chains | Get miner chains |
| [**getChainsFactoryInfo**](OtherApi.md#getChainsFactoryInfo) | **GET** /chains/factory-info | Get miner chains factory info |
| [**getChips**](OtherApi.md#getChips) | **GET** /chips | Get miner chips. Deprecated. Use /chains route instead |
| [**getInfo**](OtherApi.md#getInfo) | **GET** /info | Get miner info |
| [**layout**](OtherApi.md#layout) | **GET** /layout | Layout |
| [**perfSummary**](OtherApi.md#perfSummary) | **GET** /perf-summary | Summary |
| [**status**](OtherApi.md#status) | **GET** /status | Get status |
| [**summary**](OtherApi.md#summary) | **GET** /summary | Summary |
| [**ui**](OtherApi.md#ui) | **GET** /ui | UI |


<a id="findMiner"></a>
# **findMiner**
> FindMinerStatus findMiner()

Find miner

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : FindMinerStatus = apiInstance.findMiner()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#findMiner")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#findMiner")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**FindMinerStatus**](FindMinerStatus.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getChains"></a>
# **getChains**
> kotlin.collections.List&lt;AntmChainChips&gt; getChains()

Get miner chains

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : kotlin.collections.List<AntmChainChips> = apiInstance.getChains()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#getChains")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#getChains")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;AntmChainChips&gt;**](AntmChainChips.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getChainsFactoryInfo"></a>
# **getChainsFactoryInfo**
> FactoryInfoReply getChainsFactoryInfo()

Get miner chains factory info

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : FactoryInfoReply = apiInstance.getChainsFactoryInfo()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#getChainsFactoryInfo")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#getChainsFactoryInfo")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**FactoryInfoReply**](FactoryInfoReply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getChips"></a>
# **getChips**
> AntmChainsChipsStats getChips()

Get miner chips. Deprecated. Use /chains route instead

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : AntmChainsChipsStats = apiInstance.getChips()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#getChips")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#getChips")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**AntmChainsChipsStats**](AntmChainsChipsStats.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getInfo"></a>
# **getInfo**
> InfoJson getInfo()

Get miner info

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : InfoJson = apiInstance.getInfo()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#getInfo")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#getInfo")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InfoJson**](InfoJson.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="layout"></a>
# **layout**
> Layout layout()

Layout

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : Layout = apiInstance.layout()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#layout")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#layout")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Layout**](Layout.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="perfSummary"></a>
# **perfSummary**
> PerfSummary perfSummary()

Summary

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : PerfSummary = apiInstance.perfSummary()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#perfSummary")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#perfSummary")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**PerfSummary**](PerfSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="status"></a>
# **status**
> StatusPane status()

Get status

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : StatusPane = apiInstance.status()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#status")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#status")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**StatusPane**](StatusPane.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="summary"></a>
# **summary**
> Summary summary()

Summary

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : Summary = apiInstance.summary()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#summary")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#summary")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Summary**](Summary.md)

### Authorization


Configure apikeyAuth:
    ApiClient.apiKey["x-api-key"] = ""
    ApiClient.apiKeyPrefix["x-api-key"] = ""
Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="ui"></a>
# **ui**
> UiSettings ui()

UI

### Example
```kotlin
// Import classes:
//import com.natio21.nocoiner_control.openapi.client.infrastructure.*
//import com.natio21.nocoiner_control.openapi.client.models.*

val apiInstance = OtherApi()
try {
    val result : UiSettings = apiInstance.ui()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling OtherApi#ui")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling OtherApi#ui")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UiSettings**](UiSettings.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

