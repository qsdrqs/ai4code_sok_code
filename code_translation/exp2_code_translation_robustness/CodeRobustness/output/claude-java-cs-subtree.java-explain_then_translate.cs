void Method(LittleEndianOutput out) { out.WriteShort(); }
void (BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.tailBlock, 0, src.tailBlkIdx); }
void AddByte(byte b) { if (upto >= blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId() { }
DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
() => { return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + (( ) ? termsDictOffsets.RamBytesUsed() : 0); }
public string GetTagMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
POIFSFileSystem() { (true); _header.SetBATCount; _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0);
void (address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < ); }
SubmoduleAddCommand SubmoduleAddCommand(string path) { this.path = path; return this; }
ListIngestionsResult() { request = beforeClientExecution(request); return executeListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator; }
ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool Ready() { lock (lockObject) { if (inputStream == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || inputStream.DataAvailable; } catch { return false; } } }
EscherOptRecord() { }
public synchronized int Read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentOutOfRangeException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this.buffer[pos + i]; } pos += copylen; return copylen; }
OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Method(string str) { Write(str ?? Convert.ToString((object)null)); }
NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { = functionName; }
V() { return base.nextEntry.getValue(); }
public void Read(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
void Method() { throw new NotSupportedException(); }
CacheSubnetGroup() { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
void (string parameters) { base.SetParams(parameters); language = country = variant = ""; string[] tokens = parameters.Split(','); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution; return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request); }
HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard; }
AssociateSigninDelegateGroupsWithAccountResult(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((short)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string EscapeRegexLiteral(string input) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = input.IndexOf("\\E", apos)) >= 0) { sb.Append(input.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(input.Substring(apos)).Append("\\E").ToString(); }
ByteBuffer(value) { throw new InvalidOperationException(); }
ArrayPtg(object[,] values2d) { nColumns = values2d.GetLength(1); nRows = values2d.GetLength(0); _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = values2d[r, c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution; return executeGetIceServerConfig(request); }
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void IncrementRefCount() { refCount.IncrementAndGet(); }
UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return ExecuteUpdateConfigurationSetSendingEnabled(request); }
() { return getXBATEntriesPerBlock() * ; }
void Method(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
string() { StringBuilder b = new StringBuilder(); l = length; b.Append(Path.DirectorySeparatorChar); for(i = 0; i < l; i++) { b.Append(getComponent(i)); if(i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); return this; }
void (ProgressMonitor pm) { ; }
void () { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
string () { return ; }
( value ) { for ( i = 0 ; i < mSize ; i ++ ) if ( mValues [ i ] == value ) return - 1 ; }
List<CharsRef> (char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } HashSet<CharsRef> terms = new HashSet<CharsRef>(dictionary.IgnoreCase ? StringComparer.OrdinalIgnoreCase : StringComparer.Ordinal); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return executeGetGatewayResponses; }
void SetPosition(int pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
(n) { s = Math.Min(available(), Math.Max(0, n)); ptr += s; }
BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
void WriteToOutput(LittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort(field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, output); } else { StringUtil.PutCompressedUnicode(field_6_author, output); } if (field_7_padding != null) { output.WriteByte((byte)field_7_padding.Value); } }
(string str) => { return LastIndexOf; }
bool (E @object) { return addLastImpl; }
void RemoveSection(string section, string subsection) { ConfigSnapshot src, res; do { src = state.Value; res = UnsetSection(src, section, subsection); } while (!state.CompareExchange(ref state, res, src)); }
string () { return tagName; }
void InsertSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o) { lock (mutex) { return _delegate.Remove(o); } }
DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
() { return inCoreLength; }
void (bool newValue) { ; }
Pair(ContentSource oldSource, ContentSource newSource) {      this.oldSource = oldSource;      this.newSource = newSource;  }
(int i) { if (/* condition */) throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(HttpMethod.Post); }
bool () { }
void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request); }
( ) { }
public static WeightedTerm GetWeightedTerm(Query query) { return GetTerms(query, false); }
ByteBuffer() { throw new ReadOnlyBufferException(); }
void UnpackBlocks(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = byte0 >> 2; byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
string GetName() { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new char[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new char[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
DescribeNotebookInstanceLifecycleConfigResult(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
string () { return ; }
CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions; }
DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
EscherRecord(index) { return escherRecords[index]; }
GetApisResult GetApis(GetApisRequest request) { request = beforeClientExecution; return ExecuteGetApis(request); }
DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
TrackingRefUpdate() { }
void () { Console.Write(b.ToString()); }
QueryNode() { return getChildren[0]; }
NotIgnoredFilter(workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
AreaRecord(RecordInputStream input) { field_1_formatFlags = input.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange() { return prefixToOrdRange[dim]; }
string() { string symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().size) { symbol = getInputStream().getText(Interval.of(startIndex, startIndex)); symbol = Utils.escapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
E() { return peekFirstImpl; }
CreateWorkspacesResult() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
NumberFormatIndexRecord() { return copy; }
DescribeRepositoriesResult() { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { new FileInfo(fileName); }
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage; }
public static string ConvertToHex(object value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request); }
HSSFColor(index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); } var b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(); }
ValueEval Evaluate(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedException(_functionName); }
void Method() { out.WriteInt16((short)field_1_number_crn_records); out.WriteInt16((short)field_2_sheet_table_index); }
DescribeDBEngineVersionsResult() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(char character, int fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ConvertCharsToBytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
UploadArchiveResult UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return ExecuteUploadArchive(request); }
List GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[Size()]; var sqi = weightBySpanQuery.Keys.GetEnumerator(); i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); }
StashCreateCommand() => new StashCreateCommand();
FieldInfo GetFieldInfo() { return byName[fieldName]; }
DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
ModifyDBProxyResult ModifyDBProxyRequest(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy; }
void AddEntry(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { Array.Resize(ref outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[Math.Max(count + 1, (count + 1) * 2)]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[Math.Max(count + 1, (count + 1) * 2)]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool () { return fs.exists; }
FilterOutputStream(Stream output) { this.output = output; }
ScaleClusterRequest("/clusters/[ClusterId]"); SetMethod(MethodType.PUT);
DataValidationConstraint(operatorType, formula1, String formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult() { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
void MethodName(bool flag) { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool () { }
ErrorNode ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
LatvianStemFilterFactory(Dictionary<string, string> args) : base(args) { if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + args); } }
EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory CreateTokenFilterFactory(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet; }
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (Object o) { return; }
bool () { return protectedHasArray(); }
UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
void Method() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
RequestSpotInstancesResult() { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
[ ] ( ) { return findObjectRecord.GetObjectData( ); }
GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution; return ExecuteGetContactAttributes(request); }
String() { return getKey + ": " + getValue(); }
ListTextTranslationJobsResult() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
GetContactMethodsResult() { request = beforeClientExecution(request); return executeGetContactMethods(request); }
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return ExecuteDescribeAnomalyDetectors(request); }
public static string (string message, ObjectId changeId) { return insertId; }
(AnyObjectId objectId, int typeHint) { var sz = db.GetObjectSize(objectId); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult() { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
NumberPtg() : base(input.ReadDouble()) { }
GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return ExecuteGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector; }
ReportInstanceStatusResult ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm; }
TokenStream() { return new PortugueseStemFilter(input); }
FtCblsSubRecord() { reserved = new ;
public bool RemoveObject(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string () { return ; }
ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
WindowProtectRecord(options) { }
UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations; }
void EncodeIntegers(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
StopWorkspacesResult(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
String(wordId, surface[], off, len) { }
String() { }
public static double CalculateVariance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResult() { request = beforeClientExecution(request); return executeDescribeResize(request); }
bool () { return passedThroughNonGreedyDecision; }
() { return end; }
void ProcessCells(CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
( ) { }
(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.Get().CompareTo(this.bytes.Get()); else return float.Compare(this.boost, other.boost); }
(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
void Method(LittleEndianOutput out) { out.WriteShort(); }
DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, object keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool (AnyObjectId id) { return findOffset(id) != ; }
GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock(this) { DimConfig ft = fieldTypes.ContainsKey(dimName) ? fieldTypes[dimName] : null; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
() { var i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { char c = i.Current; Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return ExecuteDeleteLifecyclePolicy(request); }
