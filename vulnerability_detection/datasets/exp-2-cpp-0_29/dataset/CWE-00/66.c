TfLiteStatus GatherNd(const TfLiteTensor* params, const TfLiteTensor* indices,
                      TfLiteTensor* output) {
  reference_ops::GatherNd(
      GetTensorShape(params), GetTensorData<ParamsT>(params),
      GetTensorShape(indices), GetTensorData<IndicesT>(indices),
      GetTensorShape(output), GetTensorData<ParamsT>(output));
  return kTfLiteOk;
}