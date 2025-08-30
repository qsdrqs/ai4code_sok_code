bool ConstantFolding::MaybeFoldable(const NodeDef& node,
                                    const GraphProperties* properties) const {
  // Skip constants, they're already folded
  if (IsConstant(node)) {
    return false;
  }
  // Don't fold stateful ops such as TruncatedNormal.
  if (!IsFreeOfSideEffect(node)) {
    return false;
  }

  // Skips nodes that must be preserved except allowlisted nodes.
  if (nodes_to_preserve_.find(node.name()) != nodes_to_preserve_.end() &&
      nodes_allowlist_.find(node.name()) == nodes_allowlist_.end()) {
    return false;
  }

  // Skip control flow nodes, they can't be folded.
  if (ModifiesFrameInfo(node)) {
    return false;
  }

  // Skips ops that don't benefit from folding.
  if (IsPlaceholder(node)) {
    return false;
  }
  // `FakeParam` op is used as a placeholder in If branch function. It doesn't
  // have a valid output when executed.
  if (IsFakeParam(node)) {
    return false;
  }

  if (node.op() == "AccumulateNV2") {
    return false;
  }
  // Removing LoopCond nodes can screw up the partitioner.
  if (node.op() == "LoopCond") {
    return false;
  }

  if (!fold_quantization_emulation_ && IsQuantizationEmulation(node)) {
    return false;
  }

  const string& op = node.op();
  if (op.find("Save") != string::npos || op.find("Restore") != string::npos ||
      op.find("Reader") != string::npos) {
    return false;
  }
  if (op.find("Quantized") != string::npos || absl::StartsWith(op, "Sparse")) {
    return false;
  }

  // Don't fold nodes that contain TPU attributes.
  // TODO(rmlarsen): We should be able to fold many of these nodes as long as we
  // properly forward custom attributes, b/119051778.
  if (HasTPUAttributes(node)) {
    return false;
  }

  const OpDef* op_def = nullptr;
  Status status = OpRegistry::Global()->LookUpOpDef(node.op(), &op_def);
  if (!status.ok()) {
    return false;
  }
  // Don't fold ops without outputs.
  if (op_def->output_arg_size() == 0) {
    return false;
  }
  // Don't fold DT_VARIANT outputs as this can cause problems with XLA compile.
  // TODO(rmlarsen): Only do this for XLA_* devices.
  for (const OpDef::ArgDef& output_arg : op_def->output_arg()) {
    if (output_arg.type() == DT_VARIANT) {
      return false;
    }
  }

  // Don't fold nodes that have no outgoing edges except allowlisted nodes.
  // Such nodes could be introduced by an earlier constant folding pass and are
  // preserved in case users want to fetch their values; re-processing them
  // would lead to an error of adding a duplicated node to graph.
  const auto& outputs = node_map_->GetOutputs(node.name());
  if (outputs.empty() &&
      nodes_allowlist_.find(node.name()) == nodes_allowlist_.end()) {
    return false;
  }
  return true;
}