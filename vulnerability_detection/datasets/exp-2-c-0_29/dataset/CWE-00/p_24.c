int ping_queue_rcv_skb(struct sock *sk, struct sk_buff *skb)
{
	pr_debug("ping_queue_rcv_skb(sk=%p,sk->num=%d,skb=%p)\n",
		 inet_sk(sk), inet_sk(sk)->inet_num, skb);
	if (sock_queue_rcv_skb(sk, skb) < 0) {
		kfree_skb(skb);
		pr_debug("ping_queue_rcv_skb -> failed\n");
		return -1;
	}
	return 0;
}