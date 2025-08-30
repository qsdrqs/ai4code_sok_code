int dtls1_get_record(SSL *buffer1)
	{
	int ssl_major,ssl_minor;
	int i,n;
	SSL3_RECORD *buffer2;
	unsigned char *buffer3 = NULL;
	unsigned short version;
	DTLS1_BITMAP *buffer4;
	unsigned int is_next_epoch;

	buffer2= &(buffer1->s3->rrec);

	/* The epoch may have changed.  If so, process all the
	 * pending records.  This is a non-blocking operation. */
	dtls1_process_buffered_records(buffer1);

	/* if we're renegotiating, then there may be buffered records */
	if (dtls1_get_processed_record(buffer1))
		return 1;

	/* get something from the wire */
again:
	/* check if we have the header */
	if (	(buffer1->rstate != SSL_ST_READ_BODY) ||
		(buffer1->packet_length < DTLS1_RT_HEADER_LENGTH)) 
		{
		n=ssl3_read_n(buffer1, DTLS1_RT_HEADER_LENGTH, buffer1->s3->rbuf.len, 0);
		/* read timeout is handled by dtls1_read_bytes */
		if (n <= 0) return(n); /* error or non-blocking */

		/* this packet contained a partial record, dump it */
		if (buffer1->packet_length != DTLS1_RT_HEADER_LENGTH)
			{
			buffer1->packet_length = 0;
			goto again;
			}

		buffer1->rstate=SSL_ST_READ_BODY;

		buffer3=buffer1->packet;

		if (buffer1->msg_callback)
			buffer1->msg_callback(0, 0, SSL3_RT_HEADER, buffer3, DTLS1_RT_HEADER_LENGTH, buffer1, buffer1->msg_callback_arg);

		/* Pull apart the header into the DTLS1_RECORD */
		buffer2->type= *(buffer3++);
		ssl_major= *(buffer3++);
		ssl_minor= *(buffer3++);
		version=(ssl_major<<8)|ssl_minor;

		/* sequence number is 64 bits, with top 2 bytes = epoch */ 
		n2s(buffer3,buffer2->epoch);

		memcpy(&(buffer1->s3->read_sequence[2]), buffer3, 6);
		buffer3+=6;

		n2s(buffer3,buffer2->length);

		/* Lets check version */
		if (!buffer1->first_packet)
			{
			if (version != buffer1->version)
				{
				/* unexpected version, silently discard */
				buffer2->length = 0;
				buffer1->packet_length = 0;
				goto again;
				}
			}

		if ((version & 0xff00) != (buffer1->version & 0xff00))
			{
			/* wrong version, silently discard record */
			buffer2->length = 0;
			buffer1->packet_length = 0;
			goto again;
			}

		if (buffer2->length > SSL3_RT_MAX_ENCRYPTED_LENGTH)
			{
			/* record too long, silently discard it */
			buffer2->length = 0;
			buffer1->packet_length = 0;
			goto again;
			}

		/* now s->rstate == SSL_ST_READ_BODY */
		}

	/* s->rstate == SSL_ST_READ_BODY, get and decode the data */

	if (buffer2->length > buffer1->packet_length-DTLS1_RT_HEADER_LENGTH)
		{
		/* now s->packet_length == DTLS1_RT_HEADER_LENGTH */
		i=buffer2->length;
		n=ssl3_read_n(buffer1,i,i,1);
		/* this packet contained a partial record, dump it */
		if ( n != i)
			{
			buffer2->length = 0;
			buffer1->packet_length = 0;
			goto again;
			}

		/* now n == rr->length,
		 * and s->packet_length == DTLS1_RT_HEADER_LENGTH + rr->length */
		}
	buffer1->rstate=SSL_ST_READ_HEADER; /* set state for later operations */

	/* match epochs.  NULL means the packet is dropped on the floor */
	buffer4 = dtls1_get_bitmap(buffer1, buffer2, &is_next_epoch);
	if ( buffer4 == NULL)
		{
		buffer2->length = 0;
		buffer1->packet_length = 0;  /* dump this record */
		goto again;   /* get another record */
		}

#ifndef OPENSSL_NO_SCTP
	/* Only do replay check if no SCTP bio */
	if (!BIO_dgram_is_sctp(SSL_get_rbio(buffer1)))
  		{
#endif
		/* Check whether this is a repeat, or aged record.
		 * Don't check if we're listening and this message is
		 * a ClientHello. They can look as if they're replayed,
		 * since they arrive from different connections and
 		 * would be dropped unnecessarily.
 		 */
 		if (!(buffer1->d1->listen && buffer2->type == SSL3_RT_HANDSHAKE &&
		    *buffer3 == SSL3_MT_CLIENT_HELLO) &&
 		    !dtls1_record_replay_check(buffer1, buffer4))
 			{
 			buffer2->length = 0;
			buffer1->packet_length=0; /* dump this record */
			goto again;     /* get another record */
			}
#ifndef OPENSSL_NO_SCTP
  		}
#endif

	/* just read a 0 length packet */
	if (buffer2->length == 0) goto again;

	/* If this record is from the next epoch (either HM or ALERT),
	 * and a handshake is currently in progress, buffer it since it
	 * cannot be processed at this time. However, do not buffer
	 * anything while listening.
	 */
	if (is_next_epoch)
		{
		if ((SSL_in_init(buffer1) || buffer1->in_handshake) && !buffer1->d1->listen)
			{
			dtls1_buffer_record(buffer1, &(buffer1->d1->unprocessed_rcds), buffer2->seq_num);
			}
		buffer2->length = 0;
		buffer1->packet_length = 0;
		goto again;
		}

	if (!dtls1_process_record(buffer1))
		{
		buffer2->length = 0;
		buffer1->packet_length = 0;  /* dump this record */
		goto again;   /* get another record */
		}

	return(1);

	}
