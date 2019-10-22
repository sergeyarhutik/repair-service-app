package com.epam.brest.model.stub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientStubTest {

    private static final Integer CLIENT_ID = 1;
    private static final String CLIENT_NAME = "clientName";
    private static final Integer COUNT_OF_DEVICES = 11;

    private ClientStub clientStub = new ClientStub();

    @Test
    void getClientId() {
        clientStub.setClientId(CLIENT_ID);
        Assertions.assertEquals(clientStub.getClientId(), CLIENT_ID);
    }

    @Test
    void getClientName() {
        clientStub.setClientName(CLIENT_NAME);
        Assertions.assertEquals(clientStub.getClientName(), CLIENT_NAME);
    }

    @Test
    void getCountOfDevices() {
        clientStub.setcountOfDevices(COUNT_OF_DEVICES);
        Assertions.assertEquals(clientStub.getcountOfDevices(), COUNT_OF_DEVICES);
    }

    @Test
    void testToString() {
        clientStub.setClientId(CLIENT_ID);
        clientStub.setClientName(CLIENT_NAME);
        clientStub.setcountOfDevices(COUNT_OF_DEVICES);
        String expectedResponseClient = "Client{"
                + "clientId=" + CLIENT_ID
                + ", clientName='" + CLIENT_NAME + '\''
                + ", countOfDevices=" + COUNT_OF_DEVICES
                + '}';
    }

}
