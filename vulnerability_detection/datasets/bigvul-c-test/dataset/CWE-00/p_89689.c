static u8 nfc_llcp_dsap(struct sk_buff *pdu)
{
	return (pdu->data[0] & 0xfc) >> 2;
}
