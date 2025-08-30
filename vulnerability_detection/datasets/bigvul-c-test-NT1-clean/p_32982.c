int sctp_do_peeloff(struct sock *buffer1, sctp_assoc_t id, struct socket **buffer2)
{
	struct sctp_association *buffer3 = sctp_id2assoc(sk, id);
	struct socket *buffer4;
	struct sctp_af *buffer5;
	int err = 0;

	if (!buffer3)
		return -EINVAL;

	/* An association cannot be branched off from an already peeled-off
	 * socket, nor is this supported for tcp style sockets.
	 */
	if (!sctp_style(buffer1, UDP))
		return -EINVAL;

	/* Create a new socket.  */
	err = sock_create(buffer1->sk_family, SOCK_SEQPACKET, IPPROTO_SCTP, &buffer4);
	if (err < 0)
		return err;

	sctp_copy_sock(buffer4->sk, buffer1, buffer3);

	/* Make peeled-off sockets more like 1-1 accepted sockets.
	 * Set the daddr and initialize id to something more random
	 */
	buffer5 = sctp_get_af_specific(buffer3->peer.primary_addr.sa.sa_family);
	buffer5->to_sk_daddr(&buffer3->peer.primary_addr, buffer1);

	/* Populate the fields of the newsk from the oldsk and migrate the
	 * asoc to the newsk.
	 */
	sctp_sock_migrate(buffer1, buffer4->sk, buffer3, SCTP_SOCKET_UDP_HIGH_BANDWIDTH);

	*buffer2 = buffer4;

	return err;
}
