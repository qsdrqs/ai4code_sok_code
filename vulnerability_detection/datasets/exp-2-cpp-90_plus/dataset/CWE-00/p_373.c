Status ShapeRefiner::RunShapeFn(const Node* node,
                                const OpRegistrationData* op_reg_data,
                                ExtendedInferenceContext* ec,
                                InferenceContext* outer_context) {
  // This will be filled in with real data in a second pass.
  std::vector<const Tensor*> input_tensors(node->num_inputs(), nullptr);
  std::vector<Tensor> real_tensors(node->num_inputs());
  std::vector<bool> attempted_materialization(node->num_inputs());
  std::vector<bool> attempted_tensor_as_shape_conversion(node->num_inputs());
  std::vector<ShapeHandle> input_tensors_as_shapes;

  auto* c = ec->get_context();

  c->set_input_tensors(input_tensors);
  c->set_input_tensors_as_shapes(input_tensors_as_shapes);

  // Run the shape inference function, and return if there was an error.
  // Capture as lambda, because we might need to re-run inference later on.
  auto run_inference_lambda = [&]() {
    if (function_library_ && IsFunctionCall(*function_library_, *node)) {
      bool disable_shape_inference;
      if (!GetNodeAttr(AttrSlice(node->def()), "_disable_call_shape_inference",
                       &disable_shape_inference)
               .ok() ||
          !disable_shape_inference) {
        // Special inference logic for user-defined functions.
        NameAttrList function;
        TF_RETURN_IF_ERROR(
            NameAndAttrsFromFunctionCall(node->def(), &function));
        const FunctionDef* function_def =
            function_library_->Find(function.name());
        if (function_def != nullptr) {
          // The constant Tensor map we have for the outside context is not
          // valid inside the function. We need to push a new clean map while
          // performing inference on the function body.
          auto const_tensor_map_copy = const_tensor_map_;
          const_tensor_map_.clear();
          Status function_inference_status = InferShapesForFunction(
              function_def, AttrSlice(&function.attr()), ec);
          const_tensor_map_ = const_tensor_map_copy;
          return function_inference_status;
        }
      }
    }

    if (op_reg_data->shape_inference_fn) {
      TF_RETURN_IF_ERROR(c->Run(op_reg_data->shape_inference_fn));
    } else {
      TF_RETURN_IF_ERROR(c->Run(shape_inference::UnknownShape));
    }
    return Status::OK();
  };
  TF_RETURN_IF_ERROR(run_inference_lambda());

  // We must run the shape function repeatedly, in case users write
  // shape functions where they only conditionally call input_tensor()
  // based on the values of another input tensor.
  bool rerun_shape_fn;
  do {
    // If the result of running shape inference would have benefitted
    // from knowing the values of input tensors, try to materialize
    // the results of those tensors, and then run the shape inference
    // function again using those known tensors.
    rerun_shape_fn = false;

    // NOTE: It is possible to batch the extraction and
    // materialization of inputs, instead of materializing one input
    // at a time like we do below.  If input-at-a-time computation
    // becomes a bottleneck, we could separate ExtractConstantSubgraph
    // into two functions: one that returns true if an input is
    // derivable from constants, and another function that extracts
    // the subgraph for multiple target nodes and executes the whole
    // subgraph once.

    for (int i = 0; i < c->num_inputs(); ++i) {
      if (!c->requested_input_tensor(i)) {
        continue;
      }
      // Check if we have not already filled in the requested input,
      // and if not, try to materialize the tensors.
      if (!attempted_materialization[i]) {
        attempted_materialization[i] = true;

        Tensor result;
        bool evaluated = false;
        TF_RETURN_IF_ERROR(EvaluateConstantTensorForEdge(
            node, i, &evaluated, &result, outer_context));
        if (evaluated) {
          real_tensors[i] = result;
          input_tensors[i] = &real_tensors[i];
          // We have more concrete information about a shape,
          // so re-run shape inference.
          rerun_shape_fn = true;
        }
      }
      if (c->requested_input_tensor_as_partial_shape(i) &&
          !attempted_tensor_as_shape_conversion[i]) {
        attempted_tensor_as_shape_conversion[i] = true;
        if (i >= input_tensors_as_shapes.size()) {
          input_tensors_as_shapes.resize(i + 1);
        }
        ShapeHandle s;
        TF_RETURN_IF_ERROR(ConstantPartialShape(c, node, i, &s, outer_context));
        input_tensors_as_shapes[i] = s;
        rerun_shape_fn = true;
      }
    }

    if (rerun_shape_fn) {
      // We have more information about the shapes on this pass,
      // so re-run shape inference.
      c->set_input_tensors(input_tensors);
      c->set_input_tensors_as_shapes(input_tensors_as_shapes);
      TF_RETURN_IF_ERROR(run_inference_lambda());
    }
  } while (rerun_shape_fn);

  return Status::OK();
}