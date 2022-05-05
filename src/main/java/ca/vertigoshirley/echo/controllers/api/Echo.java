package ca.vertigoshirley.echo.controllers.api;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.vertigoshirley.echo.serde.EchoJson;

@RestController(value = "echoApi")
@RequestMapping("/api")
public class Echo {

    @RequestMapping({"","/"})
    @ResponseBody
    public EchoJson getContents
    (
        HttpServletRequest request,
        @RequestHeader MultiValueMap<String, String> headers,
        @RequestParam(required = false) MultiValueMap<String, String> uri,
        @RequestBody(required = false) String body
    )
    {
        EchoJson response = new EchoJson();

        ArrayList<String> headerNames = new ArrayList<>();

        String verb = request.getMethod();

        response.title = "ECHO HTTP Server";
        response.requestTypeIntro = "You have submitted a " + verb + " request.";

        response.headerCountStatement = "Your request has " + headers.size() + " distinct headers associated with it.";

        if (uri != null)
        {
            response.uriCountStatement = "You have included " + uri.size() + " distinct query parameters.";
        }

        if (body != null)
        {
            response.bodyStatement = "Your request included a body!";
            if (verb.toUpperCase().equals("GET") || verb.toUpperCase().equals("DELETE") || verb.toUpperCase().equals("OPTIONS")|| verb.toUpperCase().equals("HEAD"))
            {
                response.bodyWarning = "This is undefined behaviour as part of the HTTP standard for " + verb + " requests.";
            }
            else if (verb.toUpperCase().equals("TRACE"))
            {
                response.bodyWarning = "The TRACE HTTP method is explicitly disallowed from containing a body, you standards-violator you!";
            }
        }

        response.rawRequestIntro = "Your HTTP request looked something like this:";

        StringBuilder builder = new StringBuilder("GET /api");

        if (uri != null)
        {
            builder.append("?");
            for (String uriName : uri.keySet())
            {
                for (String value : uri.get(uriName))
                {
                    builder.append(uriName);
                    builder.append("=");
                    builder.append(value);
                    builder.append("&");
                }
            }
            builder.deleteCharAt(builder.length() - 1); 
        }

        builder.append(" HTTP/1.1");
        response.greeting = builder.toString();

        builder.setLength(0);;
        for (String header : headers.keySet())
        {
            for (String value : headers.get(header))
            {
                response.headers.add(header + ": " + value);
            }
        }

        if (body != null)
        {
            response.body = body;
        }

        return response;
    }
}
