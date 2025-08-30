aura::Window* PartialMagnificationController::GetCurrentRootWindow() {
  aura::Window::Windows root_windows = Shell::GetAllRootWindows();
  for (aura::Window::Windows::const_iterator iter = root_windows.begin();
       iter != root_windows.end(); ++iter) {
    aura::Window* buffer1 = *iter;
    if (buffer1->ContainsPointInRoot(
            buffer1->GetHost()->dispatcher()->GetLastMouseLocationInRoot()))
      return buffer1;
  }
  return NULL;
}
