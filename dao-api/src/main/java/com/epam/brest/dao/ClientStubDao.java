package com.epam.brest.dao;

import com.epam.brest.model.stub.ClientStub;

import java.util.List;

/**
 * ClientStub DAO Interface.
 */
public interface ClientStubDao {

    /**
     * Get all clients with avg salary by client.
     *
     * @return clients list.
     */
    List<ClientStub> findAllWithDevices();

}
