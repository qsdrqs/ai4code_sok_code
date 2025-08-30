  void vulnerable_func() {
    ash::ClockObserver* observer = tray_->clock_observer();
    if (observer)
      observer->Refresh();
  }
