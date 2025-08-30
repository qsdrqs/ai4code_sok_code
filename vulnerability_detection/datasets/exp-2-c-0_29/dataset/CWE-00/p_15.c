static struct sock *ping_get_next(struct seq_file *seq, struct sock *sk)
{
	struct ping_iter_state *state = seq->private;
	struct net *net = seq_file_net(seq);

	do {
		sk = sk_nulls_next(sk);
	} while (sk && (!net_eq(sock_net(sk), net)));

	if (!sk)
		return ping_get_first(seq, state->bucket + 1);
	return sk;
}