public void Serialize(LittleEndianOutput output){ output.WriteShort(); }
void addAll(BlockList<T> src){if(src.size==0)srcDirIdx=0;for(;srcDirIdx<src.tailDirIdx;srcDirIdx++)addAll(src.directory[srcDirIdx],0,BLOCK_SIZE);if(src.tailBlkIdx!=0)addAll(src.tailBlock,0,src.tailBlkIdx);}
void (  b ) { if ( ) { if ( currentBlock != null ) { addBlock ( currentBlock ) ; } currentBlock = new [ blockSize ] ; upto = 0 ; } currentBlock [ upto ++ ] = b ; }
ObjectId() { }
DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request){request=BeforeClientExecution(request);return ExecuteDeleteDomainEntry;}
public virtual long RamBytesUsed(){return ((termOffsets != null) ? termOffsets.ramBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.ramBytesUsed() : 0);}
public string GetMessage(){byte[] raw=buffer;int msgB=RawParseUtils.tagMessage;if(msgB<0){return"";}return RawParseUtils.decode(guessEncoding(),raw,msgB,raw.Length);}
public POIFSFileSystem() : this(true) { _header.SetBATCount(1); _header.SetBATArray(new[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
public void Reset(int address){slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];System.Diagnostics.Debug.Assert(slice != null);upto = address & ByteBlockPool.BYTE_BLOCK_MASK;offset0 = address;System.Diagnostics.Debug.Assert(upto < ByteBlockPool.BYTE_BLOCK_SIZE);}
SubmoduleAddCommand(string path){this.path = path; return this;}
ListIngestionsResult() { request = beforeClientExecution(request); return executeListIngestions(request); }
public QueryParserTokenManager(CharStream stream, int lexState){SwitchTo(lexState);}
GetShardIteratorResult(GetShardIteratorRequest request){request=beforeClientExecution(request);return executeGetShardIterator;}
public ModifyStrategyRequest():base("aegis","2016-11-11","ModifyStrategy","vipaegis"){Method=MethodType.POST;}
public bool Ready(){lock(@lock){if(@in==null){throw new IOException("InputStreamReader is closed");}try{return bytes.hasRemaining||@in.available()>0;}catch(Exception){return false;}}}
EscherOptRecord() { }
public int Read(char[] buffer,int offset,int length){lock(this){if(buffer==null){throw new ArgumentNullException("buffer");}if(offset<0||length<0||offset+length>buffer.Length){throw new ArgumentOutOfRangeException();}if(length==0){return 0;}int copylen=count-pos<length?count-pos:length;for(int i=0;i<copylen;i++){buffer[offset+i]=(char)this.buffer[pos+i];}return copylen;}}
public OpenNLPSentenceBreakIterator() { this.sentenceOp = sentenceOp; }
void Print(string str){Write(str != null ? str : Convert.ToString((object)null));}
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
V() { return base.nextEntry.getValue(); }
public void Read(byte[] b,int offset,int len,bool useBuffer){int available=bufferLength-bufferPosition;if(len<=available){if(len>0)Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{if(available>0){Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){Refill();if(bufferLength<len){Array.Copy(buffer,0,b,offset,bufferLength);throw new EndOfStreamException("read past EOF: "+this);}else{Array.Copy(buffer,0,b,offset,len);}}else{long after=bufferStart+bufferPosition+len;if(after>Length())throw new EndOfStreamException("read past EOF: "+this);ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
TagQueueResult TagQueue(TagQueueRequest request){request=BeforeClientExecution(request);return ExecuteTagQueue(request);}
public void Remove() { throw new NotSupportedException(); }
public virtual ModifyCacheSubnetGroupResult CacheSubnetGroup(){request=BeforeClientExecution(request);return ExecuteModifyCacheSubnetGroup(request);}
public void setParams(string @params){base.setParams(@params);language=country=variant="";var tokens=@params.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
public override DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request = BeforeClientExecution(request); return ExecuteDeleteDocumentationVersion(request);}
public override bool Equals(object obj){if(!(obj is FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request = BeforeClientExecution(request);return ExecuteGetInstanceAccessDetails;}
public HSSFPolygon HSSFPolygon(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shape.setParent;shape.setAnchor(anchor);shapes.Add(shape);onCreate(shape);return shape;}
string GetSheetName(int sheetIndex){ return getBoundSheetRec(sheetIndex).getSheetname; }
GetDashboardResult GetDashboard(GetDashboardRequest request){request = beforeClientExecution(request);return executeGetDashboard;}
AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request=BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
public void HandleMulBlankRecord(MulBlankRecord mbr){for(j=0;j<mbr.GetNumColumns();j++){BlankRecord br=new BlankRecord();br.SetColumn((short)(j+mbr.GetFirstColumn()));br.SetRow(mbr.GetRow());br.SetXFIndex(mbr.GetXFAt);InsertCell(br);}}
public static string Quote(string str){var sb=new System.Text.StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=str.IndexOf("\\E",apos,StringComparison.Ordinal))>=0){sb.Append(str.Substring(apos,k+2-apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(str.Substring(apos)).Append("\\E").ToString();}
public ByteBuffer(object value){ throw new ReadOnlyBufferException(); }
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=(short)nColumns;_nRows=(short)nRows;object[] vv=new object[_nColumns*_nRows];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[GetValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request){request=BeforeClientExecution(request);return ExecuteGetIceServerConfig(request);}
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
public override string ToString() { return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")"; }
void IncreaseRefCount() { refCount.IncrementAndGet(); }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
() { return GetXBATEntriesPerBlock() * ; }
private void Pow10(int pow10){TenPower tp=TenPower.GetInstance(Math.Abs(pow10));if(pow10<0){MulShift(tp._divisor,tp._divisorShift);}else{MulShift(tp._multiplicand,tp._multiplierShift);}}
public override string ToString(){var b=new System.Text.StringBuilder();int l=length;b.Append(System.IO.Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i<l-1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){ this.fetcher = fetcher; SetRoleName(roleName); return this; }
void (ProgressMonitor pm) { }
void Method() { if (!first) { ptr = 0; if (!eof()) parseEntry(); } }
public E Previous(){if(iterator.PreviousIndex()>=start){return iterator.Previous();}throw new InvalidOperationException();}
string () { return; }
(value) { for (i = 0; i < mSize; i++) if (mValues[i] == value) return -1; }
public List<CharsRef> SomeMethod(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
private void SetPosition(long pos) { currentBlockIndex = (int)(pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int)(pos & blockMask); }
( n ){ s = (int)System.Math.Min( available(), System.Math.Max(0, n) ); ptr += s; }
public BootstrapActionDetail(){SetBootstrapActionConfig(bootstrapActionConfig);}
public virtual void Serialize(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort((short)field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding.HasValue) { out.WriteByte(field_7_padding.Value); } }
public int LastIndexOf(string @string){ return lastIndexOf; }
bool (E @object){return addLastImpl ;}
public void UnsetSection(string section,string subsection){ConfigSnapshot src,res;do{src=state.Get();res=UnsetSection(src,section,subsection);}while(!state.CompareAndSet(src,res));}
public override java.lang.String tagName(){return tagName;}
void Add(int index, SubRecord element){ subrecords.Add(element); }
bool () { lock (mutex) { return @delegate().Remove(o); } }
public DoubleMetaphoneFilter DoubleMetaphoneFilter(TokenStream input){return new DoubleMetaphoneFilter();}
int InCoreLength() { return inCoreLength; }
void (bool newValue) { ; }
Pair(ContentSource oldSource, ContentSource newSource){this.oldSource = oldSource; this.newSource = newSource;}
(  i ) { if ( ) throw new IndexOutOfRangeException ( i ) ; return entries [ i ] ; }
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(); }
bool (  ) { }
void Method() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
public MergeShardsResult MergeShards(){ request = beforeClientExecution(request); return executeMergeShards(request); }
AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request){request = BeforeClientExecution(request);return ExecuteAllocateHostedConnection;}
() { }
public static WeightedTerm[] GetTerms(Query query){ return GetTerms(query, false); }
ByteBuffer(){throw new ReadOnlyBufferException();}
private static void Encode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
string GetName(){string s=getPath();if("/".Equals(s)||"".Equals(s))s=getHost();if(s==null)throw new IllegalArgumentException();string[] elements;if("file".Equals(scheme)||LOCAL_FILE.IsMatch(s))elements=s.Split(new[]{Path.DirectorySeparatorChar,'/'},StringSplitOptions.RemoveEmptyEntries);else elements=Regex.Split(s,"/+");if(elements.Length==0)throw new IllegalArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result))result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request,options);}
string () { return ; }
public override CreateVpnConnectionResult CreateVpnConnection(){request = beforeClientExecution(request); return executeCreateVpnConnection(request);}
public DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request){request = BeforeClientExecution(request); return ExecuteDescribeVoices(request);}
ListMonitoringExecutionsResult(ListMonitoringExecutionsRequest request){request = beforeClientExecution(request); return executeListMonitoringExecutions;}
public DescribeJobRequest(string vaultName, string jobId){SetVaultName(vaultName);SetJobId(jobId);}
EscherRecord Get(int index){ return escherRecords[index]; }
public GetApisResult GetApis(GetApisRequest request){request = BeforeClientExecution(request);return ExecuteGetApis(request);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request,options);}
internal TrackingRefUpdate() { }
void Foo(){ Console.Write(b.ToString()); }
QueryNode QueryNode() { return getChildren[0]; }
internal NotIgnoredFilter(int workdirTreeIndex){ this.workdirTreeIndex = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1){field_1_formatFlags = in1.ReadShort();}
GetThumbnailRequest(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public virtual PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(){request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request);}
OrdRange() { return prefixToOrdRange[dim]; }
public override string ToString(){string symbol="";if(startIndex>=0&&startIndex<InputStream.Size){symbol=InputStream.GetText(Interval.Of(startIndex,startIndex));symbol=Utils.EscapeWhitespace(symbol,false);}return string.Format(CultureInfo.CurrentCulture,"{0}('{1}')",typeof(LexerNoViableAltException).Name,symbol);}
E peekFirst() { return peekFirstImpl; }
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){request = beforeClientExecution(request); return executeCreateWorkspaces(request);}
public virtual NumberFormatIndexRecord NumberFormatIndexRecord() { return copy; }
public override DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request){ request = BeforeClientExecution(request); return ExecuteDescribeRepositories(request); }
public SparseIntArray(int initialCapacity){initialCapacity=ArrayUtils.IdealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
HyphenatedWordsFilter() { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult CreateDistributionWithTags() { request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode){ new FileInfo(fileName); }
DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){request=beforeClientExecution(request);return executeDeleteWorkspaceImage;}
public static string Convert(long value){ var sb = new StringBuilder(16); writeHex(sb, value, 16, ""); return sb.ToString(); }
public UpdateDistributionResult(UpdateDistributionRequest request){request = beforeClientExecution(request); return executeUpdateDistribution;}
public HSSFColor GetColor(int index){if(index==HSSFColorPredefined.AUTOMATIC.Index){return HSSFColorPredefined.AUTOMATIC.Color;}byte[] b=_palette.GetColor(index);return b==null?null:new CustomColor();}
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol){ throw new NotImplementedFunctionException(_functionName); }
void Serialize(){@out.WriteShort((short)field_1_number_crn_records);@out.WriteShort((short)field_2_sheet_table_index);}
public DescribeDBEngineVersionsResult DescribeDBEngineVersions(){ return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(char character, int fontIndex){this._character = character; this._fontIndex = fontIndex;}
public static byte[] ToBytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public UploadArchiveResult UploadArchive(UploadArchiveRequest request){request=beforeClientExecution;return executeUploadArchive(request);}
public IList<IToken> GetHiddenTokensToLeft(int tokenIndex){return GetHiddenTokensToLeft(tokenIndex, -1);}
public override bool Equals(object obj){if(ReferenceEquals(this,obj))return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
public SpanQuery SpanQuery(){SpanQuery[] spanQueries=new SpanQuery[size()];var sqi=weightBySpanQuery.Keys.GetEnumerator();int i=0;while(sqi.MoveNext()){SpanQuery sq=(SpanQuery)sqi.Current;float boost=weightBySpanQuery[sq];if(boost!=1f){sq=new SpanBoostQuery(sq,boost);}spanQueries[i++]=sq;}return spanQueries.Length==1?spanQueries[0]:SpanOrQuery(spanQueries);}
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo() { return byName[fieldName]; }
DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request){request=BeforeClientExecution(request);return ExecuteDescribeEventSource(request);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
CancelUpdateStackResult() { request = BeforeClientExecution(request); return ExecuteCancelUpdateStack(request); }
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){request = BeforeClientExecution(request);return ExecuteModifyLoadBalancerAttributes(request);}
public override SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request){request = beforeClientExecution(request); return executeSetInstanceProtection(request);}
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options=new InvokeOptions();options.RequestMarshaller=ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller=ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
public void Add(char[] output, int offset, int len, int endOffset, int posLength){if(count==outputs.Length){outputs=ArrayUtil.Grow(outputs,count+1);}if(count==endOffsets.Length){int[] next=new int[ArrayUtil.Oversize(1+count,sizeof(int))];Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}if(count==posLengths.Length){int[] next=new int[ArrayUtil.Oversize(1+count,sizeof(int))];Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public FetchLibrariesRequest():base("CloudPhoto","2017-07-11","FetchLibraries","cloudphoto"){SetProtocol(ProtocolType.HTTPS);}
bool () { return fs.exists; }
FilterOutputStream(){ this.@out = @out; }
ScaleClusterRequest("/clusters/[ClusterId]"); SetMethod(MethodType.PUT);
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2){return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options=new InvokeOptions();options.RequestMarshaller=ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller=ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request,options);}
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeCacheSubnetGroups(request);}
void () { field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag); }
bool (  ) { }
ErrorNode ErrorNode(Token badToken){ErrorNodeImpl t=new ErrorNodeImpl(badToken);addAnyChild(t);t.setParent;return t;}
public LatvianStemFilterFactory(IDictionary<string,string> args) : base(args) { if (args.Count > 0) { throw new ArgumentException("Unknown parameters: " + args); } }
EventSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory TokenFilterFactory(string name, java.util.Map<string, string> args){return loader.newInstance(name, args);}
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { }
GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request){request = beforeClientExecution(request); return executeGetThreatIntelSet;}
public virtual RevFilter RevFilter(){ return new Binary(a.Clone(), b.Clone()); }
bool (object o) { return; }
bool () { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request=beforeClientExecution;return executeUpdateContributorInsights(request);}
void  () { records.Remove(fileShare); records.Remove(writeProtect); writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
RequestSpotInstancesResult() { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public byte[] GetObjectData(){return findObjectRecord.GetObjectData();}
GetContactAttributesResult(GetContactAttributesRequest request){request = beforeClientExecution; return executeGetContactAttributes(request);}
public override string ToString(){return getKey() + ": " + getValue(); }
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request){request=BeforeClientExecution(request);return ExecuteListTextTranslationJobs(request);}
public GetContactMethodsResult GetContactMethods(){request = beforeClientExecution(request);return executeGetContactMethods(request);}
public static ( ) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return ( )fd.GetIndex(); }
public DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request=BeforeClientExecution;return ExecuteDescribeAnomalyDetectors(request);}
public static string (string message, ObjectId changeId){ return insertId; }
long GetObjectSize(AnyObjectId objectId,int typeHint){long sz=db.GetObjectSize(objectId,typeHint);if(sz<0){if(typeHint==OBJ_ANY)throw new MissingObjectException(objectId.Copy(),JGitText.Get().UnknownObjectType2);throw new MissingObjectException(objectId.Copy(),typeHint);}return sz;}
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request){request = BeforeClientExecution(request);return ExecuteImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){request=BeforeClientExecution(request);return ExecutePutLifecycleEventHookExecutionStatus(request);}
NumberPtg(){In.ReadDouble();}
private GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){request=BeforeClientExecution(request);return ExecuteGetFieldLevelEncryptionConfig(request);}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request,options);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options=new InvokeOptions();options.RequestMarshaller=ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller=ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request,options);}
DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request){request=BeforeClientExecution(request);return ExecuteDeleteAlarm(request);}
public virtual TokenStream TokenStream() { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[16]; }
public bool Remove(object obj){lock(mutex){return c.Remove(obj);}}
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request){request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
public virtual string (){ return ; }
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){ SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(object options) { ; }
public UnbufferedCharStream(int bufferSize){ data = new char[bufferSize]; }
GetOperationsResult GetOperations(GetOperationsRequest request){request=beforeClientExecution(request);return executeGetOperations;}
public virtual void Write(byte[] b, int o){NB.EncodeInt32(b,o,this.w1);NB.EncodeInt32(b,o+4,this.w2);NB.EncodeInt32(b,o+8,this.w3);NB.EncodeInt32(b,o+12,this.w4);NB.EncodeInt32(b,o+16,this.w5);}
public WindowOneRecord(RecordInputStream inStream){field_1_h_hold=inStream.ReadShort();field_2_v_hold=inStream.ReadShort();field_3_width=inStream.ReadShort();field_4_height=inStream.ReadShort();field_5_options=inStream.ReadShort();field_6_active_sheet=inStream.ReadShort();field_7_first_visible_tab=inStream.ReadShort();field_8_num_selected_tabs=inStream.ReadShort();field_9_tab_width_ratio=inStream.ReadShort();}
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options=new InvokeOptions();options.RequestMarshaller=StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller=StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request,options);}
public void Close() { if (isOpen) { isOpen = false; try { Dump(); } finally { try { channel.Truncate(fileLength); } finally { try { channel.Close(); } finally { fos.Close(); } } } } }
public virtual DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeMatchmakingRuleSets(request);}
public string String(int wordId, char[] surface, int off, int len) { }
String() { }
public static double Calc(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
public DescribeResizeResult DescribeResize(DescribeResizeRequest request){request=BeforeClientExecution(request);return ExecuteDescribeResize(request);}
bool () { return passedThroughNonGreedyDecision; }
() => end
public void Walk(CellHandler handler){firstRow=range.GetFirstRow();lastRow=range.GetLastRow();firstColumn=range.GetFirstColumn();lastColumn=range.GetLastColumn();width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();Row currentRow=null;Cell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;++ctx.rowNumber){currentRow=sheet.GetRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;++ctx.colNumber){currentCell=currentRow.GetCell(ctx.colNumber);if(currentCell==null){continue;}if(IsEmpty(currentCell)&&!traverseEmptyCells){continue;}rowSize=ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber,firstRow),width);ctx.ordinalNumber=ArithmeticUtils.AddAndCheck(rowSize,ctx.colNumber-firstColumn+1);handler.OnCell(currentCell,ctx);}}}
() { }
public int CompareTo(ScoreTerm other){return this.boost==other.boost?other.bytes.Get().CompareTo(this.bytes.Get()):this.boost.CompareTo(other.boost);}
private static int Normalize(char[] s, int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;goto case KEHEH;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=Delete(s,i,len);i--;break;default:break;}}return len;}
public void SomeMethod(LittleEndianOutput out){ out.WriteShort; }
public DiagnosticErrorListener(){this.exactOnly=exactOnly;}
public KeySchemaElement(string attributeName, KeyType keyType){ this.setAttributeName(attributeName); this.setKeyType(keyType.ToString()); }
GetAssignmentResult(){request = beforeClientExecution(request);return executeGetAssignment(request);}
bool (AnyObjectId id) { return findOffset(id) != ; }
public GroupingSearch GroupingSearch(bool allGroups){this.allGroups = allGroups;return this;}
public void SetMultiValued(string dimName, bool v){lock(this){DimConfig ft=m_fieldTypes.ContainsKey(dimName)?m_fieldTypes[dimName]:null;if(ft==null){ft=new DimConfig();m_fieldTypes[dimName]=ft;}ft.multiValued=v;}}
{ IEnumerator<char> i = cells.Keys.GetEnumerator(); size = 0; while (i.MoveNext()) { char c = i.Current; Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResult DeleteVoiceConnector(DeleteVoiceConnectorRequest request){request=beforeClientExecution;return ExecuteDeleteVoiceConnector(request);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request,options);}
