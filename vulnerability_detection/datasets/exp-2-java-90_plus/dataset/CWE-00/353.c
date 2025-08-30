private void registerMessageListener() {
		// do not register multiple packet listeners
		if (mPacketListener != null)
			mXMPPConnection.removePacketListener(mPacketListener);

		PacketTypeFilter filter = new PacketTypeFilter(Message.class);

		mPacketListener = new PacketListener() {
			public void processPacket(Packet packet) {
				try {
				if (packet instanceof Message) {
					Message msg = (Message) packet;

					String[] fromJID = getJabberID(msg.getFrom());
					
					int direction = ChatConstants.INCOMING;
					Carbon cc = CarbonManager.getCarbon(msg);

					// extract timestamp
					long ts;
					DelayInfo timestamp = (DelayInfo)msg.getExtension("delay", "urn:xmpp:delay");
					if (timestamp == null)
						timestamp = (DelayInfo)msg.getExtension("x", "jabber:x:delay");
					if (cc != null) // Carbon timestamp overrides packet timestamp
						timestamp = cc.getForwarded().getDelayInfo();
					if (timestamp != null)
						ts = timestamp.getStamp().getTime();
					else
						ts = System.currentTimeMillis();

					// try to extract a carbon
					if (cc != null) {
						Log.d(TAG, "carbon: " + cc.toXML());
						msg = (Message)cc.getForwarded().getForwardedPacket();

						// outgoing carbon: fromJID is actually chat peer's JID
						if (cc.getDirection() == Carbon.Direction.sent) {
							fromJID = getJabberID(msg.getTo());
							direction = ChatConstants.OUTGOING;
						} else {
							fromJID = getJabberID(msg.getFrom());

							// hook off carbonated delivery receipts
							DeliveryReceipt dr = (DeliveryReceipt)msg.getExtension(
									DeliveryReceipt.ELEMENT, DeliveryReceipt.NAMESPACE);
							if (dr != null) {
								Log.d(TAG, "got CC'ed delivery receipt for " + dr.getId());
								changeMessageDeliveryStatus(dr.getId(), ChatConstants.DS_ACKED);
							}
						}

						// ignore carbon copies of OTR messages sent by broken clients
						if (msg.getBody() != null && msg.getBody().startsWith("?OTR")) {
							Log.i(TAG, "Ignoring OTR carbon from " + msg.getFrom() + " to " + msg.getTo());
							return;
						}
					}

					// check for jabber MUC invitation
					if(direction == ChatConstants.INCOMING && handleMucInvitation(msg)) {
						sendReceiptIfRequested(packet);
						return;
					}

					String chatMessage = msg.getBody();

					// display error inline
					if (msg.getType() == Message.Type.error) {
						if (changeMessageDeliveryStatus(msg.getPacketID(), ChatConstants.DS_FAILED))
							mServiceCallBack.notifyMessage(fromJID, msg.getError().toString(), (cc != null), Message.Type.error);
						else if (mucJIDs.contains(msg.getFrom())) {
							handleKickedFromMUC(msg.getFrom(), false, null,
									msg.getError().toString());
						}
						return; // we do not want to add errors as "incoming messages"
					}

					// ignore empty messages
					if (chatMessage == null) {
						if (msg.getSubject() != null && msg.getType() == Message.Type.groupchat
								&& mucJIDs.contains(fromJID[0])) {
							// this is a MUC subject, update our DB
							ContentValues cvR = new ContentValues();
							cvR.put(RosterProvider.RosterConstants.STATUS_MESSAGE, msg.getSubject());
							cvR.put(RosterProvider.RosterConstants.STATUS_MODE, StatusMode.available.ordinal());
							Log.d(TAG, "MUC subject for " + fromJID[0] + " set to: " + msg.getSubject());
							upsertRoster(cvR, fromJID[0]);
							return;
						}
						Log.d(TAG, "empty message.");
						return;
					}

					// obtain Last Message Correction, if present
					Replace replace = (Replace)msg.getExtension(Replace.NAMESPACE);
					String replace_id = (replace != null) ? replace.getId() : null;

					// carbons are old. all others are new
					int is_new = (cc == null) ? ChatConstants.DS_NEW : ChatConstants.DS_SENT_OR_READ;
					if (msg.getType() == Message.Type.error)
						is_new = ChatConstants.DS_FAILED;

					boolean is_muc = (msg.getType() == Message.Type.groupchat);
					boolean is_from_me = (direction == ChatConstants.OUTGOING) ||
						(is_muc && fromJID[1].equals(getMyMucNick(fromJID[0])));

					// handle MUC-PMs: messages from a nick from a known MUC or with
					// an <x> element
					MUCUser muc_x = (MUCUser)msg.getExtension("x", "http://jabber.org/protocol/muc#user");
					boolean is_muc_pm = !is_muc  && !TextUtils.isEmpty(fromJID[1]) &&
							(muc_x != null || mucJIDs.contains(fromJID[0]));

					// TODO: ignoring 'received' MUC-PM carbons, until XSF sorts out shit:
					// - if yaxim is in the MUC, it will receive a non-carbonated copy of
					//   incoming messages, but not of outgoing ones
					// - if yaxim isn't in the MUC, it can't respond anyway
					if (is_muc_pm && !is_from_me && cc != null)
						return;

					if (is_muc_pm) {
						// store MUC-PMs under the participant's full JID, not bare
						//is_from_me = fromJID[1].equals(getMyMucNick(fromJID[0]));
						fromJID[0] = fromJID[0] + "/" + fromJID[1];
						fromJID[1] = null;
						Log.d(TAG, "MUC-PM: " + fromJID[0] + " d=" + direction + " fromme=" + is_from_me);
					}

					// Carbons and MUC history are 'silent' by default
					boolean is_silent = (cc != null) || (is_muc && timestamp != null);

					if (!is_muc || checkAddMucMessage(msg, msg.getPacketID(), fromJID, timestamp)) {
						addChatMessageToDB(direction, fromJID, chatMessage, is_new, ts, msg.getPacketID(), replace_id);
						// only notify on private messages or when MUC notification requested
						boolean need_notify = !is_muc || mConfig.needMucNotification(getMyMucNick(fromJID[0]), chatMessage);
						// outgoing carbon -> clear notification by signalling 'null' message
						if (is_from_me) {
							mServiceCallBack.notifyMessage(fromJID, null, true, msg.getType());
							// TODO: MUC PMs
							ChatHelper.markAsRead(mService, fromJID[0]);
						} else if (direction == ChatConstants.INCOMING && need_notify)
							mServiceCallBack.notifyMessage(fromJID, chatMessage, is_silent, msg.getType());
					}
					sendReceiptIfRequested(packet);
				}
				} catch (Exception e) {
					// SMACK silently discards exceptions dropped from processPacket :(
					Log.e(TAG, "failed to process packet:");
					e.printStackTrace();
				}
			}
		};

		mXMPPConnection.addPacketListener(mPacketListener, filter);
	}