Status ConstantFolding::MaterializeConstantValuedNode(
    NodeDef* node, const GraphProperties& properties) {
  if (disable_compressed_tensor_optimization_) {
    return Status::OK();
  }
  // Nodes that generate constant-valued outputs can be represented compactly in
  // compressed format, regardless of their shape.
  const std::vector<OpInfo::TensorProperties>& output_props =
      properties.GetOutputProperties(node->name());
  if (output_props.size() != 1) return Status::OK();
  const auto& output_shape = output_props[0].shape();
  if (!PartialTensorShape(output_shape).IsFullyDefined()) {
    return Status::OK();
  }
  if (IsFill(*node)) {
    const auto output_dtype = output_props[0].dtype();
    NodeDef* input_node = nullptr;
    for (int i = 0; i < 2; ++i) {
      input_node = node_map_->GetNode(NodeName(node->input(i)));
      if (input_node == nullptr || !IsReallyConstant(*input_node)) {
        return Status::OK();
      }
    }
    TF_RETURN_IF_ERROR(CheckAttrExists(*input_node, "value"));

    // Copy the input tensor to the fill node, set the output shape and data
    // type, and change the node type to Const.
    TensorProto* tensor = (*node->mutable_attr())["value"].mutable_tensor();
    const TensorProto& input_tensor = input_node->attr().at("value").tensor();
    if (!input_tensor.tensor_content().empty()) {
      // Convert the value to repeated field format, so we can use the
      // decompression mechanism to store only a single value in the constant
      // node, even if the shape specified in the original Fill is large.
      Tensor t;
      if (!t.FromProto(input_tensor)) {
        return errors::InvalidArgument(
            "Could not construct Tensor form TensorProto in node: ",
            input_node->name());
      }
      tensor->clear_tensor_content();
      t.AsProtoField(tensor);
    } else {
      *tensor = input_tensor;
    }
    *(tensor->mutable_tensor_shape()) = output_shape;
    (*node->mutable_attr())["dtype"].set_type(output_dtype);
    node->mutable_attr()->erase("T");
    node->mutable_attr()->erase("index_type");
    node->set_op("Const");
    for (int i = 0; i < 2; i++) {
      // Change inputs to a control inputs.
      const string ctrl_dep = AsControlDependency(node->input(i));
      node_map_->UpdateInput(node->name(), node->input(i), ctrl_dep);
      node->set_input(i, ctrl_dep);
    }
    graph_modified_ = true;
  } else {
    double value =
        (IsZerosLike(*node) ? 0.0 : (IsOnesLike(*node) ? 1.0 : -1.0));
    if (value >= 0) {
      TF_RETURN_IF_ERROR(ReplaceOperationWithConstant(
          value, properties, output_shape, node, graph_));
    }
  }
  return Status::OK();
}