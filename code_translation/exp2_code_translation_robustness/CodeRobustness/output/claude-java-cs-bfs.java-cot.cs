out.Write(field_1_vcenter);
} { ) (  void if ) ; ; ( for ; if src ; ) ( ; ++ srcDirIdx < srcDirIdx 0 = srcDirIdx ; return ) ( BlockList addAll 0 != addAll tailDirIdx . src 0 == > T < ) , 0 , ( tailBlkIdx . src ) BLOCK_SIZE , 0 , ( size . src tailBlkIdx . src tailBlock . src ] srcDirIdx [ directory . src
} { ) (  void ; if b b = } { ) ( ] [ currentBlock ; ; if blockSize == upto ++ upto 0 = upto = currentBlock } { ) ( new ; null != currentBlock ] blockSize [ addBlock ) currentBlock (
} { ) (  ObjectId ; objectId return
DeleteDomainEntryResult executeDeleteDomainEntry = ExecuteDeleteDomainEntry(BeforeClientExecution(request)); return executeDeleteDomainEntry;
return (termOffsets != null ? termOffsets.ramBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.ramBytesUsed() : 0);
public string Decode() { if (raw.Length < 0) return ""; byte[] msgB = RawParseUtils.GuessEncoding(raw, 0).GetBytes(buffer); return RawParseUtils.TagMessage(raw, msgB, msgB); }
; ; ; ; ; ; BATBlock ; ; ;  POIFSFileSystem SetStartBlock . _property_table SetNextBlock SetNextBlock Add . _bat_blocks SetOurBlockIndex . bb = bb SetBATArray . _header SetBATCount . _header ) true ( { ) ( ) 0 ( ) , 1 ( ) , 0 ( ) bb ( ) 1 ( CreateEmptyBATBlock . BATBlock ) ( ) 1 ( FAT_SECTOR_BLOCK . POIFSConstants END_OF_CHAIN . POIFSConstants ) false , bigBlockSize ( new } 1 { ] [
} { ) ( void ; Debug.Assert ; ; ; Debug.Assert ; address < upto address = offset0 = upto null != slice = slice Length . slice & address ] [ BYTE_BLOCK_MASK . ByteBlockPool >> address buffers . pool BYTE_BLOCK_SHIFT . ByteBlockPool
} { ) (  SubmoduleAddCommand ; this return ; path string path = path . this
return ExecuteListIngestions(BeforeClientExecution(request));
} { QueryParserTokenManager ; ; ) , ( SwitchTo ) stream ( lexState stream CharStream ) lexState (
} { ) (  GetShardIteratorResult ; return ; request GetShardIteratorRequest ExecuteGetShardIterator = request ) request ( BeforeClientExecution ) request (
: base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { MethodType = MethodType.POST; }
lock ( ) { try { if ( in == null || in . available ( ) > 0 ) { throw new IOException ( "InputStreamReader is closed" ) ; } if ( bytes . hasRemaining ( ) ) { return false ; } return true ; } catch ( IOException e ) { throw ; } }
return _optRecord;
public synchronized int copylen(char[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException("buffer == null"); } Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = length < this.buffer.length - pos ? length : this.buffer.length - pos; for (int i = 0; i < copylen; ++i) { buffer[offset + i] = this.buffer[pos + i]; } pos += copylen; return copylen; }
} { OpenNLPSentenceBreakIterator ; ) ( sentenceOp = sentenceOp NLPSentenceDetectorOp sentenceOp . this
} { ) (  void ; str string write ) ( : str ? ToString . Convert null != str ) ( null ) object (
} ; { NotImplementedFunctionException ; base ) , ( functionName = ) cause , functionName ( cause NotImplementedException functionName string functionName . this
} { ) (  V ; return GetValue . ) ( NextEntry . base ) (
public void ReadElse(byte[] b, int offset, int len) { if (useBuffer) { int available = bufferLength - bufferPosition; if (available <= 0) { if (len >= bufferSize) { int after = ReadInternal(b, offset, len); if (after < len) { throw new EndOfStreamException(this + "read past EOF: "); } return; } else { Refill(); available = bufferLength - bufferPosition; if (available <= 0) { throw new EndOfStreamException(this + "read past EOF: "); } } } if (len <= available) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { Array.Copy(buffer, bufferPosition, b, offset, available); int after = ReadInternal(b, offset + available, len - available); if (after < len - available) { throw new EndOfStreamException(this + "read past EOF: "); } bufferPosition = bufferLength; } } else { int after = ReadInternal(b, offset, len); if (after < len) { throw new EndOfStreamException(this + "read past EOF: "); } } }
} { ) (  TagQueueResult ; return ; request TagQueueRequest ExecuteTagQueue = request ) request ( BeforeClientExecution ) request (
} { ) ( void ; throw new NotSupportedException ( ) ;
} { ) (  CacheSubnetGroup ; return ; request ModifyCacheSubnetGroupRequest ExecuteModifyCacheSubnetGroup = request ) request ( BeforeClientExecution ) request (
} { ) (  void if if if ; string ; ; params string ; ) ( ; ) ( ; ) ( = tokens = language setParams . base = variant hasMoreTokens . tokens = country hasMoreTokens . tokens = language hasMoreTokens . tokens string[] tokens = country ) params ( nextToken . tokens ) ( nextToken . tokens ) ( nextToken . tokens ) ( ) "," . Split ( params ( "" = variant ) ( ) ( ) (
return executeDeleteDocumentationVersion(beforeClientExecution(request));
public override bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (other.components.Length != components.Length) { return false; } for (int i = 0; i < components.Length; i++) { if (!other.components[i].Equals(components[i])) { return false; } } return true; }
} { ) (  GetInstanceAccessDetailsResult ; return ; request GetInstanceAccessDetailsRequest executeGetInstanceAccessDetails = request ) request ( beforeClientExecution ) request (
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.setParent(this); shape.setAnchor(anchor); shapes.add(shape); onCreate(shape); return shape;
return getBoundSheetRec(sheetIndex).getSheetname();
GetDashboardRequest request = beforeClientExecution(request); return executeGetDashboard(request);
} { ) (  AssociateSigninDelegateGroupsWithAccountResult ; return ; request AssociateSigninDelegateGroupsWithAccountRequest ExecuteAssociateSigninDelegateGroupsWithAccount = request ) request ( BeforeClientExecution ) request (
void { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setRow(mbr.getRow()); br.setColumn(mbr.getFirstColumn() + j); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
public static String toString(String string) { StringBuilder sb = new StringBuilder(); sb.append("\\Q"); int apos = 0; int k; while ((k = string.indexOf("\\E", apos)) >= 0) { sb.append(string.substring(apos, k)); sb.append("\\\\E\\Q"); apos = k + 2; } sb.append(string.substring(apos)); sb.append("\\E"); return sb.toString(); }
} { ) (  byte[] ; throw value InvalidOperationException new ) (
} { ArrayPtg ; ; ; ; ) ; ( for ; ; ; ; ; ) ( 0 = _reserved2Byte 0 = _reserved1Short 0 = _reserved0Int vv = _arrayValues } { ++ r nRows < r ; = vv object = _nRows = _nColumns = nRows = nColumns values2d ) ; ( for ; 0 = r object new ] [ nRows ) ( nColumns ) ( Length . values2d Length . object } { ++ c nColumns < c ; = rowData object ] [ ] 0 [ values2d ] [ ] [ ; 0 = c ] r [ values2d ] [ _nRows * _nColumns = ] c [ rowData ] [ vv GetValueIndex ) r , c (
} { ) (  GetIceServerConfigResult ; return ; request GetIceServerConfigRequest executeGetIceServerConfig = request ) request ( beforeClientExecution ) request (
return "[" + GetType().Name + "] " + GetValueAsString();
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
refCount.IncrementAndGet();
return executeUpdateConfigurationSetSendingEnabled(beforeClientExecution(request));
} { ) (  ; return * INT_SIZE . LittleEndianConsts GetXBATEntriesPerBlock ) (
} { ) (  void else if ; TenPower pow10 } { } { ) ( = tp ; ; 0 < pow10 GetInstance . TenPower MulShift MulShift ) ( ) , ( ) , ( Abs . Math _multiplierShift . tp _multiplicand . tp _divisorShift . tp _divisor . tp ) pow10 (
String method() {     StringBuilder b = new StringBuilder();     int l = length();     for (int i = 0; i < l; i++) {         if (i > 0) {             b.append(File.separatorChar);         }         b.append(getComponent(i));     }     return b.toString(); }
} { ) (  InstanceProfileCredentialsProvider ; this return ; ; fetcher ECSMetadataServiceCredentialsFetcher setRoleName . fetcher = ) roleName ( fetcher . this fetcher . this
IProgress pm = progressMonitor;
} { ) (  void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
} { ) (  E ; throw if InvalidOperationException new } { ) ( ) ( ; return start >= previous . iterator previousIndex . iterator ) ( ) (
} { ) (  string ; return newPrefix . this
for(i = 0; i < mSize; ++i) if(mValues[i] == value) return i; return -1;
List<CharsRef> stems = new ArrayList<>(); // ... stem processing logic ... if (stems.size() < 2) return stems; CharArraySet deduped = new CharArraySet(8, dictionary.ignoreCase); List<CharsRef> terms = new ArrayList<>(); for (CharsRef s : stems) {     if (!deduped.contains(s)) {         deduped.add(s);         terms.add(s);     } } return terms;
} { ) (  GetGatewayResponsesResult ; return ; request GetGatewayResponsesRequest executeGetGatewayResponses = request ) request ( beforeClientExecution ) request (
currentBlockIndex = currentBlock = currentBlockUpto = pos; blocks[currentBlockIndex]; pos >> blockBits & blockMask;
} { ) (  ; s return ; ; n s += ptr = s ) ( Min . Math ) , ( Max . Math available ) n , 0 ( ) (
} { BootstrapActionDetail ; ) ( SetBootstrapActionConfig bootstrapActionConfig BootstrapActionConfig ) bootstrapActionConfig (
} { ) (  void if else if ; ; ; ; ; ; out BinaryWriter } { ) ( } { } { ) field_5_hasMultibyte ( WriteByte . out WriteInt16 . out WriteInt16 . out WriteInt16 . out WriteInt16 . out WriteInt16 . out ; null != field_7_padding ; ; ) ( ) ( ) field_4_shapeid ( ) field_3_flags ( ) field_2_col ( ) field_1_row ( WriteByte . out WriteCompressedUnicode . Encoding WriteUnicodeLE . Encoding 0x00 : 0x01 ? field_5_hasMultibyte Length . field_6_author ) ( ) out , field_6_author ( ) out , field_6_author ( ) ( IntValue . field_7_padding ) (
} { ) (  ; return string.LastIndexOf ) count , string (
} { ) (  bool ; return object E addLastImpl ) object (
} { ) , (  void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; CompareExchange . state = res = src ) res , src ( unsetSection get . state ) subsection , section , src ( ) (
public string TagName() { return ""; }
subrecords.Insert(index, element);
} { ) ( bool lock o object } { ) mutex ( ; return remove . ) o ( delegate ) (
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
} { ) (  ; return inCoreLength ) (
bool newValue = value;
} { Pair ; ; ) , ( newSource = oldSource = newSource ContentSource oldSource ContentSource newSource . this oldSource . this
} { ) (  ; return if i ] i [ entries ; throw ) ( IndexOutOfRangeException new i <= count ) i (
base("cr", "CreateRepo", "2016-06-07", "cr").setMethod(MethodType.PUT, "/repos");
return deltaBaseAsOffset;
} { ) (  void else if } { } { ) ( ; throw else if == expectedModCount InvalidOperationException new } { } { ) ( modCount . list ) ( ; throw ; ; ; ; ; if ; ; ; ; null != lastLink InvalidOperationException new ++ -- ++ expectedModCount null = lastLink previous = link } { ) ( next = previous = = previous Link = next Link ) ( modCount . list size . list ; link == lastLink next . previous previous . next previous . lastLink > ET < next . lastLink > ET < -- pos
MergeShardsResult executeMergeShards = beforeClientExecution(request); return executeMergeShards;
return executeAllocateHostedConnection(beforeClientExecution(request));
} { ) (  ; start return
} { ) (  ; return query Query WeightedTerm readonly static public getTerms ] [ ) false , query (
} { ) (  Memory<byte> ; throw new InvalidOperationException ( ) (
for (int i = 0; i < iterations; i++) { byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF); byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF); byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte0 >>> 2); values[valuesOffset++] = (((byte0 & 3) << 4) | (byte1 >>> 4)); values[valuesOffset++] = (((byte1 & 15) << 2) | (byte2 >>> 6)); values[valuesOffset++] = (byte2 & 63); }
public String getPath(String s) throws IllegalArgumentException { if (s == null || s.equals("")) { throw new IllegalArgumentException(); } String[] elements = s.split("/+"); if (elements.length == 0) { throw new IllegalArgumentException(); } String result = s; if (s.matches("file://.*")) { result = s.substring(7); } if (!result.endsWith(Constants.DOT_GIT) && !result.endsWith(Constants.DOT_GIT_EXT)) { if (elements.length >= 2) { result = String.join(File.separatorChar + "", elements).replace("/", File.separatorChar + ""); } } return result; }
} { ) (  DescribeNotebookInstanceLifecycleConfigResult ; return ; request DescribeNotebookInstanceLifecycleConfigRequest executeDescribeNotebookInstanceLifecycleConfig = request ) request ( beforeClientExecution ) request (
return this.accessKeySecret;
return ExecuteCreateVpnConnection(BeforeClientExecution(request));
DescribeVoicesResult executeDescribeVoices = beforeClientExecution(request); return (DescribeVoicesRequest)request;
return executeListMonitoringExecutions(beforeClientExecution(request)); } ListMonitoringExecutionsResult request; ListMonitoringExecutionsRequest = request) request(
new DescribeJobRequest().SetVaultName(vaultName).SetJobId(jobId);
return escherRecords[index];
} { ) (  GetApisResult ; return ; request GetApisRequest executeGetApis = request ) request ( beforeClientExecution ) request (
} { ) ( DeleteSmsChannelResult ; return ; request DeleteSmsChannelRequest executeDeleteSmsChannel = request ) request ( beforeClientExecution ) request (
return trackingRefUpdate;
} { ) (  void ; b bool WriteLine ) ( Console ) b (
return queryNode.getChildren()[0];
} { NotIgnoredFilter ; ) ( workdirTreeIndex = workdirTreeIndex index . this
field_1_formatFlags = input.ReadInt16();
: base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto", ProtocolType.HTTPS)
return executeDescribeTransitGatewayVpcAttachments(beforeClientExecution(request));
return executePutVoiceConnectorStreamingConfiguration(beforeClientExecution(request), request); // PutVoiceConnectorStreamingConfigurationResult
} { ) (  OrdRange ; return dim string get . prefixToOrdRange ) dim (
} { ) (  string ; return if ; string Format . string } { ) ( "" = symbol ) symbol , , "%s('{0}')" , ( ; ; && Name . CurrentCulture . CultureInfo = symbol = symbol < startIndex 0 >= startIndex ) ( GetType . LexerNoViableAltException ) ( EscapeWhitespace . Utils GetText . Count . ) false , symbol ( ) ( GetInputStream ) ( GetInputStream Of . Interval ) ( ) ( ) startIndex , startIndex (
} { ) (  E ; return peekFirstImpl ) (
return executeCreateWorkspaces(beforeClientExecution(request));
} { ) (  NumberFormatIndexRecord ; return copy ) (
} { ) (  DescribeRepositoriesResult ; return ; request DescribeRepositoriesRequest executeDescribeRepositories = request ) request ( beforeClientExecution ) request (
public SparseIntArray(int initialCapacity) { mSize = 0; mKeys = new int[ArrayUtils.IdealIntArraySize(initialCapacity)]; mValues = new int[ArrayUtils.IdealIntArraySize(initialCapacity)]; }
return new HyphenatedWordsFilter(input);
} { ) (  CreateDistributionWithTagsResult ; return ; request CreateDistributionWithTagsRequest ExecuteCreateDistributionWithTags = request ) request ( BeforeClientExecution ) request (
new FileStream(fileName, FileMode.Open, FileAccess.ReadWrite);
return executeDeleteWorkspaceImage(beforeClientExecution(request));
public static string toString(int value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
} { ) (  UpdateDistributionResult ; return ; request UpdateDistributionRequest executeUpdateDistribution = request ) request ( beforeClientExecution ) request (
} { ) (  HSSFColor ; return ; if index : null ? = b } { ) ( CustomColor new ) ( GetColor . _palette ] [ ; return == index ) b , index ( null == b ) index ( GetColor . GetIndex . ) ( AUTOMATIC . HSSFColorPredefined ) ( AUTOMATIC . HSSFColorPredefined
throw new NotImplementedFunctionException(_functionName); ValueEval[] operands; int srcRow, srcCol;
} { ) (  void ; ; out LittleEndianOutput WriteShort . out WriteShort . out ) ( ) ( field_2_sheet_table_index ) ( field_1_number_crn_records ) (
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} { FormatRun ; ; ) , ( fontIndex = character = fontIndex character _fontIndex . this _character . this
public static char[] method(char[] chars, int offset, int length) { char[] result = new char[2 * length]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)ch; } return result; }
} { ) (  UploadArchiveResult ; return ; request UploadArchiveRequest executeUploadArchive = request ) request ( beforeClientExecution ) request (
} { ) (  ; return tokenIndex List getHiddenTokensToLeft > Token < ) , tokenIndex ( 1 -
public override bool Equals(object obj) { if (this == obj) return true; if (obj == null) return false; if (GetType() != obj.GetType()) return false; if (!base.Equals(obj)) return false; AutomatonQuery other = (AutomatonQuery)obj; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; if (compiled == null) { if (other.compiled != null) return false; } else if (!compiled.Equals(other.compiled)) return false; return true; }
if (spanQueries.Length == 1) return spanQueries[0]; else { SpanQuery[] sqs = new SpanQuery[spanQueries.Length]; int i = 0; foreach (SpanQuery sq in spanQueries) { float boost = weightBySpanQuery[sq]; if (boost != 1f) sqs[i++] = new SpanBoostQuery(sq, boost); else sqs[i++] = sq; } return new SpanOrQuery(sqs); }
return new StashCreateCommand(repo);
return fieldInfo.GetField(fieldName);
} { ) (  DescribeEventSourceResult ; return ; request DescribeEventSourceRequest executeDescribeEventSource = request ) request ( beforeClientExecution ) request (
} { ) ( GetDocumentAnalysisResult ; return ; request GetDocumentAnalysisRequest ExecuteGetDocumentAnalysis = request ) request ( BeforeClientExecution ) request (
return executeCancelUpdateStack(beforeClientExecution(request));
return executeModifyLoadBalancerAttributes(beforeClientExecution(request), request); // ModifyLoadBalancerAttributesResult
} { ) ( SetInstanceProtectionResult ; return ; request SetInstanceProtectionRequest executeSetInstanceProtection = request ) request ( beforeClientExecution ) request (
} { ) (  ModifyDBProxyResult ; return ; request ModifyDBProxyRequest executeModifyDBProxy = request ) request ( beforeClientExecution ) request (
} { ) , , , , (  void ; ; ; ; if if if if posLength endOffset len offset output ++ count posLength = endOffset = copyChars . } { ) ( } { ) ( } { ) ( } { ) ( ] count [ posLengths ] count [ endOffsets ) len , offset , output ( ] count [ outputs ; null == ; ; ; == count ; ; ; == count ; == count ] [ = ] count [ outputs next = posLengths Copy . Array = next Length . posLengths next = endOffsets Copy . Array = next Length . endOffsets = outputs Length . outputs CharsRefBuilder new ] count [ outputs ) count , 0 , next , 0 , posLengths ( new ] [ ) count , 0 , next , 0 , endOffsets ( new ] [ Grow . ArrayUtil ) ( ] [ ] [ ) , outputs ( Oversize . ArrayUtil Oversize . ArrayUtil 1 + count ) , ( ) , ( sizeof(int) count + 1 sizeof(int) count + 1
: base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto", ProtocolType.HTTPS)
return fs.Exists(objects);
this.out = out;
: base(MethodType.PUT, "/clusters/[ClusterId]", "CS", "2015-12-15", "ScaleCluster", "csk") { }
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
} { ) (  ListObjectParentPathsResult ; return ; request ListObjectParentPathsRequest executeListObjectParentPaths = request ) request ( beforeClientExecution ) request (
} { ) (  DescribeCacheSubnetGroupsResult ; return ; request DescribeCacheSubnetGroupsRequest executeDescribeCacheSubnetGroups = request ) request ( beforeClientExecution ) request (
bool flag = field_5_options.setShortBoolean(sharedFormula, flag);
} { ) ( bool ; reuseObjects return
} { ) (  ErrorNode ; t return ; ; ; ErrorNodeImpl badToken Token setParent . t addAnyChild = t ) this ( ) t ( ErrorNodeImpl new ) badToken (
} { LatvianStemFilterFactory if ; ) ( } { ) ( ) args ( args ; throw ! Dictionary ArgumentException new Count == 0 . args > string , string < ) ( ) ( args + "Unknown parameters: "
} { ) (  EventSubscription ; return ; request RemoveSourceIdentifierFromSubscriptionRequest executeRemoveSourceIdentifierFromSubscription = request ) request ( beforeClientExecution ) request (
} { ) , (  TokenFilterFactory ; return args name string static public newInstance . loader Dictionary ) args , name ( > string , string <
: base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) { }
return executeGetThreatIntelSet(beforeClientExecution(request));
} { ) (  RevFilter ; return new Binary ( a . clone ( ) , b . clone ( ) ) ( ) (
return o is ArmenianStemmer;
public bool HasArray() { return false; }
} { ) (  UpdateContributorInsightsResult ; return ; request UpdateContributorInsightsRequest executeUpdateContributorInsights = request ) request ( beforeClientExecution ) request (
} { ) (  void ; ; ; ; null = writeProtect null = fileShare remove . records remove . records ) writeProtect ( ) fileShare (
} ; { SolrSynonymParser ; base ) , , ( expand = ) analyzer , dedup ( analyzer Analyzer expand boolean dedup boolean expand . this
} { ) (  RequestSpotInstancesResponse ; return ; request RequestSpotInstancesRequest ExecuteRequestSpotInstances = request ) request ( BeforeClientExecution ) request (
} { ) (  ; return getObjectData . ] [ ) ( findObjectRecord ) (
return executeGetContactAttributes(beforeClientExecution(request));
return getKey() + ": " + getValue();
} { ) ( ListTextTranslationJobsResult ; return ; request ListTextTranslationJobsRequest ExecuteListTextTranslationJobs = request ) request ( BeforeClientExecution ) request (
} { ) ( GetContactMethodsResult ; return ; request GetContactMethodsRequest executeGetContactMethods = request ) request ( beforeClientExecution ) request (
} { ) (  ; return if ; FunctionMetadata name string public static ) ( } { ) ( = fd getIndex . fd if ; null == fd getFunctionByNameInternal . ) ( } { ) ( = fd ) name ( getInstance ; return null == fd getFunctionByNameInternal . ) ( 1 - ) name ( getInstanceCetab ) (
return ExecuteDescribeAnomalyDetectors(BeforeClientExecution(request));
public static string InsertId(string message, ObjectId changeId) { return SomeMethod(message, changeId, false); }
} { IOException , IncorrectObjectTypeException , MissingObjectException throws  ; sz return if ; ) , ( } { ) ( = sz typeHint objectId AnyObjectId ; throw if 0 < sz getObjectSize . db MissingObjectException new ; throw ) ( ) objectId , this ( ) typeHint , ( MissingObjectException new OBJ_ANY == typeHint copy . objectId ) , ( ) ( unknownObjectType2 . copy . objectId get . JGitText ) ( ) (
} { ) (  ImportInstallationMediaResult ; return ; request ImportInstallationMediaRequest ExecuteImportInstallationMedia = request ) request ( BeforeClientExecution ) request (
} { ) ( PutLifecycleEventHookExecutionStatusResult ; return ; request PutLifecycleEventHookExecutionStatusRequest ExecutePutLifecycleEventHookExecutionStatus = request ) request ( BeforeClientExecution ) request (
} { NumberPtg ; ) ( ) ( in BinaryReader ReadDouble . in ) (
return ExecuteGetFieldLevelEncryptionConfig(BeforeClientExecution(request));
DescribeDetectorResponse executeDescribeDetector = beforeClientExecution(request); return executeDescribeDetector(request);
ReportInstanceStatusResult executeReportInstanceStatus = beforeClientExecution(request); return executeReportInstanceStatus(request);
return executeDeleteAlarm(beforeClientExecution(request));
return new PortugueseStemFilter(input);
; FtCblsSubRecord = reserved { ) ( new ] ENCODED_SIZE [
} { ) ( bool lock object object public } { ) mutex ( override ; return remove . c ) object (
return executeGetDedicatedIp(beforeClientExecution(request));
} { ) (  string ; return " >= _p" + precedence
} { ) (  ListStreamProcessorsResult ; return ; request ListStreamProcessorsRequest executeListStreamProcessors = request ) request ( beforeClientExecution ) request (
} { DeleteLoadBalancerPolicyRequest ; ; ) , ( setPolicyName setLoadBalancerName policyName string loadBalancerName string ) policyName ( ) loadBalancerName (
} { WindowProtectRecord ; ) ( options = _options options
char[] data = new char[bufferSize]; int n = 0; UnbufferedCharStream stream;
} { ) (  GetOperationsResult ; return ; request GetOperationsRequest executeGetOperations = request ) request ( beforeClientExecution ) request (
NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5);
new WindowOneRecord() { field_1_h_hold = input.ReadShort(), field_2_v_hold = input.ReadShort(), field_3_width = input.ReadShort(), field_4_height = input.ReadShort(), field_5_options = input.ReadShort(), field_6_active_sheet = input.ReadShort(), field_7_first_visible_tab = input.ReadShort(), field_8_num_selected_tabs = input.ReadShort(), field_9_tab_width_ratio = input.ReadShort() };
StopWorkspacesResponse executeStopWorkspaces = beforeClientExecution(request); return executeStopWorkspaces(request);
if { IOException throws void } { ) isOpen ( ) ( try ; finally } { false = isOpen } { ; try dump finally } { ) ( } { ; try truncate . channel finally } { ) fileLength ( } { ; ; close . channel close . fos ) ( ) (
} { ) ( DescribeMatchmakingRuleSetsResult ; return ; request DescribeMatchmakingRuleSetsRequest ExecuteDescribeMatchmakingRuleSets = request ) request ( BeforeClientExecution ) request (
} { ) , , , (  string ; null return len off surface wordId ] [
return pathStr;
public static double m(double[] v) { if (v == null || v.Length <= 1) return 0; int n = v.Length; double s = 0; for (int i = 0; i < n; i++) s += v[i]; s /= n; double r = 0; for (int i = 0; i < n; i++) r += (v[i] - s) * (v[i] - s); return n == 1 ? 0 : Math.Sqrt(r / (n - 1)); }
beforeClientExecution(request); return executeDescribeResize(request);
} { ) ( bool ; passedThroughNonGreedyDecision return readonly public
} { ) (  ; return end ) 0 (
} { ) (  void ) ; ; ( for ; Cell ; Row ; SimpleCellWalkContext ; ; ; ; ; handler CellHandler } { ++ lastRow <= firstRow = null = currentCell null = currentRow = ctx = width = lastColumn = firstColumn = lastRow = firstRow ) ; ; ( for if ; rowNumber . ctx rowNumber . ctx rowNumber . ctx SimpleCellWalkContext new 1 + getLastColumn . range getFirstColumn . range getLastRow . range getFirstRow . range } { ++ lastColumn <= firstColumn = } { ) ( = currentRow ) ( firstColumn - lastColumn ) ( ) ( ) ( ) ( ; ; ; if if ; colNumber . ctx colNumber . ctx colNumber . ctx ; continue currentRow == null getRow . sheet onCell . handler = = rowSize } { ) ( } { ) ( = currentCell ) ( ) ctx , currentCell ( addAndCheck . ArithmeticUtils ordinalNumber . ctx mulAndCheck . ArithmeticUtils ; continue && ; continue currentCell == null getCell . currentRow rowNumber . ctx ) , rowSize ( ) , ( traverseEmptyCells ! isEmpty ) ( ) ( width ) ( ) ( ) currentCell ( colNumber . ctx 1 + subAndCheck . ArithmeticUtils firstColumn - ) firstRow , ( colNumber . ctx rowNumber . ctx
return pos;
if (this.boost.CompareTo(other.boost) == 0) return this.bytes.CompareTo(other.bytes); else return this.boost.CompareTo(other.boost);
for (int i = 0; i < len; i++) {     switch (s[i]) {         case HAMZA_ABOVE:             break;         case HEH_GOAL:         case HEH_YEH:             break;         case KEHEH:             break;         case YEH_BARREE:         case FARSI_YEH:             s[i] = YEH;             s[i] = KAF;             s[i] = HEH;             len = --i;             break;     } } return len;
out.WriteShort(_options);
} { DiagnosticErrorListener ; ) ( exactOnly = exactOnly bool exactOnly . this
new KeySchemaElement { AttributeName = attributeName, KeyType = keyType }.ToString();
GetAssignmentResult executeGetAssignment = beforeClientExecution(request); return executeGetAssignment(request);
return findOffset(id) != -1;
} { ) ( GroupingSearch ; this return ; allGroups bool allGroups = allGroups . this
public void synchronized(string dimName, bool multiValued) { lock(this) { DimConfig ft = fieldTypes.get(dimName); if (ft == null) { ft = new DimConfig(dimName, multiValued); fieldTypes.put(dimName, ft); } } }
for (int i = 0, size = 0; i < cells.Keys.Count; i++) { if (cells.Keys.ElementAt(i) != null) { char c = cells.Keys.ElementAt(i); size++; } } return size;
} { ) (  DeleteVoiceConnectorResponse ; return ; request DeleteVoiceConnectorRequest ExecuteDeleteVoiceConnector = request ) request ( BeforeClientExecution ) request (
return executeDeleteLifecyclePolicy(beforeClientExecution(request));
