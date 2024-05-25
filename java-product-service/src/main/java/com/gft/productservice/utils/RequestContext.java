package com.gft.productservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestContext {
    private static final ThreadLocal<String> customerIdHolder = new ThreadLocal<>();

    public static void setCustomerId(String customerId) {
        customerIdHolder.set(customerId);
    }

    public static String getCustomerId() {
        return customerIdHolder.get();
    }
}
