package com.billcomconsulting.sav.services.interfaces;


import com.billcomconsulting.sav.entities.Device;

import java.util.List;

public interface IServiceDevice {

    public Device addDevice(Device device);
    public List<Device> getAllDevice() ;
    public  boolean deleteDevice(Long idDevice) ;
    public Device updateDevice (Long idDevice) ;

}
