(LittleEndianOutput out) => out.WriteShort();
void Process<T>(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.tailBlock, 0, src.tailBlkIdx); }
void Add(byte b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry; }
() => (termOffsets?.RamBytesUsed() ?? 0) + (termsDictOffsets?.RamBytesUsed() ?? 0);
public string String() { byte[] raw = buffer; msgB = RawParseUtils.TagMessage; if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
_header.BATCount = 1; _header.BATArray = new int[] { 1 }; BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.OurBlockIndex = 1; _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.StartBlock = 0;
slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address;
public SubmoduleAddCommand SubmoduleAddCommand(string path) { this.path = path; return this; }
ListIngestionsResult ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { ; SwitchTo(lexState); }
GetShardIteratorResult GetShardIteratorResult(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator; }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public bool Ready() { lock (lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || in.Position < in.Length; } catch { return false; } } }
public EscherOptRecord() { }
lock (this) { if (buffer == null) { throw new NullReferenceException("buffer == null"); } if (offset < 0 || length < 0 || length > buffer.Length - offset) { throw new IndexOutOfRangeException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } return copylen; }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Method(string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
V V() { return base.nextEntry.GetValue(); }
public void Read(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) System.Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { System.Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { System.Array.Copy(buffer, 0, b, offset, bufferLength); throw new System.IO.EndOfStreamException("read past EOF: " + this); } else { System.Array.Copy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new System.IO.EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult TagQueueResult(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
() => throw new NotSupportedException();
CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; string[] parts = @params.Split(','); if (parts.Length > 0) language = parts[0]; if (parts.Length > 1) country = parts[1]; if (parts.Length > 2) variant = parts[2]; }
public DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution; ExecuteDeleteDocumentationVersion(request); }
if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (i = length - 1; ; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true;
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails; }
public HSSFPolygon HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape; }
string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).Sheetname;
GetDashboardResult GetDashboardResult(GetDashboardRequest request) { request = BeforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = BeforeClientExecution; return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); }
System.Text.StringBuilder sb = new System.Text.StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = @string.IndexOf("\\E", apos)) >= 0) { sb.Append(@string.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(@string.Substring(apos)).Append("\\E").ToString();
ByteBuffer(byte[] value) { throw new NotSupportedException(); }
ArrayPtg(object[][] values2d) { nColumns = values2d[0].Length; nRows = values2d.Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution; return ExecuteGetIceServerConfig(request); }
public override string ToString() => $"{GetType().Name} [{GetValueAsString()}]";
public override string ToString() => $"ToChildBlockJoinQuery ({parentQuery})";
Interlocked.Increment(ref refCount);
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return ExecuteUpdateConfigurationSetSendingEnabled(request); }
() { return GetXBATEntriesPerBlock() * ; }
void Pow10(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(tp._divisor, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
public string ToPathString() { System.Text.StringBuilder b = new System.Text.StringBuilder(); int l = Length; b.Append(System.IO.Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(System.IO.Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); }
void MethodName(ProgressMonitor pm) {}
void Method() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
public E Previous() { if (iterator.PreviousIndex >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
string MethodName() { return null; }
for (i = 0; i < mSize; i++) if (mValues[i] == value) return -1;
var stems = Stem(word, length); if (stems.Count < 2) { return stems; } var terms = new CharArraySet(8, dictionary.IgnoreCase); var deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped;
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return executeGetGatewayResponses; }
void SetPosition(int pos) { currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask; }
{ s = Math.Min(available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte((byte)field_7_padding.Value); }
(string @string) => { return lastIndexOf; }
boolean (  E object ) { return addLastImpl ; }
public void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = _state; res = UnsetSection(src, section, subsection); } while (System.Threading.Interlocked.CompareExchange(ref _state, res, src) != src); }
string TagName { get { return tagName; } }
subrecords.Insert(index, element);
() => { lock (mutex) { return delegate().Remove(o); } }
public DoubleMetaphoneFilter(TokenStream input) : base(input) { }
() => inCoreLength;
void MethodName(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
(int i) { if () throw new IndexOutOfRangeException("Array index out of range: " + i); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { setMethod(); }
bool MethodName() { }
void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { var next = lastLink.next; var previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.Count--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
MergeShardsResult MergeShardsResult() { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() => {};
public static WeightedTerm WeightedTerm(Query query) => GetTerms(query, false);
ByteBuffer() { throw new NotSupportedException(); }
void Translate(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
string s = GetPath(); if (s == "/" || s == "") s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file" == scheme || LOCAL_FILE.IsMatch(s)) elements = Regex.Split(s, "[\\\\" + Path.DirectorySeparatorChar + "/]"); else elements = Regex.Split(s, "/+"); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT == result) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result;
DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
string MethodName() { return null; }
request = beforeClientExecution(request); return executeCreateVpnConnection(request);
public DescribeVoicesResult DescribeVoices() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
ListMonitoringExecutionsResult ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return executeListMonitoringExecutions; }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord this[int index] => escherRecords[index];
GetApisResult GetApisResult(GetApisRequest request) { request = beforeClientExecution; return executeGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
public TrackingRefUpdate() { }
void Method() { Console.Write(b.ToString()); }
QueryNode() => GetChildren[0];
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) => this.workdirTreeIndex = workdirTreeIndex;
AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
new GetThumbnailRequest(ProtocolType.Https);
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) => ExecuteDescribeTransitGatewayVpcAttachments(BeforeClientExecution(request));
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange OrdRange() => prefixToOrdRange[dim];
string symbol = ""; if (startIndex >= 0 && startIndex < InputStream.Size) symbol = Utils.EscapeWhitespace(InputStream.GetText(new Interval(startIndex, startIndex)), false); return $"{nameof(LexerNoViableAltException)}('{symbol}')";
E E() => peekFirstImpl;
CreateWorkspacesResult CreateWorkspacesResult() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public NumberFormatIndexRecord NumberFormatIndexRecord() => copy;
public DescribeRepositoriesResult DescribeRepositoriesResult() { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter() : this(input) { }
public CreateDistributionWithTagsResult CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { new System.IO.FileInfo(fileName); }
public DeleteWorkspaceImageResult DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHexString(long value) { return value.ToString("X16"); }
UpdateDistributionResult(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return executeUpdateDistribution; }
public HSSFColor HSSFColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); } byte[] b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(); }
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index);
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] Convert(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
UploadArchiveResult UploadArchiveResult(UploadArchiveRequest request) { request = BeforeClientExecution; return ExecuteUploadArchive(request); }
IList GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (!object.Equals(term, other.term)) return false; return true; }
var spanQueries = weightBySpanQuery.Select(kvp => kvp.Value != 1f ? new SpanBoostQuery(kvp.Key, kvp.Value) : kvp.Key).ToArray(); return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries);
StashCreateCommand StashCreateCommand() => new StashCreateCommand();
FieldInfo FieldInfo() => byName[fieldName];
DescribeEventSourceResult(DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
public GetDocumentAnalysisResult GetDocumentAnalysisResult() { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
CancelUpdateStackResult() { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult SetInstanceProtection() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
ModifyDBProxyResult ModifyDBProxyResult(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return executeModifyDBProxy; }
if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++;
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool IsExisting() => fs.Exists;
public FilterOutputStream(Stream @out) { this.@out = @out; }
new ScaleClusterRequest("/clusters/[ClusterId]") { Method = MethodType.PUT };
DVConstraint DataValidationConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult ListObjectParentPathsResult() { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups; }
field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
private bool MethodName() { }
ErrorNodeImpl ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); }
EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory CreateInstance(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet; }
Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool Boolean(object o) { return false; }
bool HasArray() => ProtectedHasArray();
UpdateContributorInsightsResult UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void Method() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
RequestSpotInstancesResult RequestSpotInstances() { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
() => findObjectRecord.GetObjectData();
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = BeforeClientExecution; return ExecuteGetContactAttributes(request); }
public override string ToString() => $"{GetKey}: {GetValue()}";
public ListTextTranslationJobsResult ListTextTranslationJobsResult() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
GetContactMethodsResult() { request = beforeClientExecution(request); return executeGetContactMethods(request); }
FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex();
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return ExecuteDescribeAnomalyDetectors(request); }
public static string MethodName(string message, ObjectId changeId) { return insertId; }
{ long sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
ImportInstallationMediaResult ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
NumberPtg() { @in.ReadDouble(); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult DescribeDetectorResult(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector; }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution; return ExecuteReportInstanceStatus(request); }
DeleteAlarmResult DeleteAlarmResult(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new ;
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string MethodName() { return null; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(object options) { }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
public GetOperationsResult GetOperationsResult(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations; }
void Encode(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
public String(int wordId, char[] surface, int off, int len) { }
public String() { }
public static double M(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0, s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
public bool PassedThroughNonGreedyDecision => passedThroughNonGreedyDecision;
() => end;
firstRow = range.FirstRow; lastRow = range.LastRow; firstColumn = range.FirstColumn; lastColumn = range.LastColumn; width = lastColumn - firstColumn + 1; var ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } var rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width); ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } }
( ) { }
if (this.boost == other.boost) return other.bytes.Get().CompareTo(this.bytes.Get()); else return float.Compare(this.boost, other.boost);
{ for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
(LittleEndianOutput out) => out.WriteShort();
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
public GetAssignmentResult GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool Contains(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out DimConfig ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.MultiValued = v; } }
{ return cells.Keys.Count(c => at(c).cmd >= 0); }
public DeleteVoiceConnectorResult DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution; return ExecuteDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
