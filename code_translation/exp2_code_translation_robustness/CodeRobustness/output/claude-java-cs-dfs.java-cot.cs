} writer.Write(Field1VCenter); }
void Method(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { src.directory[srcDirIdx].AddAll(0, BLOCK_SIZE); } if (src.tailBlkIdx != 0) { tailBlock.AddAll(src.tailBlock.src, 0); } }
} ; b = ] ++ upto [ currentBlock } ; 0 = upto ; ] blockSize [ int new = currentBlock } ; ) currentBlock ( addBlock { ) null != currentBlock ( if { ) blockSize == upto ( if { ) b ( void
return ObjectId;
return ExecuteDeleteDomainEntry(beforeClientExecution(request));
return (termOffsets != null ? termOffsets.ramBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.ramBytesUsed() : 0);
public string DecodeTagMessage(byte[] raw) { byte[] msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.Decode(RawParseUtils.GuessEncoding(raw, msgB), raw, msgB.Length); }
POIFSFileSystem() { _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void Method(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
} ; this return ; path = path . this { ) path string (  SubmoduleAddCommand
return ExecuteListIngestions(BeforeClientExecution(request));
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
GetShardIteratorResult executeGetShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator(request); }
// Cannot translate: Invalid Java syntax
boolean method() throws IOException { synchronized(lock) { if(in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.hasRemaining() || in.available() > 0; } catch(Exception e) { return false; } } }
} ; _optRecord return { ) (  EscherOptRecord
public synchronized void method(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer == null"); } if (length == 0) { return 0; } int copylen = (count - pos < length) ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } pos += copylen; return copylen; }
} ; sentenceOp = sentenceOp . this { ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
} ; ) ) null ) Object ( ( valueOf . String : str ? null != str ( write { ) str String (  void
} ; functionName = functionName . this ; ) cause , functionName ( base { ) cause NotImplementedException , functionName string ( NotImplementedFunctionException
} ; ) ( getValue . ) ( nextEntry . base return { ) (  V
public void ReadInternal(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { int after = bufferStart + bufferPosition + len; if (after > length) throw new EndOfStreamException("read past EOF: " + this); bufferPosition = 0; bufferLength = 0; bufferStart = after; } } }
TagQueueResult TagQueue(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
} ; ) ( NotSupportedException new throw { ) ( void
} ; ) request ( executeModifyCacheSubnetGroup return ; ) request ( beforeClientExecution = request { ) request ModifyCacheSubnetGroupRequest (  CacheSubnetGroup
void setParams(String params) {     String language = "", country = "", variant = "";     StringTokenizer st = new StringTokenizer(params, ",");     if (st.hasMoreTokens()) language = st.nextToken();     if (st.hasMoreTokens()) country = st.nextToken();     if (st.hasMoreTokens()) variant = st.nextToken();     super.setParams(); }
return ExecuteDeleteDocumentationVersion(BeforeClientExecution(request));
boolean equals(Object obj) {     if (!(obj instanceof FacetLabel)) {         return false;     }     FacetLabel other = (FacetLabel) obj;     if (length != other.length) {         return false;     }     for (int i = length - 1; i >= 0; i--) {         if (!components[i].equals(other.components[i])) {             return false;         }     }     return true; }
GetInstanceAccessDetailsResult executeGetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails(request); }
} ; shape return ; ) shape ( onCreate ; ) shape ( Add . shapes ; ) anchor ( SetAnchor . shape ; ) this ( SetParent . shape ; ) anchor , this ( HSSFPolygon new = shape HSSFPolygon { ) anchor HSSFChildAnchor ( HSSFPolygon
return getBoundSheetRec(sheetIndex).getSheetname();
GetDashboardResult executeGetDashboard(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard(request); }
} ; ) request ( ExecuteAssociateSigninDelegateGroupsWithAccount return ; ) request ( BeforeClientExecution = request { ) request AssociateSigninDelegateGroupsWithAccountRequest (  AssociateSigninDelegateGroupsWithAccountResult
void (MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((mbr.GetFirstColumn() + j)); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string (string string) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = string.IndexOf("\\E", apos)) >= 0) { sb.Append(string.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(string.Substring(apos)).Append("\\E").ToString(); }
} ; ) ( NotSupportedException new throw { ) value ( byte[]
public ArrayPtg(Object[][] values2d) { _nColumns = values2d[0].Length; _nRows = values2d.Length; int nRows = _nRows; int nColumns = _nColumns; Object[] vv = new Object[_nRows * _nColumns]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
return executeGetIceServerConfig(beforeClientExecution(request));
return GetType().Name + "[" + GetValueAsString() + "]";
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public void IncrementAndGet() { refCount.Increment(); }
return ExecuteUpdateConfigurationSetSendingEnabled(request = BeforeClientExecution(request));
} ; INT_SIZE . LittleEndianConsts * ) ( getXBATEntriesPerBlock return { ) (
void (pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
String toString() { StringBuilder b = new StringBuilder(); int l = length(); b.append(Path.DirectorySeparatorChar); for(int i = 0; i < l; i++) { b.append(getComponent(i)); if(i < l - 1) { b.append(Path.DirectorySeparatorChar); } } return b.toString(); }
InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.setRoleName(roleName); return this; };
// Invalid/corrupted source code - cannot translate
} } ; ) ( parseEntry ) ) ( eof ! ( if ; 0 = ptr { ) ) ( first ! ( if { ) ( void
} ; ) ( InvalidOperationException new throw } ; ) ( previous . iterator return { ) start >= ) ( previousIndex . iterator ( if { ) (  E
} ; newPrefix . this return { ) (  string
{ ( value ) for ( i = 0 ; i < mSize ; i ++ ) if ( mValues [ i ] == value ) return i ; return - 1 ; }
CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase); List<CharsRef> deduped = new ArrayList<>(); List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped;
GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
void (pos) { currentBlockIndex = (pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (pos & blockMask); }
} ; s return ; s += ptr ; ) ) n , 0 ( Max . Math , ) ( available ( Min . Math ) ( = s { ) n (
} ; ) bootstrapActionConfig ( SetBootstrapActionConfig { ) bootstrapActionConfig BootstrapActionConfig ( BootstrapActionDetail
void Serialize(ILittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte(field_7_padding.IntValue()); } }
return string.LastIndexOf(string, count);
} ; ) object ( addLastImpl return { ) object E (  bool
void (String section, String subsection) { ConfigSnapshot, do { src = state.get(); res = unsetSection(src, section, subsection); } while (!(state.compareAndSet(src, res))); }
public string GetTagName() { return tagName; }
subrecords.Insert(index, element);
lock(mutex) { return delegate.Remove(o); }
return new DoubleMetaphoneFilter(input, inject, maxCodeLength);
} ; ) ( inCoreLength return { ) (
} ; newValue = value { ) newValue bool (  void
} ; newSource = newSource . this ; oldSource = oldSource . this { ) newSource ContentSource , oldSource ContentSource ( Pair
if (i >= count) throw new IndexOutOfRangeException(); return entries[i];
: base("cr", "CreateRepo", "2016-06-07", "cr").SetMethod(MethodType.PUT, "/repos")
public bool DeltaBaseAsOffset { get; return deltaBaseAsOffset; }
public void Remove() { if (expectedModCount != list.modCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); if (lastLink != null) { Link<ET> previous = lastLink.previous; Link<ET> next = lastLink.next; if (previous != null) previous.next = next; if (next != null) next.previous = previous; } pos--; lastLink = null; expectedModCount++; list.size--; list.modCount++; }
} ; ) request ( ExecuteMergeShards return ; ) request ( BeforeClientExecution = request { ) request MergeShardsRequest (  MergeShardsResult
return executeAllocateHostedConnection(request = beforeClientExecution(request)); AllocateHostedConnectionResult (AllocateHostedConnectionRequest request) {
} ; start return { ) (
} ; ) false , query ( getTerms return { ) query Query (  ] [ WeightedTerm readonly static public
} ; ) ( InvalidOperationException new throw { ) (  Buffer
void method(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { byte byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >>> 2; byte byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); byte byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6); values[valuesOffset++] = byte2 & 63; } }
if (s == null) throw new ArgumentException(); string[] elements; if (s.Equals("") || s.Equals("/")) { s = GetPath(); } if (s.Equals("")) { s = GetHost(); } if ("file".Equals(scheme) || LOCAL_FILE.Matches(s)) { elements = s.Split(new char[] { '\\', Path.DirectorySeparatorChar, '/' }); } else { elements = s.Split(new string[] { "/+" }, StringSplitOptions.None); } if (elements.Length == 0) throw new ArgumentException(); string result; if (Constants.DOT_GIT.Equals(result)) { result = elements[elements.Length - 1]; } else { if (result.EndsWith(Constants.DOT_GIT_EXT)) { result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); } } return result;
return executeDescribeNotebookInstanceLifecycleConfig(request = beforeClientExecution(request)); DescribeNotebookInstanceLifecycleConfigResponse (DescribeNotebookInstanceLifecycleConfigRequest request) {
} ; accessKeySecret . this return { ) (  string
CreateVpnConnectionResult CreateVpnConnection(CreateVpnConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
return executeDescribeVoices(request = beforeClientExecution(request)); } DescribeVoicesResult (DescribeVoicesRequest request) {
return ExecuteListMonitoringExecutions(request = BeforeClientExecution(request)); } ListMonitoringExecutionsResponse ListMonitoringExecutionsRequest request
DescribeJobRequest(string vaultName, string jobId) { setVaultName(vaultName); setJobId(jobId); }
return escherRecords[index];
} ; ) request ( ExecuteGetApis return ; ) request ( BeforeClientExecution = request { ) request GetApisRequest (  GetApisResult
return ExecuteDeleteSmsChannel(request = BeforeClientExecution(request)); DeleteSmsChannelResult(DeleteSmsChannelRequest request)
} ; trackingRefUpdate return { ) (  TrackingRefUpdate
void Method(bool b) { Console.Write(Convert.ToString(b)); }
// Cannot translate: Invalid/corrupted Java syntax
} ; workdirTreeIndex = index . this { ) workdirTreeIndex ( NotIgnoredFilter
} ; ) ( ReadShort . @in = field_1_formatFlags { ) @in RecordInputStream ( AreaRecord
base(HTTPS.ProtocolType, "cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { } GetThumbnailRequest
} ; ) request ( ExecuteDescribeTransitGatewayVpcAttachments return ; ) request ( BeforeClientExecution = request { ) request DescribeTransitGatewayVpcAttachmentsRequest (  DescribeTransitGatewayVpcAttachmentsResult
PutVoiceConnectorStreamingConfigurationResult executePutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
return prefixToOrdRange[dim]; } OrdRange(string dim) {
} ; ) symbol , ) ( Name . GetType ( ) . LexerNoViableAltException , "%s('%s')" , ) ( CurrentCulture . CultureInfo ( Format . string return } ; ) false , symbol ( EscapeWhitespace . Utils = symbol ; ) ) startIndex , startIndex ( Of . Interval ( GetText . ) ( GetInputStream = symbol { ) ) ( Size . ) ( GetInputStream < startIndex && 0 >= startIndex ( if ; "" = symbol string { ) (  string
return peekFirstImpl();
} ; ) request ( ExecuteCreateWorkspaces return ; ) request ( BeforeClientExecution = request { ) request CreateWorkspacesRequest (  CreateWorkspacesResult
} ; ) ( copy return { ) (  NumberFormatIndexRecord
return ExecuteDescribeRepositories(beforeClientExecution(request));
} ; 0 = mSize ; ] initialCapacity [ new = mValues ; ] initialCapacity [ new = mKeys ; ) initialCapacity ( idealIntArraySize . ArrayUtils = initialCapacity { ) initialCapacity ( SparseIntArray public
} ; ) input ( HyphenatedWordsFilter new return { ) input TokenStream (  HyphenatedWordsFilter
return executeCreateDistributionWithTags(beforeClientExecution(request)); } CreateDistributionWithTagsResult (CreateDistributionWithTagsRequest request) {
new FileStream(string fileName, FileMode mode)
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string (value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
} ; ) request ( ExecuteUpdateDistribution return ; ) request ( BeforeClientExecution = request { ) request UpdateDistributionRequest (  UpdateDistributionResult
} ; ) b , index ( CustomColor new : null ? ) null == b ( return ; ) index ( GetColor . _palette = b ] [ } ; ) ( GetColor . AUTOMATIC . HSSFColorPredefined return { ) ) ( GetIndex . AUTOMATIC . HSSFColorPredefined == index ( if { ) index (  HSSFColor
throw new NotImplementedException(); } (int srcRow, int srcCol, ValueEval[][] operands) _functionName {
void Method(LittleEndianOutput out) { out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index); }
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} ; fontIndex = _fontIndex . this ; character = _character . this { ) fontIndex , character ( FormatRun
public static byte[] method(char[] chars, int offset, int length) { byte[] result = new byte[2 * length]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return executeUploadArchive(request = beforeClientExecution(request)); } UploadArchiveResult (UploadArchiveRequest request) {
return List<Token> GetHiddenTokensToLeft(tokenIndex) { tokenIndex - 1 };
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (this.GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; IEnumerator<SpanQuery> sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries);
} ; ) repo ( StashCreateCommand new return { ) (  StashCreateCommand
FieldInfo GetFieldByName(string fieldName) { return fieldName.get(); }
} ; ) request ( ExecuteDescribeEventSource return ; ) request ( BeforeClientExecution = request { ) request DescribeEventSourceRequest (  DescribeEventSourceResult
return ExecuteGetDocumentAnalysis(BeforeClientExecution(request));
return executeCancelUpdateStack(request = beforeClientExecution(request)); CancelUpdateStackResult CancelUpdateStackRequest
} ; ) request ( ExecuteModifyLoadBalancerAttributes return ; ) request ( BeforeClientExecution = request { ) request ModifyLoadBalancerAttributesRequest (  ModifyLoadBalancerAttributesResult
return ExecuteSetInstanceProtection(BeforeClientExecution(request)); } SetInstanceProtectionResult(SetInstanceProtectionRequest request) {
} ; ) request ( ExecuteModifyDBProxy return ; ) request ( BeforeClientExecution = request { ) request ModifyDBProxyRequest (  ModifyDBProxyResult
void method(output, offset, len, endOffset, posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; ++count; }
; ) HttpsProtocolType (  ; ) "cloudphoto" , "FetchLibraries" , "2017-07-11" , "CloudPhoto" ( base { ) ( FetchLibrariesRequest
} ; ) stcejbo ( stisxE . eliF nruter { ) (  loob
} ; out = out . this { ) out Stream ( Stream
: base("CS", "2015-12-15", "ScaleCluster", "csk") { setMethod("/clusters/[ClusterId]"); setMethod(MethodType.PUT); }
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
return executeListObjectParentPaths(request = beforeClientExecution(request));
return executeDescribeCacheSubnetGroups(request = beforeClientExecution(request));
void SetShortBoolean(bool flag) { sharedFormula = field_5_options; }
} ; reuseObjects return { ) ( bool
ErrorNode (Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent(this); return t; }
if (!args.isEmpty()) { throw new ArgumentException("Unknown parameters: " + args); }
return ExecuteRemoveSourceIdentifierFromSubscription(BeforeClientExecution(request)); // EventSubscription RemoveSourceIdentifierFromSubscriptionRequest request
public static TokenFilterFactory(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS);
public GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
return new Binary(clone.a, clone.b);
return o is ArmenianStemmer;
} ; ) ( protectedHasArray return { ) ( bool sealed public
return executeUpdateContributorInsights(request = beforeClientExecution(request));
} ; null = writeProtect ; null = fileShare ; ) writeProtect ( remove . records ; ) fileShare ( remove . records { ) (  void
SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base(analyzer) { this.expand = expand; this.dedup = dedup; }
return ExecuteRequestSpotInstances(request = BeforeClientExecution(request));
} ; ) ( GetObjectData . ) ( FindObjectRecord return { ) (  ] [
return ExecuteGetContactAttributes(request = BeforeClientExecution(request)); GetContactAttributesResult GetContactAttributesRequest
return getKey() + ": " + getValue();
} ; ) request ( ExecuteListTextTranslationJobs return ; ) request ( BeforeClientExecution = request { ) request ListTextTranslationJobsRequest (  ListTextTranslationJobsResult
return ExecuteGetContactMethods(request = BeforeClientExecution(request)); } GetContactMethodsResult(GetContactMethodsRequest request) {
public static FunctionMetadata fd = getInstance.getFunctionByNameInternal(name); if (fd == null) { fd = getInstanceCetab().getFunctionByNameInternal(name); } if (fd == null) { return -1; } return fd.getIndex();
return executeDescribeAnomalyDetectors(request = beforeClientExecution(request));
public static string methodName(string message, ObjectId changeId) { return insertId(message, changeId, false); }
public void Method(AnyObjectId objectId, int typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException { int sz = db.getObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.copy(), JGitText.get().unknownObjectType2); throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
} ; ) request ( ExecuteImportInstallationMedia return ; ) request ( BeforeClientExecution = request { ) request ImportInstallationMediaRequest (  ImportInstallationMediaResult
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
} ; ) ) ( ReadDouble . in ( { ) in LittleEndianInput ( NumberPtg
GetFieldLevelEncryptionConfigResult executeGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution(request); return executeGetFieldLevelEncryptionConfig(request); }
} ; ) request ( executeDescribeDetector return ; ) request ( beforeClientExecution = request { ) request DescribeDetectorRequest (  DescribeDetectorResult
return ExecuteReportInstanceStatus(BeforeClientExecution(request));
} ; ) request ( executeDeleteAlarm return ; ) request ( beforeClientExecution = request { ) request DeleteAlarmRequest (  DeleteAlarmResult
return new PortugueseStemFilter(input);
; ] ENCODED_SIZE [ new = reserved { ) ( FtCblsSubRecord
override public bool remove(Object obj) { lock(mutex) { return c.remove(obj); } }
GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
return ">= _p" + precedence;
} ; ) request ( ExecuteListStreamProcessors return ; ) request ( BeforeClientExecution = request { ) request ListStreamProcessorsRequest (  ListStreamProcessorsResponse
DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { setLoadBalancerName(loadBalancerName); setPolicyName(policyName); }
} ; options = _options { ) options ( WindowProtectRecord
UnbufferedCharStream(int bufferSize) { n = 0; data = new char[bufferSize]; }
} ; ) request ( executeGetOperations return ; ) request ( beforeClientExecution = request { ) request GetOperationsRequest (  GetOperationsResult
void Method(byte[] b, int o) { NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
return ExecuteStopWorkspaces(request = BeforeClientExecution(request)); StopWorkspacesResult StopWorkspacesRequest
void method() /*throws IOException*/ { if (isOpen) { isOpen = false; try { dump(); try { channel.truncate(fileLength); } finally { try { channel.close(); } finally { fos.close(); } } } } }
return ExecuteDescribeMatchmakingRuleSets((request = BeforeClientExecution(request)));
} ; null return { ) len , off , ] [ surface , wordId (  string
// Cannot translate: invalid Java syntax
public static double r = double.NaN; if (v != null && v.length >= 1) { m = 0; s = 0; n = v.length; for (i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r;
return executeDescribeResize(request = beforeClientExecution(request));
public bool PassedThroughNonGreedyDecision { get; }
} ; ) 0 ( end return { ) (
} } } ; ) ctx , currentCell ( OnCell . handler ; ) ) 1 + firstColumn - colNumber . ctx ( , rowSize ( AddAndCheck . ArithmeticUtils = ordinalNumber . ctx ; ) width ) ( , ) firstRow , rowNumber . ctx ( SubAndCheck . ArithmeticUtils ) ( ( MulAndCheck . ArithmeticUtils = rowSize } ; continue { ) traverseEmptyCells ! && ) currentCell ( IsEmpty ( if } ; continue { ) null == currentCell ( if ; ) colNumber . ctx ( GetCell . currentRow = currentCell { ) colNumber . ctx ++ ; lastColumn <= colNumber . ctx ; firstColumn = colNumber . ctx ( for } ; continue { ) null == currentRow ( if ; ) rowNumber . ctx ( GetRow . sheet = currentRow { ) rowNumber . ctx ++ ; lastRow <= rowNumber . ctx ; firstRow = rowNumber . ctx ( for ; null = currentCell Cell ; null = currentRow Row ; ) ( SimpleCellWalkContext new = ctx SimpleCellWalkContext ; 1 + firstColumn - lastColumn = width ; ) ( GetLastColumn . range = lastColumn ; ) ( GetFirstColumn . range = firstColumn ; ) ( GetLastRow . range = lastRow ; ) ( GetFirstRow . range = firstRow { ) handler CellHandler (  void
} ; pos return { ) (
if (this.boost == other.boost) return this.bytes.get().compareTo(other.bytes.get()); return other.boost.compareTo(this.boost);
for (i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; } } return len; }
void Method(LittleEndianOutput out) { out.WriteShort(_options); }
} ; exactOnly = exactOnly . this { ) exactOnly bool ( DiagnosticErrorListener
KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType); ToString(); }
} ; ) request ( executeGetAssignment return ; ) request ( beforeClientExecution = request { ) request GetAssignmentRequest (  GetAssignmentResult
} ; 1 - != ) id ( findOffset return { ) id AnyObjectId (  bool
} ; this return ; allGroups = allGroups . this { ) allGroups bool (  GroupingSearch
public void synchronized(string dimName, bool v) { lock(this) { DimConfig ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
{ foreach(char c in cells.Keys) { char e = Cell.At(c); if(e >= 0) { size++; } } return size; }
return ExecuteDeleteVoiceConnector(BeforeClientExecution(request));
return ExecuteDeleteLifecyclePolicy(request = BeforeClientExecution(request));
