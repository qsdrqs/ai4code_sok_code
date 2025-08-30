FileManagerBrowserTestBase::CreateDriveIntegrationService(Profile* profile) {
  if (base::FeatureList::IsEnabled(chromeos::features::kDriveFs)) {
    drive_volumes_[profile->GetOriginalProfile()] =
        std::make_unique<DriveFsTestVolume>(profile->GetOriginalProfile());
    if (!IsIncognitoModeTest() && !DoesTestStartWithNoVolumesMounted() &&
        profile->GetPath().BaseName().value() == "user") {
      base::ThreadTaskRunnerHandle::Get()->PostTask(
          FROM_HERE,
          base::BindOnce(base::IgnoreResult(&LocalTestVolume::Mount),
                         base::Unretained(local_volume_.get()), profile));
    }
  } else {
    drive_volumes_[profile->GetOriginalProfile()] =
        std::make_unique<DriveTestVolume>();
  }
  if (DoesTestStartWithNoVolumesMounted()) {
    profile->GetPrefs()->SetBoolean(drive::prefs::kDriveFsPinnedMigrated, true);
  }
  auto* integration_service = drive_volumes_[profile->GetOriginalProfile()]
                                  ->CreateDriveIntegrationService(profile);
  if (DoesTestStartWithNoVolumesMounted()) {
    integration_service->SetEnabled(false);
  }
  return integration_service;
}
