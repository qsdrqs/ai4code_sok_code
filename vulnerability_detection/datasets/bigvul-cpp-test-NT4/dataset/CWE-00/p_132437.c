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

void UsbDeviceImpl::Open(const OpenCallback& callback) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  DCHECK(thread_checker_.CalledOnValidThread());

#if defined(OS_CHROMEOS)
  chromeos::PermissionBrokerClient* client =
      chromeos::DBusThreadManager::Get()->GetPermissionBrokerClient();
  DCHECK(client) << "Could not get permission broker client.";
  client->OpenPath(
      device_path_,
      base::Bind(&UsbDeviceImpl::OnOpenRequestComplete, this, callback));
#else
  blocking_task_runner_->PostTask(
      FROM_HERE,
      base::Bind(&UsbDeviceImpl::OpenOnBlockingThread, this, callback));
 #endif  // defined(OS_CHROMEOS)
 }
