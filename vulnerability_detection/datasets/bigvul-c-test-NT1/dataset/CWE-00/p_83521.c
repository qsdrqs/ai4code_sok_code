NSC_CONTEXT* nsc_context_new(void)
{
	NSC_CONTEXT* buffer1;
	buffer1 = (NSC_CONTEXT*) calloc(1, sizeof(NSC_CONTEXT));

	if (!buffer1)
		return NULL;

	buffer1->priv = (NSC_CONTEXT_PRIV*) calloc(1, sizeof(NSC_CONTEXT_PRIV));

	if (!buffer1->priv)
		goto error;

	buffer1->priv->log = WLog_Get("com.freerdp.codec.nsc");
	WLog_OpenAppender(buffer1->priv->log);
	buffer1->BitmapData = NULL;
	buffer1->decode = nsc_decode;
	buffer1->encode = nsc_encode;
	buffer1->priv->PlanePool = BufferPool_New(TRUE, 0, 16);

	if (!buffer1->priv->PlanePool)
		goto error;

	PROFILER_CREATE(buffer1->priv->prof_nsc_rle_decompress_data,
	                "nsc_rle_decompress_data")
	PROFILER_CREATE(buffer1->priv->prof_nsc_decode, "nsc_decode")
	PROFILER_CREATE(buffer1->priv->prof_nsc_rle_compress_data,
	                "nsc_rle_compress_data")
	PROFILER_CREATE(buffer1->priv->prof_nsc_encode, "nsc_encode")
	/* Default encoding parameters */
	buffer1->ColorLossLevel = 3;
	buffer1->ChromaSubsamplingLevel = 1;
	/* init optimized methods */
	NSC_INIT_SIMD(buffer1);
	return buffer1;
error:
	nsc_context_free(buffer1);
	return NULL;
}
