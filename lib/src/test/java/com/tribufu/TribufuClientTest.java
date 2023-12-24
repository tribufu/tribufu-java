// Copyright (c) Tribufu. All Rights Reserved

package com.tribufu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TribufuClientTest {
    @Test
    void clientGetId() {
        TribufuClient client = new TribufuClient(0, "client_secret");
        assertEquals(0, client.getId());
    }
}
