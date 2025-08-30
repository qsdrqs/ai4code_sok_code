void fx_TypedArray(txMachine* the)
{
	txSlot* instance = fxConstructTypedArray(the);
	txSlot* dispatch = instance->next;
	txSlot* view = dispatch->next;
	txSlot* buffer = view->next;
	txSlot* data = C_NULL;
	txU2 shift = dispatch->value.typedArray.dispatch->shift;
	txSlot* slot;
	if ((mxArgc > 0) && (mxArgv(0)->kind == XS_REFERENCE_KIND)) {
		slot = mxArgv(0)->value.reference->next;
		if (slot && ((slot->kind == XS_ARRAY_BUFFER_KIND) || (slot->kind == XS_HOST_KIND))) {
			txInteger offset = fxArgToByteLength(the, 1, 0);
			txInteger size;
			txSlot* info;
			if (offset & ((1 << shift) - 1))
				mxRangeError("invalid byteOffset %ld", offset);
			size = fxArgToByteLength(the, 2, -1);
			info = fxGetBufferInfo(the, mxArgv(0));
			if (size >= 0) {
				size <<= shift;
				if (info->value.bufferInfo.length < (offset + size))
					mxRangeError("out of range byteLength %ld", size);
			}
			else {
				if (info->value.bufferInfo.length & ((1 << shift) - 1))
					mxRangeError("invalid byteLength %ld", info->value.bufferInfo.length);
				size = info->value.bufferInfo.length - offset;
				if (size < 0)
					mxRangeError("out of range byteLength %ld", size);
				if (info->value.bufferInfo.maxLength >= 0)
					size = -1;
			}
			view->value.dataView.offset = offset;
			view->value.dataView.size = size;
			buffer->kind = XS_REFERENCE_KIND;
			buffer->value.reference = mxArgv(0)->value.reference;
		}
		else if (slot && (slot->kind == XS_TYPED_ARRAY_KIND)) {
			txSlot* sourceDispatch = slot;
			txSlot* sourceView = sourceDispatch->next;
			txSlot* sourceBuffer = sourceView->next;
			txU2 sourceShift = sourceDispatch->value.typedArray.dispatch->shift;
			txInteger sourceLength = fxCheckDataViewSize(the, sourceView, sourceBuffer, XS_IMMUTABLE) >> sourceShift;
			txSlot* sourceData = sourceBuffer->value.reference->next;
			txInteger sourceDelta = sourceDispatch->value.typedArray.dispatch->size;
			txInteger sourceOffset = sourceView->value.dataView.offset;
			txInteger offset = 0;
			txInteger size = sourceLength << shift;
			/* THIS */
			mxPushUninitialized();	
			/* FUNCTION */
			mxPush(mxArrayBufferConstructor);
			/* TARGET */
			if (sourceData->kind == XS_ARRAY_BUFFER_KIND) {
				mxPushSlot(sourceBuffer);
				mxGetID(mxID(_constructor));
				fxToSpeciesConstructor(the, &mxArrayBufferConstructor);
			}
			else
				mxPush(mxArrayBufferConstructor);
			/* RESULT */
			mxPushUndefined();	
			mxPushUninitialized();	
			mxPushUninitialized();	
			/* ARGUMENTS */
			sourceLength = fxGetDataViewSize(the, sourceView, sourceBuffer) >> sourceShift;
			size = sourceLength << shift;
			mxPushInteger(size);
			mxRunCount(1);
			mxPullSlot(buffer);
			sourceLength = fxCheckDataViewSize(the, sourceView, sourceBuffer, XS_IMMUTABLE) >> sourceShift;
			size = sourceLength << shift;
			
			data = fxCheckArrayBufferDetached(the, buffer, XS_MUTABLE);
			view->value.dataView.offset = offset;
			view->value.dataView.size = size;
			if (dispatch == sourceDispatch)
				c_memcpy(data->value.arrayBuffer.address + offset, sourceData->value.arrayBuffer.address + sourceOffset, size);
			else {
				txBoolean contentType = (dispatch->value.typedArray.dispatch->constructorID == _BigInt64Array)
						|| (dispatch->value.typedArray.dispatch->constructorID == _BigUint64Array);
				txBoolean sourceContentType = (sourceDispatch->value.typedArray.dispatch->constructorID == _BigInt64Array)
						|| (sourceDispatch->value.typedArray.dispatch->constructorID == _BigUint64Array);
				if (contentType != sourceContentType)
					mxTypeError("incompatible content type");
				mxPushUndefined();
				while (offset < size) {
					(*sourceDispatch->value.typedArray.dispatch->getter)(the, sourceData, sourceOffset, the->stack, EndianNative);
					(*dispatch->value.typedArray.dispatch->coerce)(the, the->stack);
					(*dispatch->value.typedArray.dispatch->setter)(the, data, offset, the->stack, EndianNative);
					sourceOffset += sourceDelta;
					offset += 1 << shift;
				}
				mxPop();
			}
		}
		else {
			fx_TypedArray_from_object(the, instance, C_NULL, C_NULL);
		}
	}
	else {
        txInteger length = fxArgToByteLength(the, 0, 0);
        if (length & (((1 << shift) - 1) << (32 - shift)))
			mxRangeError("out of range byteLength");
        length <<= shift;
		mxPush(mxArrayBufferConstructor);
		mxNew();
		mxPushInteger(length);
		mxRunCount(1);
		mxPullSlot(buffer);
        view->value.dataView.offset = 0;
        view->value.dataView.size = length;
	}
}