package ca.vertigoshirley.echo.controllers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class Echo {
    

    @RequestMapping({""})
    @ResponseBody
    public String getContents
    (
        HttpServletRequest request,
        @RequestHeader MultiValueMap<String, String> headers,
        @RequestParam(required = false) MultiValueMap<String, String> uri,
        @RequestBody(required = false) String body
    )
    {
        
        StringBuilder getHtml = new StringBuilder("<!DOCTYPE html>\n");

        ArrayList<String> headerNames = new ArrayList<>();

        String verb = request.getMethod();

        getHtml.append("<head>\n\t<title>ECHO HTTP Server</title>\n</head>\n");
        getHtml.append("<body>\t<p>You have submitted a ");
        getHtml.append(verb);
        getHtml.append(" request.");
        getHtml.append("\t<p>Your request has ");
        getHtml.append(headers.size());
        getHtml.append(" distinct headers associated with it.");

        if (uri != null)
        {
            getHtml.append("\t<p>You have included ");
            getHtml.append(uri.size());
            getHtml.append(" distinct query parameters.");
        }

        if (body != null)
        {
            getHtml.append("\t<p>Your request included a body!  ");
            if (verb.toUpperCase().equals("GET") || verb.toUpperCase().equals("DELETE") || verb.toUpperCase().equals("OPTIONS")|| verb.toUpperCase().equals("HEAD"))
            {
                getHtml.append("This is undefined behaviour as part of the HTTP standard for ");
                getHtml.append(verb);
                getHtml.append(" requests.");
            }
            else if (verb.toUpperCase().equals("TRACE"))
            {
                getHtml.append("The TRACE HTTP method is explicitly disallowed from containing a body, you standards-violator you!");
            }
        }

        getHtml.append("<p>Your HTTP request looked something like this: \n<br>");

        getHtml.append("<div class=\"req\">");
        getHtml.append("<p>GET /");

        if (uri != null)
        {
            getHtml.append("?");
            for (String uriName : uri.keySet())
            {
                for (String value : uri.get(uriName))
                {
                    getHtml.append(uriName);
                    getHtml.append("=");
                    getHtml.append(value);
                    getHtml.append("&");
                }
            }
            getHtml.deleteCharAt(getHtml.length() - 1); 
        }
            

        getHtml.append(" HTTP/1.1<br>");

        for (String header : headers.keySet())
        {
            for (String value : headers.get(header))
            {
                getHtml.append(header);
                getHtml.append(": ");
                getHtml.append(value);
                getHtml.append("<br>");
            }
        }

        if (body != null)
        {
            getHtml.append("<br>");
            getHtml.append(body);
        }

        getHtml.append("</div>");
        

        getHtml.append("<style>.req {font-family:monospace; margin-bottom:0; margin: 0; padding-top:0;}</style>");

        return getHtml.toString();
    }
}
