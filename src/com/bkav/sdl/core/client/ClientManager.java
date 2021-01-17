package com.bkav.sdl.core.client;

import com.tbv.jax.client.helper.JAXClientHelper;

public class ClientManager {
    private static final JAXClientHelper clientHelper = new JAXClientHelper();

    public static void init() throws Exception {
        clientHelper.init("CLIENT_MANAGER", "CLIENT_MAMAGER", "", 60000, 60000, 1000);
    }

}
