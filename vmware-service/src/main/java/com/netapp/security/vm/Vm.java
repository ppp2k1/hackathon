package com.netapp.security.vm;

/*import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;*/
import java.util.Date;

/*@Entity
//@NamedQuery(name = "Vm.findByName", query = "SELECT id FROM vm.vms WHERE vmname = ?1")
@Table(name = "vms")
@EntityListeners(AuditingEntityListener.class)*/

import java.util.*;
public class Vm {

   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    private long id;

    /*@Column(name = "vmname", nullable = false)*/
    private String vmName;

    /*@Column(name = "ip_address", nullable = false)*/
    private String ipAddress;

    public Host host;
    public Datastore datastore;
    public List<Vmdk>  vmdk;

    public long getId() {
        return id;
    }

    public String getVmName() {
        return vmName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Host getHost() {
        return host;
    }

    public Datastore getDatastore() {
        return datastore;
    }


    public List<Vmdk> getVmdk() {
        return vmdk;
    }

    public Vm(long id, String vmName, String ipAddress, Host host, Datastore datastore, List<Vmdk> vmdk) {
        this.id = id;
        this.vmName = vmName;
        this.ipAddress = ipAddress;
        this.host = host;
        this.datastore = datastore;
        this.vmdk = vmdk;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVmdk(List<Vmdk> vmdk) {
        this.vmdk = vmdk;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

}
