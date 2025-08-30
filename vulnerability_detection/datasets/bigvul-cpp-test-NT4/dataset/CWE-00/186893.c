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

void CoordinatorImpl::RequestGlobalMemoryDumpAndAppendToTrace(
     MemoryDumpType dump_type,
     MemoryDumpLevelOfDetail level_of_detail,
     const RequestGlobalMemoryDumpAndAppendToTraceCallback& callback) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

   auto adapter =
       [](const RequestGlobalMemoryDumpAndAppendToTraceCallback& callback,
         bool success, uint64_t dump_guid,
         mojom::GlobalMemoryDumpPtr) { callback.Run(success, dump_guid); };

  QueuedRequest::Args args(dump_type, level_of_detail, {},
                           true /* add_to_trace */, base::kNullProcessId);
  RequestGlobalMemoryDumpInternal(args, base::BindRepeating(adapter, callback));
}
