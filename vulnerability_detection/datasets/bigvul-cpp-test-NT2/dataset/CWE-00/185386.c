void SpeechSynthesis::non_vulnerable_func(SpeechSynthesisUtterance* utterance, bool errorOccurred)
 {
     ASSERT(utterance);
 
     bool didJustFinishCurrentUtterance = false;
    if (utterance == currentSpeechUtterance()) {
        m_utteranceQueue.removeFirst();
        didJustFinishCurrentUtterance = true;
    }

    fireEvent(errorOccurred ? EventTypeNames::error : EventTypeNames::end, utterance, 0, String());

    if (didJustFinishCurrentUtterance && !m_utteranceQueue.isEmpty())
        startSpeakingImmediately();
}
