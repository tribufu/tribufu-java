// Copyright (c) Tribufu. All Rights Reserved

package com.tribufu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TribufuApiTest {
    @Test
    void defaultApi() {
        TribufuApi api = new TribufuApi();
        assertNotEquals(null, api);
    }
}
