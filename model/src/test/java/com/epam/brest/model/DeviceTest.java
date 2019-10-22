package com.epam.brest.model;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DeviceTest {

    private static final Integer DEVICE_ID = 1;
    private static final String DEVICE_NAME = "deviceName";
    private static final String DEVICE_DESCRIPTION = "deviceDescription";
    private static final Integer PARENT_ID = 1;
    private static final String DEVICE_DATE = "2019-07-07-00:00:00";

    private Device device = new Device();

    @Test
    public void getDeviceId() {
        device.setDeviceId(DEVICE_ID);
        Assertions.assertEquals(device.getDeviceId(), DEVICE_ID);
    }

    @Test
    public void getDeviceName() {
        device.setDeviceName(DEVICE_NAME);
        Assertions.assertEquals(device.getDeviceName(), DEVICE_NAME);
    }

    @Test
    public void getParentId() {
        device.setParentId(PARENT_ID);
        Assertions.assertEquals(device.getParentId(), PARENT_ID);
    }

    @Test
    public void getDeviceDescription() {
        device.setDeviceDescription(DEVICE_DESCRIPTION);
        Assertions.assertEquals(device.getDeviceDescription(), DEVICE_DESCRIPTION);
    }

    @Test
    public void getDeviceDate() throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.US);
        Date date = formatDate.parse(DEVICE_DATE);
        device.setDeviceDate(date);
        Assertions.assertEquals(device.getDeviceDate(), date);
    }
}