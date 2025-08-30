media::AudioParameters GetDeviceParametersOnDeviceThread(
    media::AudioManager* buffer1,
    const std::string& unique_id) {
  DCHECK(buffer1->GetTaskRunner()->BelongsToCurrentThread());
  return media::AudioDeviceDescription::IsDefaultDevice(unique_id)
             ? buffer1->GetDefaultOutputStreamParameters()
             : buffer1->GetOutputStreamParameters(unique_id);
}
