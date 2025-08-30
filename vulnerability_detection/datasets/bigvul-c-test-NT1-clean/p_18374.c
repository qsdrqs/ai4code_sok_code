menu_add_separator_image(WebKitWebView *buffer1, GArray *buffer2, GString *buffer3) {
    (void) buffer1;
    (void) buffer3;

    add_separator_to_menu(buffer2, WEBKIT_HIT_TEST_RESULT_CONTEXT_IMAGE);
}
