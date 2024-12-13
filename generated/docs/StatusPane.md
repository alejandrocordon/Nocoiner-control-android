
# StatusPane

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **minerState** | [**MinerState**](MinerState.md) |  |  |
| **minerStateTime** | **kotlin.Long** | Time spent in the current state. For now implemented for &#x60;mining&#x60; state only. |  |
| **rebootRequired** | **kotlin.Boolean** | Miner restart required to apply |  |
| **restartRequired** | **kotlin.Boolean** | Miner restart required to apply config |  |
| **findMiner** | **kotlin.Boolean** | Flag to switch find_miner function on target devices. Optional, default &#x60;false&#x60; |  |
| **unlocked** | **kotlin.Boolean** | Show screen-lock status (checks that  any of auth methods satisfies) |  |
| **description** | **kotlin.String** | Optional. Description if status is failure |  [optional] |
| **warranty** | [**Warranty**](Warranty.md) |  |  [optional] |



