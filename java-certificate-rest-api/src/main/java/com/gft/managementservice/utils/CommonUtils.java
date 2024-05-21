package com.gft.managementservice.utils;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonUtils {
    public static <T> boolean isNullOrEmpty(List<T> items) {
        return items == null || items.isEmpty();
    }
}
