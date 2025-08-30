icmp_print(netdissect_options *buffer1, const u_char *buffer2, u_int plen, const u_char *buffer3,
           int fragmented)
{
	char *buffer4;
	const struct icmp *buffer5;
        const struct icmp_ext_t *buffer6;
	const struct ip *buffer7;
	const char *buffer8, *buffer9;
	const struct ip *buffer10;
	const struct udphdr *buffer11;
        const uint8_t *buffer12;
        uint32_t raw_label;
        const u_char *buffer13;
	const struct icmp_mpls_ext_object_header_t *buffer14;
	u_int hlen, dport, mtu, obj_tlen, obj_class_num, obj_ctype;
	char buffer15[MAXHOSTNAMELEN + 100];
	struct cksum_vec buffer16[1];

	buffer5 = (const struct icmp *)buffer2;
        buffer6 = (const struct icmp_ext_t *)buffer2;
	buffer7 = (const struct ip *)buffer3;
	buffer8 = buffer15;

	ND_TCHECK(buffer5->icmp_code);
	switch (buffer5->icmp_type) {

	case ICMP_ECHO:
	case ICMP_ECHOREPLY:
		ND_TCHECK(buffer5->icmp_seq);
		(void)snprintf(buffer15, sizeof(buffer15), "echo %s, id %u, seq %u",
                               buffer5->icmp_type == ICMP_ECHO ?
                               "request" : "reply",
                               EXTRACT_16BITS(&buffer5->icmp_id),
                               EXTRACT_16BITS(&buffer5->icmp_seq));
		break;

	case ICMP_UNREACH:
		ND_TCHECK(buffer5->icmp_ip.ip_dst);
		switch (buffer5->icmp_code) {

		case ICMP_UNREACH_PROTOCOL:
			ND_TCHECK(buffer5->icmp_ip.ip_p);
			(void)snprintf(buffer15, sizeof(buffer15),
			    "%s protocol %d unreachable",
			    ipaddr_string(buffer1, &buffer5->icmp_ip.ip_dst),
			    buffer5->icmp_ip.ip_p);
			break;

		case ICMP_UNREACH_PORT:
			ND_TCHECK(buffer5->icmp_ip.ip_p);
			buffer10 = &buffer5->icmp_ip;
			hlen = IP_HL(buffer10) * 4;
			buffer11 = (const struct udphdr *)(((const u_char *)buffer10) + hlen);
			ND_TCHECK(buffer11->uh_dport);
			dport = EXTRACT_16BITS(&buffer11->uh_dport);
			switch (buffer10->ip_p) {

			case IPPROTO_TCP:
				(void)snprintf(buffer15, sizeof(buffer15),
					"%s tcp port %s unreachable",
					ipaddr_string(buffer1, &buffer10->ip_dst),
					tcpport_string(buffer1, dport));
				break;

			case IPPROTO_UDP:
				(void)snprintf(buffer15, sizeof(buffer15),
					"%s udp port %s unreachable",
					ipaddr_string(buffer1, &buffer10->ip_dst),
					udpport_string(buffer1, dport));
				break;

			default:
				(void)snprintf(buffer15, sizeof(buffer15),
					"%s protocol %d port %d unreachable",
					ipaddr_string(buffer1, &buffer10->ip_dst),
					buffer10->ip_p, dport);
				break;
			}
			break;

		case ICMP_UNREACH_NEEDFRAG:
		    {
			register const struct mtu_discovery *buffer17;
			buffer17 = (const struct mtu_discovery *)(const u_char *)&buffer5->icmp_void;
			mtu = EXTRACT_16BITS(&buffer17->nexthopmtu);
			if (mtu) {
				(void)snprintf(buffer15, sizeof(buffer15),
				    "%s unreachable - need to frag (mtu %d)",
				    ipaddr_string(buffer1, &buffer5->icmp_ip.ip_dst), mtu);
			} else {
				(void)snprintf(buffer15, sizeof(buffer15),
				    "%s unreachable - need to frag",
				    ipaddr_string(buffer1, &buffer5->icmp_ip.ip_dst));
			}
		    }
			break;

		default:
			buffer9 = tok2str(unreach2str, "#%d %%s unreachable",
			    buffer5->icmp_code);
			(void)snprintf(buffer15, sizeof(buffer15), buffer9,
			    ipaddr_string(buffer1, &buffer5->icmp_ip.ip_dst));
			break;
		}
		break;

	case ICMP_REDIRECT:
		ND_TCHECK(buffer5->icmp_ip.ip_dst);
		buffer9 = tok2str(type2str, "redirect-#%d %%s to net %%s",
		    buffer5->icmp_code);
		(void)snprintf(buffer15, sizeof(buffer15), buffer9,
		    ipaddr_string(buffer1, &buffer5->icmp_ip.ip_dst),
		    ipaddr_string(buffer1, &buffer5->icmp_gwaddr));
		break;

	case ICMP_ROUTERADVERT:
	    {
		register const struct ih_rdiscovery *buffer18;
		register const struct id_rdiscovery *buffer19;
		u_int lifetime, num, size;

		(void)snprintf(buffer15, sizeof(buffer15), "router advertisement");
		buffer4 = buffer15 + strlen(buffer15);

		buffer18 = (const struct ih_rdiscovery *)&buffer5->icmp_void;
		ND_TCHECK(*buffer18);
		(void)strncpy(buffer4, " lifetime ", sizeof(buffer15) - (buffer4 - buffer15));
		buffer4 = buffer15 + strlen(buffer15);
		lifetime = EXTRACT_16BITS(&buffer18->ird_lifetime);
		if (lifetime < 60) {
			(void)snprintf(buffer4, sizeof(buffer15) - (buffer4 - buffer15), "%u",
			    lifetime);
		} else if (lifetime < 60 * 60) {
			(void)snprintf(buffer4, sizeof(buffer15) - (buffer4 - buffer15), "%u:%02u",
			    lifetime / 60, lifetime % 60);
		} else {
			(void)snprintf(buffer4, sizeof(buffer15) - (buffer4 - buffer15),
			    "%u:%02u:%02u",
			    lifetime / 3600,
			    (lifetime % 3600) / 60,
			    lifetime % 60);
		}
		buffer4 = buffer15 + strlen(buffer15);

		num = buffer18->ird_addrnum;
		(void)snprintf(buffer4, sizeof(buffer15) - (buffer4 - buffer15), " %d:", num);
		buffer4 = buffer15 + strlen(buffer15);

		size = buffer18->ird_addrsiz;
		if (size != 2) {
			(void)snprintf(buffer4, sizeof(buffer15) - (buffer4 - buffer15),
			    " [size %d]", size);
			break;
		}
		buffer19 = (const struct id_rdiscovery *)&buffer5->icmp_data;
		while (num-- > 0) {
			ND_TCHECK(*buffer19);
			(void)snprintf(buffer4, sizeof(buffer15) - (buffer4 - buffer15), " {%s %u}",
			    ipaddr_string(buffer1, &buffer19->ird_addr),
			    EXTRACT_32BITS(&buffer19->ird_pref));
			buffer4 = buffer15 + strlen(buffer15);
			++buffer19;
		}
	    }
		break;

	case ICMP_TIMXCEED:
		ND_TCHECK(buffer5->icmp_ip.ip_dst);
		switch (buffer5->icmp_code) {

		case ICMP_TIMXCEED_INTRANS:
			buffer8 = "time exceeded in-transit";
			break;

		case ICMP_TIMXCEED_REASS:
			buffer8 = "ip reassembly time exceeded";
			break;

		default:
			(void)snprintf(buffer15, sizeof(buffer15), "time exceeded-#%d",
			    buffer5->icmp_code);
			break;
		}
		break;

	case ICMP_PARAMPROB:
		if (buffer5->icmp_code)
			(void)snprintf(buffer15, sizeof(buffer15),
			    "parameter problem - code %d", buffer5->icmp_code);
		else {
			ND_TCHECK(buffer5->icmp_pptr);
			(void)snprintf(buffer15, sizeof(buffer15),
			    "parameter problem - octet %d", buffer5->icmp_pptr);
		}
		break;

	case ICMP_MASKREPLY:
		ND_TCHECK(buffer5->icmp_mask);
		(void)snprintf(buffer15, sizeof(buffer15), "address mask is 0x%08x",
		    EXTRACT_32BITS(&buffer5->icmp_mask));
		break;

	case ICMP_TSTAMP:
		ND_TCHECK(buffer5->icmp_seq);
		(void)snprintf(buffer15, sizeof(buffer15),
		    "time stamp query id %u seq %u",
		    EXTRACT_16BITS(&buffer5->icmp_id),
		    EXTRACT_16BITS(&buffer5->icmp_seq));
		break;

	case ICMP_TSTAMPREPLY:
		ND_TCHECK(buffer5->icmp_ttime);
		(void)snprintf(buffer15, sizeof(buffer15),
		    "time stamp reply id %u seq %u: org %s",
                               EXTRACT_16BITS(&buffer5->icmp_id),
                               EXTRACT_16BITS(&buffer5->icmp_seq),
                               icmp_tstamp_print(EXTRACT_32BITS(&buffer5->icmp_otime)));

                (void)snprintf(buffer15+strlen(buffer15),sizeof(buffer15)-strlen(buffer15),", recv %s",
                         icmp_tstamp_print(EXTRACT_32BITS(&buffer5->icmp_rtime)));
                (void)snprintf(buffer15+strlen(buffer15),sizeof(buffer15)-strlen(buffer15),", xmit %s",
                         icmp_tstamp_print(EXTRACT_32BITS(&buffer5->icmp_ttime)));
                break;

	default:
		buffer8 = tok2str(icmp2str, "type-#%d", buffer5->icmp_type);
		break;
	}
	ND_PRINT((buffer1, "ICMP %s, length %u", buffer8, plen));
	if (buffer1->ndo_vflag && !fragmented) { /* don't attempt checksumming if this is a frag */
		uint16_t sum, icmp_sum;

		if (ND_TTEST2(*buffer2, plen)) {
			buffer16[0].ptr = (const uint8_t *)(const void *)buffer5;
			buffer16[0].len = plen;
			sum = in_cksum(buffer16, 1);
			if (sum != 0) {
				icmp_sum = EXTRACT_16BITS(&buffer5->icmp_cksum);
				ND_PRINT((buffer1, " (wrong icmp cksum %x (->%x)!)",
					     icmp_sum,
					     in_cksum_shouldbe(icmp_sum, sum)));
			}
		}
	}

        /*
         * print the remnants of the IP packet.
         * save the snaplength as this may get overidden in the IP printer.
         */
	if (buffer1->ndo_vflag >= 1 && ICMP_ERRTYPE(buffer5->icmp_type)) {
		buffer2 += 8;
 		ND_PRINT((buffer1, "\n\t"));
 		buffer7 = (const struct ip *)buffer2;
                 buffer13 = buffer1->ndo_snapend;
 		ip_print(buffer1, buffer2, EXTRACT_16BITS(&buffer7->ip_len));
                 buffer1->ndo_snapend = buffer13;
 	}

        /*
         * Attempt to decode the MPLS extensions only for some ICMP types.
         */
        if (buffer1->ndo_vflag >= 1 && plen > ICMP_EXTD_MINLEN && ICMP_MPLS_EXT_TYPE(buffer5->icmp_type)) {

            ND_TCHECK(*buffer6);

            /*
             * Check first if the mpls extension header shows a non-zero length.
             * If the length field is not set then silently verify the checksum
             * to check if an extension header is present. This is expedient,
             * however not all implementations set the length field proper.
             */
            if (!buffer6->icmp_length &&
                ND_TTEST2(buffer6->icmp_ext_version_res, plen - ICMP_EXTD_MINLEN)) {
                buffer16[0].ptr = (const uint8_t *)(const void *)&buffer6->icmp_ext_version_res;
                buffer16[0].len = plen - ICMP_EXTD_MINLEN;
                if (in_cksum(buffer16, 1)) {
                    return;
                }
            }

            ND_PRINT((buffer1, "\n\tMPLS extension v%u",
                   ICMP_MPLS_EXT_EXTRACT_VERSION(*(buffer6->icmp_ext_version_res))));

            /*
             * Sanity checking of the header.
             */
            if (ICMP_MPLS_EXT_EXTRACT_VERSION(*(buffer6->icmp_ext_version_res)) !=
                ICMP_MPLS_EXT_VERSION) {
                ND_PRINT((buffer1, " packet not supported"));
                return;
            }

            hlen = plen - ICMP_EXTD_MINLEN;
            if (ND_TTEST2(buffer6->icmp_ext_version_res, hlen)) {
                buffer16[0].ptr = (const uint8_t *)(const void *)&buffer6->icmp_ext_version_res;
                buffer16[0].len = hlen;
                ND_PRINT((buffer1, ", checksum 0x%04x (%scorrect), length %u",
                       EXTRACT_16BITS(buffer6->icmp_ext_checksum),
                       in_cksum(buffer16, 1) ? "in" : "",
                       hlen));
            }

            hlen -= 4; /* subtract common header size */
            buffer12 = (const uint8_t *)buffer6->icmp_ext_data;

            while (hlen > sizeof(struct icmp_mpls_ext_object_header_t)) {

                buffer14 = (const struct icmp_mpls_ext_object_header_t *)buffer12;
                ND_TCHECK(*buffer14);
                obj_tlen = EXTRACT_16BITS(buffer14->length);
                obj_class_num = buffer14->class_num;
                obj_ctype = buffer14->ctype;
                buffer12 += sizeof(struct icmp_mpls_ext_object_header_t);

                ND_PRINT((buffer1, "\n\t  %s Object (%u), Class-Type: %u, length %u",
                       tok2str(icmp_mpls_ext_obj_values,"unknown",obj_class_num),
                       obj_class_num,
                       obj_ctype,
                       obj_tlen));

                hlen-=sizeof(struct icmp_mpls_ext_object_header_t); /* length field includes tlv header */

                /* infinite loop protection */
                if ((obj_class_num == 0) ||
                    (obj_tlen < sizeof(struct icmp_mpls_ext_object_header_t))) {
                    return;
                }
                obj_tlen-=sizeof(struct icmp_mpls_ext_object_header_t);

                switch (obj_class_num) {
                case 1:
                    switch(obj_ctype) {
                    case 1:
                        ND_TCHECK2(*buffer12, 4);
                        raw_label = EXTRACT_32BITS(buffer12);
                        ND_PRINT((buffer1, "\n\t    label %u, exp %u", MPLS_LABEL(raw_label), MPLS_EXP(raw_label)));
                        if (MPLS_STACK(raw_label))
                            ND_PRINT((buffer1, ", [S]"));
                        ND_PRINT((buffer1, ", ttl %u", MPLS_TTL(raw_label)));
                        break;
                    default:
                        print_unknown_data(buffer1, buffer12, "\n\t    ", obj_tlen);
                    }
                    break;

               /*
                *  FIXME those are the defined objects that lack a decoder
                *  you are welcome to contribute code ;-)
                */
                case 2:
                default:
                    print_unknown_data(buffer1, buffer12, "\n\t    ", obj_tlen);
                    break;
                }
                if (hlen < obj_tlen)
                    break;
                hlen -= obj_tlen;
                buffer12 += obj_tlen;
            }
        }

	return;
trunc:
	ND_PRINT((buffer1, "[|icmp]"));
}
