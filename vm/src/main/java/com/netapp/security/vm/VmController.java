package com.netapp.security.vm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vmware/")
public class VmController {

    @Autowired
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
    }

    @GetMapping("/config")
    public ResponseEntity<Vm> getVmsByName(@RequestParam(value= "vmName") String vmName)  {
        Vm vm = vmRepository.findByVmName(vmName);
        return ResponseEntity.ok().body(vm);
    }

    @PostMapping("/vms")
    public Vm createVm(@Valid @RequestBody Vm vm) {
        return vmRepository.save(vm);
    }

}