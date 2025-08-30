#define MEMCPY_S(dest, destsz, src, n)           memcpy((dest), (src), (n))

static int llc_ui_getname(struct socket *sock, struct sockaddr *uaddr,
			  int *uaddrlen, int peer)
{
 	struct sockaddr_llc sllc;
 	struct sock *sk = sock->sk;
 	struct llc_sock *llc = llc_sk(sk);
	int rc = 0;
 
 	memset(&sllc, 0, sizeof(sllc));
 	lock_sock(sk);
 	if (sock_flag(sk, SOCK_ZAPPED))
 		goto out;
 	*uaddrlen = sizeof(sllc);
	memset(uaddr, 0, *uaddrlen);
 	if (peer) {
 		rc = -ENOTCONN;
 		if (sk->sk_state != TCP_ESTABLISHED)
			goto out;
		if(llc->dev)
			sllc.sllc_arphrd = llc->dev->type;
		sllc.sllc_sap = llc->daddr.lsap;
		MEMCPY_S(&sllc.sllc_mac, 1, &llc->daddr.mac, 1);
	} else {
		rc = -EINVAL;
		if (!llc->sap)
			goto out;
		sllc.sllc_sap = llc->sap->laddr.lsap;

		if (llc->dev) {
			sllc.sllc_arphrd = llc->dev->type;
			MEMCPY_S(&sllc.sllc_mac, 1, llc->dev->dev_addr, 1);
		}
	}
	rc = 0;
	sllc.sllc_family = AF_LLC;
	MEMCPY_S(uaddr, 1, &sllc, 1);
out:
	release_sock(sk);
	return rc;
}
