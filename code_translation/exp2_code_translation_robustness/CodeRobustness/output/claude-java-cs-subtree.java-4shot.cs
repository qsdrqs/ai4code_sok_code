void (LittleEndianOutput output) { output.WriteShort; }
void (BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.tailBlock, 0, src.tailBlkIdx); }
void (b) { if () { if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new [blockSize]; upto = 0; } currentBlock[upto++] = b; }
ObjectId() { }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
() { return ((termOffsets != null) ? termOffsets.ramBytesUsed() : 0) + (() ? termsDictOffsets.ramBytesUsed() : 0); }
public virtual string GetFullMessage(){byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length);}
POIFSFileSystem() { (true); _header.SetBATCount; _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0);
void (address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < ); }
SubmoduleAddCommand(String path) { this.path = path; return this; }
ListIngestionsResult() { request = BeforeClientExecution(request); return ExecuteListIngestions(request); }
QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { }
bool () { lock (lockObject) { if (input == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || input.Available > 0; } catch { return false; } } }
EscherOptRecord() { }
public virtual int Read(byte[] buffer, int offset, int length) { lock (this) { if (buffer == null) { throw new ArgumentNullException("buffer == null"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentOutOfRangeException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this.buffer[pos + i]; } pos += copylen; return copylen; } }
OpenNLPSentenceBreakIterator(){this.sentenceOp = sentenceOp;}
void (string str) { Write(str ?? string.Empty); }
NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
V(){return base.nextEntry.GetValue();}
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { Refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); } } else { long after = bufferStart + bufferPosition + len; if (after > Length()) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
void () { throw new NotSupportedException(); }
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request, options);}
void SetParams(string parameters) { base.SetParams(parameters); language = country = variant = ""; var st = new StringTokenizer(parameters, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
bool (object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (i = length - 1; ; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
string GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).Sheetname; }
GetDashboardResult(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard; }
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
void ProcessMulBlankRecord(MulBlankRecord mbr) { for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetColumn((short)(j + mbr.GetFirstColumn())); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string(){StringBuilder sb = new StringBuilder();sb.Append("\\Q");apos = 0;;while ((k = @string.IndexOf("\\E", apos)) >= 0){sb.Append(@string.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q");apos = k + 2;}return sb.Append(@string.Substring(apos)).Append("\\E").ToString();}
ByteBuffer(value) { throw new ReadOnlyBufferException(); }
ArrayPtg(object values2d) { nColumns = values2d[0].length; nRows = values2d.length; _nColumns = () nColumns; _nRows = () nRows; object[] vv = new object[_nColumns * _nRows]; for (r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
public override string ToString() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public override string ToString(string field) { return "ToChildBlockJoinQuery (" + _parentQuery.ToString() + ")"; }
void IncrementRefCount() { refCount.IncrementAndGet(); }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
() { return GetXBATEntriesPerBlock() * ; }
void (int pow10) { TenPower tp = TenPower.getInstance(Math.Abs(pow10)); if (pow10 < 0) { mulShift(, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
string () { StringBuilder b = new StringBuilder(); l = length; b.Append(Path.DirectorySeparatorChar); for (i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); return this; }
void (ProgressMonitor pm) { ; }
void(){if(!first){ptr = 0;if(!eof())parseEntry();}}
E Previous() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new InvalidOperationException(); }
string () { return ; }
(value) { for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
public virtual List<CharsRef> Stem(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
void (pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = ; currentBlockUpto = (int)(pos & blockMask); }
(n) { s = Math.Min(Available(), Math.Max(0, n)); ptr += s; }
BootstrapActionDetail ( ) { SetBootstrapActionConfig ( bootstrapActionConfig ) ; }
void (LittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort(field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, output); } else { StringUtil.PutCompressedUnicode(field_6_author, output); } if (field_7_padding != null) { output.WriteByte((byte)field_7_padding.Value); } }
(string str) => { return LastIndexOf; }
bool Add(E obj) { return AddLastImpl(obj); }
void (  , string subsection ) { ConfigSnapshot , ; do { src = state . Get ( ) ; res = unsetSection ( src , section , subsection ) ; } while ( ! state . CompareAndSet ( src , res ) ) ; }
string () { return tagName; }
void Insert(int index, SubRecord element) { subrecords.Insert(index, element); }
bool Remove(object o) { lock (mutex) { return ((ICollection<T>)@delegate).Remove(o); } }
DoubleMetaphoneFilter(TokenStream input) { return new DoubleMetaphoneFilter(); }
() { return inCoreLength; }
void Method(bool newValue) { }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
(i) { if () throw new System.IndexOutOfRangeException(i.ToString()); return entries[i]; }
CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { ("/repos"); SetMethod(); }
bool () { }
void (){if (expectedModCount == list.modCount){if (lastLink != null){Link next = lastLink.next;Link<ET> previous = lastLink.previous;next.previous = previous;previous.next = next;if (lastLink == link){pos--;}link = previous;lastLink = null;expectedModCount++;list.size--;list.modCount++;}else{throw new java.lang.IllegalStateException();}}else{throw new java.util.ConcurrentModificationException();}}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
() { }
public static WeightedTerm[] ExtractTerms(Query query) { return GetTerms(query, false); }
ByteBuffer() { throw new ReadOnlyBufferException(); }
void (byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public virtual string GetHumanishName(){string s = GetPath();if ("/".Equals(s) || "".Equals(s)) s = GetHost();if (s == null) throw new System.ArgumentException();string[] elements;if ("file".Equals(scheme) || LOCAL_FILE.IsMatch(s)) elements = s.Split(new char[] { System.IO.Path.DirectorySeparatorChar, '/' }, System.StringSplitOptions.RemoveEmptyEntries);else elements = s.Split(new char[] { '/' }, System.StringSplitOptions.RemoveEmptyEntries);if (elements.Length == 0) throw new System.ArgumentException();string result = elements[elements.Length - 1];if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2];else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length);return result;}
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
string () { return ; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
DescribeJobRequest ( , string jobId ) { setVaultName ( vaultName ) ; setJobId ( jobId ) ; }
EscherRecord(index) { return escherRecords.Get; }
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
DeleteSmsChannelResult(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteSmsChannel; }
TrackingRefUpdate() { }
void () { Print(b.ToString()); }
QueryNode() { return GetChildren().Get(0); }
NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
AreaRecord(RecordInputStream input) { field_1_formatFlags = input.ReadShort(); }
GetThumbnailRequest(ProtocolType.HTTPS);
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
PutVoiceConnectorStreamingConfigurationResult(){request = BeforeClientExecution(request);return ExecutePutVoiceConnectorStreamingConfiguration(request);}
OrdRange() { return prefixToOrdRange[dim]; }
string(){string symbol = "";if (startIndex >= 0 && startIndex < getInputStream().size){symbol = getInputStream().getText(Interval.of(startIndex, startIndex));symbol = Utils.escapeWhitespace(symbol, false);}return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol);}
E(){return peekFirstImpl;}
CreateWorkspacesResult() { request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request); }
NumberFormatIndexRecord() { return copy; }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new object[initialCapacity]; mSize = 0; }
HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance;return Invoke<CreateDistributionWithTagsResponse>(request, options);}
RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName)) { }
DeleteWorkspaceImageResult(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage; }
public static string (value) { StringBuilder sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
UpdateDistributionResult(UpdateDistributionRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateDistribution; }
HSSFColor(index) { if (index == HSSFColorPredefined.AUTOMATIC.Index) { return HSSFColorPredefined.AUTOMATIC.Color; } byte[] b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(); }
ValueEval (ValueEval operands, srcRow, srcCol) { throw new NotImplementedFunctionException(_functionName); }
void(){out.WriteShort((short)field_1_number_crn_records);out.WriteShort((short)field_2_sheet_table_index);}
DescribeDBEngineVersionsResponse() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(character, fontIndex) { this._character = character; = fontIndex; }
public static byte[] ToByteArray(char[] chars, int offset, int length){byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for(int i = offset; i < end; ++i){char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
UploadArchiveResult(UploadArchiveRequest request) { request = beforeClientExecution; return ExecuteUploadArchive(request); }
List(tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(Object obj){if(this == obj) return true;if(!base.Equals(obj)) return false;if(GetType() != obj.GetType()) return false;AutomatonQuery other = (AutomatonQuery)obj;if(!compiled.Equals(other.compiled)) return false;if(term == null){if(other.term != null) return false;}else if(!term.Equals(other.term)) return false;return true;}
SpanQuery(){SpanQuery[] spanQueries = new SpanQuery[Size()];var sqi = weightBySpanQuery.Keys.GetEnumerator();i = 0;while(sqi.MoveNext()){SpanQuery sq = sqi.Current;boost = weightBySpanQuery[sq];if(boost != 1f){sq = new SpanBoostQuery(sq, boost);}spanQueries[i++] = sq;}if(spanQueries.Length == 1) return spanQueries[0];else return new SpanOrQuery(spanQueries);}
StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo() { return byName[fieldName]; }
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
GetDocumentAnalysisResult(){request = BeforeClientExecution(request);return ExecuteGetDocumentAnalysis(request);}
CancelUpdateStackResult(){request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = SetInstanceProtectionRequestMarshaller.Instance;options.ResponseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.Instance;return Invoke<SetInstanceProtectionResponse>(request, options);}
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(1 + count, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
bool () { return fs.exists; }
FilterOutputStream() { this.output = output; }
ScaleClusterRequest("/clusters/[ClusterId]"); SetMethod(MethodType.PUT);
DataValidationConstraint(operatorType, , string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request, options);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
void (){field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);}
bool(){}
ErrorNode(Token badToken){ErrorNodeImpl t = new ErrorNodeImpl(badToken);AddAnyChild(t);t.SetParent;return t;}
LatvianStemFilterFactory(IDictionary<string, string> args) : base(args) { if (args.Count > 0) { throw new System.ArgumentException("Unknown parameters: " + args); } }
public virtual EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<EventSubscription>(request, options);}
public static TokenFilterFactory(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (Object o) { return ; }
bool(){return ProtectedHasArray();}
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance;return Invoke<UpdateContributorInsightsResponse>(request, options);}
void () { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
[ ] (  ) { return findObjectRecord.getObjectData ( ) ; }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public override string ToString() { return getKey() + ": " + getValue(); }
ListTextTranslationJobsResult(){request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(new GetContactMethodsRequest(), options);}
public static int GetFunctionIndex(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string (string message, ObjectId changeId) { return insertId; }
(AnyObjectId objectId, int typeHint) { sz = db.GetObjectSize; if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
ImportInstallationMediaResult() { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
NumberPtg() { (input.ReadDouble()); }
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
DescribeDetectorResult(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector; }
ReportInstanceStatusResult(ReportInstanceStatusRequest request) { request = beforeClientExecution; return ExecuteReportInstanceStatus(request); }
DeleteAlarmResult(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm; }
TokenStream() { return new PortugueseStemFilter(input); }
FtCblsSubRecord() { reserved = new byte[0]; }
public virtual bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult() { request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request); }
string () { return ; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
WindowProtectRecord(options) { ; }
UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
void (byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream input) { field_1_h_hold = input.ReadShort(); field_2_v_hold = input.ReadShort(); field_3_width = input.ReadShort(); field_4_height = input.ReadShort(); field_5_options = input.ReadShort(); field_6_active_sheet = input.ReadShort(); field_7_first_visible_tab = input.ReadShort(); field_8_num_selected_tabs = input.ReadShort(); field_9_tab_width_ratio = input.ReadShort(); }
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
void () { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
string (int wordId, char[] surface, int off, int len) { }
string() { }
public static double Variance(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResult(){request = BeforeClientExecution(request);return ExecuteDescribeResize(request);}
bool () { return passedThroughNonGreedyDecision; }
() { return end; }
void (CellHandler handler) { firstRow = range.GetFirstRow(); lastRow = range.GetLastRow(); firstColumn = range.GetFirstColumn(); lastColumn = range.GetLastColumn(); width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty(currentCell) && !traverseEmptyCells) { continue; } rowSize = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width); ctx.ordinalNumber = ArithmeticUtils.AddAndCheck(rowSize, (ctx.colNumber - firstColumn + 1)); handler.OnCell(currentCell, ctx); } } }
() { }
(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.get().CompareTo(this.bytes.get()); else return float.Compare(this.boost, other.boost); }
(s[], len) { for (i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; default: break; } } return len; }
void (LittleEndianOutput @out) { @out.WriteShort; }
DiagnosticErrorListener() { this.exactOnly = exactOnly; }
KeySchemaElement(String attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType.ToString(); }
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request, options);}
bool (AnyObjectId id) { return findOffset(id) != ; }
GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public virtual void SetMultiValued(string dimName, bool v) { DimConfig ft = fieldTypes[dimName]; if (ft == null) { ft = new DimConfig(); fieldTypes[dimName] = ft; } ft.multiValued = v; }
() { IEnumerator<char> i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { char c = i.Current; Cell e = At(c); if (e.cmd >= 0) { size++; } } return size; }
DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request) { request = beforeClientExecution; return executeDeleteVoiceConnector(request); }
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request, options);}
