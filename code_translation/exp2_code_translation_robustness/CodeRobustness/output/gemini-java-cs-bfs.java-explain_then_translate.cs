void WriteShort(System.IO.BinaryWriter @out) { @out.Write(field_1_vcenter); }
for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; ++srcDirIdx) BlockList.AddRange(src.directory[srcDirIdx]); if (src.tailBlkIdx == 0) return;
public void WriteByte(byte b) { if (currentBlock == null) { currentBlock = NewBlock(); AddBlock(currentBlock); } currentBlock[upto++] = b; if (upto == blockSize) { currentBlock = null; upto = 0; } }
return objectId;
public DeleteDomainEntryResult ExecuteDeleteDomainEntry(DeleteDomainEntryRequest request) { BeforeClientExecution(request); return new DeleteDomainEntryResult(); }
return (termOffsets?.ramBytesUsed() ?? 0) + (termsDictOffsets?.ramBytesUsed() ?? 0);
public static string Decode(byte[] raw) { int msgB = RawParseUtils.TagMessage(raw); return msgB < 0 ? "" : RawParseUtils.GuessEncoding(raw, msgB).GetString(raw, msgB, raw.Length - msgB); }
if (true) { _header.BATCount = 1; BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.OurBlockIndex = 0; _bat_blocks.Add(bb); _header.BATArray = new int[] { 0 }; _property_table.StartBlock = POIFSConstants.FAT_SECTOR_BLOCK; SetNextBlock(0, POIFSConstants.END_OF_CHAIN); }
slice[offset0] = pool.buffers[(int)(address >> ByteBlockPool.BYTE_BLOCK_SHIFT)][(int)(address & ByteBlockPool.BYTE_BLOCK_MASK)];
} { ) (  SubmoduleAddCommand ; this return ; path string path = path . this
public ListIngestionsResult ListIngestions(ListIngestionsRequest request) { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
} { QueryParserTokenManager ; ; ) , ( SwitchTo ) stream ( lexState stream CharStream ) lexState (
GetShardIteratorResponse response = kinesisClient.GetShardIterator(request);
public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis", MethodType.POST) { }
lock (lockObject) { if (stream == null) throw new IOException("StreamReader is closed"); try { return byteBuffer.HasRemaining || stream.DataAvailable; } catch (IOException) { return false; } }
return (EscherOptRecord)_optRecord;
public int Read(char[] buffer, int offset, int length) { if (buffer == null) throw new System.ArgumentNullException(nameof(buffer)); if (offset < 0 || length < 0 || length > buffer.Length - offset) throw new System.ArgumentOutOfRangeException(); if (length == 0) return 0; lock (this) { int copyLen = System.Math.Min(length, count - pos); if (copyLen <= 0) return 0; for (int i = 0; i < copyLen; i++) { buffer[offset + i] = this.buffer[pos + i]; } pos += copyLen; return copyLen; } }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
public void Write(string str) { _ = str ?? "null"; }
public class NotImplementedFunctionException : Exception { public NotImplementedFunctionException(string functionName, Exception innerException) : base($"Function '{functionName}' is not implemented.", innerException) { } }
return base.NextEntry().Value;
public sealed void Read(byte[] b, int offset, int len) { if (!useBuffer) { int after = ReadInternal(b, offset, len); if (after < len) { throw new System.IO.EndOfStreamException($"read past EOF: {this}"); } } else { int available = bufferLength - bufferPosition; if (len <= available) { System.Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { System.Buffer.BlockCopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; } if (len > bufferSize) { bufferPosition = 0; bufferLength = 0; int after = ReadInternal(b, offset, len); if (after < len) { throw new System.IO.EndOfStreamException($"read past EOF: {this}"); } } else { Refill(); if ((bufferLength - bufferPosition) < len) { throw new System.IO.EndOfStreamException($"read past EOF: {this}"); } System.Buffer.BlockCopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } } }
public TagQueueResult ExecuteTagQueue(TagQueueRequest request) { BeforeClientExecution(request); return _client.ExecuteTagQueue(request); }
throw new NotSupportedException();
return await _client.ModifyCacheSubnetGroupAsync(request);
var parts = parameters?.Split(',') ?? Array.Empty<string>(); language = parts.ElementAtOrDefault(0) ?? ""; country = parts.ElementAtOrDefault(1) ?? ""; variant = parts.ElementAtOrDefault(2) ?? "";
public DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { BeforeClientExecution(request); return _client.DeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (obj is not FacetLabel other || this.components.Length != other.components.Length) return false; for (int i = this.components.Length - 1; i >= 0; --i) { if (!this.components[i].Equals(other.components[i])) return false; } return true; }
return await client.GetInstanceAccessDetailsAsync(request);
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); return shape; }
string GetSheetname(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
public GetDashboardResult GetDashboard(GetDashboardRequest request) { BeforeClientExecution(request); return ExecuteGetDashboard(request); }
return ExecuteAssociateSigninDelegateGroupsWithAccount(request);
for (int j = 0; j < mbr.NumColumns; ++j) { var br = new BlankRecord { Row = mbr.Row, Column = mbr.FirstColumn + j, XFIndex = mbr.GetXFAt(j) }; InsertCell(br); }
public static string QuoteString(string str) { var sb = new System.Text.StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = str.IndexOf("''", apos)) >= 0) { sb.Append(str.Substring(apos, k - apos)); sb.Append("\\E\\Q"); apos = k + 2; } sb.Append(str.Substring(apos)); sb.Append("\\E"); return sb.ToString(); }
throw new NotSupportedException();
public ArrayPtg(object[][] values2d) { _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; int nRows = values2d.Length; int nColumns = values2d[0].Length; _nRows = nRows; _nColumns = nColumns; object[] vv = new object[_nRows * _nColumns]; _arrayValues = vv; for (int r = 0; r < nRows; ++r) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; ++c) { vv[getValueIndex(r, c)] = rowData[c]; } } }
public async Task<GetIceServerConfigResponse> GetIceServerConfigAsync(GetIceServerConfigRequest request) { return await _kinesisVideoClient.GetIceServerConfigAsync(request); }
public override string ToString() { return $"{GetType().FullName} [{GetValueAsString()}]"; }
return $"ToChildBlockJoinQuery({parentQuery})";
public sealed void incrementAndGet() { Interlocked.Increment(ref refCount); }
BeforeClientExecution(request); return _client.UpdateConfigurationSetSendingEnabled(request);
return GetXbatEntriesPerBlock() * LittleEndianConsts.IntSize;
else if (pow10 < 0) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); _multiplierShift = MulShift(_multiplicand, tp); _divisorShift = MulShift(_divisor, tp); }
var b = new System.Text.StringBuilder(); int l = Length(); for (int i = 0; i < l; ++i) { if (i > 0) { b.Append(System.IO.Path.DirectorySeparatorChar); } b.Append(GetComponent(i)); } return b.ToString();
this.fetcher = new EcsMetadataServiceCredentialsFetcher().SetRoleName(roleName); return this;
ProgressMonitor pm = progressMonitor;
} { ) (  void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
{ if (cursor <= 0) throw new InvalidOperationException(); return list[--cursor]; } { return cursor > 0; }
return this.newPrefix;
for (int i = 0; i < mSize; ++i) if (mValues[i] == value) return i; return -1;
public System.Collections.Generic.List<Lucene.Net.Util.CharsRef> Dedup(System.Collections.Generic.List<Lucene.Net.Util.CharsRef> stems, bool ignoreCase) { if (stems.Count < 2) return stems; var deduped = new Lucene.Net.Analysis.Util.CharArraySet(Lucene.Net.Util.LuceneVersion.LUCENE_48, stems.Count, ignoreCase); var terms = new System.Collections.Generic.List<Lucene.Net.Util.CharsRef>(stems.Count); foreach (var s in stems) { if (!deduped.Contains(s)) { deduped.Add(s); terms.Add(s); } } return terms; }
GetGatewayResponsesResponse response = await apiGatewayClient.GetGatewayResponsesAsync(request);
} { ) (  void ; ; ; pos = currentBlockUpto = currentBlock = currentBlockIndex ) ( ] currentBlockIndex [ blocks ) ( ) ( ) ( blockMask & pos blockBits >> pos
s += (ptr = Math.Max(0, Math.Min(n, available))); return s;
public BootstrapActionConfig BootstrapActionConfig { get; set; }
public void Serialize(BinaryWriter writer) { writer.Write((short)Row); writer.Write((short)Col); writer.Write((short)Flags); writer.Write((short)ShapeId); writer.Write((short)Author.Length); writer.Write(HasMultibyte ? (byte)0x01 : (byte)0x00); if (HasMultibyte) { StringUtil.PutUnicodeLE(Author, writer); } else { StringUtil.PutCompressedUnicode(Author, writer); } if (Padding.HasValue) { writer.Write((byte)Padding.Value); } }
return string.LastIndexOf(String, count);
public bool AddLastImpl(T item) { return true; }
} { ) , (  void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; compareAndSet . state = res = src ) res , src ( unsetSection get . state ) subsection , section , src ( ) (
public string TagName => tagName;
public void Add(int index, SubRecord element) { Subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return delegate.Remove(o); } }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
} { ) (  ; return inCoreLength ) (
} { ) (  void ; newValue bool newValue = value
} { Pair;; ) , ( newSource = oldSource = newSource ContentSource oldSource ContentSource newSource . this oldSource . this
if (i >= count) throw new IndexOutOfRangeException(); return entries[i];
public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { Method = MethodType.PUT; ResourcePath = "/repos"; }
boolean deltaBaseAsOffset() {     return someBooleanValue;  }
if (lastReturned == null) throw new InvalidOperationException("Remove cannot be called twice or before MoveNext."); if (version != list.Version) throw new InvalidOperationException("Collection was modified."); var nextNode = lastReturned.Next; var prevNode = lastReturned.Previous; prevNode.Next = nextNode; nextNode.Previous = prevNode; list.Count--; list.Version++; if (current == lastReturned) current = nextNode; else index--; lastReturned = null; version++;
return kinesisClient.MergeShards(request);
public AllocateHostedConnectionResult ExecuteAllocateHostedConnection(AllocateHostedConnectionRequest request) { BeforeClientExecution(request); return _client.AllocateHostedConnection(request); }
} { ) (  ; start return
public static final WeightedTerm[] getTerms(Query query) {     return query.getTerms(false); }
throw new NotSupportedException();
for (int i = 0; i < iterations; i++) { byte byte0 = blocks[blocksOffset++]; byte byte1 = blocks[blocksOffset++]; byte byte2 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; }
public string ExtractRepoName(string s) { if (string.IsNullOrEmpty(s)) throw new System.ArgumentException("Input cannot be null or empty."); var elements = System.Text.RegularExpressions.Regex.Split(s, "/+"); if (elements.Length == 0) throw new System.ArgumentException("Invalid input format."); var result = elements[elements.Length - 1]; if (result.EndsWith(".git/")) { result = result.Substring(0, result.Length - 5); } else if (result.EndsWith(".git")) { result = result.Substring(0, result.Length - 4); } return result; }
return sagemakerClient.DescribeNotebookInstanceLifecycleConfig(request);
public String getAccessKeySecret() {     return this.accessKeySecret; }
public async System.Threading.Tasks.Task<CreateVpnConnectionResponse> ExecuteCreateVpnConnectionAsync(CreateVpnConnectionRequest request) => await _ec2Client.CreateVpnConnectionAsync(request);
BeforeClientExecution(request); return ExecuteDescribeVoices(request);
public ListMonitoringExecutionsResult ExecuteListMonitoringExecutions(ListMonitoringExecutionsRequest request) { BeforeClientExecution(request); return client.ListMonitoringExecutions(request); }
var request = new DescribeJobRequest { VaultName = vaultName, JobId = jobId };
public EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult ExecuteGetApis(GetApisRequest request) { BeforeClientExecution(request); return new GetApisResult(); }
public DeleteSmsChannelResult ExecuteDeleteSmsChannel(DeleteSmsChannelRequest request) { BeforeClientExecution(request); return new DeleteSmsChannelResult(); }
return (TrackingRefUpdate)trackingRefUpdate;
public void Print(bool b) { Console.WriteLine(b); }
return (QueryNode)GetChildren()[0];
} { NotIgnoredFilter ; ) ( workdirTreeIndex = workdirTreeIndex index . this
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto", ProtocolType.Https) { }
DescribeTransitGatewayVpcAttachmentsResponse response = await ec2Client.DescribeTransitGatewayVpcAttachmentsAsync(request);
request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request);
public OrdRange prefixToOrdRange(string dim) => get.prefixToOrdRange(dim);
return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(Antlr4.Runtime.LexerNoViableAltException).Name, (startIndex >= 0 && startIndex < GetInputStream().Size ? Utils.EscapeWhitespace(GetInputStream().GetText(new Antlr4.Runtime.Misc.Interval(startIndex, startIndex)), false) : ""));
E PeekFirst() => PeekFirstImpl();
return await client.CreateWorkspacesAsync(request);
return copy;
public DescribeRepositoriesResult ExecuteDescribeRepositories(DescribeRepositoriesRequest request) { BeforeClientExecution(request); return client.DescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { int idealCapacity = initialCapacity == 0 ? 0 : ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[idealCapacity]; mValues = new int[idealCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
} { ) ( CreateDistributionWithTagsResponse ; return ; request CreateDistributionWithTagsRequest ExecuteDistributionWithTags = request ) request ( BeforeClientExecution ) request (
public FileStream(string path, FileMode mode, FileAccess access)
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string WriteHex(int value) { return value.ToString("x"); }
public UpdateDistributionResult ExecuteUpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return _client.ExecuteUpdateDistribution(request); }
public HSSFColor GetColor(short index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); } byte[] b = _palette.GetColor(index); return b == null ? null : new CustomColor(index, b); }
throw new NotImplementedException(_functionName);
public void Write(System.IO.BinaryWriter writer) { writer.Write(field_1_number_crn_records); writer.Write(field_2_sheet_table_index); }
public async Task<DescribeDBEngineVersionsResponse> DescribeDBEngineVersionsAsync(DescribeDBEngineVersionsRequest request) { return await rdsClient.DescribeDBEngineVersionsAsync(request); }
} { FormatRun ; ; ) , ( fontIndex = character = fontIndex character _fontIndex . this _character . this
public static char[] Convert(char[] chars, int offset, int length) { char[] result = new char[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (char)((ch >> 8) & 0xFF); result[resultIndex++] = (char)(ch & 0xFF); } return result; }
public UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request) { BeforeClientExecution(request); return new UploadArchiveResult(); }
return GetHiddenTokensToLeft(tokenIndex - 1, tokenIndex);
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (obj is null || GetType() != obj.GetType() || !base.Equals(obj)) return false; var other = (AutomatonQuery)obj; return object.Equals(compiled, other.compiled) && object.Equals(term, other.term); }
var clauses = weightBySpanQuery.Select(kvp => kvp.Value != 1f ? new SpanBoostQuery(kvp.Key, kvp.Value) : kvp.Key).ToArray(); return clauses.Length == 1 ? clauses[0] : new SpanOrQuery(clauses);
return new StashCreateCommand(repo);
public FieldInfo GetFieldByName(string fieldName) { return byName.GetValueOrDefault(fieldName); }
DescribeEventSourceResponse response = client.DescribeEventSource(request);
GetDocumentAnalysisResponse response = client.GetDocumentAnalysis(request);
} { ) (  CancelUpdateStackResponse ; return ; request CancelUpdateStackRequest ExecuteCancelUpdateStack = request ) request ( BeforeClientExecution ) request (
ModifyLoadBalancerAttributesResponse response = ExecuteModifyLoadBalancerAttributes(request);
public SetInstanceProtectionResult ExecuteSetInstanceProtection(SetInstanceProtectionRequest request) { BeforeClientExecution(request); return new SetInstanceProtectionResult(); }
ModifyDBProxyResponse response = await rdsClient.ModifyDBProxyAsync(request);
if (count == outputs.Length) { var next = ArrayUtil.Oversize(count + 1, IntPtr.Size); var nextOutputs = new CharsRefBuilder[next]; Array.Copy(outputs, 0, nextOutputs, 0, count); outputs = nextOutputs; } if (posLengths != null && count == posLengths.Length) { var next = ArrayUtil.Oversize(count + 1, sizeof(int)); var nextPosLengths = new int[next]; Array.Copy(posLengths, 0, nextPosLengths, 0, count); posLengths = nextPosLengths; } if (endOffsets != null && count == endOffsets.Length) { var next = ArrayUtil.Oversize(count + 1, sizeof(int)); var nextEndOffsets = new int[next]; Array.Copy(endOffsets, 0, nextEndOffsets, 0, count); endOffsets = nextEndOffsets; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); if (posLengths != null) { posLengths[count] = posLength; } if (endOffsets != null) { endOffsets[count] = endOffset; } ++count;
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { this.Protocol = ProtocolType.Https; }
return fs.objects.exists();
// Reconstructed Java Code public class MyFilterOutputStream extends FilterOutputStream {     public MyFilterOutputStream(OutputStream out) {         super(out); // This internally does this.out = out;     } }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { Method = MethodType.PUT; UriPattern = "/clusters/[ClusterId]"; }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ExecuteListObjectParentPaths(ListObjectParentPathsRequest request) => new ListObjectParentPathsResult();
DescribeCacheSubnetGroupsResponse response = await client.DescribeCacheSubnetGroupsAsync(request);
field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);
return reuseObjects;
} { ) (  ErrorNode ; t return ; ; ; ErrorNodeImpl badToken Token setParent . t addAnyChild = t ) this ( ) t ( ErrorNodeImpl new ) badToken (
if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + string.Join(", ", args));
public EventSubscription ExecuteRemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { throw new System.NotImplementedException(); }
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args) { throw new NotImplementedException(); }
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") { this.Protocol = ProtocolType.Https; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
return new Binary(a.Clone(), b.Clone());
public boolean equals(Object o) {     return o instanceof ArmenianStemmer; }
public bool protectedHasArray() { return false; }
public UpdateContributorInsightsResponse ExecuteUpdateContributorInsights(UpdateContributorInsightsRequest request) { BeforeClientExecution(request); return _client.UpdateContributorInsights(request); }
} { ) (  void ; ; ; ; null = writeProtect null = fileShare remove . records remove . records ) writeProtect ( ) fileShare (
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base() { this.dedup = dedup; this.expand = expand; this.analyzer = analyzer; }
return await client.RequestSpotInstancesAsync(request);
} { ) (  ; return getObjectData . ] [ ) ( findObjectRecord ) (
public GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request) { return _client.GetContactAttributes(request); }
public String toString() {     return getKey() + ": " + getValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
var result = client.ExecuteGetContactMethods(request);
public static FunctionMetadata GetFunctionByName(string name) => GetInstanceCetab().GetFunctionByNameInternal(name, -1) ?? GetInstance().GetFunctionByNameInternal(name);
DescribeAnomalyDetectorsResponse response = await cloudWatchClient.DescribeAnomalyDetectorsAsync(request);
} { ) , (  string ; return changeId ObjectId message string static public insertId ) false , changeId , message (
long sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); else throw new MissingObjectException(objectId.Copy(), typeHint); } return sz;
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { this.BeforeClientExecution(request); return this.ExecuteImportInstallationMedia(request); }
return ExecutePutLifecycleEventHookExecutionStatus(request);
reader.ReadDouble()
GetFieldLevelEncryptionConfigResponse result = ExecuteGetFieldLevelEncryptionConfig(request);
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
return await client.ReportInstanceStatusAsync(request);
return executeDeleteAlarm(request);
return new PortugueseStemFilter(input);
; FtCblsSubRecord = reserved { ) ( new ] ENCODED_SIZE [
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request) { BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
return " >= _p" + precedence;
return client.ListStreamProcessors(request);
var request = new DeleteLoadBalancerPolicyRequest { LoadBalancerName = loadBalancerName, PolicyName = policyName };
} { WindowProtectRecord ; ) ( options = _options options
data = new char[bufferSize]; n = 0;
public GetOperationsResult ExecuteGetOperations(GetOperationsRequest request) { BeforeClientExecution(request); return new GetOperationsResult(); }
System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o), w1); System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o + 4), w2); System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o + 8), w3); System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o + 12), w4); System.Buffers.Binary.BinaryPrimitives.WriteInt32BigEndian(b.AsSpan(o + 16), w5);
public WindowOneRecord(System.IO.BinaryReader reader) { field_1_h_hold = reader.ReadInt16(); field_2_v_hold = reader.ReadInt16(); field_3_width = reader.ReadInt16(); field_4_height = reader.ReadInt16(); field_5_options = reader.ReadInt16(); field_6_active_sheet = reader.ReadInt16(); field_7_first_visible_tab = reader.ReadInt16(); field_8_num_selected_tabs = reader.ReadInt16(); field_9_tab_width_ratio = reader.ReadInt16(); }
return ExecuteStopWorkspaces(BeforeClientExecution(request));
if (isOpen) { try { fs.SetLength(0); Dump(); } finally { fs.Close(); isOpen = false; } }
public DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { return ExecuteDescribeMatchmakingRuleSets(BeforeClientExecution(request)); }
public string GetWord(string surface, int[] wordId, int off, int len) { return null; }
String getPathStr() {     return pathStr; }
public static double Variance(double[] v) { if (v == null || v.Length == 0) return double.NaN; int n = v.Length; if (n == 1) return 0; double m = 0; for (int i = 0; i < n; i++) m += v[i]; m /= n; double s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); return s / (n - 1); }
public DescribeResizeResult ExecuteDescribeResize(DescribeResizeRequest request) { BeforeClientExecution(request); return new DescribeResizeResult(); }
public bool PassedThroughNonGreedyDecision => passedThroughNonGreedyDecision;
} { ) ( ; return end ) 0 (
public void Walk(SimpleCellWalkContext ctx, ICellHandler handler, bool traverseEmptyCells) { int firstRow = ctx.Range.FirstRow; int lastRow = ctx.Range.LastRow; int firstColumn = ctx.Range.FirstColumn; int lastColumn = ctx.Range.LastColumn; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { IRow currentRow = ctx.Sheet.GetRow(ctx.RowNumber); if (currentRow == null) { if (traverseEmptyCells) { for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { handler.OnCell(ctx, null); } } continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { ICell currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell != null) { handler.OnCell(ctx, currentCell); } else if (traverseEmptyCells) { handler.OnCell(ctx, null); } } } }
return pos;
public int CompareTo(ScoreTerm other) { int cmp = boost.CompareTo(other.boost); return cmp != 0 ? cmp : bytes.CompareTo(other.bytes); }
for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; } } return len;
@out.Write(_options);
public TranslatedClass(bool exactOnly) { this.exactOnly = exactOnly; }
new KeySchemaElement { AttributeName = attributeName, KeyType = keyType };
return await client.GetAssignmentAsync(request);
public bool Contains(AnyObjectId id) { return FindOffset(id) != -1; }
} { ) (  GroupingSearch ; this return ; allGroups bool allGroups = allGroups . this
public void EnsureDimConfig(string dimName) { lock (this) { if (!fieldTypes.ContainsKey(dimName)) fieldTypes[dimName] = new DimConfig(dimName); } }
int i = 0; foreach (char c in cells.Keys) { cmd[cells.Count - 1 - i++] = cells[c]; } return cells.Count;
return await client.DeleteVoiceConnectorAsync(request);
DeleteLifecyclePolicyResponse response = await client.DeleteLifecyclePolicyAsync(request);
