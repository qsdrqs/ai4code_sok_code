public void WriteShort(LittleEndianOutput output) { output.WriteShort(field_1_vcenter); }
void BlockList<T>(T src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx;) { src.directory[srcDirIdx].addAll(0, BLOCK_SIZE); srcDirIdx++; } if (src.tailBlkIdx != 0) { tailBlock.src.addAll(0, src.tailBlkIdx); } }
void Method(object b) { if (upto == blockSize) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new object[blockSize]; upto = 0; } currentBlock[upto++] = b; }
} ; objectId return { ) (  ObjectId
DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0);
public string final() { byte[] raw = buffer; byte[] msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.decode(guessEncoding(raw, msgB), msgB, raw.length); }
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); _property_table.SetStartBlock(0);
void(address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; address = offset0; assert upto < slice.length; }
} ; this return ; path = path . this { ) path String (  SubmoduleAddCommand
return executeListIngestions(request = beforeClientExecution(request)); ListIngestionsResult ListIngestionsRequest
QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
} ; ) request ( executeGetShardIterator return ; ) request ( beforeClientExecution = request { ) request GetShardIteratorRequest (  GetShardIteratorResult
; ) POST . MethodType (  ; ) "vipaegis" , "ModifyStrategy" , "2016-11-11" , "aegis" ( base { ) ( ModifyStrategyRequest
public bool Ready() { lock (lockObject) { if (stream == null) throw new IOException("StreamReader is closed"); try { return bytes.HasRemaining || stream.DataAvailable; } catch { return false; } } }
return new EscherOptRecord();
public synchronized int read(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer == null"); } Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = (count - pos < length) ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer.charAt(pos + i); } pos += copylen; return copylen; }
// Cannot translate - source Java code is malformed and syntactically invalid
} ; ) ) null ) Object ( ( valueOf . String : str ? null != str ( write { ) str String (  void
public class NotImplementedFunctionException : NotImplementedException { public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { } }
} ; ) ( getValue . ) ( nextEntry . super return { ) (  V
public final void readInternal(byte[] b, int offset, int len, boolean useBuffer) throws IOException { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (len < bufferLength) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new EOFException("read past EOF: " + this); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { int after = bufferStart + bufferPosition + len; if (after > length) throw new EOFException("read past EOF: " + this); readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult ExecuteTagQueue(TagQueueRequest request) { request = BeforeClientExecution(request); return ExecuteTagQueue(request); }
throw new NotSupportedException();
return executeModifyCacheSubnetGroup(request = beforeClientExecution(request));
void setParams(string params) { string language = "", country = "", variant = ""; string[] tokens = params.Split(','); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; base.setParams(params); }
return executeDeleteDocumentationVersion(request = beforeClientExecution(request));
public override bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
return executeGetInstanceAccessDetails(beforeClientExecution(request));
HSSFPolygon shape = new HSSFPolygon(anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); shape.OnCreate(); return shape;
return getBoundSheetRec(sheetIndex).getSheetname();
GetDashboardResult executeGetDashboard(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard(request); }
return executeAssociateSigninDelegateGroupsWithAccount(request = beforeClientExecution(request)); AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { }
void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn(mbr.GetFirstColumn() + j); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string Quote(string input) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = input.IndexOf("\\E", apos)) >= 0) { sb.Append(input.Substring(apos, k - apos + 2)); sb.Append("\\E\\Q"); apos = k + 2; } sb.Append(input.Substring(apos)); sb.Append("\\E"); return sb.ToString(); }
throw new InvalidOperationException();
ArrayPtg(Object[][] values2d) { nColumns = values2d[0].Length; nRows = values2d.Length; _nColumns = nColumns; _nRows = nRows; Object[] vv = new Object[_nRows * _nColumns]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
return executeGetIceServerConfig(request = beforeClientExecution(request)); GetIceServerConfigResult(GetIceServerConfigRequest request) {
return getClass().getName() + " [" + getValueAsString() + "]";
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
} ; ) ( incrementAndGet . refCount { ) (  void final public
return executeUpdateConfigurationSetSendingEnabled((request = beforeClientExecution(request)) UpdateConfigurationSetSendingEnabledRequest); UpdateConfigurationSetSendingEnabledResult
} ; INT_SIZE . LittleEndianConsts * ) ( getXBATEntriesPerBlock return { ) (
void pow10(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
string toString() { StringBuilder b = new StringBuilder(); int l = length(); b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(getComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
InstanceProfileCredentialsProvider ECSMetadataServiceCredentialsFetcher fetcher this fetcher fetcher this setRoleName roleName return this
} ; pm = progressMonitor { ) pm ProgressMonitor (  void
} } ; ) ( parseEntry ) ) ( eof ! ( if ; 0 = ptr { ) ) ( first ! ( if { ) (  void
} ; ) ( NoSuchElementException new throw } ; ) ( previous . iterator return { ) start >= ) ( previousIndex . iterator ( if { ) (  E
} ; newPrefix . this return { ) (  String
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1;
List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped;
GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
void pos() { currentBlockIndex = (pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (pos & blockMask); }
} ; s return ; s += ptr ; ) ) n , 0 ( Max . Math , ) ( available ( Min . Math ) ( = s { ) n (
} ; ) bootstrapActionConfig ( setBootstrapActionConfig { ) bootstrapActionConfig BootstrapActionConfig ( BootstrapActionDetail
void WriteToOutput(LittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort(field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, output); } else { StringUtil.PutCompressedUnicode(field_6_author, output); } if (field_7_padding != null) { foreach (byte b in field_7_padding) { output.WriteByte(b); } } }
// Invalid/malformed code - cannot be translated
} ; ) object ( addLastImpl return { ) object E (  boolean
void ConfigSnapshot(string section, string subsection) { ConfigSnapshot src; ConfigSnapshot res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
} ; tagName return { ) ( string final public
void Add(int index, SubRecord element) { subrecords.Insert(index, element); }
lock (mutex) { return delegate.Remove(o); }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
} ; ) ( inCoreLength return { ) (
} ; newValue = value; ) newValue bool ( void
} ; newSource = newSource . this ; oldSource = oldSource . this { ) newSource ContentSource , oldSource ContentSource ( Pair
if (i <= count) throw new IndexOutOfRangeException(); return entries[i];
base("cr", "CreateRepo", "2016-06-07", "cr").setMethod(MethodType.PUT).setEndpoint("/repos");
} ; deltaBaseAsOffset return { ) (  boolean
if (expectedModCount == list.modCount) { if (lastLink != null) { Link<ET> previous = lastLink.previous; Link<ET> next = lastLink.next; if (previous != null) previous.next = next; if (next != null) next.previous = previous; pos--; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); }
return executeMergeShards(request = beforeClientExecution(request)); MergeShardsResult MergeShardsRequest
return executeAllocateHostedConnection(request = beforeClientExecution(request)); AllocateHostedConnectionResult(AllocateHostedConnectionRequest request)
} ; start return { ) (
} ; ) false , query ( getTerms return { ) query Query (  ] [ WeightedTerm final static public
throw new ReadOnlyException();
void Method(byte[] blocks, int blocksOffset, byte[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { byte byte0 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)(byte0 >> 2); byte byte1 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)(((byte0 & 3) << 4) | (byte1 >> 4)); byte byte2 = blocks[blocksOffset++]; values[valuesOffset++] = (byte)(((byte1 & 15) << 2) | (byte2 >> 6)); values[valuesOffset++] = (byte)(byte2 & 63); } }
if (s == null) throw new ArgumentException(); string path; if (s.Equals("/") || s.Equals("")) { s = GetPath(); s = GetHost(); } string[] elements; if ("file".Equals(scheme) || Matcher(s).Matches()) { elements = s.Split(new string[] { "\\" + File.SeparatorChar + "[/]" }, StringSplitOptions.None); } else { elements = s.Split(new string[] { "/+" }, StringSplitOptions.None); } if (elements.Length == 0) throw new ArgumentException(); string result; if (Constants.DOT_GIT.Equals(result)) { result = elements[elements.Length - 1]; } if (result.EndsWith(Constants.DOT_GIT_EXT)) { result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); } return result;
return executeDescribeNotebookInstanceLifecycleConfig(request = beforeClientExecution(request));
} ; accessKeySecret . this return { ) (  String
return executeCreateVpnConnection(request = beforeClientExecution(request)); CreateVpnConnectionResult CreateVpnConnectionRequest(request) { }
return executeDescribeVoices(beforeClientExecution(request)); DescribeVoicesResult(DescribeVoicesRequest request) { request = }
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
return escherRecords[index];
} ; ) request ( executeGetApis return ; ) request ( beforeClientExecution = request { ) request GetApisRequest (  GetApisResult
return executeDeleteSmsChannel((request = beforeClientExecution(request)));
} ; trackingRefUpdate return { ) (  TrackingRefUpdate
void print(bool b) { Console.Write(b.ToString()); }
} ; ) 0 ( get . ) ( getChildren return { ) (  QueryNode
// Cannot translate: Original Java code is syntactically invalid
AreaRecord(RecordInputStream @in) { field_1_formatFlags = @in.readShort(); }
: base("CloudPhoto", "GetThumbnail", "2017-07-11", "cloudphoto", ProtocolType.HTTPS) { }
DescribeTransitGatewayVpcAttachmentsResponse executeDescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
return executePutVoiceConnectorStreamingConfiguration(request = beforeClientExecution(request)); PutVoiceConnectorStreamingConfigurationResult(PutVoiceConnectorStreamingConfigurationRequest request) {
// Cannot translate - invalid Java syntax provided
return String.Format(Locale.GetDefault(), "%s('%s')", typeof(LexerNoViableAltException).Name, (startIndex >= 0 && startIndex < GetInputStream().Size()) ? Utils.EscapeWhitespace(GetInputStream().GetText(Interval.Of(startIndex, startIndex))) : "");
} ; ) ( peekFirstImpl return { ) ( E
CreateWorkspacesResult return executeCreateWorkspaces(request); request = beforeClientExecution(request); } (CreateWorkspacesRequest request) {
} ; ) ( copy return { ) (  NumberFormatIndexRecord
return executeDescribeRepositories(request = beforeClientExecution(request)); } DescribeRepositoriesResult(DescribeRepositoriesRequest request) {
SparseIntArray(int initialCapacity) {     initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity);     mKeys = new int[initialCapacity];     mValues = new int[initialCapacity];     mSize = 0; }
return new HyphenatedWordsFilter(input);
return executeCreateDistributionWithTags(request = beforeClientExecution(request));
FileStream(fileName, mode) throws FileNotFoundException
return executeDeleteWorkspaceImage(beforeClientExecution(request)); } DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) {
public static string (value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.toString(); }
UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request); }
return index == HSSFColorPredefined.AUTOMATIC.GetIndex() ? HSSFColorPredefined.AUTOMATIC.GetColor() : (b = _palette.GetColor(index)) == null ? null : new CustomColor(b, index);
ValueEval _functionName(ValueEval[][] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index);
return new DescribeDBEngineVersionsRequest();
new FormatRun(this._fontIndex, this._character);
public static byte[] EncodeChars(char[] chars, int offset, int length) { byte[] result = new byte[2 * length]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return executeUploadArchive(request = beforeClientExecution(request)); UploadArchiveResult UploadArchiveRequest
return getHiddenTokensToLeft(tokenIndex); } List<Token>
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (obj == null) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.other.Equals(other.term.other)) return false; return true; }
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Keys.Count]; IEnumerator<SpanQuery> sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries);
return new StashCreateCommand(repo);
// Cannot translate malformed Java syntax
return executeDescribeEventSource((request = beforeClientExecution(request)) => { }); DescribeEventSourceResult DescribeEventSourceRequest request;
GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request) { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
return executeCancelUpdateStack(request = beforeClientExecution(request)); CancelUpdateStackResult CancelUpdateStackRequest;
return executeModifyLoadBalancerAttributes((request = beforeClientExecution(request)));
return executeSetInstanceProtection(request = beforeClientExecution(request)); SetInstanceProtectionResult SetInstanceProtectionRequest
return executeModifyDBProxy(request = beforeClientExecution(request)); ModifyDBProxyResult ModifyDBProxyRequest(request) {
void AddEntry(object output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; ++count; }
// Cannot translate malformed Java code
} ; ) objects ( exists . fs return { ) ( bool
} ; out = out . this { ) out Stream ( Stream
: ) PUT . MethodType ( setMethod ; ) "/clusters/[ClusterId]" (  ; ) "csk" , "ScaleCluster" , "2015-12-15" , "CS" ( base { ) ( ScaleClusterRequest
return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);
ListObjectParentPathsResult executeListObjectParentPaths(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
return executeDescribeCacheSubnetGroups(request = beforeClientExecution(request)); DescribeCacheSubnetGroupsResult(DescribeCacheSubnetGroupsRequest request)
} ; ) flag , field_5_options ( setShortBoolean . sharedFormula = field_5_options { ) flag bool (  void
} ; reuseObjects return { ) (  boolean
ErrorNode t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent(this); return t;
public LatvianStemFilterFactory(IDictionary<string, string> args) { if (!args.Count == 0) { throw new ArgumentException("Unknown parameters: " + args); } }
} ; ) request ( executeRemoveSourceIdentifierFromSubscription return ; ) request ( beforeClientExecution = request { ) request RemoveSourceIdentifierFromSubscriptionRequest (  EventSubscription
public static TokenFilterFactory newInstance(string name, Map<string, string> args) { return loader.newInstance(name, args); }
base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS);
GetThreatIntelSetResult executeGetThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
} ; ) ) ( clone . b , ) ( clone . a ( Binary new return { ) (  RevFilter
return o is ArmenianStemmer;
} ; ) ( protectedHasArray return { ) ( bool final public
return executeUpdateContributorInsights(request = beforeClientExecution(request)); } UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) {
} ; null = writeProtect ; null = fileShare ; ) writeProtect ( remove . records ; ) fileShare ( remove . records { ) (  void
} ; expand = expand . this ; ) analyzer , dedup ( base { ) analyzer Analyzer , expand bool , dedup bool ( SolrSynonymParser
return executeRequestSpotInstances(request = beforeClientExecution(request)); RequestSpotInstancesResult RequestSpotInstancesRequest(request)
} ; ) ( getObjectData . ) ( findObjectRecord return { ) (  ] [
GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteGetContactAttributes(request); }
return getKey() + ": " + getValue();
return executeListTextTranslationJobs(request = beforeClientExecution(request)); ListTextTranslationJobsResult(ListTextTranslationJobsRequest request) {
return executeGetContactMethods(request = beforeClientExecution(request)); GetContactMethodsResult(GetContactMethodsRequest request) {
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); } if (fd == null) { return -1; } return fd.GetIndex(); }
return executeDescribeAnomalyDetectors(request = beforeClientExecution(request));
public static object Method(string message, ObjectId changeId) { return new { insertId = false, changeId = changeId, message = message }; }
public int getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException { int sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); } throw new MissingObjectException(objectId.Copy(), typeHint, JGitText.Get().unknownObjectType2); } return sz; }
return executeImportInstallationMedia((request = beforeClientExecution(request)) => ImportInstallationMediaResult(ImportInstallationMediaRequest(request)));
return executePutLifecycleEventHookExecutionStatus((request = beforeClientExecution(request)));
} ; ) ) ( ReadDouble . in ( { ) in LittleEndianInput ( NumberPtg
return executeGetFieldLevelEncryptionConfig(request = beforeClientExecution(request)); GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request)
return executeDescribeDetector((request = beforeClientExecution(request)));
return executeReportInstanceStatus(request = beforeClientExecution(request));
return executeDeleteAlarm(request = beforeClientExecution(request)); DeleteAlarmResult DeleteAlarmRequest
return new PortugueseStemFilter(input);
; ] ENCODED_SIZE [ new = reserved { ) ( FtCblsSubRecord
public override bool Remove(object obj) { lock(mutex) { return collection.Remove(obj); } }
return executeGetDedicatedIp(request = beforeClientExecution(request)); GetDedicatedIpResult(GetDedicatedIpRequest request) {
} ; " >= _p" + precedence return { ) (  String
return executeListStreamProcessors(request = beforeClientExecution(request)); ListStreamProcessorsResult ListStreamProcessorsRequest(request)
DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { setLoadBalancerName(loadBalancerName); setPolicyName(policyName); }
} ; options = _options { ) options ( WindowProtectRecord
UnbufferedCharStream(int bufferSize) { n = 0; data = new char[bufferSize]; }
GetOperationsResult GetOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
void method(byte[] b, int o) { NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
StopWorkspacesResult return executeStopWorkspaces(request = beforeClientExecution(request)); } (StopWorkspacesRequest request) {
if (isOpen) { isOpen = false; try { dump(); try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { try { fos.Close(); } finally { } } } } }
return executeDescribeMatchmakingRuleSets((request = beforeClientExecution(request)));
} ; null return { ) len , off , ] [ surface , wordId (  string
} ; pathStr return { ) (  String
public static double Method(double[] v) { double r; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; return r; } else { r = double.NaN; return r; } }
return executeDescribeResize(request = beforeClientExecution(request)); } DescribeResizeResult(DescribeResizeRequest request) {
} ; passedThroughNonGreedyDecision return { ) (  bool final public
} ; ) 0 ( end return { ) (
void handler(CellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (currentCell.isEmpty() && !traverseEmptyCells) { continue; } ctx.ordinalNumber = ArithmeticUtils.mulAndCheck(ArithmeticUtils.subAndCheck(ctx.rowNumber, firstRow), width) + ArithmeticUtils.addAndCheck(ctx.colNumber - firstColumn, 1); handler.onCell(currentCell, ctx); } } }
} ; pos return { ) (
if (this.boost == other.boost) return this.bytes.CompareTo(other.bytes); else return ((float)this.boost).CompareTo((float)other.boost);
for (i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; } } return len; }
} ; ) _options ( writeShort . out { ) out LittleEndianOutput (  void
} ; exactOnly = exactOnly . this { ) exactOnly boolean ( DiagnosticErrorListener
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType); }
GetAssignmentResult executeGetAssignment(GetAssignmentRequest request) { request = beforeClientExecution(request); return executeGetAssignment(request); }
// Cannot translate invalid/corrupted Java syntax
} ; this return ; allGroups = allGroups . this { ) allGroups boolean (  GroupingSearch
public synchronized void Method(string dimName, boolean v) { DimConfig ft = fieldTypes.get(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes.put(dimName, ft); } ft.multiValued = v; }
Iterator<Character> i = cells.keySet().iterator(); int size = 0; for (; i.hasNext();) { Character c = i.next(); Cell e = cells.at(c); if (e.cmd >= 0) { size++; } } return size;
return executeDeleteVoiceConnector(request = beforeClientExecution(request)); DeleteVoiceConnectorResult DeleteVoiceConnectorRequest(request) {
DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
