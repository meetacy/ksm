package ksm

import ksm.annotation.LibraryApi
import ksm.context.StateContext

public interface StateController {
    public val context: StateContext

    public interface Builder {
        @LibraryApi
        public var context: StateContext
    }
}
