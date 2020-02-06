package com.netapp.security.vm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/vmware/")
public class VmController {

    /*@Autowired
    private VmRepository vmRepository;

    @GetMapping("/vms")
    public List<Vm> getAllvms() {
        return vmRepository.findAll();
    }

    @GetMapping("/vms/{id}")
    public ResponseEntity<Vm> getVmsById(@PathVariable(value = "id") Long vmId)
            throws ResourceNotFoundException {
        Vm vm = vmRepository
                        .findById(vmId)
                        .orElseThrow(() -> new ResourceNotFoundException("vm not found."));
        return ResponseEntity.ok().body(vm);
    }*/

    @GetMapping("/config")
    public ResponseEntity<Vm> getVmsByName(@RequestParam(value= "vmName") String vmName)  {
       /* Vm vm = new Vm(); //vmRepository.findByVmName(vmName);
        vm.setId(134);
        vm.setIpAddress("10.129.238.10");
        vm.setVmName(vmName);*/
        Host host = new Host("10.226.179.41", "yes", "yes", "DROP");
        Datastore datastore = new Datastore("nfs", "Test_W1", "/vmfs/volumes/139d6349-d60c3822", "10.193.48.51",
                "/Test_W1");
        Vmdk vmdk1 =new Vmdk(vmName, "/vmfs/volumes/139d6349-d60c3822/" + vmName + "/" + vmName  + ".vmdk",
                "Test_W1");
        Vmdk vmdk2 =new Vmdk(vmName + "_db", "/vmfs/volumes/139d6349-d60c3822/" + vmName + "_db/" + vmName  + "_db.vmdk",
                "Test_W1");
        List<Vmdk> vmdkList = new ArrayList<>();
        vmdkList.add(0, vmdk1);
        vmdkList.add(1, vmdk2);

        Vm vm = new Vm(134, vmName, "10.129.238.10", host, datastore, vmdkList);
        return ResponseEntity.ok().body(vm);
    }

}