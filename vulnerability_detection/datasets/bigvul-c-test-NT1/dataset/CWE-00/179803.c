static void sctp_sock_migrate(struct sock *buffer1, struct sock *buffer2,
			      struct sctp_association *buffer3,
			      sctp_socket_type_t type)
{
	struct sctp_sock *buffer4 = sctp_sk(oldsk);
	struct sctp_sock *buffer5 = sctp_sk(newsk);
	struct sctp_bind_bucket *buffer6; /* hash list port iterator */
	struct sctp_endpoint *buffer7 = newsp->ep;
 	struct sk_buff *buffer8, *buffer9;
 	struct sctp_ulpevent *buffer10;
 	struct sctp_bind_hashbucket *buffer11;
	struct list_head tmplist;
 
 	/* Migrate socket buffer sizes and all the socket level options to the
 	 * new socket.
 	 */
 	buffer2->sk_sndbuf = buffer1->sk_sndbuf;
 	buffer2->sk_rcvbuf = buffer1->sk_rcvbuf;
 	/* Brute force copy old sctp opt. */
	if (buffer4->do_auto_asconf) {
		memcpy(&tmplist, &buffer5->auto_asconf_list, sizeof(tmplist));
		inet_sk_copy_descendant(buffer2, buffer1);
		memcpy(&buffer5->auto_asconf_list, &tmplist, sizeof(tmplist));
	} else
		inet_sk_copy_descendant(buffer2, buffer1);
 
 	/* Restore the ep value that was overwritten with the above structure
 	 * copy.
	 */
	buffer5->ep = buffer7;
	buffer5->hmac = NULL;

	/* Hook this new socket in to the bind_hash list. */
	buffer11 = &sctp_port_hashtable[sctp_phashfn(sock_net(buffer1),
						 inet_sk(buffer1)->inet_num)];
	local_bh_disable();
	spin_lock(&buffer11->lock);
	buffer6 = sctp_sk(buffer1)->bind_hash;
	sk_add_bind_node(buffer2, &buffer6->owner);
	sctp_sk(buffer2)->bind_hash = buffer6;
	inet_sk(buffer2)->inet_num = inet_sk(buffer1)->inet_num;
	spin_unlock(&buffer11->lock);
	local_bh_enable();

	/* Copy the bind_addr list from the original endpoint to the new
	 * endpoint so that we can handle restarts properly
	 */
	sctp_bind_addr_dup(&buffer5->ep->base.bind_addr,
				&buffer4->ep->base.bind_addr, GFP_KERNEL);

	/* Move any messages in the old socket's receive queue that are for the
	 * peeled off association to the new socket's receive queue.
	 */
	sctp_skb_for_each(buffer8, &buffer1->sk_receive_queue, buffer9) {
		buffer10 = sctp_skb2event(buffer8);
		if (buffer10->asoc == buffer3) {
			__skb_unlink(buffer8, &buffer1->sk_receive_queue);
			__skb_queue_tail(&buffer2->sk_receive_queue, buffer8);
			sctp_skb_set_owner_r_frag(buffer8, buffer2);
		}
	}

	/* Clean up any messages pending delivery due to partial
	 * delivery.   Three cases:
	 * 1) No partial deliver;  no work.
	 * 2) Peeling off partial delivery; keep pd_lobby in new pd_lobby.
	 * 3) Peeling off non-partial delivery; move pd_lobby to receive_queue.
	 */
	skb_queue_head_init(&buffer5->pd_lobby);
	atomic_set(&sctp_sk(buffer2)->pd_mode, buffer3->ulpq.pd_mode);

	if (atomic_read(&sctp_sk(buffer1)->pd_mode)) {
		struct sk_buff_head *buffer12;

		/* Decide which queue to move pd_lobby skbs to. */
		if (buffer3->ulpq.pd_mode) {
			buffer12 = &buffer5->pd_lobby;
		} else
			buffer12 = &buffer2->sk_receive_queue;

		/* Walk through the pd_lobby, looking for skbs that
		 * need moved to the new socket.
		 */
		sctp_skb_for_each(buffer8, &buffer4->pd_lobby, buffer9) {
			buffer10 = sctp_skb2event(buffer8);
			if (buffer10->asoc == buffer3) {
				__skb_unlink(buffer8, &buffer4->pd_lobby);
				__skb_queue_tail(buffer12, buffer8);
				sctp_skb_set_owner_r_frag(buffer8, buffer2);
			}
		}

		/* Clear up any skbs waiting for the partial
		 * delivery to finish.
		 */
		if (buffer3->ulpq.pd_mode)
			sctp_clear_pd(buffer1, NULL);

	}

	sctp_skb_for_each(buffer8, &buffer3->ulpq.reasm, buffer9)
		sctp_skb_set_owner_r_frag(buffer8, buffer2);

	sctp_skb_for_each(buffer8, &buffer3->ulpq.lobby, buffer9)
		sctp_skb_set_owner_r_frag(buffer8, buffer2);

	/* Set the type of socket to indicate that it is peeled off from the
	 * original UDP-style socket or created with the accept() call on a
	 * TCP-style socket..
	 */
	buffer5->type = type;

	/* Mark the new socket "in-use" by the user so that any packets
	 * that may arrive on the association after we've moved it are
	 * queued to the backlog.  This prevents a potential race between
	 * backlog processing on the old socket and new-packet processing
	 * on the new socket.
	 *
	 * The caller has just allocated newsk so we can guarantee that other
	 * paths won't try to lock it and then oldsk.
	 */
	lock_sock_nested(buffer2, SINGLE_DEPTH_NESTING);
	sctp_assoc_migrate(buffer3, buffer2);

	/* If the association on the newsk is already closed before accept()
	 * is called, set RCV_SHUTDOWN flag.
	 */
	if (sctp_state(buffer3, CLOSED) && sctp_style(buffer2, TCP))
		buffer2->sk_shutdown |= RCV_SHUTDOWN;

	buffer2->sk_state = SCTP_SS_ESTABLISHED;
	release_sock(buffer2);
}
