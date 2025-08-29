void Method(LittleEndianOutput @out) { @out.WriteShort(); }
void Method(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void Method(byte b) { if (condition) { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
ObjectId() { }
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
() { return ((termOffsets != null) ? termOffsets.ramBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.ramBytesUsed() : 0); }
public string GetMessage() { var raw = buffer; var msgB = RawParseUtils.tagMessage; if (msgB < 0) { return ""; } return RawParseUtils.decode(guessEncoding(), raw, msgB, raw.Length); }
POIFSFileSystem() : this(true) { _header.SetBATCount(); _header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void (address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < ); }
SubmoduleAddCommand(string path) { this.path = path; return this; }
ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState) { ; SwitchTo(lexState); }
GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return executeGetShardIterator; }
ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool ( ) { lock ( lockObject ) { if ( in == null ) { throw new IOException ( "InputStreamReader is closed" ) ; } try { return bytes . HasRemaining || in . Available ( ) > 0 ; } catch ( Exception e ) { return false ; } } }
public EscherOptRecord() { }
public int Read(byte[] buffer, int offset, int length) { lock(this) { if (buffer == null) { throw new ArgumentNullException("buffer"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentOutOfRangeException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this.buffer[pos + i]; } pos += copylen; return copylen; } }
OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Method(string str) { Write(str != null ? str : Convert.ToString((object)null)); }
NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
V() { return base.nextEntry.getValue(); }
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult TagQueue(TagQueueRequest request) { request = beforeClientExecution; return executeTagQueue(request); }
public void Method() { throw new NotSupportedException(); }
CacheSubnetGroup() { request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); }
void (string parameters) { base.SetParams(parameters); language = country = variant = ""; string[] tokens = parameters.Split(','); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
bool (object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails; }
HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
string GetSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname; }
GetDashboardResult GetDashboard(GetDashboardRequest request) { request = BeforeClientExecution(request); return executeGetDashboard; }
public AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution; return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn((short)(j + mbr.getFirstColumn())); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
public static string MethodName(string str) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = str.IndexOf("\\E", apos)) >= 0) { sb.Append(str.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(str.Substring(apos)).Append("\\E").ToString(); }
ByteBuffer(object value) { throw new InvalidOperationException(); }
ArrayPtg(object[,] values2d) { nColumns = values2d.GetLength(1); nRows = values2d.GetLength(0); _nColumns = (byte)nColumns; _nRows = (byte)nRows; object[] vv = new object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = values2d[r, c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution; return ExecuteGetIceServerConfig(request); }
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void Method() { refCount.IncrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return executeUpdateConfigurationSetSendingEnabled(request); }
( ) { return GetXBATEntriesPerBlock() * ; }
void MethodName(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(, tp._divisorShift); } else { MulShift(tp._multiplicand, tp._multiplierShift); } }
string ToString() { StringBuilder b = new StringBuilder(); int l = length; b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(getComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); return this; }
void Method(ProgressMonitor pm) { }
void Method() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
public string Method() { return null; }
( value ) { for ( int i = 0 ; i < mSize ; i ++ ) if ( mValues [ i ] == value ) return - 1 ; }
List<CharsRef> (word[], length) { List<CharsRef> stems = stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses; }
void Method(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
(  n ) { s = ( ) Math . Min ( available ( ) , Math . Max ( 0 , n ) ) ; ptr += s ; }
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
void WriteToOutput(LittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort(field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, output); } else { StringUtil.PutCompressedUnicode(field_6_author, output); } if (field_7_padding != null) { output.WriteByte((byte)field_7_padding.Value); } }
( string str ) { return lastIndexOf; }
bool Method(E obj) { return addLastImpl; }
void (  , string subsection ) { ConfigSnapshot , ; do { src = state . Value ; res = unsetSection ( src , section , subsection ) ; } while ( Interlocked . CompareExchange ( ref state , res , src ) != src ) ; }
public string ToString() { return tagName; }
void InsertSubRecord(int index, SubRecord element) { subrecords.Add(element); }
bool ( ) { lock ( mutex ) { return delegate ( ) . Remove ( o ) ; } }
DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
{ return inCoreLength; }
void SetValue(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
(  i ) { if ( ) throw new IndexOutOfRangeException ( i.ToString() ) ; return entries [ i ] ; }
CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetPath("/repos"); SetMethod(); }
bool (  ) { }
public void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
MergeShardsResult() { request = beforeClientExecution(request); return executeMergeShards(request); }
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return executeAllocateHostedConnection; }
() => { }
public static readonly WeightedTerm GetWeightedTerm(Query query) { return GetTerms(query, false); }
ByteBuffer() { throw new ReadOnlyBufferException(); }
void Method(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
string GetName() { string s = GetPath(); if ("/" == s || "" == s) s = GetHost(); if (s == null) throw new ArgumentException(); string[] elements; if ("file" == scheme || LOCAL_FILE.IsMatch(s)) elements = s.Split(new char[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); else elements = s.Split(new char[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT == result) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution; return executeDescribeNotebookInstanceLifecycleConfig(request); }
public string Method() { return null; }
CreateVpnConnectionResult() { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
DescribeVoicesResult() { request = beforeClientExecution(request); return executeDescribeVoices(request); }
ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions; }
DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult GetApis(GetApisRequest request) { request = beforeClientExecution(request); return executeGetApis(request); }
DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel(request); }
internal TrackingRefUpdate() { }
void Method() { Console.Write(b.ToString()); }
QueryNode() { return getChildren[0]; }
NotIgnoredFilter(workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
AreaRecord(RecordInputStream input) { field_1_formatFlags = input.ReadShort(); }
new GetThumbnailRequest(ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange() { return prefixToOrdRange[dim]; }
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format("{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public E E() { return peekFirstImpl; }
CreateWorkspacesResult() { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
DescribeRepositoriesResult() { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new object[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult() { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public FileStream(string fileName, FileMode mode, FileAccess access) { }
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string MethodName(object value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
HSSFColor(int index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); } byte[] b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(); }
ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void Method() { out.WriteShort((short)field_1_number_crn_records); out.WriteShort((short)field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult DescribeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(character, fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] (char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
UploadArchiveResult UploadArchive(UploadArchiveRequest request) { request = beforeClientExecution; return executeUploadArchive(request); }
List<object> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; int i = 0; foreach(SpanQuery sq in weightBySpanQuery.Keys) { float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); }
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo GetFieldInfo() { return byName[fieldName]; }
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return ExecuteDescribeEventSource(request); }
GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
CancelUpdateStackResult() { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult() { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public ModifyDBProxyResult ModifyDBProxyResult(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy; }
void AddOutput(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { Array.Resize(ref outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[Math.Max(count + 1, (count + 1) * 2)]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[Math.Max(count + 1, (count + 1) * 2)]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool () { return fs.exists; }
public FilterOutputStream(Stream output) { this.output = output; }
ScaleClusterRequest("/clusters/[ClusterId]"); SetMethod(MethodType.PUT);
DataValidationConstraint(operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
{ request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return executeDescribeCacheSubnetGroups; }
void MethodName() { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
bool (  ) { }
ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addAnyChild(t); t.setParent; return t; }
LatvianStemFilterFactory(Dictionary<string, object> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + string.Join(", ", args)); } }
EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory Create(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (object o) { return; }
public bool HasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
void Method() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
RequestSpotInstancesResult() { request = BeforeClientExecution(request); return ExecuteRequestSpotInstances(request); }
() => findObjectRecord.GetObjectData();
GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution; return executeGetContactAttributes(request); }
public override string ToString() { return getKey + ": " + getValue(); }
ListTextTranslationJobsResult() { request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request); }
GetContactMethodsResult() { request = BeforeClientExecution(request); return ExecuteGetContactMethods(request); }
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeAnomalyDetectors(request); }
public static string (string message, ObjectId changeId) { return insertId; }
(AnyObjectId objectId, int typeHint) { var sz = db.GetObjectSize(); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
{ request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult() { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
NumberPtg() { (in.ReadDouble()); }
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution; return executeGetFieldLevelEncryptionConfig(request); }
DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(); }
ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution; return executeReportInstanceStatus(request); }
DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
TokenStream() { return new PortugueseStemFilter(input); }
FtCblsSubRecord ( ) { reserved = new ;
public bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult() { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public string Method() { return null; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
WindowProtectRecord(options) { }
UnbufferedCharStream(int bufferSize) { ; data = new char[bufferSize]; }
GetOperationsResult GetOperations(GetOperationsRequest request) { request = BeforeClientExecution(request); return ExecuteGetOperations(); }
void EncodeMethod(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution; return executeStopWorkspaces(request); }
void Method() { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.truncate(fileLength); } finally { try { channel.close(); } finally { fos.close(); } } } }
{ request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
String(int wordId, char[] surface, int off, int len) { }
String() { }
public static double CalculateVariance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult DescribeResize() { request = beforeClientExecution(request); return executeDescribeResize(request); }
bool GetPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
() { return end; }
void ProcessCells(CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() => { }
(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.Value.CompareTo(this.bytes.Value); else return float.Compare(this.boost, other.boost); }
private int ProcessArray(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
void Method(LittleEndianOutput @out) { @out.WriteShort(); }
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName, KeyType keyType) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public GetAssignmentResult GetAssignmentResult() { request = beforeClientExecution(request); return executeGetAssignment(request); }
bool (AnyObjectId id) { return findOffset(id) != ; }
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v) { lock(this) { DimConfig ft; if (!fieldTypes.TryGetValue(dimName, out ft)) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; } }
() { size = 0; foreach (char c in cells.Keys) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
