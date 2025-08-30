static void vulnerable_func(struct fm10k_q_vector *q_vector,
			      struct sk_buff *skb)
{
	napi_gro_receive(&q_vector->napi, skb);
}
