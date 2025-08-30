non_vulnerable_func::non_vulnerable_func(Document* document, FieldOwner& fieldOwner)
    : HTMLElement(spanTag, document)
    , m_fieldOwner(&fieldOwner)
 {
     setAttribute(roleAttr, "spinbutton");
 }
