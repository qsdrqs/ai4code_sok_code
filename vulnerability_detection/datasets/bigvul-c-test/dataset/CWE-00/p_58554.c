static void* transport_client_thread(void* arg)
{
	DWORD status;
	DWORD nCount;
	HANDLE events[32];
	freerdp* instance;
	rdpContext* context;
	rdpTransport* transport;
	TerminateEventArgs e;

	transport = (rdpTransport*) arg;
	instance = (freerdp*) transport->settings->instance;
	context = instance->context;

	while (1)
	{
		nCount = 0;
		events[nCount++] = transport->stopEvent;
		events[nCount] = transport->connectedEvent;

		status = WaitForMultipleObjects(nCount + 1, events, FALSE, INFINITE);

		if (WaitForSingleObject(transport->stopEvent, 0) == WAIT_OBJECT_0)
		{
			break;
		}

		transport_get_read_handles(transport, (HANDLE*) &events, &nCount);

		status = WaitForMultipleObjects(nCount, events, FALSE, INFINITE);

		if (WaitForSingleObject(transport->stopEvent, 0) == WAIT_OBJECT_0)
		{
			break;
		}

		if (!freerdp_check_fds(instance))
			break;
	}

	return NULL;
}
