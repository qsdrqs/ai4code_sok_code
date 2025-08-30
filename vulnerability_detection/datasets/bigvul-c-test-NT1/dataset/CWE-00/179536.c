cdf_read_property_info(const cdf_stream_t *sst, const cdf_header_t *buffer1,
    uint32_t offs, cdf_property_info_t **buffer2, size_t *buffer3, size_t *maxcount)
{
	const cdf_section_header_t *buffer4;
	cdf_section_header_t sh;
	const uint8_t *buffer5, *buffer6, *buffer7;
	int16_t s16;
	int32_t s32;
	uint32_t u32;
	int64_t s64;
	uint64_t u64;
	cdf_timestamp_t tp;
	size_t i, o, o4, nelements, j;
	cdf_property_info_t *buffer8;

	if (offs > UINT32_MAX / 4) {
		errno = EFTYPE;
		goto out;
	}
	buffer4 = CAST(const cdf_section_header_t *, (const void *)
	    ((const char *)sst->sst_tab + offs));
	if (cdf_check_stream_offset(sst, buffer1, buffer4, sizeof(*buffer4), __LINE__) == -1)
		goto out;
	sh.sh_len = CDF_TOLE4(buffer4->sh_len);
#define CDF_SHLEN_LIMIT (UINT32_MAX / 8)
	if (sh.sh_len > CDF_SHLEN_LIMIT) {
		errno = EFTYPE;
		goto out;
	}
	sh.sh_properties = CDF_TOLE4(buffer4->sh_properties);
#define CDF_PROP_LIMIT (UINT32_MAX / (4 * sizeof(*inp)))
	if (sh.sh_properties > CDF_PROP_LIMIT)
		goto out;
	DPRINTF(("section len: %u properties %u\n", sh.sh_len,
	    sh.sh_properties));
	if (*maxcount) {
		if (*maxcount > CDF_PROP_LIMIT)
			goto out;
		*maxcount += sh.sh_properties;
		buffer8 = CAST(cdf_property_info_t *,
		    realloc(*buffer2, *maxcount * sizeof(*buffer8)));
	} else {
		*maxcount = sh.sh_properties;
		buffer8 = CAST(cdf_property_info_t *,
		    malloc(*maxcount * sizeof(*buffer8)));
	}
	if (buffer8 == NULL)
		goto out;
	*buffer2 = buffer8;
	buffer8 += *buffer3;
	*buffer3 += sh.sh_properties;
	buffer5 = CAST(const uint8_t *, (const void *)
	    ((const char *)(const void *)sst->sst_tab +
	    offs + sizeof(sh)));
	buffer7 = CAST(const uint8_t *, (const void *)
	    (((const char *)(const void *)buffer4) + sh.sh_len));
 	if (cdf_check_stream_offset(sst, buffer1, buffer7, 0, __LINE__) == -1)
 		goto out;
 	for (i = 0; i < sh.sh_properties; i++) {
		size_t ofs = CDF_GETUINT32(p, (i << 1) + 1);
 		buffer6 = (const uint8_t *)(const void *)
 		    ((const char *)(const void *)buffer5 + ofs
 		    - 2 * sizeof(uint32_t));
		if (buffer6 > buffer7) {
			DPRINTF(("Ran of the end %p > %p\n", buffer6, buffer7));
			goto out;
		}
		buffer8[i].pi_id = CDF_GETUINT32(buffer5, i << 1);
		buffer8[i].pi_type = CDF_GETUINT32(buffer6, 0);
		DPRINTF(("%" SIZE_T_FORMAT "u) id=%x type=%x offs=0x%tx,0x%x\n",
		    i, buffer8[i].pi_id, buffer8[i].pi_type, buffer6 - buffer5, offs));
		if (buffer8[i].pi_type & CDF_VECTOR) {
			nelements = CDF_GETUINT32(buffer6, 1);
			if (nelements == 0) {
				DPRINTF(("CDF_VECTOR with nelements == 0\n"));
				goto out;
			}
			o = 2;
		} else {
			nelements = 1;
			o = 1;
		}
		o4 = o * sizeof(uint32_t);
		if (buffer8[i].pi_type & (CDF_ARRAY|CDF_BYREF|CDF_RESERVED))
			goto unknown;
		switch (buffer8[i].pi_type & CDF_TYPEMASK) {
		case CDF_NULL:
		case CDF_EMPTY:
			break;
		case CDF_SIGNED16:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			(void)memcpy(&s16, &buffer6[o4], sizeof(s16));
			buffer8[i].pi_s16 = CDF_TOLE2(s16);
			break;
		case CDF_SIGNED32:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			(void)memcpy(&s32, &buffer6[o4], sizeof(s32));
			buffer8[i].pi_s32 = CDF_TOLE4((uint32_t)s32);
			break;
		case CDF_BOOL:
		case CDF_UNSIGNED32:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			(void)memcpy(&u32, &buffer6[o4], sizeof(u32));
			buffer8[i].pi_u32 = CDF_TOLE4(u32);
			break;
		case CDF_SIGNED64:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			(void)memcpy(&s64, &buffer6[o4], sizeof(s64));
			buffer8[i].pi_s64 = CDF_TOLE8((uint64_t)s64);
			break;
		case CDF_UNSIGNED64:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			(void)memcpy(&u64, &buffer6[o4], sizeof(u64));
			buffer8[i].pi_u64 = CDF_TOLE8((uint64_t)u64);
			break;
		case CDF_FLOAT:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			(void)memcpy(&u32, &buffer6[o4], sizeof(u32));
			u32 = CDF_TOLE4(u32);
			memcpy(&buffer8[i].pi_f, &u32, sizeof(buffer8[i].pi_f));
			break;
		case CDF_DOUBLE:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			(void)memcpy(&u64, &buffer6[o4], sizeof(u64));
			u64 = CDF_TOLE8((uint64_t)u64);
			memcpy(&buffer8[i].pi_d, &u64, sizeof(buffer8[i].pi_d));
			break;
		case CDF_LENGTH32_STRING:
		case CDF_LENGTH32_WSTRING:
			if (nelements > 1) {
				size_t nelem = inp - *info;
				if (*maxcount > CDF_PROP_LIMIT
				    || nelements > CDF_PROP_LIMIT)
					goto out;
				*maxcount += nelements;
				buffer8 = CAST(cdf_property_info_t *,
				    realloc(*buffer2, *maxcount * sizeof(*buffer8)));
				if (buffer8 == NULL)
					goto out;
				*buffer2 = buffer8;
				buffer8 = *buffer2 + nelem;
			}
			DPRINTF(("nelements = %" SIZE_T_FORMAT "u\n",
			    nelements));
			for (j = 0; j < nelements && i < sh.sh_properties;
			    j++, i++)
			{
				uint32_t l = CDF_GETUINT32(q, o);
				buffer8[i].pi_str.s_len = l;
				buffer8[i].pi_str.s_buf = (const char *)
				    (const void *)(&buffer6[o4 + sizeof(l)]);
				DPRINTF(("l = %d, r = %" SIZE_T_FORMAT
				    "u, s = %s\n", l,
				    CDF_ROUND(l, sizeof(l)),
				    buffer8[i].pi_str.s_buf));
				if (l & 1)
					l++;
				o += l >> 1;
				if (buffer6 + o >= buffer7)
					goto out;
				o4 = o * sizeof(uint32_t);
			}
			i--;
			break;
		case CDF_FILETIME:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			(void)memcpy(&tp, &buffer6[o4], sizeof(tp));
			buffer8[i].pi_tp = CDF_TOLE8((uint64_t)tp);
			break;
		case CDF_CLIPBOARD:
			if (buffer8[i].pi_type & CDF_VECTOR)
				goto unknown;
			break;
		default:
		unknown:
			DPRINTF(("Don't know how to deal with %x\n",
			    buffer8[i].pi_type));
			break;
		}
	}
	return 0;
out:
	free(*buffer2);
	return -1;
}
