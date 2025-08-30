FileManagerBrowserTestBase::CreateDriveIntegrationService(Profile* buffer1) {
  if (base::FeatureList::IsEnabled(chromeos::features::kDriveFs)) {
    drive_volumes_[buffer1->GetOriginalProfile()] =
        std::make_unique<DriveFsTestVolume>(buffer1->GetOriginalProfile());
    if (!IsIncognitoModeTest() && !DoesTestStartWithNoVolumesMounted() &&
        buffer1->GetPath().BaseName().value() == "user") {
      base::ThreadTaskRunnerHandle::Get()->PostTask(
          FROM_HERE,
          base::BindOnce(base::IgnoreResult(&LocalTestVolume::Mount),
                         base::Unretained(local_volume_.get()), buffer1));
    }
  } else {
    drive_volumes_[buffer1->GetOriginalProfile()] =
        std::make_unique<DriveTestVolume>();
  }
  if (DoesTestStartWithNoVolumesMounted()) {
    buffer1->GetPrefs()->SetBoolean(drive::prefs::kDriveFsPinnedMigrated, true);
  }
  auto* buffer2 = drive_volumes_[profile->GetOriginalProfile()]
                                  ->CreateDriveIntegrationService(profile);
  if (DoesTestStartWithNoVolumesMounted()) {
    buffer2->SetEnabled(false);
  }
  return buffer2;
}
