package com.gft.productservice.utils;

import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionUtil {

    public static <T> boolean isNullOrEmpty(List<T> items) {
        return items == null || items.isEmpty();
    }

    public static <K, V> boolean isNullOrEmpty(Map<K, V> items) {
        return items == null || items.isEmpty();
    }
}
