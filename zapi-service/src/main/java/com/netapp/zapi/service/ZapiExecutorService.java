package com.netapp.zapi.service;

import com.netapp.autozapi.client.ZapiTarget.Protocol;
import com.netapp.common.util.CollectionUtils;
import com.netapp.common.util.StringUtils;
import com.netapp.common.zapi.annotation.*;
import com.netapp.zapi.ZapiServiceApplication;
import com.netapp.zapi.model.GenericZapiRequest;
import com.netapp.zapi.zapi.OntapConnectionInfo;
import com.netapp.zapi.zapi.OntapConnectionProviderFactory;
import com.netapp.zapi.zapi.ZAPIConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.base.CaseFormat.*;


@SpringBootConfiguration
public class ZapiExecutorService {

    @Autowired
    private OntapConnectionProviderFactory ontapConnectionProviderFactory;


    public void getClusterSecurity() {

        String address = "10.193.48.51";
        String username = "admin";
        String password = "netapp1!";
        int port = 443;
        Protocol protocol = Protocol.HTTPS;

        ZAPIConnection zapiConnection = ontapConnectionProviderFactory.getOntapConnectionProvider(new OntapConnectionInfo(
                address,
                username,
                password,
                port,
                protocol)).getZAPIConnection();
    }


    public Object executeZapi(GenericZapiRequest genericZapiRequest){
        if(genericZapiRequest != null && genericZapiRequest.getZapiRequest() != null) {
            try {
                Class c = ZapiServiceApplication.APIMAP.get(genericZapiRequest.getZapiRequest());
                Object zapiRequest = c.getConstructor().newInstance();




                //query param
                if(!CollectionUtils.isEmpty(genericZapiRequest.getQueryParams()) && GetIterAPIRequest.class.isAssignableFrom(c)){
                    String zapiObject = genericZapiRequest.getZapiObject();
                    if(StringUtils.isEmpty(zapiObject)) {
                        Method m = Arrays.asList(c.getDeclaredMethods()).stream().filter(a -> {
                            if (a.isAnnotationPresent(Input.class)) {
                                if (a.getAnnotation(Input.class).value().equalsIgnoreCase("desired-attributes")) {
                                    return true;
                                }
                            }
                            return false;
                        }).findFirst().get();

                        if(m != null) {
                            zapiObject = m.getAnnotation(Type.class).value();
                        }
                    }
                    if(zapiObject != null) {
                        Class zapiObjClass = ZapiServiceApplication.TYPEDEFMAP.get(zapiObject);
                        Object zapiObj = zapiObjClass.getConstructor().newInstance();
                        setZapiObject(genericZapiRequest.getQueryParams(), zapiObj);
                        zapiRequest.getClass().getMethod("withQuery", Object.class).invoke(zapiRequest, zapiObj);
                    }
                }

                //inputParam
                if(!CollectionUtils.isEmpty(genericZapiRequest.getInputParams()) && !GetIterAPIRequest.class.isAssignableFrom(c)){
                    setZapiObject(genericZapiRequest.getInputParams(), zapiRequest);
                }

                String address = "10.193.48.51";
                String username = "admin";
                String password = "netapp1!";
                int port = 443;
                Protocol protocol = Protocol.HTTPS;
                ZAPIConnection zapiConnection = ontapConnectionProviderFactory.getOntapConnectionProvider(new OntapConnectionInfo(
                        address,
                        username,
                        password,
                        port,
                        protocol)).getZAPIConnection();

                if(GetIterAPIRequest.class.isAssignableFrom(c)) {
                    List<Object> apiResponse = zapiConnection.list((GetIterAPIRequest<?, Object>) zapiRequest);
                    return apiResponse;
                } else {
                    APIResponse apiResponse = zapiConnection.run((APIRequest)zapiRequest);
                    return apiResponse;
                }




            } catch ( NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private void setZapiObject(Map<String, Object> params, Object zapiObj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        for (String query : params.keySet()) {
            Object value = params.get(query);
            query = LOWER_CAMEL.to(LOWER_HYPHEN, query);
            if (value instanceof String) {
                zapiObj.getClass().getMethod("set" + StringUtils.initialUpperCase(LOWER_HYPHEN.to(UPPER_CAMEL,query)), String.class).invoke(zapiObj, value.toString());
            } else if (value instanceof Integer) {
                zapiObj.getClass().getMethod("set" + StringUtils.initialUpperCase(LOWER_HYPHEN.to(UPPER_CAMEL,query)), Integer.class).invoke(zapiObj, (int) value);
            } else if (value instanceof List) {
                zapiObj.getClass().getMethod("set" + StringUtils.initialUpperCase(LOWER_HYPHEN.to(UPPER_CAMEL,query)), List.class).invoke(zapiObj, (List<Object>) value);
            } else if (value instanceof Boolean) {
                zapiObj.getClass().getMethod("set" + StringUtils.initialUpperCase(LOWER_HYPHEN.to(UPPER_CAMEL,query)), Boolean.class).invoke(zapiObj, Boolean.TRUE.equals(value));
            } else if (value instanceof Map) {
                Class innClass = ZapiServiceApplication.TYPEDEFMAP.get(LOWER_CAMEL.to(LOWER_HYPHEN, query));
                Object innObj = innClass.getConstructor().newInstance();
                setZapiObject((Map<String, Object>) value, innObj);
                zapiObj.getClass().getMethod("set" + StringUtils.initialUpperCase(LOWER_HYPHEN.to(UPPER_CAMEL,query)), innClass).invoke(zapiObj, innObj);
            }
        }
    }
}
