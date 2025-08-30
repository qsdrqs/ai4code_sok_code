static v8::Handle<v8::Value> convert4Callback(const v8::Arguments& args)
 {
     INC_STATS("DOM.TestObj.convert4");
     if (args.Length() < 1)
        return V8Proxy::throwNotEnoughArgumentsError();
     TestObj* buffer1 = V8TestObj::toNative(args.Holder());
     EXCEPTION_BLOCK(d*, , V8d::HasInstance(MAYBE_MISSING_PARAMETER(args, 0, DefaultIsUndefined)) ? V8d::toNative(v8::Handle<v8::Object>::Cast(MAYBE_MISSING_PARAMETER(args, 0, DefaultIsUndefined))) : 0);
     buffer1->convert4();
    return v8::Handle<v8::Value>();
}
