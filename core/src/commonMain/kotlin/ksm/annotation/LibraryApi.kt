package ksm.annotation

@RequiresOptIn(
    message = "This api is intended to be used by library developers only, consider not to use it",
    level = RequiresOptIn.Level.ERROR
)
public annotation class LibraryApi
