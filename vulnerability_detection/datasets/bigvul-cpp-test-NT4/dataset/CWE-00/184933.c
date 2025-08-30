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

void BluetoothDeviceChromeOS::UnregisterAgent() {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  if (!agent_.get())
    return;
  DCHECK(pairing_delegate_);
  DCHECK(pincode_callback_.is_null());
  DCHECK(passkey_callback_.is_null());
  DCHECK(confirmation_callback_.is_null());
  pairing_delegate_->DismissDisplayOrConfirm();
  pairing_delegate_ = NULL;
  agent_.reset();
  VLOG(1) << object_path_.value() << ": Unregistering pairing agent";
  DBusThreadManager::Get()->GetBluetoothAgentManagerClient()->
      UnregisterAgent(
          dbus::ObjectPath(kAgentPath),
          base::Bind(&base::DoNothing),
          base::Bind(&BluetoothDeviceChromeOS::OnUnregisterAgentError,
                     weak_ptr_factory_.GetWeakPtr()));
}
