#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

NSC_CONTEXT* nsc_context_new(void)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	NSC_CONTEXT* context;
	context = (NSC_CONTEXT*) calloc(1, sizeof(NSC_CONTEXT));

	if (!context)
		return NULL;

	context->priv = (NSC_CONTEXT_PRIV*) calloc(1, sizeof(NSC_CONTEXT_PRIV));

	if (!context->priv)
		goto error;

	context->priv->log = WLog_Get("com.freerdp.codec.nsc");
	WLog_OpenAppender(context->priv->log);
	context->BitmapData = NULL;
	context->decode = nsc_decode;
	context->encode = nsc_encode;
	context->priv->PlanePool = BufferPool_New(TRUE, 0, 16);

	if (!context->priv->PlanePool)
		goto error;

	PROFILER_CREATE(context->priv->prof_nsc_rle_decompress_data,
	                "nsc_rle_decompress_data")
	PROFILER_CREATE(context->priv->prof_nsc_decode, "nsc_decode")
	PROFILER_CREATE(context->priv->prof_nsc_rle_compress_data,
	                "nsc_rle_compress_data")
	PROFILER_CREATE(context->priv->prof_nsc_encode, "nsc_encode")
	/* Default encoding parameters */
	context->ColorLossLevel = 3;
	context->ChromaSubsamplingLevel = 1;
	/* init optimized methods */
	NSC_INIT_SIMD(context);
	return context;
error:
	nsc_context_free(context);
	return NULL;
}
