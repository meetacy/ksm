package ksm.annotation

@RequiresOptIn(
    message = "Indicates that the following function creates a copy of context. " +
        "Do not opt-in! Only propagate this annotation"
)
public annotation class MutateContext
