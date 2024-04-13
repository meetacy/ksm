package ksm.annotation

@RequiresOptIn(
    message = "This constructor is intended to be used by library developers only, consider to use another one.",
    level = RequiresOptIn.Level.ERROR
)
public annotation class LibraryConstructor
