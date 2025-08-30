static void TestObjectPythonReplaceableAttributeSetterCallback(v8::Local<v8::String> name, v8::Local<v8::Value> jsValue, const v8::PropertyCallbackInfo<void>& info)
{
    TestObjectPythonV8Internal::TestObjectPythonReplaceableAttributeSetter(name, jsValue, info);
}
