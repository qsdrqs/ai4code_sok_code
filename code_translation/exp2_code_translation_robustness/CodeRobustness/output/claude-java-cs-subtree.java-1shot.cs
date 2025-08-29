void WriteToOutput(LittleEndianOutput output) { output.WriteShort(); }
void (BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.tailBlock, 0, src.tailBlkIdx); }
void (  b ) { if ( ) { if ( currentBlock != null ) { addBlock ( currentBlock ) ; } currentBlock = new [ blockSize ] ; upto = 0 ; } currentBlock [ upto ++ ] = b ; }
ObjectId() { }
DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteDomainEntry; }
() { return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + (() ? termsDictOffsets.RamBytesUsed() : 0); }
public string GetMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
POIFSFileSystem() { (true); _header.SetBATCount; _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void (address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < ); }
SubmoduleAddCommand(string path) { this.path = path; return this; }
ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
GetShardIteratorResult(GetShardIteratorRequest request) { request = BeforeClientExecution(request); return ExecuteGetShardIterator; }
ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool () { lock (lockObject) { if (input == null) { throw new System.IO.IOException("InputStreamReader is closed"); } try { return bytes.hasRemaining || input.available() > 0; } catch (System.Exception e) { return false; } } }
EscherOptRecord (  ) { }
public synchronized (  [ ] buffer , offset , length ) { if ( buffer == null ) { throw new ArgumentNullException ( "buffer == null" ) ; } Arrays . checkOffsetAndCount ( buffer . length , offset , length ) ; if ( length == 0 ) { return 0 ; } copylen = count - pos < length ? count - pos : length ; for ( i = 0 ; i < copylen ; i ++ ) { buffer [ offset + i ] = ( ) this . buffer [ pos + i ] ; } ; return copylen ; }
OpenNLPSentenceBreakIterator ( ) { this . sentenceOp = sentenceOp ; }
void Method(string str) { Write(str ?? "null"); }
NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { = functionName; }
V() { return base.nextEntry.getValue(); }
public void (byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult(TagQueueRequest request) { request = beforeClientExecution; return ExecuteTagQueue(request); }
void () { throw new System.NotSupportedException(); }
CacheSubnetGroup() { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
void (string parameters) { base.SetParams(parameters); language = country = variant = ""; StringTokenizer st = new StringTokenizer(parameters, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
DeleteDocumentationVersionResult(DeleteDocumentationVersionRequest request) { request = BeforeClientExecution; return ExecuteDeleteDocumentationVersion(request); }
bool Equals(Object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).Sheetname; }
GetDashboardResult(GetDashboardRequest request) { request = BeforeClientExecution(request); return ExecuteGetDashboard; }
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
void (MulBlankRecord mbr) { for (j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn(())(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt); InsertCell(br); } }
public static string () { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); apos = 0; while ((k = str.IndexOf("\\E", apos)) >= 0) { sb.Append(str.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q"); apos = k + 2; } return sb.Append(str.Substring(apos)).Append("\\E").ToString(); }
ByteBuffer(value) { throw new ReadOnlyBufferException(); }
ArrayPtg(System.Object values2d){nColumns = values2d[0].length;nRows = values2d.length;_nColumns = () nColumns;_nRows = () nRows;System.Object[] vv = new System.Object[_nColumns * _nRows];for(r = 0; r < nRows; r++){System.Object[] rowData = values2d[r];for(c = 0; c < nColumns; c++){vv[getValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
String() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void () { Interlocked.Increment(ref refCount); }
UpdateConfigurationSetSendingEnabledResult(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution; return ExecuteUpdateConfigurationSetSendingEnabled(request); }
( ) { return GetXBATEntriesPerBlock() * ; }
void (int pow10) { TenPower tp = TenPower.getInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
String() { StringBuilder b = new StringBuilder(); l = length; b.Append(Path.DirectorySeparatorChar); for (i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; .SetRoleName(roleName); return this; }
void (ProgressMonitor pm) { ; }
void () { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
string () { return ; }
(value) { for (i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
public virtual IList<CharsRef> Stem(char[] word, int length) { IList<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase); IList<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
GetGatewayResponsesResult (GetGatewayResponsesRequest request) { request = BeforeClientExecution(request); return ExecuteGetGatewayResponses; }
void (  pos ) { currentBlockIndex = ( ) ( pos >> blockBits ) ; currentBlock = ; currentBlockUpto = ( ) ( pos & blockMask ) ; }
(n) { s = Math.Min(available(), Math.Max(0, n)); ptr += s; }
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
void (LittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort(field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, output); } else { StringUtil.PutCompressedUnicode(field_6_author, output); } if (field_7_padding != null) { output.WriteByte((byte)field_7_padding.Value); } }
(string str) { return LastIndexOf; }
bool AddLast(E obj) { return AddLastImpl(obj); }
void (  , string subsection ) { ConfigSnapshot , ; do { src = state . Get ( ) ; res = UnsetSection ( src , section , subsection ) ; } while ( ! state . CompareAndSet ( src , res ) ) ; }
String() { return tagName; }
void (int index, SubRecord element) { subrecords.Add(element); }
bool () { lock (mutex) { return delegate().remove(o); } }
DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
() { return inCoreLength; }
void Method(bool newValue) { }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
(i) { if () throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetResourcePath("/repos"); SetHttpMethod(HttpMethod.POST); }
bool () { }
void () { if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new System.InvalidOperationException(); } } else { throw new System.InvalidOperationException(); } }
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection; }
() => { }
public static WeightedTerm GetTerms(Query query) { return GetTerms(query, false); }
ByteBuffer ( ) { throw new ReadOnlyBufferException ( ) ; }
void (byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
string () { string s = GetPath(); if ("/".Equals(s) || "".Equals(s)) s = GetHost(); if (s == null) throw new ArgumentException(); string[]; if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new char[] { Path.DirectorySeparatorChar, '/' }); else elements = s.Split(new string[] { "/+" }, StringSplitOptions.None); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
DescribeNotebookInstanceLifecycleConfigResult (  DescribeNotebookInstanceLifecycleConfigRequest request ) { request = beforeClientExecution ; return executeDescribeNotebookInstanceLifecycleConfig ( request ) ; }
String() { return; }
CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
GetApisResult(GetApisRequest request) { request = beforeClientExecution; return ExecuteGetApis(request); }
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
TrackingRefUpdate() { }
void () { print(b.ToString()); }
QueryNode() { return GetChildren[0]; }
NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
AreaRecord ( RecordInputStream in ) { field_1_formatFlags = in . readShort ( ) ; }
GetThumbnailRequest(ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult() { request = BeforeClientExecution(request); return ExecuteDescribeTransitGatewayVpcAttachments(request); }
PutVoiceConnectorStreamingConfigurationResult() { request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request); }
OrdRange() { return prefixToOrdRange[dim]; }
string() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
E() { return peekFirstImpl; }
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
NumberFormatIndexRecord() { return copy; }
DescribeRepositoriesResult() { request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new object[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResult() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
RandomAccessFile(string fileName, string mode) { this(new FileInfo(fileName)); }
DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage; }
public static string (  value ) { StringBuilder sb = new StringBuilder ( 16 ) ; writeHex ( sb , value , 16 , "" ) ; return sb . ToString ( ) ; }
UpdateDistributionResult (UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution; }
HSSFColor(index) { if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) { return HSSFColorPredefined.AUTOMATIC.GetColor(); } var b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(); }
ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
void () { out.WriteInt16((short)field_1_number_crn_records); out.WriteInt16((short)field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions() { var options = new InvokeOptions(); options.RequestMarshaller = DescribeDBEngineVersionsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeDBEngineVersionsResponseUnmarshaller.Instance; return Invoke<DescribeDBEngineVersionsResponse>(new DescribeDBEngineVersionsRequest(), options); }
FormatRun(character, fontIndex) { this._character = character; = fontIndex; }
public static byte[] ToByteArray(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return executeUploadArchive(request); }
List<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(Object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery(){SpanQuery[] spanQueries = new SpanQuery[Size()];var sqi = weightBySpanQuery.Keys.GetEnumerator();i = 0;while(sqi.MoveNext()){SpanQuery sq = sqi.Current;boost = weightBySpanQuery[sq];if(boost != 1f){sq = new SpanBoostQuery(sq, boost);}spanQueries[i++] = sq;}if(spanQueries.Length == 1) return spanQueries[0];else return new SpanOrQuery(spanQueries);}
StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo() { return byName[fieldName]; }
DescribeEventSourceResult (DescribeEventSourceRequest request) { request = beforeClientExecution; return executeDescribeEventSource(request); }
GetDocumentAnalysisResult() { request = BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
CancelUpdateStackResult() { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
ModifyLoadBalancerAttributesResult(ModifyLoadBalancerAttributesRequest request) { request = beforeClientExecution; return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResult() { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
ModifyDBProxyResult(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy; }
void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool () { return fs.Exists; }
FilterOutputStream ( ) { this . out = out ; }
ScaleClusterRequest("/clusters/[ClusterId]"); SetMethod(MethodType.PUT);
DataValidationConstraint(operatorType, , string formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResult(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups; }
void () { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool(){  }
ErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent; return t; }
LatvianStemFilterFactory(IDictionary<string, string> args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + args); } }
EventSubscription ( ) { request = BeforeClientExecution ( request ) ; return ExecuteRemoveSourceIdentifierFromSubscription ( request ) ; }
public static TokenFilterFactory(, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult (  GetThreatIntelSetRequest request ) { request = beforeClientExecution ( request ) ; return executeGetThreatIntelSet ; }
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (  Object o ) { return ; }
bool () { return ProtectedHasArray(); }
UpdateContributorInsightsResult (  UpdateContributorInsightsRequest request ) { request = beforeClientExecution ; return executeUpdateContributorInsights ( request ) ; }
void () { records.Remove(fileShare); records.Remove(writeProtect); ; writeProtect = null; }
SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
[ ] ( ) { return findObjectRecord.GetObjectData(); }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
String() { return GetKey + ": " + GetValue(); }
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
GetContactMethodsResult (  ) { request = beforeClientExecution ( request ) ; return executeGetContactMethods ( request ) ; }
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
DescribeAnomalyDetectorsResult(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution; return ExecuteDescribeAnomalyDetectors(request); }
public static String (  String message , ObjectId changeId ) { return insertId ; }
(AnyObjectId objectId, typeHint) throws MissingObjectException, IncorrectObjectTypeException, IOException { sz = db.GetObjectSize; if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
NumberPtg() { (in.ReadDouble()); }
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
DescribeDetectorResult (  DescribeDetectorRequest request ) { request = beforeClientExecution ( request ) ; return executeDescribeDetector ; }
ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return ExecuteReportInstanceStatus(request); }
DeleteAlarmResult (  DeleteAlarmRequest request ) { request = BeforeClientExecution ( request ) ; return ExecuteDeleteAlarm ; }
TokenStream() { return new PortugueseStemFilter(input); }
FtCblsSubRecord() { reserved = new ; }
public virtual bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string () { return ; }
ListStreamProcessorsResult(ListStreamProcessorsRequest request) { request = beforeClientExecution; return executeListStreamProcessors(request); }
DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
WindowProtectRecord(options) { ; }
UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
void (byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
StopWorkspacesResult(StopWorkspacesRequest request) { request = beforeClientExecution; return ExecuteStopWorkspaces(request); }
void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
DescribeMatchmakingRuleSetsResult() { request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request); }
String(wordId, surface[], off, len) { }
String() { }
public static double Variance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResult() { request = BeforeClientExecution(request); return ExecuteDescribeResize(request); }
bool () { return passedThroughNonGreedyDecision; }
( ) { return end ; }
void (CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() => { }
(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.Get().CompareTo(this.bytes.Get()); else return float.Compare(this.boost, other.boost); }
(s[], len) { for (i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = Delete(s, i, len); i--; break; default: break; } } return len; }
void (LittleEndianOutput out) { out.WriteShort(); }
DiagnosticErrorListener ( ) { this . exactOnly = exactOnly ; }
KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
GetAssignmentResult() { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
bool Contains(AnyObjectId id) { return FindOffset(id) != -1; }
GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public virtual void SetMultiValued(string dimName, bool v) { DimConfig ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; }
() { var i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { char c = i.Current; Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return executeDeleteLifecyclePolicy(request); }
