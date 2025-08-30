void PlatformSensorFusion::vulnerable_func() {
  for (const auto& pair : source_sensors_)
    pair.second->StopListening(this);

  fusion_algorithm_->Reset();
}
