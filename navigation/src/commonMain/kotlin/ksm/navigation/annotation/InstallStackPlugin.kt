package ksm.navigation.annotation

@RequiresOptIn(
    message = "You install StackPlugin without usage of installStackPlugin function. " +
        "In order to make Lifecycle work properly with StackPlugin, consider to use this function instead",
    level = RequiresOptIn.Level.ERROR
)
public annotation class InstallStackPlugin
