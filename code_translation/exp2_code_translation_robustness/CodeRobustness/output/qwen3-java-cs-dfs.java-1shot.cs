internal virtual void WriteShort(LittleEndianOutput @out, int field_1_vcenter) { @out.WriteShort(field_1_vcenter); }
public override void BlockList<T>(src) { if (src.tailBlkIdx != 0) addAll(tailBlkIdx.src, 0, src.tailBlock); for (srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(directory[srcDirIdx], 0, BLOCK_SIZE); if (src.size == 0) return; }
public void Method(byte b){if(upto==blockSize){if(currentBlock!=null){currentBlock=new byte[blockSize];AddBlock(currentBlock);}}currentBlock[upto++] = b;upto=0;}
public virtual ObjectId ObjectId() { throw new System.NotImplementedException(); }
public DeleteDomainEntryResult ExecuteDeleteDomainEntry(DeleteDomainEntryRequest request) { BeforeClientExecution(request); return ExecuteDeleteDomainEntry(request); }
return (null != termOffsets ? ramBytesUsed() : 0) + (null != termsDictOffsets ? ramBytesUsed() : 0);
public virtual string Method() { buffer = raw; msgB = RawParseUtils.TagMessage(0, raw); if (msgB < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(), raw, msgB, raw.Length); }
public POIFSFileSystem() { _propertyTable.StartBlock = 0; POIFSConstants.SetNextBlock(FAT_SECTOR_BLOCK, 1); POIFSConstants.SetNextBlock(END_OF_CHAIN, 0); _batBlocks.Add(bb); bb.OurBlockIndex = 1; BATBlock bb = BATBlock.CreateEmptyBATBlock(false, bigBlockSize); _header.BATArray = new BATBlock[] { }; _header.BATCount = 1; true; new POIFSFileSystem(); }
public virtual void Address(int address){Debug.Assert(length.slice < upto);address = offset0;address = (ByteBlockPool)(BYTE_BLOCK_MASK & address);Debug.Assert(slice != null);slice = buffers.pool[BYTE_BLOCK_SHIFT >> address];}
public class SubmoduleAddCommand { private List<string> path = new List<string>(); public SubmoduleAddCommand() { path.Add("this"); path.Add("return"); path.Add(";"); } public List<string> Path { get { return path; } } }
public ListIngestionsResult ListIngestionsRequest(ListIngestionsRequest request) { request = beforeClientExecution(request); return executeListIngestions(request); }
new QueryParserTokenManager(new CharStream(stream, lexState), lexState);
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request) { beforeClientExecution(request); return executeGetShardIterator(request); }
[HttpPost][MethodType("vipaegis","ModifyStrategy","2016-11-11","aegis")] public class ModifyStrategyRequest : object { public ModifyStrategyRequest() : base() {} }
public bool Available() { lock (lock) { if (@in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || @in.Available > 0; } catch (IOException) { return false; } } }
return new EscherOptRecord { _optRecord = _optRecord };
public int Method(char[] buffer, int offset, int length, int pos, int count) { lock (this) { if (buffer == null) throw new ArgumentNullException("buffer", "buffer == null"); if (offset < 0 || length < 0 || offset + length > buffer.Length) throw new ArgumentOutOfRangeException(); if (length == 0) return 0; int copylen = Math.Min(length, pos - count); for (int i = 0; i < copylen; i++) buffer[offset + i] = buffer[pos + i]; return copylen; } }
sentenceOp = new NLPSentenceDetectorOp(OpenNLPSentenceBreakIterator);
public static void Write(string str){if(str != null)Convert.ToString((object)null);}
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { this.functionName = functionName; }
public override V GetNextEntry() { return base.GetValue(); }
public void ReadInternal(byte[] b, int offset, int len, bool useBuffer) { bufferLength = 0; bufferPosition = 0; after = bufferStart; int available = bufferStart + bufferLength - bufferPosition; if (available <= len) { if (useBuffer && bufferSize < len) { refill(); if (len < bufferLength) { Array.Copy(buffer, 0, b, offset, len); } else { throw new EOFException(this + "read past EOF: "); } } else { Array.Copy(buffer, bufferPosition, b, offset, len); len = bufferPosition + len; } } else { Array.Copy(buffer, bufferPosition, b, offset, available); bufferPosition += available; len += bufferPosition; } if (after > length) throw new EOFException(this + "read past EOF: "); }
public virtual TagQueueResult TagQueueRequest(RequestType request) { beforeClientExecution = request; return executeTagQueue(request); }
public void Method() { throw new NotSupportedException(); }
public CacheSubnetGroup ModifyCacheSubnetGroupRequest(Request request) { request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request); }
public virtual void setParams(string params) { base.setParams(params); java.util.StringTokenizer st = new java.util.StringTokenizer(params, ","); string variant = "", country = "", language = ""; if (st.hasMoreTokens()) variant = st.nextToken(); if (st.hasMoreTokens()) country = st.nextToken(); if (st.hasMoreTokens()) language = st.nextToken(); }
public DeleteDocumentationVersionResult ExecuteDeleteDocumentationVersion(DeleteDocumentationVersionRequest request){beforeClientExecution = request;return request;}
public override bool Equals(object obj) { if (!(obj is FacetLabel other)) { return false; } if (other.length != length) { return false; } for (int i = length - 1; i >= 0; --i) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResult GetInstanceAccessDetailsRequest(request) { return executeGetInstanceAccessDetails(beforeClientExecution(request)); }
public HSSFPolygon(HSSFPatriarch parent, HSSFChildAnchor anchor) { _parent = parent; _anchor = anchor; } HSSFPolygon shape = new HSSFPolygon(this, anchor); shapes.Add(shape); return shape;
public virtual string GetSheetname(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
public static GetDashboardResult ExecuteGetDashboard(GetDashboardRequest request) { beforeClientExecution = request; return ExecuteGetDashboard(request); }
public override AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccountRequest(Request request) { request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
public static void MulBlankRecord() { for (int j = 0; j < mbr.GetNumColumns(); j++) { br = new BlankRecord(); br.SetColumn(j + mbr.GetFirstColumn()); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); } }
public static string StringMethod(string stringParam){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0;int k;while((k=stringParam.IndexOf("\\E",apos))>=0){sb.Append(stringParam.Substring(apos,k-apos)).Append("\\\\E\\Q");apos=k+2;}sb.Append(stringParam.Substring(apos)).Append("\\E");return sb.ToString();}
public virtual void Method(ByteBuffer value) { throw new ReadOnlyBufferException(); }
public virtual void ArrayPtg(object[][] values2d) { _reserved2Byte = 0; _reserved1Short = 0; _reserved0Int = 0; int nColumns = values2d[0].Length; int nRows = values2d.Length; object[] vv = new object[nRows * nColumns]; for (int r = 0; r < nRows; r++) { object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; }
GetIceServerConfigResult = GetIceServerConfigRequest(request); request = beforeClientExecution(request); return executeGetIceServerConfig(request);
public override string ToString() { return GetType() + " [" + Name + "] " + ValueAsString; }
public string SomeMethod(string field){return parentQuery.ToString()+"ToChildBlockJoinQuery("+field+")";}
public void IncrementAndGet() { refCount.IncrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
return (LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock());
public override void pow10(int pow10) { TenPower tp = TenPower.getInstance().Math.Abs(pow10); if (pow10 < 0) { mulShift(_divisor._tp, _divisorShift._tp); } else { mulShift(_multiplicand._tp, _multiplierShift._tp); } }
public override string ToString() { var b = new StringBuilder(); int l = Length(); b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; ++i) { b.Append(Path.DirectorySeparatorChar); if (i < l - 1) { b.Append(GetComponent(i)); } } return b.ToString(); }
public virtual InstanceProfileCredentialsProvider(string roleName) { this.fetcher = new ECSMetadataServiceCredentialsFetcher(this.fetcher).SetRoleName(roleName); return this; }
public virtual void ProgressMonitor(){var pm = new ProgressMonitor();}
public virtual void SomeMethod(){if(!first){if(!eof){ParseEntry();}}ptr=0;}
public override E Next() { if (start >= previousIndex().iterator()) { return previous().iterator(); } throw new NoSuchElementException(); }
public String NewPrefix() { return this.newPrefix; }
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1;
List<CharsRef> stems = stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(ignoreCase.dictionary, 8); List<CharsRef> deduped = new List<CharsRef>(); foreach (var s in stems) { if (!terms.Contains(s)) { deduped.Add(s); } } return deduped;
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
public virtual void MethodName(int pos){currentBlockIndex = (pos >> blockBits) & blockMask;currentBlock = blocks[currentBlockIndex];currentBlockUpto = pos;}
for (s = Math.Min(available(), Math.Max(0, n)); s < ptr; s += ptr) { return s; }
public void SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig, BootstrapActionDetail detail)
public void Write(LittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort((short)field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) StringUtil.PutCompressedUnicode(output, field_6_author); else StringUtil.PutUnicodeLe(output, field_6_author); if (field_7_padding != null) output.WriteByte(field_7_padding.intValue()); }
public static int lastIndexOf(java.lang.String string, int count) { return ...; }
public bool AddLastImpl(E @object) { return true; }
public void unsetSection(string section, string subsection) { do { ConfigSnapshot src = state.Get(); ConfigSnapshot res = UnsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res)); }
public string TagName() { return null; }
public void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public virtual bool Remove(object o) { lock(mutex) { return delegate.Remove(o); } }
public virtual DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input, int maxCodeLength, InjectType inject) { return new DoubleMetaphoneFilter(inject, maxCodeLength, input); }
public static int GetInCoreLength(){return inCoreLength;}
public virtual void SetValue(bool newValue) { newValue = value; }
public virtual (ContentSource, ContentSource) CreatePair() { var newSource = this; var oldSource = this; return (newSource, oldSource); }
public virtual SomeType SomeMethod(int i) { if (i <= count) { throw new ArrayIndexOutOfBoundsException(i); } return entries[i]; }
public static class CreateRepoRequest { public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { } } [PUT.MethodType(setMethod, "/repos")] public class CreateRepoRequest { }
bool DeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount == expectedModCount) { if (lastLink != null) { if (link == lastLink) --pos; next = next.previous; previous = previous.next; previous.lastLink = (Link<ET>)previous; next.lastLink = (Link<ET>)next; } } else { throw new ConcurrentModificationException(); } else { throw new InvalidOperationException(); } ++modCount; --size; ++expectedModCount; lastLink = null; previous = link;
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request) { return ExecuteMergeShards(BeforeClientExecution(request)); }
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
public void Method(){start:return;}
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
public virtual ByteBuffer SomeMethod() { throw new ReadOnlyBufferException(); }
public void method(int iterations, int valuesOffset, short[] values, int blocksOffset, byte[] blocks) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (short)(byte0 >> 2); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (short)((byte0 & 3) << 4 | (byte1 >> 4)); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (short)((byte1 & 15) << 2 | (byte2 >> 6)); } }
public virtual string TranslateMethod() { string s = GetPath(); if (s == null || s.Equals("") || s.Equals("/")) s = GetHost(); string[] elements = Regex.Split(s, "[\\" + Path.DirectorySeparatorChar + "/]+"); if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (!result.EndsWith(Constants.DOT_GIT_EXT)) { if (result.Equals(Constants.DOT_GIT)) result = elements[elements.Length - 2]; } if (!string.IsNullOrEmpty(result) && result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public sealed override DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfigRequest(Request request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
public virtual string accessKeySecret() { return this.accessKeySecret; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public virtual ReceiveCommand.DescribeVoicesResult DescribeVoicesRequest(ReceiveCommand.DescribeVoicesRequest request) { beforeClientExecution = request; return executeDescribeVoices(request); }
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
public DescribeJobRequest(string jobId, string vaultName) { SetJobId(jobId); SetVaultName(vaultName); }
public EscherRecord get(int index) { return escherRecords[index]; }
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public static TrackingRefUpdate TrackingRefUpdate() { return new TrackingRefUpdate(); }
public void Print(bool b){Convert.ToString(b);}
public virtual QueryNode GetFirstChild() { return GetChildren()[0]; }
new NotIgnoredFilter(index => { this.workdirTreeIndex = index; });
public AreaRecord(RecordInputStream @in) { field_1_formatFlags = @in.ReadShort(); }
public virtual GetThumbnailResponse GetThumbnail(GetThumbnailRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetThumbnailRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThumbnailResponseUnmarshaller.Instance; return Invoke<GetThumbnailResponse>(request, options); }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
public virtual OrdRange @get(string dim) { return prefixToOrdRange.@get(dim); }
string symbol = ""; if (GetInputStream().Size() > startIndex && startIndex >= 0) { symbol = GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(CultureInfo.GetDefault(), "%s('%s')", GetSimpleName(), symbol);
public virtual T PeekFirstImpl() { return PeekFirstImpl(); }
public virtual CreateWorkspacesResult CreateWorkspacesRequest(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public override NumberFormatIndexRecord Copy() { return new NumberFormatIndexRecord(); }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity) { initialCapacity = java.util.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public virtual HyphenatedWordsFilter HyphenatedWordsFilter(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult CreateDistributionWithTagsRequest() { return executeCreateDistributionWithTags(request).beforeClientExecution(request); }
public class RandomAccessFile { public RandomAccessFile(string fileName, string mode) { new File(fileName, mode); } }
public DeleteWorkspaceImageResult DeleteWorkspaceImageRequest(DeleteWorkspaceImageRequest request){beforeClientExecution = request;return executeDeleteWorkspaceImage(request);}
public static string HexString(int value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public virtual UpdateDistributionResult UpdateDistributionRequest(Request request) { beforeClientExecution = request; return request.executeUpdateDistribution(); }
if (HSSFColorPredefined.AUTOMATIC.getIndex() == index) return HSSFColorPredefined.AUTOMATIC.getColor(); _palette[index] = new CustomColor(b, index); return b == null ? null : new CustomColor(b, index);
public ValueEval _functionName(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public virtual void write(LittleEndianOutput @out) { writeShort(field_1_number_crn_records, @out); writeShort(field_2_sheet_table_index, @out); }
public DescribeDBEngineVersionsRequest DescribeDBEngineVersions() { return new DescribeDBEngineVersionsRequest(); }
public void FormatRun(int fontIndex, char character) { this.fontIndex = fontIndex; this.character = character; }
public static byte[] method(char[] chars, int offset, int length) { byte[] result = new byte[2 * length]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)ch; result[resultIndex++] = (byte)(ch >> 8); } return result; }
public UploadArchiveResult UploadArchiveRequest(Request request) { beforeClientExecution = request; return executeUploadArchive(request); }
public virtual List<Token> GetHiddenTokens(int tokenIndex) => GetHiddenTokensToLeft(tokenIndex, 1);
public override bool Equals(object obj) { if (obj == this) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = obj as AutomatonQuery; if (other == null) return false; if (compiled != null ? !compiled.Equals(other.compiled) : other.compiled != null) return false; if (term != null ? !term.Equals(other.term) : other.term != null) return false; return true; }
public static SpanQuery CreateSpanQuery(Dictionary<SpanQuery, float> weightBySpanQuery) { SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Keys.Count]; int i = 0; IEnumerator<SpanQuery> sqi = weightBySpanQuery.Keys.GetEnumerator(); while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1.0f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) { return spanQueries[0]; } else { return new SpanOrQuery(spanQueries); } }
public StashCreateCommand StashCreateCommand(Repo repo) { return new StashCreateCommand(repo); }
public virtual FieldInfo GetFieldByName(string fieldName) { return byName.Get(fieldName); }
public sealed override DescribeEventSourceResult executeDescribeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
public override GetDocumentAnalysisResult GetDocumentAnalysisRequest(Request request) { BeforeClientExecution(request); return ExecuteGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStackRequest(Request request){request = BeforeClientExecution(request);return ExecuteCancelUpdateStack(request);}
BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(new ModifyLoadBalancerAttributesRequest(request));
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = SetInstanceProtectionRequestMarshaller.Instance;options.ResponseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.Instance;return Invoke<SetInstanceProtectionResponse>(request, options);}
public ModifyDBProxyResult ExecuteModifyDBProxyRequest(ModifyDBProxyRequest request) { request = BeforeClientExecution(request); return ExecuteModifyDBProxy(request); }
++count; posLength = posLengths[count]; endOffset = endOffsets[count]; if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } output = outputs[count].copyChars(len, offset); if (posLengths.Length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, Integer.BYTES)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (endOffsets.Length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, Integer.BYTES)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (outputs.Length == count) { outputs = ArrayUtil.grow(outputs, count + 1); } public void copyChars(int posLength, int endOffset, int len, int offset, CharsRefBuilder output)
public class FetchLibrariesRequest : SomeBaseClass { public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") {} }
public virtual bool Exists(Objects objects){var options = new InvokeOptions();options.RequestMarshaller = ExistsRequestMarshaller.Instance;options.ResponseUnmarshaller = BooleanResponseUnmarshaller.Instance;return Invoke<bool>(objects, options);}
public class FilterOutputStream : Stream { public FilterOutputStream(Stream outStream) : base() { this.out = outStream; } }
public override void PUT_MethodType_setMethod() { base.ScaleClusterRequest("csk", "ScaleCluster", "2015-12-15", "CS"); }
public virtual DataValidationConstraint CreateTimeConstraint(string formula1, string formula2, int operatorType){return DVConstraint.CreateTimeConstraint(formula2, formula1, operatorType);}
public virtual ListObjectParentPathsResult ListObjectParentPathsRequest(Request request) { throw new System.NotImplementedException(); }
public override DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request); }
public virtual void SetShortBoolean(bool flag, object field_5_options) { sharedFormula = field_5_options; }
public virtual bool ReuseObjects() { return reuseObjects; }
ErrorNodeImpl t = new ErrorNodeImpl(badToken); t.SetParent(this); AddAnyChild(t); return t;
new LatvianStemFilterFactory<Dictionary<string, string>>(args => { if (args.Count != 0) throw new ArgumentException("Unknown parameters: " + args); });
public virtual EventSubscription RemoveSourceIdentifierFromSubscriptionRequest(Request request) { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory NewInstance(string name, Dictionary<string, string> args) { return loader; }
public AddAlbumPhotosRequest() : base(HTTPS.ProtocolType, "cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") { }
public GetThreatIntelSetResult GetThreatIntelSetRequest(Request request) { request = BeforeClientExecution(request); return ExecuteGetThreatIntelSet(request); }
public override Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public bool ArmenianStemmer(object o) { return o is ArmenianStemmer; }
public bool HasArray() { return protectedHasArray; }
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance;return Invoke<UpdateContributorInsightsResponse>(request, options);}
public virtual void SomeMethod() { writeProtect = null; fileShare = null; remove.records(writeProtect); remove.records(fileShare); }
public SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base(analyzer, expand, dedup) { this.expand = expand; }
public override RequestSpotInstancesResult ExecuteRequestSpotInstances(RequestSpotInstancesRequest request) { return BeforeClientExecution(request); }
public virtual object FindObjectRecord(){var options = new InvokeOptions();options.RequestMarshaller = FindObjectRecordRequestMarshaller.Instance;options.ResponseUnmarshaller = FindObjectRecordResponseUnmarshaller.Instance;return Invoke<object>(options);}
public GetContactAttributesResult GetContactAttributesRequest(GetContactAttributesRequest request){request = (GetContactAttributesRequest)beforeClientExecution(request); return executeGetContactAttributes(request);}
(key) => key.GetKey() + ": " + key.GetValue();
public ListTextTranslationJobsResult ExecuteListTextTranslationJobs(ListTextTranslationJobsRequest request) { request = BeforeClientExecution(request); return request.Execute(); }
public override GetContactMethodsResult ExecuteGetContactMethods(GetContactMethodsRequest request) { request = beforeClientExecution(request); return request; }
public static FunctionMetadata getFunctionByName(string name){FunctionMetadata fd=getInstance().getFunctionByNameInternal(name);if(fd==null){fd=getInstanceCetab().getFunctionByNameInternal(name);}if(fd==null){return -1;}return fd.getIndex();}
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { beforeClientExecution(request); return executeDescribeAnomalyDetectors(request); }
public static string insertId(ObjectId changeId, string message){return insertId(false, changeId, message);}
public int GetObjectSize(AnyObjectId objectId, int typeHint) { if (typeHint == OBJ_ANY) { if (sz < 0) throw new MissingObjectException(objectId.Copy(), JGitText.Instance.UnknownObjectType2); sz = db.GetObjectSize(objectId, this); } return sz; }
public ImportInstallationMediaResult ImportInstallationMediaRequest(Request request){request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult ExecutePutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request); }
public class NumberPtg { static NumberPtg() { var in = new LittleEndianInput(); in.ReadDouble(); } }
public virtual GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfigRequest(GetFieldLevelEncryptionConfigRequest request){this.beforeClientExecution = request;return executeGetFieldLevelEncryptionConfigRequest(request);}
public virtual DescribeDetectorResult DescribeDetectorRequest(DescribeDetectorRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeDetector(request); }
public ReportInstanceStatusResult ReportInstanceStatusRequest(Request request) { beforeClientExecution(request); return executeReportInstanceStatus(request); }
public DeleteAlarmResult ExecuteDeleteAlarm(DeleteAlarmRequest request){request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request);}
public TokenStream TokenStream(TokenStream input_1) { return new PortugueseStemFilter(input_1); }
FtCblsSubRecord[] reserved = new FtCblsSubRecord[ENCODED_SIZE];
public override bool Remove(object c) { lock (mutex) { return base.Remove(c); } }
return this.executeGetDedicatedIp(request, beforeClientExecution = (r) => new this._enclosing.GetDedicatedIpResult(r));
return " >= _p" + precedence;
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { LoadBalancerName = loadBalancerName; PolicyName = policyName; }
public virtual WindowProtectRecordResponse WindowProtectRecord(WindowProtectRecordRequest request){var options = new InvokeOptions();options.RequestMarshaller = WindowProtectRecordRequestMarshaller.Instance;options.ResponseUnmarshaller = WindowProtectRecordResponseUnmarshaller.Instance;return Invoke<WindowProtectRecordResponse>(request, options);}
new UnbufferedCharStream(bufferSize) { data = new char[bufferSize], n = 0 }
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
public virtual void EncodeInt32(int[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5); }
public class WindowOneRecord { public WindowOneRecord(RecordInputStream inStream) { field_9_tab_width_ratio = ReadShort(inStream); field_8_num_selected_tabs = ReadShort(inStream); field_7_first_visible_tab = ReadShort(inStream); field_6_active_sheet = ReadShort(inStream); field_5_options = ReadShort(inStream); field_4_height = ReadShort(inStream); field_3_width = ReadShort(inStream); field_2_v_hold = ReadShort(inStream); field_1_h_hold = ReadShort(inStream); } }
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
public void MethodName() { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.Truncate(fileLength); } finally { fos.Close(); } } } }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public string SomeMethod(int len, int off, SomeType[] surface, int wordId) { return null; }
public override string GetPathStr() { return pathStr; }
public static double m(double[] v) { double r = double.NaN; if (v != null && v.Length >= 1) { int n = v.Length; double s = 0; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); r = Math.Sqrt(s); } return r; }
public virtual DescribeResizeResult describeResize(DescribeResizeRequest request) { request = beforeClientExecution(request); return executeDescribeResize(request); }
public sealed bool PassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int End() { return 0; }
public void CellHandler(SimpleCellWalkContext ctx){Cell currentCell = null;Row currentRow = null;int firstRow = range.GetFirstRow();int lastRow = range.GetLastRow();int firstColumn = range.GetFirstColumn();int lastColumn = range.GetLastColumn();int width = 1 + firstColumn - lastColumn;ctx.OrdinalNumber = ArithmeticUtils.MultiplyAndCheck(ctx.RowSize, (ctx.RowNumber - firstRow)) + (ctx.ColNumber - firstColumn + 1);for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ctx.RowNumber++){currentRow = sheet.GetRow(ctx.RowNumber);if (currentRow == null)continue;for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ctx.ColNumber++){currentCell = currentRow.GetCell(ctx.ColNumber);if (currentCell == null)continue;if (!currentCell.IsEmpty() || traverseEmptyCells){ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(ArithmeticUtils.MultiplyAndCheck(ctx.RowSize, (ctx.RowNumber - firstRow)), (ctx.ColNumber - firstColumn + 1));if (!ctx.Eof)ctx.ParseEntry();}}}}
public PosType Pos(){return (PosType)Build(null, reader, 0, 0);}
public int CompareTo(ScoreTerm other){if(boost==other.boost){return this.bytes.Get().CompareTo(other.bytes.Get());}else{return boost.CompareTo(other.boost);}}
for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: len = delete(len, i, s); break; case KEHEH: len = delete(len, i, s); break; case KAF: len = delete(len, i, s); break; case HEH_YEH: case HEH_GOAL: len = delete(len, i, s); break; case HAMZA_ABOVE: len = delete(len, i, s); break; } } return len;
public virtual void WriteShort(LittleEndianOutput output)
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(KeyType keyType, string attributeName) { SetAttributeName(attributeName); SetKeyType(keyType.ToString()); }
public virtual GetAssignmentResult GetAssignmentRequest(Request request) { request = BeforeClientExecution(request); return ExecuteGetAssignment(request); }
public virtual bool FindOffset(AnyObjectId id) { return findOffset != 1; }
public GroupingSearch GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public virtual void DimConfig(string dimName, bool v) { if (ft == null) { ft = new DimConfig(); } fieldTypes[dimName] = ft; ft.multiValued = v; }
public int size(){int size=0;IEnumerator<Character> i=cells.Keys.GetEnumerator();while(i.MoveNext()){Character c=i.Current;if(c.e>=0){++size;}}return size;}
public DeleteVoiceConnectorResult DeleteVoiceConnectorRequest(Request request) { beforeClientExecution = request; return executeDeleteVoiceConnector(request); }
public virtual DeleteLifecyclePolicyResult ExecuteDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteLifecyclePolicy(request); }
