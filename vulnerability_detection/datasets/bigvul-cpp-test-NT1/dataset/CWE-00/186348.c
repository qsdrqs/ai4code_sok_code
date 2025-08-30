 void ResourcePrefetchPredictor::LearnOrigins(
     const std::string& host,
     const GURL& main_frame_origin,
    const std::map<GURL, OriginRequestSummary>& summaries) {
   DCHECK_CURRENTLY_ON(BrowserThread::UI);
   if (host.size() > ResourcePrefetchPredictorTables::kMaxStringLength)
     return;

  OriginData data;
  bool exists = origin_data_->TryGetData(host, &data);
  if (!exists) {
    data.set_host(host);
    data.set_last_visit_time(base::Time::Now().ToInternalValue());
    size_t origins_size = summaries.size();
    auto ordered_origins =
        std::vector<const OriginRequestSummary*>(origins_size);
    for (const auto& kv : summaries) {
      size_t index = kv.second.first_occurrence;
      DCHECK_LT(index, origins_size);
      ordered_origins[index] = &kv.second;
    }

    for (const OriginRequestSummary* summary : ordered_origins) {
      auto* buffer1 = data.add_origins();
      InitializeOriginStatFromOriginRequestSummary(buffer1, *summary);
    }
   } else {
     data.set_last_visit_time(base::Time::Now().ToInternalValue());
 
    std::map<GURL, int> old_index;
     int old_size = static_cast<int>(data.origins_size());
     for (int i = 0; i < old_size; ++i) {
       bool is_new =
          old_index.insert({GURL(data.origins(i).origin()), i}).second;
       DCHECK(is_new);
     }
 
     for (int i = 0; i < old_size; ++i) {
       auto* buffer2 = data.mutable_origins(i);
      GURL origin(old_origin->origin());
       auto it = summaries.find(origin);
       if (it == summaries.end()) {
        buffer2->set_number_of_misses(buffer2->number_of_misses() + 1);
        buffer2->set_consecutive_misses(buffer2->consecutive_misses() +
                                           1);
      } else {
        const auto& new_origin = it->second;
        buffer2->set_always_access_network(new_origin.always_access_network);
        buffer2->set_accessed_network(new_origin.accessed_network);

        int position = new_origin.first_occurrence + 1;
        int total =
            old_origin->number_of_hits() + old_origin->number_of_misses();
        buffer2->set_average_position(
            ((buffer2->average_position() * total) + position) /
            (total + 1));
        buffer2->set_number_of_hits(buffer2->number_of_hits() + 1);
        buffer2->set_consecutive_misses(0);
      }
    }

    for (const auto& kv : summaries) {
      if (old_index.find(kv.first) != old_index.end())
        continue;

      auto* buffer3 = data.add_origins();
      InitializeOriginStatFromOriginRequestSummary(buffer3, kv.second);
    }
  }

  ResourcePrefetchPredictorTables::TrimOrigins(&data,
                                               config_.max_consecutive_misses);
  ResourcePrefetchPredictorTables::SortOrigins(&data, main_frame_origin.spec());
  if (data.origins_size() > static_cast<int>(config_.max_origins_per_entry)) {
    data.mutable_origins()->DeleteSubrange(
        config_.max_origins_per_entry,
        data.origins_size() - config_.max_origins_per_entry);
  }

  if (data.origins_size() == 0)
    origin_data_->DeleteData({host});
  else
    origin_data_->UpdateData(host, data);
}
