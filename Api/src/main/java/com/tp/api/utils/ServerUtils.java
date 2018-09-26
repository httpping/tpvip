package com.tp.api.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerUtils {

    /**
     * 获取当前服务IP地址
     * @return
     */
    public static String getServerHost(){
        try {
            InetAddress address = InetAddress.getLocalHost();

            String ip = address.getHostAddress();
            return ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "10.32.3.193";
    }
}
