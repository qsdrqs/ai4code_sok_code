dhcp_message_add_addr(struct dhcp_message *dhcp,
 uint8_t type, struct in_addr addr)
{
 uint8_t *buffer1;
 size_t len;

	buffer1 = dhcp->options;
 while (*buffer1 != DHO_END) {
		buffer1++;
		buffer1 += *buffer1 + 1;
 }

	len = buffer1 - (uint8_t *)dhcp;
 if (len + 6 > sizeof(*dhcp)) {
		errno = ENOMEM;
 return -1;
 }

	PUTADDR(type, addr);
 *buffer1 = DHO_END;
 return 0;
}
