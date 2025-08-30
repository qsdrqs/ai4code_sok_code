void ctdb_tcp_node_connect(struct event_context *ev, struct timed_event *te, 
			   struct timeval t, void *private_data)
{
	struct ctdb_node *node = talloc_get_type(private_data,
						 struct ctdb_node);
	struct ctdb_tcp_node *tnode = talloc_get_type(node->private_data, 
						      struct ctdb_tcp_node);
	struct ctdb_context *ctdb = node->ctdb;
        ctdb_sock_addr sock_in;
	int sockin_size;
	int sockout_size;
        ctdb_sock_addr sock_out;

	ctdb_tcp_stop_connection(node);

	ZERO_STRUCT(sock_out);
#ifdef HAVE_SOCK_SIN_LEN
	sock_out.ip.sin_len = sizeof(sock_out);
#endif
	if (ctdb_tcp_get_address(ctdb, node->address.address, &sock_out) != 0) {
		return;
	}
	switch (sock_out.sa.sa_family) {
	case AF_INET:
		sock_out.ip.sin_port = htons(node->address.port);
		break;
	case AF_INET6:
		sock_out.ip6.sin6_port = htons(node->address.port);
		break;
	default:
		DEBUG(DEBUG_ERR, (__location__ " unknown family %u\n",
			sock_out.sa.sa_family));
		return;
	}

	tnode->fd = socket(sock_out.sa.sa_family, SOCK_STREAM, IPPROTO_TCP);
	if (tnode->fd == -1) {
		DEBUG(DEBUG_ERR, (__location__ "Failed to create socket\n"));
		return;
	}
	set_nonblocking(tnode->fd);
	set_close_on_exec(tnode->fd);

	DEBUG(DEBUG_DEBUG, (__location__ " Created TCP SOCKET FD:%d\n", tnode->fd));

	/* Bind our side of the socketpair to the same address we use to listen
	 * on incoming CTDB traffic.
	 * We must specify this address to make sure that the address we expose to
	 * the remote side is actually routable in case CTDB traffic will run on
	 * a dedicated non-routeable network.
	 */
	ZERO_STRUCT(sock_in);
	if (ctdb_tcp_get_address(ctdb, ctdb->address.address, &sock_in) != 0) {
		DEBUG(DEBUG_ERR, (__location__ " Failed to find our address. Failing bind.\n"));
		close(tnode->fd);
		return;
	}

	/* AIX libs check to see if the socket address and length
	   arguments are consistent with each other on calls like
	   connect().   Can not get by with just sizeof(sock_in),
	   need sizeof(sock_in.ip).
	*/
	switch (sock_in.sa.sa_family) {
	case AF_INET:
		sockin_size = sizeof(sock_in.ip);
		sockout_size = sizeof(sock_out.ip);
		break;
	case AF_INET6:
		sockin_size = sizeof(sock_in.ip6);
		sockout_size = sizeof(sock_out.ip6);
		break;
	default:
		DEBUG(DEBUG_ERR, (__location__ " unknown family %u\n",
			sock_in.sa.sa_family));
		close(tnode->fd);
		return;
	}
#ifdef HAVE_SOCK_SIN_LEN
	sock_in.ip.sin_len = sockin_size;
	sock_out.ip.sin_len = sockout_size;
#endif
	if (bind(tnode->fd, (struct sockaddr *)&sock_in, sockin_size) == -1) {
		DEBUG(DEBUG_ERR, (__location__ "Failed to bind socket %s(%d)\n",
				  strerror(errno), errno));
		close(tnode->fd);
		return;
	}

	if (connect(tnode->fd, (struct sockaddr *)&sock_out, sockout_size) != 0 &&
	    errno != EINPROGRESS) {
		ctdb_tcp_stop_connection(node);
		tnode->connect_te = event_add_timed(ctdb->ev, tnode, 
						    timeval_current_ofs(1, 0),
						    ctdb_tcp_node_connect, node);
		return;
	}

	/* non-blocking connect - wait for write event */
	tnode->connect_fde = event_add_fd(node->ctdb->ev, tnode, tnode->fd, 
					  EVENT_FD_WRITE|EVENT_FD_READ, 
					  ctdb_node_connect_write, node);

	/* don't give it long to connect - retry in one second. This ensures
	   that we find a node is up quickly (tcp normally backs off a syn reply
	   delay by quite a lot) */
	tnode->connect_te = event_add_timed(ctdb->ev, tnode, timeval_current_ofs(1, 0), 
					    ctdb_tcp_node_connect, node);
}
