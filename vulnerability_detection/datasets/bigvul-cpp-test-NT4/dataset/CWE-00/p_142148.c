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

FileManagerBrowserTestBase::CreateDriveIntegrationService(Profile* profile) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
