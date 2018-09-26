package json;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.InputStream;

/**
 * Class for handling json, converting to and from
 * Created by Admin on 3/6/17.
 */

public class json {

    private Gson gson;

    public json() {
        gson = new Gson();
    }

    public String encode(Object result)
    {
        return gson.toJson(result);
    }

    public Object decode(HttpExchange exchange, Class objClass)
    {
        InputStream is = exchange.getRequestBody();
        return gson.fromJson(is.toString(),objClass);
    }
}

