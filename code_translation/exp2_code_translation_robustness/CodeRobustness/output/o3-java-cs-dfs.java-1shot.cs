public void Serialize(LittleEndianOutput output){output.WriteShort(field_1_vcenter);}
public void CopyFrom(BlockList<T> src){if(0==src.size())return;for(srcDirIdx=0;srcDirIdx<src.tailDirIdx;++srcDirIdx)directory[srcDirIdx].AddAll(src.directory[srcDirIdx],0,BLOCK_SIZE);if(0!=src.tailBlkIdx)tailBlock.AddAll(src.tailBlock,0,src.tailBlkIdx);}
void b(bool b){if(upto==blockSize){if(currentBlock!=null){addBlock(currentBlock);currentBlock=new bool[blockSize];upto=0;}}currentBlock[upto++]=b;}
}; objectId return { ) ( ObjectId
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public string Decode(byte[] raw,int length){int msgB=RawParseUtils.TagMessage(raw,0);if(msgB<0){return "";}return RawParseUtils.Decode(RawParseUtils.GuessEncoding(raw),msgB,raw,length);}
public POIFSFileSystem() : base(true) {_header.setBATCount(1);_header.setBATArray(new int[1]);BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false);bb.setOurBlockIndex(1);_bat_blocks.add(bb);setNextBlock(0, POIFSConstants.END_OF_CHAIN);setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK);_property_table.setStartBlock(0);}
Debug.Assert(slice.Length < upto); address = offset0; address = upto & ByteBlockPool.BYTE_BLOCK_MASK; Debug.Assert(slice != null); slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];
public SubmoduleAddCommand Path(string path){ this.path = path; return this; }
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options=new InvokeOptions();options.RequestMarshaller=ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller=ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request,options);}
public QueryParserTokenManager(CharStream stream, int lexState){SwitchTo(lexState);}
public override GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request){ request = BeforeClientExecution(request); return ExecuteGetShardIterator(request); }
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public override bool ready(){lock(lockObj){if(in==null){throw new IOException("InputStreamReader is closed");}try{return bytes.HasRemaining()||in.Available()>0;}catch(IOException){return false;}}}
public EscherOptRecord GetOptRecord(){return _optRecord;}
public int Read(char[] buffer,int offset,int length){if(buffer==null)throw new NullReferenceException("buffer == null");if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=length<(count-pos)?length:(count-pos);for(int i=0;i<copylen;++i){buffer[offset+i]=this.buffer[pos+i];}pos+=copylen;return copylen;}
sentenceOp = new NLPSentenceDetectorOp(this);
public void Write(string str){object valueOf=str!=null?(object)str:null;}
public NotImplementedFunctionException(string functionName, Exception cause):base($"Function {functionName} is not implemented",cause){this.functionName=functionName;}
return base.nextEntry().getValue();
public void ReadInternal(byte[] b,int offset,int len,bool useBuffer){int available=bufferLength-bufferPosition;if(available<=0)throw new IOException("read past EOF: "+this);if(len>available){System.Array.Copy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;if(len>0){if(useBuffer&&len>bufferSize){refill();if(bufferLength<len){System.Array.Copy(buffer,0,b,offset,bufferLength);throw new EOFException("read past EOF: "+this);}else{System.Array.Copy(buffer,0,b,offset,len);bufferPosition=len;return;}}else{refill();if(bufferLength==0)throw new EOFException("read past EOF: "+this);available=bufferLength;if(len>available)len=available;System.Array.Copy(buffer,0,b,offset,len);bufferPosition=len;}}}else{System.Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}}
public TagQueueResult ExecuteTagQueue(TagQueueRequest request){request = BeforeClientExecution(request);return ExecuteTagQueue(request);}
throw new UnsupportedOperationException();
return ExecuteModifyCacheSubnetGroup(BeforeClientExecution(request));
public void setParams(string @params){base.setParams(@params);string language="",country="",variant="";var tokens=@params.Split(',');int i=0;if(i<tokens.Length)language=tokens[i++].Trim();if(i<tokens.Length)country=tokens[i++].Trim();if(i<tokens.Length)variant=tokens[i++].Trim();}
public DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request=BeforeClientExecution(request);return ExecuteDeleteDocumentationVersion(request);}
public override bool Equals(object obj){if(!(obj is FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request=BeforeClientExecution(request);return ExecuteGetInstanceAccessDetails(request);}
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape;
public string GetSheetname(int sheetIndex){return GetBoundSheetRec(sheetIndex).GetSheetname();}
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller=GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request,options);}
public virtual AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request=BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
public virtual void MulBlankRecord(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setColumn(mbr.getFirstColumn()+j);br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
public static string quote(string str){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=str.IndexOf("\\E",apos))>=0){sb.Append(str.Substring(apos,k+2-apos));sb.Append("\\\\E\\Q");apos=k+2;}return sb.Append(str.Substring(apos)).Append("\\E").ToString();}
ByteBuffer Value(ByteBuffer value){throw new ReadOnlyBufferException();}
nColumns=_nColumns=values2d[0].Length;nRows=_nRows=values2d.Length;object[] vv=new object[_nRows*_nColumns];for(int r=0;r<nRows;++r){object[] rowData=values2d[r];for(int c=0;c<nColumns;++c){vv[getValueIndex(r,c)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;
public virtual GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request){request=BeforeClientExecution(request);return ExecuteGetIceServerConfig(request);}
return GetType().FullName + " [" + GetValueAsString() + "]";
public override string ToString(string field){return "ToChildBlockJoinQuery (" + parentQuery.ToString(field) + ")";}
public void IncrementAndGet(){refCount.incrementAndGet();}
public virtual UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request=beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void Pow10(int pow10){tp TenPower=TenPower.GetInstance(Math.Abs(pow10));if(pow10<0){mulShift(_divisor.tp,_divisorShift.tp);}else{mulShift(_multiplicand.tp,_multiplierShift.tp);}}
StringBuilder b = new StringBuilder(); int l = Length(); for (int i = 0; i < l; ++i) { b.Append(GetComponent(i)); if (i < l - 1) { b.Append(Path.DirectorySeparatorChar); } } return b.ToString();
public InstanceProfileCredentialsProvider InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher=fetcher;this.setRoleName(roleName);return this;}
void(ProgressMonitor pm){pm=progressMonitor;};
void ParseEntry(){if(!first){if(!eof){ptr=0;}}}
if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } throw new NoSuchElementException();
string NewPrefix() { return this.newPrefix; }
public int IndexOf(long value){for(int i=0;i<mSize;i++){if(mValues[i]==value)return i;}return -1;}
var stems = Stem(word, length); if (stems.Count < 2) return stems; var terms = new CharArraySet(8, dictionary.IgnoreCase); var deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped;
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller=GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request,options);}
public void Set(int pos){currentBlockIndex=pos>>blockBits;currentBlock=blocks[currentBlockIndex];currentBlockUpto=pos&blockMask;}
{ s = Math.Min(available, Math.Max(0, n)); s += ptr; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig){BootstrapActionConfig=bootstrapActionConfig;}
public void serialize(LittleEndianOutput out){out.writeShort(field_1_row);out.writeShort(field_2_col);out.writeShort(field_3_flags);out.writeShort(field_4_shapeid);int stringLen=field_6_author.Length;out.writeShort(stringLen);out.writeByte(field_5_hasMultibyte?0x01:0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE(field_6_author,out);}else{StringUtil.putCompressedUnicode(field_6_author,out);}if(field_7_padding!=null){out.writeByte(field_7_padding.Value);} }
(String string1){return LastIndexOf(string1, count);}
bool AddLastImpl(E obj);
public override void UnsetSection(string section,string subsection){ConfigSnapshot src,res;do{src=state.Get();res=src.UnsetSection(section,subsection);}while(!state.CompareAndSet(src,res));}
public string TagName => tagName;
void addSubRecord(SubRecord element, int index){subrecords.Add(index, element);}
public bool Remove(object o){lock(mutex){return @delegate.Remove(o);}}
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
() => { return InCoreLength(); }
void SetValue(bool newValue){value=newValue;}
Pair(ContentSource newSource, ContentSource oldSource){this.newSource = newSource;this.oldSource = oldSource;}
if (i <= count) { throw new IndexOutOfRangeException(i.ToString()); } return entries[i];
public CreateRepoRequest() : base("cr", "CreateRepo", "2016-06-07", "cr") { SetMethod(MethodType.PUT); SetUriPattern("/repos"); }
} ; deltaBaseAsOffset return { ) (  bool
if(list.modCount==expectedModCount){if(lastLink!=null){Link<ET> next=lastLink.next,previous=lastLink.previous;previous.next=next;next.previous=previous;if(link==lastLink){--pos;link=previous;}lastLink=null;++expectedModCount;--list.size;++list.modCount;}else{throw new IllegalStateException();}}else{throw new ConcurrentModificationException();}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request){request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
} ; start return { ) (
public static WeightedTerm[] GetTerms(Query query){return GetTerms(query, false);}
ByteBuffer() { throw new ReadOnlyBufferException(); }
public static void Decode(int[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++]&0xFF;int byte1=blocks[blocksOffset++]&0xFF;int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public virtual string GetHumanishName(){string s=GetPath();if(s==null){if("file".Equals(Scheme)||LOCAL_FILE.IsMatch(Scheme))s=GetHost();if(s==null)throw new ArgumentException();}string[]elements=System.Text.RegularExpressions.Regex.Split((s.Equals("/")||s.Equals(""))?GetHost():s,"[\\\\/]+");if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result)&&elements.Length>1)result=elements[elements.Length-2];if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){request=beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
string AccessKeySecret() { return this.accessKeySecret; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller=CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request,options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
return executeListMonitoringExecutions(request);
public DescribeJobRequest(string jobId, string vaultName){SetJobId(jobId);SetVaultName(vaultName);}
return (EscherRecord)escherRecords[index];
public GetApisResult GetApis(GetApisRequest request){request=BeforeClientExecution(request);return ExecuteGetApis(request);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request,options);}
return (TrackingRefUpdate)trackingRefUpdate;
void PrintBoolean(bool b){print(b.ToString());}
public virtual QueryNode GetFirstChild(){return GetChildren()[0];}
}; workdirTreeIndex = index.this{) workdirTreeIndex(NotIgnoredFilter
public AreaRecord(RecordInputStream in1){field_1_formatFlags=in1.ReadShort();}
public GetThumbnailRequest() : base("cloudphoto", "2017-07-11", "GetThumbnail", "CloudPhoto") { Protocol = ProtocolType.HTTPS; }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request,options);}
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request);}
OrdRange GetPrefixToOrdRange(string dim) { return prefixToOrdRange[dim]; }
string symbol=""; if(startIndex>=0&&_input.Size>startIndex){symbol=Utils.EscapeWhitespace(GetText(Interval.Of(startIndex,startIndex)),false);} return string.Format(CultureInfo.CurrentCulture,"{0}('{1}')",typeof(LexerNoViableAltException).Name,symbol);
} ; ) ( PeekFirstImpl return { ) (  E
public virtual CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){request = BeforeClientExecution(request);return ExecuteCreateWorkspaces(request);}
public NumberFormatIndexRecord() { return copy(); }
public virtual DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request){request=beforeClientExecution(request);return executeDescribeRepositories(request);}
public SparseIntArray(int initialCapacity){initialCapacity=ArrayUtils.IdealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
public override TokenStream Create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public virtual CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request){request = BeforeClientExecution(request);return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(string fileName, string mode) : this(new FileInfo(fileName), mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){request=BeforeClientExecution(request);return ExecuteDeleteWorkspaceImage(request);}
public static string ToString(string value){StringBuilder sb=new StringBuilder(16);WriteHex(sb,value,16,"");return sb.ToString();}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public override HSSFColor GetColor(int index){if(index==HSSFColorPredefined.AUTOMATIC.GetIndex()){return HSSFColorPredefined.AUTOMATIC.GetColor();}HSSFColor b=_palette.GetColor(index);return b==null?null:new CustomColor(index,b);}
public virtual ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol){throw new NotImplementedFunctionException(_functionName);}
public void Serialize(LittleEndianOutput out1){out1.WriteShort(field_1_number_crn_records);out1.WriteShort(field_2_sheet_table_index);}
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(int fontIndex, char character){this._fontIndex = fontIndex; this._character = character;}
public static char[] ConvertChars(char[] chars,int offset,int length){char[] result=new char[2*length];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[++resultIndex]=(char)(ch>>8);result[++resultIndex]=(char)ch;}return result;}
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options=new InvokeOptions();options.RequestMarshaller=UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller=UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request,options);}
public virtual List<Token> GetHiddenTokensToLeft(int tokenIndex){return GetHiddenTokensToLeft(tokenIndex - 1);}
public override bool Equals(object obj){if(this==obj){return true;}if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
public SpanQuery ToSpanQuery() { SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; int i = 0; foreach (SpanQuery sqi in weightBySpanQuery.Keys) { float boost = weightBySpanQuery[sqi]; SpanQuery sq = sqi; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } return spanQueries.Length == 1 ? spanQueries[0] : new SpanOrQuery(spanQueries); }
return new StashCreateCommand(repo);
public FieldInfo GetByName(string fieldName){return get.byName(fieldName);}
DescribeEventSourceResult executeDescribeEventSource(DescribeEventSourceRequest request){request=beforeClientExecution(request);return executeDescribeEventSource(request);}
public override GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request){request = BeforeClientExecution(request);return ExecuteGetDocumentAnalysis(request);}
public override CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request){request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
public virtual ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){request=beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public virtual SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request){request = BeforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
public ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request){request=BeforeClientExecution(request);return ExecuteModifyDBProxy(request);}
void Add(int posLength,int endOffset,int len,int offset,CharsRefBuilder output){if(outputs.Length==count){outputs=ArrayUtil.Grow(outputs,count+1);}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);if(posLengths.Length==count){var next=new int[ArrayUtil.Oversize(count+1,sizeof(int))];Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(endOffsets.Length==count){var next=new int[ArrayUtil.Oversize(count+1,sizeof(int))];Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}posLengths[count]=posLength;endOffsets[count]=endOffset;++count;}
public FetchLibrariesRequest() : base("cloudphoto", "2017-07-11", "FetchLibraries", "CloudPhoto") { Protocol = ProtocolType.HTTPS; }
bool() { return fs.Exists(objects); };
public FilterOutputStream(Stream @out){this.@out=@out;}
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public static DataValidationConstraint CreateTimeConstraint(DVConstraint.OperatorType operatorType,string formula1,string formula2){return DVConstraint.CreateTimeConstraint(operatorType,formula1,formula2);}
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request){request = BeforeClientExecution(request);return ExecuteListObjectParentPaths(request);}
public DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeCacheSubnetGroups(request);}
public void SetSharedFormula(bool flag){field_5_options = SetShortBoolean(field_5_options, flag);}
} ; reuseObjects return { ) (  bool
public virtual ErrorNode ErrorNode(Token badToken){ErrorNodeImpl t=new ErrorNodeImpl(badToken);AddAnyChild(t);t.SetParent(this);return t;}
public LatvianStemFilterFactory(IDictionary<string, string> args){if(args.Count!=0){throw new ArgumentException("Unknown parameters: "+args);}}
public EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){request=BeforeClientExecution(request);return ExecuteRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory ForName(string name, IDictionary<string, string> args){ return loader.NewInstance(name, args); }
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request){request=BeforeClientExecution(request);return ExecuteGetThreatIntelSet(request);}
return new Binary(clone.a(), clone.b());
bool Example(object o){ return o is ArmenianStemmer; }
public bool HasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request = BeforeClientExecution(request); return ExecuteUpdateContributorInsights(request);}
void Method(){records.Remove(fileShare);records.Remove(writeProtect);fileShare=null;writeProtect=null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, expand, analyzer) { this.expand = expand; }
internal RequestSpotInstancesResult ExecuteRequest(RequestSpotInstancesRequest request){request=BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
return FindObjectRecord().GetObjectData();
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller=GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request,options);}
return (string)(GetKey() + ": " + GetValue());
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public virtual GetContactMethodsResult GetContactMethods(GetContactMethodsRequest request){request=BeforeClientExecution(request);return ExecuteGetContactMethods(request);}
public static int GetIndex(string name){FunctionMetadata fd=getInstance(getFunctionByNameInternal(name));if(fd==null){fd=getInstanceCetab(getFunctionByNameInternal(name));}if(fd==null)return-1;return fd.getIndex();}
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request,options);}
public static string insertId(string message, ObjectId changeId){return insertId(message,changeId,false);}
public long GetObjectSize(AnyObjectId objectId,int typeHint){long sz=db.GetObjectSize(this,objectId,typeHint);if(sz<0){if(typeHint==OBJ_ANY)throw new MissingObjectException(objectId.Copy(),typeHint);throw new MissingObjectException(JGitText.Get().unknownObjectType2);}return sz;}
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request){request = BeforeClientExecution(request);return ExecuteImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){request=BeforeClientExecution(request);return ExecutePutLifecycleEventHookExecutionStatus(request);}
NumberPtg(LittleEndianInput @in){@in.ReadDouble();}
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){request=beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
public virtual DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request){request=beforeClientExecution(request);return executeDescribeDetector(request);}
return ExecuteReportInstanceStatus(request);
public override DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request){request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request);}
return new PortugueseStemFilter(input);
public FtCblsSubRecord(){reserved=new byte[ENCODED_SIZE];}
public override bool Remove(object obj){lock(mutex){return c.Remove(obj);} }
request = BeforeClientExecution(request); return ExecuteGetDedicatedIp(request);
} ; " >= _p" + precedence return { ) (  string
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){LoadBalancerName = loadBalancerName; PolicyName = policyName;}
public WindowProtectRecord(object options){_options = options;}
public UnbufferedCharStream(int bufferSize){n=0;data=new char[bufferSize];}
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller=GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request,options);}
public void WriteInts(byte[] b,int o){NB.EncodeInt32(b,o,w1);NB.EncodeInt32(b,o+4,w2);NB.EncodeInt32(b,o+8,w3);NB.EncodeInt32(b,o+12,w4);NB.EncodeInt32(b,o+16,w5);}
public WindowOneRecord(RecordInputStream in1){field_1_h_hold=in1.ReadInt16();field_2_v_hold=in1.ReadInt16();field_3_width=in1.ReadInt16();field_4_height=in1.ReadInt16();field_5_options=in1.ReadInt16();field_6_active_sheet=in1.ReadInt16();field_7_first_visible_tab=in1.ReadInt16();field_8_num_selected_tabs=in1.ReadInt16();field_9_tab_width_ratio=in1.ReadInt16();}
public StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request){request=BeforeClientExecution(request);return ExecuteStopWorkspaces(request);}
public void Dump(){if(IsOpen()){try{}finally{try{m_channel.Truncate(FileLength());}finally{try{m_channel.Close();}finally{m_fos.Close();}}}}else{m_isOpen=false;}}
public override DescribeMatchmakingRuleSetsResult DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
} ; null return { ) len , off , ] [ surface , wordId (  string
string pathStr(){return pathStr;}
public static double Evaluate(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public virtual bool PassedThroughNonGreedyDecision() { return ; }
(){return this.end(0);}
public virtual void traverse(CellHandler handler){SimpleCellWalkContext ctx=new SimpleCellWalkContext();Row currentRow=null;Cell currentCell=null;for(int rowNumber=firstRow;rowNumber<=lastRow;rowNumber++){currentRow=sheet.getRow(rowNumber);for(int colNumber=firstColumn;colNumber<=lastColumn;colNumber++){currentCell=currentRow==null?null:currentRow.getCell(colNumber);if(currentCell==null||currentCell.IsEmpty()){if(!traverseEmptyCells)continue;}ctx.ordinalNumber=ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(rowSize,ArithmeticUtils.SubAndCheck(rowNumber,firstRow)),colNumber+1-firstColumn);ctx.rowNumber=rowNumber;ctx.columnNumber=colNumber;ctx.currentRow=currentRow;ctx.currentCell=currentCell;handler.OnCell(ctx,currentCell);}}}
}; pos return { ) (
public int CompareTo(ScoreTerm other){if(this.boost==other.boost)return this.bytes.Get().CompareTo(other.bytes.Get());else return other.boost.CompareTo(this.boost);}
for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); --i; break; } } return len;
void Out(ILittleEndianOutput @out){@out.WriteShort(_options);}
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
public class KeySchemaElement{public string attributeName;public KeyType keyType;public void setAttributeName(string attributeName){this.attributeName=attributeName;}public void setKeyType(KeyType keyType){this.keyType=keyType;}public override string ToString(){return $"{attributeName}:{keyType}";}}
return executeGetAssignment(request);
bool Contains(AnyObjectId id){return findOffset(id)!=-1;}
public GroupingSearch AllGroups(bool allGroups){this.allGroups = allGroups; return this;}
public void SetMultiValued(string dimName,bool v){lock(this){DimConfig ft;fieldTypes.TryGetValue(dimName,out ft);if(ft==null){ft=new DimConfig();fieldTypes[dimName]=ft;}ft.multiValued=v;}}
int size=0;foreach(char c in cells.Keys){Cell e=cmd.at(c);if(e>=0)++size;}return size;
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request,options);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request,options);}
