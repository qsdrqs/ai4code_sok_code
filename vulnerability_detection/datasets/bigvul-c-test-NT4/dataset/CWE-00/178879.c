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

static void update_open_stateflags(struct nfs4_state *state, mode_t open_flags)
 {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	switch (open_flags) {
 		case FMODE_WRITE:
 			state->n_wronly++;
 			break;
		case FMODE_READ:
			state->n_rdonly++;
			break;
 		case FMODE_READ|FMODE_WRITE:
 			state->n_rdwr++;
 	}
	nfs4_state_set_mode_locked(state, state->state | open_flags);
 }
