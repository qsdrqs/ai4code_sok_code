#include <stdio.h>
#include <stdlib.h>
#include <string.h>
kex_new(struct ssh *ssh, char *proposal[PROPOSAL_MAX], struct

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

kex **kexp)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	struct kex *kex;
	int r;

	*kexp = NULL;
	if ((kex = calloc(1, sizeof(*kex))) == NULL)
		return SSH_ERR_ALLOC_FAIL;
	if ((kex->peer = sshbuf_new()) == NULL ||
	    (kex->my = sshbuf_new()) == NULL) {
		r = SSH_ERR_ALLOC_FAIL;
		goto out;
	}
	if ((r = kex_prop2buf(kex->my, proposal)) != 0)
		goto out;
	kex->done = 0;
	kex_reset_dispatch(ssh);
	r = 0;
	*kexp = kex;
 out:
	if (r != 0)
		kex_free(kex);
	return r;
}
