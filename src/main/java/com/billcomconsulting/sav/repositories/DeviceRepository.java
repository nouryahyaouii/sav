package com.billcomconsulting.sav.repositories;

import com.billcomconsulting.sav.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {
}
