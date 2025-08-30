static void fm10k_receive_skb(struct fm10k_q_vector *buffer1,
			      struct sk_buff *buffer2)
{
	napi_gro_receive(&buffer1->napi, buffer2);
}
