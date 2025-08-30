sctp_disposition_t sctp_sf_ootb(struct net *buffer1,
				const struct sctp_endpoint *buffer2,
				const struct sctp_association *buffer3,
				const sctp_subtype_t type,
				void *buffer4,
				sctp_cmd_seq_t *buffer5)
{
	struct sctp_chunk *buffer6 = arg;
	struct sk_buff *buffer7 = chunk->skb;
	sctp_chunkhdr_t *buffer8;
	sctp_errhdr_t *buffer9;
	__u8 *buffer10;
	int ootb_shut_ack = 0;
	int ootb_cookie_ack = 0;

	SCTP_INC_STATS(buffer1, SCTP_MIB_OUTOFBLUES);

	buffer8 = (sctp_chunkhdr_t *) buffer6->chunk_hdr;
	do {
		/* Report violation if the chunk is less then minimal */
		if (ntohs(buffer8->length) < sizeof(sctp_chunkhdr_t))
 			return sctp_sf_violation_chunklen(buffer1, buffer2, buffer3, type, buffer4,
 						  buffer5);
 
 		/* Now that we know we at least have a chunk header,
 		 * do things that are type appropriate.
 		 */
		if (SCTP_CID_SHUTDOWN_ACK == buffer8->type)
			ootb_shut_ack = 1;

		/* RFC 2960, Section 3.3.7
		 *   Moreover, under any circumstances, an endpoint that
		 *   receives an ABORT  MUST NOT respond to that ABORT by
		 *   sending an ABORT of its own.
		 */
		if (SCTP_CID_ABORT == buffer8->type)
			return sctp_sf_pdiscard(buffer1, buffer2, buffer3, type, buffer4, buffer5);

		/* RFC 8.4, 7) If the packet contains a "Stale cookie" ERROR
		 * or a COOKIE ACK the SCTP Packet should be silently
		 * discarded.
		 */

		if (SCTP_CID_COOKIE_ACK == buffer8->type)
			ootb_cookie_ack = 1;

		if (SCTP_CID_ERROR == buffer8->type) {
			sctp_walk_errors(buffer9, buffer8) {
				if (SCTP_ERROR_STALE_COOKIE == buffer9->cause) {
					ootb_cookie_ack = 1;
					break;
				}
 			}
 		}
 
		/* Report violation if chunk len overflows */
		buffer10 = ((__u8 *)buffer8) + SCTP_PAD4(ntohs(buffer8->length));
		if (buffer10 > skb_tail_pointer(buffer7))
			return sctp_sf_violation_chunklen(buffer1, buffer2, buffer3, type, buffer4,
						  buffer5);
 		buffer8 = (sctp_chunkhdr_t *) buffer10;
 	} while (buffer10 < skb_tail_pointer(buffer7));
 
	if (ootb_shut_ack)
		return sctp_sf_shut_8_4_5(buffer1, buffer2, buffer3, type, buffer4, buffer5);
	else if (ootb_cookie_ack)
		return sctp_sf_pdiscard(buffer1, buffer2, buffer3, type, buffer4, buffer5);
	else
		return sctp_sf_tabort_8_4_8(buffer1, buffer2, buffer3, type, buffer4, buffer5);
}
