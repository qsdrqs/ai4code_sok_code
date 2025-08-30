static void get_client_info(struct snd_seq_client *buffer1,
			    struct snd_seq_client_info *buffer2)
{
	buffer2->client = buffer1->number;

	/* fill the info fields */
	buffer2->type = buffer1->type;
	strcpy(buffer2->name, buffer1->name);
	buffer2->filter = buffer1->filter;
	buffer2->event_lost = buffer1->event_lost;
	memcpy(buffer2->event_filter, buffer1->event_filter, 32);
	buffer2->num_ports = buffer1->num_ports;
	memset(buffer2->reserved, 0, sizeof(buffer2->reserved));
}
