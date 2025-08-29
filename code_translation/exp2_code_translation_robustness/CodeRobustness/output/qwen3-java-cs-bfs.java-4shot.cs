public virtual void WriteShort(LittleEndianOutput @out) { @out.WriteShort(this.field_1_vcenter); }
public override void Decode() { for (int srcDirIdx = 0; srcDirIdx < src.directory.Length; ++srcDirIdx) { if (src != null) { BlockList.AddAll(src.directory[srcDirIdx].tailBlock, 0, 0, src.tailBlkIdx, 0, BLOCK_SIZE, 0, src.tailDirIdx, 0, 0); } } return; }
public virtual void Process() { if (b) { b = false; } if (currentBlock == null) { currentBlock = new byte[blockSize]; } if (upto == blockSize) { upto = 0; } addBlock(currentBlock); }
public virtual ObjectId GetObjectId(){return objectId;}
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
public virtual long RamBytesUsed() { return (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0) + (termOffsets != null ? ramBytesUsed.termOffsets() : 0); }
public string Decode(byte[] raw) { var buffer = raw; var msgB = buffer; if (msgB.Length < 0) { return ""; } return RawParseUtils.guessEncoding(raw, 0, raw.Length); }
BATBlock[] bb = new BATBlock[1]; bb[0] = CreateEmptyBATBlock(); _header.BATCount = 1; _propertyTable.NextBlock = bb; _batBlocks = bb; _batBlocks[0].BATArray = _header; POIFSFileSystem.StartBlock = FAT_SECTOR_BLOCK; POIFSConstants.END_OF_CHAIN = false; bigBlockSize = 1;
Debug.Assert(address < upto); address = offset0 = upto; Debug.Assert(slice != null); ByteBlockPool.buffers[(address >> BYTE_BLOCK_SHIFT) & BYTE_BLOCK_MASK];
public SubmoduleAddCommand Path(string path) { this.path = path; return this; }
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
new QueryParserTokenManager().SwitchTo(stream, lexState);
public virtual GetShardIteratorResult ExecuteGetShardIterator(GetShardIteratorRequest request) { BeforeClientExecution(request); return request; }
public virtual ModifyStrategyResponse ModifyStrategy(ModifyStrategyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyStrategyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyStrategyResponseUnmarshaller.Instance;return Invoke<ModifyStrategyResponse>(request, options);}
public virtual bool Available(){lock(this){try{if(in==null)throw new IOException("InputStreamReader is closed");return in.Available()>0||HasRemainingBytes();}catch(IOException){return false;}}}
public virtual IEscherOptRecord GetOptRecord(){return _optRecord;}
public int Copylen(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer"); } Arrays.CheckOffsetAndCount(buffer.Length, offset, length); int copylen = 0; int pos = this.pos; if (length > 0) { for (int i = 0; i < length; i++) { buffer[i + pos] = this.CharAt(i + offset); } copylen = length; } return copylen; }
public virtual OpenNLPSentenceBreakIterator CreateSentenceBreakIterator(){var options = new InvokeOptions();options.RequestMarshaller = OpenNLPSentenceBreakIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = OpenNLPSentenceBreakIteratorResponseUnmarshaller.Instance;return Invoke<OpenNLPSentenceBreakIterator>(new OpenNLPSentenceBreakIteratorRequest(), options);}
public virtual void Write(string str, object value){if (str != null){str.ToString();}else{}}
throw new NotImplementedException(functionName, cause);
public override object V() { return base.nextEntry().GetValue(); }
public virtual void Read(byte[] b, int offset, int len) { if (available <= len) { if (useBuffer) { Refill(); if (available == 0) throw new EOFException("read past EOF: " + len); } Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (len > bufferLength) { int after = ReadInternal(b, offset, len); if (after == -1) throw new EOFException("read past EOF: " + len); available = after; } else { Refill(); Array.Copy(buffer, 0, buffer, bufferLength, available); bufferPosition = 0; bufferStart += bufferLength; available += offset; Array.Copy(buffer, 0, b, offset, len); bufferPosition += len; } } }
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
public virtual void Method() { throw new NotSupportedException(); }
return beforeClientExecution(request);
java.util.StringTokenizer st = new java.util.StringTokenizer(params, ","); variant = ""; if (st.HasMoreTokens()) { language = st.NextToken(); } if (st.HasMoreTokens()) { country = st.NextToken(); } if (st.HasMoreTokens()) { variant = st.NextToken(); } base.SetParams(params);
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; if (!(obj is FacetLabel)) return false; var other = (FacetLabel)obj; if (components.Length != other.components.Length) return false; for (int i = components.Length - 1; i >= 0; --i) { if (!components[i].Equals(other.components[i])) return false; } return true; }
GetInstanceAccessDetailsRequest executeGetInstanceAccessDetails = request; request.BeforeClientExecution(request); return GetInstanceAccessDetailsResult;
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor){HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.Parent = this;shape.Anchor = anchor;shapes.Add(shape);OnCreate(shape);return shape;}
return GetSheetname(sheetIndex).GetBoundSheetRec(sheetIndex);
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request, options);}
public virtual AssociateSigninDelegateGroupsWithAccountResult ExecuteAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){BeforeClientExecution(request);return request;}
static { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.XFIndex = mbr.GetXFAt(j); br.Row = mbr.Row; br.Column = (short)(mbr.FirstColumn + j); } }
public static string ToString() { var sb = new StringBuilder(); int apos = 0; int k; while ((k = string.IndexOf("\\E", apos)) >= 0) { sb.Append("\\Q").Append(string.Substring(apos, k - apos)).Append("\\E").Append("\\\\E\\Q"); apos = k + 2; } sb.Append("\\Q").Append(string.Substring(apos)).Append("\\E"); return sb.ToString(); }
public virtual void Method(ByteBuffer buffer) { throw new Exception(); }
int _reserved2Byte = 0; int _reserved1Short = 0; int _reserved0Int = 0; object[] _arrayValues; int _nRows; int _nColumns; object[,] values2d = new object[nRows, nColumns]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { values2d[r, c] = _arrayValues[GetValueIndex(r, c)]; } } int GetValueIndex(int r, int c) { return r * _nColumns + c; }
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
return GetType().Name + " [" + GetValueAsString() + "]";
public override String ToString(){StringBuilder sb=new StringBuilder();sb.Append("ToChildBlockJoinQuery (").Append(parentQuery).Append(")");return sb.ToString();}
public void IncrementAndGet() { }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
return INT_SIZE * LittleEndianConsts.GetXBATEntriesPerBlock();
public virtual void SomeMethod() { TenPower pow10; if (true) { } else { } var tp = TenPower.Instance.MulShift(Math.Abs(tp._multiplicand), tp._divisor, tp._divisorShift, tp._multiplierShift); }
public string Translate(){var b=new StringBuilder();int l=GetComponentCount();for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i<l-1){b.Append(separatorChar);}}return b.ToString();}
public virtual InstanceProfileCredentialsProvider GetProvider() { var fetcher = new ECSMetadataServiceCredentialsFetcher(); fetcher.RoleName = this.roleName; return new InstanceProfileCredentialsProvider(fetcher); }
public virtual void Method(ProgressMonitor pm) { pm = progressMonitor; }
public override void ParseEntry(int ptr, int first) { if (true) { if (true) { ParseEntry(ptr, 0); First(); !Eof(); } } }
public override object Previous() { if (start >= iterator.PreviousIndex()) { throw new NoSuchElementException(); } return iterator.Previous(); }
public virtual string GetNewPrefix() { return this.newPrefix; }
public virtual bool ContainsValue(double value){for(int i=0;i<mSize;i++){if(mValues[i]==value){return true;}}return false;}
var deduped = new List<CharsRef>(stems.Count);foreach(var stem in stems){if(stem.Length>2)deduped.Add(stem);}var terms = new List<CharsRef>();foreach(var term in terms){if(!deduped.Contains(term))deduped.Add(term);}return deduped;
public virtual GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResultUnmarshaller.Instance;return Invoke<GetGatewayResponsesResult>(request, options);}
public virtual void SomeMethod(){pos = currentBlockUpto = currentBlock = currentBlockIndex;blocks[currentBlockIndex];blockMask & (pos >> blockBits);}
public virtual int MethodName(){s=ptr;s+=n;return Math.Min(available, Math.Max(n,0));}
public virtual BootstrapActionDetail SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) {}
public virtual void method(LittleEndianOutput out) { out.WriteByte(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); if (field_7_padding != null) out.WriteByte(0x00); else out.WriteShort(0x01); if (field_5_hasMultibyte) StringUtil.PutCompressedUnicode(out, field_6_author); else StringUtil.PutUnicodeLE(out, field_6_author); out.WriteShort((short)field_6_author.Length); }
public virtual string LastIndexOf(int count, string str) { return str; }
public virtual bool AddLastImpl(object E, object object) { return true; }
public void UnsetSection(IConfigSnapshot src, string section, string subsection) { state = res = src.State; CompareAndSet(...); ... }
public string TagName() { return tagName; }
public virtual void Add(SubRecord element, int index) { subrecords.Add(element, index); }
public bool O(object o){lock(mutex){return remove(o);}}
public static TokenStream CreateDoubleMetaphoneFilter(bool inject, int maxCodeLength, TokenStream input) { return new DoubleMetaphoneFilter(inject, maxCodeLength, input); }
public int GetInCoreLength() { return inCoreLength; }
bool newValue = value;
var pair = new KeyValuePair<ContentSource, ContentSource>(); this.newSource = this.oldSource; this.oldSource = this.newSource;
if (i >= count) throw new System.ArgumentOutOfRangeException(); return entries[i];
public virtual CreateRepoRequest SetMethod() { base("/repos", "cr", "CreateRepo", "2016-06-07", "cr"); PUT.MethodType; return this; }
public virtual bool DeltaBaseAsOffset() { return deltaBaseAsOffset; }
public virtual void SomeMethod(){lock (this){if(expectedModCount != modCount)throw new ConcurrentModificationException();if(lastLink != null)throw new IllegalStateException();expectedModCount++;size--;modCount++;Link next = previous.next;Link lastLink = previous.lastLink;previous = previous.previous;next = next.next;pos--;}}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public virtual AllocateHostedConnectionResult ExecuteAllocateHostedConnection(AllocateHostedConnectionRequest request) { var executeAllocateHostedConnection = request; beforeClientExecution(request); return null; }
public virtual void Start() { return; }
public static Query GetTerms(Query query, bool flag){return query;}
public virtual void Method(ByteBuffer buffer) { throw new java.nio.ReadOnlyBufferException(); }
for (int i = 0; i < iterations; i++) { byte byte0 = blocks[blocksOffset++]; byte byte1 = blocks[blocksOffset++]; byte byte2 = blocks[blocksOffset++]; values[valuesOffset] = (values[valuesOffset] & 0xFF) | ((byte0 & 0xFF) << 2) | ((byte1 & 0xF) >> 4); valuesOffset++; values[valuesOffset] = (values[valuesOffset] & 0xFF) | ((byte1 & 0xFF) << 4) | ((byte2 & 0x3F) >> 6); valuesOffset++; values[valuesOffset] = (values[valuesOffset] & 0xFF) | ((byte2 & 0xFF) << 6); valuesOffset++; }
public static string Normalize(string s) { if (s == null || System.Text.RegularExpressions.Regex.IsMatch(s, @"/+")) { return null; } string[] elements = s.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries); if (elements.Length == 0) { throw new ArgumentException("Invalid path", nameof(s)); } for (int i = 0; i < elements.Length; i++) { if (elements[i].Equals(Constants.DOT_GIT, StringComparison.Ordinal)) { throw new ArgumentException("Path contains invalid element", nameof(s)); } } string result = string.Join("/", elements); if (result.EndsWith(Constants.DOT_GIT, StringComparison.Ordinal)) { result = result.Substring(0, result.Length - Constants.DOT_GIT.Length); } if (result.StartsWith("file://", StringComparison.Ordinal)) { result = result.Substring(7); } if (result.StartsWith("file:", StringComparison.Ordinal)) { result = result.Substring(5); } if (result.StartsWith("/", StringComparison.Ordinal)) { result = result.Substring(1); } if (result.EndsWith("/", StringComparison.Ordinal)) { result = result.Substring(0, result.Length - 1); } return result; }
public override DescribeNotebookInstanceLifecycleConfigResult ExecuteDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return BeforeClientExecution(request); }
public virtual string GetAccessKeySecret() { return this.accessKeySecret; }
public virtual CreateVpnConnectionResult ExecuteCreateVpnConnection(CreateVpnConnectionRequest request){beforeClientExecution(request);return request;}
return ExecuteDescribeVoices(request.BeforeClientExecution(request));
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
public DescribeJobRequest(string jobId, string vaultName) { SetJobId(jobId); SetVaultName(vaultName); }
public virtual EscherRecord Get(int index) { return escherRecords[index]; }
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
return trackingRefUpdate;
public void Print(bool b) { b.ToString(); }
public QueryNode Get() { return GetChildren(0); }
{ NotIgnoredFilter; workdirTreeIndex = workdirTreeIndex[index].this; }
AreaRecord field_1_formatFlags = RecordInputStream.readShort(@in);
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto", HTTPS.ProtocolType) {}
public virtual DescribeTransitGatewayVpcAttachmentsResult ExecuteDescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){return BeforeClientExecution(request);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
public virtual string GetPrefixToOrdRange(int dim){return ordRange != null ? ordRange.ToString() : string.Empty;}
public SomeClass(string symbol, int startIndex) { if (someCondition) { string formatted = string.Format("%s('%s')", GetSimpleName(), symbol); throw new LexerNoViableAltException(Utils.EscapeWhitespace(GetText()), GetInputStream().Substring(Interval.Of(startIndex, startIndex)), false, symbol); } }
public virtual E PeekFirstImpl() { return PeekFirstImpl(); }
public virtual CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResultUnmarshaller.Instance;return Invoke<CreateWorkspacesResult>(request, options);}
public virtual NumberFormatIndexRecord Copy() { return new NumberFormatIndexRecord(); }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity) { mSize = 0; int size = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[size]; mValues = new int[size]; }
public virtual TokenStream HyphenatedWordsFilter(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult ExecuteCreateDistributionWithTags(CreateDistributionWithTagsRequest request) { return BeforeClientExecution(request); }
public class RandomAccessFileWrapper { public RandomAccessFileWrapper(string fileName, string mode) { var fileStream = new System.IO.FileStream(fileName, System.IO.FileMode.Open); } }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string ToString(){return new StringBuilder(16).Append(writeHex(value, 16)).ToString();}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public virtual HSSFColor CustomColor() { return (b == null) ? _palette.GetCustomColor(HSSFColorPredefined.AUTOMATIC) : _palette.GetColor(index).GetCustomColor(); }
public ValueEval _functionName(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void WriteFields(LittleEndianOutput output) { output.WriteShort(field_1_number_crn_records); output.WriteShort(field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResult DescribeDBEngineVersions(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
this._fontIndex = fontIndex; this._character = character;
public static byte[] processChars(char[] chars, int offset, int length, byte[] result, int resultIndex, int end) { for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
return list.GetHiddenTokensToLeft<Token>(tokenIndex - 1);
public override bool Equals(object obj){if(obj == null)return false;if(this == obj)return true;if(!(obj is AutomatonQuery"))return false;AutomatonQuery other=(AutomatonQuery)obj;if(term != null?!term.Equals(other.term):other.term != null)return false;if(compiled != null?!compiled.Equals(other.compiled):other.compiled != null)return false;return base.Equals(obj);}
foreach(var sq in spanQueries){var boost=weightBySpanQuery[sq];if(boost!=1f)new SpanBoostQuery(sq,boost);}
public virtual StashCreateCommand CreateStashCreateCommand(Repo repo) { return new StashCreateCommand(repo); }
public virtual string GetFieldName(){return FieldInfo.ByName(fieldName);}
return ExecuteDescribeEventSource((DescribeEventSourceRequest)BeforeClientExecution(request));
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ExecuteModifyLoadBalancerAttributes(){return ExecuteModifyLoadBalancerAttributes(BeforeClientExecution(new ModifyLoadBalancerAttributesRequest()));}
public SetInstanceProtectionResult ExecuteSetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return result; }
public virtual ModifyDBProxyResponse ExecuteModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
public void SomeMethod() { int[] posLengths = null; int[] endOffsets = null; CharsRefBuilder[] outputs = null; int count = 0; int offset = 0; int len = 0; int posLength = 0; int endOffset = 0; if (count == 0) {} if (count == 0) {} if (count == 0) {} if (count == 0) {} posLengths[count] = offset + len; endOffsets[count] = posLength; outputs[count] = new CharsRefBuilder(); count++; if (count == outputs.Length) { int[] next = (int[])ArrayUtil.Grow(posLengths, 1 + posLengths.Length); Array.Copy(posLengths, 0, next, 0, count); posLengths = next; next = (int[])ArrayUtil.Grow(endOffsets, 1 + endOffsets.Length); Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; outputs = (CharsRefBuilder[])ArrayUtil.Grow(outputs, 1 + outputs.Length); } } static int Oversize(int count, int numBytes) { return (count + 1) * numBytes; }
public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto", HTTPS.ProtocolType) { }
public bool Fs(object objects) { return exists.fs(objects); }
public PassageScorer(Stream out1) { this.out = out1; }
public virtual ScaleClusterResponse SetMethod(ScaleClusterRequest request){var options = new InvokeOptions();options.HttpMethod = "PUT";options.RequestUri = "/clusters/[ClusterId]";options.RequestMarshaller = ScaleClusterRequestMarshaller.Instance;options.ResponseUnmarshaller = ScaleClusterResponseUnmarshaller.Instance;return Invoke<ScaleClusterResponse>(request, options);}
public virtual DataValidationConstraint CreateTimeConstraint(string formula2, string formula1, int operatorType){return new DVConstraint(formula2, formula1, operatorType);}
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request, options);}
public virtual DescribeCacheSubnetGroupsResponse ExecuteDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
public void SharedFormula(bool flag, object field_5_options){SetShortBoolean(flag, field_5_options);}
public virtual bool ReuseObjects() { return reuseObjects; }
public ErrorNode CreateErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); t.SetParent(this); AddAnyChild(t); return t; }
public LatvianStemFilterFactory(Dictionary<string, string> args) { if (args.Count > 0) throw new ArgumentException("Unknown parameters: " + args); }
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory NewInstance(Dictionary<string, string> args, string name, ClassLoader loader) { return new TokenFilterFactory(args, name, loader); }
new AddAlbumPhotosRequest("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", ProtocolType.Https);
public override GetThreatIntelSetResult ExecuteGetThreatIntelSet(GetThreatIntelSetRequest request) { BeforeClientExecution(request); return request; }
public virtual Binary RevFilter() { return new Binary(clone.A, clone.B); }
public bool IsArmenianStemmer(object o) { return o is ArmenianStemmer; }
public bool ProtectedHasArray() { return false; }
public virtual UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateContributorInsightsResultUnmarshaller.Instance;return Invoke<UpdateContributorInsightsResult>(request, options);}
public virtual void MethodName(WriteProtectType writeProtect, FileShareType fileShare) { writeProtect = null; fileShare = null; remove.Records(); remove.Records(); }
public SolrSynonymParser(Analyzer analyzer, bool expand, bool dedup) : base(analyzer, expand, dedup) { this.analyzer = analyzer; this.expand = expand; this.dedup = dedup; }
return (RequestSpotInstancesResult)beforeClientExecution(request);
public virtual object SomeMethod() { return GetObjectData(FindObjectRecord()); }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public virtual string SomeMethod() { return GetKey() + ": " + GetValue(); }
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public virtual GetContactMethodsResult ExecuteGetContactMethods(GetContactMethodsRequest request) { BeforeClientExecution(request); return request; }
public static FunctionMetadata GetFunctionByNameInternal(string name) { FunctionMetadata fd = GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstance(name); } else if (GetIndex(fd) == -1) { fd = GetInstanceCetab(name); } return fd; }
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string InsertId(string message, ObjectId changeId) { return SomeMethod(false, changeId, message); }
if (sz < 0) throw new MissingObjectException(objectId, "unknownObjectType2"); if (typeHint == OBJ_ANY) return new Copy(objectId); throw new IncorrectObjectTypeException(objectId, typeHint);
return executeImportInstallationMedia(beforeClientExecution(request));
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
Sharpen.LittleEndianInput.readDouble(@in);
public override GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = BeforeClientExecution(request); return request; }
public DescribeDetectorResult ExecuteDescribeDetector(DescribeDetectorRequest request){return BeforeClientExecution(request);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request, options);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
return new PortugueseStemFilter(input);
FtCblsSubRecord[] reserved = new FtCblsSubRecord[ENCODED_SIZE];
public virtual bool Remove(bool @synchronized, object c) { return object.Mutex(); }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
public virtual string GetPrecedenceString() { return " >= _p" + precedence; }
public virtual ListStreamProcessorsResult ExecuteListStreamProcessors(ListStreamProcessorsRequest request) { ListStreamProcessorsRequest executeListStreamProcessors = request; return BeforeClientExecution(request); }
public virtual DeleteLoadBalancerPolicyResponse DeleteLoadBalancerPolicy(DeleteLoadBalancerPolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLoadBalancerPolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLoadBalancerPolicyResponseUnmarshaller.Instance;return Invoke<DeleteLoadBalancerPolicyResponse>(request, options);}
public WindowProtectRecord() { _options = options; }
UnbufferedCharStream stream = new UnbufferedCharStream(data = new char[bufferSize], 0, n, bufferSize);
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetOperationsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance; return Invoke<GetOperationsResponse>(request, options); }
public void EncodeInt32(byte[] b, int o, int w1, int w2, int w3, int w4, int w5)
public override void ReadData(RecordInputStream in) { field_9_tab_width_ratio = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_5_options = in.ReadShort(); field_4_height = in.ReadShort(); field_3_width = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_1_h_hold = in.ReadShort(); }
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
public virtual void TranslateMethod() { try { IsOpen(); } finally { isOpen = false; } try { Dump(); } finally { TruncateChannel(fileLength); } channel.Close(); fos.Close(); }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public virtual string SomeMethod(int len, int off, int surface, int wordId) { return null; }
return pathStr;
public static double SomeMethod(double[] v) { if (v == null || v.Length == 0) return 0; int n = v.Length, m = 0; double s = 0; for (int i = 0; i < n; ++i) { if (double.IsNaN(v[i])) continue; s += v[i]; ++m; } if (m >= 1) { double avg = s / m; double variance = 0; for (int i = 0; i < n; ++i) { if (double.IsNaN(v[i])) continue; variance += (v[i] - avg) * (v[i] - avg); } return Math.Sqrt(variance / (m - 1)); } return 0; }
public DescribeResizeResult ExecuteDescribeResize(DescribeResizeRequest request) { return BeforeClientExecution(request); }
public sealed override bool PassedThroughNonGreedyDecision(){return false;}
public virtual int End(){return 0;}
public void TraverseCells() { var ctx = new SimpleCellWalkContext(); ctx.FirstRow = GetFirstRow(); ctx.LastRow = GetLastRow(); ctx.FirstColumn = GetFirstColumn(); ctx.LastColumn = GetLastColumn(); ctx.Width = ctx.LastColumn - ctx.FirstColumn + 1; for (var rowNumber = ctx.FirstRow; rowNumber <= ctx.LastRow; rowNumber++) { var currentRow = GetRow(rowNumber); if (currentRow == null) continue; var rowSize = currentRow.Cells.Count; for (var colNumber = ctx.FirstColumn; colNumber <= ctx.LastColumn; colNumber++) { var currentCell = currentRow.GetCell(colNumber); if (currentCell == null && !IsEmpty(currentRow, colNumber)) continue; if (handler != null) { handler.CellHandler(new CellContext { Row = currentRow, Cell = currentCell, RowIndex = rowNumber, ColumnIndex = colNumber, Ordinal = ArithmeticUtils.OrdinalNumber(ctx) }); } } } }

public virtual float CompareTo(ScoreTerm other){if (other.boost != this.boost.get()) return this.boost.get() - other.boost; return this.bytes.compareTo(other.bytes);}
public virtual void ProcessCharacters(int[] s, ref int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case Constants.HAMZA_ABOVE: break; case Constants.HEH_GOAL: case Constants.HEH_YEH: break; case Constants.KEHEH: break; case Constants.YEH_BARREE: case Constants.FARSI_YEH: --i; len = DeleteCharacter(s, i, len); break; } } }
public void WriteShort(LittleEndianOutput @out, Options _options)
public class DiagnosticErrorListener { private bool exactOnly; public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; } }
public override string ToString() => $"KeySchemaElement [keyType={KeyType}, attributeName={AttributeName}]";
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request, options);}
} { ) (  bool ; return id != 1 - findOffset ) id (
public virtual GroupingSearch SomeMethod() { bool allGroups = this.allGroups; return this; }
public DimConfig(string dimName, bool v) { if (fieldTypes.Put(dimName, ft = new DimConfig()) == null) { ft.MultiValued = (this.V = v); } }
public IEnumerator<Character> GetEnumerator(){int i=0;int size=cells.Count;while(i<size){yield return cells[i++];}}
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
public virtual DeleteLifecyclePolicyResult ExecuteDeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { return BeforeClientExecution(request); }
