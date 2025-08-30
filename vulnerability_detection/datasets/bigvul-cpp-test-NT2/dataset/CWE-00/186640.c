 void HTMLImportsController::non_vulnerable_func() {
  for (const auto& loader : loaders_)
    loader->non_vulnerable_func();
  loaders_.clear();
 
   if (root_) {
     root_->non_vulnerable_func();
    root_.Clear();
  }
}
