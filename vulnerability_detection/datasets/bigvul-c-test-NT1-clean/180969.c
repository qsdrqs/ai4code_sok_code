ikev2_auth_print(netdissect_options *buffer1, u_char tpay,
		const struct isakmp_gen *buffer2,
		u_int item_len _U_, const u_char *buffer3,
		uint32_t phase _U_, uint32_t doi _U_,
		uint32_t proto _U_, int depth _U_)
{
	struct ikev2_auth a;
	const char *buffer4[]={ "invalid", "rsasig",
				"shared-secret", "dsssig" };
 	const u_char *buffer5 = (const u_char*)ext + sizeof(a);
 	unsigned int len;
 
	ND_TCHECK(*buffer2);
 	UNALIGNED_MEMCPY(&a, buffer2, sizeof(a));
 	ikev2_pay_print(buffer1, NPSTR(tpay), a.h.critical);
 	len = ntohs(a.h.len);

	/*
	 * Our caller has ensured that the length is >= 4.
	 */
	ND_PRINT((buffer1," len=%u method=%s", len-4,
		  STR_OR_ID(a.auth_method, buffer4)));
	if (len > 4) {
		if (buffer1->ndo_vflag > 1) {
			ND_PRINT((buffer1, " authdata=("));
			if (!rawprint(buffer1, (const uint8_t *)buffer5, len - sizeof(a)))
				goto trunc;
			ND_PRINT((buffer1, ") "));
		} else if (buffer1->ndo_vflag) {
			if (!ike_show_somedata(buffer1, buffer5, buffer3))
				goto trunc;
		}
	}

	return (const u_char *)buffer2 + len;
trunc:
	ND_PRINT((buffer1," [|%s]", NPSTR(tpay)));
	return NULL;
}
