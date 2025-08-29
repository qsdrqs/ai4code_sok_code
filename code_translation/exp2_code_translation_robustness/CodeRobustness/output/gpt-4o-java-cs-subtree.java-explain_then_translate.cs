void (LittleEndianOutput out) { out.WriteShort(); }
void ProcessBlockList<T>(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void (b) { if () { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new [blockSize]; upto = 0; } currentBlock[upto++] = b; }
ObjectId() { }
DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry; }
() => (termOffsets != null ? termOffsets.ramBytesUsed() : 0) + (() ? termsDictOffsets.ramBytesUsed() : 0);
public string Method() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage; if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
POIFSFileSystem() { (true); _header.SetBATCount; _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void (address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < ); }
SubmoduleAddCommand(string path) { this.path = path; return this; }
ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator; }
ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool Method() { lock (lockObj) { if (inStream == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || inStream.Available > 0; } catch { return false; } } }
EscherOptRecord() { }
public int CopyToBuffer(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentOutOfRangeException(); } if (length == 0) { return 0; } int copylen = Math.Min(count - pos, length); for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } return copylen; }
OpenNLPSentenceBreakIterator(string sentenceOp) { this.sentenceOp = sentenceOp; }
void Method(string str) { Write(str ?? Convert.ToString((object)null)); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { }
object V() { return base.nextEntry.getValue(); }
public void Read(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
void MethodName() => throw new NotSupportedException();
CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
void SetParams(string params) { base.SetParams(params); language = country = variant = ""; var st = new StringTokenizer(params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return executeDeleteDocumentationVersion(request); }
bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1;; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResult(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails; }
HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
string SheetIndex(int sheetIndex) => GetBoundSheetRec(sheetIndex).Sheetname;
GetDashboardResult(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard; }
AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn(j + mbr.GetFirstColumn()); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt); InsertCell(br); } }
public static string ProcessString(string input) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = input.IndexOf("\\E", apos)) >= 0) { sb.Append(input.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(input.Substring(apos)).Append("\\E").ToString(); }
throw new ReadOnlyBufferException();
ArrayPtg(object values2d) { int nColumns = ((object[])values2d)[0].Length; int nRows = ((object[])values2d).Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = (object[])((object[])values2d)[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
GetIceServerConfigResult GetIceServerConfigResult(GetIceServerConfigRequest request) { request = beforeClientExecution; return executeGetIceServerConfig(request); }
string ToString() => GetType().FullName + " [" + GetValueAsString() + "]";
string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void () { refCount.IncrementAndGet(); }
UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
() => getXBATEntriesPerBlock() * ;
void Pow10(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
string ToString() { StringBuilder b = new StringBuilder(); int l = length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; .SetRoleName(roleName); return this; }
void MethodName(ProgressMonitor pm) { }
void () { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException(); }
String exampleMethod() { return "example"; }
(value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
List<CharsRef> ProcessWord(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } HashSet<CharsRef> terms = new HashSet<CharsRef>(new CharsRefComparer(dictionary.IgnoreCase)); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
GetGatewayResponsesResult(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses; }
void (pos) { currentBlockIndex = (pos >> blockBits); currentBlock = ; currentBlockUpto = (pos & blockMask); }
ptr += (s = Math.Min(available(), Math.Max(0, n)));
BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
void WriteTo(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); } }
(string) => lastIndexOf;
boolean someMethod(E object) {     return addLastImpl; }
void ( , string subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
string TagName() { return tagName; }
void addSubRecord(int index, SubRecord element) {     subrecords.add(index, element); }
bool Method() { lock (mutex) { return delegate().Remove(o); } }
DoubleMetaphoneFilter(TokenStream input) => new DoubleMetaphoneFilter();
() => inCoreLength;
void (bool newValue) { }
Pair(ContentSource oldSource, ) { this.oldSource = oldSource; this.newSource = newSource; }
( i ) { if ( ) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { ("/repos"); SetMethod(); }
boolean isTrue() {     return true; }
void () { if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
MergeShardsResult() { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() { }
public static WeightedTerm Query(Query query) => getTerms(query, false);
ByteBuffer() { throw new ReadOnlyBufferException(); }
void ProcessBlocks(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
string GetRepositoryName() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
DescribeNotebookInstanceLifecycleConfigResult(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
string () { return; }
CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions; }
DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
EscherRecord(int index) => escherRecords[index];
GetApisResult(GetApisRequest request) { request = beforeClientExecution; return executeGetApis(request); }
DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(); }
TrackingRefUpdate() { }
void Method() { Console.Write(b.ToString()); }
QueryNode() { return getChildren[0]; }
NotIgnoredFilter(workdirTreeIndex) => this.workdirTreeIndex = workdirTreeIndex;
AreaRecord(RecordInputStream input) { field_1_formatFlags = input.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange() => prefixToOrdRange[dim];
string Method() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
E() { return peekFirstImpl; }
CreateWorkspacesResult() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
NumberFormatIndexRecord() { return copy; }
DescribeRepositoriesResult() { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
new RandomAccessFile(string fileName, string mode) { new File(fileName); }
DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage; }
public static string MethodName(object value) { var sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
UpdateDistributionResult(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution; }
HSSFColor(index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); } byte[] b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(); }
ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedException(_functionName); }
void Method() { out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index); }
DescribeDBEngineVersionsResult() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ConvertCharsToBytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return executeUploadArchive(request); }
List GetHiddenTokensToLeft(int tokenIndex) => getHiddenTokensToLeft(tokenIndex, -1);
bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; IEnumerator sqi = weightBySpanQuery.Keys.GetEnumerator(); i = 0; while (sqi.MoveNext()) { SpanQuery sq = (SpanQuery)sqi.Current; boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); }
StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo() { return byName.Get(fieldName); }
DescribeEventSourceResult(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
CancelUpdateStackResult() => executeCancelUpdateStack(beforeClientExecution(request));
ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
ModifyDBProxyResult(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy; }
void AddOutput(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { Array.Resize(ref outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool () { return fs.exists; }
FilterOutputStream() { this.out = out; }
ScaleClusterRequest("/clusters/[ClusterId]").SetMethod(MethodType.PUT);
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups; }
void () { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool () { }
ErrorNode(Token badToken) { var t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent(); return t; }
LatvianStemFilterFactory(Dictionary<string, object> args) { base(args); if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
EventSubscription() { request = BeforeClientExecution(request); return ExecuteRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory Create( , Dictionary<string, string> args) => loader.NewInstance(name, args);
AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet; }
RevFilter() { return new Binary(a.clone(), b.clone()); }
bool MethodName(object o) { return true; }
bool MethodName() { return ProtectedHasArray(); }
UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void () { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
SolrSynonymParser(bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
RequestSpotInstancesResult { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
() => findObjectRecord.getObjectData();
GetContactAttributesResult(GetContactAttributesRequest request) { request = beforeClientExecution; return executeGetContactAttributes(request); }
string ToString() => getKey + ": " + getValue();
ListTextTranslationJobsResult() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
GetContactMethodsResult() { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution(request); return executeDescribeAnomalyDetectors(request); }
public static string (string message, ObjectId changeId) { return insertId; }
(long AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize; if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
NumberPtg() { in.ReadDouble(); }
GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector; }
ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
DeleteAlarmResult(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
TokenStream() => new PortugueseStemFilter(input);
FtCblsSubRecord() { reserved = new(); }
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string () { return ; }
ListStreamProcessorsResult(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
DeleteLoadBalancerPolicyRequest(string loadBalancerName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
WindowProtectRecord ( object options ) { }
UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
void EncodeIntegers(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o, w5); }
WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
void Method() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
string (string wordId, string[] surface, int off, int len) { }
string () { }
public static void CalculateVariance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0, s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } }
DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
bool () { return passedThroughNonGreedyDecision; }
() => end;
void ProcessCells(CellHandler handler) { int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } int rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() { }
(ScoreTerm other) => this.boost == other.boost ? other.bytes.Get().CompareTo(this.bytes.Get()) : this.boost.CompareTo(other.boost);
int ProcessCharacters(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; goto case KEHEH; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
void (LittleEndianOutput out) { out.WriteShort(); }
DiagnosticErrorListener() { this.exactOnly = exactOnly; }
KeySchemaElement(string attributeName) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool AnyObjectId(AnyObjectId id) { return findOffset(id) != ; }
GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void MethodName(string dimName, bool v) { lock (this) { DimConfig ft; if (!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
() { var i = cells.Keys.GetEnumerator(); size = 0; for (; i.MoveNext(); ) { char c = i.Current; Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
