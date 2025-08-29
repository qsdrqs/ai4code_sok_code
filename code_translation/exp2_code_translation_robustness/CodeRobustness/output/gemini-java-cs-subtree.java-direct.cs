(BinaryWriter writer, short value) => writer.Write(value);
void Method(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void ProcessItem(object b) { if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new object[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry; }
() => { return (termOffsets?.RamBytesUsed() ?? 0) + (termsDictOffsets?.RamBytesUsed() ?? 0); };
public sealed override string ToString() { byte[] raw = buffer; msgB = RawParseUtils.tagMessage; if (msgB < 0) return ""; return RawParseUtils.decode(guessEncoding(), raw, msgB, raw.Length); }
_header.BATCount = 1; _header.BATArray = new int[] { 1 }; BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.StartBlock = 0;
slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; System.Diagnostics.Debug.Assert(upto < ByteBlockPool.BYTE_BLOCK_SIZE);
public SubmoduleAddCommand SubmoduleAddCommand(string path) { this.path = path; return this; }
ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator; }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool Ready() { lock (lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || in.Available() > 0; } catch { return false; } } }
public EscherOptRecord() { }
lock (this) { if (buffer == null) throw new ArgumentNullException("buffer", "buffer == null"); if ((offset | length) < 0 || offset > buffer.Length || length > buffer.Length - offset) throw new IndexOutOfRangeException(); if (length == 0) return 0; int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) buffer[offset + i] = this.buffer[pos + i]; return copylen; }
public OpenNLPSentenceBreakIterator(SentenceOp sentenceOp) { this.sentenceOp = sentenceOp; }
void Method(string str) { write(str ?? "null"); }
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { }
public V GetValue() => base.nextEntry.GetValue();
public void Read(byte[] b, int offset, int len, bool useBuffer) { available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) System.Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { System.Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { System.Array.Copy(buffer, 0, b, offset, bufferLength); throw new System.IO.EndOfStreamException("read past EOF: " + this); } else { System.Array.Copy(buffer, 0, b, offset, len); } } else { after = bufferStart + bufferPosition + len; if (after > Length()) throw new System.IO.EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult TagQueueResult(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
throw new NotSupportedException();
public object CacheSubnetGroup() { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
public override void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; var parts = @params.Split(new[] {','}, StringSplitOptions.RemoveEmptyEntries); if (parts.Length > 0) language = parts[0]; if (parts.Length > 1) country = parts[1]; if (parts.Length > 2) variant = parts[2]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals ( object obj ) { if ( obj is not FacetLabel other ) { return false ; } if ( length != other . length ) { return false ; } for ( int i = length - 1 ; i >= 0 ; i -- ) { if ( ! components [ i ] . Equals ( other . components [ i ] ) ) { return false ; } } return true ; }
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails; }
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape; }
string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).SheetName;
public GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return executeAssociateSigninDelegateGroupsWithAccount(request); }
void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = j + mbr.FirstColumn; br.Row = mbr.Row; br.XFIndex = mbr.XFAt; InsertCell(br); } }
public static string Translate(string str) { int k; StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; while ((k = str.IndexOf("\\E", apos)) >= 0) { sb.Append(str.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(str.Substring(apos)).Append("\\E").ToString(); }
public ByteBuffer(byte[] value) { throw new NotSupportedException(); }
internal ArrayPtg(object[][] values2d) { nColumns = values2d[0].Length; nRows = values2d.Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution; return ExecuteGetIceServerConfig(request); }
public override string ToString() => $"{GetType().FullName} [{GetValueAsString()}]";
public override string ToString() => $"ToChildBlockJoinQuery ({parentQuery})";
Interlocked.Increment(ref refCount);
UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
() { return GetXBATEntriesPerBlock() * ; }
void Pow10(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(tp._divisor, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
StringBuilder b = new StringBuilder(); l = length; b.Append(Path.DirectorySeparatorChar); for (i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString();
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); }
void Method(ProgressMonitor pm) { }
void Method() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException();
public String() { return; }
(  value ) { for ( i = 0 ; i < mSize ; i ++ ) if ( mValues [ i ] == value ) return - 1 ; }
List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) return stems; var terms = new CharArraySet(8, dictionary.IgnoreCase); var deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped;
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request); }
void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
(n) { s = Math.Min(available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { BootstrapActionConfig = bootstrapActionConfig; }
void Write(BinaryWriter output) { output.Write((short)field_1_row); output.Write((short)field_2_col); output.Write((short)field_3_flags); output.Write((short)field_4_shapeid); output.Write((short)field_6_author.Length); output.Write((byte)(field_5_hasMultibyte ? 0x01 : 0x00)); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, output); } else { StringUtil.PutCompressedUnicode(field_6_author, output); } if (field_7_padding != null) { output.Write((byte)field_7_padding.Value); } }
(string @string) => lastIndexOf;
bool Add(E item) { return AddLastImpl; }
void UnsetSection(string section, string subsection) { ConfigSnapshot src, res; do { src = Volatile.Read(ref state); res = UnsetSection(src, section, subsection); } while (Interlocked.CompareExchange(ref state, res, src) != src); }
string TagName => tagName;
(index, element) => { subrecords.Add(element); }
lock (mutex) { return Delegate().Remove(o); }
public DoubleMetaphoneFilter(TokenStream input) : base(input) { }
() => inCoreLength;
void Method(bool newValue) { ; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
(int i) { if () throw new IndexOutOfRangeException(); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(); }
bool MethodName() { }
if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); }
MergeShardsResult() => executeMergeShards(request = beforeClientExecution(request));
public AllocateHostedConnectionResult AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection; }
() => {};
public static WeightedTerm WeightedTerm(Query query) => getTerms(query, false);
public ByteBuffer() { throw new NotSupportedException(); }
void Translate(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
string s = GetPath(); if ("/" == s || "" == s) s = GetHost(); if (s == null) throw new System.ArgumentException(); string[] elements; if ("file" == scheme || LOCAL_FILE.IsMatch(s)) elements = s.Split(new[] { '/', System.IO.Path.DirectorySeparatorChar }); else elements = s.Split(new[] { '/' }, System.StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new System.ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT == result) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result;
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
public String() { return; }
CreateVpnConnectionResult CreateVpnConnectionResult() { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
DescribeVoicesResult DescribeVoicesResult() => ExecuteDescribeVoices(BeforeClientExecution(request));
ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions; }
public DescribeJobRequest(string vaultName, string jobId) { VaultName = vaultName; JobId = jobId; }
public EscherRecord this[int index] => escherRecords[index];
GetApisResult GetApisResult(GetApisRequest request) { request = beforeClientExecution; return executeGetApis(request); }
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
public TrackingRefUpdate() { }
void Method() { Console.Write(b.ToString()); }
QueryNode() => Children[0];
NotIgnoredFilter(workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange() => prefixToOrdRange[dim];
string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol);
public E Peek() => peekFirstImpl;
CreateWorkspacesResult() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
public NumberFormatIndexRecord NumberFormatIndexRecord() => copy;
DescribeRepositoriesResult DescribeRepositoriesResult() { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter() : this(input) { }
CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) => _stream = new FileStream(fileName, mode == "r" ? FileMode.Open : FileMode.OpenOrCreate, mode == "r" ? FileAccess.Read : FileAccess.ReadWrite);
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return executeDeleteWorkspaceImage; }
public static string ToHex(long value) { System.Text.StringBuilder sb = new System.Text.StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistributionResult(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return executeUpdateDistribution; }
if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) return HSSFColorPredefined.AUTOMATIC.GetColor(); byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor();
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
out.Write((short)field_1_number_crn_records); out.Write((short)field_2_sheet_table_index);
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() => DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] Method(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
UploadArchiveResult UploadArchiveResult(UploadArchiveRequest request) => executeUploadArchive(beforeClientExecution);
IList<IToken> GetHiddenTokensToLeft(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, -1);
if (ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true;
var queries = weightBySpanQuery.Select(kvp => kvp.Value != 1f ? new SpanBoostQuery(kvp.Key, kvp.Value) : kvp.Key).ToArray(); return queries.Length == 1 ? queries[0] : new SpanOrQuery(queries);
public StashCreateCommand StashCreateCommand() => new StashCreateCommand();
FieldInfo FieldInfo() => byName[fieldName];
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution; return ExecuteDescribeEventSource(request); }
GetDocumentAnalysisResult GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
public SetInstanceProtectionResult SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxyResult(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy; }
if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { var next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; System.Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { var next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; System.Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++;
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") {}
bool Exists() => fs.Exists;
FilterOutputStream(Stream @out) { this.out = @out; }
new ScaleClusterRequest("/clusters/[ClusterId]") { Method = MethodType.PUT };
DataValidationConstraint(int operatorType, string formula1, string formula2) => DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
ListObjectParentPathsResult ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroupsResult(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups; }
field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);
bool ( ) { }
public ErrorNode ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + string.Join(", ", args.Keys)); }
EventSubscription ( ) { request = beforeClientExecution ( request ) ; return executeRemoveSourceIdentifierFromSubscription ( request ) ; }
public static TokenFilterFactory Create(string name, IDictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet; }
Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool MethodName(object o) { return false; }
bool() { return protectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsightsResult(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return executeUpdateContributorInsights(request); }
void Method() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
RequestSpotInstancesResult RequestSpotInstancesResult() { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
() => { return findObjectRecord.GetObjectData(); }
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution; return executeGetContactAttributes(request); }
public override string ToString() => $"{GetKey}: {GetValue()}";
public ListTextTranslationJobsResult ListTextTranslationJobsResult() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
GetContactMethodsResult GetContactMethodsResult() { request = beforeClientExecution(request); return executeGetContactMethods(request); }
return (GetInstance().GetFunctionByNameInternal(name) ?? GetInstanceCetab().GetFunctionByNameInternal(name))?.GetIndex() ?? -1;
DescribeAnomalyDetectorsResult(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return executeDescribeAnomalyDetectors(request); }
public static string MethodName(string message, ObjectId changeId) { return insertId; }
sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz;
ImportInstallationMediaResult ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
NumberPtg() { @in.ReadDouble(); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector; }
ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
public TokenStream TokenStream() => new PortugueseStemFilter(input);
FtCblsSubRecord() { reserved = new ;
public bool Remove(object @object) { lock (mutex) { return c.Remove(@object); } }
GetDedicatedIpResult GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
public String() { return; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public WindowProtectRecord(object options) { }
UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult GetOperationsResult(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations; }
void Method(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream @in) { field_1_h_hold = @in.ReadShort(); field_2_v_hold = @in.ReadShort(); field_3_width = @in.ReadShort(); field_4_height = @in.ReadShort(); field_5_options = @in.ReadShort(); field_6_active_sheet = @in.ReadShort(); field_7_first_visible_tab = @in.ReadShort(); field_8_num_selected_tabs = @in.ReadShort(); field_9_tab_width_ratio = @in.ReadShort(); }
StopWorkspacesResult StopWorkspacesResult(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
public void TranslatedMethod() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSetsResult() => ExecuteDescribeMatchmakingRuleSets(BeforeClientExecution(request));
public String(int wordId, char[] surface, int off, int len) { }
public String() { }
public static double f(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0, s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
bool PassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
=> end;
void ProcessCells(CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; var ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } var rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width); ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.ColNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() { }
(ScoreTerm other) => this.boost == other.boost ? other.bytes.Get().CompareTo(this.bytes.Get()) : float.Compare(this.boost, other.boost);
for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len;
(BinaryWriter writer, short value) => writer.Write(value);
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
public AssignmentResult GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool Has(AnyObjectId id) => findOffset(id) != -1;
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock (this) { if (!fieldTypes.TryGetValue(dimName, out DimConfig ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
{ size = cells.Keys.Select(c => at(c)).Count(e => e.cmd >= 0); return size; }
public DeleteVoiceConnectorResult DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request) { request = BeforeClientExecution; return ExecuteDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
