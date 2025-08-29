writer => writer.Write
if (src.Size == 0) srcDirIdx = 0; for (; srcDirIdx < src.TailDirIdx; srcDirIdx++) AddAll(src.Directory[srcDirIdx], 0, BLOCK_SIZE); if (src.TailBlkIdx != 0) AddAll(src.TailBlock, 0, src.TailBlkIdx);
void Write(byte b) { if (currentBlock == null || upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
public DeleteDomainEntryResult GetDeleteDomainEntryResult(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry; }
=> (termOffsets?.RamBytesUsed() ?? 0) + (termsDictOffsets?.RamBytesUsed() ?? 0);
public string GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) return ""; return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
_header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0);
slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address;
public SubmoduleAddCommand SubmoduleAddCommand(string path) { this.Path = path; return this; }
ListIngestionsResult ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator; }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
public bool Ready() { lock (lockObject) { if (inputStream == null) { throw new System.IO.IOException("InputStreamReader is closed"); } try { return charBufferHasData || (inputStream as System.Net.Sockets.NetworkStream)?.DataAvailable == true; } catch { return false; } } }
public EscherOptRecord() { }
[System.Runtime.CompilerServices.MethodImpl(System.Runtime.CompilerServices.MethodImplOptions.Synchronized)] public int Read(char[] buffer, int offset, int length) { if (buffer == null) { throw new System.ArgumentNullException(nameof(buffer)); } if (offset < 0 || length < 0 || offset > buffer.Length - length) { throw new System.ArgumentOutOfRangeException(); } if (length == 0) { return 0; } int copyLen = count - pos < length ? count - pos : length; for (int i = 0; i < copyLen; i++) { buffer[offset + i] = _buffer[pos + i]; } return copyLen; }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void (string str) { Write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException innerException) : base(functionName, innerException) { this.FunctionName = functionName; }
V GetValue() => base.NextEntry.GetValue();
public sealed void ReadBytes(byte[] b, int offset, int len, bool useBuffer){ int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new System.IO.EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new System.IO.EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult TagQueueResult(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
() => throw new NotSupportedException();
CacheSubnetGroup() => ExecuteModifyCacheSubnetGroup(BeforeClientExecution(request));
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; string[] parts = @params.Split(','); if (parts.Length > 0) language = parts[0]; if (parts.Length > 1) country = parts[1]; if (parts.Length > 2) variant = parts[2]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) => ExecuteDeleteDocumentationVersion(BeforeClientExecution);
public override bool Equals(object obj) { if (obj is not FacetLabel other || length != other.length) return false; for (int i = length - 1; i >= 0; i--) if (!components[i].Equals(other.components[i])) return false; return true; }
GetInstanceAccessDetailsResult(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return executeGetInstanceAccessDetails; }
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape;
string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).Sheetname;
public GetDashboardResult GetDashboardResult(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
for (int j = 0; j < mbr.NumColumns; j++) { var br = new BlankRecord { Column = j + mbr.FirstColumn, Row = mbr.Row, XFIndex = mbr.GetXFAt(j) }; InsertCell(br); }
public static string Translate(string @string) { var sb = new System.Text.StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = @string.IndexOf("\\E", apos)) >= 0) { sb.Append(@string.Substring(apos, k + 2 - apos)).Append("\\E\\Q"); apos = k + 2; } return sb.Append(@string.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer(byte value) { throw new NotSupportedException(); }
public ArrayPtg(object[][] values2d) { nColumns = values2d[0].Length; nRows = values2d.Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution; return executeGetIceServerConfig(request); }
public override string ToString() => $"{GetType().FullName} [{GetValueAsString()}]";
public override string ToString() { return $"ToChildBlockJoinQuery ({parentQuery.ToString()})"; }
void Increment() => System.Threading.Interlocked.Increment(ref refCount);
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
{ return GetXBATEntriesPerBlock() * ; }
void ApplyPowerOf10(int pow10) { TenPower tp = TenPower.getInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
StringBuilder b = new StringBuilder(); l = length; b.Append(Path.DirectorySeparatorChar); for (i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString();
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this._fetcher = fetcher; } public InstanceProfileCredentialsProvider SetRoleName(string roleName) { this.RoleName = roleName; return this; }
void MethodName(ProgressMonitor pm) { }
void MyMethod() { if (!first) { ptr = 0; if (!Eof()) ParseEntry(); } }
if (iterator.PreviousIndex() >= start) return iterator.Previous(); throw new InvalidOperationException();
public string MethodName() { return null; }
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1;
List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) return stems; var comparer = dictionary.IgnoreCase ? CharsRefComparer.IgnoreCase : CharsRefComparer.Ordinal; var terms = new HashSet<CharsRef>(8, comparer); var deduped = new List<CharsRef>(); foreach (var s in stems) { if (terms.Add(s)) { deduped.Add(s); } } return deduped;
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return executeGetGatewayResponses; }
void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
s = Math.Min(Available(), Math.Max(0, n)); ptr += s;
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
void Serialize(BinaryWriter writer) { writer.Write(field_1_row); writer.Write(field_2_col); writer.Write(field_3_flags); writer.Write(field_4_shapeid); writer.Write((short)field_6_author.Length); writer.Write((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, writer); } else { StringUtil.PutCompressedUnicode(field_6_author, writer); } if (field_7_padding != null) { writer.Write((byte)field_7_padding.Value); } }
(string _) => lastIndexOf;
public bool MethodName(E item) => addLastImpl;
void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = Volatile.Read(ref state); res = UnsetSection(src, section, subsection); } while (Interlocked.CompareExchange(ref state, res, src) != src); }
public string TagName => tagName;
void Add(int index, SubRecord element) { subrecords.Insert(index, element); }
lock (mutex) { return @delegate().Remove(o); }
public DoubleMetaphoneFilter(TokenStream input) : base(input) {}
() => inCoreLength;
void MethodName(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
if (i < 0 || i >= entries.Count) throw new IndexOutOfRangeException(); return entries[i];
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { "/repos"; SetMethod(); }
bool MethodName() { }
if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); }
public MergeShardsResult MergeShardsResult() { request = BeforeClientExecution(request); return ExecuteMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() => { };
public static WeightedTerm WeightedTerm(Query query) { return GetTerms(query, false); }
public ByteBuffer() { throw new NotSupportedException(); }
void Decode(byte[] blocks, ref int blocksOffset, int[] values, ref int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++]; int byte1 = blocks[blocksOffset++]; int byte2 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
string s = GetPath(); if (s == "/" || string.IsNullOrEmpty(s)) s = GetHost(); if (s == null) throw new System.ArgumentException(); string[] elements = (scheme == "file" || LOCAL_FILE.IsMatch(s)) ? System.Text.RegularExpressions.Regex.Split(s, @"[\\/]") : System.Text.RegularExpressions.Regex.Split(s, "/+"); if (elements.Length == 0) throw new System.ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT == result) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result;
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfigResult(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution; return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
string MethodName() { return null; }
CreateVpnConnectionResult CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public DescribeVoicesResult DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions; }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord this[int index] => escherRecords[index];
public GetApisResult GetApis(GetApisRequest request) { request = beforeClientExecution; return ExecuteGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
public TrackingRefUpdate() { }
void PrintValue() { Console.Write(b.ToString()); }
QueryNode() => GetChildren()[0];
public NotIgnoredFilter(int workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
public OrdRange OrdRange => prefixToOrdRange[dim];
return string.Format("{0}('{1}')", typeof(LexerNoViableAltException).Name, (startIndex >= 0 && startIndex < InputStream.Size ? Utils.EscapeWhitespace(InputStream.GetText(Interval.Of(startIndex, startIndex)), false) : ""));
E E() => peekFirstImpl;
public CreateWorkspacesResult CreateWorkspacesResult() => ExecuteCreateWorkspaces(BeforeClientExecution(request));
public NumberFormatIndexRecord NumberFormatIndexRecord() => copy;
public DescribeRepositoriesResult DescribeRepositoriesResult() { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter() : this(input) { }
public CreateDistributionWithTagsResult CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { new System.IO.FileInfo(fileName); }
public DeleteWorkspaceImageResult DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHexString(long value) { return value.ToString("x16"); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) => ExecuteUpdateDistribution(BeforeClientExecution(request));
if (index == HSSFColorPredefined.Automatic.GetIndex()) return HSSFColorPredefined.Automatic.GetColor(); var b = _palette.GetColor(index); return b == null ? null : new CustomColor();
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void Write() { out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToByteArray(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return executeUploadArchive(request); }
IList<object> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) { return ReferenceEquals(this, obj) || (obj is AutomatonQuery other && base.Equals(other) && GetType() == other.GetType() && object.Equals(compiled, other.compiled) && object.Equals(term, other.term)); }
var queries = WeightBySpanQuery.Select(kvp => kvp.Value != 1f ? new SpanBoostQuery(kvp.Key, kvp.Value) : kvp.Key).ToArray(); return queries.Length == 1 ? queries[0] : new SpanOrQuery(queries);
StashCreateCommand StashCreateCommand() => new StashCreateCommand();
FieldInfo FieldInfo() => byName[fieldName];
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeEventSource(request); }
public GetDocumentAnalysisResult GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
CancelUpdateStackResult CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return ExecuteModifyLoadBalancerAttributes(request); }
request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request);
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy; }
if (count == outputs.Length) Array.Resize(ref outputs, count + 1); if (count == endOffsets.Length) Array.Resize(ref endOffsets, count + 1); if (count == posLengths.Length) Array.Resize(ref posLengths, count + 1); (outputs[count] ??= new CharsRefBuilder()).CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++;
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool Exists() => fs.Exists;
public FilterOutputStream(Stream outputStream) { this.@out = outputStream; }
new ScaleClusterRequest("/clusters/[ClusterId]") { Method = MethodType.Put };
public DVConstraint DataValidationConstraint(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
ListObjectParentPathsResult ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups; }
void UpdateOptions() { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
bool MethodName() { }
public ErrorNode ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); _ = t.SetParent; return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet; }
RevFilter() { return new Binary((byte[])a.Clone(), (byte[])b.Clone()); }
bool MyMethod(object o) { return false; }
bool HasArray() => ProtectedHasArray();
public UpdateContributorInsightsResult UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null;
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances() { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
() => findObjectRecord.GetObjectData()
GetContactAttributesResult GetContactAttributesResult(GetContactAttributesRequest request) { request = beforeClientExecution; return executeGetContactAttributes(request); }
public override string ToString() => $"{Key}: {Value}";
public ListTextTranslationJobsResult ListTextTranslationJobs() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
public GetContactMethodsResult GetContactMethodsResult() { request = beforeClientExecution(request); return executeGetContactMethods(request); }
return (GetInstance().GetFunctionByNameInternal(name) ?? GetInstanceCetab().GetFunctionByNameInternal(name))?.GetIndex() ?? -1;
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return executeDescribeAnomalyDetectors(request); }
public static string MethodName(string message, ObjectId changeId) { return insertId; }
{ long sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); else throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public ImportInstallationMediaResult ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg(BinaryReader reader) { reader.ReadDouble(); }
GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult DescribeDetectorResult(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return executeDescribeDetector; }
public ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
public TokenStream Create() => new PortugueseStemFilter(input);
public FtCblsSubRecord() { reserved = new byte[0]; }
public bool Remove(object item) { lock (mutex) { return c.Remove(item); } }
public GetDedicatedIpResult GetDedicatedIp() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string MethodName() { return null; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = BeforeClientExecution; return ExecuteListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(object options) { }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
public GetOperationsResult GetOperationsResult(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations; }
void Encode(byte[] b, int o) { System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o), w1); System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o + 4), w2); System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o + 8), w3); System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o + 12), w4); System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o + 16), w5); }
public WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
return ExecuteDescribeMatchmakingRuleSets(BeforeClientExecution(request));
public Word(string wordId, char[] surface, int off, int len) { }
public String() { }
public static double Calculate(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { int n = v.Length; double s = 0; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
bool IsPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
() => end;
void WalkRange(ICellHandler handler) { firstRow = range.FirstRow; lastRow = range.LastRow; firstColumn = range.FirstColumn; lastColumn = range.LastColumn; width = lastColumn - firstColumn + 1; var ctx = new SimpleCellWalkContext(); for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { var currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) continue; for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { var currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) continue; if (IsEmpty(currentCell) && !traverseEmptyCells) continue; ctx.OrdinalNumber = checked(((long)(ctx.RowNumber - firstRow) * width) + (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() => { };
(ScoreTerm other) => (Boost == other.Boost) ? other.Bytes.Get().CompareTo(Bytes.Get()) : Boost.CompareTo(other.Boost);
for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len;
(LittleEndianOutput out, short val) => out.WriteShort(val);
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool Has(ObjectId id) => FindOffset(id) != -1;
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out var ft)) { fieldTypes.Add(dimName, ft = new DimConfig()); } ft.MultiValued = v; } }
return cells.Keys.Count(c => at(c).cmd >= 0);
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) => ExecuteDeleteVoiceConnector(beforeClientExecution);
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return ExecuteDeleteLifecyclePolicy(request); }
