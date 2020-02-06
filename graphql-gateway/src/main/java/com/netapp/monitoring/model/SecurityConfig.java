package com.netapp.monitoring.model;

import com.netapp.monitoring.model.Ontap;
import com.netapp.monitoring.model.VmWare;

public class SecurityConfig {
    String name;
    VmWare vmWare;
    Ontap ontap;

    public SecurityConfig() {
    }

    public SecurityConfig(String name, VmWare vmWare, Ontap ontap) {
        this.name = name;
        this.vmWare = vmWare;
        this.ontap = ontap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VmWare getVmWare() {
        return vmWare;
    }

    public void setVmWare(VmWare vmWare) {
        this.vmWare = vmWare;
    }

    public Ontap getOntap() {
        return ontap;
    }

    public void setOntap(Ontap ontap) {
        this.ontap = ontap;
    }
}
