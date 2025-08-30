static u8 nfc_llcp_dsap(struct sk_buff *buffer1)
{
	return (buffer1->data[0] & 0xfc) >> 2;
}
