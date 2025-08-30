TfLiteStatus PopulateQuantizedLstmParams8x8_16(
    TfLiteContext* context, TfLiteNode* node,
    lstm_eval::IntegerLstmParameter* integer_lstm_param) {
  // Calculate quantized clip for projection and cell.
  const auto* params =
      static_cast<TfLiteUnidirectionalSequenceLSTMParams*>(node->builtin_data);
  const float cell_clip = params->cell_clip;
  const float proj_clip = params->proj_clip;

  const TfLiteTensor* cell_state =
      GetVariableInput(context, node, lstm::full::kCellStateTensor);
  TF_LITE_ENSURE(context, cell_state != nullptr);
  TfLiteTensor* output_tensor;
  TF_LITE_ENSURE_OK(
      context,
      GetOutputSafe(context, node, lstm::full::kOutputTensor, &output_tensor));

  auto* cell_state_params =
      static_cast<TfLiteAffineQuantization*>(cell_state->quantization.params);
  auto* proj_params = static_cast<TfLiteAffineQuantization*>(
      output_tensor->quantization.params);
  if (cell_clip > 0.0) {
    integer_lstm_param->quantized_cell_clip = static_cast<int16_t>(std::min(
        std::max(cell_clip / cell_state_params->scale->data[0], -32768.0f),
        32767.0f));
  } else {
    integer_lstm_param->quantized_cell_clip = 0;
  }
  if (proj_clip > 0.0) {
    integer_lstm_param->quantized_proj_clip = static_cast<int8_t>(std::min(
        std::max(proj_clip / proj_params->scale->data[0], -128.0f), 127.0f));
  } else {
    integer_lstm_param->quantized_proj_clip = 0;
  }

  // Calculate effective scales.
  OpData* op_data = static_cast<OpData*>(node->user_data);
  const bool use_layer_norm = op_data->use_layer_norm;

  const TfLiteTensor* input;
  TF_LITE_ENSURE_OK(
      context, GetInputSafe(context, node, lstm::full::kInputTensor, &input));

  const TfLiteTensor* input_to_input_weights = GetOptionalInputTensor(
      context, node, lstm::full::kInputToInputWeightsTensor);
  const TfLiteTensor* input_to_forget_weights;
  TF_LITE_ENSURE_OK(
      context,
      GetInputSafe(context, node, lstm::full::kInputToForgetWeightsTensor,
                   &input_to_forget_weights));
  const TfLiteTensor* input_to_cell_weights;
  TF_LITE_ENSURE_OK(context, GetInputSafe(context, node,
                                          lstm::full::kInputToCellWeightsTensor,
                                          &input_to_cell_weights));
  const TfLiteTensor* input_to_output_weights;
  TF_LITE_ENSURE_OK(
      context,
      GetInputSafe(context, node, lstm::full::kInputToOutputWeightsTensor,
                   &input_to_output_weights));

  const TfLiteTensor* recurrent_to_input_weights = GetOptionalInputTensor(
      context, node, lstm::full::kRecurrentToInputWeightsTensor);
  const TfLiteTensor* recurrent_to_forget_weights;
  TF_LITE_ENSURE_OK(
      context,
      GetInputSafe(context, node, lstm::full::kRecurrentToForgetWeightsTensor,
                   &recurrent_to_forget_weights));
  const TfLiteTensor* recurrent_to_cell_weights;
  TF_LITE_ENSURE_OK(
      context,
      GetInputSafe(context, node, lstm::full::kRecurrentToCellWeightsTensor,
                   &recurrent_to_cell_weights));
  const TfLiteTensor* recurrent_to_output_weights;
  TF_LITE_ENSURE_OK(
      context,
      GetInputSafe(context, node, lstm::full::kRecurrentToOutputWeightsTensor,
                   &recurrent_to_output_weights));

  const TfLiteTensor* cell_to_input_weights = GetOptionalInputTensor(
      context, node, lstm::full::kCellToInputWeightsTensor);
  const TfLiteTensor* cell_to_forget_weights = GetOptionalInputTensor(
      context, node, lstm::full::kCellToForgetWeightsTensor);
  const TfLiteTensor* cell_to_output_weights = GetOptionalInputTensor(
      context, node, lstm::full::kCellToOutputWeightsTensor);

  const TfLiteTensor* input_layer_norm_coefficients = GetOptionalInputTensor(
      context, node, lstm::full::kInputLayerNormCoefficientsTensor);
  const TfLiteTensor* forget_layer_norm_coefficients = GetOptionalInputTensor(
      context, node, lstm::full::kForgetLayerNormCoefficientsTensor);
  const TfLiteTensor* cell_layer_norm_coefficients = GetOptionalInputTensor(
      context, node, lstm::full::kCellLayerNormCoefficientsTensor);
  const TfLiteTensor* output_layer_norm_coefficients = GetOptionalInputTensor(
      context, node, lstm::full::kOutputLayerNormCoefficientsTensor);

  const TfLiteTensor* projection_weights = GetOptionalInputTensor(
      context, node, lstm::full::kProjectionWeightsTensor);

  TfLiteTensor* output_state =
      GetVariableInput(context, node, lstm::full::kOutputStateTensor);
  TF_LITE_ENSURE(context, output_state != nullptr);

  // Since we have already checked that weights are all there or none, we can
  // check the existence of only one to get the condition.
  const bool use_cifg = (input_to_input_weights == nullptr);
  const bool use_peephole = (cell_to_output_weights != nullptr);
  const bool use_projection = (projection_weights != nullptr);

  // Get intermediate scales and zero points.
  std::vector<float> intermediate_scale;
  std::vector<int32> intermediate_zp;
  for (int i = 0; i < 4; ++i) {
    if (use_layer_norm) {
      TfLiteTensor* intermediate;
      TF_LITE_ENSURE_OK(context,
                        GetIntermediatesSafe(context, node, i, &intermediate));
      auto* params = static_cast<TfLiteAffineQuantization*>(
          intermediate->quantization.params);
      intermediate_scale.push_back(params->scale->data[0]);
      intermediate_zp.push_back(params->zero_point->data[0]);
    } else {
      // Q3.12 for activation functions.
      intermediate_scale.push_back(std::pow(2, -12));
      intermediate_zp.push_back(0);
    }
  }
  // In the absence of projection, hidden becomes otuput and this intermediate
  // is ignored.
  TfLiteTensor* hidden;
  TF_LITE_ENSURE_OK(context, GetIntermediatesSafe(context, node, 4, &hidden));
  auto* hidden_params =
      static_cast<TfLiteAffineQuantization*>(hidden->quantization.params);
  intermediate_scale.push_back(hidden_params->scale->data[0]);
  intermediate_zp.push_back(hidden_params->zero_point->data[0]);

  // Scales.
  const float default_scale = 1.0;
  float input_scale = default_scale;
  float input_to_input_weight_scale = default_scale;
  float recurrent_to_input_weight_scale = default_scale;
  float cell_to_input_weight_scale = default_scale;
  float input_to_forget_weight_scale = default_scale;
  float recurrent_to_forget_weight_scale = default_scale;
  float cell_to_forget_weight_scale = default_scale;
  float input_to_cell_weight_scale = default_scale;
  float recurrent_to_cell_weight_scale = default_scale;
  float input_to_output_weight_scale = default_scale;
  float recurrent_to_output_weight_scale = default_scale;
  float cell_to_output_weight_scale = default_scale;
  float projection_weight_scale = default_scale;
  float layer_norm_input_scale = default_scale;
  float layer_norm_forget_scale = default_scale;
  float layer_norm_cell_scale = default_scale;
  float layer_norm_output_scale = default_scale;
  float output_state_scale = default_scale;
  int cell_scale = 1;

  // Effective scales.
  float effective_input_to_input_scale = default_scale;
  float effective_recurrent_to_input_scale = default_scale;
  float effective_cell_to_input_scale = default_scale;
  float effective_input_to_forget_scale = default_scale;
  float effective_recurrent_to_forget_scale = default_scale;
  float effective_cell_to_forget_scale = default_scale;
  float effective_input_to_cell_scale = default_scale;
  float effective_recurrent_to_cell_scale = default_scale;
  float effective_input_to_output_scale = default_scale;
  float effective_recurrent_to_output_scale = default_scale;
  float effective_cell_to_output_scale = default_scale;
  float effective_proj_scale = default_scale;
  float effective_hidden_scale = default_scale;

  // Populate scales.
  if (!use_cifg) {
    input_to_input_weight_scale = input_to_input_weights->params.scale;
    recurrent_to_input_weight_scale = recurrent_to_input_weights->params.scale;
  }

  if (use_peephole) {
    if (!use_cifg) {
      cell_to_input_weight_scale = cell_to_input_weights->params.scale;
    }
    cell_to_forget_weight_scale = cell_to_forget_weights->params.scale;
    cell_to_output_weight_scale = cell_to_output_weights->params.scale;
  }

  if (use_layer_norm) {
    if (!use_cifg) {
      layer_norm_input_scale = input_layer_norm_coefficients->params.scale;
    }
    layer_norm_forget_scale = forget_layer_norm_coefficients->params.scale;
    layer_norm_cell_scale = cell_layer_norm_coefficients->params.scale;
    layer_norm_output_scale = output_layer_norm_coefficients->params.scale;
  }

  if (use_projection) {
    projection_weight_scale = projection_weights->params.scale;
  }
  output_state_scale = output_state->params.scale;

  input_to_forget_weight_scale = input_to_forget_weights->params.scale;
  input_to_cell_weight_scale = input_to_cell_weights->params.scale;
  input_to_output_weight_scale = input_to_output_weights->params.scale;
  recurrent_to_forget_weight_scale = recurrent_to_forget_weights->params.scale;
  recurrent_to_cell_weight_scale = recurrent_to_cell_weights->params.scale;
  recurrent_to_output_weight_scale = recurrent_to_output_weights->params.scale;

  // Check cell state (already used above)
  TF_LITE_ENSURE(context, CheckedLog2(cell_state->params.scale, &cell_scale));
  // TF_LITE_ENSURE(context, cell_scale <= -9);
  integer_lstm_param->cell_scale = cell_scale;
  input_scale = input->params.scale;

  // Calculate effective scales.
  if (!use_cifg) {
    effective_input_to_input_scale =
        input_to_input_weight_scale * input_scale / intermediate_scale[0];
    effective_recurrent_to_input_scale = recurrent_to_input_weight_scale *
                                         output_state_scale /
                                         intermediate_scale[0];
  }
  effective_input_to_forget_scale =
      input_to_forget_weight_scale * input_scale / intermediate_scale[1];
  effective_recurrent_to_forget_scale = recurrent_to_forget_weight_scale *
                                        output_state_scale /
                                        intermediate_scale[1];

  effective_input_to_cell_scale =
      input_to_cell_weight_scale * input_scale / intermediate_scale[2];
  effective_recurrent_to_cell_scale = recurrent_to_cell_weight_scale *
                                      output_state_scale /
                                      intermediate_scale[2];

  effective_input_to_output_scale =
      input_to_output_weight_scale * input_scale / intermediate_scale[3];
  effective_recurrent_to_output_scale = recurrent_to_output_weight_scale *
                                        output_state_scale /
                                        intermediate_scale[3];

  effective_hidden_scale =
      std::pow(2, -15) / intermediate_scale[4] * std::pow(2, -15);

  effective_proj_scale =
      projection_weight_scale * intermediate_scale[4] / output_state_scale;

  if (use_peephole) {
    if (!use_cifg) {
      effective_cell_to_input_scale = std::pow(2, cell_scale) *  // NOLINT
                                      cell_to_input_weight_scale /
                                      intermediate_scale[0];
    }
    effective_cell_to_forget_scale = std::pow(2, cell_scale) *  // NOLINT
                                     cell_to_forget_weight_scale /
                                     intermediate_scale[1];
    effective_cell_to_output_scale = std::pow(2, cell_scale) *  // NOLINT
                                     cell_to_output_weight_scale /
                                     intermediate_scale[3];
  }

  // Decompose scales.
  QuantizeMultiplier(effective_input_to_input_scale,
                     &integer_lstm_param->effective_input_to_input_scale_a,
                     &integer_lstm_param->effective_input_to_input_scale_b);
  QuantizeMultiplier(effective_recurrent_to_input_scale,
                     &integer_lstm_param->effective_recurrent_to_input_scale_a,
                     &integer_lstm_param->effective_recurrent_to_input_scale_b);
  QuantizeMultiplier(effective_cell_to_input_scale,
                     &integer_lstm_param->effective_cell_to_input_scale_a,
                     &integer_lstm_param->effective_cell_to_input_scale_b);
  QuantizeMultiplier(effective_input_to_forget_scale,
                     &integer_lstm_param->effective_input_to_forget_scale_a,
                     &integer_lstm_param->effective_input_to_forget_scale_b);
  QuantizeMultiplier(
      effective_recurrent_to_forget_scale,
      &integer_lstm_param->effective_recurrent_to_forget_scale_a,
      &integer_lstm_param->effective_recurrent_to_forget_scale_b);
  QuantizeMultiplier(effective_cell_to_forget_scale,
                     &integer_lstm_param->effective_cell_to_forget_scale_a,
                     &integer_lstm_param->effective_cell_to_forget_scale_b);
  QuantizeMultiplier(effective_input_to_cell_scale,
                     &integer_lstm_param->effective_input_to_cell_scale_a,
                     &integer_lstm_param->effective_input_to_cell_scale_b);
  QuantizeMultiplier(effective_recurrent_to_cell_scale,
                     &integer_lstm_param->effective_recurrent_to_cell_scale_a,
                     &integer_lstm_param->effective_recurrent_to_cell_scale_b);
  QuantizeMultiplier(effective_input_to_output_scale,
                     &integer_lstm_param->effective_input_to_output_scale_a,
                     &integer_lstm_param->effective_input_to_output_scale_b);
  QuantizeMultiplier(
      effective_recurrent_to_output_scale,
      &integer_lstm_param->effective_recurrent_to_output_scale_a,
      &integer_lstm_param->effective_recurrent_to_output_scale_b);
  QuantizeMultiplier(effective_cell_to_output_scale,
                     &integer_lstm_param->effective_cell_to_output_scale_a,
                     &integer_lstm_param->effective_cell_to_output_scale_b);
  QuantizeMultiplier(effective_proj_scale,
                     &integer_lstm_param->effective_proj_scale_a,
                     &integer_lstm_param->effective_proj_scale_b);
  QuantizeMultiplier(effective_hidden_scale,
                     &integer_lstm_param->effective_hidden_scale_a,
                     &integer_lstm_param->effective_hidden_scale_b);
  QuantizeMultiplier(layer_norm_input_scale,
                     &integer_lstm_param->layer_norm_input_scale_a,
                     &integer_lstm_param->layer_norm_input_scale_b);
  QuantizeMultiplier(layer_norm_forget_scale,
                     &integer_lstm_param->layer_norm_forget_scale_a,
                     &integer_lstm_param->layer_norm_forget_scale_b);
  QuantizeMultiplier(layer_norm_cell_scale,
                     &integer_lstm_param->layer_norm_cell_scale_a,
                     &integer_lstm_param->layer_norm_cell_scale_b);
  QuantizeMultiplier(layer_norm_output_scale,
                     &integer_lstm_param->layer_norm_output_scale_a,
                     &integer_lstm_param->layer_norm_output_scale_b);

  integer_lstm_param->hidden_zp = intermediate_zp[4];

  // 10000 is used to make sure the kernel logic does not overflow.
  if (!use_cifg) {
    integer_lstm_param->input_variance_guard =
        std::max(1, static_cast<int32_t>(10000 * layer_norm_input_scale));
  }
  integer_lstm_param->forget_variance_guard =
      std::max(1, static_cast<int32_t>(10000 * layer_norm_forget_scale));
  integer_lstm_param->cell_variance_guard =
      std::max(1, static_cast<int32_t>(10000 * layer_norm_cell_scale));
  integer_lstm_param->output_variance_guard =
      std::max(1, static_cast<int32_t>(10000 * layer_norm_output_scale));

  return kTfLiteOk;
}