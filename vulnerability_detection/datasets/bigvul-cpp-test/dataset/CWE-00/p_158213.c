void InstallablePaymentAppCrawler::Start(
    const std::vector<mojom::PaymentMethodDataPtr>& requested_method_data,
    FinishedCrawlingCallback callback,
    base::OnceClosure finished_using_resources) {
  callback_ = std::move(callback);
  finished_using_resources_ = std::move(finished_using_resources);

  std::set<GURL> manifests_to_download;
  for (const auto& method_data : requested_method_data) {
    for (const auto& method_name : method_data->supported_methods) {
      if (!base::IsStringUTF8(method_name))
        continue;
      GURL url = GURL(method_name);
      if (url.is_valid()) {
        manifests_to_download.insert(url);
      }
    }
  }

  if (manifests_to_download.empty()) {
    content::BrowserThread::PostTask(
        content::BrowserThread::UI, FROM_HERE,
        base::BindOnce(
            &InstallablePaymentAppCrawler::FinishCrawlingPaymentAppsIfReady,
            weak_ptr_factory_.GetWeakPtr()));
    return;
  }

  number_of_payment_method_manifest_to_download_ = manifests_to_download.size();
  for (const auto& url : manifests_to_download) {
    downloader_->DownloadPaymentMethodManifest(
        url,
        base::BindOnce(
            &InstallablePaymentAppCrawler::OnPaymentMethodManifestDownloaded,
            weak_ptr_factory_.GetWeakPtr(), url));
  }
}
