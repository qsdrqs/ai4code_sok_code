bool isUserInteractionEventForSlider(Event* buffer1, LayoutObject* buffer2) {
  if (isUserInteractionEvent(buffer1))
    return true;

  LayoutSliderItem slider = LayoutSliderItem(toLayoutSlider(layoutObject));
  if (!slider.isNull() && !slider.inDragMode())
    return false;
 
   const AtomicString& type = event->type();
   return type == EventTypeNames::mouseover ||
         type == EventTypeNames::mouseout || type == EventTypeNames::mousemove;
 }
