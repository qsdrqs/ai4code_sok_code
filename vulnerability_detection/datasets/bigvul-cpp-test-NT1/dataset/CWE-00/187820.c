status_t BnOMX::onTransact(
 uint32_t code, const Parcel &data, Parcel *buffer1, uint32_t flags) {
 switch (code) {
 case LIVES_LOCALLY:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);
            node_id node = (node_id)data.readInt32();
 pid_t pid = (pid_t)data.readInt32();
            buffer1->writeInt32(livesLocally(node, pid));

 return OK;
 }

 case LIST_NODES:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

 List<ComponentInfo> list;
            listNodes(&list);

            buffer1->writeInt32(list.size());
 for (List<ComponentInfo>::iterator it = list.begin();
                 it != list.end(); ++it) {
 ComponentInfo &cur = *it;

                buffer1->writeString8(cur.mName);
                buffer1->writeInt32(cur.mRoles.size());
 for (List<String8>::iterator role_it = cur.mRoles.begin();
                     role_it != cur.mRoles.end(); ++role_it) {
                    buffer1->writeString8(*role_it);
 }
 }

 return NO_ERROR;
 }

 case ALLOCATE_NODE:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

 const char *buffer2 = data.readCString();

            sp<IOMXObserver> observer =
                interface_cast<IOMXObserver>(data.readStrongBinder());

            node_id node;

 status_t err = allocateNode(name, observer, &node);
            buffer1->writeInt32(err);
 if (err == OK) {
                buffer1->writeInt32((int32_t)node);
 }

 return NO_ERROR;
 }

 case FREE_NODE:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();

            buffer1->writeInt32(freeNode(node));

 return NO_ERROR;
 }

 case SEND_COMMAND:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();

            OMX_COMMANDTYPE cmd =
 static_cast<OMX_COMMANDTYPE>(data.readInt32());

            OMX_S32 param = data.readInt32();
            buffer1->writeInt32(sendCommand(node, cmd, param));

 return NO_ERROR;
 }

 case GET_PARAMETER:
 case SET_PARAMETER:
 case GET_CONFIG:
 case SET_CONFIG:
 case SET_INTERNAL_OPTION:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_INDEXTYPE index = static_cast<OMX_INDEXTYPE>(data.readInt32());

 size_t size = data.readInt64();

 status_t err = NOT_ENOUGH_DATA;

             void *buffer3 = NULL;
             size_t pageSize = 0;
             size_t allocSize = 0;
            if (code != SET_INTERNAL_OPTION && size < 8) {
                 ALOGE("b/27207275 (%zu)", size);
                android_errorWriteLog(0x534e4554, "27207275");
 } else {
                err = NO_MEMORY;
                pageSize = (size_t) sysconf(_SC_PAGE_SIZE);
 if (size > SIZE_MAX - (pageSize * 2)) {
                    ALOGE("requested param size too big");
 } else {
                    allocSize = (size + pageSize * 2) & ~(pageSize - 1);
                    buffer3 = mmap(NULL, allocSize, PROT_READ | PROT_WRITE,
                            MAP_PRIVATE | MAP_ANONYMOUS, -1 /* fd */, 0 /* offset */);
 }
 if (buffer3 != MAP_FAILED) {
                    err = data.read(buffer3, size);
 if (err != OK) {
                        android_errorWriteLog(0x534e4554, "26914474");

                     } else {
                         err = NOT_ENOUGH_DATA;
                         OMX_U32 declaredSize = *(OMX_U32*)params;
                        if (code != SET_INTERNAL_OPTION && declaredSize > size) {
                             ALOGE("b/27207275 (%u/%zu)", declaredSize, size);
                             android_errorWriteLog(0x534e4554, "27207275");
 } else {
                            mprotect((char*)buffer3 + allocSize - pageSize, pageSize, PROT_NONE);
 switch (code) {
 case GET_PARAMETER:
                                    err = getParameter(node, index, buffer3, size);
 break;
 case SET_PARAMETER:
                                    err = setParameter(node, index, buffer3, size);
 break;
 case GET_CONFIG:
                                    err = getConfig(node, index, buffer3, size);
 break;
 case SET_CONFIG:
                                    err = setConfig(node, index, buffer3, size);
 break;
 case SET_INTERNAL_OPTION:
 {
 InternalOptionType type =
 (InternalOptionType)data.readInt32();

                                    err = setInternalOption(node, index, type, buffer3, size);
 break;
 }

 default:
                                    TRESPASS();
 }
 }
 }
 } else {
                    ALOGE("couldn't map: %s", strerror(errno));
 }
 }

            buffer1->writeInt32(err);

 if ((code == GET_PARAMETER || code == GET_CONFIG) && err == OK) {
                buffer1->write(buffer3, size);
 }

 if (buffer3) {
                munmap(buffer3, allocSize);
 }
            buffer3 = NULL;

 return NO_ERROR;
 }

 case GET_STATE:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_STATETYPE state = OMX_StateInvalid;

 status_t err = getState(node, &state);
            buffer1->writeInt32(state);
            buffer1->writeInt32(err);

 return NO_ERROR;
 }

 case ENABLE_GRAPHIC_BUFFERS:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            OMX_BOOL enable = (OMX_BOOL)data.readInt32();

 status_t err = enableGraphicBuffers(node, port_index, enable);
            buffer1->writeInt32(err);

 return NO_ERROR;
 }

 case GET_GRAPHIC_BUFFER_USAGE:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();

            OMX_U32 usage = 0;
 status_t err = getGraphicBufferUsage(node, port_index, &usage);
            buffer1->writeInt32(err);
            buffer1->writeInt32(usage);

 return NO_ERROR;
 }

 case USE_BUFFER:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            sp<IMemory> params =
                interface_cast<IMemory>(data.readStrongBinder());
            OMX_U32 allottedSize = data.readInt32();

            buffer_id buffer;
 status_t err = useBuffer(node, port_index, params, &buffer, allottedSize);
            buffer1->writeInt32(err);

 if (err == OK) {
                buffer1->writeInt32((int32_t)buffer);
 }

 return NO_ERROR;
 }

 case USE_GRAPHIC_BUFFER:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            sp<GraphicBuffer> graphicBuffer = new GraphicBuffer();
            data.read(*graphicBuffer);

            buffer_id buffer;
 status_t err = useGraphicBuffer(
                    node, port_index, graphicBuffer, &buffer);
            buffer1->writeInt32(err);

 if (err == OK) {
                buffer1->writeInt32((int32_t)buffer);
 }

 return NO_ERROR;
 }

 case UPDATE_GRAPHIC_BUFFER_IN_META:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            sp<GraphicBuffer> graphicBuffer = new GraphicBuffer();
            data.read(*graphicBuffer);
            buffer_id buffer = (buffer_id)data.readInt32();

 status_t err = updateGraphicBufferInMeta(
                    node, port_index, graphicBuffer, buffer);
            buffer1->writeInt32(err);

 return NO_ERROR;
 }

 case CREATE_INPUT_SURFACE:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();

            sp<IGraphicBufferProducer> bufferProducer;
 MetadataBufferType type = kMetadataBufferTypeInvalid;
 status_t err = createInputSurface(node, port_index, &bufferProducer, &type);

 if ((err != OK) && (type == kMetadataBufferTypeInvalid)) {
                android_errorWriteLog(0x534e4554, "26324358");
 }

            buffer1->writeInt32(type);
            buffer1->writeInt32(err);

 if (err == OK) {
                buffer1->writeStrongBinder(IInterface::asBinder(bufferProducer));
 }

 return NO_ERROR;
 }

 case CREATE_PERSISTENT_INPUT_SURFACE:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            sp<IGraphicBufferProducer> bufferProducer;
            sp<IGraphicBufferConsumer> bufferConsumer;
 status_t err = createPersistentInputSurface(
 &bufferProducer, &bufferConsumer);

            buffer1->writeInt32(err);

 if (err == OK) {
                buffer1->writeStrongBinder(IInterface::asBinder(bufferProducer));
                buffer1->writeStrongBinder(IInterface::asBinder(bufferConsumer));
 }

 return NO_ERROR;
 }

 case SET_INPUT_SURFACE:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();

            sp<IGraphicBufferConsumer> bufferConsumer =
                    interface_cast<IGraphicBufferConsumer>(data.readStrongBinder());

 MetadataBufferType type = kMetadataBufferTypeInvalid;
 status_t err = setInputSurface(node, port_index, bufferConsumer, &type);

 if ((err != OK) && (type == kMetadataBufferTypeInvalid)) {
                android_errorWriteLog(0x534e4554, "26324358");
 }

            buffer1->writeInt32(type);
            buffer1->writeInt32(err);
 return NO_ERROR;
 }

 case SIGNAL_END_OF_INPUT_STREAM:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();

 status_t err = signalEndOfInputStream(node);
            buffer1->writeInt32(err);

 return NO_ERROR;
 }

 case STORE_META_DATA_IN_BUFFERS:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            OMX_BOOL enable = (OMX_BOOL)data.readInt32();

 MetadataBufferType type = kMetadataBufferTypeInvalid;
 status_t err = storeMetaDataInBuffers(node, port_index, enable, &type);

            buffer1->writeInt32(type);
            buffer1->writeInt32(err);

 return NO_ERROR;
 }

 case PREPARE_FOR_ADAPTIVE_PLAYBACK:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            OMX_BOOL enable = (OMX_BOOL)data.readInt32();
            OMX_U32 max_width = data.readInt32();
            OMX_U32 max_height = data.readInt32();

 status_t err = prepareForAdaptivePlayback(
                    node, port_index, enable, max_width, max_height);
            buffer1->writeInt32(err);

 return NO_ERROR;
 }

 case CONFIGURE_VIDEO_TUNNEL_MODE:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            OMX_BOOL tunneled = (OMX_BOOL)data.readInt32();
            OMX_U32 audio_hw_sync = data.readInt32();

 native_handle_t *buffer4 = NULL;
 status_t err = configureVideoTunnelMode(
                    node, port_index, tunneled, audio_hw_sync, &sideband_handle);
            buffer1->writeInt32(err);
 if(err == OK){
                buffer1->writeNativeHandle(buffer4);
 }

 return NO_ERROR;
 }

 case ALLOC_BUFFER:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
 if (!isSecure(node) || port_index != 0 /* kPortIndexInput */) {
                ALOGE("b/24310423");
                buffer1->writeInt32(INVALID_OPERATION);
 return NO_ERROR;
 }

 size_t size = data.readInt64();

            buffer_id buffer;
 void *buffer5;
 status_t err = allocateBuffer(
                    node, port_index, size, &buffer, &buffer_data);
            buffer1->writeInt32(err);

 if (err == OK) {
                buffer1->writeInt32((int32_t)buffer);
                buffer1->writeInt64((uintptr_t)buffer5);
 }

 return NO_ERROR;
 }

 case ALLOC_BUFFER_WITH_BACKUP:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            sp<IMemory> params =
                interface_cast<IMemory>(data.readStrongBinder());
            OMX_U32 allottedSize = data.readInt32();

            buffer_id buffer;
 status_t err = allocateBufferWithBackup(
                    node, port_index, params, &buffer, allottedSize);

            buffer1->writeInt32(err);

 if (err == OK) {
                buffer1->writeInt32((int32_t)buffer);
 }

 return NO_ERROR;
 }

 case FREE_BUFFER:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            OMX_U32 port_index = data.readInt32();
            buffer_id buffer = (buffer_id)data.readInt32();
            buffer1->writeInt32(freeBuffer(node, port_index, buffer));

 return NO_ERROR;
 }

 case FILL_BUFFER:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            buffer_id buffer = (buffer_id)data.readInt32();
 bool haveFence = data.readInt32();
 int fenceFd = haveFence ? ::dup(data.readFileDescriptor()) : -1;
            buffer1->writeInt32(fillBuffer(node, buffer, fenceFd));

 return NO_ERROR;
 }

 case EMPTY_BUFFER:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
            buffer_id buffer = (buffer_id)data.readInt32();
            OMX_U32 range_offset = data.readInt32();
            OMX_U32 range_length = data.readInt32();
            OMX_U32 flags = data.readInt32();
            OMX_TICKS timestamp = data.readInt64();
 bool haveFence = data.readInt32();
 int fenceFd = haveFence ? ::dup(data.readFileDescriptor()) : -1;
            buffer1->writeInt32(emptyBuffer(
                    node, buffer, range_offset, range_length, flags, timestamp, fenceFd));

 return NO_ERROR;
 }

 case GET_EXTENSION_INDEX:
 {
            CHECK_OMX_INTERFACE(IOMX, data, buffer1);

            node_id node = (node_id)data.readInt32();
 const char *buffer6 = data.readCString();

            OMX_INDEXTYPE index;
 status_t err = getExtensionIndex(node, parameter_name, &index);

            buffer1->writeInt32(err);

 if (err == OK) {
                buffer1->writeInt32(index);
 }

 return OK;
 }

 default:
 return BBinder::onTransact(code, data, buffer1, flags);
 }
}
