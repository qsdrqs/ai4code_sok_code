static void gf_m2ts_process_tdt_tot(GF_M2TS_Demuxer *ts, GF_M2TS_SECTION_ES *tdt_tot_es, GF_List *sections, u8 table_id, u16 ex_table_id, u8 version_number, u8 last_section_number, u32 status)
{
	unsigned char *data;
	u32 data_size, nb_sections;
	u32 date, yp, mp, k;
	GF_M2TS_Section *section;
	GF_M2TS_TDT_TOT *time_table;
	const char *table_name;

	/*wait for the last section */
	if ( !(status & GF_M2TS_TABLE_END) )
		return;

	switch (table_id) {
	case GF_M2TS_TABLE_ID_TDT:
		table_name = "TDT";
		break;
	case GF_M2TS_TABLE_ID_TOT:
		table_name = "TOT";
		break;
	default:
		GF_LOG(GF_LOG_DEBUG, GF_LOG_CONTAINER, ("[MPEG-2 TS] Unimplemented table_id %u for PID %u\n", table_id, GF_M2TS_PID_TDT_TOT_ST));
		return;
	}

	nb_sections = gf_list_count(sections);
	if (nb_sections > 1) {
		GF_LOG(GF_LOG_WARNING, GF_LOG_CONTAINER, ("[MPEG-2 TS] %s on multiple sections not supported\n", table_name));
	}

	section = (GF_M2TS_Section *)gf_list_get(sections, 0);
	data = section->data;
	data_size = section->data_size;

	/*TOT only contains 40 bits of UTC_time; TDT add descriptors and a CRC*/
	if ((table_id==GF_M2TS_TABLE_ID_TDT) && (data_size != 5)) {
		GF_LOG(GF_LOG_WARNING, GF_LOG_CONTAINER, ("[MPEG-2 TS] Corrupted TDT size\n", table_name));
	}
	GF_SAFEALLOC(time_table, GF_M2TS_TDT_TOT);
	if (!time_table) {
		GF_LOG(GF_LOG_ERROR, GF_LOG_CONTAINER, ("[MPEG-2 TS] Fail to alloc DVB time table\n"));
		return;
	}

	/*UTC_time - see annex C of DVB-SI ETSI EN 300468*/
/* decodes an Modified Julian Date (MJD) into a Co-ordinated Universal Time (UTC)
See annex C of DVB-SI ETSI EN 300468 */
	date = data[0]*256 + data[1];
	yp = (u32)((date - 15078.2)/365.25);
	mp = (u32)((date - 14956.1 - (u32)(yp * 365.25))/30.6001);
	time_table->day = (u32)(date - 14956 - (u32)(yp * 365.25) - (u32)(mp * 30.6001));
	if (mp == 14 || mp == 15) k = 1;
	else k = 0;
	time_table->year = yp + k + 1900;
	time_table->month = mp - 1 - k*12;

	time_table->hour   = 10*((data[2]&0xf0)>>4) + (data[2]&0x0f);
	time_table->minute = 10*((data[3]&0xf0)>>4) + (data[3]&0x0f);
	time_table->second = 10*((data[4]&0xf0)>>4) + (data[4]&0x0f);
	assert(time_table->hour<24 && time_table->minute<60 && time_table->second<60);
	GF_LOG(GF_LOG_DEBUG, GF_LOG_CONTAINER, ("[MPEG-2 TS] Stream UTC time is %u/%02u/%02u %02u:%02u:%02u\n", time_table->year, time_table->month, time_table->day, time_table->hour, time_table->minute, time_table->second));

	switch (table_id) {
	case GF_M2TS_TABLE_ID_TDT:
		if (ts->TDT_time) gf_free(ts->TDT_time);
		ts->TDT_time = time_table;
		if (ts->on_event) ts->on_event(ts, GF_M2TS_EVT_TDT, time_table);
		break;
	case GF_M2TS_TABLE_ID_TOT:
#if 0
	{
		u32 pos, loop_len;
		loop_len = ((data[5]&0x0f) << 8) | (data[6] & 0xff);
		data += 7;
		pos = 0;
		while (pos < loop_len) {
			u8 tag = data[pos];
			pos += 2;
			if (tag == GF_M2TS_DVB_LOCAL_TIME_OFFSET_DESCRIPTOR) {
				char tmp_time[10];
				u16 offset_hours, offset_minutes;
				now->country_code[0] = data[pos];
				now->country_code[1] = data[pos+1];
				now->country_code[2] = data[pos+2];
				now->country_region_id = data[pos+3]>>2;

				sprintf(tmp_time, "%02x", data[pos+4]);
				offset_hours = atoi(tmp_time);
				sprintf(tmp_time, "%02x", data[pos+5]);
				offset_minutes = atoi(tmp_time);
				now->local_time_offset_seconds = (offset_hours * 60 + offset_minutes) * 60;
				if (data[pos+3] & 1) now->local_time_offset_seconds *= -1;

				dvb_decode_mjd_to_unix_time(data+pos+6, &now->unix_next_toc);

				sprintf(tmp_time, "%02x", data[pos+11]);
				offset_hours = atoi(tmp_time);
				sprintf(tmp_time, "%02x", data[pos+12]);
				offset_minutes = atoi(tmp_time);
				now->next_time_offset_seconds = (offset_hours * 60 + offset_minutes) * 60;
				if (data[pos+3] & 1) now->next_time_offset_seconds *= -1;
				pos+= 13;
			}
		}
		/*TODO: check lengths are ok*/
		if (ts->on_event) ts->on_event(ts, GF_M2TS_EVT_TOT, time_table);
	}
#endif
	/*check CRC32*/
	if (ts->tdt_tot->length<4) {
		GF_LOG(GF_LOG_ERROR, GF_LOG_CONTAINER, ("[MPEG-2 TS] corrupted %s table (less than 4 bytes but CRC32 should be present\n", table_name));
		goto error_exit;
	}
	if (!gf_m2ts_crc32_check(ts->tdt_tot->section, ts->tdt_tot->length-4)) {
		GF_LOG(GF_LOG_ERROR, GF_LOG_CONTAINER, ("[MPEG-2 TS] corrupted %s table (CRC32 failed)\n", table_name));
		goto error_exit;
	}
	if (ts->TDT_time) gf_free(ts->TDT_time);
	ts->TDT_time = time_table;
	if (ts->on_event) ts->on_event(ts, GF_M2TS_EVT_TOT, time_table);
	break;
	default:
		assert(0);
		goto error_exit;
	}

	return; /*success*/

error_exit:
	gf_free(time_table);
	return;
}