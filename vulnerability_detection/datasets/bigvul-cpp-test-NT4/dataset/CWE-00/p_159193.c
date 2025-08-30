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

void DownloadItemImpl::SetHashState(
    std::unique_ptr<crypto::SecureHash> hash_state) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  hash_state_ = std::move(hash_state);
  if (!hash_state_) {
    destination_info_.hash.clear();
    return;
  }

  std::unique_ptr<crypto::SecureHash> clone_of_hash_state(hash_state_->Clone());
  std::vector<char> hash_value(clone_of_hash_state->GetHashLength());
  clone_of_hash_state->Finish(&hash_value.front(), hash_value.size());
  destination_info_.hash.assign(hash_value.begin(), hash_value.end());
}
