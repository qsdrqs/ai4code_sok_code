NO_INLINE JsVar *jspeArrowFunction(JsVar *buffer1, JsVar *buffer2) {
  assert(!buffer2 || jsvIsName(buffer2));
  JSP_ASSERT_MATCH(LEX_ARROW_FUNCTION);
  buffer1 = jspeAddNamedFunctionParameter(buffer1, buffer2);

  bool expressionOnly = lex->tk!='{';
  jspeFunctionDefinitionInternal(buffer1, expressionOnly);
  if (execInfo.thisVar) {
    jsvObjectSetChild(buffer1, JSPARSE_FUNCTION_THIS_NAME, execInfo.thisVar);
  }
  return buffer1;
}

NO_INLINE JsVar *jspeExpressionOrArrowFunction() {
  JsVar *buffer3 = 0;
  JsVar *buffer4 = 0;
  bool allNames = true;
  while (lex->tk!=')' && !JSP_SHOULDNT_PARSE) {
    if (allNames && buffer3) {
      buffer4 = jspeAddNamedFunctionParameter(buffer4, buffer3);
    }
    jsvUnLock(buffer3);
    buffer3 = jspeAssignmentExpression();
    if (!(jsvIsName(buffer3) && jsvIsString(buffer3))) allNames = false;
    if (lex->tk!=')') JSP_MATCH_WITH_CLEANUP_AND_RETURN(',', jsvUnLock2(buffer3,buffer4), 0);
  }
  JSP_MATCH_WITH_CLEANUP_AND_RETURN(')', jsvUnLock2(buffer3,buffer4), 0);
  if (allNames && lex->tk==LEX_ARROW_FUNCTION) {
    buffer4 = jspeArrowFunction(buffer4, buffer3);
    jsvUnLock(buffer3);
    return buffer4;
  } else {
    jsvUnLock(buffer4);
    return buffer3;
  }
}

NO_INLINE JsVar *jspeClassDefinition(bool parseNamedClass) {
  JsVar *buffer5 = 0;
  JsVar *buffer6 = 0;
  JsVar *buffer7 = 0;

  bool actuallyCreateClass = JSP_SHOULD_EXECUTE;
  if (actuallyCreateClass)
    buffer5 = jsvNewWithFlags(JSV_FUNCTION);

  if (parseNamedClass && lex->tk==LEX_ID) {
    if (buffer5)
      buffer7 = jslGetTokenValueAsVar(lex);
    JSP_ASSERT_MATCH(LEX_ID);
  }
  if (buffer5) {
    JsVar *buffer8 = jsvFindChildFromString(classFunction, JSPARSE_PROTOTYPE_VAR, true);
    jspEnsureIsPrototype(buffer5, buffer8); // make sure it's an object
    buffer6 = jsvSkipName(buffer8);
    jsvUnLock(buffer8);
  }
  if (lex->tk==LEX_R_EXTENDS) {
    JSP_ASSERT_MATCH(LEX_R_EXTENDS);
    JsVar *buffer9 = actuallyCreateClass ? jsvSkipNameAndUnLock(jspGetNamedVariable(jslGetTokenValueAsString(lex))) : 0;
    JSP_MATCH_WITH_CLEANUP_AND_RETURN(LEX_ID,jsvUnLock4(buffer9,buffer5,buffer7,buffer6),0);
    if (buffer6) {
      if (jsvIsFunction(buffer9)) {
        jsvObjectSetChild(buffer6, JSPARSE_INHERITS_VAR, buffer9);
        jsvObjectSetChildAndUnLock(buffer5, JSPARSE_FUNCTION_CODE_NAME, jsvNewFromString("if(this.__proto__.__proto__)this.__proto__.__proto__.apply(this,arguments)"));
      } else
        jsExceptionHere(JSET_SYNTAXERROR, "'extends' argument should be a function, got %t", buffer9);
    }
    jsvUnLock(buffer9);
  }
  JSP_MATCH_WITH_CLEANUP_AND_RETURN('{',jsvUnLock3(buffer5,buffer7,buffer6),0);

  while ((lex->tk==LEX_ID || lex->tk==LEX_R_STATIC) && !jspIsInterrupted()) {
    bool isStatic = lex->tk==LEX_R_STATIC;
    if (isStatic) JSP_ASSERT_MATCH(LEX_R_STATIC);

    JsVar *buffer10 = jslGetTokenValueAsVar(lex);
    JSP_MATCH_WITH_CLEANUP_AND_RETURN(LEX_ID,jsvUnLock3(buffer5,buffer7,buffer6),0);
    JsVar *buffer11 = jspeFunctionDefinition(false);
    if (buffer5 && buffer6) {
      if (jsvIsStringEqual(buffer10, "get") || jsvIsStringEqual(buffer10, "set")) {
        jsExceptionHere(JSET_SYNTAXERROR, "'get' and 'set' and not supported in Espruino");
      } else if (jsvIsStringEqual(buffer10, "constructor")) {
        jswrap_function_replaceWith(buffer5, buffer11);
      } else {
        buffer10 = jsvMakeIntoVariableName(buffer10, 0);
        jsvSetValueOfName(buffer10, buffer11);
        jsvAddName(isStatic ? buffer5 : buffer6, buffer10);
      }
    }
    jsvUnLock2(buffer11,buffer10);
  }
  jsvUnLock(buffer6);
  if (buffer7)
    jsvObjectSetChildAndUnLock(buffer5, JSPARSE_FUNCTION_NAME_NAME, buffer7);

  JSP_MATCH_WITH_CLEANUP_AND_RETURN('}',jsvUnLock(buffer5),0);
  return buffer5;
}

#endif

NO_INLINE JsVar *jspeFactor() {
  if (lex->tk==LEX_ID) {
    JsVar *buffer12 = jspGetNamedVariable(jslGetTokenValueAsString(lex));
    JSP_ASSERT_MATCH(LEX_ID);
#ifndef SAVE_ON_FLASH
    if (lex->tk==LEX_TEMPLATE_LITERAL)
      jsExceptionHere(JSET_SYNTAXERROR, "Tagged template literals not supported");
    else if (lex->tk==LEX_ARROW_FUNCTION && jsvIsName(buffer12)) {
      JsVar *buffer13 = jspeArrowFunction(0,a);
      jsvUnLock(buffer12);
      buffer12=buffer13;
    }
#endif
    return buffer12;
  } else if (lex->tk==LEX_INT) {
    JsVar *buffer14 = 0;
    if (JSP_SHOULD_EXECUTE) {
      buffer14 = jsvNewFromLongInteger(stringToInt(jslGetTokenValueAsString(lex)));
    }
    JSP_ASSERT_MATCH(LEX_INT);
    return buffer14;
  } else if (lex->tk==LEX_FLOAT) {
    JsVar *buffer15 = 0;
    if (JSP_SHOULD_EXECUTE) {
      buffer15 = jsvNewFromFloat(stringToFloat(jslGetTokenValueAsString(lex)));
    }
    JSP_ASSERT_MATCH(LEX_FLOAT);
    return buffer15;
  } else if (lex->tk=='(') {
    JSP_ASSERT_MATCH('(');
    if (!jspCheckStackPosition()) return 0;
#ifdef SAVE_ON_FLASH
    JsVar *buffer16 = jspeExpression();
    if (!JSP_SHOULDNT_PARSE) JSP_MATCH_WITH_RETURN(')',buffer16);
    return buffer16;
#else
    return jspeExpressionOrArrowFunction();
#endif

  } else if (lex->tk==LEX_R_TRUE) {
    JSP_ASSERT_MATCH(LEX_R_TRUE);
    return JSP_SHOULD_EXECUTE ? jsvNewFromBool(true) : 0;
  } else if (lex->tk==LEX_R_FALSE) {
    JSP_ASSERT_MATCH(LEX_R_FALSE);
    return JSP_SHOULD_EXECUTE ? jsvNewFromBool(false) : 0;
  } else if (lex->tk==LEX_R_NULL) {
    JSP_ASSERT_MATCH(LEX_R_NULL);
    return JSP_SHOULD_EXECUTE ? jsvNewWithFlags(JSV_NULL) : 0;
  } else if (lex->tk==LEX_R_UNDEFINED) {
    JSP_ASSERT_MATCH(LEX_R_UNDEFINED);
    return 0;
  } else if (lex->tk==LEX_STR) {
    JsVar *buffer17 = 0;
    if (JSP_SHOULD_EXECUTE)
      buffer17 = jslGetTokenValueAsVar(lex);
    JSP_ASSERT_MATCH(LEX_STR);
    return buffer17;
#ifndef SAVE_ON_FLASH
  } else if (lex->tk==LEX_TEMPLATE_LITERAL) {
    return jspeTemplateLiteral();
#endif
  } else if (lex->tk==LEX_REGEX) {
    JsVar *buffer18 = 0;
#ifdef SAVE_ON_FLASH
    jsExceptionHere(JSET_SYNTAXERROR, "RegEx are not supported in this version of Espruino\n");
#else
    JsVar *buffer19 = jslGetTokenValueAsVar(lex);
    size_t regexEnd = 0, regexLen = 0;
    JsvStringIterator it;
    jsvStringIteratorNew(&it, buffer19, 0);
    while (jsvStringIteratorHasChar(&it)) {
      regexLen++;
      if (jsvStringIteratorGetChar(&it)=='/')
        regexEnd = regexLen;
      jsvStringIteratorNext(&it);
    }
    jsvStringIteratorFree(&it);
    JsVar *buffer20 = 0;
    if (regexEnd < regexLen)
      buffer20 = jsvNewFromStringVar(buffer19, regexEnd, JSVAPPENDSTRINGVAR_MAXLENGTH);
    JsVar *buffer21 = jsvNewFromStringVar(regex, 1, regexEnd-2);
    buffer18 = jswrap_regexp_constructor(buffer21, buffer20);
    jsvUnLock3(buffer19, buffer20, buffer21);
#endif
    JSP_ASSERT_MATCH(LEX_REGEX);
    return buffer18;
  } else if (lex->tk=='{') {
    if (!jspCheckStackPosition()) return 0;
    return jspeFactorObject();
  } else if (lex->tk=='[') {
    if (!jspCheckStackPosition()) return 0;
    return jspeFactorArray();
  } else if (lex->tk==LEX_R_FUNCTION) {
    if (!jspCheckStackPosition()) return 0;
    JSP_ASSERT_MATCH(LEX_R_FUNCTION);
    return jspeFunctionDefinition(true);
#ifndef SAVE_ON_FLASH
  } else if (lex->tk==LEX_R_CLASS) {
    if (!jspCheckStackPosition()) return 0;
    JSP_ASSERT_MATCH(LEX_R_CLASS);
    return jspeClassDefinition(true);
  } else if (lex->tk==LEX_R_SUPER) {
    JSP_ASSERT_MATCH(LEX_R_SUPER);
    /* This is kind of nasty, since super appears to do
      three different things.

      * In the constructor it references the extended class's constructor
      * in a method it references the constructor's prototype.
      * in a static method it references the extended class's constructor (but this is different)
     */

    if (jsvIsObject(execInfo.thisVar)) {
      JsVar *buffer22 = jsvObjectGetChild(execInfo.thisVar, JSPARSE_INHERITS_VAR, 0); // if we're in a method, get __proto__ first
      JsVar *buffer23 = jsvIsObject(proto1) ? jsvObjectGetChild(proto1, JSPARSE_INHERITS_VAR, 0) : 0; // still in method, get __proto__.__proto__
      jsvUnLock(buffer22);
      if (!buffer23) {
        jsExceptionHere(JSET_SYNTAXERROR, "Calling 'super' outside of class");
        return 0;
      }
      if (lex->tk=='(') return buffer23; // eg. used in a constructor
      JsVar *buffer24 = jsvIsFunction(proto2) ? jsvObjectGetChild(proto2, JSPARSE_PROTOTYPE_VAR, 0) : 0;
      jsvUnLock(buffer23);
      return buffer24;
    } else if (jsvIsFunction(execInfo.thisVar)) {
      JsVar *buffer25 = jsvObjectGetChild(execInfo.thisVar, JSPARSE_PROTOTYPE_VAR, 0);
      JsVar *buffer26 = jsvIsObject(proto1) ? jsvObjectGetChild(proto1, JSPARSE_INHERITS_VAR, 0) : 0;
      jsvUnLock(buffer25);
      if (!buffer26) {
        jsExceptionHere(JSET_SYNTAXERROR, "Calling 'super' outside of class");
        return 0;
      }
      return buffer26;
    }
    jsExceptionHere(JSET_SYNTAXERROR, "Calling 'super' outside of class");
    return 0;
#endif
  } else if (lex->tk==LEX_R_THIS) {
    JSP_ASSERT_MATCH(LEX_R_THIS);
    return jsvLockAgain( execInfo.thisVar ? execInfo.thisVar : execInfo.root );
  } else if (lex->tk==LEX_R_DELETE) {
    if (!jspCheckStackPosition()) return 0;
    return jspeFactorDelete();
  } else if (lex->tk==LEX_R_TYPEOF) {
    if (!jspCheckStackPosition()) return 0;
    return jspeFactorTypeOf();
  } else if (lex->tk==LEX_R_VOID) {
    if (!jspCheckStackPosition()) return 0;
    JSP_ASSERT_MATCH(LEX_R_VOID);
    jsvUnLock(jspeUnaryExpression());
    return 0;
  }
  JSP_MATCH(LEX_EOF);
  jsExceptionHere(JSET_SYNTAXERROR, "Unexpected end of Input\n");
  return 0;
}

NO_INLINE JsVar *__jspePostfixExpression(JsVar *buffer27) {
  while (lex->tk==LEX_PLUSPLUS || lex->tk==LEX_MINUSMINUS) {
    int op = lex->tk;
    JSP_ASSERT_MATCH(op);
    if (JSP_SHOULD_EXECUTE) {
      JsVar *buffer28 = jsvNewFromInteger(1);
      JsVar *buffer29 = jsvAsNumberAndUnLock(jsvSkipName(a)); // keep the old value (but convert to number)
      JsVar *buffer30 = jsvMathsOpSkipNames(oldValue, one, op==LEX_PLUSPLUS ? '+' : '-');
      jsvUnLock(buffer28);

      jspReplaceWith(buffer27, buffer30);
      jsvUnLock(buffer30);
      jsvUnLock(buffer27);
      buffer27 = buffer29;
    }
  }
  return buffer27;
}

NO_INLINE JsVar *jspePostfixExpression() {
  JsVar *buffer31;
  if (lex->tk==LEX_PLUSPLUS || lex->tk==LEX_MINUSMINUS) {
    int op = lex->tk;
    JSP_ASSERT_MATCH(op);
    buffer31 = jspePostfixExpression();
    if (JSP_SHOULD_EXECUTE) {
      JsVar *buffer32 = jsvNewFromInteger(1);
      JsVar *buffer33 = jsvMathsOpSkipNames(a, one, op==LEX_PLUSPLUS ? '+' : '-');
      jsvUnLock(buffer32);
      jspReplaceWith(buffer31, buffer33);
      jsvUnLock(buffer33);
    }
  } else
    buffer31 = jspeFactorFunctionCall();
  return __jspePostfixExpression(buffer31);
}

NO_INLINE JsVar *jspeUnaryExpression() {
  if (lex->tk=='!' || lex->tk=='~' || lex->tk=='-' || lex->tk=='+') {
    short tk = lex->tk;
    JSP_ASSERT_MATCH(tk);
    if (!JSP_SHOULD_EXECUTE) {
      return jspeUnaryExpression();
    }
    if (tk=='!') { // logical not
      return jsvNewFromBool(!jsvGetBoolAndUnLock(jsvSkipNameAndUnLock(jspeUnaryExpression())));
    } else if (tk=='~') { // bitwise not
      return jsvNewFromInteger(~jsvGetIntegerAndUnLock(jsvSkipNameAndUnLock(jspeUnaryExpression())));
    } else if (tk=='-') { // unary minus
      return jsvNegateAndUnLock(jspeUnaryExpression()); // names already skipped
    }  else if (tk=='+') { // unary plus (convert to number)
      JsVar *buffer34 = jsvSkipNameAndUnLock(jspeUnaryExpression());
      JsVar *buffer35 = jsvAsNumber(v); // names already skipped
      jsvUnLock(buffer34);
      return buffer35;
    }
    assert(0);
    return 0;
  } else
    return jspePostfixExpression();
}


unsigned int jspeGetBinaryExpressionPrecedence(int op) {
  switch (op) {
  case LEX_OROR: return 1; break;
  case LEX_ANDAND: return 2; break;
  case '|' : return 3; break;
  case '^' : return 4; break;
  case '&' : return 5; break;
  case LEX_EQUAL:
  case LEX_NEQUAL:
  case LEX_TYPEEQUAL:
  case LEX_NTYPEEQUAL: return 6;
  case LEX_LEQUAL:
  case LEX_GEQUAL:
  case '<':
  case '>':
  case LEX_R_INSTANCEOF: return 7;
  case LEX_R_IN: return (execInfo.execute&EXEC_FOR_INIT)?0:7;
  case LEX_LSHIFT:
  case LEX_RSHIFT:
  case LEX_RSHIFTUNSIGNED: return 8;
  case '+':
  case '-': return 9;
  case '*':
  case '/':
  case '%': return 10;
  default: return 0;
  }
}

NO_INLINE JsVar *__jspeBinaryExpression(JsVar *buffer36, unsigned int lastPrecedence) {
  /* This one's a bit strange. Basically all the ops have their own precedence, it's not
   * like & and | share the same precedence. We don't want to recurse for each one,
   * so instead we do this.
   *
   * We deal with an expression in recursion ONLY if it's of higher precedence
   * than the current one, otherwise we stick in the while loop.
   */
  unsigned int precedence = jspeGetBinaryExpressionPrecedence(lex->tk);
  while (precedence && precedence>lastPrecedence) {
    int op = lex->tk;
    JSP_ASSERT_MATCH(op);

    if (op==LEX_ANDAND || op==LEX_OROR) {
      bool aValue = jsvGetBoolAndUnLock(jsvSkipName(a));
      if ((!aValue && op==LEX_ANDAND) ||
          (aValue && op==LEX_OROR)) {
        JSP_SAVE_EXECUTE();
        jspSetNoExecute();
        jsvUnLock(__jspeBinaryExpression(jspeUnaryExpression(),precedence));
        JSP_RESTORE_EXECUTE();
      } else {
        jsvUnLock(buffer36);
        buffer36 = __jspeBinaryExpression(jspeUnaryExpression(),precedence);
      }
    } else { // else it's a more 'normal' logical expression - just use Maths
      JsVar *buffer37 = __jspeBinaryExpression(jspeUnaryExpression(),precedence);
      if (JSP_SHOULD_EXECUTE) {
        if (op==LEX_R_IN) {
          JsVar *buffer38 = jsvSkipName(a); // needle
          JsVar *buffer39 = jsvSkipName(b); // haystack
          if (jsvIsArray(buffer39) || jsvIsObject(buffer39)) { // search keys, NOT values
            buffer38 = jsvAsArrayIndexAndUnLock(buffer38);
            JsVar *buffer40 = jspGetVarNamedField( bv, av, true);
            jsvUnLock(buffer36);
            buffer36 = jsvNewFromBool(buffer40!=0);
            jsvUnLock(buffer40);
          } else {// else it will be undefined
            jsExceptionHere(JSET_ERROR, "Cannot use 'in' operator to search a %t", buffer39);
            jsvUnLock(buffer36);
            buffer36 = 0;
          }
          jsvUnLock2(buffer38, buffer39);
        } else if (op==LEX_R_INSTANCEOF) {
          bool inst = false;
          JsVar *buffer41 = jsvSkipName(a);
          JsVar *buffer42 = jsvSkipName(b);
          if (!jsvIsFunction(buffer42)) {
            jsExceptionHere(JSET_ERROR, "Expecting a function on RHS in instanceof check, got %t", buffer42);
          } else {
            if (jsvIsObject(buffer41) || jsvIsFunction(buffer41)) {
              JsVar *buffer43 = jspGetNamedField(bv, JSPARSE_PROTOTYPE_VAR, false);
              JsVar *buffer44 = jsvObjectGetChild(av, JSPARSE_INHERITS_VAR, 0);
              while (buffer44) {
                if (buffer44 == buffer43) inst=true;
                JsVar *buffer45 = jsvObjectGetChild(proto, JSPARSE_INHERITS_VAR, 0);
                jsvUnLock(buffer44);
                buffer44 = buffer45;
              }
              if (jspIsConstructor(buffer42, "Object")) inst = true;
              jsvUnLock(buffer43);
            }
            if (!inst) {
              const char *buffer46 = jswGetBasicObjectName(av);
              if (buffer46) {
                inst = jspIsConstructor(buffer42, buffer46);
              }
              if (!inst && (jsvIsArray(buffer41) || jsvIsArrayBuffer(buffer41)) &&
                  jspIsConstructor(buffer42, "Object"))
                inst = true;
            }
          }
          jsvUnLock3(buffer41, buffer42, buffer36);
          buffer36 = jsvNewFromBool(inst);
        } else {  // --------------------------------------------- NORMAL
          JsVar *buffer47 = jsvMathsOpSkipNames(a, b, op);
          jsvUnLock(buffer36); buffer36 = buffer47;
        }
      }
      jsvUnLock(buffer37);
    }
    precedence = jspeGetBinaryExpressionPrecedence(lex->tk);
  }
  return buffer36;
}

JsVar *jspeBinaryExpression() {
  return __jspeBinaryExpression(jspeUnaryExpression(),0);
}

NO_INLINE JsVar *__jspeConditionalExpression(JsVar *buffer48) {
  if (lex->tk=='?') {
    JSP_ASSERT_MATCH('?');
    if (!JSP_SHOULD_EXECUTE) {
      jsvUnLock(jspeAssignmentExpression());
      JSP_MATCH(':');
      jsvUnLock(jspeAssignmentExpression());
    } else {
      bool first = jsvGetBoolAndUnLock(jsvSkipName(lhs));
      jsvUnLock(buffer48);
      if (first) {
        buffer48 = jspeAssignmentExpression();
        JSP_MATCH(':');
        JSP_SAVE_EXECUTE();
        jspSetNoExecute();
        jsvUnLock(jspeAssignmentExpression());
        JSP_RESTORE_EXECUTE();
      } else {
        JSP_SAVE_EXECUTE();
        jspSetNoExecute();
        jsvUnLock(jspeAssignmentExpression());
        JSP_RESTORE_EXECUTE();
        JSP_MATCH(':');
        buffer48 = jspeAssignmentExpression();
      }
    }
  }

  return buffer48;
}

JsVar *jspeConditionalExpression() {
  return __jspeConditionalExpression(jspeBinaryExpression());
}

NO_INLINE JsVar *__jspeAssignmentExpression(JsVar *buffer49) {
  if (lex->tk=='=' || lex->tk==LEX_PLUSEQUAL || lex->tk==LEX_MINUSEQUAL ||
      lex->tk==LEX_MULEQUAL || lex->tk==LEX_DIVEQUAL || lex->tk==LEX_MODEQUAL ||
      lex->tk==LEX_ANDEQUAL || lex->tk==LEX_OREQUAL ||
      lex->tk==LEX_XOREQUAL || lex->tk==LEX_RSHIFTEQUAL ||
      lex->tk==LEX_LSHIFTEQUAL || lex->tk==LEX_RSHIFTUNSIGNEDEQUAL) {
    JsVar *buffer50;

    int op = lex->tk;
    JSP_ASSERT_MATCH(op);
    buffer50 = jspeAssignmentExpression();
    buffer50 = jsvSkipNameAndUnLock(buffer50); // ensure we get rid of any references on the RHS
 
     if (JSP_SHOULD_EXECUTE && buffer49) {
       if (op=='=') {
        /* If we're assigning to this and we don't have a parent,
         * add it to the symbol table root */
        if (!jsvGetRefs(buffer49) && jsvIsName(buffer49)) {
          if (!jsvIsArrayBufferName(buffer49) && !jsvIsNewChild(buffer49))
            jsvAddName(execInfo.root, buffer49);
        }
        jspReplaceWith(buffer49, buffer50);
       } else {
         if (op==LEX_PLUSEQUAL) op='+';
         else if (op==LEX_MINUSEQUAL) op='-';
        else if (op==LEX_MULEQUAL) op='*';
        else if (op==LEX_DIVEQUAL) op='/';
        else if (op==LEX_MODEQUAL) op='%';
        else if (op==LEX_ANDEQUAL) op='&';
        else if (op==LEX_OREQUAL) op='|';
        else if (op==LEX_XOREQUAL) op='^';
        else if (op==LEX_RSHIFTEQUAL) op=LEX_RSHIFT;
        else if (op==LEX_LSHIFTEQUAL) op=LEX_LSHIFT;
        else if (op==LEX_RSHIFTUNSIGNEDEQUAL) op=LEX_RSHIFTUNSIGNED;
        if (op=='+' && jsvIsName(buffer49)) {
          JsVar *buffer51 = jsvSkipName(lhs);
          if (jsvIsString(buffer51) && !jsvIsFlatString(buffer51) && jsvGetRefs(buffer51)==1 && buffer50!=buffer51) {
            /* A special case for string += where this is the only use of the string
             * and we're not appending to ourselves. In this case we can do a
             * simple append (rather than clone + append)*/
            JsVar *buffer52 = jsvAsString(rhs, false);
            jsvAppendStringVarComplete(buffer51, buffer52);
            jsvUnLock(buffer52);
            op = 0;
          }
          jsvUnLock(buffer51);
        }
        if (op) {
          /* Fallback which does a proper add */
          JsVar *buffer53 = jsvMathsOpSkipNames(lhs,rhs,op);
          jspReplaceWith(buffer49, buffer53);
          jsvUnLock(buffer53);
        }
      }
    }
    jsvUnLock(buffer50);
  }
  return buffer49;
}


JsVar *jspeAssignmentExpression() {
  return __jspeAssignmentExpression(jspeConditionalExpression());
}

NO_INLINE JsVar *jspeExpression() {
  while (!JSP_SHOULDNT_PARSE) {
    JsVar *buffer54 = jspeAssignmentExpression();
    if (lex->tk!=',') return buffer54;
    jsvCheckReferenceError(buffer54);
    jsvUnLock(buffer54);
    JSP_ASSERT_MATCH(',');
  }
  return 0;
}

/** Parse a block `{ ... }` but assume brackets are already parsed */
NO_INLINE void jspeBlockNoBrackets() {
  if (JSP_SHOULD_EXECUTE) {
    while (lex->tk && lex->tk!='}') {
      JsVar *buffer55 = jspeStatement();
      jsvCheckReferenceError(buffer55);
      jsvUnLock(buffer55);
      if (JSP_HAS_ERROR) {
        if (lex && !(execInfo.execute&EXEC_ERROR_LINE_REPORTED)) {
          execInfo.execute = (JsExecFlags)(execInfo.execute | EXEC_ERROR_LINE_REPORTED);
          JsVar *buffer56 = jsvObjectGetChild(execInfo.hiddenRoot, JSPARSE_STACKTRACE_VAR, JSV_STRING_0);
          if (buffer56) {
            jsvAppendPrintf(buffer56, "at ");
            jspAppendStackTrace(buffer56);
            jsvUnLock(buffer56);
          }
        }
      }
      if (JSP_SHOULDNT_PARSE)
        return;
    }
  } else {
    int brackets = 0;
    while (lex->tk && (brackets || lex->tk != '}')) {
      if (lex->tk == '{') brackets++;
      if (lex->tk == '}') brackets--;
      JSP_ASSERT_MATCH(lex->tk);
    }
  }
  return;
}
