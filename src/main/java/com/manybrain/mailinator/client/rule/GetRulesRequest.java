package com.manybrain.mailinator.client.rule;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.manybrain.mailinator.client.Request;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.manybrain.mailinator.client.JerseyClient.CLIENT;
import static com.manybrain.mailinator.client.Utils.emptyIfNull;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class GetRulesRequest
        implements Request<Rules>
{

    private static final String URL = "https://api.mailinator.com/streams/{stream_id}/rules/";

    private static final WebTarget WEB_TARGET = CLIENT.target(URL);

    @NonNull
    private final String streamId;

    public Rules execute(String apiToken)
    {
        WebTarget webTarget = WEB_TARGET.resolveTemplate("stream_id", emptyIfNull(streamId));

        return webTarget.request(MediaType.APPLICATION_JSON_TYPE)
                        .header(AUTHORIZATION, apiToken)
                        .get(Rules.class);
    }


}