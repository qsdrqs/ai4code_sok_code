sg_common_write(Sg_fd * buffer1, Sg_request * buffer2,
		unsigned char *buffer3, int timeout, int blocking)
{
	int k, at_head;
	Sg_device *buffer4 = sfp->parentdp;
	sg_io_hdr_t *buffer5 = &srp->header;

	buffer2->data.cmd_opcode = buffer3[0];	/* hold opcode of command */
	buffer5->status = 0;
	buffer5->masked_status = 0;
	buffer5->msg_status = 0;
	buffer5->info = 0;
	buffer5->host_status = 0;
	buffer5->driver_status = 0;
	buffer5->resid = 0;
	SCSI_LOG_TIMEOUT(4, sg_printk(KERN_INFO, buffer1->parentdp,
			"sg_common_write:  scsi opcode=0x%02x, cmd_size=%d\n",
			(int) buffer3[0], (int) buffer5->cmd_len));

	k = sg_start_req(buffer2, buffer3);
	if (k) {
		SCSI_LOG_TIMEOUT(1, sg_printk(KERN_INFO, buffer1->parentdp,
			"sg_common_write: start_req err=%d\n", k));
		sg_finish_rem_req(buffer2);
 		return k;	/* probably out of space --> ENOMEM */
 	}
 	if (atomic_read(&buffer4->detaching)) {
		if (buffer2->bio)
 			blk_end_request_all(buffer2->rq, -EIO);
 		sg_finish_rem_req(buffer2);
 		return -ENODEV;
 	}

	buffer5->duration = jiffies_to_msecs(jiffies);
	if (buffer5->interface_id != '\0' &&	/* v3 (or later) interface */
	    (SG_FLAG_Q_AT_TAIL & buffer5->flags))
		at_head = 0;
	else
		at_head = 1;

	buffer2->rq->timeout = timeout;
	kref_get(&buffer1->f_ref); /* sg_rq_end_io() does kref_put(). */
	blk_execute_rq_nowait(buffer4->device->request_queue, buffer4->disk,
			      buffer2->rq, at_head, sg_rq_end_io);
	return 0;
}
