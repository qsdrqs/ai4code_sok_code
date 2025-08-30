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

void PreconnectManager::Start(const GURL& url,
                              std::vector<PreconnectRequest> requests) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  DCHECK_CURRENTLY_ON(content::BrowserThread::UI);
  const std::string host = url.host();
  if (preresolve_info_.find(host) != preresolve_info_.end())
    return;

  auto iterator_and_whether_inserted = preresolve_info_.emplace(
      host, std::make_unique<PreresolveInfo>(url, requests.size()));
  PreresolveInfo* info = iterator_and_whether_inserted.first->second.get();
 
   for (auto request_it = requests.begin(); request_it != requests.end();
        ++request_it) {
    DCHECK(request_it->origin.GetOrigin() == request_it->origin);
     PreresolveJobId job_id = preresolve_jobs_.Add(
         std::make_unique<PreresolveJob>(std::move(*request_it), info));
     queued_jobs_.push_back(job_id);
  }

  TryToLaunchPreresolveJobs();
}
