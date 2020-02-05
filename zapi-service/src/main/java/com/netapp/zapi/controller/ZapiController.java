package com.netapp.zapi.controller;

import com.netapp.zapi.model.GenericZapiRequest;
import com.netapp.zapi.service.ZapiExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZapiController {

    @Autowired
    private ZapiExecutorService zapiExecutorService;

    @PostMapping("/execute/zapi")
    public Object getTest(@RequestBody GenericZapiRequest genericZapiRequest) {
        Object o = zapiExecutorService.executeZapi(genericZapiRequest);
        return ResponseEntity.ok(o);
    }

}
