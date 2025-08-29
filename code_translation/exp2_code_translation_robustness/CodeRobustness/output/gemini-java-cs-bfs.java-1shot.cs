out.WriteShort(field_1_vcenter);
for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; ++srcDirIdx) BlockList.AddRange(src.directory[srcDirIdx]);
if (currentBlock == null || upto == blockSize) { AddBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b;
public virtual GetObjectIdResponse GetObjectId(GetObjectIdRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetObjectIdRequestMarshaller.Instance;options.ResponseUnmarshaller = GetObjectIdResponseUnmarshaller.Instance;return Invoke<GetObjectIdResponse>(request, options);}
request = this.beforeClientExecution(request); return this.executeDeleteDomainEntry(request);
return (termOffsets != null ? termOffsets.RamBytesUsed() : 0L) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0L);
public string Decode(byte[] raw){if (raw.Length < 0) return "";string msgB = RawParseUtils.GuessEncoding(raw, 0, raw.Length);msgB = RawParseUtils.TagMessage(msgB, 0);return msgB;}
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.OurBlockIndex = 0; _bat_blocks.Add(bb); _header.BATCount = 1; _header.SetBATArray(new int[] { 0 }); _property_table.StartBlock = 1; bb.SetValueAt(0, POIFSConstants.FAT_SECTOR_BLOCK); bb.SetValueAt(1, POIFSConstants.END_OF_CHAIN);
slice = pool.Buffers[address >> ByteBlockPool.ByteBlockShift][address & ByteBlockPool.ByteBlockMask];
} { ) ( SubmoduleAddCommand ; this return ; path string path = path . this
public ListIngestionsResult ExecuteListIngestions(ListIngestionsRequest request) { request = BeforeClientExecution(request); return null; }
public virtual SwitchToResponse SwitchTo(SwitchToRequest request){var options = new InvokeOptions();options.RequestMarshaller = SwitchToRequestMarshaller.Instance;options.ResponseUnmarshaller = SwitchToResponseUnmarshaller.Instance;return Invoke<SwitchToResponse>(request, options);}
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { this.Method = MethodType.POST; }
public virtual bool Ready() { lock (this) { if (in == null) throw new System.IO.IOException("InputStreamReader is closed"); try { return in.Available() > 0; } catch (System.IO.IOException) { return false; } } }
public virtual EscherOptRecord GetOptRecord() { return _optRecord; }
public virtual int Write(string buffer, int offset, int length){lock (this){if (buffer == null){throw new ArgumentNullException(nameof(buffer), "buffer == null");}if (length == 0){return 0;}if (offset < 0 || length < 0 || offset + length > buffer.Length){throw new ArgumentOutOfRangeException();}int copylen = length;for (int i = 0; i < copylen; i++){this.buffer[pos + i] = buffer[offset + i];}pos += copylen;return copylen;}}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp){this.sentenceOp = sentenceOp;}
public void Write(string str){ ((str != null ? str : (object)null)); }
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) {}
return base.NextEntry().GetValue();
}} {  void else if ; ) , , , ( public } { } { ) ( = available useBuffer bool len offset b else if if ; if available <= len bufferPosition - bufferLength } { } { ) ( } { ) ( len += bufferPosition ; ) ( ] [ ; ; ; ; if ; else if ; && useBuffer ; ; ; ; 0 > available Array.Copy 0 > len 0 = bufferLength 0 = bufferPosition after = bufferStart readInternal ; throw ) ( = after } { } { ) ( refill bufferSize < len available += bufferPosition available -= len available += offset Array.Copy ) len , offset , b , bufferPosition , buffer ( ) len , offset , b ( new EndOfStreamException > after len + ; ; ; throw ; len < bufferLength ) ( ) available , offset , b , bufferPosition , buffer ( ) ( Length bufferPosition + bufferStart len = bufferPosition Array.Copy new EndOfStreamException Array.Copy this + "read past EOF: " ) ( ) len , offset , b , 0 , buffer ( ) ( ) bufferLength , offset , b , 0 , buffer ( this + "read past EOF: "
}} { ) ( TagQueueResult ; return ; request TagQueueRequest ExecuteTagQueue = request ) request ( BeforeClientExecution ) request (
throw new NotSupportedException();
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request, options);}
public virtual void SetParams(string parameters){base.SetParams(parameters);variant = "";string[] tokens = parameters.Split(',');if (tokens.Length > 0) language = tokens[0];if (tokens.Length > 1) country = tokens[1];if (tokens.Length > 2) variant = tokens[2];}
{ request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request); }
public override bool Equals(object obj) { if (obj == this) { return true; } if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; --i) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
public HSSFPolygon OnCreate(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); return shape; }
public string GetSheetname(int sheetIndex){return GetBoundSheetRec(sheetIndex).GetSheetname();}
public GetDashboardResult GetDashboard(GetDashboardRequest request) { BeforeClientExecution(request); return ExecuteGetDashboard(request); }
} { ) (  AssociateSigninDelegateGroupsWithAccountResult ; return ; request AssociateSigninDelegateGroupsWithAccountRequest ExecuteAssociateSigninDelegateGroupsWithAccount = request ) request ( BeforeClientExecution ) request (
for (int j = 0; j < mbr.GetNumColumns(); ++j) { BlankRecord br = new BlankRecord(); br.SetRow(mbr.GetRow()); br.SetColumn(mbr.GetFirstColumn() + j); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); }
public static string toString(string @string){System.Text.StringBuilder sb = new System.Text.StringBuilder();sb.Append("\\Q");int apos = 0;int k;while ((k = @string.IndexOf("\\E", apos)) >= 0){sb.Append(@string.Substring(apos, k - apos));sb.Append("\\\\E\\Q");apos = k + 2;}sb.Append(@string.Substring(apos));sb.Append("\\E");return sb.ToString();}
} { ) (  Memory<byte> ; throw value ReadOnlyMemoryException new ) (
public ArrayPtg(object[][] values2d){ int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; object[] vv = new object[nRows * nColumns]; for (int r = 0; r < nRows; r++){ object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++){ vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
request = BeforeClientExecution(request); return ExecuteGetIceServerConfig(request);
return GetType().Name + " [" + GetValueAsString() + "]";
return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";
public virtual void IncrementAndGet() { RefCount.IncrementAndGet(); }
} { ) (  UpdateConfigurationSetSendingEnabledResult ; return ; request UpdateConfigurationSetSendingEnabledRequest ExecuteUpdateConfigurationSetSendingEnabled = request ) request ( BeforeClientExecution ) request (
return GetXBATEntriesPerBlock() * LittleEndianConsts.IntSize;
else if (pow10 < 0) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); _divisor.MulShift(tp, _divisorShift); _multiplicand.MulShift(tp, _multiplierShift); }
return string.Join(Path.DirectorySeparatorChar, Enumerable.Range(0, Length).Select(i => GetComponent(i)));
InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.RoleName = roleName; return this; }
ProgressMonitor pm = progressMonitor;
void ParseEntry() { if ((!Eof())) { ((ptr = 0)); }; if ((!first)) {}; }
public E Previous() { if (start >= iterator.PreviousIndex()) throw new InvalidOperationException(); return iterator.Previous(); }
{ return (String) this.NewPrefix; }
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) return i; } return -1;
List<CharsRef> terms = new List<CharsRef>(); CharArraySet deduped = new CharArraySet(8, ignoreCase); foreach (CharsRef s in stems) { if (deduped.Add(s)) { terms.Add(s); } } return terms;
request = BeforeClientExecution(request); return ExecuteGetGatewayResponses(request);
currentBlockIndex = pos >> blockBits; currentBlock = blocks[currentBlockIndex]; currentBlockUpto = pos & blockMask;
n = Math.Min(available(), Math.Max(0, n));
} { BootstrapActionDetail ; ) ( SetBootstrapActionConfig bootstrapActionConfig BootstrapActionConfig ) bootstrapActionConfig (
out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte(field_7_padding.IntValue); }
return str.LastIndexOf(str, count);
public virtual AddLastImplResponse AddLastImpl(AddLastImplRequest request){var options = new InvokeOptions();options.RequestMarshaller = AddLastImplRequestMarshaller.Instance;options.ResponseUnmarshaller = AddLastImplResponseUnmarshaller.Instance;return Invoke<AddLastImplResponse>(request, options);}
public virtual UnsetSectionResponse UnsetSection(UnsetSectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UnsetSectionRequestMarshaller.Instance;options.ResponseUnmarshaller = UnsetSectionResponseUnmarshaller.Instance;return Invoke<UnsetSectionResponse>(request, options);}
public string TagName { get; set; }
subrecords.Insert(index, element);
public bool Remove(object o) { lock (mutex) { return @delegate.Remove(o); } }
public virtual DoubleMetaphoneFilterResponse DoubleMetaphoneFilter(DoubleMetaphoneFilterRequest request){var options = new InvokeOptions();options.RequestMarshaller = DoubleMetaphoneFilterRequestMarshaller.Instance;options.ResponseUnmarshaller = DoubleMetaphoneFilterResponseUnmarshaller.Instance;return Invoke<DoubleMetaphoneFilterResponse>(request, options);}
} { ) (  ; return inCoreLength ) (
bool newValue = value;
public virtual PairResponse Pair(PairRequest request){var options = new InvokeOptions();options.RequestMarshaller = PairRequestMarshaller.Instance;options.ResponseUnmarshaller = PairResponseUnmarshaller.Instance;return Invoke<PairResponse>(request, options);}
if (count <= i) throw new IndexOutOfRangeException(); return entries[i];
public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { this.Method = MethodType.PUT; this.Uri = "/repos"; }
public bool DeltaBaseAsOffset() { return deltaBaseAsOffset; }
} { ) ( void else if } { } { ) ( ; throw else if == expectedVersion InvalidOperationException new } { } { ) ( Version . list ) ( ; throw ; ; ; ; ; if ; ; ; ; null != lastNode InvalidOperationException new ++ -- ++ expectedVersion null = lastNode Previous = currentNode } { ) ( Next = Previous = = Previous LinkedListNode = Next LinkedListNode ) ( Version . list Count . list ; currentNode == lastNode Next . Previous Previous . Next Previous . lastNode > T < Next . lastNode > T < -- index
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request);
} { ) (  ; start return
public static WeightedTerm[] GetTerms(Query query) { return GetTerms(query, false); }
throw new java.nio.ReadOnlyBufferException();
for (int i = 0; i < iterations; ++i) { byte byte0 = blocks[blocksOffset++]; byte byte1 = blocks[blocksOffset++]; byte byte2 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; }
} { ; return result else if ; string if ; if ; if if ; string ) ( if ; ) ( = result ; throw ) ( = elseelements ; ) ( string ; throw ) ( ; ) ( = s ; ) ( = result Equals . ] [ elements new ArgumentException 0 == Split . s = elements || ] [ new ArgumentException s == null = s || Path = result EndsWith . result ] [ elements ) result ( DOT_GIT . Constants 1 - ) ( Length . elements ) "/+" ( Split . s IsMatch . Equals . "file" ) ( Host Equals . "" Equals . "/" ) ( Substring . result ) ( 2 - Length . elements ) ( ) ( Matcher . LOCAL_FILE ) Scheme ( ) ( ) s ( ) s ( ) , 0 ( DOT_GIT_EXT . Constants Length . elements "/]" + ) s ( - + "[\\" Length . Length . result DirectorySeparatorChar . Path ) ( DOT_GIT_EXT . Constants ) (
public virtual DescribeNotebookInstanceLifecycleConfigResult ExecuteDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request);}
public override string accessKeySecret() { return this._enclosing._accessKeySecret; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
request = BeforeClientExecution(request); return ExecuteDescribeVoices(request);
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
} { DescribeJobRequest ; ; ) , ( SetJobId SetVaultName jobId string vaultName string ) jobId ( ) vaultName (
public EscherRecord this[int index] => escherRecords[index];
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public virtual TrackingRefUpdate trackingRefUpdate() { return trackingRefUpdate; }
void Print(bool b){Console.WriteLine(b.ToString());}
return (QueryNode) getChildren().get(0);
this.Index.WorkdirTreeIndex = new NotIgnoredFilter(workdirTreeIndex);
public AreaRecord(RecordInputStream @in){field_1_formatFlags = @in.ReadShort();}
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) { }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
}} { ) ( PutVoiceConnectorStreamingConfigurationResult ; return ; request PutVoiceConnectorStreamingConfigurationRequest ExecutePutVoiceConnectorStreamingConfiguration = request ) request ( BeforeClientExecution ) request (
public OrdRange Get(string dim){return prefixToOrdRange[dim];}
public virtual GetTextFromLexerResponse GetTextFromLexer(GetTextFromLexerRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetTextFromLexerRequestMarshaller.Instance;options.ResponseUnmarshaller = GetTextFromLexerResponseUnmarshaller.Instance;return Invoke<GetTextFromLexerResponse>(request, options);}
public virtual E PeekFirst() { return PeekFirstImpl(); }
return ExecuteCreateWorkspaces(BeforeClientExecution(request));
} { ) (  NumberFormatIndexRecord ; return Copy ) (
public override DescribeRepositoriesResult executeDescribeRepositories(DescribeRepositoriesRequest request){throw new System.NotImplementedException();}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request);
RandomAccessFile(string fileName, string mode) throws FileNotFoundException {}
public virtual DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){request = BeforeClientExecution(request);return ExecuteDeleteWorkspaceImage(request);}
public static string WriteHex(int value){return Convert.ToString(value, 16);}
public override UpdateDistributionResult ExecuteUpdateDistribution(UpdateDistributionRequest request) { return ExecuteUpdateDistribution(BeforeClientExecution(request)); }
public HSSFColor GetColor(short index){if (index == HSSFColor.Automatic.Index) return HSSFColor.Automatic.GetInstance(); byte[] b = _palette.GetColor(index); if (b == null) return null; return new HSSFColor.CustomColor(index, b);}
throw new NotImplementedFunctionException(_functionName);
public void Write(LittleEndianOutput out) { out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index); }
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDBEngineVersionsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDBEngineVersionsResponseUnmarshaller.Instance;return Invoke<DescribeDBEngineVersionsResponse>(request, options);}
this._character = character; this._fontIndex = fontIndex;
public static byte[] ToBytes(char[] chars, int offset, int length){return System.Text.Encoding.BigEndianUnicode.GetBytes(chars, offset, length);}
{ request = BeforeClientExecution(request); return ExecuteUploadArchive(request); }
return GetHiddenTokensToLeft(tokenIndex - 1);
public override bool Equals(object obj) { if (ReferenceEquals(this, obj)) return true; var other = obj as AutomatonQuery; return other != null && base.Equals(obj) && object.Equals(term, other.term) && compiled.Equals(other.compiled); }
public virtual SpanQuery GetWeightedSpanQuery(SpanQuery[] spanQueries, IDictionary<SpanQuery, float> weightBySpanQuery) { if (spanQueries.Length == 1) return spanQueries[0]; else { SpanQuery[] sq = new SpanQuery[spanQueries.Length]; int i = 0; foreach (SpanQuery s in spanQueries) { if (weightBySpanQuery.TryGetValue(s, out float boost) && boost != 1f) { sq[i++] = new SpanBoostQuery(s, boost); } else { sq[i++] = s; } } return new SpanOrQuery(sq); } }
StashCreateCommand StashCreateCommand() { return new StashCreateCommand(repo); }
public virtual FieldInfo byName(string fieldName){throw new System.NotImplementedException();}
BeforeClientExecution(request); return (DescribeEventSourceResult)ExecuteDescribeEventSource(request);
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
} { ) (  CancelUpdateStackResult ; return ; request CancelUpdateStackRequest ExecuteCancelUpdateStack = request ) request ( BeforeClientExecution ) request (
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public SetInstanceProtectionResult ExecuteSetInstanceProtection(SetInstanceProtectionRequest request){request = BeforeClientExecution(request);return new SetInstanceProtectionResult();}
} { ) (  ModifyDBProxyResult ; return ; request ModifyDBProxyRequest ExecuteModifyDBProxy = request ) request ( BeforeClientExecution ) request (
if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); posLengths = ArrayUtil.Grow(posLengths, count + 1); endOffsets = ArrayUtil.Grow(endOffsets, count + 1); } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); posLengths[count] = posLength; endOffsets[count] = endOffset; count++;
public FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) {}
bool Exists() { throw new System.NotImplementedException(); }
public FilterOutputStream(java.io.OutputStream out) { this.out = out; }
public virtual ScaleClusterResponse ScaleCluster(ScaleClusterRequest request){var options = new InvokeOptions();options.RequestMarshaller = ScaleClusterRequestMarshaller.Instance;options.ResponseUnmarshaller = ScaleClusterResponseUnmarshaller.Instance;return Invoke<ScaleClusterResponse>(request, options);}
public DataValidationConstraint createTimeConstraint(int operatorType, string formula1, string formula2){return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);}
public override ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request){request = BeforeClientExecution(request);return ExecuteListObjectParentPaths(request);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
sharedFormula.SetShortBoolean(field_5_options, flag);
} { ) (  bool ; reuseObjects return
ErrorNode CreateErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); t.SetParent(this); AddAnyChild(t); return t; }
if (args.Count > 0) { throw new System.ArgumentException("Unknown parameters: " + args); }
} { ) (  EventSubscription ; return ; request RemoveSourceIdentifierFromSubscriptionRequest executeRemoveSourceIdentifierFromSubscription = request ) request ( beforeClientExecution ) request (
} { ) , ( TokenFilterFactory ; return args name string static public NewInstance . Loader IDictionary ) args , name ( > string , string <
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", ProtocolType.HTTPS) { }
} { ) (  GetThreatIntelSetResult ; return ; request GetThreatIntelSetRequest ExecuteGetThreatIntelSet = request ) request ( BeforeClientExecution ) request (
public virtual RevFilterResponse RevFilter(RevFilterRequest request){var options = new InvokeOptions();options.RequestMarshaller = RevFilterRequestMarshaller.Instance;options.ResponseUnmarshaller = RevFilterResponseUnmarshaller.Instance;return Invoke<RevFilterResponse>(request, options);}
public override bool Equals(object o) { return o is ArmenianStemmer; }
public bool ProtectedHasArray() { return false; }
} { ) (  UpdateContributorInsightsResult ; return ; request UpdateContributorInsightsRequest ExecuteUpdateContributorInsights = request ) request ( BeforeClientExecution ) request (
}} ) ( void ; ; ; ; null = writeProtect null = fileShare remove . records remove . records ) writeProtect ( ) fileShare (
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
} { ) ( ; return GetObjectData . ] [ ) ( FindObjectRecord ) (
public GetContactAttributesResult executeGetContactAttributes(GetContactAttributesRequest request){ request = (this.beforeClientExecution(request)); return request; }
public override string ToString() { return GetKey() + ": " + GetValue(); }
public virtual ITextTranslationJobsOperations TextTranslationJobs { get; private set; }
} { ) (  GetContactMethodsResult ; return ; request GetContactMethodsRequest ExecuteGetContactMethods = request ) request ( BeforeClientExecution ) request (
} { ) ( ; return if ; FunctionMetadata name string static public ) ( } { ) ( = fd getIndex . fd if ; null == fd getFunctionByNameInternal . ) ( } { ) ( = fd ) name ( getInstance ; return null == fd getFunctionByNameInternal . ) ( 1 - ) name ( getInstanceCetab ) (
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string InsertId(ObjectId changeId, string message) { return changeId(message, false); }
throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2);
public override ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request) { request = BeforeClientExecution(request); return ExecuteImportInstallationMedia(request); }
request = BeforeClientExecution(request); return ExecutePutLifecycleEventHookExecutionStatus(request);
public NumberPtg(LittleEndianInput in){throw new System.NotImplementedException();}
request = BeforeClientExecution(request); return ExecuteGetFieldLevelEncryptionConfig(request);
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
return ExecuteReportInstanceStatus(BeforeClientExecution(request));
} { ) (  DeleteAlarmResult ; return ; request DeleteAlarmRequest ExecuteDeleteAlarm = request ) request ( BeforeClientExecution ) request (
public virtual PortugueseStemFilterResponse PortugueseStemFilter(PortugueseStemFilterRequest request){var options = new InvokeOptions();options.RequestMarshaller = PortugueseStemFilterRequestMarshaller.Instance;options.ResponseUnmarshaller = PortugueseStemFilterResponseUnmarshaller.Instance;return Invoke<PortugueseStemFilterResponse>(request, options);}
public override int DataSize { get { return ENCODED_SIZE; } }
public override bool Remove(object @object) { lock (mutex) { return c.Remove(@object); } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
} { ) (  string ; return " >= _p" + precedence
}} { ) ( ListStreamProcessorsResult ; return ; request ListStreamProcessorsRequest ExecuteListStreamProcessors = request ) request ( BeforeClientExecution ) request (
new DeleteLoadBalancerPolicyRequest { LoadBalancerName = loadBalancerName, PolicyName = policyName };
}} { WindowProtectRecord ; ) ( options = _options options
data = new char[bufferSize]; n = 0;
public virtual GetOperationsResult GetOperations(GetOperationsRequest request){request = BeforeClientExecution(request);return ExecuteGetOperations(request);}
NB.EncodeInt32(b, o + 16, w5); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o, w1);
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.ReadShort(); field_2_v_hold = in1.ReadShort(); field_3_width = in1.ReadShort(); field_4_height = in1.ReadShort(); field_5_options = in1.ReadShort(); field_6_active_sheet = in1.ReadShort(); field_7_first_visible_tab = in1.ReadShort(); field_8_num_selected_tabs = in1.ReadShort(); field_9_tab_width_ratio = in1.ReadShort(); }
} { ) (  StopWorkspacesResult ; return ; request StopWorkspacesRequest ExecuteStopWorkspaces = request ) request ( BeforeClientExecution ) request (
public void Close() { if (IsOpen()) { try { try { Dump(); } finally { try { channel.SetLength(FileLength()); } finally { channel.Close(); fos.Close(); } } } finally { isOpen = false; } } }
}} { ) (  NGit.Diff.DescribeMatchmakingRuleSetsResult ; return ; request NGit.Diff.DescribeMatchmakingRuleSetsRequest ExecuteDescribeMatchmakingRuleSets = request ) request ( BeforeClientExecution ) request (`
} { ) , , , (  string ; null return len off surface wordId ] [
return pathStr;
public static double Variance(double[] v){if(v==null||v.Length==0)return double.NaN;int n=v.Length;double m=0;for(int i=0;i<n;i++){m+=v[i];}m/=n;double s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}return n>1?s/(n-1):0;}
request = BeforeClientExecution(request); return ExecuteDescribeResize(request);
public sealed override bool PassedThroughNonGreedyDecision(){return false;}
var options = new InvokeOptions();options.RequestMarshaller = EndRequestMarshaller.Instance;options.ResponseUnmarshaller = EndResponseUnmarshaller.Instance;return Invoke<EndResponse>(0, options);
for (int rowNum = range.FirstRow; rowNum <= range.LastRow; rowNum++){ var row = sheet.GetRow(rowNum); if (row == null) continue; for (int colNum = range.FirstColumn; colNum <= range.LastColumn; colNum++){ var cell = row.GetCell(colNum); if (cell != null) handler.OnCell(cell, new SimpleCellWalkContext()); } }
() => { return pos; }
if (this.boost == other.boost) return this.bytes.CompareTo(other.bytes); else return this.boost.CompareTo(other.boost);
} { ) , (  ; len return ) ; ( for len s } { ++ i len < i ; ] [ switch 0 = i } { ) ( : : : : : : : ] i [ s ; break ; break ; ; HAMZA_ABOVE case ; break ; HEH_GOAL case HEH_YEH case ; break ; KEHEH case ; break ; YEH_BARREE case FARSI_YEH case -- i = len HEH = KAF = YEH = Delete ] i [ s ] i [ s ] i [ s ) len , i , s (
public virtual void WriteShort(LittleEndianOutput @out) { @out.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
new KeySchemaElement { AttributeName = attributeName, KeyType = keyType };
}} { ) ( GetAssignmentResult ; return ; request GetAssignmentRequest executeGetAssignment = request ) request ( beforeClientExecution ) request (
bool Has(AnyObjectId id) { return FindOffset(id) != -1; }
GroupingSearch AllGroups(bool allGroups){this.allGroups = allGroups;return this;}
public void SomeMethod(string dimName, bool v){lock(this){DimConfig ft;ft = fieldTypes.Get(dimName);if (ft == null){ft = new DimConfig(dimName, ft);fieldTypes.Put(dimName, ft);}ft.MultiValued = v;}}
} { ) ( ; Size return ) ; ; ( for ; ; } { HasNext . i 0 = Size = i IEnumerator if ; Cell ; char ) ( iterator . > char < } { ) ( = e = c ) ( Keys . cells ; 0 >= At Next . i ) ( ++ Size cmd . e ) c ( ) (
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
return ExecuteDeleteLifecyclePolicy(BeforeClientExecution(request));
