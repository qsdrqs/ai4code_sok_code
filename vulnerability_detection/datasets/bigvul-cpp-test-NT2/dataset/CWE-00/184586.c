  virtual void non_vulnerable_func(CommandLine* command_line) {
    GpuFeatureTest::non_vulnerable_func(command_line);
    command_line->AppendSwitch(switches::kEnableThreadedCompositing);
  }
