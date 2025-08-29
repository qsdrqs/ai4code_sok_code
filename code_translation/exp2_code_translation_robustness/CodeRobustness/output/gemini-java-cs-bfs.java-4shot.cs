public virtual void Write(LittleEndianOutput output){output.WriteShort(field_1_vcenter);}
} { ) (  void if ) ; ; ( for ; if src ; ) ( ; ++ srcDirIdx < srcDirIdx 0 = srcDirIdx ; return ) ( BlockList AddRange 0 != AddRange tailDirIdx . src 0 == > T < ) , 0 , ( tailBlkIdx . src ) BLOCK_SIZE , 0 , ( size . src tailBlkIdx . src tailBlock . src ] srcDirIdx [ directory . src
if (upto == blockSize) { if (currentBlock != null) { AddBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b;
public virtual ObjectId ObjectId() { return objectId; }
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public string Decode(byte[] raw){int msgB = RawParseUtils.TagMessage(raw, 0);if (msgB < 0)return "";return RawParseUtils.Decode(RawParseUtils.GuessEncoding(this), raw, msgB, raw.Length);}
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(0); _bat_blocks.Add(bb); _header.SetBATCount(1); _header.SetBATArray(new int[] { 0 }); _property_table.SetStartBlock(0); _property_table.SetNextBlock(POIFSConstants.END_OF_CHAIN);
} { ) (  void ; Debug.Assert ; ; ; Debug.Assert ; address < upto address = offset0 = upto null != slice = slice Length . slice & address ] [ ByteBlockPool.BYTE_BLOCK_MASK >> address buffers . pool ByteBlockPool.BYTE_BLOCK_SHIFT
public virtual NGit.Api.SubmoduleAddCommand Path(string path){this.path = path;return this;}
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
public QueryParserTokenManager(CharStream stream, int lexState) : base(stream){ SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){request = BeforeClientExecution(request);return ExecuteGetShardIterator(request);}
public ModifyStrategyRequest():base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis"){this.Method = "POST";}
public bool Ready() { lock (this) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining || in.DataAvailable; } catch (IOException) { return false; } } }
public EscherOptRecord GetOptRecord() { return _optRecord; }
public int copy(char[] buffer, int offset, int length){if (buffer == null) throw new ArgumentNullException("buffer", "buffer == null"); if (length == 0) return 0; int copylen = (this.buffer.Length - pos < length) ? this.buffer.Length - pos : length; for (int i = 0; i < copylen; ++i) buffer[offset + i] = this.buffer[pos + i]; pos += copylen; return copylen;}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp){this.sentenceOp = sentenceOp;}
void Write(string str){object obj = str;}
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { this.FunctionName = functionName; }
} { ) (  V ; return GetValue . ) ( NextEntry . base ) (
public virtual void ReadBytes(byte[] b, int offset, int len){if (len <= (_bufferLength - _bufferPosition)){if (len > 0){Buffer.BlockCopy(_buffer, _bufferPosition, b, offset, len);}_bufferPosition += len;}else{int available = _bufferLength - _bufferPosition;if (available > 0){Buffer.BlockCopy(_buffer, _bufferPosition, b, offset, available);offset += available;len -= available;_bufferPosition += available;}if (len > _bufferSize){_ReadInternal(b, offset, len);_bufferStart += len;_bufferPosition = 0;_bufferLength = 0;}else{_Refill();if (_bufferLength < len){Buffer.BlockCopy(_buffer, 0, b, offset, _bufferLength);throw new EndOfStreamException("read past EOF: " + this);}else{Buffer.BlockCopy(_buffer, 0, b, offset, len);_bufferPosition = len;}}}}
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
throw new NotSupportedException();
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request, options);}
public override void SetParams(string parameters){base.SetParams(parameters);string[] parts = parameters.Split(new[] {','}, StringSplitOptions.RemoveEmptyEntries);if (parts.Length > 0) m_language = parts[0];if (parts.Length > 1) m_country = parts[1];if (parts.Length > 2) m_variant = parts[2]; else m_variant = "";}
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj) => obj is FacetLabel other && Length == other.Length && Components.SequenceEqual(other.Components);
return ExecuteGetInstanceAccessDetails(BeforeClientExecution(request));
public HSSFPolygon OnCreate(HSSFChildAnchor anchor){HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.Parent = this;shape.Anchor = anchor;Shapes.Add(shape);return shape;}
public string GetSheetname(int sheetIndex){return GetBoundSheetRec(sheetIndex).SheetName;}
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request, options);}
{ request = BeforeClientExecution(request); return ExecuteAssociateSigninDelegateGroupsWithAccount(request); }
for (int j = 0; j < mbr.NumColumns; ++j) { var br = new BlankRecord(); br.Row = mbr.Row; br.Column = mbr.FirstColumn + j; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); }
public static string ToString(string str) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = str.IndexOf("\\E", apos)) != -1) { sb.Append(str.Substring(apos, k - apos)); sb.Append("\\\\E\\Q"); apos = k + 2; } sb.Append(str.Substring(apos)); sb.Append("\\E"); return sb.ToString(); }
(MemoryStream value) { throw new NotSupportedException(); }
public ArrayPtg(object[][] values2d){int nRows = values2d.Length;int nColumns = values2d[0].Length;_nRows = nRows;_nColumns = nColumns;object[] vv = new object[nRows * nColumns];for (int r = 0; r < nRows; r++){object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++){vv[GetValueIndex(r, c)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
public override string ToString(){return GetType().Name + " [" + GetValueAsString() + "]";}
public string ToString(string field){return "ToChildBlockJoinQuery (" + parentQuery + ")";}
public void IncrementAndGet(){refCount.IncrementAndGet();}
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
} { ) (  void else if ; TenPower pow10 } { } { ) ( = tp ; ; 0 < pow10 GetInstance . TenPower MulShift MulShift ) ( ) , ( ) , ( Abs . Math _multiplierShift . tp _multiplicand . tp _divisorShift . tp _divisor . tp ) pow10 (
public virtual string ToString() { if (Count == 0) return string.Empty; var b = new System.Text.StringBuilder(GetComponent(0)); for (int i = 1; i < Count; i++) b.Append(System.IO.Path.DirectorySeparatorChar).Append(GetComponent(i)); return b.ToString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){this._fetcher = fetcher;}
ProgressMonitor pm = progressMonitor;
if (!First) { Ptr = 0; } if (!Eof()) { ParseEntry(); }
public E Previous() { if (iterator.PreviousIndex() < start) throw new InvalidOperationException(); return previous; }
return this.newPrefix;
for (i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1;
if (stems.Count < 2) return stems; var terms = new List<CharsRef>(stems.Count); var deduped = new CharArraySet(8, ignoreCase); foreach (var s in stems) { if (!deduped.Contains(s)) { terms.Add(s); deduped.Add(s); } } return terms;
return ExecuteGetGatewayResponses(BeforeClientExecution(request));
currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask;
n = System.Math.Max(0, System.Math.Min(s, available)); ptr += n; return s;
public BootstrapActionDetail WithBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig){this.BootstrapActionConfig = bootstrapActionConfig;return this;}
public void Serialize(LittleEndianOutput out){out.WriteShort(field_1_row);out.WriteShort(field_2_col);out.WriteShort(field_3_flags);out.WriteShort(field_4_shapeid);out.WriteShort(field_6_author.Length);out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00);if (field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author, out);}else{StringUtil.PutCompressedUnicode(field_6_author, out);}if (field_7_padding != null){out.Write(field_7_padding.Value);}}
return string.LastIndexOf(String, count);
private bool AddLastImpl(E e){LinkLast(e);return true;}
} { ) , ( void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; CompareAndSet . state = res = src ) res , src ( UnsetSection Get . state ) subsection , section , src ( ) (
public string TagName() { return tagName; }
subrecords.Insert(index, element);
public bool Remove(object o){lock(_mutex){return _delegate.Remove(o);}}
public DoubleMetaphoneFilter(TokenStream input):this(input, maxCodeLength, inject){}
{ return InCoreLength(); }
bool newValue = value;
this.oldSource = oldSource; this.newSource = newSource;
if (count <= i) { throw new IndexOutOfRangeException(); } return entries[i];
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo") {this.Method = "PUT";this.UriPattern = "/repos";}
return (bool)deltaBaseAsOffset;
if (list.ModCount != expectedModCount) { throw new InvalidOperationException(); } if (lastLink == null) { throw new InvalidOperationException(); } var next = lastLink.Next; var previous = lastLink.Previous; previous.Next = next; next.Previous = previous; list.Count--; list.ModCount++; if (link == lastLink) { link = next; } else { pos--; } lastLink = null; expectedModCount++;
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
return start;
} { ) ( ; return query Query WeightedTerm static public GetTerms ] [ ) false , query (
public override java.nio.ByteBuffer Compact() { throw new java.nio.ReadOnlyBufferException(); }
for (int i = 0; i < iterations; i++){ byte byte0 = values[valuesOffset++]; byte byte1 = values[valuesOffset++]; byte byte2 = values[valuesOffset++]; blocks[blocksOffset++] = (byte)(byte2 >> 6); blocks[blocksOffset++] = (byte)((byte1 & 15) << 4 | byte2 >> 4); blocks[blocksOffset++] = (byte)((byte0 & 3) << 6 | byte2 & 63); blocks[blocksOffset++] = (byte)(byte0 >> 2);}
public static string GetRepoName(string s){if (s == null){throw new ArgumentNullException(nameof(s));}string result = s;if (result.EndsWith(Constants.DOT_GIT)){result = result.Substring(0, result.Length - Constants.DOT_GIT.Length);}return result.Split('/').Last();}
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
public string AccessKeySecret { get; set; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public override ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request){request = BeforeClientExecution(request);return ExecuteListMonitoringExecutions(request);}
} { DescribeJobRequest ; ; ) , ( SetJobId SetVaultName jobId string vaultName string ) jobId ( ) vaultName (
return (EscherRecord)escherRecords[index];
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate TrackingRefUpdate() { return trackingRefUpdate; }
public void Print(bool b){(b.ToString());}
{ return (QueryNode) GetChildren()[0]; }
this.workdirTreeIndex = index.NotIgnoredFilter;
public AreaRecord(IRecordInputStream input) { field_1_formatFlags = input.ReadShort(); }
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { Protocol = ProtocolType.Https; }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
public string Get(OrdRange dim){return dim.PrefixToOrdRange();}
return string.Format("{0}('{1}')", e.GetType().Name, (e.StartIndex >= 0 && e.StartIndex < e.InputStream.Size) ? Utils.EscapeWhitespace(e.InputStream.GetText(new Interval(e.StartIndex, e.StartIndex)), false) : "");
public E PeekFirst() { return PeekFirstImpl(); }
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
{ return (NumberFormatIndexRecord) Copy(); }
return ExecuteDescribeRepositories(BeforeClientExecution(request));
public SparseIntArray(int initialCapacity){initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity);mKeys = new int[initialCapacity];mValues = new int[initialCapacity];mSize = 0;}
public HyphenatedWordsFilter Create(TokenStream input){return new HyphenatedWordsFilter(input);}
public virtual CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request) { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
new FileStream(fileName, FileMode.OpenOrCreate, FileAccess.ReadWrite);
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string ToString(int value){System.Text.StringBuilder sb = new System.Text.StringBuilder(16);WriteHex(sb, value);return sb.ToString();}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
HSSFColor b = _palette.GetColor(index); return (b == null) ? null : new HSSFColor.CustomColor(index, b.GetTriplet());
throw new NotImplementedFunctionException(FunctionName);
out.WriteShort(field_1_number_crn_records);out.WriteShort(field_2_sheet_table_index);
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
this._character = character; this._fontIndex = fontIndex;
public static byte[] ToByteArray(char[] chars, int offset, int length){ byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++){ char ch = chars[i]; result[resultIndex++] = (byte)(ch); result[resultIndex++] = (byte)(ch >> 8); } return result; }
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
return GetHiddenTokensToLeft(tokenIndex - 1, tokenIndex);
public override bool Equals(object obj) { if (object.ReferenceEquals(this, obj)) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!object.Equals(m_compiled, other.m_compiled)) return false; if (!object.Equals(m_term, other.m_term)) return false; return true; }
if (spanQueries.Count == 1) return spanQueries[0]; var sq = new SpanQuery[spanQueries.Count]; int i = 0; foreach (var query in spanQueries) { if (weightBySpanQuery.TryGetValue(query, out float boost) && boost != 1f) { sq[i++] = new SpanBoostQuery(query, boost); } else { sq[i++] = query; } } return new SpanOrQuery(sq);
StashCreateCommand() { return new StashCreateCommand(repo); }
public FieldInfo ByName(string fieldName){return Get().FieldName;}
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request) { request = BeforeClientExecution(request); return ExecuteSetInstanceProtection(request); }
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
void CopyChars(CharsRef output, int offset, int len, int posLength, int endOffset){if (count == outputs.Length){outputs = ArrayUtil.Grow(outputs, count + 1);}if (count == posLengths.Length){posLengths = ArrayUtil.Grow(posLengths, count + 1);}if (count == endOffsets.Length){endOffsets = ArrayUtil.Grow(endOffsets, count + 1);}if (outputs[count] == null){outputs[count] = new CharsRefBuilder();}outputs[count].CopyChars(output, offset, len);posLengths[count] = posLength;endOffsets[count] = endOffset;count++;}
public FetchLibrariesRequest():base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto"){Protocol = ProtocolType.HTTPS;}
return fs.Exists(objects);
this.out = (OutputStream)out;
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { this.Method = MethodType.PUT; this.UriPattern = "/clusters/[ClusterId]"; }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2){return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request, options);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
public virtual void SetSharedFormula(bool flag){ field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool ReuseObjects(){return;}
return badToken(new ErrorNodeImpl(this));
if (args.Count > 0) { throw new ArgumentException($"Unknown parameters: [{string.Join(", ", args.Keys)}]"); }
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args){return loader.NewInstance(name, args);}
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
return new Binary(a.Clone(), b.Clone(), RevFilter);
public override bool Equals(object o){return o is ArmenianStemmer;}
protected internal bool hasArray() { return this.array != null; }
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance;return Invoke<UpdateContributorInsightsResponse>(request, options);}
} { ) (  void ; ; ; ; null = WriteProtect null = FileShare Remove . Records Remove . Records ) WriteProtect ( ) FileShare (
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup) { this.expand = expand; this.analyzer = analyzer; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){request = BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
return FindObjectRecord().GetObjectData();
public GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){request = BeforeClientExecution(request);return ExecuteGetContactAttributes(request);}
public virtual string ToString() { return GetKey() + ": " + GetValue(); }
public virtual ListTextTranslationJobsResult ExecuteListTextTranslationJobs(ListTextTranslationJobsRequest request){beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public GetContactMethodsResult ExecuteGetContactMethods(GetContactMethodsRequest request){return BeforeClientExecution(request);}
public static int GetFunctionByNameInternal(string name){FunctionMetadata fd = GetInstanceCetab().GetFunctionByNameInternal(name);if (fd == null){fd = GetInstance().GetFunctionByNameInternal(name);if (fd == null){return -1;}}return fd.GetIndex();}
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static ObjectId InsertId(string changeId, string message) { return new ObjectId(changeId, message, false); }
public virtual long GetObjectSize(AnyObjectId objectId, int typeHint){long sz = db.GetObjectSize(objectId, typeHint); if (sz < 0){if (typeHint == Constants.OBJ_ANY)throw new MissingObjectException(objectId, typeHint); throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2(objectId.Copy()));} return sz;}
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
public NumberPtg(LittleEndianInput in) : this(in.ReadDouble()) {}
{ request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request); }
return ExecuteDescribeDetector(BeforeClientExecution(request));
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request, options);}
return ExecuteDeleteAlarm(BeforeClientExecution(request));
return new PortugueseStemFilter(input);
reserved = new byte[ENCODED_SIZE];
public override bool Remove(object @object) { lock (mutex) { return c.Remove(@object); } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
return " >= _p" + precedence;
request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request);
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){LoadBalancerName = loadBalancerName; PolicyName = policyName;}
options = (WindowProtectRecord)_options.Clone();
data = new UnbufferedCharStream[bufferSize]; n = 0;
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 16, w5);
} { WindowOneRecord ; ; ; ; ; ; ; ; ; ) ( = field_9_tab_width_ratio = field_8_num_selected_tabs = field_7_first_visible_tab = field_6_active_sheet = field_5_options = field_4_height = field_3_width = field_2_v_hold = field_1_h_hold in RecordInputStream ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ReadShort . in ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) ( ) (
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
try { channel.Truncate(fileLength); } finally { isOpen = false; fos.Close(); channel.Close(); }
public virtual DescribeMatchmakingRuleSetsResult ExecuteDescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeMatchmakingRuleSets(request);}
public virtual string GetTerm(char[] surface, int off, int len, int wordId){return null;}
return pathStr;
public static double R(double[] v){if (v == null || v.Length == 0) return double.NaN; int n = v.Length; if (n == 1) return 0; double m = 0; for (int i = 0; i < n; i++) m += v[i]; m /= n; double s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); return s / (n - 1);}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public bool PassedThroughNonGreedyDecision() { return false; }
} { ) (  ; return End ) 0 (
private void Walk(ICellHandler handler, Range range, bool traverseEmptyCells){ int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = ArithmeticUtils.SubAndCheck(lastColumn, firstColumn) + 1; var ctx = new SimpleCellWalkContext(); for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ++ctx.RowNumber) { var currentRow = sheet.GetRow(ctx.RowNumber); if (currentRow == null) { if (traverseEmptyCells) { for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width), ArithmeticUtils.SubAndCheck(ctx.ColNumber, firstColumn) + 1); handler.OnCell(null, ctx); } } continue; } int rowSize = currentRow.LastCellNum; for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ++ctx.ColNumber) { if (!traverseEmptyCells && ctx.ColNumber > rowSize) { break; } var currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null || (currentCell.CellType == CellType.Blank && !traverseEmptyCells)) { if (traverseEmptyCells) { ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width), ArithmeticUtils.SubAndCheck(ctx.ColNumber, firstColumn) + 1); handler.OnCell(null, ctx); } continue; } ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width), ArithmeticUtils.SubAndCheck(ctx.ColNumber, firstColumn) + 1); handler.OnCell(currentCell, ctx); } } }
return pos;
if (Boost == other.Boost) return Bytes.CompareTo(other.Bytes); else return Boost.CompareTo(other.Boost);
for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; } } return len;
public virtual void WriteShort(LittleEndianOutput @out){@out.WriteShort((_options));}
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
} { KeySchemaElement ; ; ) , ( SetKeyType SetAttributeName keyType KeyType attributeName string ) ( ) attributeName ( ToString . keyType ) (
} { ) (  GetAssignmentResult ; return ; request GetAssignmentRequest ExecuteGetAssignment = request ) request ( BeforeClientExecution ) request (
public override bool Id(AnyObjectId id){return FindOffset(id) != -1;}
public GroupingSearch GroupingSearch(bool allGroups){this.allGroups = allGroups;return this;}
public void Put(string dimName, bool multiValued){lock (this){DimConfig ft;if (!fieldTypes.TryGetValue(dimName, out ft)){ft = new DimConfig();fieldTypes[dimName] = ft;}DimConfig v = ft;v.multiValued = multiValued;}}
int size = 0; foreach (char c in cells.Keys) { size++; } return size;
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request, options);}
