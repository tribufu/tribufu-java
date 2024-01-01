// Copyright (c) Tribufu. All Rights Reserved

package com.tribufu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TribufuServerTest {
    @Test
    void defaultApi() {
        TribufuServer server = new TribufuServer("0", "1", "...");
        assertEquals("0", server.getServerId());
        assertEquals("1", server.getClientId());
    }
}
