package com.billcomconsulting.sav.controllers;


import com.billcomconsulting.sav.entities.Device;
import com.billcomconsulting.sav.exceptions.ResourceNotFoundException;
import com.billcomconsulting.sav.repositories.DeviceRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceDevice;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/device" , produces = "application/json" , consumes = "application/json")
@AllArgsConstructor
public class DeviceController {

    DeviceRepository deviceRepository;
    IServiceDevice iServiceDevice;


    @GetMapping(value="/getallDevices", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<Device> getAllDevice() {
        return deviceRepository.findAll();
    }



    @PostMapping("/addDevice")
    @ResponseBody
    public ResponseEntity<?> createDevice(@RequestBody Device device) throws IOException {

        Device saveddevice = iServiceDevice.addDevice(device);
        return ResponseEntity.ok(saveddevice);
    }



    @PutMapping(value = "/UPDATE_Device/{id}", consumes = MediaType.ALL_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Device> updateDevice(@PathVariable long id, @RequestParam(required = false) String imei,
                                               @RequestParam(required = false) Date guarantee,
                                               @RequestParam(required = false) String guarante,
                                               @RequestParam(required = false) String nbRetourSav,
                                               @RequestParam(required = false) String status,
                                               @RequestParam(required = false) String batteryImei,
                                               @RequestParam(required = false) String brand,
                                               @RequestParam(required = false) String model,
                                               @RequestParam(required = false) String terminal
    ) throws Exception {


        String ddevice = "{\"imei\": \"" + imei + "\",   \"guarante\": \"" + guarante + "\",   \"guarantee\": \"" + guarantee + "\",   \"nbRetourSav\": \"" + nbRetourSav + "\",   \"status\": \"" + status + "\",   \"batteryImei\": \"" + batteryImei + "\",   \"terminal\": \"" + terminal + "\",   \"brand\": \"" + brand + "\",   \"model\": \"" + model + "\" }";

        Device ogdevice = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found for this id :: " + id));

        System.out.println(ogdevice);

        ObjectMapper objectMapper = new ObjectMapper();
        Device device = objectMapper.readValue(ddevice, Device.class);


        if (imei != null) {
            ogdevice.setImei(device.getImei());
        }

        if (guarante != null) {
            ogdevice.setGuarante(device.getGuarante());
        }
        if (guarantee != null) {
            ogdevice.setGuarantee(device.getGuarantee());
        }

        if (nbRetourSav != null) {
            ogdevice.setNbRetourSav(device.getNbRetourSav());
        }

        if (brand != null) {
            ogdevice.setBrand(device.getBrand());
        }
        if (model != null) {
            ogdevice.setModel(device.getModel());
        }

        if (status != null) {
            ogdevice.setStatus(device.getStatus());
        }

        if (batteryImei != null) {
            ogdevice.setBatteryImei(device.getBatteryImei());
        }

        if (terminal != null) {
            ogdevice.setTerminal(device.getTerminal());
        }


        iServiceDevice.addDevice(ogdevice);

        return ResponseEntity.ok(ogdevice);
    }









    @DeleteMapping(value="/deleteDevice/{idDevice}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public  boolean deleteDevice(@PathVariable Long idDevice)  {return  iServiceDevice.deleteDevice(idDevice);}



    @GetMapping(value="/getDeviceby/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Device> getDeviceById(@PathVariable(value = "id") Long deviceId)
            throws ResourceNotFoundException {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("device not found for this id :: " + deviceId));
        System.out.println(device.toString());
        return ResponseEntity.ok().body(device);
    }





}
