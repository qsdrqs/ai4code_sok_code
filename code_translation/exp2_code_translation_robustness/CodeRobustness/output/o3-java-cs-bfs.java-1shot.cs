public virtual void Serialize(LittleEndianOutput out1){out1.WriteShort(field_1_vcenter);}
for (srcDirIdx = 0; srcDirIdx < tailDirIdx; ++srcDirIdx) { if (src.directory[srcDirIdx] != null) { BlockList<T> addAll = src.directory[srcDirIdx].addAll(src.tailBlock.src[tailBlkIdx].src, 0, BLOCK_SIZE, src.size); if (addAll != null && addAll.Count != 0) { return; } } }
public void AddBlock(byte b){if(b==b){currentBlock[upto++]=b;if(upto==blockSize){upto=0;currentBlock=new byte[blockSize];if(null!=currentBlock)AddBlock(currentBlock);}}}
public virtual ObjectId ObjectId(){return objectId;}
public DeleteDomainEntryResult DeleteDomainEntry(DeleteDomainEntryRequest request){request = BeforeClientExecution(request);return ExecuteDeleteDomainEntry(request);}
return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public string Decode(byte[] raw){int msgB=RawParseUtils.TagMessage(raw,0,raw.Length);if(msgB<0)return"";return RawParseUtils.GuessEncoding(raw,0,raw.Length);}
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); SetNextBlock(0, POIFSConstants.FAT_SECTOR_BLOCK); SetNextBlock(1, POIFSConstants.END_OF_CHAIN); bb.SetNextBlock(POIFSConstants.END_OF_CHAIN); _property_table.SetStartBlock(POIFSConstants.END_OF_CHAIN); _bat_blocks.Add(bb);
Debug.Assert(address < upto); address = offset0 = upto; Debug.Assert(slice != null); slice = slice.Length & BYTE_BLOCK_MASK; address = pool.buffers[address >> BYTE_BLOCK_SHIFT];
public SubmoduleAddCommand Path(string path){this.path=path;return this;}
public ListIngestionsResult ExecuteListIngestions(ListIngestionsRequest request){request=BeforeClientExecution(request);return ExecuteListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
lock(lockObj){ try{ if(in==null) throw new IOException("InputStreamReader is closed"); if(bytes.HasRemaining()||in.Available()>0) return true; return false; } catch(IOException){ throw; } }
return (EscherOptRecord)_optRecord;
public virtual int Read(char[] buffer,int offset,int length){if(buffer==null)throw new NullReferenceException("buffer == null");if(offset<0||length<0||offset+length>buffer.Length)throw new IndexOutOfRangeException();if(length==0)return 0;int copylen=count-pos;if(copylen<=0)return-1;if(length<copylen)copylen=length;for(int i=0;i<copylen;++i){buffer[i+offset]=this.CharAt(i+pos);}pos+=copylen;return copylen;}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp){this.sentenceOp=sentenceOp;}
void write(string str){(object)(str!=null?str:null);}
public NotImplementedFunctionException(string functionName, Exception cause) : base(cause){this.functionName = functionName;}
}{return (V)base.NextEntry().getValue();}
public void ReadBytes(byte[] b,int offset,int len,bool useBuffer){if(len<0){throw new ArgumentException("len must be >= 0 (got "+len+")");}long available=bufferLength-bufferPosition;if(useBuffer&&len<=available){if(len==0){return;}Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;return;}if(useBuffer&&available>0){Array.Copy(buffer,bufferPosition,b,offset,(int)available);offset+=(int)available;len-=(int)available;bufferPosition+=available;}long after=bufferStart+bufferPosition+len;if(after>Length()){throw new EndOfStreamException("read past EOF: "+this);}if(useBuffer&&len<bufferSize){Refill();if(bufferLength<len){Array.Copy(buffer,bufferPosition,b,offset,bufferLength);bufferPosition+=bufferLength;throw new EndOfStreamException("read past EOF: "+this);}Array.Copy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;return;}ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}
public TagQueueResult ExecuteTagQueue(TagQueueRequest request){request=BeforeClientExecution(request);return request;}
throw new System.NotSupportedException();
public CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){request = BeforeClientExecution(request);return ExecuteModifyCacheSubnetGroup(request);}
public void SetParams(string @params) { variant = ""; var st = new StringTokenizer(@params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); base.SetParams(); }
public virtual DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request=BeforeClientExecution(request);return ExecuteDeleteDocumentationVersion(request);}
public override bool Equals(object obj){if(this==obj)return true;if(!(obj is FacetLabel))return false;FacetLabel other=(FacetLabel)obj;if(length!=other.length)return false;for(int i=length-1;i>=0;--i){if(!components[i].Equals(other.components[i]))return false;}return true;}
public override GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request=beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
public HSSFPolygon OnCreate(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shape.SetParent(this);shape.SetAnchor(anchor);shapes.Add(shape);return shape;}
string GetSheetname(int sheetIndex){ return getBoundSheetRec(sheetIndex).getSheetname(); }
public virtual GetDashboardResult GetDashboard(GetDashboardRequest request){request=BeforeClientExecution(request);return ExecuteGetDashboard(request);}
AssociateSigninDelegateGroupsWithAccountRequest request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request);
public void Process(MulBlankRecord mbr){for(int j=0;j<mbr.GetNumColumns();++j){BlankRecord br=new BlankRecord(mbr.GetRow(),mbr.GetXFAt(j+mbr.GetFirstColumn()));br.SetColumn(j);br.SetRow(0);br.SetXFIndex(InsertCell(br));}}
public static string toString(string str){throw new System.NotImplementedException();}
throw new ReadOnlyBufferException();
_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;object[] vv=_arrayValues;_nRows=nRows;_nColumns=nColumns;object[][] values2d=new object[nRows][];for(int r=0;r<nRows;++r){object[] rowData=new object[nColumns];values2d[r]=rowData;for(int c=0;c<nColumns;++c){rowData[c]=vv[getValueIndex(r,c)];}}
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller=GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request,options);}
public override string ToString(){return GetType().FullName+" ["+GetValueAsString()+"]";}
public string ToString(string field){return parentQuery.ToString(field)+"ToChildBlockJoinQuery("+")";}
public void IncrementAndGet(){refCount.IncrementAndGet();}
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request=BeforeClientExecution(request);return ExecuteUpdateConfigurationSetSendingEnabled(request);}
return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
TenPower pow10 = TenPower.GetInstance(Math.Abs(tp._divisor), tp._divisorShift, tp._multiplicand, tp._multiplierShift); if (0 < pow10) { tp = MulShift(tp, pow10); } else { tp = MulShift(tp, pow10); }
{ var b = new StringBuilder(length); for (int i = 0; i < l; ++i) { if (i > 0) b.Append(Path.DirectorySeparatorChar); b.Append(getComponent(i, l - 1)); } return b.ToString(); }
public InstanceProfileCredentialsProvider WithRoleName(string roleName){this.fetcher=new ECSMetadataServiceCredentialsFetcher();this.fetcher.SetRoleName(roleName);return this;}
ProgressMonitor pm = progressMonitor;
} { ) (  void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
if (iterator.PreviousIndex() >= start) return iterator.Previous(); else throw new NoSuchElementException(E);
public string NewPrefix(){return this.newPrefix;}
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1;
private IList<CharsRef> Dedup(IList<CharsRef> stems){IList<CharsRef> deduped=new List<CharsRef>();CharArraySet terms=new CharArraySet(8*stems.Count,ignoreCase);foreach(CharsRef s in stems){CharsRef stem=new CharsRef(s.Chars,0,s.Length);if(terms.Add(stem)){deduped.Add(s);}}return deduped;}
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller=GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request,options);}
} { ) (  void ; ; ; pos = currentBlockUpto = currentBlock = currentBlockIndex ) ( ] currentBlockIndex [ blocks ) ( ) ( ) ( blockMask & pos blockBits >> pos

public void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig){this.bootstrapActionConfig=bootstrapActionConfig;}
public void Serialize(LittleEndianOutput out){out.WriteByte(field_1_row);out.WriteByte(field_2_col);out.WriteShort(field_3_flags);out.WriteShort(field_4_shapeid);out.WriteByte((byte)(field_5_hasMultibyte?0x01:0x00));StringUtil.PutUnicodeLE(field_6_author.Length,out);if(field_5_hasMultibyte)StringUtil.PutUnicodeLE(field_6_author,out);else StringUtil.PutCompressedUnicode(field_6_author,out);if(field_7_padding!=null)out.WriteShort(field_7_padding.Value);}
public int lastIndexOf(string @string,int count){return @string.LastIndexOf(count);}
public virtual object AddLastImpl(object e, bool flag){return e;}
void UnsetSection(string section,string subsection){ConfigSnapshot src,res;do{src=state.Get();res=src.UnsetSection(section,subsection);}while(!state.CompareAndSet(src,res));}
public string TagName() { return tagName; }
public void AddSubRecord(int index, SubRecord element){subrecords.Insert(index, element);}
public bool Remove(object o){lock(mutex){return @delegate.Remove(o);}}
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
return inCoreLength();
public void SetNewValue(bool value){ newValue = value; }
var pair = new Pair<ContentSource, ContentSource>(this.oldSource = oldSource, this.newSource = newSource);
if (i < count) return entries[i]; throw new IndexOutOfRangeException(i.ToString());
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
bool DeltaBaseAsOffset(){return deltaBaseAsOffset;}
if (list.modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new InvalidOperationException(); Link previous = lastLink.previous, next = lastLink.next; if (previous == null) list.firstLink = next; else previous.next = next; if (next == null) list.lastLink = previous; else next.previous = previous; if (next == lastLink) next = lastLink.next; else --pos; lastLink = null; ++expectedModCount;
public virtual MergeShardsResult MergeShards(MergeShardsRequest request){request=BeforeClientExecution(request);return ExecuteMergeShards(request);}
AllocateHostedConnectionRequest request = BeforeClientExecution(request); return ExecuteAllocateHostedConnection(request);
} { ) (  ; start return
public static WeightedTerm[] getTerms(Query query){return getTerms(query,false);}
throw new ReadOnlyBufferException();
public static void Encode(byte[] values,int valuesOffset,byte[] blocks,int blocksOffset,int iterations){for(int i=0;i<iterations;++i){int byte0=values[valuesOffset++]&0xFF;int byte1=values[valuesOffset++]&0xFF;int byte2=values[valuesOffset++]&0xFF;blocks[blocksOffset++]=(byte)(byte0>>2);blocks[blocksOffset++]=(byte)(((byte0<<4)&0x3F)|(byte1>>4));blocks[blocksOffset++]=(byte)(((byte1<<2)&0x3F)|(byte2>>6));blocks[blocksOffset++]=(byte)(byte2&0x3F);} }
} {  throws  string ; result return else if ; string if ; if ; if if ; string ) ( if ; ) ( = result ; throw ) ( = elseelements ; ) ( string ; throw ) ( ; ) ( = s ; ) ( = result Equals . ] [ elements ArgumentException new 0 == Split . s = elements || ] [ ArgumentException new null == s = s || GetPath = result EndsWith . result ] [ elements ) result ( Constants.DOT_GIT 1 - ) ( elements.Length ) "/+" ( s.Split Regex.IsMatch . Equals . "file" ) ( GetHost Equals . "" Equals . "/" ) ( result.Substring ) ( elements.Length - 2 ) ( ) ( matcher . LOCAL_FILE ) scheme ( ) ( ) s ( ) s ( ) , 0 ( Constants.DOT_GIT_EXT elements.Length "/]" + ) s ( - + "[\\" result.Length Path.DirectorySeparatorChar ) ( Constants.DOT_GIT_EXT ) (
public DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){request=BeforeClientExecution(request);return ExecuteDescribeNotebookInstanceLifecycleConfig(request);}
public virtual string GetAccessKeySecret(){return this.accessKeySecret;}
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller=CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request,options);}
public override DescribeVoicesResult describeVoices(DescribeVoicesRequest request){request=beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request){request = BeforeClientExecution(request);return ExecuteListMonitoringExecutions(request);}
public DescribeJobRequest(string vaultName, string jobId){setVaultName(vaultName);setJobId(jobId);}
public EscherRecord Get(int index) { return escherRecords[index]; }
public virtual GetApisResponse GetApis(GetApisRequest request){request=BeforeClientExecution(request);return ExecuteGetApis(request);}
public DeleteSmsChannelResult DeleteSmsChannel(DeleteSmsChannelRequest request){request=BeforeClientExecution(request);return ExecuteDeleteSmsChannel(request);}
return trackingRefUpdate;
void Method(){bool b;Console.Write(b.ToString());}
{ return (QueryNode)getChildren()[0]; }
}{ NotIgnoredFilter; )( workdirTreeIndex = this.index.workdirTreeIndex
field_1_formatFlags = in.ReadInt16();
public GetThumbnailRequest() : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { Protocol = ProtocolType.HTTPS; }
public override DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeTransitGatewayVpcAttachments(request);}
request = BeforeClientExecution(request); return ExecutePutVoiceConnectorStreamingConfiguration(request);
} { ) (  OrdRange ; return dim string get . prefixToOrdRange ) dim (
} { ) (  string ; return if ; string Format . string } { ) ( "" = symbol ) symbol , , "%s('%s')" , ( ; ; && getSimpleName . getDefault . Locale = symbol = symbol < startIndex 0 >= startIndex ) ( class . LexerNoViableAltException ) ( escapeWhitespace . Utils getText . size . ) false , symbol ( ) ( getInputStream ) ( getInputStream of . Interval ) ( ) ( ) startIndex , startIndex (
return (E)PeekFirstImpl();
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options=new InvokeOptions();options.RequestMarshaller=CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller=CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request,options);}
public NumberFormatIndexRecord Copy(){return copy;}
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request,options);}
public SparseIntArray(int initialCapacity){if(initialCapacity==0){mKeys=EmptyArray.INT;mValues=EmptyArray.INT;}else{initialCapacity=ArrayUtils.idealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];}mSize=0;}
return new HyphenatedWordsFilter(input);
{ request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public RandomAccessFile(string fileName, string mode) { }
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){request=BeforeClientExecution(request);return ExecuteDeleteWorkspaceImage(request);}
public static string ToString(System.Text.StringBuilder value){System.Text.StringBuilder sb=new System.Text.StringBuilder(16);WriteHex(sb,value,16,"");return sb.ToString();}
request = BeforeClientExecution(request); return ExecuteUpdateDistribution(request);
public virtual HSSFColor getColor(short index){if(index==HSSFColorPredefined.AUTOMATIC.getIndex())return null;HSSFColor result=HSSFColor.getIndexHash().get(index);if(result==null){byte[] color=_palette.getColor(index);if(color!=null)return new CustomColor(color);}return result;}
throw new NotImplementedFunctionException(_functionName, operands, srcRow, srcCol);
out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index);
public virtual DescribeDBEngineVersionsResult DescribeDBEngineVersions(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
{ FormatRun(); this._character.fontIndex = character.fontIndex; }
public static byte[] Something(char[] chars,int offset,int length){byte[] result=new byte[length*2];int resultIndex=0;int end=offset+length;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)ch;result[resultIndex++]=(byte)(ch>>8);}return result;}
request = beforeClientExecution(request); return executeUploadArchive(request);
return (List<Token>)GetHiddenTokensToLeft(tokenIndex - 1);
public override bool Equals(object obj){if(this==obj)return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;if(compiled==null){if(other.compiled!=null)return false;}else if(!compiled.Equals(other.compiled))return false;return true;}
public virtual SpanQuery Rewrite(){SpanQuery[] sq=new SpanQuery[spanQueries.Length];int i=0;var sqi=((IEnumerable<SpanQuery>)spanQueries).GetEnumerator();while(sqi.MoveNext()){SpanQuery s=sqi.Current;float boost=weightBySpanQuery[s];if(boost!=1f)s=new SpanBoostQuery(s,boost);sq[i++]=s;}return sq.Length==1?sq[0]:new SpanOrQuery(sq);}
return new StashCreateCommand(repo);
public FieldInfo GetFieldInfoByName(string fieldName){return FieldInfo.ByName(fieldName);}
public override DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request){request=beforeClientExecution(request);return executeDescribeEventSource(request);}
{ GetDocumentAnalysisRequest request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
public CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request){request = BeforeClientExecution(request);return ExecuteCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResult ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){request=BeforeClientExecution(request);return ExecuteModifyLoadBalancerAttributes(request);}
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=SetInstanceProtectionRequestMarshaller.Instance;options.ResponseUnmarshaller=SetInstanceProtectionResponseUnmarshaller.Instance;return Invoke<SetInstanceProtectionResponse>(request,options);}
ModifyDBProxyRequest request = beforeClientExecution(request); ModifyDBProxyResult result = executeModifyDBProxy(request); return result;
public virtual void Add(int posLength,int endOffset,char[] output,int offset,int len){if(count==posLengths.Length){int next=ArrayUtil.Oversize(count+1,sizeof(int));int[] newPosLengths=new int[next];System.Array.Copy(posLengths,0,newPosLengths,0,count);posLengths=newPosLengths;int[] newEndOffsets=new int[next];System.Array.Copy(endOffsets,0,newEndOffsets,0,count);endOffsets=newEndOffsets;CharsRefBuilder[] newOutputs=new CharsRefBuilder[next];System.Array.Copy(outputs,0,newOutputs,0,count);outputs=newOutputs;}posLengths[count]=posLength;endOffsets[count]=endOffset;outputs[count]=new CharsRefBuilder();outputs[count].CopyChars(output,offset,len);++count;}
public FetchLibrariesRequest():base("cloudphoto","2017-07-11","FetchLibraries","cloudphoto"){setProtocol(ProtocolType.HTTPS);}
} { ) (  bool ; return exists . fs ) objects (
_out = @out;
public ScaleClusterRequest() : base("/clusters/[ClusterId]", "csk", "ScaleCluster", "2015-12-15", "CS") { SetMethod(MethodType.PUT); }
public static DVConstraint createTimeConstraint(int operatorType,string formula1,string formula2){return new DVConstraint(DataValidationConstraint.ValidationType.TIME,operatorType,formula1,formula2);}
public override com.amazonaws.services.clouddirectory.model.ListObjectParentPathsResult listObjectParentPaths(com.amazonaws.services.clouddirectory.model.ListObjectParentPathsRequest request){request=beforeClientExecution(request);return executeListObjectParentPaths(request);}
request = BeforeClientExecution(request); return ExecuteDescribeCacheSubnetGroups(request);
public void setSharedFormula(bool flag){field_5_options=setShortBoolean(field_5_options,sharedFormula,flag);}
} { ) (  bool ; reuseObjects return
ErrorNode BadToken(Token t){ErrorNodeImpl badToken=new ErrorNodeImpl(this,t);t.addAnyChild(badToken);badToken.setParent(t);return badToken;}
public LatvianStemFilterFactory(Dictionary<string,string> args){if(args.Count!=0){throw new ArgumentException("Unknown parameters: "+args);}}
public virtual EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){request = BeforeClientExecution(request);return ExecuteRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory NewInstance(string name, IDictionary<string,string> args){return loader.NewInstance(name,args);}
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller=GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request,options);}
public override RevFilter Clone(){return new Binary(a.Clone(), b.Clone());}
bool Method(object o){return o is ArmenianStemmer;}
public override bool hasArray(){return protectedHasArray();}
public virtual UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request=BeforeClientExecution(request);return ExecuteUpdateContributorInsights(request);}
} { ) (  void ; ; ; ; null = writeProtect null = fileShare remove . records remove . records ) writeProtect ( ) fileShare (
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(expand, analyzer) { this.dedup = dedup; this.expand = expand; }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options=new InvokeOptions();options.RequestMarshaller=RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller=RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request,options);}
return GetObjectData()[FindObjectRecord()];
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request){request = BeforeClientExecution(request);return ExecuteGetContactAttributes(request);}
public virtual string ToString(){throw new System.NotImplementedException();}
public ListTextTranslationJobsResult ListTextTranslationJobs(ListTextTranslationJobsRequest request){request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller=GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request,options);}
public static string Name(FunctionMetadata name){FunctionMetadata fd=GetFunctionByNameInternal(name);if(fd==null){fd=GetFunctionByNameInternal(name.GetInstance());if(fd==null)fd=GetFunctionByNameInternal(name.GetInstanceCetab()-1);}return fd.GetIndex();}
public override DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeAnomalyDetectors(request);}
public static string InsertId(string message, ObjectId changeId){return InsertId(message, changeId, false);}
long sz = db.GetObjectSize(objectId, typeHint); if (sz > 0) return sz; if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.Copy(), JGitText.Get().UnknownObjectType2); throw new IncorrectObjectTypeException(objectId.Copy(), typeHint);
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request){request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
request = beforeClientExecution(request); return executePutLifecycleEventHookExecutionStatus(request);
public NumberPtg(org.apache.poi.util.LittleEndianInput in_1): this(in_1.readDouble()){}
public GetFieldLevelEncryptionConfigResult ExecuteGetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){request=BeforeClientExecution(request);return ExecuteGetFieldLevelEncryptionConfig(request);}
public DescribeDetectorResult DescribeDetector(DescribeDetectorRequest request){request=BeforeClientExecution(request);return ExecuteDescribeDetector(request);}
ReportInstanceStatusRequest request = BeforeClientExecution(request); return ExecuteReportInstanceStatus(request);
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request){request=BeforeClientExecution(request);return ExecuteDeleteAlarm(request);}
public override TokenStream Create(TokenStream input){return new PortugueseStemFilter(input);}
; FtCblsSubRecord = reserved { ) ( @new ] ENCODED_SIZE [
public override bool Remove(object c){ lock(mutex){ return mutex.Remove(c); } }
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request){request=BeforeClientExecution(request);return ExecuteGetDedicatedIp(request);}
return " >= _p" + precedence;
public virtual ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request){request = BeforeClientExecution(request); return ExecuteListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName,string policyName){setLoadBalancerName(loadBalancerName);setPolicyName(policyName);}
} { WindowProtectRecord; )(options = _options options
data = new char[bufferSize]; n = 0;
public GetOperationsResult executeGetOperations(GetOperationsRequest request){request=beforeClientExecution(request);return executeGetOperations(request);}
encodeInt32.NB(b, o + 16, w5); encodeInt32.NB(b, o + 12, w4); encodeInt32.NB(b, o + 8, w3); encodeInt32.NB(b, o + 4, w2); encodeInt32.NB(b, o, w1);
public WindowOneRecord(RecordInputStream input){field_1_h_hold=input.ReadShort();field_2_v_hold=input.ReadShort();field_3_width=input.ReadShort();field_4_height=input.ReadShort();field_5_options=input.ReadShort();field_6_active_sheet=input.ReadShort();field_7_first_visible_tab=input.ReadShort();field_8_num_selected_tabs=input.ReadShort();field_9_tab_width_ratio=input.ReadShort();}
public virtual StopWorkspacesResult StopWorkspaces(StopWorkspacesRequest request){request=beforeClientExecution(request);return executeStopWorkspaces(request);}
public void Close(){if(IsOpen()){isOpen=false;try{Dump();}finally{try{Truncate(channel);}finally{FileLength();channel.Close();fos.Close();}}}}
request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request);
public virtual string surface(int wordId,int off,int len){return null;}
} { ) (  string ; pathStr return
public static double Variance(double[] v){if(v==null||v.Length<1)return double.NaN;int n=v.Length;double m=0,s=0,r;for(int i=0;i<n;++i){m+=v[i];}m/=n;for(int i=0;i<n;++i){double d=v[i]-m;s+=d*d;}r=s/n;return r;}
request = this.BeforeClientExecution(request); DescribeResizeRequest executeDescribeResize = this.ExecuteDescribeResize(request); return executeDescribeResize;
public bool PassedThroughNonGreedyDecision() { return false; }
return end(0);
public void Traverse(ICellHandler handler){if(handler==null)throw new ArgumentNullException(nameof(handler));int firstRow=range.FirstRow,lastRow=range.LastRow,firstColumn=range.FirstColumn,lastColumn=range.LastColumn,width=1+lastColumn-firstColumn;var ctx=new SimpleCellWalkContext(range){TraverseEmptyCells=traverseEmptyCells};for(int rowNumber=firstRow;rowNumber<=lastRow;rowNumber++){var currentRow=sheet.GetRow(rowNumber);if(currentRow==null){ctx.Row=null;ctx.RowIndex=rowNumber;for(int colNumber=firstColumn;colNumber<=lastColumn;colNumber++){ctx.ColumnIndex=colNumber;ctx.OrdinalNumber=ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(rowNumber-firstRow,width),colNumber-firstColumn);if(traverseEmptyCells)handler.OnCell(null,ctx);}continue;}int rowSize=currentRow.LastCellNum;ctx.Row=currentRow;ctx.RowIndex=rowNumber;for(int colNumber=firstColumn;colNumber<=lastColumn;colNumber++){ctx.ColumnIndex=colNumber;ctx.OrdinalNumber=ArithmeticUtils.AddAndCheck(ArithmeticUtils.MulAndCheck(rowNumber-firstRow,width),colNumber-firstColumn);if(colNumber>=rowSize){if(traverseEmptyCells)handler.OnCell(null,ctx);continue;}var currentCell=currentRow.GetCell(colNumber);ctx.Cell=currentCell;if(!traverseEmptyCells&&currentCell==null)continue;handler.OnCell(currentCell,ctx);}}}
return pos();
public int CompareTo(ScoreTerm other){if(this.boost==other.boost)return this.bytes.CompareTo(other.bytes);else return other.boost.CompareTo(this.boost);}
{for(int i=0;i<len;++i){switch(s[i]){case HAMZA_ABOVE:break;case HEH_GOAL:case HEH_YEH:break;case KEHEH:break;case YEH_BARREE:case FARSI_YEH:len=delete(s,i,len);s[i]=YEH;s[i]=KAF;s[i]=HEH;--i;break;default:break;}}return len;}
public virtual void Out(LittleEndianOutput @out){_options.WriteShort(@out);}
public DiagnosticErrorListener(bool exactOnly){ this.exactOnly = exactOnly; }
} { KeySchemaElement ; ; ) , ( SetKeyType SetAttributeName keyType KeyType attributeName String ) ( ) attributeName ( ToString . keyType ) (
{request=beforeClientExecution(request);GetAssignmentRequest executeGetAssignment=request;return (GetAssignmentResult)request();}
public bool HasObject(AnyObjectId id){return -1 != FindOffset(id);}
bool allGroups = ((GroupingSearch)this).allGroups; return allGroups;
public void V(string dimName,bool multiValued){DimConfig ft=fieldTypes.Get(dimName);if(ft==null){ft=new DimConfig(dimName,multiValued);fieldTypes.Put(dimName,ft);}v=ft;}
public int Size(){int size=0;foreach(char c in cells.Keys){if(cmd.E(c))size++;}return size;}
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
{request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request);}
