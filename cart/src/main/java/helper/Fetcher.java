package helper;

import com.google.appengine.api.modules.ModulesServiceFactory;
import com.google.appengine.api.urlfetch.*;
import com.google.gson.Gson;
import model.Item;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Jean-Baptiste Clion on 15.06.2016.
 */
public class Fetcher {

    private static final URLFetchService urlFetchService = URLFetchServiceFactory.getURLFetchService();

    private static final Gson GSON = new Gson();


    public static Item getItem(HttpHeaders hh, Long itemId) throws IOException {
        Item toReturn = null;

        Item item = prefetch(buildServiceUrl("catalog", "1", "/rest/catalog/item/" + itemId), HTTPMethod.GET, hh, Item.class);
        if (item != null) { toReturn = item;}

        return toReturn;
    }

    private static String buildServiceUrl(String service, String version, String endpoint){

        String toReturn = null;

        String baseUrl = ModulesServiceFactory.getModulesService().getVersionHostname(service, version);

        if (baseUrl != null && !baseUrl.isEmpty() && endpoint != null && !endpoint.isEmpty()) {
            toReturn = "http://" + baseUrl + endpoint;
        }

        return toReturn;
    }


    private static <T> T prefetch(String url, HTTPMethod httpMethod, HttpHeaders hh, Class<T> type) throws IOException {
        T toReturn = null;

        if (url != null && !url.isEmpty() && httpMethod != null) {
            toReturn = fetch(url, httpMethod, hh, type);
        }

        return toReturn;
    }

    private static <T> T fetch(String url, HTTPMethod method, HttpHeaders hh, Class<T> type) throws IOException {

        T toReturn = null;

        if (url != null && !url.isEmpty()) {

            URL serviceUrl = new URL(url);

            if (method != null) {

                FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
                fetchOptions.validateCertificate();
                fetchOptions.doNotFollowRedirects();
                fetchOptions.setDeadline(30D);
                HTTPRequest request = new HTTPRequest(serviceUrl, method, fetchOptions);

                if (hh != null) {

                    MultivaluedMap<String, String> requestHeaders = hh.getRequestHeaders();

                    if (requestHeaders != null) {

                        for (String key : hh.getRequestHeaders().keySet()) {
                            List<String> values = requestHeaders.get(key);

                            if(values != null){

                                for (String value : values) {
                                    request.addHeader(new HTTPHeader(key, value));
                                }

                            }

                        }

                        HTTPResponse httpResponse = urlFetchService.fetch(request);

                        if (httpResponse != null) {

                            if (httpResponse.getResponseCode() == 200) {

                                byte[] content = httpResponse.getContent();

                                if (content != null) {

                                    String response = new String(httpResponse.getContent());

                                    if (response != null) {
                                        toReturn  = GSON.fromJson(response, type);
                                    }

                                }

                            }
                        }
                    }
                }

            }

        }

        return toReturn;

    }

}