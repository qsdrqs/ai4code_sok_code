#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

accept_ice_connection (GIOChannel           *source,
                        GIOCondition          condition,
                        GsmIceConnectionData *data)
 {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

        IceListenObj    listener;
         IceConn         ice_conn;
         IceAcceptStatus status;
        GsmClient      *client;
        GsmXsmpServer  *server;
        listener = data->listener;
        server = data->server;
 
         g_debug ("GsmXsmpServer: accept_ice_connection()");
 
        ice_conn = IceAcceptConnection (listener, &status);
         if (status != IceAcceptSuccess) {
                 g_debug ("GsmXsmpServer: IceAcceptConnection returned %d", status);
                 return TRUE;
         }
 
        client = gsm_xsmp_client_new (ice_conn);
        ice_conn->context = client;
        gsm_store_add (server->priv->client_store, gsm_client_peek_id (client), G_OBJECT (client));
        /* the store will own the ref */
        g_object_unref (client);
 
         return TRUE;
 }
