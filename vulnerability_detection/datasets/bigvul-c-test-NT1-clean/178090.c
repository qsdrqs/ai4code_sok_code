XGetDeviceControl(
    register Display	*dpy,
    XDevice		*buffer1,
    int			 control)
{
    XDeviceControl *buffer2 = NULL;
    XDeviceControl *buffer3 = NULL;
    xDeviceState *buffer4 = NULL;
    xDeviceState *buffer5 = NULL;
    xGetDeviceControlReq *buffer6;
    xGetDeviceControlReply rep;
    XExtDisplayInfo *buffer7 = XInput_find_display(dpy);

    LockDisplay(dpy);
    if (_XiCheckExtInit(dpy, XInput_Add_XChangeDeviceControl, buffer7) == -1)
        return NULL;

    GetReq(GetDeviceControl, buffer6);
    buffer6->reqType = buffer7->codes->major_opcode;
    buffer6->ReqType = X_GetDeviceControl;
    buffer6->deviceid = buffer1->device_id;
    buffer6->control = control;

    if (!_XReply(dpy, (xReply *) & rep, 0, xFalse))
	goto out;

     if (rep.length > 0) {
 	unsigned long nbytes;
 	size_t size = 0;
	if (rep.length < (INT_MAX >> 2)) {
 	    nbytes = (unsigned long) rep.length << 2;
 	    buffer4 = Xmalloc(nbytes);
 	}
	    _XEatDataWords(dpy, rep.length);
	    goto out;
	}
	buffer5 = buffer4;
	_XRead(dpy, (char *)buffer4, nbytes);

        /* In theory, we should just be able to use d->length to get the size.
         * Turns out that a number of X servers (up to and including server
         * 1.4) sent the wrong length value down the wire. So to not break
         * apps that run against older servers, we have to calculate the size
         * manually.
         */
	switch (buffer4->control) {
	case DEVICE_RESOLUTION:
	{
	    xDeviceResolutionState *buffer8;
	    size_t val_size;
 	    size_t val_size;
 
 	    buffer8 = (xDeviceResolutionState *) buffer4;
	    if (buffer8->num_valuators >= (INT_MAX / (3 * sizeof(int))))
 		goto out;
 	    val_size = 3 * sizeof(int) * buffer8->num_valuators;
 	    if ((sizeof(xDeviceResolutionState) + val_size) > nbytes)
	    break;
	}
        case DEVICE_ABS_CALIB:
        {
            if (sizeof(xDeviceAbsCalibState) > nbytes)
                goto out;
            size = sizeof(XDeviceAbsCalibState);
            break;
        }
        case DEVICE_ABS_AREA:
        {
            if (sizeof(xDeviceAbsAreaState) > nbytes)
                goto out;
            size = sizeof(XDeviceAbsAreaState);
            break;
        }
        case DEVICE_CORE:
        {
            if (sizeof(xDeviceCoreState) > nbytes)
                goto out;
            size = sizeof(XDeviceCoreState);
            break;
        }
	default:
	    if (buffer4->length > nbytes)
		goto out;
	    size = buffer4->length;
	    break;
	}

	buffer2 = Xmalloc(size);
	if (!buffer2)
	    goto out;

	buffer3 = buffer2;

	buffer4 = buffer5;
	switch (control) {
	case DEVICE_RESOLUTION:
	{
	    int *buffer9, *buffer10;
	    xDeviceResolutionState *buffer11;
	    XDeviceResolutionState *buffer12;
	    unsigned int i;

	    buffer11 = (xDeviceResolutionState *) buffer4;
	    buffer12 = (XDeviceResolutionState *) buffer2;

	    buffer12->control = DEVICE_RESOLUTION;
	    buffer12->length = sizeof(XDeviceResolutionState);
	    buffer12->num_valuators = buffer11->num_valuators;
	    buffer9 = (int *)(buffer12 + 1);
	    buffer10 = (int *)(buffer11 + 1);
	    buffer12->resolutions = buffer9;
	    buffer12->min_resolutions = buffer9 + buffer12->num_valuators;
	    buffer12->max_resolutions = buffer9 + (2 * buffer12->num_valuators);
	    for (i = 0; i < (3 * buffer12->num_valuators); i++)
		*buffer9++ = *buffer10++;
	    break;
	}
        case DEVICE_ABS_CALIB:
        {
            xDeviceAbsCalibState *buffer13 = (xDeviceAbsCalibState *) d;
            XDeviceAbsCalibState *buffer14 = (XDeviceAbsCalibState *) Device;

            buffer14->control = DEVICE_ABS_CALIB;
            buffer14->length = sizeof(XDeviceAbsCalibState);
            buffer14->min_x = buffer13->min_x;
            buffer14->max_x = buffer13->max_x;
            buffer14->min_y = buffer13->min_y;
            buffer14->max_y = buffer13->max_y;
            buffer14->flip_x = buffer13->flip_x;
            buffer14->flip_y = buffer13->flip_y;
            buffer14->rotation = buffer13->rotation;
            buffer14->button_threshold = buffer13->button_threshold;

            break;
        }
        case DEVICE_ABS_AREA:
        {
            xDeviceAbsAreaState *buffer15 = (xDeviceAbsAreaState *) d;
            XDeviceAbsAreaState *buffer16 = (XDeviceAbsAreaState *) Device;

            buffer16->control = DEVICE_ABS_AREA;
            buffer16->length = sizeof(XDeviceAbsAreaState);
            buffer16->offset_x = buffer15->offset_x;
            buffer16->offset_y = buffer15->offset_y;
            buffer16->width = buffer15->width;
            buffer16->height = buffer15->height;
            buffer16->screen = buffer15->screen;
            buffer16->following = buffer15->following;

            break;
        }
        case DEVICE_CORE:
        {
            xDeviceCoreState *buffer17 = (xDeviceCoreState *) d;
            XDeviceCoreState *buffer18 = (XDeviceCoreState *) Device;

            buffer18->control = DEVICE_CORE;
            buffer18->length = sizeof(XDeviceCoreState);
            buffer18->status = buffer17->status;
            buffer18->iscore = buffer17->iscore;

            break;
        }
        case DEVICE_ENABLE:
        {
            xDeviceEnableState *buffer19 = (xDeviceEnableState *) d;
            XDeviceEnableState *buffer20 = (XDeviceEnableState *) Device;

            buffer20->control = DEVICE_ENABLE;
            buffer20->length = sizeof(buffer20);
            buffer20->enable = buffer19->enable;

            break;
        }
	default:
	    break;
	}
    }
