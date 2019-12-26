package utils;

import io.mola.galimatias.GalimatiasParseException;
import io.mola.galimatias.URL;

import java.net.URISyntaxException;

public class URLUtils {

    public static String decodeUrl(String urlToDecode) {
        try {
            return URL.parse(urlToDecode).toJavaURI().toString();
        } catch (URISyntaxException | GalimatiasParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
