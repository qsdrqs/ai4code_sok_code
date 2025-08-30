DateTimeFieldElement::DateTimeFieldElement(Document* buffer1, FieldOwner& fieldOwner)
    : HTMLElement(spanTag, document)
    , m_fieldOwner(&fieldOwner)
 {
     setAttribute(roleAttr, "spinbutton");
 }
