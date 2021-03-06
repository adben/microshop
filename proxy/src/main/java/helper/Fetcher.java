package helper;

import com.google.appengine.api.modules.ModulesServiceFactory;
import com.google.appengine.api.urlfetch.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Cart;
import model.Item;
import model.Order;

import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean-Baptiste Clion on 15.06.2016.
 */
public class Fetcher {

    private static final URLFetchService urlFetchService = URLFetchServiceFactory.getURLFetchService();

    private static final Gson GSON = new Gson();

    public static List<Item> getCatalog(HttpHeaders hh) throws IOException {
        List<Item> toReturn = new ArrayList<>();

        String serviceUrl = buildServiceUrl("catalog", "1", "/rest/catalog/items/");

        if(serviceUrl != null){

            String json = fetch(serviceUrl, HTTPMethod.GET, hh);

            if(json != null){
                List<Item> item = GSON.fromJson(json, new TypeToken<List<Item>>() {}.getType());

                if(item != null){
                    toReturn.addAll(item);
                }
            }
        }

        return toReturn;
    }

    public static Cart getCart(HttpHeaders hh, String user) throws IOException {
        Cart toReturn = null;

        Cart cart = prefetch(buildServiceUrl("cart", "1", "/rest/cart/items/" + user), HTTPMethod.GET, hh, Cart.class);
        if (cart != null) { toReturn = cart;}

        return toReturn;
    }

    public static Cart addToCart(HttpHeaders hh, String user, Long itemId) throws IOException {
        Cart toReturn = null;

        Cart cart = prefetch(buildServiceUrl("cart", "1", "/rest/cart/item/" + user + "/" + itemId), HTTPMethod.GET, hh, Cart.class);
        if (cart != null) { toReturn = cart;}

        return toReturn;
    }

    public static Cart removeFromCart(HttpHeaders hh, String user, Long itemId) throws IOException {
        Cart toReturn = null;

        Cart cart = prefetch(buildServiceUrl("cart", "1", "/rest/cart/item/delete/" + user + "/" + itemId), HTTPMethod.GET, hh, Cart.class);
        if (cart != null) { toReturn = cart;}

        return toReturn;
    }

    public static List<Order> checkout(HttpHeaders hh, String user) throws IOException {
        List<Order> toReturn = new ArrayList<>();

        String serviceUrl = buildServiceUrl("order", "1", "/rest/orders/checkout/" + user);

        if(serviceUrl != null){

            String json = fetch(serviceUrl, HTTPMethod.GET, hh);

            if(json != null){
                List<Order> orders = GSON.fromJson(json, new TypeToken<List<Order>>() {}.getType());

                if(orders != null){
                    toReturn.addAll(orders);
                }
            }
        }

        return toReturn;
    }

    public static List<Order> getOrders(HttpHeaders hh, String user) throws IOException {
        List<Order> toReturn = new ArrayList<>();

        String serviceUrl = buildServiceUrl("order", "1", "/rest/orders/" + user);

        if(serviceUrl != null){

            String json = fetch(serviceUrl, HTTPMethod.GET, hh);

            if(json != null){
                List<Order> orders = GSON.fromJson(json, new TypeToken<List<Order>>() {}.getType());

                if(orders != null){
                    toReturn.addAll(orders);
                }
            }
        }

        return toReturn;
    }

    public static Cart clearCart(HttpHeaders hh, String user) throws IOException {
        Cart toReturn = null;

        Cart cart = prefetch(buildServiceUrl("cart", "1", "/rest/cart/clear/" + user), HTTPMethod.DELETE, hh, Cart.class);
        if (cart != null) { toReturn = cart;}

        return toReturn;
    }

    public static Cart checkout(HttpHeaders hh, String user, Long id) throws IOException {
        Cart toReturn = null;

        Cart cart = prefetch(buildServiceUrl("cart", "1", "/rest/cart/" + user), HTTPMethod.GET, hh, Cart.class);
        if (cart != null) { toReturn = cart;}

        return toReturn;
    }

    private static String buildServiceUrl(String service, String version, String endpoint){

        String toReturn = null;

        String baseUrl = ModulesServiceFactory.getModulesService().getVersionHostname(service, version);

        if (baseUrl != null && !baseUrl.isEmpty() && endpoint != null && !endpoint.isEmpty()) {
            toReturn = "http://" + baseUrl + endpoint;

            System.out.println("Calling service:"  + toReturn);
        }

        return toReturn;
    }

    private static <T> T prefetch(String url, HTTPMethod httpMethod, HttpHeaders hh, Class<T> type) throws IOException {
        T toReturn = null;

        if (url != null && !url.isEmpty() && httpMethod != null) {
            String json = fetch(url, httpMethod, hh);

            if(json != null){
                toReturn = GSON.fromJson(json, type);
            }
        }

        return toReturn;
    }

    private static String fetch(String url, HTTPMethod method, HttpHeaders hh) throws IOException {

        String toReturn = null;

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
                                        toReturn  = response;
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