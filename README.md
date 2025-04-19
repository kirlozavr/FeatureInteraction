# Feature interaction example

This Repository is an example of multi-module interaction.

## How does it work?

This solution based on [ActivityResultApi](https://developer.android.com/training/basics/intents/result). There are [ActivityProvider](core/feature-interaction/impl/src/main/java/com/kirlozavr/feature_interaction_impl/managers/ActivityProviderImpl.kt) that monitors ths active Activity and [FeatureManager](core/feature-interaction/impl/src/main/java/com/kirlozavr/feature_interaction_impl/managers/FeatureManagerImpl.kt) that launches our feature using ActivityResultApi and provides the result. Also, FeatureManager handles configuration changes and shuts down correctly to prevent memory-leaks.

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
You can create your own contract in any way that suits you. In this example each feature-module implements own contract that depending on the needs. Below is [SomeFeature](feature/some-feature/impl/src/main/java/com/kirlozavr/some_feature_impl/managers/SomeFeatureImpl.kt) that implements own contract.

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

And further usage looks something like this in your [ViewModel](app/src/main/java/com/kirlozavr/featureinteraction/screens/MainViewModel.kt) or somewhere else.

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

## Example of interaction

Below are the screenshots of example app. 

Screen of feature module where we can input text is presented below:

![Feature screen](images/feature_screen.png)

After we entered the text and clicked on "Save result", we return to the main screen and see it. 

![Main screen](images/main_screen.png)