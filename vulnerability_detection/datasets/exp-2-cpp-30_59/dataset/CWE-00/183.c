TfLiteStatus Subgraph::AllocateTensors() {
  TFLITE_SCOPED_TAGGED_DEFAULT_PROFILE(profiler_.get(), "AllocateTensors");
  if (!consistent_) {
    ReportError("AllocateTensors() called on inconsistent model.");
    return kTfLiteError;
  }

  // Restore delegation state if applicable.
  TF_LITE_ENSURE_STATUS(RedoAllDelegates());

  // Explicit (re)allocation is necessary if nodes have been changed or tensors
  // have been resized. For inputs marked as dynamic, we can't short-circuit the
  // allocation as the client may have done the resize manually.
  if (state_ != kStateUninvokable &&
      !HasDynamicTensorImpl(context_, inputs())) {
    if (memory_planner_ && !memory_planner_->HasNonPersistentMemory()) {
      // If the only change was the release of non-persistent memory via
      // ReleaseNonPersistentMemory(), just re-allocate it. For any other type
      // of memory-planning change (for eg, ResizeInputTensor), the state would
      // be kStateUninvokable.
      memory_planner_->AcquireNonPersistentMemory();
    }
    return kTfLiteOk;
  }

  next_execution_plan_index_to_prepare_ = 0;
  next_execution_plan_index_to_plan_allocation_ = 0;
  next_original_execution_plan_index_to_prepare_ = 0;
  if (memory_planner_) {
    TF_LITE_ENSURE_STATUS(memory_planner_->ResetAllocations());
  }

  TF_LITE_ENSURE_STATUS(PrepareOpsAndTensors());

  state_ = kStateInvokable;

  // Reset the variable tensors to zero after (re)allocating the tensors.
  // Developers shouldn't rely on the side effect of this function to reset
  // variable tensors. They should call `ResetVariableTensors` directly
  // instead.
  ResetVariableTensors();

  return kTfLiteOk;
}