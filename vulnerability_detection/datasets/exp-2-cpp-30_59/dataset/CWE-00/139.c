TfLiteStatus GatherNd(const TfLiteEvalTensor* params,
                      const TfLiteEvalTensor* indices,
                      TfLiteEvalTensor* output) {
  const int indices_dims = indices->dims->size;
  const int indices_nd = indices->dims->data[indices_dims - 1];
  const int params_dims = params->dims->size;
  const IndicesT* index_data = tflite::micro::GetTensorData<IndicesT>(indices);
  const ParamsT* param_data = tflite::micro::GetTensorData<ParamsT>(params);
  ParamsT* output_data = tflite::micro::GetTensorData<ParamsT>(output);

  int n_slices = 1;
  for (int i = 0; i < indices_dims - 1; ++i) {
    n_slices *= indices->dims->data[i];
  }

  // If indices[-1] == params.rank, fetch single elements.
  // If indices[-1] < params.rank, fetch slices.
  int slice_size = 1;
  for (int i = indices_nd; i < params_dims; ++i) {
    slice_size *= params->dims->data[i];
  }

  int remain_flat_size = ElementCount(*params->dims);

  // Number of elements per dimension
  int dims_to_count[MAX_INDICES_ND];
  for (int i = 0; i < indices_nd; ++i) {
    dims_to_count[i] = remain_flat_size / params->dims->data[i];
    remain_flat_size = dims_to_count[i];
  }

  for (int i = 0; i < n_slices; ++i) {
    int from_pos = 0;
    for (int j = 0; j < indices_nd; ++j) {
      int offset = i * indices_nd + j;
      IndicesT index = index_data[offset];
      from_pos += index * dims_to_count[j];
    }
    std::memcpy(output_data + i * slice_size, param_data + from_pos,
                sizeof(ParamsT) * slice_size);
  }
  return kTfLiteOk;
}