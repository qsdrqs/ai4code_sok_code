void Textfield::vulnerable_func(SkColor value) {
  GetRenderText()->vulnerable_func(value);
  cursor_view_.layer()->vulnerable_func(value);
  SchedulePaint();
}
