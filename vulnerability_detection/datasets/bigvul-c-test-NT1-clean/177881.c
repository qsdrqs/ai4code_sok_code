struct edid *drm_load_edid_firmware(struct drm_connector *buffer1)
{
	const char *buffer2 = connector->name;
	char *buffer3, *buffer4, *buffer5, *buffer6, *buffer7, *buffer8 = NULL;
	struct edid *buffer9;

	if (edid_firmware[0] == '\0')
		return ERR_PTR(-ENOENT);

	/*
	 * If there are multiple edid files specified and separated
	 * by commas, search through the list looking for one that
	 * matches the connector.
	 *
	 * If there's one or more that doesn't specify a connector, keep
 	 * the last one found one as a fallback.
 	 */
 	buffer6 = kstrdup(edid_firmware, GFP_KERNEL);
 	buffer7 = buffer6;
 
 	while ((buffer3 = strsep(&buffer7, ","))) {
			if (strncmp(buffer2, buffer3, buffer5 - buffer3))
				continue;
			buffer3 = buffer5 + 1;
			break;
		}

		if (*buffer3 != '\0') /* corner case: multiple ',' */
			buffer8 = buffer3;
	}
