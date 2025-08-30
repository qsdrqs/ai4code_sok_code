Jsi_Value* jsi_ValueSubscript(Jsi_Interp *interp, Jsi_Value *target, Jsi_Value *key, Jsi_Value **ret)
{
    int len;
    Jsi_ValueReset(interp, ret);
    Jsi_Value *v = jsi_ValueLookupBase(interp, target, key, ret);
    if (v)
        return v;
    const char *keyStr = Jsi_ValueString(interp, key, NULL);
    if (!keyStr)
        return NULL;
    // Special cases such as "length", "constructor", etc...
    if (Jsi_Strcmp(keyStr,"length")==0) {
        if (Jsi_ValueIsString(interp, target)) {
            len = Jsi_ValueStrlen(target);
        } else if (target->vt == JSI_VT_OBJECT && target->d.obj->isarrlist) {
            len = target->d.obj->arrCnt;
        } else if (target->vt == JSI_VT_OBJECT && target->d.obj->ot == JSI_OT_FUNCTION) {
            Jsi_Func *fo = target->d.obj->d.fobj->func;
            if (fo->type == FC_NORMAL)
                len = fo->argnames->count;
            else
                len = fo->cmdSpec->maxArgs, len = (len>=0?len:fo->cmdSpec->minArgs);
        } else if (target->vt == JSI_VT_OBJECT && target->d.obj->tree) {
            len = target->d.obj->tree->numEntries;
        } else {
            return NULL;
        }
        (*ret)->vt = JSI_VT_NUMBER;
        (*ret)->d.num = (Jsi_Number)len;
        return *ret;
    }

    if (target->vt == JSI_VT_OBJECT && (interp->subOpts.noproto==0 && Jsi_Strcmp(keyStr,"constructor")==0)) {
        const char *cp;
        Jsi_Obj *o = target->d.obj->constructor;
        if (o) {
            if (o->ot == JSI_OT_FUNCTION) {
                Jsi_Value *proto = Jsi_TreeObjGetValue(o, "prototype", 0);
                if (proto && proto->vt == JSI_VT_OBJECT && proto->d.obj->constructor) {
                    o = proto->d.obj->constructor;
                }
            }
        } else {
            switch(target->d.obj->ot) {
                case JSI_OT_NUMBER:
                    cp = "Number";
                    break;
                case JSI_OT_BOOL:
                    cp = "Boolean";
                    break;
                case JSI_OT_STRING:
                    cp = "String";
                    break;
                case JSI_OT_REGEXP:
                    cp = "RegExp";
                    break;
                case JSI_OT_OBJECT:
                    if (target->d.obj->isarrlist) {
                        cp = "Array";
                        break;
                    }
                    cp = "Object";
                    break;
                default:
                    Jsi_ValueMakeUndef(interp, ret);
                    return *ret;
            }
            v = Jsi_ValueObjLookup(interp, interp->csc, cp, 0);
            if (v==NULL || v->vt != JSI_VT_OBJECT)
                return NULL;
            o = target->d.obj->constructor = v->d.obj;
        }
        Jsi_ValueMakeObject(interp, ret, o);
        return *ret;
    }

    if (target->vt == JSI_VT_OBJECT && target->d.obj->ot == JSI_OT_FUNCTION) {
        /* Looking up something like "String.substr" */
        Jsi_Func* func = target->d.obj->d.fobj->func;
        if (func->type == FC_BUILDIN) {
            if (func->f.bits.iscons && func->name) {
                Jsi_Value *v = Jsi_ValueObjLookup(interp, interp->csc, (char*)func->name, 0);
                if (!v) {
                } else {
                    bool ooo = interp->subOpts.noproto;
                    interp->subOpts.noproto = 0;
                    v = Jsi_ValueObjLookup(interp, v, "prototype", 0);
                    interp->subOpts.noproto = ooo;
                    
                    if (v && ((v = Jsi_ValueObjLookup(interp, v, (char*)keyStr, 0)))) {
                        if (v->vt == JSI_VT_OBJECT && v->d.obj->ot == JSI_OT_FUNCTION && Jsi_Strcmp(func->name,"Interp")) {
                            Jsi_Func* sfunc = v->d.obj->d.fobj->func;
                            /* Handle "Math.pow(2,3)", "String.fromCharCode(0x21)", ... */
                            sfunc->callflags.bits.addargs = 1;
                        }
                        return v;
                    }
                }
            }
            if (Jsi_ValueIsString(interp, key)) {
                char *kstr = Jsi_ValueString(interp, key, NULL);
                if (!Jsi_Strcmp(kstr,"call") || !Jsi_Strcmp(kstr,"apply") || !Jsi_Strcmp(kstr,"bind")) {
                    char fbuf[JSI_MAX_NUMBER_STRING];
                    snprintf(fbuf, sizeof(fbuf), "Function.%s", kstr);
                    Jsi_Value *vv = Jsi_NameLookup(interp, fbuf);
                    if (vv)
                        return vv;
                }
            }
        }
    }
    return NULL;
}