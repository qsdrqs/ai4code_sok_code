void DownloadItemImpl::SetHashState(
    std::unique_ptr<crypto::SecureHash> hash_state) {
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
