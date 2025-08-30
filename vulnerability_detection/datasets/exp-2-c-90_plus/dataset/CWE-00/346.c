static GF_Err gf_text_process_sub(GF_Filter *filter, GF_TXTIn *ctx, GF_FilterPacket *ipck)
{
	u32 i, j, len, line;
	GF_TextSample *samp;
	Double ts_scale;
	char szLine[2048], szTime[20], szText[2048];

	//same setup as for srt
	if (!ctx->is_setup) {
		ctx->is_setup = GF_TRUE;
		return txtin_setup_srt(filter, ctx, GF_FALSE);
	}
	if (!ctx->opid) return GF_NOT_SUPPORTED;
	if (!ctx->playstate) return GF_OK;
	else if (ctx->playstate==2) return GF_EOS;

	if (ctx->seek_state==1) {
		ctx->seek_state = 2;
		gf_fseek(ctx->src, 0, SEEK_SET);
	}

	if (ctx->fps.den && ctx->fps.num) {
		ts_scale = ((Double) ctx->fps.num) / ctx->fps.den;
	} else {
		ts_scale = 25;
	}

	line = 0;

	while (1) {
		char *sOK = gf_text_get_utf8_line(szLine, 2048, ctx->src, ctx->unicode_type);
		if (!sOK) break;

		REM_TRAIL_MARKS(szLine, "\r\n\t ")

		line++;
		len = (u32) strlen(szLine);
		if (!len) continue;

		i=0;
		if (szLine[i] != '{') {
			GF_LOG(GF_LOG_ERROR, GF_LOG_PARSER, ("[TXTIn] Bad SUB file (line %d): expecting \"{\" got \"%c\"\n", line, szLine[i]));
			continue;
		}
		while (szLine[i+1] && szLine[i+1]!='}') {
			szTime[i] = szLine[i+1];
			i++;
			if (i>=19)
				break;
		}
		szTime[i] = 0;
		ctx->start = atoi(szTime);
		if (ctx->start < ctx->end) {
			GF_LOG(GF_LOG_WARNING, GF_LOG_PARSER, ("[TXTIn] corrupted SUB frame (line %d) - starts (at %d ms) before end of previous one (%d ms) - adjusting time stamps\n", line, ctx->start, ctx->end));
			ctx->start = ctx->end;
		}
		j=i+2;
		i=0;
		if (szLine[i+j] != '{') {
			GF_LOG(GF_LOG_WARNING, GF_LOG_PARSER, ("[TXTIn] Bad SUB file - expecting \"{\" got \"%c\"\n", szLine[i]));
			continue;
		}
		while (szLine[i+1+j] && szLine[i+1+j]!='}') {
			szTime[i] = szLine[i+1+j];
			i++;
		}
		szTime[i] = 0;
		ctx->end = atoi(szTime);
		j+=i+2;

		if (ctx->start > ctx->end) {
			GF_LOG(GF_LOG_WARNING, GF_LOG_PARSER, ("[TXTIn] corrupted SUB frame (line %d) - ends (at %d ms) before start of current frame (%d ms) - skipping\n", line, ctx->end, ctx->start));
			continue;
		}

		if (ctx->start && ctx->first_samp) {
			samp = gf_isom_new_text_sample();
			txtin_process_send_text_sample(ctx, samp, 0, (u32) (ts_scale*ctx->start), GF_TRUE);
			ctx->first_samp = GF_FALSE;
			gf_isom_delete_text_sample(samp);
		}

		for (i=j; i<len; i++) {
			if (szLine[i]=='|') {
				szText[i-j] = '\n';
			} else {
				szText[i-j] = szLine[i];
			}
		}
		szText[i-j] = 0;

		if (ctx->prev_end) {
			samp = gf_isom_new_text_sample();
			txtin_process_send_text_sample(ctx, samp, (u64) (ts_scale*(s64)ctx->prev_end), (u32) (ts_scale*(ctx->start - ctx->prev_end)), GF_TRUE);
			gf_isom_delete_text_sample(samp);
		}

		samp = gf_isom_new_text_sample();
		gf_isom_text_add_text(samp, szText, (u32) strlen(szText) );
		txtin_process_send_text_sample(ctx, samp, (u64) (ts_scale*(s64)ctx->start), (u32) (ts_scale*(ctx->end - ctx->start)), GF_TRUE);
		gf_isom_delete_text_sample(samp);

		ctx->prev_end = ctx->end;

		gf_filter_pid_set_info(ctx->opid, GF_PROP_PID_DOWN_BYTES, &PROP_LONGUINT( gf_ftell(ctx->src )) );

		if (gf_filter_pid_would_block(ctx->opid))
			return GF_OK;
	}
	/*final flush*/
	if (ctx->end && !ctx->noflush) {
		samp = gf_isom_new_text_sample();
		txtin_process_send_text_sample(ctx, samp, (u64) (ts_scale*(s64)ctx->end), 0, GF_TRUE);
		gf_isom_delete_text_sample(samp);
	}

	gf_filter_pid_set_info_str( ctx->opid, "ttxt:last_dur", &PROP_UINT(0) );

	return GF_EOS;
}