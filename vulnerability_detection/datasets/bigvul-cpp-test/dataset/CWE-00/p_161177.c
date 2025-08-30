AudioInputDeviceManager* MediaStreamManager::audio_input_device_manager() {
  DCHECK_CURRENTLY_ON(BrowserThread::IO);
  DCHECK(audio_input_device_manager_.get());
  return audio_input_device_manager_.get();
}
