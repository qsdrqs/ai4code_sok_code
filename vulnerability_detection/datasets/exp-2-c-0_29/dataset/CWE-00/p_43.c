Jsi_Value* Jsi_ValueMakeObject(Jsi_Interp *interp, Jsi_Value **vPtr, Jsi_Obj *o)  {
    Jsi_Value *v = (vPtr?*vPtr:NULL);
    if (v && v->vt == JSI_VT_OBJECT && o == v->d.obj)
        return v;
    if (v)
        Jsi_ValueReset(interp, vPtr);
    else
        v = Jsi_ValueNew(interp);
    //Jsi_IncrRefCount(interp, v);
    if (!o)
        o = Jsi_ObjNewType(interp, JSI_OT_OBJECT);
    v->vt = JSI_VT_OBJECT;
    v->d.obj = o;
    Jsi_ObjIncrRefCount(interp,v->d.obj);
    return v;
}