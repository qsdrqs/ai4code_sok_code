bool
ConnectionBase::preparseNewBytes(int bytesRead)
{
   DebugLog(<< "In State: " << connectionStates[mConnState]);
   
  start:   // If there is an overhang come back here, effectively recursing
   
   switch(mConnState)
   {
      case NewMessage:
      {
         if (strncmp(mBuffer + mBufferPos, Symbols::CRLFCRLF, 4) == 0)
         {
            DebugLog(<< "Got incoming double-CRLF keepalive (aka ping).");
            mBufferPos += 4;
            bytesRead -= 4;
            onDoubleCRLF();
            if (bytesRead)
            {
               goto start;
            }
            else
            {
               delete [] mBuffer;
               mBuffer = 0;
               return true;
            }
         }
         else if (strncmp(mBuffer + mBufferPos, Symbols::CRLF, 2) == 0)
         {
            //DebugLog(<< "Got incoming CRLF keepalive response (aka pong).");
            mBufferPos += 2;
            bytesRead -= 2;
            onSingleCRLF();
            if (bytesRead)
            {
               goto start;
            }
            else
            {
               delete [] mBuffer;
               mBuffer = 0;
               return true;
            }
         }

         resip_assert(mTransport);
         mMessage = new SipMessage(&mTransport->getTuple());
         
         DebugLog(<< "ConnectionBase::process setting source " << mWho);
         mMessage->setSource(mWho);
         mMessage->setTlsDomain(mTransport->tlsDomain());

#ifdef USE_SSL
         // Set TlsPeerName if message is from TlsConnection
         TlsConnection *tlsConnection = dynamic_cast<TlsConnection *>(this);
         if(tlsConnection)
         {
            std::list<Data> peerNameList;
            tlsConnection->getPeerNames(peerNameList);
            mMessage->setTlsPeerNames(peerNameList);
         }
#endif
         mMsgHeaderScanner.prepareForMessage(mMessage);
         // Fall through to the next case.
      }
      case ReadingHeaders:
      {
         unsigned int chunkLength = (unsigned int)mBufferPos + bytesRead;
         char *unprocessedCharPtr;
         MsgHeaderScanner::ScanChunkResult scanChunkResult =
            mMsgHeaderScanner.scanChunk(mBuffer,
                                        chunkLength,
                                        &unprocessedCharPtr);
         if (scanChunkResult == MsgHeaderScanner::scrError)
         {
            //.jacob. Not a terribly informative warning.
            WarningLog(<< "Discarding preparse!");
            delete [] mBuffer;
            mBuffer = 0;
            delete mMessage;
            mMessage = 0;
            mConnState = NewMessage;
            return false;
         }

         if (mMsgHeaderScanner.getHeaderCount() > 1024)
         {
            WarningLog(<< "Discarding preparse; too many headers");
            delete [] mBuffer;
            mBuffer = 0;
            delete mMessage;
            mMessage = 0;
            mConnState = NewMessage;
            return false;
         }

         unsigned int numUnprocessedChars = 
            (unsigned int)((mBuffer + chunkLength) - unprocessedCharPtr);

         if(numUnprocessedChars > ConnectionBase::ChunkSize &&
            scanChunkResult == MsgHeaderScanner::scrNextChunk)
         {
            WarningLog(<< "Discarding preparse; header-field-value (or "
                        "header name) too long");
            delete [] mBuffer;
            mBuffer = 0;
            delete mMessage;
            mMessage = 0;
            mConnState = NewMessage;
            return false;
         }

         if(numUnprocessedChars==chunkLength)
         {
            // .bwc. MsgHeaderScanner wasn't able to parse anything useful;
            // don't bother mMessage yet, but make more room in mBuffer.
            size_t size = numUnprocessedChars*3/2;
            if (size < ConnectionBase::ChunkSize)
            {
               size = ConnectionBase::ChunkSize;
            }
            char* newBuffer = 0;
            try
            {
               newBuffer=MsgHeaderScanner::allocateBuffer((int)size);
            }
            catch(std::bad_alloc&)
            {
               ErrLog(<<"Failed to alloc a buffer during preparse!");
               return false;
            }
            memcpy(newBuffer, unprocessedCharPtr, numUnprocessedChars);
            delete [] mBuffer;
            mBuffer = newBuffer;
            mBufferPos = numUnprocessedChars;
            mBufferSize = size;
            mConnState = ReadingHeaders;
            return true;
         }

         mMessage->addBuffer(mBuffer);
         mBuffer=0;

         if (scanChunkResult == MsgHeaderScanner::scrNextChunk)
         {
            // Message header is incomplete...
            if (numUnprocessedChars == 0)
            {
               // ...but the chunk is completely processed.
               //.jacob. I've discarded the "assigned" concept.
               //DebugLog(<< "Data assigned, not fragmented, not complete");
               try
               {
                  mBuffer = MsgHeaderScanner::allocateBuffer(ChunkSize);
               }
               catch(std::bad_alloc&)
               {
                  ErrLog(<<"Failed to alloc a buffer during preparse!");
                  return false;
               }
               mBufferPos = 0;
               mBufferSize = ChunkSize;
            }
            else
            {
               // ...but some of the chunk must be shifted into the next one.
               size_t size = numUnprocessedChars*3/2;
               if (size < ConnectionBase::ChunkSize)
               {
                  size = ConnectionBase::ChunkSize;
               }
               char* newBuffer = 0;
               try
               {
                  newBuffer = MsgHeaderScanner::allocateBuffer((int)size);
               }
               catch(std::bad_alloc&)
               {
                  ErrLog(<<"Failed to alloc a buffer during preparse!");
                  return false;
               }
               memcpy(newBuffer, unprocessedCharPtr, numUnprocessedChars);
               mBuffer = newBuffer;
               mBufferPos = numUnprocessedChars;
               mBufferSize = size;
            }
            mConnState = ReadingHeaders;
         }
         else
         {
            size_t contentLength = 0;
            
            try
            {
               // The message header is complete.
               contentLength=mMessage->const_header(h_ContentLength).value();
            }
            catch(resip::BaseException& e)  // Could be SipMessage::Exception or ParseException
            {
               WarningLog(<<"Malformed Content-Length in connection-based transport"
                           ". Not much we can do to fix this.  " << e);
               // .bwc. Bad Content-Length. We are hosed.
               delete mMessage;
               mMessage = 0;
               mBuffer = 0;
               // .bwc. mMessage just took ownership of mBuffer, so we don't
               // delete it here. We do zero it though, for completeness.
               //.jacob. Shouldn't the state also be set here?
               return false;
            }
            
            if(contentLength > messageSizeMax || contentLength < 0)
            {
               WarningLog(<<"Content-Length in connection-based "
                           "transport exceeds maximum " << messageSizeMax);
               delete mMessage;
               mMessage = 0;
               mBuffer = 0;
               // .bwc. mMessage just took ownership of mBuffer, so we don't
               // delete it here. We do zero it though, for completeness.
               //.jacob. Shouldn't the state also be set here?
               return false;
            }

            if (numUnprocessedChars < contentLength)
            {
               // The message body is incomplete.
               DebugLog(<< "partial body received");
               size_t newSize=resipMin(resipMax((size_t)numUnprocessedChars*3/2,
                                             (size_t)ConnectionBase::ChunkSize),
                                    contentLength);
               char* newBuffer = MsgHeaderScanner::allocateBuffer((int)newSize);
               memcpy(newBuffer, unprocessedCharPtr, numUnprocessedChars);
               mBufferPos = numUnprocessedChars;
               mBufferSize = newSize;
               mBuffer = newBuffer;
               
               mConnState = PartialBody;
            }
            else
            {
               // Do this stuff BEFORE we kick the message out the door.
               // Remember, deleting or passing mMessage on invalidates our
               // buffer!
               int overHang = numUnprocessedChars - (int)contentLength;

               mConnState = NewMessage;
               mBuffer = 0;
               if (overHang > 0) 
               {
                  // The next message has been partially read.
                  size_t size = overHang*3/2;
                  if (size < ConnectionBase::ChunkSize)
                  {
                     size = ConnectionBase::ChunkSize;
                  }
                  char* newBuffer = MsgHeaderScanner::allocateBuffer((int)size);
                  memcpy(newBuffer,
                         unprocessedCharPtr + contentLength,
                         overHang);
                  mBuffer = newBuffer;
                  mBufferPos = 0;
                  mBufferSize = size;
                  
                  DebugLog (<< "Extra bytes after message: " << overHang);
                  DebugLog (<< Data(mBuffer, overHang));
                  
                  bytesRead = overHang;
               }

               // The message body is complete.
               mMessage->setBody(unprocessedCharPtr, (UInt32)contentLength);
               CongestionManager::RejectionBehavior b=mTransport->getRejectionBehaviorForIncoming();
               if (b==CongestionManager::REJECTING_NON_ESSENTIAL
                     || (b==CongestionManager::REJECTING_NEW_WORK
                        && mMessage->isRequest()))
               {
                  UInt32 expectedWait(mTransport->getExpectedWaitForIncoming());
                  // .bwc. If this fifo is REJECTING_NEW_WORK, we will drop
                  // requests but not responses ( ?bwc? is this right for ACK?). 
                  // If we are REJECTING_NON_ESSENTIAL, 
                  // we reject all incoming work, since losing something from the 
                  // wire will not cause instability or leaks (see 
                  // CongestionManager.hxx)
                  
                  // .bwc. This handles all appropriate checking for whether
                  // this is a response or an ACK.
                  std::auto_ptr<SendData> tryLater(transport()->make503(*mMessage, expectedWait/1000));
                  if(tryLater.get())
                  {
                     transport()->send(tryLater);
                  }
                  delete mMessage; // dropping message due to congestion
                  mMessage = 0;
               }
               else if (!transport()->basicCheck(*mMessage))
               {
                  delete mMessage;
                  mMessage = 0;
               }
               else
               {
                  Transport::stampReceived(mMessage);
                  DebugLog(<< "##Connection: " << *this << " received: " << *mMessage);
                  resip_assert( mTransport );
                  mTransport->pushRxMsgUp(mMessage);
                  mMessage = 0;
               }

               if (overHang > 0) 
               {
                  goto start;
               }
            }
         }
         break;
      }
      case PartialBody:
      {
         size_t contentLength = 0;

         try
         {
             contentLength = mMessage->const_header(h_ContentLength).value();
         }
         catch(resip::BaseException& e)  // Could be SipMessage::Exception or ParseException
         {
            WarningLog(<<"Malformed Content-Length in connection-based transport"
                        ". Not much we can do to fix this. " << e);
            // .bwc. Bad Content-Length. We are hosed.
            delete [] mBuffer;
            mBuffer = 0;
            delete mMessage;
            mMessage = 0;
            //.jacob. Shouldn't the state also be set here?
            return false;
         }

         mBufferPos += bytesRead;
         if (mBufferPos == contentLength)
         {
            mMessage->addBuffer(mBuffer);
            mMessage->setBody(mBuffer, (UInt32)contentLength);
            mBuffer=0;
            // .bwc. basicCheck takes up substantial CPU. Don't bother doing it
            // if we're overloaded.
            CongestionManager::RejectionBehavior b=mTransport->getRejectionBehaviorForIncoming();
            if (b==CongestionManager::REJECTING_NON_ESSENTIAL
                  || (b==CongestionManager::REJECTING_NEW_WORK
                     && mMessage->isRequest()))
            {
               UInt32 expectedWait(mTransport->getExpectedWaitForIncoming());
               // .bwc. If this fifo is REJECTING_NEW_WORK, we will drop
               // requests but not responses ( ?bwc? is this right for ACK?). 
               // If we are REJECTING_NON_ESSENTIAL, 
               // we reject all incoming work, since losing something from the 
               // wire will not cause instability or leaks (see 
               // CongestionManager.hxx)
               
               // .bwc. This handles all appropriate checking for whether
               // this is a response or an ACK.
               std::auto_ptr<SendData> tryLater = transport()->make503(*mMessage, expectedWait/1000);
               if(tryLater.get())
               {
                  transport()->send(tryLater);
               }
               delete mMessage; // dropping message due to congestion
               mMessage = 0;
            }
            else if (!transport()->basicCheck(*mMessage))
            {
               delete mMessage;
               mMessage = 0;
            }
            else
            {
               DebugLog(<< "##ConnectionBase: " << *this << " received: " << *mMessage);

               Transport::stampReceived(mMessage);
               resip_assert( mTransport );
               mTransport->pushRxMsgUp(mMessage);
               mMessage = 0;
            }
            mConnState = NewMessage;
         }
         else if (mBufferPos == mBufferSize)
         {
            // .bwc. We've filled our buffer; go ahead and make more room.
            size_t newSize = resipMin(mBufferSize*3/2, contentLength);
            char* newBuffer = 0;
            try
            {
               newBuffer=new char[newSize];
            }
            catch(std::bad_alloc&)
            {
               ErrLog(<<"Failed to alloc a buffer while receiving body!");
               return false;
            }
            memcpy(newBuffer, mBuffer, mBufferSize);
            mBufferSize=newSize;
            delete [] mBuffer;
            mBuffer = newBuffer;
         }
         break;
      }
      default:
         resip_assert(0);
   }
   return true;
}