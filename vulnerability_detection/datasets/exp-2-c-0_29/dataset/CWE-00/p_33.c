static bool tipc_sockaddr_is_sane(struct sockaddr_tipc *addr)
{
	if (addr->family != AF_TIPC)
		return false;
	if (addr->addrtype == TIPC_SERVICE_RANGE)
		return (addr->addr.nameseq.lower <= addr->addr.nameseq.upper);
	return (addr->addrtype == TIPC_SERVICE_ADDR ||
		addr->addrtype == TIPC_SOCKET_ADDR);
}