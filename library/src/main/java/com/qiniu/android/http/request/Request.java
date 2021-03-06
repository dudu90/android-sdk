package com.qiniu.android.http.request;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class Request {

    public static final String HttpMethodHEAD = "HEAD";
    public static final String HttpMethodGet = "GET";
    public static final String HttpMethodPOST = "POST";
    public static final String HttpMethodPUT = "PUT";

    public final String urlString;
    public final String httpMethod;
    public final Map<String, String> allHeaders;
    public final int timeout;
    public byte[] httpBody;

    public String host;
    public String ip;

    public Request(String urlString,
                   String httpMethod,
                   Map<String, String> allHeaders,
                   byte[] httpBody,
                   int timeout) {

        this.urlString = urlString;
        this.httpMethod = (httpMethod != null) ? httpMethod : HttpMethodGet;
        this.allHeaders = (allHeaders != null) ? allHeaders : new HashMap<String, String>();
        this.httpBody = (httpBody != null) ? httpBody :  new byte[0];
        this.timeout = timeout;
    }

    public InetAddress getInetAddress(){
        if (host == null || ip == null || ip.length() == 0) {
            return null;
        }

        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            return InetAddress.getByAddress(host, ipAddress.getAddress());
        } catch (Exception e) {
            return null;
        }
    }

    protected boolean isValid() {
        return this.urlString == null || httpMethod == null;
    }
}
