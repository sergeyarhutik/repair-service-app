package com.epam.brest.rest_app;

import com.epam.brest.model.Device;
import com.epam.brest.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
public class DeviceRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceRestController.class);

    @Autowired
    private DeviceService deviceService;

    @GetMapping(value = "/devices")
    public List<Device> findAll() {
        LOGGER.debug("get all devices");
        return deviceService.findAll();
    }

    @GetMapping(value = "/devices/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Device findById(@PathVariable Integer id) {
        LOGGER.debug("find device by id({})", id);
        return deviceService.findById(id);
    }

    @PutMapping(value = "/devices")
    public final void update(@RequestBody final Device device) {
        LOGGER.debug("update device ({})", device);
        deviceService.update(device);
    }

    @DeleteMapping(value = "/devices/{id}")
    public void delete(@PathVariable("id") int id) {
        LOGGER.debug("delete device ({})", id);
        deviceService.delete(id);
    }

    @PostMapping(value = "/devices")
    @ResponseStatus(HttpStatus.CREATED)
    public Device addDevice(@RequestBody Device device) {
        LOGGER.debug("add device({})", device);
        return deviceService.addDevice(device);
    }

    /**
     * Filtration devices by date.
     *
     * @param fromDate - start date.
     * @param toDate   - end date.
     * @return - list of devices.
     * @throws ParseException - exception for parsing date.
     */
    @GetMapping(value = "/devices/{fromDate}/{toDate}")
    public List<Device> filterDeviceByDate(
            @PathVariable(value = "fromDate") String fromDate,
            @PathVariable(value = "toDate") String toDate)
            throws ParseException {
        LOGGER.debug("REST-server filterByDate({} - {})", fromDate, toDate);
        SimpleDateFormat formatDate
                = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",
                Locale.US);
        Date startDate = formatDate.parse(fromDate);
        Date endDate = formatDate.parse(toDate);
        return deviceService.filterDeviceByDate(startDate, endDate);
    }
}
