void TestRenderFrame::vulnerable_func(
    const std::string& html) {
  next_navigation_html_override_ = html;
}
