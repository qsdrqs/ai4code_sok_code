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

static v8::Handle<v8::Value> methodWithNonOptionalArgAndOptionalArgCallback(const v8::Arguments& args)
 {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

     INC_STATS("DOM.TestObj.methodWithNonOptionalArgAndOptionalArg");
     if (args.Length() < 1)
        return V8Proxy::throwNotEnoughArgumentsError();
     TestObj* imp = V8TestObj::toNative(args.Holder());
     EXCEPTION_BLOCK(int, nonOpt, toInt32(MAYBE_MISSING_PARAMETER(args, 0, DefaultIsUndefined)));
     if (args.Length() <= 1) {
        imp->methodWithNonOptionalArgAndOptionalArg(nonOpt);
        return v8::Handle<v8::Value>();
    }
    EXCEPTION_BLOCK(int, opt, toInt32(MAYBE_MISSING_PARAMETER(args, 1, DefaultIsUndefined)));
    imp->methodWithNonOptionalArgAndOptionalArg(nonOpt, opt);
    return v8::Handle<v8::Value>();
}
