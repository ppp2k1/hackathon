package com.netapp.security.vm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VmRepository extends JpaRepository<Vm, Long> {
    public Vm findByVmName(@Param("vmName") String vmName);
}