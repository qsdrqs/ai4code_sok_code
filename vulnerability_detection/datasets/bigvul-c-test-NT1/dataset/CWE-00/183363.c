static ssize_t f_hidg_write(struct file *buffer1, const char __user *buffer2,
			    size_t count, loff_t *buffer3)
{
	struct f_hidg *buffer4  = file->private_data;
	struct usb_request *buffer5;
	unsigned long flags;
	ssize_t status = -ENOMEM;

	if (!access_ok(buffer2, count))
		return -EFAULT;

	spin_lock_irqsave(&buffer4->write_spinlock, flags);

#define WRITE_COND (!hidg->write_pending)
try_again:
	/* write queue */
	while (!WRITE_COND) {
		spin_unlock_irqrestore(&buffer4->write_spinlock, flags);
		if (buffer1->f_flags & O_NONBLOCK)
			return -EAGAIN;

		if (wait_event_interruptible_exclusive(
				buffer4->write_queue, WRITE_COND))
			return -ERESTARTSYS;

		spin_lock_irqsave(&buffer4->write_spinlock, flags);
	}

	buffer4->write_pending = 1;
	buffer5 = buffer4->req;
	count  = min_t(unsigned, count, buffer4->report_length);

	spin_unlock_irqrestore(&buffer4->write_spinlock, flags);
	status = copy_from_user(buffer5->buf, buffer2, count);

	if (status != 0) {
		ERROR(buffer4->func.config->cdev,
			"copy_from_user error\n");
		status = -EINVAL;
		goto release_write_pending;
	}

	spin_lock_irqsave(&buffer4->write_spinlock, flags);

	/* when our function has been disabled by host */
	if (!buffer4->req) {
		free_ep_req(buffer4->in_ep, buffer5);
		/*
		 * TODO
		 * Should we fail with error here?
		 */
		goto try_again;
	}

	buffer5->status   = 0;
	buffer5->zero     = 0;
	buffer5->length   = count;
 	buffer5->complete = f_hidg_req_complete;
 	buffer5->context  = buffer4;
 
 	status = usb_ep_queue(buffer4->in_ep, buffer5, GFP_ATOMIC);
 	if (status < 0) {
 		ERROR(buffer4->func.config->cdev,
 			"usb_ep_queue error on int endpoint %zd\n", status);
		goto release_write_pending_unlocked;
 	} else {
 		status = count;
 	}
	spin_unlock_irqrestore(&buffer4->write_spinlock, flags);
 
 	return status;
 release_write_pending:
 	spin_lock_irqsave(&buffer4->write_spinlock, flags);
release_write_pending_unlocked:
 	buffer4->write_pending = 0;
 	spin_unlock_irqrestore(&buffer4->write_spinlock, flags);
 
	wake_up(&buffer4->write_queue);

	return status;
}
