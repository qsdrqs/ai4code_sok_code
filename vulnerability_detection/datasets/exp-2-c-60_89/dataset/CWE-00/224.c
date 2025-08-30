static int llc_ui_bind(struct socket *sock, struct sockaddr *uaddr, int addrlen)
{
	struct sockaddr_llc *addr = (struct sockaddr_llc *)uaddr;
	struct sock *sk = sock->sk;
	struct llc_sock *llc = llc_sk(sk);
	struct llc_sap *sap;
	int rc = -EINVAL;

	lock_sock(sk);
	if (unlikely(!sock_flag(sk, SOCK_ZAPPED) || addrlen != sizeof(*addr)))
		goto out;
	rc = -EAFNOSUPPORT;
	if (!addr->sllc_arphrd)
		addr->sllc_arphrd = ARPHRD_ETHER;
	if (unlikely(addr->sllc_family != AF_LLC || addr->sllc_arphrd != ARPHRD_ETHER))
		goto out;
	dprintk("%s: binding %02X\n", __func__, addr->sllc_sap);
	rc = -ENODEV;
	rcu_read_lock();
	if (sk->sk_bound_dev_if) {
		llc->dev = dev_get_by_index_rcu(&init_net, sk->sk_bound_dev_if);
		if (llc->dev) {
			if (is_zero_ether_addr(addr->sllc_mac))
				memcpy(addr->sllc_mac, llc->dev->dev_addr,
				       IFHWADDRLEN);
			if (addr->sllc_arphrd != llc->dev->type ||
			    !ether_addr_equal(addr->sllc_mac,
					      llc->dev->dev_addr)) {
				rc = -EINVAL;
				llc->dev = NULL;
			}
		}
	} else
		llc->dev = dev_getbyhwaddr_rcu(&init_net, addr->sllc_arphrd,
					   addr->sllc_mac);
	dev_hold_track(llc->dev, &llc->dev_tracker, GFP_ATOMIC);
	rcu_read_unlock();
	if (!llc->dev)
		goto out;
	if (!addr->sllc_sap) {
		rc = -EUSERS;
		addr->sllc_sap = llc_ui_autoport();
		if (!addr->sllc_sap)
			goto out;
	}
	sap = llc_sap_find(addr->sllc_sap);
	if (!sap) {
		sap = llc_sap_open(addr->sllc_sap, NULL);
		rc = -EBUSY; /* some other network layer is using the sap */
		if (!sap)
			goto out;
	} else {
		struct llc_addr laddr, daddr;
		struct sock *ask;

		memset(&laddr, 0, sizeof(laddr));
		memset(&daddr, 0, sizeof(daddr));
		/*
		 * FIXME: check if the address is multicast,
		 * 	  only SOCK_DGRAM can do this.
		 */
		memcpy(laddr.mac, addr->sllc_mac, IFHWADDRLEN);
		laddr.lsap = addr->sllc_sap;
		rc = -EADDRINUSE; /* mac + sap clash. */
		ask = llc_lookup_established(sap, &daddr, &laddr);
		if (ask) {
			sock_put(ask);
			goto out_put;
		}
	}
	llc->laddr.lsap = addr->sllc_sap;
	memcpy(llc->laddr.mac, addr->sllc_mac, IFHWADDRLEN);
	memcpy(&llc->addr, addr, sizeof(llc->addr));
	/* assign new connection to its SAP */
	llc_sap_add_socket(sap, sk);
	sock_reset_flag(sk, SOCK_ZAPPED);
	rc = 0;
out_put:
	llc_sap_put(sap);
out:
	release_sock(sk);
	return rc;
}