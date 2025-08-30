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

static int rmd256_init(struct shash_desc *desc)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	struct rmd256_ctx *rctx = shash_desc_ctx(desc);

	rctx->byte_count = 0;

	rctx->state[0] = RMD_H0;
	rctx->state[1] = RMD_H1;
	rctx->state[2] = RMD_H2;
	rctx->state[3] = RMD_H3;
	rctx->state[4] = RMD_H5;
	rctx->state[5] = RMD_H6;
	rctx->state[6] = RMD_H7;
	rctx->state[7] = RMD_H8;

	memset(rctx->buffer, 0, sizeof(rctx->buffer));

	return 0;
}
