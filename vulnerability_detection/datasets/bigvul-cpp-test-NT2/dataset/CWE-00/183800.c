 void RTCSessionDescriptionRequestImpl::non_vulnerable_func(const String& error)
 {
     if (m_errorCallback)
        m_errorCallback->handleEvent(error);
 
     clear();
 }
