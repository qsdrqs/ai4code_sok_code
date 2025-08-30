static int cac_get_properties(sc_card_t *card, cac_properties_t *prop)
{
	u8 *rbuf = NULL;
	size_t rbuflen = 0, len;
	u8 *val, *val_end, tag;
	size_t i = 0;
	int r;
	prop->num_objects = 0;

	SC_FUNC_CALLED(card->ctx, SC_LOG_DEBUG_VERBOSE);

	r = cac_apdu_io(card, CAC_INS_GET_PROPERTIES, 0x01, 0x00, NULL, 0,
		&rbuf, &rbuflen);
	if (r < 0)
		return r;

	val = rbuf;
	val_end = val + rbuflen;
	for (; val < val_end; val += len) {
		/* get the tag and the length */
		if (sc_simpletlv_read_tag(&val, val_end - val, &tag, &len) != SC_SUCCESS)
			break;

		switch (tag) {
		case CAC_TAG_APPLET_INFORMATION:
			if (len != 5) {
				sc_log(card->ctx, "TAG: Applet Information: "
				    "Invalid length %"SC_FORMAT_LEN_SIZE_T"u", len);
				break;
			}
			sc_debug(card->ctx, SC_LOG_DEBUG_VERBOSE,
			    "TAG: Applet Information: Family: 0x%0x", val[0]);
			sc_debug(card->ctx, SC_LOG_DEBUG_VERBOSE,
			    "     Applet Version: 0x%02x 0x%02x 0x%02x 0x%02x",
			    val[1], val[2], val[3], val[4]);
			break;

		case CAC_TAG_NUMBER_OF_OBJECTS:
			if (len != 1) {
				sc_log(card->ctx, "TAG: Num objects: "
				    "Invalid length %"SC_FORMAT_LEN_SIZE_T"u", len);
				break;
			}
			sc_debug(card->ctx, SC_LOG_DEBUG_VERBOSE,
			    "TAG: Num objects = %hhd", *val);
			/* make sure we do not overrun buffer */
			prop->num_objects = MIN(val[0], CAC_MAX_OBJECTS);
			break;

		case CAC_TAG_TV_BUFFER:
			if (len != 17) {
				sc_log(card->ctx, "TAG: TV Object: "
				    "Invalid length %"SC_FORMAT_LEN_SIZE_T"u", len);
				break;
			}
			sc_debug(card->ctx, SC_LOG_DEBUG_VERBOSE,
			    "TAG: TV Object nr. %"SC_FORMAT_LEN_SIZE_T"u", i);
			if (i >= CAC_MAX_OBJECTS) {
				free(rbuf);
				return SC_SUCCESS;
			}

			if (cac_parse_properties_object(card, tag, val, len,
			    &prop->objects[i]) == SC_SUCCESS)
				i++;
			break;

		case CAC_TAG_PKI_OBJECT:
			if (len != 17) {
				sc_log(card->ctx, "TAG: PKI Object: "
				    "Invalid length %"SC_FORMAT_LEN_SIZE_T"u", len);
				break;
			}
			sc_debug(card->ctx, SC_LOG_DEBUG_VERBOSE,
			    "TAG: PKI Object nr. %"SC_FORMAT_LEN_SIZE_T"u", i);
			if (i >= CAC_MAX_OBJECTS) {
				free(rbuf);
				return SC_SUCCESS;
			}

			if (cac_parse_properties_object(card, tag, val, len,
			    &prop->objects[i]) == SC_SUCCESS)
				i++;
			break;

		default:
			/* ignore tags we don't understand */
			sc_log(card->ctx, "TAG: Unknown (0x%02x), len=%"
			    SC_FORMAT_LEN_SIZE_T"u", tag, len);
			break;
		}
	}
	free(rbuf);
	/* sanity */
	if (i != prop->num_objects)
		sc_log(card->ctx, "The announced number of objects (%u) "
		    "did not match reality (%"SC_FORMAT_LEN_SIZE_T"u)",
		    prop->num_objects, i);
	prop->num_objects = i;

	return SC_SUCCESS;
}