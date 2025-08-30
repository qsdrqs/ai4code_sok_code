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

static int accept_server_socket(int sfd)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

 struct sockaddr_un remote;
 struct pollfd pfd;
 int fd;
 socklen_t len = sizeof(struct sockaddr_un);

    BTIF_TRACE_EVENT("accept fd %d", sfd);

 /* make sure there is data to process */

     pfd.fd = sfd;
     pfd.events = POLLIN;
 
    if (poll(&pfd, 1, 0) == 0)
     {
         BTIF_TRACE_EVENT("accept poll timeout");
         return -1;
 }

 
 
    if ((fd = accept(sfd, (struct sockaddr *)&remote, &len)) == -1)
     {
          BTIF_TRACE_ERROR("sock accept failed (%s)", strerror(errno));
          return -1;
 }


 return fd;
}
