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

sc_get_driver(void)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	struct sc_card_driver *iso_drv = sc_get_iso7816_driver();

	if (!iso_ops)
		iso_ops = iso_drv->ops;

	authentic_ops = *iso_ops;

	authentic_ops.match_card = authentic_match_card;
	authentic_ops.init = authentic_init;
	authentic_ops.finish = authentic_finish;
	authentic_ops.read_binary = authentic_read_binary;
	authentic_ops.write_binary = authentic_write_binary;
	authentic_ops.update_binary = authentic_update_binary;
	authentic_ops.erase_binary = authentic_erase_binary;
	/* authentic_ops.resize_file = authentic_resize_file; */
	authentic_ops.select_file = authentic_select_file;
	/* get_response: Untested */
	authentic_ops.get_challenge = authentic_get_challenge;
	authentic_ops.set_security_env = authentic_set_security_env;
	/* decipher: Untested */
	authentic_ops.decipher = authentic_decipher;
	/* authentic_ops.compute_signature = authentic_compute_signature; */
	authentic_ops.create_file = authentic_create_file;
	authentic_ops.delete_file = authentic_delete_file;
	authentic_ops.card_ctl = authentic_card_ctl;
	authentic_ops.process_fci = authentic_process_fci;
	authentic_ops.pin_cmd = authentic_pin_cmd;
	authentic_ops.card_reader_lock_obtained = authentic_card_reader_lock_obtained;

	return &authentic_drv;
}
