public virtual void WriteShort(LittleEndianOutput @out){@out.WriteShort();}
public virtual void MethodName(BlockList<T> src) { if (src.size == 0) srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void Add(byte b){if(currentBlock==null||upto==currentBlock.Length){if(currentBlock!=null){AddBlock(currentBlock);}currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId() { }
public virtual DeleteDomainEntryResult DeleteDomainEntryResult(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry; }
return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public string Method(){byte[] raw = buffer;int msgB = RawParseUtils.TagMessage;if(msgB <0){return "";}return RawParseUtils.Decode(GuessEncoding(),raw,msgB,raw.Length);}
public POIFSFileSystem() { (true); _header.SetBATCount(); _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
public virtual void SetAddress(long address) { slice = pool.buffers[(int)(address >> ByteBlockPool.BYTE_BLOCK_SHIFT)]; Debug.Assert(slice != null); upto = (int)(address & ByteBlockPool.BYTE_BLOCK_MASK); offset0 = address; Debug.Assert(upto < 0); }
public virtual SubmoduleAddCommand SubmoduleAddCommand(string path) { this.path = path; return this; }
public ListIngestionsResult() { _request = BeforeClientExecution(_request); return ExecuteListIngestions(_request); }
public QueryParserTokenManager(CharStream stream, int lexState) { ; SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIteratorResult(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { ; }
public virtual bool HasRemaining(){lock(@lock){if(@in==null){throw new IOException("InputStreamReader is closed");}try{return bytes.HasRemaining()||@in.Available()>0;}catch(Exception e){return false;}}}
public EscherOptRecord() { }
public int Read(char[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer"); } if (offset < 0 || length < 0 || offset + length > buffer.Length) { throw new ArgumentException("Invalid offset or length"); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } return copylen; }
protected internal OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
public virtual void Write(string str){Write(str != null ? str : "null");}
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause){_functionName = functionName;}
public virtual V V() { return base.nextEntry.GetValue(); }
public sealed void Read(byte[] b, int offset, int len, bool useBuffer) {int available = _bufferLength - _bufferPosition;if (len <= available) {if (len > 0) System.Array.Copy(_buffer, _bufferPosition, b, offset, len);_bufferPosition += len;} else {if (available > 0) {System.Array.Copy(_buffer, _bufferPosition, b, offset, available);offset += available;len -= available;_bufferPosition += available;}if (useBuffer && len < _bufferSize) {Refill();if (_bufferLength < len) {System.Array.Copy(_buffer, 0, b, offset, _bufferLength);throw new java.io.EOFException("read past EOF: " + this);} else {System.Array.Copy(_buffer, 0, b, offset, len);}} else {long after = _bufferStart + _bufferPosition + len;if (after > Length()) throw new java.io.EOFException("read past EOF: " + this);ReadInternal(b, offset, len);_bufferStart = after;_bufferPosition = 0;_bufferLength = 0;}}}
public override TagQueueResult Execute(TagQueueRequest request) { request = BeforeClientExecution; return ExecuteTagQueue(request); }
public virtual void Method() { throw new NotSupportedException(); }
public CacheSubnetGroup CacheSubnetGroup(){request = BeforeClientExecution(request); return ExecuteModifyCacheSubnetGroup(request);}
public override void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; string[] tokens = @params.Split(','); if (tokens.Length > 0) language = tokens[0]; if (tokens.Length > 1) country = tokens[1]; if (tokens.Length > 2) variant = tokens[2]; }
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj){if(!(obj is FacetLabel))return false;FacetLabel other=(FacetLabel)obj;if(length!=other.length)return false;for(int i=length-1;;i--)if(!components[i].Equals(other.components[i]))return false;return true;}
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
public virtual HSSFPolygon HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
public virtual string GetSheetName(int sheetIndex) => GetBoundSheetRec(sheetIndex).Sheetname;
public GetDashboardResult GetDashboardResult(GetDashboardRequest request){request = BeforeClientExecution(request);return executeGetDashboard;}
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
private void Process(MulBlankRecord mbr) { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(j + mbr.FirstColumn); br.Row = mbr.Row; br.XFIndex = mbr.XFAt; InsertCell(br); } }
public static string Escape(string str){StringBuilder sb = new StringBuilder();sb.Append("\\Q");int apos = 0;int k;while((k = str.IndexOf("\\E", apos))>=0){sb.Append(str.Substring(apos, k+2 - apos)).Append("\\\\E\\Q");apos = k+2;}return sb.Append(str.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer(object value) { throw new java.nio.ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;nRows=values2d.Length;_nColumns=(short)nColumns;_nRows=(short)nRows;object[] vv=new object[_nColumns*_nRows];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[GetValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
public virtual string String() { return GetType().Name + " [" + GetValueAsString() + "]"; }
public string String() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
public void VoidMethod() { refCount.IncrementAndGet(); }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
public virtual int SomeMethod() { return getXBATEntriesPerBlock() * ; }
public virtual void Method(int pow10){TenPower tp = TenPower.Instance(Math.Abs(pow10));if(pow10 <0){MulShift(tp.DivisorShift);}else{MulShift(tp.Multiplicand, tp.MultiplierShift);}}
public virtual string ToString(){StringBuilder b = new StringBuilder();int l = length;b.Append(System.IO.Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i < l - 1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
public override InstanceProfileCredentialsProvider Create(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; SetRoleName(roleName); return this; }
public virtual void Method(ProgressMonitor pm) { }
void MyMethod() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
public virtual T E() { if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException(); }
public String() { return; }
public int someMethod(long value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return -1; } } }
public List<CharsRef> MethodName(char[] word, int length) { List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) return stems; CharArraySet terms = new CharArraySet(8, dictionary.IgnoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public virtual GetGatewayResponsesResult GetGatewayResponsesResult(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResultUnmarshaller.Instance;return Invoke<GetGatewayResponsesResult>(request, options);}
public override void method(int pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
s = System.Math.Min(available(), System.Math.Max(0, n)); ptr += s;
public BootstrapActionDetail() { _bootstrapActionConfig = bootstrapActionConfig; }
public void method(LittleEndianOutput @out){@out.WriteShort(field_1_row);@out.WriteShort(field_2_col);@out.WriteShort(field_3_flags);@out.WriteShort(field_4_shapeid);@out.WriteShort((short)field_6_author.Length);@out.WriteByte((byte)(field_5_hasMultibyte?0x01:0x00));if(field_5_hasMultibyte){StringUtil.putUnicodeLE();}else{StringUtil.putCompressedUnicode(field_6_author,@out);}if(field_7_padding.HasValue){@out.WriteByte((byte)field_7_padding.Value);}}
public int LastIndexOf(string str) { return lastIndexOf; }
public virtual bool Add(E obj) { return addLastImpl; }
public virtual void SomeMethod(string subsection){ConfigSnapshot src; ConfigSnapshot res; do {src = state.Get(); res = UnsetSection(src, section, subsection);} while (!state.CompareAndSet(src, res));}
public virtual string String() { return tagName; }
public virtual void Add(int index, SubRecord element) { subrecords.Add(element); }
public virtual bool Remove() { lock (mutex) { return @delegate.Remove(o); } }
public DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input){return new DoubleMetaphoneFilter(input);}
public virtual long InCoreLength(){return inCoreLength;}
public virtual void Method(bool newValue) { }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public virtual int GetEntry(int i) { if (i < 0 || i >= entries.Length) throw new ArgumentOutOfRangeException(nameof(i)); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { "/repos"; SetMethod(); }
public virtual bool Method() { }
public void Remove(){if(expectedModCount == list.ModCount){if(lastLink != null){Link<ET> next = lastLink.Next;Link<ET> previous = lastLink.Previous;next.Previous = previous;previous.Next = next;if(lastLink == link){pos--;}link = previous;lastLink = null;expectedModCount++;list.Size--;list.ModCount++;}else{throw new IllegalStateException();}}else{throw new ConcurrentModificationException();}}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public AllocateHostedConnectionResult(AllocateHostedConnectionRequest request) { request = BeforeClientExecution(request); return executeAllocateHostedConnection; }
public virtual void Method() { }
public static WeightedTerm GetTerms(Query query) { return GetTerms(query, false); }
public ByteBuffer() { throw new ReadOnlyBufferException(); }
public void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++];values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++];values[valuesOffset++]=(byte0&3)<<4|byte1>>4;int byte2=blocks[blocksOffset++];values[valuesOffset++]=(byte1&15)<<2|byte2>>6;values[valuesOffset++]=byte2&63;}}
public string SomeMethod(){string s=Path;if(s=="/"||string.IsNullOrEmpty(s))s=Host;if(s==null)throw new IllegalArgumentException();string[] elements;if("file"==scheme||LOCAL_FILE.IsMatch(s))elements=Regex.Split(s,"[\\\\/]");else elements=Regex.Split(s,"/+");if(elements.Length==0)throw new IllegalArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT==result)result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
public String() { return; }
public virtual CreateVpnConnectionResult CreateVpnConnectionResult() { request = BeforeClientExecution(request); return ExecuteCreateVpnConnection(request); }
public virtual DescribeVoicesResult DescribeVoicesResult() { request = BeforeClientExecution(request); return ExecuteDescribeVoices(request); }
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
public DescribeJobRequest(string vaultName, string jobId) { SetVaultName(vaultName); SetJobId(jobId); }
public EscherRecord Get(int index) { return escherRecords.Get(index); }
public GetApisResult GetApisResult(GetApisRequest request) { request = beforeClientExecution; return ExecuteGetApis(request); }
public virtual DeleteSmsChannelResult DeleteSmsChannelResult(DeleteSmsChannelRequest request) { request = BeforeClientExecution(request); return executeDeleteSmsChannel; }
public TrackingRefUpdate() { }
public virtual void Method() { Console.WriteLine(b.ToString()); }
public virtual QueryNode QueryNode(){return getChildren[0];}
public NotIgnoredFilter(WorkdirTreeIndex workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream @in) { field_1_formatFlags = @in.ReadShort(); }
public GetThumbnailRequest() : base(ProtocolType.HTTPS) {}
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachmentsResult(){request = BeforeClientExecution(request);return ExecuteDescribeTransitGatewayVpcAttachments(request);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
public OrdRange OrdRange() { return prefixToOrdRange[dim]; }
public string String(){string symbol="";if(startIndex>=0&&startIndex<GetInputStream().Size){symbol=GetInputStream().GetText(Interval.Of(startIndex,startIndex));symbol=Utils.EscapeWhitespace(symbol,false);}return string.Format(System.Globalization.CultureInfo.CurrentCulture,"{0}('{1}')",typeof(LexerNoViableAltException).Name,symbol);}
public E E() { return peekFirstImpl; }
public virtual CreateWorkspacesResponse CreateWorkspacesResult(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
public virtual NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new object[initialCapacity]; mSize = 0; }
public HyphenatedWordsFilter HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance;return Invoke<CreateDistributionWithTagsResponse>(request, options);}
public RandomAccessFile(string fileName, string mode) { new File(fileName); }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string Convert(int value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public HSSFColor GetColor(int index){if(index == HSSFColorPredefined.AUTOMATIC.Index){return HSSFColorPredefined.AUTOMATIC.Color;}byte[] b = _palette.GetColor(index);return b == null ? null : new CustomColor();}
public ValueEval(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void Write(){out.Write((short)field_1_number_crn_records);out.Write((short)field_2_sheet_table_index);}
public virtual DescribeDBEngineVersionsResult DescribeDBEngineVersions(){var request = new DescribeDBEngineVersionsRequest();var options = new InvokeOptions();options.RequestMarshaller = DescribeDBEngineVersionsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDBEngineVersionsResultUnmarshaller.Instance;return Invoke<DescribeDBEngineVersionsResult>(request, options);}
public FormatRun(char character, int fontIndex) { this._character = character; this.fontIndex = fontIndex; }
public static byte[] ConvertCharsToBytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public virtual UploadArchiveResponse UploadArchiveResult(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
public virtual List GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj){if(ReferenceEquals(this, obj))return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
public SpanQuery SpanQuery() { SpanQuery[] spanQueries = new SpanQuery[size()]; IEnumerator sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = (SpanQuery)sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries); }
public virtual StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
public virtual FieldInfo FieldInfo() { return byName[fieldName]; }
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResult CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance; options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance; return Invoke<ModifyLoadBalancerAttributesResponse>(request, options); }
public SetInstanceProtectionResult() { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
public void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int size = ArrayUtil.Oversize(1 + count, sizeof(int)); int[] next = new int[size]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int size = ArrayUtil.Oversize(1 + count, sizeof(int)); int[] next = new int[size]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") {}
public bool Exists() { return fs.exists; }
public FilterOutputStream(Stream @out) { this.out = @out; }
public ScaleClusterRequest() : base("/clusters/[ClusterId]") { SetMethod(MethodType.PUT); }
public virtual DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult ListObjectParentPathsResult() { request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request); }
public virtual DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){request = BeforeClientExecution(request);return executeDescribeCacheSubnetGroups;}
public void Method() { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
public virtual bool boolean(){ }
public virtual ErrorNodeImpl ErrorNode(Token badToken) { var t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.Parent = this; return t; }
public LatvianStemFilterFactory(java.util.Map args) : base(args) { if (!args.isEmpty()) { throw new System.ArgumentException("Unknown parameters: " + args); } }
public virtual EventSubscriptionResponse EventSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = EventSubscriptionResponseUnmarshaller.Instance;return Invoke<EventSubscriptionResponse>(request, options);}
public static TokenFilterFactory TokenFilterFactory(string name, Dictionary<string, string> args) { return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto", "openAPI") { }
public GetThreatIntelSetResult ExecuteGetThreatIntelSet(GetThreatIntelSetRequest request){request = BeforeClientExecution(request);return executeGetThreatIntelSet;}
public virtual Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public bool Method(object o) { return false; }
public bool protectedHasArray() { return protectedHasArray(); }
public override UpdateContributorInsightsResult ExecuteUpdateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution; return base.ExecuteUpdateContributorInsights(request); }
public virtual void MethodName() { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
() => findObjectRecord.getObjectData();
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public virtual string ToString(){return getKey + ": " + GetValue();}
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public virtual GetContactMethodsResponse GetContactMethods(){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static int GetFunctionIndex(string name){FunctionMetadata fd=GetInstance().GetFunctionByNameInternal(name);if(fd==null){fd=GetInstanceCetab().GetFunctionByNameInternal(name);if(fd==null){return -1;}}return fd.GetIndex();}
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string InsertId(string message, ObjectId changeId) { return insertId; }
public int SomeMethod(AnyObjectId objectId, SomeType typeHint){int sz=db.GetObjectSize();if(sz<0){if(typeHint==OBJ_ANY)throw new MissingObjectException(objectId.Copy(),JGitText.Get().unknownObjectType2);throw new MissingObjectException(objectId.Copy(),typeHint);}return sz;}
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public PutLifecycleEventHookExecutionStatusResult() { request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request); }
public NumberPtg() { @in.ReadDouble(); }
public GetFieldLevelEncryptionConfigResult(GetFieldLevelEncryptionConfigRequest request) { request = m_beforeClientExecution; return ExecuteGetFieldLevelEncryptionConfig(request); }
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request, options);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
public virtual TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new ; }
public virtual bool Remove(object @object) { lock (mutex) { return c.Remove(@object); } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
public string String() { return; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(object options) { }
public UnbufferedCharStream(int bufferSize) { data = new char[bufferSize]; }
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
public virtual void MethodName(byte[] b, int o) { NB.EncodeInt32(b, o, w1); NB.EncodeInt32(b, o + 4, w2); NB.EncodeInt32(b, o + 8, w3); NB.EncodeInt32(b, o + 12, w4); NB.EncodeInt32(b, , w5); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
public virtual StopWorkspacesResult StopWorkspacesResult(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResultUnmarshaller.Instance;return Invoke<StopWorkspacesResult>(request, options);}
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.SetLength(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSetsResult() { this.request = beforeClientExecution(this.request); return executeDescribeMatchmakingRuleSets(this.request); }
public String(int wordId, String[] surface, int off, int len) {}
internal String(){ }
public static double CalculateVariance(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length,i;for(i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for(i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public virtual bool IsPassedThroughNonGreedyDecision(){return passedThroughNonGreedyDecision;}
public virtual int Method() { return end; }
public void ProcessCells(CellHandler handler){firstRow=range.GetFirstRow();lastRow=range.GetLastRow();firstColumn=range.GetFirstColumn();lastColumn=range.GetLastColumn();width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();Row currentRow=null;Cell currentCell=null;for(ctx.RowNumber=firstRow;ctx.RowNumber<=lastRow;++ctx.RowNumber){currentRow=sheet.GetRow(ctx.RowNumber);if(currentRow==null)continue;for(ctx.ColNumber=firstColumn;ctx.ColNumber<=lastColumn;++ctx.ColNumber){currentCell=currentRow.GetCell(ctx.ColNumber);if(currentCell==null)continue;if(IsEmpty(currentCell)&&!traverseEmptyCells)continue;int rowSize=ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber,firstRow),width);ctx.OrdinalNumber=ArithmeticUtils.AddAndCheck(rowSize,(ctx.ColNumber-firstColumn+1));handler.OnCell(currentCell,ctx);}}}
public ClassName() { }
public virtual int CompareTo(ScoreTerm other) { if (this.boost == other.boost) return other.bytes.Get().CompareTo(this.bytes.Get()); else return this.boost.CompareTo(other.boost); }
public virtual int ProcessArray(char[] s, int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;goto case KEHEH;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=delete(s,i,len);i--;break;default:break;}}return len;}
public void WriteShort(LittleEndianOutput @out) { @out.WriteShort(); }
public DiagnosticErrorListener() { this.exactOnly = exactOnly; }
public KeySchemaElement(string attributeName) { this.AttributeName = attributeName; this.KeyType = keyType.ToString(); }
public virtual GetAssignmentResponse GetAssignmentResult(){var options = new InvokeOptions();options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request, options);}
public virtual bool Method(AnyObjectId id){return FindOffset(id) != ;}
public GroupingSearch(bool allGroups) { this.AllGroups = allGroups; }
public virtual void SetMultiValued(string dimName, bool v){var ft = this.fieldTypes[dimName];if(ft == null){ft = new DimConfig();this.fieldTypes[dimName] = ft;}ft.MultiValued = v;}
{var i=cells.Keys.GetEnumerator();size=0;while(i.MoveNext()){char c=i.Current;Cell e=at(c);if(e.cmd>=0){size++;}}return size;}
public virtual DeleteVoiceConnectorResult DeleteVoiceConnectorResult(DeleteVoiceConnectorRequest request){request = BeforeClientExecution;return ExecuteDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResult(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution; return ExecuteDeleteLifecyclePolicy(request); }
