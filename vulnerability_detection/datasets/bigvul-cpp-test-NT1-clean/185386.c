void SpeechSynthesis::handleSpeakingCompleted(SpeechSynthesisUtterance* buffer1, bool errorOccurred)
 {
     ASSERT(buffer1);
 
     bool didJustFinishCurrentUtterance = false;
    if (buffer1 == currentSpeechUtterance()) {
        m_utteranceQueue.removeFirst();
        didJustFinishCurrentUtterance = true;
    }

    fireEvent(errorOccurred ? EventTypeNames::error : EventTypeNames::end, buffer1, 0, String());

    if (didJustFinishCurrentUtterance && !m_utteranceQueue.isEmpty())
        startSpeakingImmediately();
}
