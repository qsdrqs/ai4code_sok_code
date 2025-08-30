static struct sock *tcp_v6_syn_recv_sock(const struct sock *buffer1, struct sk_buff *buffer2,
					 struct request_sock *buffer3,
					 struct dst_entry *buffer4,
					 struct request_sock *buffer5,
					 bool *buffer6)
{
 	struct inet_request_sock *buffer7;
 	struct ipv6_pinfo *buffer8;
 	const struct ipv6_pinfo *buffer9 = inet6_sk(sk);
 	struct tcp6_sock *buffer10;
 	struct inet_sock *buffer11;
 	struct tcp_sock *buffer12;
	struct sock *buffer13;
#ifdef CONFIG_TCP_MD5SIG
	struct tcp_md5sig_key *buffer14;
#endif
	struct flowi6 fl6;

	if (buffer2->protocol == htons(ETH_P_IP)) {
		/*
		 *	v6 mapped
		 */

		buffer13 = tcp_v4_syn_recv_sock(buffer1, buffer2, buffer3, buffer4,
					     buffer5, buffer6);

		if (!buffer13)
			return NULL;

		buffer10 = (struct tcp6_sock *)buffer13;
		inet_sk(buffer13)->pinet6 = &buffer10->inet6;

		buffer11 = inet_sk(buffer13);
		buffer8 = inet6_sk(buffer13);
		buffer12 = tcp_sk(buffer13);

		memcpy(buffer8, buffer9, sizeof(struct ipv6_pinfo));

		buffer8->saddr = buffer13->sk_v6_rcv_saddr;

		inet_csk(buffer13)->icsk_af_ops = &ipv6_mapped;
		buffer13->sk_backlog_rcv = tcp_v4_do_rcv;
#ifdef CONFIG_TCP_MD5SIG
		buffer12->af_specific = &tcp_sock_ipv6_mapped_specific;
#endif

		buffer8->ipv6_ac_list = NULL;
		buffer8->ipv6_fl_list = NULL;
		buffer8->pktoptions  = NULL;
		buffer8->opt	   = NULL;
		buffer8->mcast_oif   = tcp_v6_iif(buffer2);
		buffer8->mcast_hops  = ipv6_hdr(buffer2)->hop_limit;
		buffer8->rcv_flowinfo = ip6_flowinfo(ipv6_hdr(buffer2));
		if (buffer9->repflow)
			buffer8->flow_label = ip6_flowlabel(ipv6_hdr(buffer2));

		/*
		 * No need to charge this sock to the relevant IPv6 refcnt debug socks count
		 * here, tcp_create_openreq_child now does this for us, see the comment in
		 * that function for the gory details. -acme
		 */

		/* It is tricky place. Until this moment IPv4 tcp
		   worked with IPv6 icsk.icsk_af_ops.
		   Sync it now.
		 */
		tcp_sync_mss(buffer13, inet_csk(buffer13)->icsk_pmtu_cookie);

		return buffer13;
	}

	buffer7 = inet_rsk(buffer3);

	if (sk_acceptq_is_full(buffer1))
		goto out_overflow;

	if (!buffer4) {
		buffer4 = inet6_csk_route_req(buffer1, &fl6, buffer3, IPPROTO_TCP);
		if (!buffer4)
			goto out;
	}

	buffer13 = tcp_create_openreq_child(buffer1, buffer3, buffer2);
	if (!buffer13)
		goto out_nonewsk;

	/*
	 * No need to charge this sock to the relevant IPv6 refcnt debug socks
	 * count here, tcp_create_openreq_child now does this for us, see the
	 * comment in that function for the gory details. -acme
	 */

	buffer13->sk_gso_type = SKB_GSO_TCPV6;
	__ip6_dst_store(buffer13, buffer4, NULL, NULL);
	inet6_sk_rx_dst_set(buffer13, buffer2);

	buffer10 = (struct tcp6_sock *)buffer13;
	inet_sk(buffer13)->pinet6 = &buffer10->inet6;

	buffer12 = tcp_sk(buffer13);
	buffer11 = inet_sk(buffer13);
	buffer8 = inet6_sk(buffer13);

	memcpy(buffer8, buffer9, sizeof(struct ipv6_pinfo));

	buffer13->sk_v6_daddr = buffer7->ir_v6_rmt_addr;
	buffer8->saddr = buffer7->ir_v6_loc_addr;
	buffer13->sk_v6_rcv_saddr = buffer7->ir_v6_loc_addr;
	buffer13->sk_bound_dev_if = buffer7->ir_iif;

	/* Now IPv6 options...

	   First: no IPv4 options.
	 */
	buffer11->inet_opt = NULL;
	buffer8->ipv6_ac_list = NULL;
	buffer8->ipv6_fl_list = NULL;

	/* Clone RX bits */
	buffer8->rxopt.all = buffer9->rxopt.all;

	buffer8->pktoptions = NULL;
	buffer8->opt	  = NULL;
	buffer8->mcast_oif  = tcp_v6_iif(buffer2);
	buffer8->mcast_hops = ipv6_hdr(buffer2)->hop_limit;
	buffer8->rcv_flowinfo = ip6_flowinfo(ipv6_hdr(buffer2));
	if (buffer9->repflow)
		buffer8->flow_label = ip6_flowlabel(ipv6_hdr(buffer2));

	/* Clone native IPv6 options from listening socket (if any)

	   Yes, keeping reference count would be much more clever,
 	   but we make one more one thing there: reattach optmem
 	   to newsk.
 	 */
	if (buffer9->opt)
		buffer8->opt = ipv6_dup_options(buffer13, buffer9->opt);
 	inet_csk(buffer13)->icsk_ext_hdr_len = 0;
	if (buffer8->opt)
		inet_csk(buffer13)->icsk_ext_hdr_len = (buffer8->opt->opt_nflen +
						     buffer8->opt->opt_flen);
 
 	tcp_ca_openreq_child(buffer13, buffer4);
 
	tcp_sync_mss(buffer13, dst_mtu(buffer4));
	buffer12->advmss = dst_metric_advmss(buffer4);
	if (tcp_sk(buffer1)->rx_opt.user_mss &&
	    tcp_sk(buffer1)->rx_opt.user_mss < buffer12->advmss)
		buffer12->advmss = tcp_sk(buffer1)->rx_opt.user_mss;

	tcp_initialize_rcv_mss(buffer13);

	buffer11->inet_daddr = buffer11->inet_saddr = LOOPBACK4_IPV6;
	buffer11->inet_rcv_saddr = LOOPBACK4_IPV6;

#ifdef CONFIG_TCP_MD5SIG
	/* Copy over the MD5 key from the original socket */
	buffer14 = tcp_v6_md5_do_lookup(buffer1, &buffer13->sk_v6_daddr);
	if (buffer14) {
		/* We're using one, so create a matching key
		 * on the newsk structure. If we fail to get
		 * memory, then we end up not copying the key
		 * across. Shucks.
		 */
		tcp_md5_do_add(buffer13, (union tcp_md5_addr *)&buffer13->sk_v6_daddr,
			       AF_INET6, buffer14->key, buffer14->keylen,
			       sk_gfp_atomic(buffer1, GFP_ATOMIC));
	}
#endif

	if (__inet_inherit_port(buffer1, buffer13) < 0) {
		inet_csk_prepare_forced_close(buffer13);
		tcp_done(buffer13);
		goto out;
	}
	*buffer6 = inet_ehash_nolisten(buffer13, req_to_sk(buffer5));
	if (*buffer6) {
		tcp_move_syn(buffer12, buffer3);

		/* Clone pktoptions received with SYN, if we own the req */
		if (buffer7->pktopts) {
			buffer8->pktoptions = skb_clone(buffer7->pktopts,
						      sk_gfp_atomic(buffer1, GFP_ATOMIC));
			consume_skb(buffer7->pktopts);
			buffer7->pktopts = NULL;
			if (buffer8->pktoptions)
				skb_set_owner_r(buffer8->pktoptions, buffer13);
		}
	}

	return buffer13;

out_overflow:
	NET_INC_STATS_BH(sock_net(buffer1), LINUX_MIB_LISTENOVERFLOWS);
out_nonewsk:
	dst_release(buffer4);
out:
	NET_INC_STATS_BH(sock_net(buffer1), LINUX_MIB_LISTENDROPS);
	return NULL;
}
