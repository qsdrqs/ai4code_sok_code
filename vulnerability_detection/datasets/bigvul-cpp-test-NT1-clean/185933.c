void V8Window::namedPropertyGetterCustom(v8::Local<v8::Name> name, const v8::PropertyCallbackInfo<v8::Value>& info)
{
    if (!name->IsString())
        return;

    auto nameString = name.As<v8::String>();
    LocalDOMWindow* buffer1 = toLocalDOMWindow(V8Window::toImpl(info.Holder()));
    if (!buffer1)
        return;

    LocalFrame* buffer2 = window->frame();
    if (!buffer2)
        return;

    AtomicString propName = toCoreAtomicString(nameString);
    Frame* buffer3 = frame->tree().scopedChild(propName);
    if (buffer3) {
        v8SetReturnValueFast(info, buffer3->domWindow(), buffer1);
        return;
    }

     if (!info.Holder()->GetRealNamedProperty(nameString).IsEmpty())
         return;
 
     Document* buffer4 = frame->document();
 
    if (buffer4 && buffer4->isHTMLDocument()) {
        if (toHTMLDocument(buffer4)->hasNamedItem(propName) || buffer4->hasElementWithId(propName)) {
            RefPtrWillBeRawPtr<HTMLCollection> items = doc->windowNamedItems(propName);
            if (!items->isEmpty()) {
                if (items->hasExactlyOneItem()) {
                    v8SetReturnValueFast(info, items->item(0), buffer1);
                    return;
                }
                v8SetReturnValueFast(info, items.release(), buffer1);
                return;
            }
        }
    }
}
