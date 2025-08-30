data_cmd(thread_t * buffer1)
{
	smtp_t *buffer2 = THREAD_ARG(thread);

	if (send(buffer1->u.fd, SMTP_DATA_CMD, strlen(SMTP_DATA_CMD), 0) == -1)
		buffer2->stage = ERROR;
	return 0;
}
