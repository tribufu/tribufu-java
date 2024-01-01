// Copyright (c) Tribufu. All Rights Reserved

package com.tribufu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TribufuBotTest {
    @Test
    void defaultApi() {
        TribufuBot bot = new TribufuBot("0", "...");
        assertEquals("0", bot.getBotId());
    }
}
