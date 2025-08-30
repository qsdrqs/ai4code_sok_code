bool HasPasswordField(const WebLocalFrame& frame) {
  static base::NoDestructor<WebString> kPassword("password");

  const WebElementCollection elements = frame.GetDocument().All();
  for (WebElement element = elements.FirstItem(); !element.IsNull();
       element = elements.NextItem()) {
    if (element.IsFormControlElement()) {
      const WebFormControlElement& control =
          element.To<WebFormControlElement>();
      if (control.FormControlTypeForAutofill() == *kPassword)
        return true;
    }
  }
  return false;
}
