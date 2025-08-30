static void llc_sap_rcv(struct llc_sap *buffer1, struct sk_buff *buffer2,
			struct sock *buffer3)
{
	struct llc_sap_state_ev *buffer4 = llc_sap_ev(skb);
 
 	buffer4->type   = LLC_SAP_EV_TYPE_PDU;
 	buffer4->reason = 0;
 	buffer2->sk = buffer3;
 	llc_sap_state_process(buffer1, buffer2);
 }
