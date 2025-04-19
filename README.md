# Feature interaction example

This Repository is an example of multi-module interaction.

## How does it work?

This solution based on [ActivityResultApi](https://developer.android.com/training/basics/intents/result).

Base interface for all feature-modules looks like this:

```kotlin
interface FeatureManager {

    @MainThread
    fun <I, O> launch(
        key: String,
        contract: ActivityResultContract<I, O>,
        input: I,
        output: (FeatureResult<O>) -> Unit
    )
}
```
You can create your own contract in any way that suits you. In this example each feature-module implement own contract that depending on the needs.

```kotlin
interface SomeFeature {

    @MainThread
    fun launchSomeFeature(
        input: SomeFeatureArgs,
        onSuccess: (result: SomeFeatureResult.Success, durationInMills: Long) -> Unit = { _, _ -> },
        onFailure: (result: SomeFeatureResult.Failure, durationInMills: Long) -> Unit = { _, _ -> },
        onCancelled: (durationInMills: Long) -> Unit = {  }
    )

    ...
}
```

And further usage looks something like this in your ViewModel or somewhere else.

```kotlin
    someFeature.launchSomeFeature(
        input = SomeFeatureArgs("Please, input some text"),
        onSuccess = { 
            // do something
        },
        onFailure = { 
            // do something
        },
        onCancelled = { 
            // do something
        }
    )
```

