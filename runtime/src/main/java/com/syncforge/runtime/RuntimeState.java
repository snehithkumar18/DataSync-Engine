package com.syncforge.runtime;

public enum RuntimeState {
    PENDING,
    RUNNING,
    APPLIED,
    FAILED,
    ROLLED_BACK,
    SKIPPED,
    CONFLICTED
}
