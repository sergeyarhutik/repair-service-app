package com.epam.brest.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientTest {

    private static final Integer CLIENT_ID = 1;
    private static final String CLIENT_NAME = "clientName";

    private Client client = new Client();

    @Test
    void getClientId() {
        client.setClientId(CLIENT_ID);
        Assertions.assertEquals(client.getClientId(), CLIENT_ID);
    }

    @Test
    void getClientName() {
        client.setClientName(CLIENT_NAME);
        Assertions.assertEquals(client.getClientName(), CLIENT_NAME);
    }

    /**
     * Constructor Client Test
     */
    ClientTest() {
        Client client = new Client(CLIENT_NAME);
        Assertions.assertEquals(client.getClientName(), CLIENT_NAME);
    }

    @Test
    void testToString() {
        client.setClientId(CLIENT_ID);
        client.setClientName(CLIENT_NAME);
        String expectedResponseClient = "Client{"
                + "clientId=" + CLIENT_ID
                + ", clientName='" + CLIENT_NAME + '\''
                + '}';
    }
}
