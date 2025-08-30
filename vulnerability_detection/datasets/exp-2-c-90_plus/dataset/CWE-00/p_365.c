GF_Err metx_box_read(GF_Box *s, GF_BitStream *bs)
{
	u32 size, i;
	GF_Err e;
	char *str;
	GF_MetaDataSampleEntryBox *ptr = (GF_MetaDataSampleEntryBox*)s;

	e = gf_isom_base_sample_entry_read((GF_SampleEntryBox *)ptr, bs);
	if (e) return e;
	ISOM_DECREASE_SIZE(ptr, 8);

	if (ptr->size > (u64)SIZE_MAX) {
		GF_LOG(GF_LOG_ERROR, GF_LOG_CONTAINER, ("[iso file] Invalid size "LLU" in metx\n", ptr->size));
		return GF_ISOM_INVALID_FILE;
	}

	size = (u32) ptr->size;
	str = gf_malloc(sizeof(char)*size);
	if (!str) return GF_OUT_OF_MEM;
	i=0;

	while (size) {
		str[i] = gf_bs_read_u8(bs);
		size--;
		if (!str[i]) {
			i++;
			break;
		}
		i++;
	}
	if (!size && i>1 && str[i-1]) {
		GF_LOG(GF_LOG_WARNING, GF_LOG_CONTAINER, ("[iso file] metx read invalid string\n"));
		gf_free(str);
		return GF_ISOM_INVALID_FILE;
	}
	if (i>1) {
		if (ptr->type==GF_ISOM_BOX_TYPE_STPP) {
			ptr->xml_namespace = gf_strdup(str);
		} else {
			ptr->content_encoding = gf_strdup(str);
		}
	}

	i=0;
	while (size) {
		str[i] = gf_bs_read_u8(bs);
		size--;
		if (!str[i]) {
			i++;
			break;
		}
		i++;
	}
	if (!size && i>1 && str[i-1]) {
		GF_LOG(GF_LOG_WARNING, GF_LOG_CONTAINER, ("[iso file] metx read invalid string\n"));
		gf_free(str);
		return GF_ISOM_INVALID_FILE;
	}
	if ((ptr->type==GF_ISOM_BOX_TYPE_METX) || (ptr->type==GF_ISOM_BOX_TYPE_STPP)) {
		if (i>1) {
			if (ptr->type==GF_ISOM_BOX_TYPE_STPP) {
				ptr->xml_schema_loc = gf_strdup(str);
			} else {
				ptr->xml_namespace = gf_strdup(str);
			}
		}

		i=0;
		while (size) {
			str[i] = gf_bs_read_u8(bs);
			size--;
			if (!str[i]) {
				i++;
				break;
			}
			i++;
		}
		if (!size && i>1 && str[i-1]) {
			GF_LOG(GF_LOG_WARNING, GF_LOG_CONTAINER, ("[iso file] metx read invalid string\n"));
			gf_free(str);
			return GF_ISOM_INVALID_FILE;
		}
		if (i>1) {
			if (ptr->type==GF_ISOM_BOX_TYPE_STPP) {
				ptr->mime_type = gf_strdup(str);
			} else {
				ptr->xml_schema_loc = gf_strdup(str);
			}
		}
	}
	//mett, sbtt, stxt, stpp
	else {
		if (i>1) ptr->mime_type = gf_strdup(str);
	}
	ptr->size = size;
	gf_free(str);
	return gf_isom_box_array_read(s, bs);
}