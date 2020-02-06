package com.netapp.zapi.zapi;

import com.netapp.autozapi.ApiExecutionException;
import com.netapp.autozapi.client.ZapiResponse;
import com.netapp.autozapi.client.ZapiRunner;
import com.netapp.common.zapi.annotation.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.xml.transform.Source;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class ZAPIConnection {

    private final ZapiRunner zapiRunner;

    /**
     * Default max record count for *-get-iter ZAPIs
     */
    private final int defaultIterBatchSize;

    ZAPIConnection(ZapiRunner zapiRunner, int defaultIterBatchSize) {
        this.zapiRunner = zapiRunner;
        this.defaultIterBatchSize = defaultIterBatchSize;
    }

    public ZapiRunner getZapiRunner() {
        return zapiRunner;
    }

    /**
     * Executes the given zapi request.
     *
     * Only a certain number of zapi requests can be executed in parallel for a given target,
     * so this method might block for a short period of time before running the zapi.
     *
     */
    public <S extends APIRequest<T>, T extends APIResponse<S>> T run(S zapiRequest) {
        boolean isRequestFailed = true;
        try {
            T apiResponse = zapiRunner.withOriginatorId(getOriginatorId()).run(zapiRequest);
            isRequestFailed = false;
            return apiResponse;
        } catch (ApiExecutionException e) {
            throw new ApiExecutionException(e);
        } finally {
            logOriginatorInfo(zapiRequest, isRequestFailed);
        }
    }

    public ZapiResponse invoke(Source source) {
        return zapiRunner.invoke(source);
    }

    public URL getUrl() {
        return zapiRunner.getUrl();
    }

    /**
     * Returns a <b>new</b> <tt>ZAPIConnection</tt> instance with the state set to tunnel apis to a vFiler
     */
    public ZAPIConnection withVFilerName(String vFilerName) {
        return new ZAPIConnection(zapiRunner.withVFiler(vFilerName), this.defaultIterBatchSize);
    }

    /**
     * Returns a <b>new</b> <tt>ZAPIConnection</tt> instance with the state set to tunnel apis to a vserver
     */
    public ZAPIConnection withVserverName(String vserverName) {
        return new ZAPIConnection(zapiRunner.withVServer(vserverName), this.defaultIterBatchSize);
    }

    /**
     * Returns a <b>new</b> <tt>ZAPIConnection</tt> instance with the provided read timeout setting.
     * @see ZapiRunner#withReadTimeout(int)
     */
    public ZAPIConnection withReadTimeout(int timeout) {
        return new ZAPIConnection(zapiRunner.withReadTimeout(timeout), this.defaultIterBatchSize);
    }

    /**
     * Returns a <b>new</b> <tt>ZAPIConnection</tt> instance with the provided connect timeout setting.
     * @see ZapiRunner#withConnectTimeout(int)
     */
    public ZAPIConnection withConnectTimeout(int timeout) {
        return new ZAPIConnection(zapiRunner.withConnectTimeout(timeout), this.defaultIterBatchSize);
    }

    public <T extends GetIterAPIResponse<?, ?>, S> Iterator<S> iterate(GetIterAPIRequest<?, S> startRequest) {
        boolean isRequestFailed = true;
        if (startRequest.getMaxRecords() == null) {
            startRequest.setMaxRecords(this.defaultIterBatchSize);
        }
        try {
            Iterator<S> getIterAPIResponse = zapiRunner.withOriginatorId(getOriginatorId()).iterate(startRequest);
            isRequestFailed = false;
            return getIterAPIResponse;
        } catch (ApiExecutionException e) {
            throw new ApiExecutionException(e);
        } finally {
            logOriginatorInfo(startRequest, isRequestFailed);
        }
    }

    public <T extends GetIterAPIResponse<?, ?>, S> List<S> list(GetIterAPIRequest<?, S> startRequest) {
        boolean isRequestFailed = true;
        if (startRequest.getMaxRecords() == null) {
            startRequest.setMaxRecords(this.defaultIterBatchSize);
        }
        try {
            List<S> getIterAPIResponses = this.zapiRunner.withOriginatorId(getOriginatorId()).list(startRequest);
            isRequestFailed = false;
            return getIterAPIResponses;
        } catch (ApiExecutionException e) {
            throw new ApiExecutionException(e);
        } finally {
            logOriginatorInfo(startRequest, isRequestFailed);
        }
    }

    /*
     * If the Originator ID is stored on the MDC map, add the user information on the DFM audit log.
     * else skip the log because this may be a DFM internal call.
     */
    @SuppressWarnings("deprecation")
    private void logOriginatorInfo(Object request, boolean isRequestFailed) {
            String apiName;
            Api apiAnnotation = request.getClass().getAnnotation(Api.class);
            try {
                apiName = apiAnnotation != null ? apiAnnotation.value() : request.getClass().getSimpleName();
            } catch (Exception ex) {
                apiName = request.getClass().getSimpleName();
            }

            StringBuilder logMessage = new StringBuilder("OnTapApi:Result=")
                    .append(isRequestFailed ? "Failure:" : "Success:")
                    .append(apiName).append(":");
            ObjectMapper objectMapper = new ObjectMapper();
            /*
             * Need to disable 'SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS' to avoid the 'JsonMappingException'
             * when the request object does not have any public attributes (e.g., system-get-version OnTap API).
             */
            objectMapper.getSerializationConfig().set(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
            try {
                logMessage.append(objectMapper.writeValueAsString(request));
            } catch (Exception e) {
                logMessage.append("-- cannot parse the zapi request");
            }
    }

    private String getOriginatorId() {
        String originatorAppName = "AIQ_UM";
        return originatorAppName;
    }
}
