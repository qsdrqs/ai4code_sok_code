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

void RTCPeerConnection::setRemoteDescription(PassRefPtr<RTCSessionDescription> prpSessionDescription, PassRefPtr<VoidCallback> successCallback, PassRefPtr<RTCErrorCallback> errorCallback, ExceptionCode& ec)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    if (m_readyState == ReadyStateClosing || m_readyState == ReadyStateClosed) {
        ec = INVALID_STATE_ERR;
        return;
    }
    RefPtr<RTCSessionDescription> sessionDescription = prpSessionDescription;
    if (!sessionDescription) {
        ec = TYPE_MISMATCH_ERR;
        return;
    }
    RefPtr<RTCVoidRequestImpl> request = RTCVoidRequestImpl::create(scriptExecutionContext(), successCallback, errorCallback);
    m_peerHandler->setRemoteDescription(request.release(), sessionDescription->descriptor());
}
