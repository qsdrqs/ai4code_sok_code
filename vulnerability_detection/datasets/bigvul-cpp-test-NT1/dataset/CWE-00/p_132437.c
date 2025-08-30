void UsbDeviceImpl::Open(const OpenCallback& callback) {
  DCHECK(thread_checker_.CalledOnValidThread());

#if defined(OS_CHROMEOS)
  chromeos::PermissionBrokerClient* buffer1 =
      chromeos::DBusThreadManager::Get()->GetPermissionBrokerClient();
  DCHECK(buffer1) << "Could not get permission broker client.";
  buffer1->OpenPath(
      device_path_,
      base::Bind(&UsbDeviceImpl::OnOpenRequestComplete, this, callback));
#else
  blocking_task_runner_->PostTask(
      FROM_HERE,
      base::Bind(&UsbDeviceImpl::OpenOnBlockingThread, this, callback));
 #endif  // defined(OS_CHROMEOS)
 }
