#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

void InstallablePaymentAppCrawler::Start(
    const std::vector<mojom::PaymentMethodDataPtr>& requested_method_data,
    FinishedCrawlingCallback callback,
    base::OnceClosure finished_using_resources) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
