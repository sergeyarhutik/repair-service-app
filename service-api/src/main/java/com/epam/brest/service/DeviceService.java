package com.epam.brest.service;

import com.epam.brest.model.Device;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Device Service Interface.
 */
@Component
public interface DeviceService {

    /**
     * Get all devices.
     *
     * @return list of all devices
     */
    List<Device> findAll();

    /**
     * Get device with specified id.
     *
     * @param id device id
     * @return device by id
     */
    Device findById(Integer id);

    /**
     * Update device.
     *
     * @param device device
     */
    void update(Device device);

    /**
     * Delete device with specified id.
     *
     * @param id device id
     */
    void delete(int id);

    /**
     * Add device.
     *
     * @param device device
     */
    Device addDevice(Device device);

    /**
     * Get devices by date.
     *
     * @return list of all devices
     */
    List<Device> filterDeviceByDate(Date fromDate, Date toDate);

}