data_cmd(thread_t * thread)
{
	smtp_t *smtp = THREAD_ARG(thread);

	if (send(thread->u.fd, SMTP_DATA_CMD, strlen(SMTP_DATA_CMD), 0) == -1)
		smtp->stage = ERROR;
	return 0;
}
