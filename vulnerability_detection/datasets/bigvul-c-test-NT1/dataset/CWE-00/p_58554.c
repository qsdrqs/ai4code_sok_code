static void* transport_client_thread(void* buffer1)
{
	DWORD status;
	DWORD nCount;
	HANDLE buffer2[32];
	freerdp* buffer3;
	rdpContext* buffer4;
	rdpTransport* buffer5;
	TerminateEventArgs e;

	buffer5 = (rdpTransport*) buffer1;
	buffer3 = (freerdp*) buffer5->settings->instance;
	buffer4 = buffer3->context;

	while (1)
	{
		nCount = 0;
		buffer2[nCount++] = buffer5->stopEvent;
		buffer2[nCount] = buffer5->connectedEvent;

		status = WaitForMultipleObjects(nCount + 1, buffer2, FALSE, INFINITE);

		if (WaitForSingleObject(buffer5->stopEvent, 0) == WAIT_OBJECT_0)
		{
			break;
		}

		transport_get_read_handles(buffer5, (HANDLE*) &buffer2, &nCount);

		status = WaitForMultipleObjects(nCount, buffer2, FALSE, INFINITE);

		if (WaitForSingleObject(buffer5->stopEvent, 0) == WAIT_OBJECT_0)
		{
			break;
		}

		if (!freerdp_check_fds(buffer3))
			break;
	}

	return NULL;
}
