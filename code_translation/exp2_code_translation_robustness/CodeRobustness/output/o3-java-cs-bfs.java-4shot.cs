public virtual void Field_1_vcenter(LittleEndianOutput @out){@out.WriteShort(this.field_1_vcenter);}
src.directory[srcDirIdx] = src.tailBlock.src.tailBlkIdx.src.size(0, BLOCK_SIZE, 0); if (src.tailDirIdx.addAll<BlockList<T>>(0) != 0) return; for (; ++srcDirIdx < srcDirIdx;) ;
if (b == b) { } if (blockSize == upto) { AddBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b;
public virtual ObjectId ObjectId(){return objectId;}
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){request=BeforeClientExecution(request);return ExecuteDeleteDomainEntry(request);}
return (termOffsets != null ? RamBytesUsed(termOffsets) : 0) + (termsDictOffsets != null ? RamBytesUsed(termsDictOffsets) : 0);
public string Decode(){int msgB = RawParseUtils.tagMessage(raw, 0);if (msgB < 0)return "";string buffer = RawParseUtils.decode(raw, msgB, raw.Length);return buffer;}
{ BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); bb.SetValueAt(0, POIFSConstants.FAT_SECTOR_BLOCK); bb.SetValueAt(1, POIFSConstants.END_OF_CHAIN); _property_table.SetStartBlock(1); _bat_blocks.Add(bb); _header.SetBATArray(new[] { 0 }); _header.SetBATCount(1); }
Debug.Assert(address < upto); offset0 = upto = address; Debug.Assert(slice != null); int sliceLength = slice.Length; slice[address & ByteBlockPool.BYTE_BLOCK_MASK] = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];
public SubmoduleAddCommand SetPath(string path){this.path = path; return this;}
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options=new InvokeOptions();options.RequestMarshaller=ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller=ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request,options);}
public QueryParserTokenManager(CharStream stream,int lexState){this.stream=stream;SwitchTo(lexState);}
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller=GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request,options);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis"){Method = MethodType.POST;}
public bool HasRemaining() {     lock (this)     {         if (@in == null)         {             throw new IOException("InputStreamReader is closed");         }         return bytes.HasRemaining() || @in.Available() > 0;     } }
public EscherOptRecord GetEscherOptRecord(){return _optRecord;}
public int Copy(char[] buffer,int offset,int length){if(buffer==null){throw new NullReferenceException("buffer == null");}if(length==0){return 0;}if(offset<0||length<0||offset+length>buffer.Length){throw new ArgumentOutOfRangeException();}int copylen=Math.Min(length,count-pos);for(int i=0;i<copylen;++i){buffer[offset+i]=this.buffer[pos+i];}pos+=copylen;return copylen;}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp){this.sentenceOp=sentenceOp;}
public void Write(string str){Write((object)(str!=null?str:null));}
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause){this._functionName = functionName;}
public V GetValue(){return base.NextEntry().GetValue();}
public void ReadBytes(byte[] b,int offset,int len,bool useBuffer){while(len>0){int available=_bufferLength-_bufferPosition;if(available>=len){if(len>0){Array.Copy(_buffer,_bufferPosition,b,offset,len);_bufferPosition+=len;len=0;}}else{if(available>0){Array.Copy(_buffer,_bufferPosition,b,offset,available);offset+=available;len-=available;_bufferPosition+=available;}if(useBuffer&&len<_bufferSize){Refill();if(_bufferLength<len){Array.Copy(_buffer,0,b,offset,_bufferLength);throw new EndOfStreamException("read past EOF: "+this);}else{Array.Copy(_buffer,0,b,offset,len);_bufferPosition=len;len=0;}}else{long after=_bufferStart+_bufferPosition+len;if(after>Length())throw new EndOfStreamException("read past EOF: "+this);ReadInternal(b,offset,len);_bufferStart=after;_bufferPosition=0;_bufferLength=0;len=0;}}}}
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options=new InvokeOptions();options.RequestMarshaller=TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller=TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request,options);}
public virtual void Unimplemented(){throw new NotSupportedException();}
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options=new InvokeOptions();options.RequestMarshaller=ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller=ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request,options);}
public void SetParams(string params1){StringTokenizer st=new StringTokenizer(params1, ",");language="";country="";variant="";if(st.HasMoreTokens()){language=st.NextToken();}if(st.HasMoreTokens()){country=st.NextToken();}if(st.HasMoreTokens()){variant=st.NextToken();}base.SetParams(language,country,variant);}
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request = BeforeClientExecution(request);return ExecuteDeleteDocumentationVersion(request);}
public override bool Equals(object obj){if(obj is FacetLabel other){if(length!=other.length)return false;for(int i=length-1;i>=0;--i){if(!components[i].Equals(other.components[i]))return false;}return true;}return false;}
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller=GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request,options);}
protected HSSFPolygon OnCreate(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shape.SetParent(this);shape.SetAnchor(anchor);shapes.Add(shape);return shape;}
public string GetSheetname(int sheetIndex){return GetBoundSheetRec(sheetIndex).GetSheetname();}
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller=GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request,options);}
public override AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request = BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
for (int j = 0; j < mbr.getNumColumns(); ++j) { BlankRecord br = new BlankRecord(); br.setColumn((short)(mbr.getFirstColumn() + j)); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); }
public static string toString(string str){const string apos="\\E";int k=str.IndexOf(apos,StringComparison.Ordinal);var sb=new StringBuilder();sb.Append("\\Q");while(k>=0){sb.Append(str.Substring(0,k));sb.Append("\\\\E\\Q");k+=2;str=str.Substring(k);k=str.IndexOf(apos,StringComparison.Ordinal);}sb.Append(str);sb.Append("\\E");return sb.ToString();}
java.nio.ByteBuffer value() { throw new java.nio.ReadOnlyBufferException(); }
{ _reserved2Byte = 0; _reserved1Short = 0; _reserved0Int = 0; object[] vv = _arrayValues; int nRows = _nRows; int nColumns = _nColumns; object[][] values2d = new object[nRows][]; for (int r = 0; r < nRows; ++r) { values2d[r] = new object[nColumns]; object[] rowData = values2d[r]; for (int c = 0; c < nColumns; ++c) { rowData[c] = vv[getValueIndex(r, c)]; } } }
public GetIceServerConfigResult ExecuteGetIceServerConfig(GetIceServerConfigRequest request){request=BeforeClientExecution(request);return request;}
public override string ToString(){return GetType().Name + " [" + GetValueAsString() + "]";}
public override string ToString(string field){return parentQuery.ToString(field) + "ToChildBlockJoinQuery(" + ")";}
public void IncrementAndGet(){_refCount.incrementAndGet();}
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options=new InvokeOptions();options.RequestMarshaller=UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller=UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request,options);}
public int GetXBATEntriesPerBlock(){return LittleEndianConsts.INT_SIZE*GetXBATEntriesPerBlock();}
else if (pow10 < 0) { tp = TenPower.GetInstance(pow10(tp._divisor, tp._divisorShift, tp._multiplicand, Math.Abs(tp._multiplierShift), mulShift(mulShift()))); }
public override string ToString(){StringBuilder b=new StringBuilder(length);for(int i=0;i<length;++i){if(i==0){b.Append(System.IO.Path.DirectorySeparatorChar);}b.Append(GetComponent(length-1-i));if(i<length-1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
this.fetcher = new ECSMetadataServiceCredentialsFetcher(); this.fetcher.SetRoleName(roleName); return this;
ProgressMonitor pm = progressMonitor;
} { ) (  void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
public E Previous(){if(iterator.PreviousIndex()>=start)return iterator.Previous();throw new System.InvalidOperationException();}
public string NewPrefix(){return this.newPrefix;}
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1;
CharArraySet deduped = new CharArraySet(8, ignoreCase); List<CharsRef> stems = terms[word.Length]; if (stems == null) { stems = new List<CharsRef>(); terms[word.Length] = stems; } for (int i = 0; i < stems.Count; i++) { CharsRef s = stems[i]; if (!dictionary.Contains(s)) { stems.Add(s); deduped.Add(s); } } return deduped;
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){request = BeforeClientExecution(request);return ExecuteGetGatewayResponses(request);}
void SetPos(int pos){this.pos=pos;currentBlockIndex=pos>>blockBits;currentBlock=blocks[currentBlockIndex];currentBlockUpto=pos&blockMask;}
ptr = s += Math.Min(Math.Max(n, 0), available); return s;
public void SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig){this.bootstrapActionConfig = bootstrapActionConfig;}
public void Serialize(LittleEndianOutput out1){out1.WriteShort(GetOptions());out1.WriteShort(GetRecordId());out1.WriteShort(field_1_row);out1.WriteShort(field_2_col);out1.WriteShort(field_3_flags);out1.WriteShort(field_4_shapeid);int length=field_6_author.Length;out1.WriteByte((byte)length);out1.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);StringUtil.PutCompressedUnicode(field_6_author,out1);if(field_7_padding!=null){out1.WriteShort(field_7_padding.Value);}}
{ return @string.LastIndexOf(@string, count); }
private E AddLastImpl(object obj){return default(E);}
public virtual void UnsetSection(string section, string subsection){while(true){ConfigSnapshot src=state.Get();ConfigSnapshot res=src.UnsetSection(section,subsection);if(src==res)return;if(state.CompareAndSet(src,res))return;}}
public string TagName(){return tagName;}
public virtual void Add(int index, SubRecord element){subrecords.Insert(index, element);}
public bool Remove(object o){lock(mutex){return @delegate.Remove(o);}}
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
{ return inCoreLength(); }
} { ) (  void ; newValue bool newValue = value
Pair<ContentSource, ContentSource> newSource = oldSource = new Pair<ContentSource, ContentSource>(this.oldSource, this.newSource);
if (i >= count) throw new IndexOutOfRangeException(i.ToString()); return entries[i];
public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr"){SetMethod(MethodType.PUT);SetUriPattern("/repos");}
public bool DeltaBaseAsOffset(){return deltaBaseAsOffset;}
public void Remove(){if(modCount!=expectedModCount)throw new Exception();if(lastLink==null)throw new Exception();var next=lastLink.Next;var previous=lastLink.Previous;if(previous==link)previous=next;else--pos;list.Remove(lastLink);link=previous;lastLink=null;++expectedModCount;}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller=AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request,options);}
} { ) (  ; start return
public static WeightedTerm[] GetTerms(Query query){return GetTerms(query, false);}
throw new ReadOnlyBufferException();
public static void Encode(byte[] values,int valuesOffset,int iterations,byte[] blocks,int blocksOffset){for(int i=0;i<iterations;++i){int byte0=values[valuesOffset++]&0xFF;int byte1=values[valuesOffset++]&0xFF;int byte2=values[valuesOffset++]&0xFF;blocks[blocksOffset++]=(byte)((byte0>>2)&0x3F);blocks[blocksOffset++]=(byte)(((byte0<<4)|(byte1>>4))&0x3F);blocks[blocksOffset++]=(byte)(((byte1<<2)|(byte2>>6))&0x3F);blocks[blocksOffset++]=(byte)(byte2&0x3F);}}
private static readonly System.Text.RegularExpressions.Regex LOCAL_FILE=new System.Text.RegularExpressions.Regex(@"^[A-Za-z]:[\\/].*",System.Text.RegularExpressions.RegexOptions.Compiled);public static string NormalizeRepositoryPath(string s){if(string.IsNullOrEmpty(s))throw new ArgumentException(nameof(s));string result=s;if(LOCAL_FILE.IsMatch(s)){result=s;}else if(Uri.TryCreate(s,UriKind.Absolute,out var uri)&&string.Equals(uri.Scheme,"file",StringComparison.OrdinalIgnoreCase)){result=uri.GetComponents(UriComponents.Path,UriFormat.Unescaped);}result=result.Replace(System.IO.Path.DirectorySeparatorChar,'/');var elements=result.Split(new[]{'/'},StringSplitOptions.RemoveEmptyEntries);if(elements.Length==0)throw new ArgumentException(s);var last=elements[^1];if(!result.EndsWith(Constants.DOT_GIT,StringComparison.Ordinal)&&!(last.Length>=Constants.DOT_GIT_EXT.Length&&last.IndexOf(Constants.DOT_GIT_EXT,StringComparison.Ordinal)==last.Length-Constants.DOT_GIT_EXT.Length))result+=Constants.DOT_GIT_EXT;return result;}
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request,options);}
public virtual string GetAccessKeySecret(){return this.accessKeySecret;}
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller=CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request,options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
request = BeforeClientExecution(request); return ExecuteListMonitoringExecutions(request);
public DescribeJobRequest(string jobId, string vaultName){SetJobId(jobId);SetVaultName(vaultName);}
public virtual EscherRecord Get(int index){return escherRecords[index];}
request = BeforeClientExecution(request); GetApisResult result = ExecuteGetApis(request); return result;
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request){request = BeforeClientExecution(request);return ExecuteDeleteSmsChannel(request);}
return trackingRefUpdate;
void print(bool b){String.valueOf(b);}
return (QueryNode)GetChildren()[0];
this.index = workdirTreeIndex;
field_1_formatFlags = in.ReadShort();
public GetThumbnailRequest(): base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto"){Protocol = ProtocolType.HTTPS;}
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
public virtual PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){request = BeforeClientExecution(request);return ExecutePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange PrefixToOrdRange(string dim){return get.PrefixToOrdRange(dim);}
string symbol = ""; if (startIndex >= 0 && startIndex < InputStream.Size) { symbol = InputStream.GetText(Interval.Of(startIndex, startIndex)); } symbol = Antlr4.Runtime.Misc.Utils.EscapeWhitespace(symbol, false); return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(Antlr4.Runtime.LexerNoViableAltException).Name, symbol);
public virtual E PeekFirst(){return PeekFirstImpl();}
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options=new InvokeOptions();options.RequestMarshaller=CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller=CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request,options);}
public NumberFormatIndexRecord Copy(){return this;}
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity){initialCapacity=ArrayUtils.IdealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
public override TokenStream Create(TokenStream input){return new HyphenatedWordsFilter(input);}
public override CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request){request=BeforeClientExecution(request);return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(String fileName, String mode) : this(new File(fileName), mode) {}
request = BeforeClientExecution(request); return ExecuteDeleteWorkspaceImage(request);
public static string ToString(StringBuilder value){var sb=new StringBuilder(16);WriteHex(sb,value,16,"");return sb.ToString();}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options=new InvokeOptions();options.RequestMarshaller=UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller=UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request,options);}
private HSSFColor GetColor(int index){if(index==HSSFColorPredefined.AUTOMATIC.Index){return null;}HSSFColor b=_palette.GetColor(index);if(b==null){return null;}return new CustomColor(b);}
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol){throw new System.NotImplementedException();}
public override void Serialize(ILittleEndianOutput out1){out1.WriteShort(field_1_number_crn_records);out1.WriteShort(field_2_sheet_table_index);}
public DescribeDBEngineVersionsResult DescribeDBEngineVersions(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
this._character = character; this._fontIndex = fontIndex; FormatRun(fontIndex, character);
public static byte[] ToBytes(char[] chars,int offset,int length){int end=offset+length;byte[] result=new byte[length*2];int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public UploadArchiveResult ExecuteUploadArchive(UploadArchiveRequest request){request = BeforeClientExecution(request);return ExecuteUploadArchive(request);}
public virtual List<Token> GetHiddenTokensToLeft(int tokenIndex){return GetHiddenTokensToLeft(tokenIndex, -1);}
public override bool Equals(object obj){if(this==obj)return true;if(obj==null)return false;if(GetType()!=obj.GetType())return false;if(!base.Equals(obj))return false;AutomatonQuery other=(AutomatonQuery)obj;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;if(!compiled.Equals(other.compiled))return false;return true;}
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Keys.Count]; int i = 0; foreach (SpanQuery sqIter in weightBySpanQuery.Keys) { SpanQuery sq = sqIter; float boost = weightBySpanQuery[sq]; if (1f != boost) sq = new SpanBoostQuery(sq, boost); spanQueries[i++] = sq; } SpanQuery spanQuery; if (spanQueries.Length > 1) { spanQuery = new SpanOrQuery(spanQueries); } else { return spanQueries[0]; } return spanQuery;
return new StashCreateCommand(repo);
public FieldInfo Get(string fieldName){return FieldInfo.GetByName(fieldName);}
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request,options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller=GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options=new InvokeOptions();options.RequestMarshaller=ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller=ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request,options);}
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=SetInstanceProtectionRequestMarshaller.Instance;options.ResponseUnmarshaller=SetInstanceProtectionResponseUnmarshaller.Instance;return Invoke<SetInstanceProtectionResponse>(request,options);}
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
public void Add(char[] output,int offset,int len,int posLength,int endOffset){if(count==outputs.Length){var nextOutputs=new CharsRefBuilder[ArrayUtil.Oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF)];var nextPosLengths=new int[ArrayUtil.Oversize(count+1,sizeof(int))];var nextEndOffsets=new int[ArrayUtil.Oversize(count+1,sizeof(int))];Array.Copy(outputs,0,nextOutputs,0,count);Array.Copy(posLengths,0,nextPosLengths,0,count);Array.Copy(endOffsets,0,nextEndOffsets,0,count);outputs=nextOutputs;posLengths=nextPosLengths;endOffsets=nextEndOffsets;}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);posLengths[count]=posLength;endOffsets[count]=endOffset;++count;}
public FetchLibrariesRequest() : base("cloudphoto", "2017-07-11", "FetchLibraries", "CloudPhoto") { Protocol = ProtocolType.HTTPS; }
public bool Objects(FileSystem fs){return fs.Exists();}
public FilterOutputStream(System.IO.Stream @out){this.@out=@out;}
public ScaleClusterRequest() : base("csk", "ScaleCluster", "2015-12-15", "CS") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public virtual DVConstraint CreateTimeConstraint(int operatorType,string formula1,string formula2){return DataValidationConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options=new InvokeOptions();options.RequestMarshaller=ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller=ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request,options);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request,options);}
public virtual void SetSharedFormula(bool flag){field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);}
bool reuseObjects() { return reuseObjects; }
ErrorNode t = new ErrorNodeImpl(badToken);AddAnyChild(t);t.SetParent(this);return t;
public LatvianStemFilterFactory(IDictionary<string, string> args) : base(args){ if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + args); } }
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args){return loader.NewInstance(name, args);}
public AddAlbumPhotosRequest(): base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto"){Protocol = ProtocolType.HTTPS;}
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
public override RevFilter Clone(){return new Binary(a.Clone(), b.Clone());}
public bool SomeMethod(object o){return o is ArmenianStemmer;}
public sealed bool ProtectedHasArray() { return protectedHasArray; }
public override UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request = BeforeClientExecution(request);return ExecuteUpdateContributorInsights(request);}
public virtual void FileShare(){writeProtect=null;fileShare=null;records.Remove(fileShare);records.Remove(writeProtect);}
public SolrSynonymParser(Analyzer analyzer, bool dedup, bool expand) : base(analyzer, dedup, expand) { this.expand = expand; this.dedup = dedup; this.analyzer = analyzer; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options=new InvokeOptions();options.RequestMarshaller=RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller=RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request,options);}
return getObjectData()[findObjectRecord()];
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public override string ToString(){return GetKey() + ": " + GetValue();}
public override ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request){request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller=GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request,options);}
public static FunctionMetadata GetFunctionByNameInternal(string name){if(name==null){return null;}FunctionMetadata fd=GetFunctionByNameInternal(name);if(fd==null){fd=GetIndex().GetInstance(name);if(fd==null){return GetInstanceCetab(name-1);}}return fd;}
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request,options);}
public static ObjectId InsertId(ObjectId changeId, string message){return changeId;}
public long GetObjectSize(AnyObjectId objectId,int typeHint){long sz=db.GetObjectSize(this,objectId,typeHint);if(0<sz)return sz;if(typeHint==OBJ_ANY)throw new MissingObjectException(objectId.Copy(),JGitText.Get().UnknownObjectType2);throw new MissingObjectException(objectId.Copy(),typeHint);}
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options=new InvokeOptions();options.RequestMarshaller=ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller=ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request,options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options=new InvokeOptions();options.RequestMarshaller=PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller=PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request,options);}
public NumberPtg(LittleEndianInput in1): this(in1.ReadDouble()){}
public virtual GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){request = BeforeClientExecution(request); return request;}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request,options);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options=new InvokeOptions();options.RequestMarshaller=ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller=ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request,options);}
DeleteAlarmRequest request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request);
return new PortugueseStemFilter(input);
reserved = new byte[ENCODED_SIZE];
public override bool Remove(object obj){lock(mutex){return c.Remove(obj);}}
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request){request = BeforeClientExecution(request);return ExecuteGetDedicatedIp(request);}
public override string ToString(){return " >= _p" + precedence;}
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request){request = BeforeClientExecution(request);return ExecuteListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){_loadBalancerName = loadBalancerName;_policyName = policyName;}
options = _options.options;
public UnbufferedCharStream(){data=new char[bufferSize=n=0];}
GetOperationsRequest executeGetOperations = BeforeClientExecution(request); return new GetOperationsResult();
public void B(byte[] b, int o){encodeInt32.NB(b,o+4,w1);encodeInt32.NB(b,o+8,w2);encodeInt32.NB(b,o+12,w3);encodeInt32.NB(b,o+16,w4);encodeInt32.NB(b,o,w5);}
public WindowOneRecord(RecordInputStream in1){field_1_h_hold=in1.ReadShort();field_2_v_hold=in1.ReadShort();field_3_width=in1.ReadShort();field_4_height=in1.ReadShort();field_5_options=in1.ReadShort();field_6_active_sheet=in1.ReadShort();field_7_first_visible_tab=in1.ReadShort();field_8_num_selected_tabs=in1.ReadShort();field_9_tab_width_ratio=in1.ReadShort();}
request = BeforeClientExecution(request); return ExecuteStopWorkspaces(request);
public void Close(){if(channel.IsOpen()){try{isOpen=false;try{Dump();}finally{}try{channel.Truncate(FileLength());}finally{}}finally{channel.Close();fos.Close();}}}
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request,options);}
public string Surface(int wordId, char[] surface, int off, int len){return null;}
return pathStr;
public static double Variance(double?[] v){double m=0,s=0;int n=0;for(int i=0;i<v.Length;i++){if(v[i].HasValue&&!double.IsNaN(v[i].Value)){++n;double delta=v[i].Value-m;m+=delta/n;s+=delta*(v[i].Value-m);}}double r=n>1?s/(n-1):0;return r;}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request,options);}
public sealed bool passedThroughNonGreedyDecision(){return passedThroughNonGreedyDecision;}
} { ) (  ; return end ) 0 (
public void Traverse(CellHandler handler){int width=range.GetLastColumn()-range.GetFirstColumn()+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext(sheet,range);for(int rowNumber=range.GetFirstRow();rowNumber<=range.GetLastRow();++rowNumber){Row currentRow=sheet.GetRow(rowNumber);int rowSize=width;if(currentRow==null&&!traverseEmptyCells)continue;ctx.SetRow(currentRow,rowNumber);handler.OnRow(ctx);for(int colNumber=range.GetFirstColumn();colNumber<=range.GetLastColumn();++colNumber){Cell currentCell=currentRow==null?null:currentRow.GetCell(colNumber);if(currentCell==null&&!traverseEmptyCells)continue;ctx.SetCell(currentCell,colNumber);handler.OnCell(ctx);}}}
return pos;
public int CompareTo(ScoreTerm other){if(this.boost==other.boost)return this.bytes.Get().CompareTo(other.bytes.Get());else return float.Compare(other.boost,this.boost);}
public int Normalize(char[] s, int len){for(int i=0;i<len;++i){switch(s[i]){case HAMZA_ABOVE:case HEH_GOAL:case HEH_YEH:s[i]=HEH;break;case KEHEH:s[i]=KAF;break;case YEH_BARREE:case FARSI_YEH:s[i]=YEH;break;case delete:len=Delete(s,i,len);i--;break;default:break;}}return len;}
public void Serialize(LittleEndianOutput output){output.WriteShort(_options);}
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
public KeySchemaElement SetAttributeName(string attributeName){this.attributeName=attributeName;return this;}public KeySchemaElement SetKeyType(KeyType keyType){this.keyType=keyType;return this;}public override string ToString(){return $"({attributeName}, {keyType})";}
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller=GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request,options);}
public virtual bool HasObject(NGit.AnyObjectId id){return FindOffset(id) != -1;}
public GroupingSearch AllGroups(bool allGroups){this.allGroups = allGroups;return this;}
public void Put(string dimName,bool v){DimConfig ft=fieldTypes.Get(dimName);if(ft==null){ft=new DimConfig(dimName,v);fieldTypes.Put(dimName,ft);}else{ft.multiValued=v;}}
public int Size(){int size=0;for(var i=cells.Keys.GetEnumerator();i.MoveNext();){var c=i.Current;Cell e=cmd.e(c);if(e>=0){size++;}}return size;}
public virtual DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request){request = BeforeClientExecution(request);return ExecuteDeleteVoiceConnector(request);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request,options);}
