void ConstantFolding::RemoveRedundantVariableUpdates(
    GraphProperties* properties, GraphDef* optimized_graph, NodeDef* node) {
  static const absl::flat_hash_set<string>* kVariableReadOps =
      new absl::flat_hash_set<string>{"AssignAddVariableOp",
                                      "AssignSubVariableOp",
                                      "AssignAdd",
                                      "AssignSub",
                                      "ScatterAdd",
                                      "ScatterSub",
                                      "ScatterMul",
                                      "ScatterDiv",
                                      "ScatterNdAdd",
                                      "ScatterNdSub",
                                      "ScatterNdMul",
                                      "ScatterNdDiv",
                                      "ResourceScatterAdd",
                                      "ResourceScatterSub",
                                      "ResourceScatterMul",
                                      "ResourceScatterDiv",
                                      "ResourceScatterNdAdd",
                                      "ResourceScatterNdSub",
                                      "ResourceScatterNdMul",
                                      "ResourceScatterNdDiv"};
  if (kVariableReadOps == nullptr ||
      kVariableReadOps->find(node->op()) == kVariableReadOps->end())
    return;
  const int value_index = absl::StrContains(node->op(), "Scatter") ? 2 : 1;
  const NodeDef* delta_node = node_map_->GetNode(node->input(value_index));
  if (delta_node == nullptr) return;
  const bool is_add_or_sub = absl::StrContains(node->op(), "Add") ||
                             absl::StrContains(node->op(), "Sub");
  if ((is_add_or_sub && IsZeros(*delta_node)) ||
      (!is_add_or_sub && IsOnes(*delta_node))) {
    VLOG(1) << "Removing redundant variable update: " << node->DebugString();
    if (absl::StrContains(node->op(), "Variable") ||
        absl::StrContains(node->op(), "Resource")) {
      ReplaceOperationWithNoOp(node, properties, optimized_graph);
    } else {
      ReplaceOperationWithIdentity(0 /* input_to_forward */, *properties, node,
                                   optimized_graph);
    }
  }
}