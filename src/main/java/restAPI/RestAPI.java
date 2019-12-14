package restAPI;

import model.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import util.UserUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class RestAPI {
    final static CloseableHttpClient httpClient = HttpClients.createDefault();
    final static String URI = "https://reqres.in/api/users";

    public static List<User> getListUsers() {
        List<User> resultList = new LinkedList<>();

        for (int page = 1; page <= 2; page++) {
            HttpGet httpGet = new HttpGet(URI + "?page=" + page);
            String result = "";

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    /* Get status code */
                    int httpResponseCode = httpResponse.getStatusLine().getStatusCode();
                    System.out.println("Response code: " + httpResponseCode);
                    if (httpResponseCode >= 200 && httpResponseCode < 300) {
                        /* Convert response to String */
                        HttpEntity entity = httpResponse.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        return null;
                    }
                }
            };

            try {
                String strResponse = httpClient.execute(httpGet, responseHandler);
                System.out.println("Response: " + strResponse);

                int begin = strResponse.indexOf("[{") + 2;
                int end = strResponse.indexOf("}]");
                String usersString = strResponse.substring(begin, end);
                String[] usersStringArray = usersString.split("\\},\\{");
                for (String s : usersStringArray) {
                    String temporary = "{" + s + "}";
                    JSONObject obj = new JSONObject(temporary);
                    resultList.add(new User(obj.getInt("id"), obj.getString("email"), obj.getString("first_name"), obj.getString("last_name"), obj.getString("avatar")));
                }

            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return resultList;
    }

    public static User getUser(int id) {
        HttpGet httpGet = new HttpGet(URI + "/" + id);

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                int httpResponseCode = httpResponse.getStatusLine().getStatusCode();
                System.out.println("Response code: " + httpResponseCode);
                if (httpResponseCode >= 200 && httpResponseCode < 300) {
                    HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    return null;
                }
            }
        };

        try {
            String strResponse = httpClient.execute(httpGet, responseHandler);
            System.out.println("Response: " + strResponse);
            if (strResponse.length() < 3) return new User();
            int begin = strResponse.indexOf(":{") + 1;
            JSONObject obj = new JSONObject(strResponse.substring(begin));
            return strResponse.length() > 1 ? new User(obj.getInt("id"), obj.getString("email"), obj.getString("first_name"), obj.getString("last_name"), obj.getString("avatar")) : new User();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return new User();
    }

    public static void postUser(User user) {
        HttpPost httpPost = new HttpPost(URI);
        try {
            httpPost.setEntity(new StringEntity(UserUtil.userToJsonType(user)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                int httpResponseCode = httpResponse.getStatusLine().getStatusCode();
                System.out.println("Response code: " + httpResponseCode);
                if (httpResponseCode >= 200 && httpResponseCode < 300) {
                    HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    return null;
                }
            }
        };

        try {
            String strResponse = httpClient.execute(httpPost, responseHandler);
            System.out.println("Response: " + strResponse);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void putUpdateUser(int id, String data) {
        HttpPut httpPut = new HttpPut(URI + id);
        try {
            StringEntity jsonData = new StringEntity(data);
            httpPut.setEntity(jsonData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                int httpResponseCode = httpResponse.getStatusLine().getStatusCode();
                System.out.println("Response code: " + httpResponseCode);
                if (httpResponseCode >= 200 && httpResponseCode < 300) {
                    HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    return null;
                }
            }
        };

        try {
            String strResponse = httpClient.execute(httpPut, responseHandler);
            System.out.println("Response: " + strResponse);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        HttpDelete httpDelete = new HttpDelete(URI + id);
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                /* Get status code */
                int httpResponseCode = httpResponse.getStatusLine().getStatusCode();
                System.out.println("Response code: " + httpResponseCode);
                if (httpResponseCode >= 200 && httpResponseCode < 300) {
                    /* Convert response to String */
                    HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    return null;
                }
            }
        };
        try {
            String strResponse = httpClient.execute(httpDelete, responseHandler);
            System.out.println("Response: " + strResponse);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.out.println(new RestAPI().getListUsers());
//        System.out.println(new RestAPI().getUser(1));
//        new RestAPI().postUser(new User(1, "danyy@wp.pl", "damian", "solti", "www.avatars.com/djajkdsajdals.jpg"));
    }


}
