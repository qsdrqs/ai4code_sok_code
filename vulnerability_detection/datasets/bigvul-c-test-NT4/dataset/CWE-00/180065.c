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

static int __init big_key_crypto_init(void)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	int ret = -EINVAL;
 
	/* init RNG */
	big_key_rng = crypto_alloc_rng(big_key_rng_name, 0, 0);
	if (IS_ERR(big_key_rng)) {
		big_key_rng = NULL;
		return -EFAULT;
 	}
 
 	/* seed RNG */
	ret = crypto_rng_reset(big_key_rng, NULL, crypto_rng_seedsize(big_key_rng));
	if (ret)
		goto error;
 
 	/* init block cipher */
	big_key_skcipher = crypto_alloc_skcipher(big_key_alg_name,
						 0, CRYPTO_ALG_ASYNC);
	if (IS_ERR(big_key_skcipher)) {
		big_key_skcipher = NULL;
		ret = -EFAULT;
		goto error;
 	}
 
 	return 0;
 
error:
 	crypto_free_rng(big_key_rng);
	big_key_rng = NULL;
 	return ret;
 }
