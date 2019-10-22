package com.epam.brest.model;

import javax.persistence.Entity;

/**
 * POJO Client for model.
 */
@Entity
public class Client {

    private Integer clientId;

    private String clientName;

    public Client() {
    }

    /**
     * Constructor with client name.
     *
     * @param clientName client name
     */
    public Client(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Returns <code>Integer</code> representation of this clientId.
     *
     * @return clientId Client Id.
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * Sets the client's identifier.
     *
     * @param clientId Client Id.
     */
    public void setClientId(final Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * Returns <code>String</code> representation of this clientName.
     *
     * @return clientName Client Name.
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the client's name.
     *
     * @param clientName Client Name.
     */
    public Client setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Client{"
                + "clientId=" + clientId
                + ", clientName='" + clientName + '\''
                + '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client client = (Client) o;

        if (!clientId.equals(client.clientId)) {
            return false;
        }
        return clientName != null
                ? clientName.equals(client.clientName)
                : client.clientName == null;
    }

    @Override
    public final int hashCode() {
        int result = clientId;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        return result;
    }
}