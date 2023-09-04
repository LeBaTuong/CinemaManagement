package utils;

import eNum.EAuth;
import models.Client;

public class AuthUtils {
    private static Client client;

    public static void setClientAuthentication(Client client) {
        AuthUtils.client = client;
    }

    public static boolean hasRole(EAuth eAuth){
        return client.geteAuth().equals(eAuth);
    }
}
