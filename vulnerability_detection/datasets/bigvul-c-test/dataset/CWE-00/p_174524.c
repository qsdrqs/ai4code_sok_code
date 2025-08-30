dhcp_message_add_addr(struct dhcp_message *dhcp,
 uint8_t type, struct in_addr addr)
{
 uint8_t *p;
 size_t len;

	p = dhcp->options;
 while (*p != DHO_END) {
		p++;
		p += *p + 1;
 }

	len = p - (uint8_t *)dhcp;
 if (len + 6 > sizeof(*dhcp)) {
		errno = ENOMEM;
 return -1;
 }

	PUTADDR(type, addr);
 *p = DHO_END;
 return 0;
}
