package com.mgmtp.blog.captcha;
import com.fasterxml.jackson.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "success", "challenge_ts", "hostname", "error-codes" })

public class GoogleResponse {
	 
    @JsonProperty("success")
    private boolean success;
     
    @JsonProperty("challenge_ts")
    private String challengeTs;
     
    @JsonProperty("hostname")
    private String hostname;
     
    @JsonProperty("error-codes")
    private String[] errorCodes;
    
    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }


}