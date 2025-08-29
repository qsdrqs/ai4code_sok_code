public virtual void Write(LittleEndianOutput out){out.WriteShort();}
void AddAll(BlockList<T> src){if(src.size==0)srcDirIdx=0;for(;srcDirIdx<src.tailDirIdx;srcDirIdx++)AddAll(src.directory[srcDirIdx],0,BLOCK_SIZE);if(src.tailBlkIdx!=0)AddAll(src.tailBlock,0,src.tailBlkIdx);}
public virtual void Append(byte b){if(upto==blockSize){if(currentBlock!=null){AddBlock(currentBlock);}currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId() { }
public virtual DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request){request = BeforeClientExecution(request);return ExecuteDeleteDomainEntry(request);}
public long RamBytesUsed(){ return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0); }
public string GetFullMessage(){byte[] raw=buffer;int msgB=RawParseUtils.tagMessage;if(msgB<0){return "";}return RawParseUtils.decode(guessEncoding(),raw,msgB,raw.Length);}
public POIFSFileSystem(){_header.SetBATCount();_header.SetBATArray(new int[]{1});BATBlock bb=BATBlock.CreateEmptyBATBlock(bigBlockSize,false);bb.SetOurBlockIndex(1);_bat_blocks.Add(bb);SetNextBlock(0,POIFSConstants.END_OF_CHAIN);SetNextBlock(1,POIFSConstants.FAT_SECTOR_BLOCK);_property_table.SetStartBlock(0);}
public void SetAddress(int address){slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];System.Diagnostics.Debug.Assert(slice != null);upto = address & ByteBlockPool.BYTE_BLOCK_MASK;offset0 = address;System.Diagnostics.Debug.Assert(upto < ByteBlockPool.BYTE_BLOCK_SIZE);}
public SubmoduleAddCommand SubmoduleAddCommand(string path){this.path = path;return this;}
public virtual ListIngestionsResult ListIngestions(ListIngestionsRequest request){request = BeforeClientExecution(request);return ExecuteListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState){;SwitchTo(lexState);}
public override GetShardIteratorResult getShardIterator(GetShardIteratorRequest request){request = beforeClientExecution(request);return executeGetShardIterator(request);}
public ModifyStrategyRequest(): base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis"){}
public bool Ready(){lock(@lock){if(in==null){throw new IOException("InputStreamReader is closed");}try{return bytes.HasRemaining||in.Available()>0;}catch(IOException){return false;}}}
public EscherOptRecord(){}
public int Read(char[] buffer,int offset,int length){if(buffer==null){throw new NullReferenceException("buffer == null");}Arrays.CheckOffsetAndCount(buffer.Length,offset,length);if(length==0){return 0;}int copylen=count-pos<length?count-pos:length;for(int i=0;i<copylen;i++){buffer[offset+i]=this.buffer[pos+i];}return copylen;}
public OpenNLPSentenceBreakIterator(){this.sentenceOp = sentenceOp;}
void Write(String str){write(str != null ? str : String.ValueOf((Object)null));}
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public virtual V Next(){return base.nextEntry.GetValue();}
public void ReadBytes(byte[] b,int offset,int len,bool useBuffer){int available=bufferLength-bufferPosition;if(len<=available){if(len>0)Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){Refill();if(bufferLength<len){Array.Copy(buffer,0,b,offset,bufferLength);throw new EndOfStreamException("read past EOF: "+this);}else{Array.Copy(buffer,0,b,offset,len);}}else{long after=bufferStart+bufferPosition+len;if(after>Length())throw new EndOfStreamException("read past EOF: "+this);ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options=new InvokeOptions();options.RequestMarshaller=TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller=TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request,options);}
public void Method(){ throw new NotSupportedException(); }
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options=new InvokeOptions();options.RequestMarshaller=ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller=ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request,options);}
public override void SetParams(string @params){base.SetParams(@params);language=country=variant=string.Empty;var tokens=@params.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
public virtual DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request = BeforeClientExecution(request);return ExecuteDeleteDocumentationVersion(request);}
public override bool Equals(object obj){if(!(obj is FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shape.SetParent(this);shape.SetAnchor(anchor);shapes.Add(shape);OnCreate(shape);return shape;}
string GetSheetName(int sheetIndex){return GetBoundSheetRec(sheetIndex).GetSheetname();}
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller=GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request,options);}
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
void ProcessMulBlankRecord(MulBlankRecord mbr){for(int j=0;j<mbr.GetNumColumns();j++){BlankRecord br=new BlankRecord();br.SetColumn((short)(j+mbr.GetFirstColumn()));br.SetRow(mbr.GetRow());br.SetXFIndex(mbr.GetXFAt());InsertCell(br);}}
public static string (){java.lang.StringBuilder sb=new java.lang.StringBuilder();sb.append("\\Q");apos=0;int k;while((k=@string.indexOf("\\E",apos))>=0){sb.append(@string.substring(apos,k+2)).append("\\\\E\\Q");apos=k+2;}return sb.append(@string.substring(apos)).append("\\E").toString();}
public ByteBuffer(object value){throw new ReadOnlyBufferException();}
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=(short)nColumns;_nRows=(short)nRows;object[] vv=new object[_nColumns*_nRows];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[GetValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
public override string ToString(){ return GetType().FullName + " [" + GetValueAsString() + "]"; }
public override string ToString(){return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";}
void () { refCount.IncrementAndGet(); }
public virtual UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request=BeforeClientExecution(request);return ExecuteUpdateConfigurationSetSendingEnabled(request);}
public int GetXBATBlockCount(){return GetXBATEntriesPerBlock() * ;}
void Pow10(int pow10){TenPower tp=TenPower.GetInstance(Math.Abs(pow10));if(pow10<0){MulShift(tp._divisor,tp._divisorShift);}else{MulShift(tp._multiplicand,tp._multiplierShift);}}
public override string ToString(){StringBuilder b=new StringBuilder();int l=length;b.Append(Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(getComponent(i));if(i<l-1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher=fetcher;SetRoleName(roleName);return this;}
void (ProgressMonitor pm) { }
void () { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
public E Previous(){if(iterator.PreviousIndex()>=start){return iterator.Previous();}throw new NoSuchElementException();}
string () { return ; }
int Method(int value){for (int i = 0; i < mSize; i++) if (mValues[i] == value) return -1;}
public List<CharsRef> GetStems(char[] word,int length){List<CharsRef> stems=Stem(word,length);if(stems.Count<2){return stems;}CharArraySet terms=new CharArraySet(8,dictionary.IgnoreCase);List<CharsRef> deduped=new List<CharsRef>();foreach(CharsRef s in stems){if(!terms.Contains(s)){deduped.Add(s);terms.Add(s);}}return deduped;}
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
void SetPosition(long pos){currentBlockIndex=(int)(pos>>blockBits);currentBlock=blocks[currentBlockIndex];currentBlockUpto=(int)(pos&blockMask);}
s = (int)Math.Min(available(), Math.Max(0, n)); ptr += s;
public BootstrapActionDetail() { SetBootstrapActionConfig(bootstrapActionConfig); }
public void Serialize(ILittleEndianOutput out1){out1.WriteShort(field_1_row);out1.WriteShort(field_2_col);out1.WriteShort(field_3_flags);out1.WriteShort(field_4_shapeid);out1.WriteShort((short)field_6_author.Length);out1.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,out1);}else{StringUtil.PutCompressedUnicode(field_6_author,out1);}if(field_7_padding!=null){out1.WriteByte((byte)field_7_padding.Value);} }
(string @string) => lastIndexOf;
bool Add(E @object){ return addLastImpl; }
public virtual void Unset(string subsection){ConfigSnapshot src,res;do{src=state.Get();res=UnsetSection(src,section,subsection);}while(!state.CompareAndSet(src,res));}
public virtual string TagName(){return tagName;}
public void Add(int index, SubRecord element){subrecords.Add(element);}
public virtual bool Remove(object o){ lock (mutex){ return @delegate().Remove(o); } }
public DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input){return new DoubleMetaphoneFilter();}
() { return inCoreLength; }
void (bool newValue) { ; }
public Pair(ContentSource oldSource, ContentSource newSource){this.oldSource = oldSource; this.newSource = newSource;}
(int i){ if() throw new IndexOutOfRangeException(i.ToString()); return entries[i]; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { UriPattern = "/repos"; Method = MethodType.POST; }
bool (  ) { }
public virtual void Remove(){if(expectedModCount==list.modCount){if(lastLink!=null){Link next=lastLink.next;Link<ET> previous=lastLink.previous;next.previous=previous;previous.next=next;if(lastLink==link){pos--;}link=previous;lastLink=null;expectedModCount++;list.size--;list.modCount++;}else{throw new System.InvalidOperationException();}}else{throw new System.InvalidOperationException();}}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options=new InvokeOptions();options.RequestMarshaller=MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller=MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request,options);}
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request){request = beforeClientExecution(request);return executeAllocateHostedConnection;}
() => { }
public static WeightedTerm[] GetTerms(Query query){ return GetTerms(query, false); }
ByteBuffer() { throw new java.nio.ReadOnlyBufferException(); }
public static void Decode(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public virtual string GetHumanishName(){string s=GetPath();if("/".Equals(s)||"".Equals(s))s=GetHost();if(s==null)throw new ArgumentException();string[] elements;if("file".Equals(scheme)||LOCAL_FILE.IsMatch(s))elements=s.Split(new[]{Path.DirectorySeparatorChar,'/'},StringSplitOptions.RemoveEmptyEntries);else elements=System.Text.RegularExpressions.Regex.Split(s,"/+");if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result))result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request,options);}
string (){return ;}
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller=CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request,options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request,options);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options=new InvokeOptions();options.RequestMarshaller=ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller=ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request,options);}
public DescribeJobRequest(string vaultName, string jobId){SetVaultName(vaultName);SetJobId(jobId);}
public virtual EscherRecord Get(int index){return escherRecords[index];}
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request,options);}
internal TrackingRefUpdate(){ }
void Method(){Console.Write(Convert.ToString(b));}
QueryNode QueryNode(){ return getChildren[0]; }
public NotIgnoredFilter(WorkingTreeIterator workdirTreeIndex) { this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream input){field_1_formatFlags = input.ReadShort();}
GetThumbnailRequest(ProtocolType.Https);
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request,options);}
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){request = BeforeClientExecution(request);return ExecutePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange GetOrdRange(){return prefixToOrdRange[dim];}
public override string ToString(){string symbol="";if(startIndex>=0&&startIndex<GetInputStream().Size){symbol=GetInputStream().GetText(Interval.Of(startIndex,startIndex));symbol=Utils.EscapeWhitespace(symbol,false);}return string.Format(CultureInfo.CurrentCulture,"{0}('{1}')",typeof(LexerNoViableAltException).Name,symbol);}
public E PeekFirst() { return peekFirstImpl; }
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
public NumberFormatIndexRecord Copy() { return copy; }
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
public SparseIntArray(int initialCapacity){initialCapacity=ArrayUtils.IdealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
HyphenatedWordsFilter HyphenatedWordsFilter(){return new HyphenatedWordsFilter(input);}
public virtual CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request){request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(string fileName, string mode){new FileInfo(fileName);}
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request,options);}
public static string Hex(long value){StringBuilder sb=new StringBuilder(16);writeHex(sb,value,16,"");return sb.ToString();}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options=new InvokeOptions();options.RequestMarshaller=UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller=UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request,options);}
public HSSFColor HSSFColor(short index){if(index==HSSFColorPredefined.AUTOMATIC.GetIndex()){return HSSFColorPredefined.AUTOMATIC.GetColor();}byte[] b=_palette.GetColor(index);return b==null?null:new CustomColor();}
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol){throw new NotImplementedFunctionException(_functionName);}
public void Serialize(LittleEndianOutput @out){@out.WriteShort((short)field_1_number_crn_records);@out.WriteShort((short)field_2_sheet_table_index);}
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(int character,int fontIndex){this._character=character;this._fontIndex=fontIndex;}
public static byte[] ToByteArray(char[] chars, int offset, int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public virtual UploadArchiveResult UploadArchive(UploadArchiveRequest request){request = beforeClientExecution;return executeUploadArchive(request);}
public IList GetHiddenTokensToLeft(int tokenIndex){ return GetHiddenTokensToLeft(tokenIndex, -1); }
public override bool Equals(object obj){if(object.ReferenceEquals(this,obj))return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
public virtual SpanQuery SpanQuery(){SpanQuery[] spanQueries=new SpanQuery[weightBySpanQuery.Count];int i=0;foreach(SpanQuery sqKey in weightBySpanQuery.Keys){float boost=weightBySpanQuery[sqKey];SpanQuery sq=sqKey;if(boost!=1f){sq=new SpanBoostQuery(sq,boost);}spanQueries[i++]=sq;}if(spanQueries.Length==1)return spanQueries[0];else return new SpanOrQuery(spanQueries);}
public static StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
public FieldInfo FieldInfo(string fieldName) { return byName[fieldName]; }
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request,options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller=GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request,options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options=new InvokeOptions();options.RequestMarshaller=CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller=CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request){request = BeforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options=new InvokeOptions();options.RequestMarshaller=ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller=ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request,options);}
public void Add(char[] output,int offset,int len,int endOffset,int posLength){if(count==outputs.Length){outputs=ArrayUtil.Grow(outputs,count+1);}if(count==endOffsets.Length){int[] next=new int[ArrayUtil.Oversize(1+count,4)];Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}if(count==posLengths.Length){int[] next=new int[ArrayUtil.Oversize(1+count,4)];Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto") { }
public bool Exists => fs.Exists;
public FilterOutputStream(java.io.OutputStream @out){this.@out = @out;}
UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT;
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2){return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
ListObjectParentPathsResult ListObjectParentPaths(){request = BeforeClientExecution(request);return ExecuteListObjectParentPaths(request);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request,options);}
void Method() { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool () { }
public virtual ErrorNode ErrorNode(Token badToken){var t=new ErrorNodeImpl(badToken);AddAnyChild(t);t.SetParent();return t;}
public LatvianStemFilterFactory(IDictionary<string,string> args) : base(args) { if (args.Count != 0) { throw new ArgumentException("Unknown parameters: " + args); } }
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){request = BeforeClientExecution(request);return ExecuteRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory ForName(string name, IDictionary<string, string> args){return loader.NewInstance(name, args);}
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto"){(); }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller=GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request,options);}
Binary RevFilter() { return new Binary(a.Clone(), b.Clone()); }
bool (object o){ return; }
public bool HasArray() { return ProtectedHasArray(); }
UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request=beforeClientExecution;return executeUpdateContributorInsights(request);}
void () { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
public byte[] GetObjectData() { return findObjectRecord.GetObjectData(); }
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
public virtual string ToString(){return GetKey() + ": " + GetValue();}
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request){request = BeforeClientExecution(request); return ExecuteListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static short GetFunctionIndex(string name){FunctionMetadata fd=GetInstance().GetFunctionByNameInternal(name);if(fd==null){fd=GetInstanceCetab().GetFunctionByNameInternal(name);if(fd==null){return -1;}}return (short)fd.GetIndex();}
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeAnomalyDetectors(request);}
public static string (string message, ObjectId changeId){ return insertId; }
public virtual long GetObjectSize(AnyObjectId objectId, int typeHint){long sz = db.GetObjectSize(objectId, typeHint);if (sz < 0){if (typeHint == Constants.OBJ_ANY){throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2);}throw new MissingObjectException(objectId.Copy(), typeHint);}return sz;}
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options=new InvokeOptions();options.RequestMarshaller=ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller=ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request,options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
internal NumberPtg(){@in.readDouble();}
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller=GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request,options);}
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request){request = BeforeClientExecution(request);return executeDescribeDetector;}
public virtual ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request){request = BeforeClientExecution(request);return ExecuteReportInstanceStatus(request);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request,options);}
public override TokenStream TokenStream(){return new PortugueseStemFilter(input);}
public FtCblsSubRecord(){reserved = new ;}
public virtual bool Remove(object @object){lock (mutex){return c.Remove(@object);}}
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller=GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request,options);}
string (  ) { return ; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){SetLoadBalancerName(loadBalancerName);SetPolicyName(policyName);}
internal WindowProtectRecord(object options){}
public UnbufferedCharStream(int bufferSize){data = new char[bufferSize];}
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller=GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request,options);}
public override void Encode(byte[] b,int o){NB.EncodeInt32(b,o,w1);NB.EncodeInt32(b,o+4,w2);NB.EncodeInt32(b,o+8,w3);NB.EncodeInt32(b,o+12,w4);NB.EncodeInt32(b,o+16,w5);}
public WindowOneRecord(RecordInputStream in1){field_1_h_hold=in1.ReadShort();field_2_v_hold=in1.ReadShort();field_3_width=in1.ReadShort();field_4_height=in1.ReadShort();field_5_options=in1.ReadShort();field_6_active_sheet=in1.ReadShort();field_7_first_visible_tab=in1.ReadShort();field_8_num_selected_tabs=in1.ReadShort();field_9_tab_width_ratio=in1.ReadShort();}
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
public void Close(){if(isOpen){isOpen=false;try{Dump();}finally{try{channel.Truncate(fileLength);}finally{try{channel.Close();}finally{fos.Close();}}}}}
public virtual DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeMatchmakingRuleSets(request);}
public String(string wordId, char[] surface, int off, int len) { }
String(){}
public static double Variance(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request,options);}
bool PassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
() { return end; }
public void Walk(CellHandler handler){firstRow=range.GetFirstRow();lastRow=range.GetLastRow();firstColumn=range.GetFirstColumn();lastColumn=range.GetLastColumn();width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();Row currentRow=null;Cell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;++ctx.rowNumber){currentRow=sheet.GetRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;++ctx.colNumber){currentCell=currentRow.GetCell(ctx.colNumber);if(currentCell==null){continue;}if(IsEmpty(currentCell)&&!traverseEmptyCells){continue;}rowSize=ArithmeticUtils.mulAndCheck(ArithmeticUtils.subAndCheck(ctx.rowNumber,firstRow),width);ctx.ordinalNumber=ArithmeticUtils.addAndCheck(rowSize,(ctx.colNumber-firstColumn+1));handler.OnCell(currentCell,ctx);}}}
() { }
public int CompareTo(ScoreTerm other){if(this.boost==other.boost)return other.bytes.Get().CompareTo(bytes.Get());else return this.boost.CompareTo(other.boost);}
public static int Normalize(char[] s,int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;goto case KEHEH;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=Delete(s,i,len);i--;break;default:break;}}return len;}
void Write(LittleEndianOutput output){output.WriteShort();}
public DiagnosticErrorListener(){this.exactOnly = exactOnly;}
public KeySchemaElement(string attributeName){SetAttributeName(attributeName);SetKeyType(keyType.ToString());}
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request, options);}
public bool Has(AnyObjectId id){return FindOffset(id) != -1;}
public virtual GroupingSearch SetAllGroups(bool allGroups){this.allGroups = allGroups;return this;}
public void SetMultiValued(string dimName, bool v){DimConfig ft = fieldTypes.Get(dimName); if(ft == null){ft = new DimConfig(); fieldTypes.Put(dimName, ft);} ft.multiValued = v;}
public int Size(){var i=cells.Keys.GetEnumerator();size=0;while(i.MoveNext()){char c=i.Current;Cell e=at(c);if(e.cmd>=0){size++;}}return size;}
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request,options);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request, options);}
