static void windowCodeRangeTest(
  WindowCodeArg *p, 
  int op,                         /* OP_Ge, OP_Gt, or OP_Le */
  int csr1,                       /* Cursor number for cursor 1 */
  int regVal,                     /* Register containing non-negative number */
  int csr2,                       /* Cursor number for cursor 2 */
  int lbl                         /* Jump destination if condition is true */
){
  Parse *pParse = p->pParse;
  Vdbe *v = sqlite3GetVdbe(pParse);
  ExprList *pOrderBy = p->pMWin->pOrderBy;  /* ORDER BY clause for window */
  int reg1 = sqlite3GetTempReg(pParse);     /* Reg. for csr1.peerVal+regVal */
  int reg2 = sqlite3GetTempReg(pParse);     /* Reg. for csr2.peerVal */
  int regString = ++pParse->nMem;           /* Reg. for constant value '' */
  int arith = OP_Add;                       /* OP_Add or OP_Subtract */
  int addrGe;                               /* Jump destination */

  assert( op==OP_Ge || op==OP_Gt || op==OP_Le );
  assert( pOrderBy && pOrderBy->nExpr==1 );
  if( pOrderBy->a[0].sortFlags & KEYINFO_ORDER_DESC ){
    switch( op ){
      case OP_Ge: op = OP_Le; break;
      case OP_Gt: op = OP_Lt; break;
      default: assert( op==OP_Le ); op = OP_Ge; break;
    }
    arith = OP_Subtract;
  }

  /* Read the peer-value from each cursor into a register */
  windowReadPeerValues(p, csr1, reg1);
  windowReadPeerValues(p, csr2, reg2);

  VdbeModuleComment((v, "CodeRangeTest: if( R%d %s R%d %s R%d ) goto lbl",
      reg1, (arith==OP_Add ? "+" : "-"), regVal,
      ((op==OP_Ge) ? ">=" : (op==OP_Le) ? "<=" : (op==OP_Gt) ? ">" : "<"), reg2
  ));

  /* Register reg1 currently contains csr1.peerVal (the peer-value from csr1).
  ** This block adds (or subtracts for DESC) the numeric value in regVal
  ** from it. Or, if reg1 is not numeric (it is a NULL, a text value or a blob),
  ** then leave reg1 as it is. In pseudo-code, this is implemented as:
  **
  **   if( reg1>='' ) goto addrGe;
  **   reg1 = reg1 +/- regVal
  **   addrGe:
  **
  ** Since all strings and blobs are greater-than-or-equal-to an empty string,
  ** the add/subtract is skipped for these, as required. If reg1 is a NULL,
  ** then the arithmetic is performed, but since adding or subtracting from
  ** NULL is always NULL anyway, this case is handled as required too.  */
  sqlite3VdbeAddOp4(v, OP_String8, 0, regString, 0, "", P4_STATIC);
  addrGe = sqlite3VdbeAddOp3(v, OP_Ge, regString, 0, reg1);
  VdbeCoverage(v);
  sqlite3VdbeAddOp3(v, arith, regVal, reg1, reg1);
  sqlite3VdbeJumpHere(v, addrGe);

  /* If the BIGNULL flag is set for the ORDER BY, then it is required to 
  ** consider NULL values to be larger than all other values, instead of 
  ** the usual smaller. The VDBE opcodes OP_Ge and so on do not handle this
  ** (and adding that capability causes a performance regression), so
  ** instead if the BIGNULL flag is set then cases where either reg1 or
  ** reg2 are NULL are handled separately in the following block. The code
  ** generated is equivalent to:
  **
  **   if( reg1 IS NULL ){
  **     if( op==OP_Ge ) goto lbl;
  **     if( op==OP_Gt && reg2 IS NOT NULL ) goto lbl;
  **     if( op==OP_Le && reg2 IS NULL ) goto lbl;
  **   }else if( reg2 IS NULL ){
  **     if( op==OP_Le ) goto lbl;
  **   }
  **
  ** Additionally, if either reg1 or reg2 are NULL but the jump to lbl is 
  ** not taken, control jumps over the comparison operator coded below this
  ** block.  */
  if( pOrderBy->a[0].sortFlags & KEYINFO_ORDER_BIGNULL ){
    /* This block runs if reg1 contains a NULL. */
    int addr = sqlite3VdbeAddOp1(v, OP_NotNull, reg1); VdbeCoverage(v);
    switch( op ){
      case OP_Ge: 
        sqlite3VdbeAddOp2(v, OP_Goto, 0, lbl); 
        break;
      case OP_Gt: 
        sqlite3VdbeAddOp2(v, OP_NotNull, reg2, lbl); 
        VdbeCoverage(v); 
        break;
      case OP_Le: 
        sqlite3VdbeAddOp2(v, OP_IsNull, reg2, lbl); 
        VdbeCoverage(v); 
        break;
      default: assert( op==OP_Lt ); /* no-op */ break;
    }
    sqlite3VdbeAddOp2(v, OP_Goto, 0, sqlite3VdbeCurrentAddr(v)+3);

    /* This block runs if reg1 is not NULL, but reg2 is. */
    sqlite3VdbeJumpHere(v, addr);
    sqlite3VdbeAddOp2(v, OP_IsNull, reg2, lbl); VdbeCoverage(v);
    if( op==OP_Gt || op==OP_Ge ){
      sqlite3VdbeChangeP2(v, -1, sqlite3VdbeCurrentAddr(v)+1);
    }
  }

  /* Compare registers reg2 and reg1, taking the jump if required. Note that
  ** control skips over this test if the BIGNULL flag is set and either
  ** reg1 or reg2 contain a NULL value.  */
  sqlite3VdbeAddOp3(v, op, reg2, lbl, reg1); VdbeCoverage(v);
  sqlite3VdbeChangeP5(v, SQLITE_NULLEQ);

  assert( op==OP_Ge || op==OP_Gt || op==OP_Lt || op==OP_Le );
  testcase(op==OP_Ge); VdbeCoverageIf(v, op==OP_Ge);
  testcase(op==OP_Lt); VdbeCoverageIf(v, op==OP_Lt);
  testcase(op==OP_Le); VdbeCoverageIf(v, op==OP_Le);
  testcase(op==OP_Gt); VdbeCoverageIf(v, op==OP_Gt);
  sqlite3ReleaseTempReg(pParse, reg1);
  sqlite3ReleaseTempReg(pParse, reg2);

  VdbeModuleComment((v, "CodeRangeTest: end"));
}