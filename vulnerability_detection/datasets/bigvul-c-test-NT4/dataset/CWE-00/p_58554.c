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

static void* transport_client_thread(void* arg)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
