package com.epam.brest.service;

import com.epam.brest.dao.ClientDao;
import com.epam.brest.dao.ClientStubDao;
import com.epam.brest.model.Client;
import com.epam.brest.model.stub.ClientStub;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;

    private ClientStubDao clientStubDao;

    public ClientServiceImpl(ClientDao clientDao, ClientStubDao clientStubDao) {
        this.clientDao = clientDao;
        this.clientStubDao = clientStubDao;
    }

    @Override
    public List<Client> findAll()
            throws DataAccessException {
        return clientDao.findAll();
    }

    @Override
    public List<ClientStub> findAllWithDevices()
            throws DataAccessException {
        return clientStubDao.findAllWithDevices();
    }

    @Override
    public Client findById(Integer id)
            throws DataAccessException {
        return clientDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to get client from DB"));
    }

    @Override
    public void update(Client client)
            throws DataAccessException {
        clientDao.update(client);
    }

    @Override
    public void delete(int id)
            throws DataAccessException {
        clientDao.delete(id);
    }

    @Override
    public Client add(Client client)
            throws DataAccessException {
        return clientDao.add(client);
    }
}
