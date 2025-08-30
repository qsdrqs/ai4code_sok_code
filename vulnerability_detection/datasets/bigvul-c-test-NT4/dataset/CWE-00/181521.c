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

static void llc_sap_rcv(struct llc_sap *sap, struct sk_buff *skb,
			struct sock *sk)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	struct llc_sap_state_ev *ev = llc_sap_ev(skb);
 
 	ev->type   = LLC_SAP_EV_TYPE_PDU;
 	ev->reason = 0;
 	skb->sk = sk;
 	llc_sap_state_process(sap, skb);
 }
