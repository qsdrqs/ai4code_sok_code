} ; ) field_1_vcenter ( WriteShort . @out { ) @out LittleEndianOutput ( void
public void AddAll<T>(BlockList<T> src){if (src.Size == 0){return;}for (int srcDirIdx = 0; srcDirIdx < src.TailDirIdx; srcDirIdx++){AddAll(src.Directory[srcDirIdx], 0, BLOCK_SIZE);}if (src.TailBlkIdx != 0){AddAll(src.TailBlock, 0, src.TailBlkIdx);}}
public void Write(byte b){if (upto == blockSize){if (currentBlock != null){AddBlock(currentBlock);}currentBlock = new byte[blockSize];upto = 0;}currentBlock[upto++] = b;}
public virtual ObjectId ObjectId(){return objectId;}
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
{ return (TermOffsets != null ? TermOffsets.RamBytesUsed() : 0) + (TermsDictOffsets != null ? TermsDictOffsets.RamBytesUsed() : 0); }
} ; ) Length . raw , msgB , raw , ) ( GuessEncoding ( Decode . RawParseUtils return } ; "" return { ) 0 < msgB ( if ; ) 0 , raw ( TagMessage . RawParseUtils = msgB ; buffer = raw ] [ { ) ( string public
; ) 0 ( SetStartBlock . _property_table ; ) FAT_SECTOR_BLOCK . POIFSConstants , 1 ( SetNextBlock ; ) END_OF_CHAIN . POIFSConstants , 0 ( SetNextBlock ; ) bb ( Add . _bat_blocks ; ) 1 ( SetOurBlockIndex . bb ; ) false , bigBlockSize ( CreateEmptyBATBlock . BATBlock = bb BATBlock ; ) } 1 { ] [ new ( SetBATArray . _header ; ) 1 ( SetBATCount . _header ; ) true (  { ) ( POIFSFileSystem
{ slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; System.Diagnostics.Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; System.Diagnostics.Debug.Assert(upto < slice.Length); }
public SubmoduleAddCommand SubmoduleAddCommand(string path){this.path = path; return this;}
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
public QueryParserTokenManager(CharStream stream, int lexState) { SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis", "openAPI") { Method = MethodType.POST; }
public virtual bool Ready(){lock(_lock){if(_in == null){throw new IOException("InputStreamReader is closed");}try{return _bytes.HasRemaining || _in.Available() > 0;}catch(IOException){return false;}}}
public EscherOptRecord OptRecord { get { return _optRecord; } }
public int Read(char[] buffer, int offset, int length){if(buffer==null)throw new ArgumentNullException("buffer");if(offset<0||length<0||buffer.Length-offset<length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copyLen=Math.Min(count-pos,length);for(int i=0;i<copyLen;i++)buffer[offset+i]=this.buffer[pos+i];pos+=copyLen;return copyLen;}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp){this.SentenceOp = sentenceOp;}
public void Write(string str){Write(str ?? "null");}
}; FunctionName = FunctionName.this; ) cause, FunctionName(base { ) cause NotImplementedException, FunctionName string(NotImplementedFunctionException
{ return base.NextEntry().GetValue(); }
public sealed void ReadInternal(byte[] b, int offset, int len, bool useBuffer){int available = BufferLength - BufferPosition;if (len <= available){if (len > 0){Array.Copy(Buffer, BufferPosition, b, offset, len);}BufferPosition += len;}else{if (available > 0){Array.Copy(Buffer, BufferPosition, b, offset, available);offset += available;len -= available;BufferPosition += available;}if (useBuffer && len < BufferSize){Refill();if (BufferLength < len){Array.Copy(Buffer, 0, b, offset, BufferLength);throw new EndOfStreamException("read past EOF: " + this);}else{Array.Copy(Buffer, 0, b, offset, len);BufferPosition = len;}}else{long after = BufferStart + BufferPosition + len;if (after > Length){throw new EndOfStreamException("read past EOF: " + this);}ReadInternal(b, offset, len);BufferStart = after;BufferPosition = 0;BufferLength = 0;}}}
return ExecuteTagQueue(BeforeClientExecution(request));
} ; ) ( System.NotSupportedException new throw { ) (  void
public override CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){request = BeforeClientExecution(request);return ExecuteModifyCacheSubnetGroup(request);}
public void SetParams(string @params) { base.SetParams(@params); language = country = variant = ""; string[] parts = @params.Split(','); if (parts.Length > 0) language = parts[0]; if (parts.Length > 1) country = parts[1]; if (parts.Length > 2) variant = parts[2]; }
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj){if (obj is FacetLabel other && length == other.length){for (int i = length - 1; i >= 0; i--){if (!components[i].Equals(other.components[i])){return false;}}return true;}return false;}
public override GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request = BeforeClientExecution(request);return ExecuteGetInstanceAccessDetails(request);}
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape;
public string GetSheetname(int sheetIndex) { return GetBoundSheetRec(sheetIndex).GetSheetname(); }
public GetDashboardResult GetDashboard(GetDashboardRequest request){request = BeforeClientExecution(request);return ExecuteGetDashboard(request);}
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
public virtual void MulBlankRecord(MulBlankRecord mbr){for (int j = 0; j < mbr.NumColumns; j++){BlankRecord br = new BlankRecord();br.Column = mbr.FirstColumn + j;br.Row = mbr.Row;br.XFIndex = mbr.GetXFAt(j);InsertCell(br);}}
public static string String(string @string){System.Text.StringBuilder sb = new System.Text.StringBuilder();sb.Append("\\Q");int apos = 0;int k;while ((k = @string.IndexOf("\\E", apos)) >= 0){sb.Append(@string.Substring(apos, k + 2 - apos)).Append("\\\\E\\Q");apos = k + 2;}return sb.Append(@string.Substring(apos)).Append("\\E").ToString();}
ByteBuffer(value) { throw new ReadOnlyBufferException(); }
} ; 0 = _reserved2Byte ; 0 = _reserved1Short ; 0 = _reserved0Int ; vv = _arrayValues } } ; ] c [ rowData = ] ) r , c ( GetValueIndex [ vv { ) ++ c ; nColumns < c ; 0 = c ( for ; ] r [ values2d = rowData ] [ object { ) ++ r ; nRows < r ; 0 = r ( for ; ] _nRows * _nColumns [ object new = vv ] [ object ; nRows ) short ( = _nRows ; nColumns ) short ( = _nColumns ; Length . ] 0 [ values2d = nColumns ; Length . values2d = nRows { ) values2d ] [ ] [ object ( ArrayPtg public
public override GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request){request = BeforeClientExecution(request);return ExecuteGetIceServerConfig(request);}
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
public override string ToString(string field){return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";}
} ; ) refCount ref ( Interlocked . Increment { ) ( void sealed override public
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
public virtual void Pow10(){TenPower tp = TenPower.GetInstance(System.Math.Abs(pow10));if (pow10 < 0){MulShift(_divisor.tp, _divisorShift.tp);}else{MulShift(_multiplicand.tp, _multiplierShift.tp);}}
public override string ToString() { StringBuilder b = new StringBuilder(); int l = Length(); b.Append(Path.DirectorySeparatorChar); for (int i = 0; i < l; i++) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString(); }
public InstanceProfileCredentialsProvider SetRoleName(ECSMetadataServiceCredentialsFetcher fetcher, string roleName){ this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
public ProgressMonitor ProgressMonitor { get { return pm; } set { this.pm = value; } }
void ParseEntry(){if(!first){if(!eof){ptr=0;ParseEntry();}}}
public override E Previous(){if (iterator.PreviousIndex() >= start){return iterator.Previous();}throw new InvalidOperationException();}
public string NewPrefix { get; }
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1;
} ; deduped return } } ; ) s ( Add . terms ; ) s ( Add . deduped { ) ) s ( Contains . terms ! ( if { ) stems in s CharsRef ( foreach ; ) ( > CharsRef < List new = deduped > CharsRef < IList ; ) ignoreCase . dictionary , 8 ( CharArraySet new = terms CharArraySet } ; stems return { ) 2 < Count . stems ( if ; ) length , word ( Stem = stems > CharsRef < IList {
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
void Pos(int pos){currentBlockIndex = pos >> blockBits;currentBlock = blocks[currentBlockIndex];currentBlockUpto = pos & blockMask;}
s = Math.Min(Available(), Math.Max(0, n)); ptr += s; return s;
} ; ) bootstrapActionConfig ( SetBootstrapActionConfig { ) bootstrapActionConfig BootstrapActionConfig ( BootstrapActionDetail
public virtual void Serialize(LittleEndianOutput @out){@out.WriteShort(field_1_row);@out.WriteShort(field_2_col);@out.WriteShort(field_3_flags);@out.WriteShort(field_4_shapeid);@out.WriteShort(field_6_author.Length);@out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00);if (field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author, @out);}else{StringUtil.PutCompressedUnicode(field_6_author, @out);}if (field_7_padding != null){@out.WriteByte(field_7_padding.Value);}}
return string.LastIndexOf(String, count);
public bool AddLastImpl(E e){return @object(@object);}
public void UnsetSection(string section, string subsection){ConfigSnapshot src, res;do{src = state.Get();res = src.UnsetSection(section, subsection);} while (!state.CompareAndSet(src, res));}
public string TagName { get; private set; }
public virtual void Add(int index, SubRecord element){subrecords.Add(index, element);}
public virtual bool Remove(object o){lock(mutex){return Delegate().Remove(o);}}
public override TokenStream Create(TokenStream input){return new DoubleMetaphoneFilter(input, maxCodeLength, inject);}
} ; InCoreLength return { ) (
set { newValue = value; }
public Pair(ContentSource newSource, ContentSource oldSource){this.NewSource = newSource; this.OldSource = oldSource;}
public V Get(int i) { if (count <= i) throw new IndexOutOfRangeException(); return entries[i]; }
public virtual CreateRepoResponse CreateRepo(CreateRepoRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateRepoRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateRepoResponseUnmarshaller.Instance;return Invoke<CreateRepoResponse>(request, options);}
bool DeltaBaseAsOffset { get; }
} } ; ) ( InvalidOperationException new throw { else } } ; ) ( InvalidOperationException new throw { else } ; ++ modCount . list ; -- Count . list ; ++ expectedModCount ; null = lastLink ; previous = link } ; -- pos { ) link == lastLink ( if ; next = Next . previous ; previous = Previous . next ; Previous . lastLink = previous > T < Link ; Next . lastLink = next > T < Link { ) null != lastLink ( if { ) modCount . list == expectedModCount ( if { ) ( Remove void
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request){request = BeforeClientExecution(request);return ExecuteAllocateHostedConnection(request);}
{ return Start(); }
public static Query Query(WeightedTerm[] query){return GetTerms(query, false);}
ByteBuffer() { throw new NotSupportedException(); }
for (int i = 0; i < iterations; ++i) { byte byte0 = blocks[blocksOffset++]; values[valuesOffset++] = byte0 >> 2; byte byte1 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); byte byte2 = blocks[blocksOffset++]; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; }
; result return ; ) Length . DOT_GIT_EXT . Constants - Length . result , 0 ( Substring . result = result ) ) ) DOT_GIT_EXT . Constants ( EndsWith . result ( if else ; ] 2 - Length . elements [ elements = result ) result == DOT_GIT . Constants ( if ; ] 1 - Length . elements [ elements = result string ; ) ) ( ArgumentException new throw ) 0 == Length . elements ( if ; ) "/+" , s ( Split . Regex = elements else ; ) "/]" + DirectorySeparatorChar . Path . IO . System + "[\\\\" , s ( Split . Regex = elements ) ) ) s ( IsMatch . LOCAL_FILE || "file" == scheme ( if ; elements ] [ string ; ) ) ( ArgumentException new throw ) null == s ( if ; ) ) ( GetHost = s ) "" == s || "/" == s ( if ; ) ) ( GetPath = s string
DescribeNotebookInstanceLifecycleConfigResponse ExecuteDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = BeforeClientExecution(request); return ExecuteDescribeNotebookInstanceLifecycleConfig(request); }
public string AccessKeySecret { get; set; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request); }
public DescribeJobRequest(string vaultName, string jobId){VaultName = vaultName;JobId = jobId;}
public EscherRecord Get(int index){return escherRecords[index];}
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate TrackingRefUpdate() => _trackingRefUpdate;
public virtual PrintResponse Print(PrintRequest request){var options = new InvokeOptions();options.RequestMarshaller = PrintRequestMarshaller.Instance;options.ResponseUnmarshaller = PrintResponseUnmarshaller.Instance;return Invoke<PrintResponse>(request, options);}
public QueryNode QueryNode() { return GetChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) { this.workdirTreeIndex = index; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.ReadShort(); }
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
{return prefixToOrdRange.Get(dim);}
public string GetErrorDisplay(int startIndex){string symbol = "";if (startIndex >= 0 && startIndex < GetInputStream().Size){symbol = GetInputStream().GetText(new Interval(startIndex, startIndex));symbol = Utils.EscapeWhitespace(symbol, false);}return string.Format(CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol);}
public E PeekFirst() { return PeekFirstImpl(); }
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
return (NumberFormatIndexRecord)Copy();
public virtual DescribeRepositoriesResult ExecuteDescribeRepositories(DescribeRepositoriesRequest request){request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request);}
public SparseIntArray(int initialCapacity){_dictionary = new Dictionary<int, int>(initialCapacity);}
public HyphenatedWordsFilter Create(TokenStream input){return new HyphenatedWordsFilter(input);}
public virtual CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request){request = BeforeClientExecution(request);return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(string fileName, string mode) : base(new File(fileName), mode) { }
public override DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request); }
public static string ToString(long value){StringBuilder sb = new StringBuilder(16);WriteHex(sb, value, 16, "");return sb.ToString();}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
{ if (index == HSSFColor.AUTOMATIC.Index) { return HSSFColor.AUTOMATIC.Color; } HSSFColor b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(index, b); }
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol){throw new NotImplementedFunctionException(_functionName);}
public void Serialize(LittleEndianOutput output){output.WriteShort(field_1_number_crn_records);output.WriteShort(field_2_sheet_table_index);}
public DescribeDBEngineVersionsResult DescribeDBEngineVersions(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(int fontIndex, int character){this.FontIndex = fontIndex;this.Character = character;}
public static byte[] ToByteArray(char[] chars, int offset, int length){byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for (int i = offset; i < end; i++){char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
} ; ) request ( ExecuteUploadArchive return ; ) request ( BeforeClientExecution = request { ) request UploadArchiveRequest (  UploadArchiveResult
public virtual List<Token> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
public virtual SpanQuery SpanQuery(){SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count];int i = 0;foreach (var entry in weightBySpanQuery){SpanQuery sq = entry.Key;float boost = entry.Value;if (boost != 1f){sq = new SpanBoostQuery(sq, boost);}spanQueries[i++] = sq;}if (spanQueries.Length == 1){return spanQueries[0];}else{return new SpanOrQuery(spanQueries);}}
} ; ) repo ( StashCreateCommand new return { ) (  StashCreateCommand
public virtual FieldInfo GetByName(string fieldName){return byName[fieldName];}
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public SetInstanceProtectionResult Execute(SetInstanceProtectionRequest request){request = BeforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
} ; ) request ( ExecuteModifyDBProxy return ; ) request ( BeforeClientExecution = request { ) request ModifyDBProxyRequest (  ModifyDBProxyResult
} ; ++ count ; posLength = ] count [ posLengths ; endOffset = ] count [ endOffsets ; ) len , offset , output ( CopyChars . ] count [ outputs } ; ) ( CharsRefBuilder new = ] count [ outputs { ) null == ] count [ outputs ( if } ; posLengths = next ; ) count , 0 , next , 0 , posLengths ( Copy . Array ; ] ) int ( sizeof , 1 + count ( Oversize . ArrayUtil [ new = next ] [ int { ) Length . posLengths == count ( if } ; endOffsets = next ; ) count , 0 , next , 0 , endOffsets ( Copy . Array ; ] ) int ( sizeof , 1 + count ( Oversize . ArrayUtil [ new = next ] [ int { ) Length . endOffsets == count ( if } ; ) 1 + count , outputs ( Grow . ArrayUtil = outputs { ) Length . outputs == count ( if { ) posLength , endOffset , len , offset , output ] [ (  void
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
} ; ) objects ( Exists . fs return { ) (  bool
public FilterOutputStream(OutputStream out1){this.Out = out1;}
public ScaleClusterRequest() : base("csk", "ScaleCluster", "2015-12-15", "CS") { Method = MethodType.PUT; UriPattern = "/clusters/[ClusterId]"; }
public virtual DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2){return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request, options);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
public virtual void SetShortBoolean(bool flag){m_field_5_options.SharedFormula = flag;}
bool ReuseObjects { get; }
} ; t return ; ) this ( SetParent . t ; ) t ( AddAnyChild ; ) badToken ( ErrorNodeImpl new = t var { ) badToken Token (  ErrorNode
} } ; ) args + "Unknown parameters: " ( ArgumentException new throw { ) 0 > Count . args ( if { ) args > string , string < IDictionary ( LatvianStemFilterFactory public
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args){return loader.NewInstance(name, args);}
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos") { Protocol = ProtocolType.HTTPS; }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
public Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
public override bool Equals(object o){return o is ArmenianStemmer;}
public sealed override bool hasArray() { return protectedHasArray(); }
public override UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){ request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request); }
} ; null = writeProtect ; null = fileShare ; ) writeProtect ( Remove . records ; ) fileShare ( Remove . records { ) ( void
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
} ; ) ( GetObjectData . ) ( FindObjectRecord return { ) (  ] [
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public override string ToString() { return GetKey() + ": " + GetValue(); }
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public GetContactMethodsResult GetContactMethodsRequest(GetContactMethodsRequest request){request = BeforeClientExecution(request);return ExecuteGetContactMethods(request);}
public static int GetFunctionIndex(string name){FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name);if (fd == null){fd = GetInstanceCetab().GetFunctionByNameInternal(name);if (fd == null){return -1;}}return fd.Index;}
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string InsertId(string message, ObjectId changeId){return message(changeId, false);}
public virtual long GetObjectSize(AnyObjectId objectId, int typeHint){long sz = _db.GetObjectSize(this, objectId);if (sz < 0){if (typeHint == Constants.OBJ_ANY)throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2);else throw new MissingObjectException(objectId.Copy(), typeHint);}return sz;}
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
new NumberPtg(in.ReadDouble())
public override GetFieldLevelEncryptionConfigResponse ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){request = BeforeClientExecution(request);return base.ExecuteGetFieldLevelEncryptionConfig(request);}
DescribeDetectorResult ExecuteDescribeDetector(DescribeDetectorRequest request){request = BeforeClientExecution(request);return ExecuteDescribeDetector(request);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request, options);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
public override TokenStream Create(TokenStream input){return new PortugueseStemFilter(input);}
FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool Remove(object obj){lock(mutex){return c.Remove(obj);}}
public virtual GetDedicatedIpResult ExecuteGetDedicatedIp(GetDedicatedIpRequest request){request = BeforeClientExecution(request);return ExecuteGetDedicatedIp(request);}
} ; " >= _p" + precedence return { ) (  string
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request){request = BeforeClientExecution(request);return ExecuteListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){this._loadBalancerName = loadBalancerName;this._policyName = policyName;}
WindowProtectRecord(options) { _options = options; }
data = new byte[bufferSize];
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){request = BeforeClientExecution(request);return ExecuteGetOperations(request);}
} ; ) w5 , 16 + o , b ( EncodeInt32 . NB ; ) w4 , 12 + o , b ( EncodeInt32 . NB ; ) w3 , 8 + o , b ( EncodeInt32 . NB ; ) w2 , 4 + o , b ( EncodeInt32 . NB ; ) w1 , o , b ( EncodeInt32 . NB { ) o , b ] [ (  void
public WindowOneRecord(RecordInputStream in){field_1_h_hold = in.ReadShort();field_2_v_hold = in.ReadShort();field_3_width = in.ReadShort();field_4_height = in.ReadShort();field_5_options = in.ReadShort();field_6_active_sheet = in.ReadShort();field_7_first_visible_tab = in.ReadShort();field_8_num_selected_tabs = in.ReadShort();field_9_tab_width_ratio = in.ReadShort();}
request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request);
} } } } ; ) ( Close . fos { finally } ; ) ( Close . channel { try { finally } ; ) fileLength ( SetLength . channel { try { finally } ; ) ( Dump { try ; false = isOpen { ) isOpen ( if { ) ( Close void public
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public override string Get(int wordId, char[] surface, int off, int len) { return null; }
public virtual string GetPathStr() { return pathStr; }
} ; r return } ; )1-n(/s : 0 ? )1==n( = r } ; ) m - ] i [ v ( * ) m - ] i [ v ( += s { ) ++ i ; n < i ; 0 = i ( for ; 0 = s ; n / s = m } ; ] i [ v += s { ) ++ i ; n < i ; 0 = i ( for ; htgneL . v = n ; 0 = s ; 0 = m { ) 1 >= htgneL . v && null != v ( if ; NaN . elbuod = r { ) v ] [ (  static public
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public bool PassedThroughNonGreedyDecision => passedThroughNonGreedyDecision;
throw new System.NotImplementedException();
public virtual void Process(ICellHandler handler){int firstRow = range.FirstRow;int lastRow = range.LastRow;int firstColumn = range.FirstColumn;int lastColumn = range.LastColumn;int width = lastColumn - firstColumn + 1;SimpleCellWalkContext ctx = new SimpleCellWalkContext();IRow currentRow = null;ICell currentCell = null;for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ctx.RowNumber++){currentRow = sheet.GetRow(ctx.RowNumber);if (currentRow == null) continue;for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ctx.ColNumber++){currentCell = currentRow.GetCell(ctx.ColNumber);if (currentCell == null || (currentCell.CellType == CellType.Blank && !traverseEmptyCells)) continue;long rowSize = ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow);ctx.OrdinalNumber = ArithmeticUtils.MulAndCheck(rowSize, width);ctx.OrdinalNumber = ArithmeticUtils.AddAndCheck(ctx.OrdinalNumber, (ctx.ColNumber - firstColumn + 1));handler.OnCell(currentCell, ctx);}}}
() { return pos; }
if (this.boost == other.boost) return other.bytes.Get().CompareTo(this.bytes.Get()); else return this.boost.CompareTo(other.boost);
} ; len return } } ; break : ; break ; -- i ; ) len , i , s ( Delete = len : HAMZA_ABOVE case ; break ; HEH = ] i [ s : HEH_GOAL case : HEH_YEH case ; break ; KAF = ] i [ s : KEHEH case ; break ; YEH = ] i [ s : YEH_BARREE case : FARSI_YEH case { ) ] i [ s ( switch { ) ++ i ; len < i ; 0 = i ( for { ) len , ] [ s (
void WriteShort(LittleEndianOutput out){out.WriteShort(_options);}
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
public KeySchemaElement(string attributeName, KeyType keyType){AttributeName = attributeName;KeyType = keyType.ToString();}
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request, options);}
} ; 1 - != ) id ( FindOffset return { ) id AnyObjectId (  bool
public GroupingSearch AllGroups(bool allGroups){this.allGroups = allGroups; return this;}
public void SetMultiValued(string dimName, bool v){lock(this){DimConfig ft;if(!fieldTypes.TryGetValue(dimName, out ft)){ft = new DimConfig();fieldTypes.Add(dimName, ft);}ft.MultiValued = v;}}
public virtual int Size() { return cells.Keys.Count(c => at(c).cmd >= 0); }
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
} ; ) request ( ExecuteDeleteLifecyclePolicy return ; ) request ( BeforeClientExecution = request { ) request DeleteLifecyclePolicyRequest (  DeleteLifecyclePolicyResult
