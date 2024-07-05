package com.billcomconsulting.sav.services.implementation;

import com.billcomconsulting.sav.entities.Device;
import com.billcomconsulting.sav.repositories.DeviceRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceDevice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class DeviceService implements IServiceDevice {

    DeviceRepository deviceRepository ;

    @Override
    public Device addDevice(Device device) {


        log.info("Device was successfully added !");
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> getAllDevice() {return deviceRepository.findAll();}




    @Override
    public boolean deleteDevice(Long idDevice) {
        Device existingDevice = deviceRepository.findById(idDevice).orElse(null);
        if(existingDevice!=null) {
            deviceRepository.delete(existingDevice);
            log.info("Device deleted");
            return true ;
        }
        log.info(" this Device is not existing");
        return false;
    }


    @Override
    public Device updateDevice(Long idDevice) {
        Device device = deviceRepository.findById(idDevice).orElse(null);
        if (device != null) {
            deviceRepository.save(device);
            log.info("Device was successfully updated !");
        }
        log.info("Device not found !");
        return  device;

    }



}
