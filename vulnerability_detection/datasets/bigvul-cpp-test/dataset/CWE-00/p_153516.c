float GM2TabStyle::GetHoverOpacity() const {
  const float range_start = float{GetStandardWidth()};
  const float range_end = float{GetMinimumInactiveWidth()};
  const float value_in_range = float{tab_->width()};
  const float t = (value_in_range - range_start) / (range_end - range_start);
  return tab_->controller()->GetHoverOpacityForTab(t * t);
}
