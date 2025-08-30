private void sanitizeString(int start, int end) {
    boolean closed = false;
    for (int i = start; i < end; ++i) {
      char ch = jsonish.charAt(i);
      switch (ch) {
        // Fixup newlines.
        case '\n': replace(i, i + 1, "\\n"); break;
        case '\r': replace(i, i + 1, "\\r"); break;
        // Not newlines in JSON but unparseable by JS eval.
        case '\u2028': replace(i, i + 1, "\\u2028"); break;
        case '\u2029': replace(i, i + 1, "\\u2029"); break;
        // String delimiting quotes that need to be converted : 'foo' -> "foo"
        // or internal quotes that might need to be escaped : f"o -> f\"o.
        case '"': case '\'':
          if (i == start) {
            if (ch == '\'') { replace(i, i + 1, '"'); }
          } else {
            if (i + 1 == end) {
              char startDelim = jsonish.charAt(start);
              if (startDelim != '\'') {
                // If we're sanitizing a string whose start was inferred, then
                // treat '"' as closing regardless.
                startDelim = '"';
              }
              closed = startDelim == ch;
            }
            if (closed) {
              if (ch == '\'') { replace(i, i + 1, '"'); }
            } else if (ch == '"') {
              insert(i, '\\');
            }
          }
          break;
        // Embedding.  Disallow </script and ]]> in string literals so that
        // the output can be embedded in HTML script elements and in XML CDATA
        // sections.
        case '/':
          // Don't over escape.  Many JSON bodies contain innocuous HTML
          // that can be safely embedded.
          if (i > start && i + 2 < end && '<' == jsonish.charAt(i - 1)
              && 's' == (jsonish.charAt(i + 1) | 32)
              && 'c' == (jsonish.charAt(i + 2) | 32)) {
            insert(i, '\\');
          }
          break;
        case ']':
          if (i + 2 < end && ']' == jsonish.charAt(i + 1)
              && '>' == jsonish.charAt(i + 2)) {
            replace(i, i + 1, "\\u005d");
          }
          break;
        // Normalize escape sequences.
        case '\\':
          if (i + 1 == end) {
            elide(i, i + 1);
            break;
          }
          char sch = jsonish.charAt(i + 1);
          switch (sch) {
            case 'b': case 'f': case 'n': case 'r': case 't': case '\\':
            case '/': case '"':
              ++i;
              break;
            case 'v':  // Recognized by JS but not by JSON.
              replace(i, i + 2, "\\u0008");
              ++i;
              break;
            case 'x':
              if (i + 4 < end && isHexAt(i+2) && isHexAt(i+3)) {
                replace(i, i + 2, "\\u00");  // \xab -> \u00ab
                i += 3;
                break;
              }
              elide(i, i + 1);
              break;
            case 'u':
              if (i + 6 < end && isHexAt(i + 2) && isHexAt(i + 3)
                  && isHexAt(i + 4) && isHexAt(i + 5)) {
                i += 5;
                break;
              }
              elide(i, i + 1);
              break;
            case '0': case '1': case '2': case '3':
            case '4': case '5': case '6': case '7':
              int octalEnd = i + 1;
              if (octalEnd + 1 < end && isOctAt(octalEnd + 1)) {
                ++octalEnd;
                if (ch <= '3' && octalEnd + 1 < end && isOctAt(octalEnd + 1)) {
                  ++octalEnd;
                }
                int value = 0;
                for (int j = i; j < octalEnd; ++j) {
                  value = (value << 3) | (jsonish.charAt(j) - '0');
                }
                replace(i + 1, octalEnd, "u00");
                appendHex(value, 2);
              }
              i = octalEnd - 1;
              break;
            default:
              // Literal char that is recognized by JS but not by JSON.
              // "\-" is valid JS but not valid JSON.
              elide(i, i + 1);
              break;
          }
          break;
        default:
          // Escape all control code-points and isolated surrogates which are
          // not embeddable in XML.
          // http://www.w3.org/TR/xml/#charsets says
          //     Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD]
          //            | [#x10000-#x10FFFF]
          if (ch < 0x20) {
            if (ch == 9 || ch == 0xa || ch == 0xd) { continue; }
          } else if (ch < 0xd800) {  // Not a surrogate.
            continue;
          } else if (ch < 0xe000) {  // A surrogate
            if (Character.isHighSurrogate(ch) && i+1 < end
                && Character.isLowSurrogate(jsonish.charAt(i+1))) {
              ++i;  // Skip over low surrogate since we have already vetted it.
              continue;
            }
          } else if (ch <= 0xfffd) {  // Not one of the 0xff.. controls.
            continue;
          }
          replace(i, i + 1, "\\u");
          for (int j = 4; --j >= 0;) {
            sanitizedJson.append(HEX_DIGITS[(ch >>> (j << 2)) & 0xf]);
          }
          break;
      }
    }
    if (!closed) { insert(end, '"'); }
  }