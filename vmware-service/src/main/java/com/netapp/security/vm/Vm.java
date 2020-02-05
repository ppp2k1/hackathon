package com.netapp.security.vm;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@Entity
//@NamedQuery(name = "Vm.findByName", query = "SELECT id FROM vm.vms WHERE vmname = ?1")
@Table(name = "vms")
@EntityListeners(AuditingEntityListener.class)
public class Vm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "vmname", nullable = false)
    private String vmName;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "firewall_on", nullable = false)
    private String firewallOn;

    public long getId() {
        return id;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setFirewallOn(String firewallOn) {
        this.firewallOn = firewallOn;
    }

    public String getVmName() {
        return vmName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getFirewallOn() {
        return firewallOn;
    }
    public void setId(long id) {
        this.id = id;
    }
}
