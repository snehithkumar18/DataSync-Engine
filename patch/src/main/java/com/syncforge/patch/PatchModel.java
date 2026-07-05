package com.syncforge.patch;

import java.util.ArrayList;
import java.util.List;

public class PatchModel {
    private final List<PatchOperation> operations = new ArrayList<>();

    public List<PatchOperation> getOperations() {
        return operations;
    }
}
