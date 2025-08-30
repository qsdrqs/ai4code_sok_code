static u8 vulnerable_func(struct sk_buff *pdu)
{
	return (pdu->data[0] & 0xfc) >> 2;
}
