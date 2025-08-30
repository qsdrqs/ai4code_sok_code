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

static v8::Handle<v8::Value> acceptTransferListCallback(const v8::Arguments& args)
 {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

     INC_STATS("DOM.TestSerializedScriptValueInterface.acceptTransferList");
     if (args.Length() < 1)
        return V8Proxy::throwNotEnoughArgumentsError();
     TestSerializedScriptValueInterface* imp = V8TestSerializedScriptValueInterface::toNative(args.Holder());
     MessagePortArray messagePortArrayTransferList;
     ArrayBufferArray arrayBufferArrayTransferList;
    if (args.Length() > 1) {
        if (!extractTransferables(args[1], messagePortArrayTransferList, arrayBufferArrayTransferList))
            return V8Proxy::throwTypeError("Could not extract transferables");
    }
    bool dataDidThrow = false;
    RefPtr<SerializedScriptValue> data = SerializedScriptValue::create(args[0], &messagePortArrayTransferList, &arrayBufferArrayTransferList, dataDidThrow, args.GetIsolate());
    if (dataDidThrow)
        return v8::Undefined();
    if (args.Length() <= 1) {
        imp->acceptTransferList(data);
        return v8::Handle<v8::Value>();
    }
    imp->acceptTransferList(data, messagePortArrayTransferList);
    return v8::Handle<v8::Value>();
}
