package com.epam.brest.dao;

import com.epam.brest.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@PropertySource("classpath:sql.properties")
@Component
@Repository
public class DeviceDaoJdbcImpl implements DeviceDao {


    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceDaoJdbcImpl.class);

    /**
     * NamedParameterJdbcTemplate.
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * SQL query for select all devices.
     */
    @Value("${device.findAll}")
    private String findAllSql;

    /**
     * SQL query for select device by id.
     */
    @Value("device.findById")
    private String findByIdSql;

    /**
     * SQL query for insert device.
     */
    @Value("${device.insert}")
    private String insertSql;

    /**
     * SQL query for update device.
     */
    @Value("${device.update}")
    private String updateSql;

    /**
     * SQL query for delete device.
     */
    @Value("${device.delete}")
    private String deleteSql;

    /**
     * SQL query for fiter devices by date.
     */
    @Value("${device.filter}")
    private String filterSql;

    /**
     * Column deviceId in device table DB.
     */
    private static final String DEVICE_ID = "deviceId";

    /**
     * Column deviceName in device table DB.
     */
    private static final String DEVICE_NAME = "deviceName";

    /**
     * Column clientId in device table DB.
     */
    private static final String PARENT_ID = "parentId";

    /**
     * Column deviceDate in device table DB.
     */
    private static final String DEVICE_DATE = "deviceDate";

    /**
     * Column deviceDescription in device table DB.
     */
    private static final String DEVICE_DESCRIPTION = "deviceDescription";

    /**
     * fromDate for SQL query.
     */
    private static final String FROM_DATE = "fromDate";

    /**
     * toDate for SQL query.
     */
    private static final String TO_DATE = "toDate";

    public DeviceDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public final Device addDevice(final Device device) {
        LOGGER.debug("addDevice({})", device);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DEVICE_NAME, device.getDeviceName());
        parameters.addValue(PARENT_ID, device.getParentId());
        parameters.addValue(DEVICE_DATE, device.getDeviceDate());
        parameters.addValue(DEVICE_DESCRIPTION, device.getDeviceDescription());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, parameters, generatedKeyHolder);
        device.setDeviceId(generatedKeyHolder.getKey().intValue());
        return device;
    }

    @Override
    public void update(Device device) {
        if (namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(device)) < 1) {
            throw new EmptyResultDataAccessException(
                    String.format("Failed to update. '%s' not found in the DB", device), 1);
        }
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer deviceId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(DEVICE_ID, deviceId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete device from DB"));
    }

    @Override
    public List<Device> findAll() {
        List<Device> devices = namedParameterJdbcTemplate.query(findAllSql, new DeviceRowMapper());
        return devices;
    }

    @Override
    public Optional<Device> findById(Integer deviceId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEVICE_ID, deviceId);
        List<Device> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Device.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class DeviceRowMapper implements RowMapper<Device> {
        @Override
        public Device mapRow(ResultSet resultSet, int i) throws SQLException {
            Device device = new Device();
            device.setDeviceId(resultSet.getInt("device_id"));
            device.setDeviceName(resultSet.getString("device_name"));
            device.setParentId(resultSet.getInt("parent_id"));
            device.setDeviceDate(resultSet.getDate("device_date"));
            device.setDeviceDescription(resultSet.getNString("device_description"));
            return device;
        }
    }

    @Override
    public final List<Device> filterDeviceByDate(Date fromDate, Date toDate) {
        LOGGER.debug("filterDeviceByDate({}, {})", fromDate, toDate);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(FROM_DATE, fromDate).addValue(TO_DATE, toDate);
        List<Device> devices = namedParameterJdbcTemplate.query(filterSql, namedParameters, new DeviceRowMapper());
        LOGGER.debug("filteredDevices({})", devices);
        return devices;
    }
}
