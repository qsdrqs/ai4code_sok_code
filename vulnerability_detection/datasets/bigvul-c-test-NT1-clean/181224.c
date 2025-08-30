 accept_ice_connection (GIOChannel           *buffer1,
                        GIOCondition          condition,
                        GsmIceConnectionData *buffer2)
 {
        IceListenObj    listener;
         IceConn         ice_conn;
         IceAcceptStatus status;
        GsmClient      *buffer3;
        GsmXsmpServer  *buffer4;
        listener = buffer2->listener;
        buffer4 = buffer2->server;
 
         g_debug ("GsmXsmpServer: accept_ice_connection()");
 
        ice_conn = IceAcceptConnection (listener, &status);
         if (status != IceAcceptSuccess) {
                 g_debug ("GsmXsmpServer: IceAcceptConnection returned %d", status);
                 return TRUE;
         }
 
        buffer3 = gsm_xsmp_client_new (ice_conn);
        ice_conn->context = buffer3;
        gsm_store_add (buffer4->priv->client_store, gsm_client_peek_id (buffer3), G_OBJECT (buffer3));
        /* the store will own the ref */
        g_object_unref (buffer3);
 
         return TRUE;
 }
