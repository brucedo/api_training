package ca.vertigoshirley.echo.serde;

import java.util.ArrayList;
import java.util.List;

public class EchoJson {

    public EchoJson()
    {
        headers = new ArrayList<>();
    }
    
    public String title;

    public String requestTypeIntro;
    public String headerCountStatement;
    public String uriCountStatement;
    public String bodyStatement;
    public String bodyWarning;

    public String rawRequestIntro;
    public String greeting;
    public List<String> headers;
    public String body;
    

}
