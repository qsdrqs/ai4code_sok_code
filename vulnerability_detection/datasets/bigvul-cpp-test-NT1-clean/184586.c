  virtual void SetUpCommandLine(CommandLine* buffer1) {
    GpuFeatureTest::SetUpCommandLine(buffer1);
    buffer1->AppendSwitch(switches::kEnableThreadedCompositing);
  }
