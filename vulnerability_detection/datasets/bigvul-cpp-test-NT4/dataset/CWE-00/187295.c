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

void FileBrowserHandlerCustomBindings::GetExternalFileEntry(
    const v8::FunctionCallbackInfo<v8::Value>& args) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

//// TODO(zelidrag): Make this magic work on other platforms when file browser
//// matures enough on ChromeOS.
 #if defined(OS_CHROMEOS)
     CHECK(args.Length() == 1);
     CHECK(args[0]->IsObject());
    v8::Local<v8::Object> file_def = args[0]->ToObject();
    std::string file_system_name(
        *v8::String::Utf8Value(file_def->Get(
            v8::String::NewFromUtf8(args.GetIsolate(), "fileSystemName"))));
    GURL file_system_root(
        *v8::String::Utf8Value(file_def->Get(
            v8::String::NewFromUtf8(args.GetIsolate(), "fileSystemRoot"))));
    std::string file_full_path(
        *v8::String::Utf8Value(file_def->Get(
            v8::String::NewFromUtf8(args.GetIsolate(), "fileFullPath"))));
    bool is_directory = file_def->Get(v8::String::NewFromUtf8(
        args.GetIsolate(), "fileIsDirectory"))->ToBoolean()->Value();
    blink::WebDOMFileSystem::EntryType entry_type =
         is_directory ? blink::WebDOMFileSystem::EntryTypeDirectory
                      : blink::WebDOMFileSystem::EntryTypeFile;
     blink::WebLocalFrame* webframe =
        blink::WebLocalFrame::frameForContext(context()->v8_context());
     args.GetReturnValue().Set(
         blink::WebDOMFileSystem::create(
             webframe,
            blink::WebFileSystemTypeExternal,
            blink::WebString::fromUTF8(file_system_name),
            file_system_root)
            .createV8Entry(blink::WebString::fromUTF8(file_full_path),
                           entry_type,
                           args.Holder(),
                           args.GetIsolate()));
 #endif
 }
