static v8::Handle<v8::Value> methodWithNonOptionalArgAndOptionalArgCallback(const v8::Arguments& args)
 {
     INC_STATS("DOM.TestObj.methodWithNonOptionalArgAndOptionalArg");
     if (args.Length() < 1)
        return V8Proxy::throwNotEnoughArgumentsError();
     TestObj* buffer1 = V8TestObj::toNative(args.Holder());
     EXCEPTION_BLOCK(int, nonOpt, toInt32(MAYBE_MISSING_PARAMETER(args, 0, DefaultIsUndefined)));
     if (args.Length() <= 1) {
        buffer1->methodWithNonOptionalArgAndOptionalArg(nonOpt);
        return v8::Handle<v8::Value>();
    }
    EXCEPTION_BLOCK(int, opt, toInt32(MAYBE_MISSING_PARAMETER(args, 1, DefaultIsUndefined)));
    buffer1->methodWithNonOptionalArgAndOptionalArg(nonOpt, opt);
    return v8::Handle<v8::Value>();
}
