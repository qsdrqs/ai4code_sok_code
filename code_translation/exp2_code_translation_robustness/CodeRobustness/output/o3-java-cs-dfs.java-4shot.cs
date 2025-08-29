public void Serialize(LittleEndianOutput @out){@out.WriteShort(field_1_vcenter);}
void Foo(BlockList<T> src){if(src.Size==0)return;for(int srcDirIdx=0;srcDirIdx<src.tailDirIdx;++srcDirIdx)directory[srcDirIdx].AddAll(src,0,BLOCK_SIZE);if(src.tailBlkIdx!=0)tailBlock.AddAll(src,0,src.tailBlkIdx);}
private void B(byte b){if(upto==blockSize){if(currentBlock!=null){AddBlock(currentBlock);}currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
return (ObjectId)objectId;
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request,options);}
return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);
public virtual string buffer(){int msgB = RawParseUtils.tagMessage(raw, 0);if (msgB < 0){return "";}return RawParseUtils.decode(guessEncoding(raw, msgB, raw.Length), raw);}
BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false);bb.setOurBlockIndex(1);_bat_blocks.Add(bb);bb.setNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK);bb.setNextBlock(0, POIFSConstants.END_OF_CHAIN);_property_table.setStartBlock(0);_header.setBATCount(1);_header.setBATArray(new[] { 1 });
void SetAddress(int address){byte[] slice=pool.buffers[address>>ByteBlockPool.BYTE_BLOCK_SHIFT];System.Diagnostics.Debug.Assert(slice!=null);int upto=address&ByteBlockPool.BYTE_BLOCK_MASK;int offset0=address;System.Diagnostics.Debug.Assert(upto<slice.Length);}
public SubmoduleAddCommand Path(string path) { this.path = path; return this; }
public virtual ListIngestionsResult ListIngestions(ListIngestionsRequest request){request = BeforeClientExecution(request);return ExecuteListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState){this.stream = stream; SwitchTo(lexState);}
public GetShardIteratorResult ExecuteGetShardIterator(GetShardIteratorRequest request){request = BeforeClientExecution(request);return ExecuteGetShardIterator(request);}
public ModifyStrategyRequest(): base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis"){Method = MethodType.POST;}
public bool Ready(){lock(_lock){if(_in==null){throw new IOException("InputStreamReader is closed");}try{return _bytes.HasRemaining()||_in.Available>0;}catch(Exception){return false;}}}
return (EscherOptRecord)_optRecord;
public int Read(char[] buffer, int offset, int length){lock(this){if(buffer==null)throw new System.NullReferenceException("buffer == null");if(offset<0||length<0||offset+length>buffer.Length)throw new System.ArgumentOutOfRangeException();if(length==0)return 0;int copylen=length<count-pos?length:count-pos;for(int i=0;i<copylen;++i){buffer[offset+i]=this.buffer[pos+i];}pos+=copylen;return copylen;}}
sentenceOp = new NLPSentenceDetectorOp(this);
void Write(string str){Write(str!=null?str:Convert.ToString((object)null));}
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { this.functionName = functionName; }
return base.NextEntry().GetValue();
public void ReadBytes(byte[] b,int offset,int len,bool useBuffer){while(len>0){int available=bufferLength-bufferPosition;if(available<=0){if(len<bufferSize&&useBuffer){Refill();available=bufferLength-bufferPosition;if(available<=0){throw new System.IO.EndOfStreamException("read past EOF: "+this);}}else{long after=bufferStart+bufferPosition+len;if(after>length){throw new System.IO.EndOfStreamException("read past EOF: "+this);}ReadInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;return;}}int cnt=len<available?len:available;System.Array.Copy(buffer,bufferPosition,b,offset,cnt);offset+=cnt;len-=cnt;bufferPosition+=cnt;}}
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options=new InvokeOptions();options.RequestMarshaller=TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller=TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request,options);}
public virtual void Method(){throw new NotSupportedException();}
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options=new InvokeOptions();options.RequestMarshaller=ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller=ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request,options);}
public override void SetParams(string parameters){base.SetParams(parameters);language=country=variant="";var tokens=parameters.Split(',');if(tokens.Length>0)language=tokens[0];if(tokens.Length>1)country=tokens[1];if(tokens.Length>2)variant=tokens[2];}
public virtual DeleteDocumentationVersionResult DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){request = BeforeClientExecution(request);return ExecuteDeleteDocumentationVersion(request);}
public override bool Equals(object obj){if(!(obj is FacetLabel other)){return false;}if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request);
HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.SetParent(this);shape.SetAnchor(anchor);shapes.Add(shape);OnCreate(shape);return shape;
public string GetSheetname(int sheetIndex){return GetBoundSheetRec(sheetIndex).GetSheetname();}
public GetDashboardResult GetDashboard(GetDashboardRequest request){request = BeforeClientExecution(request);return ExecuteGetDashboard(request);}
internal AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request = BeforeClientExecution(request);return ExecuteAssociateSigninDelegateGroupsWithAccount(request);}
public void HandleMulBlankRecord(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();++j){BlankRecord br=new BlankRecord();br.setColumn(mbr.getFirstColumn()+j);br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
public static string Quote(string @string){int k=@string.IndexOf("\\E");if(k==-1)return "\\Q"+@string+"\\E";System.Text.StringBuilder sb=new System.Text.StringBuilder(@string.Length*2);sb.Append("\\Q");int apos=0;while((k=@string.IndexOf("\\E",apos))>=0){sb.Append(@string.Substring(apos,k-apos));sb.Append("\\\\E\\Q");apos=k+2;}sb.Append(@string.Substring(apos));sb.Append("\\E");return sb.ToString();}
public virtual ByteBuffer Value(ByteBuffer value){throw new ReadOnlyBufferException();}
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=nColumns;_nRows=nRows;object[] vv=new object[_nRows*_nColumns];for(int r=0;r<nRows;++r){object[] rowData=values2d[r];for(int c=0;c<nColumns;++c){vv[GetValueIndex(r,c)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller=GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request,options);}
public override string ToString(){return GetType().FullName+" ["+GetValueAsString()+"]";}
public virtual string ToString(string field){return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";}
public void IncrementAndGet(){refCount.IncrementAndGet();}
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options=new InvokeOptions();options.RequestMarshaller=UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller=UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request,options);}
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
public static void Pow10(TenPower tp,int pow10){tp.TenPower=TenPower.GetInstance(Math.Abs(pow10));if(pow10<0){MulShift(tp._divisor,tp._divisorShift);}else{MulShift(tp._multiplicand,tp._multiplierShift);} }
{StringBuilder b=new StringBuilder();int l=Length();b.Append(Path.DirectorySeparatorChar);for(int i=0;i<l;++i){b.Append(GetComponent(i));if(i<l-1){b.Append(Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider SetRoleName(string roleName){this.fetcher.SetRoleName(roleName);return this;} public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher = fetcher;}
public virtual void SetProgressMonitor(ProgressMonitor pm){pm = progressMonitor;}
public void ParseEntry(){if(!First()){if(!Eof()){ParseEntry();}}this.ptr=0;}
public virtual E Previous(){if(iterator.PreviousIndex()>=start){return iterator.Previous();}throw new NoSuchElementException();}
public string NewPrefix(){ return this.newPrefix; }
{ for (int i = 0; i < mSize; ++i) if (mValues[i] == value) return i; return -1; }
List<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(dictionary, 8, ignoreCase); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped;
public virtual GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return Invoke<GetGatewayResponsesResponse>(request, options);}
public void Pos(int pos){currentBlockIndex=(pos>>blockBits);currentBlock=blocks[currentBlockIndex];currentBlockUpto=(pos&blockMask);}
{ s = Math.Min(available(), Math.Max(0, n)); ptr += s; return s; }
public virtual void SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig){this.bootstrapActionConfig = bootstrapActionConfig;}
public override void Serialize(ILittleEndianOutput out1){out1.WriteShort(field_1_row);out1.WriteShort(field_2_col);out1.WriteShort(field_3_flags);out1.WriteShort(field_4_shapeid);out1.WriteShort((short)field_6_author.Length);out1.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,out1);}else{StringUtil.PutCompressedUnicode(field_6_author,out1);}if(field_7_padding!=null){out1.WriteByte((byte)field_7_padding.Value);}}
} ; ) count , string ( LastIndexOf return { ) string string (
public virtual bool AddLastImpl(E @object){return @object;}
public void UnsetSection(string section,string subsection){ConfigSnapshot src,res;do{src=state.Get();res=UnsetSection(src,section,subsection);}while(!state.CompareAndSet(src,res));}
public string TagName(){return tagName;}
public void AddSubRecord(SubRecord element, int index){subrecords.Insert(index, element);}
public bool Remove(object o){lock(mutex){return @delegate.Remove(o);}}
public override TokenStream Create(TokenStream input){return new DoubleMetaphoneFilter(input, maxCodeLength, inject);}
return inCoreLength;
public void SetValue(bool newValue){value = newValue;}
public Pair(ContentSource newSource, ContentSource oldSource){this.oldSource=oldSource;this.newSource=newSource;}
if (i <= count){throw new IndexOutOfRangeException(i.ToString());}return entries[i];
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr"){UriPattern="/repos";Method=MethodType.PUT;}
public virtual bool DeltaBaseAsOffset(){return deltaBaseAsOffset;}
public void Remove(){if(list.modCount==expectedModCount){if(lastLink!=null){Link<ET> next=lastLink.next;Link<ET> previous=lastLink.previous;next.lastLink=previous;previous.lastLink=next;if(link==lastLink){link=previous;}else{pos--;}lastLink=null;expectedModCount++;list.size--;list.modCount++;}else{throw new InvalidOperationException();}}else{throw new InvalidOperationException();}}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options=new InvokeOptions();options.RequestMarshaller=MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller=MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request,options);}
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller=AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request,options);}
}; start return { )(
public static WeightedTerm[] Query(Query query){ return GetTerms(query, false); }
public ByteBuffer(){throw new ReadOnlyBufferException();}
for (i = 0; i < iterations; i++) { blocks[blocksOffset++] = (byte)(byte0 & 0xFF); values[valuesOffset++] = (byte)((byte0 >> 2) & 3); blocks[blocksOffset++] = (byte)(byte1 & 0xFF); values[valuesOffset++] = (byte)((byte1 >> 4) & 15); blocks[blocksOffset++] = (byte)(byte2 & 0xFF); values[valuesOffset++] = (byte)((byte2 >> 6) & 63); }
string result = elements[elements.Length - 1]; if (result.EndsWith(Constants.DOT_GIT_EXT)) { result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); } else if (result.Equals(Constants.DOT_GIT)) { result = elements[elements.Length - 2]; } return result;
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
string AccessKeySecret(){return this.accessKeySecret;}
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public virtual DescribeVoicesResult DescribeVoices(DescribeVoicesRequest request){request = BeforeClientExecution(request);return ExecuteDescribeVoices(request);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options=new InvokeOptions();options.RequestMarshaller=ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller=ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
public DescribeJobRequest(string vaultName, string jobId){SetVaultName(vaultName);SetJobId(jobId);}
public EscherRecord Get(int index){return escherRecords[index];}
public virtual GetApisResponse GetApis(GetApisRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller=GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request,options);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate GetTrackingRefUpdate(){return trackingRefUpdate;}
void Print(bool b){Console.Write(b.ToString());}
public QueryNode GetFirstChild(){ return GetChildren()[0]; }
new NotIgnoredFilter(workdirTreeIndex) { workdirTreeIndex = index };
public AreaRecord(RecordInputStream in1){field_1_formatFlags = in1.readShort();}
public GetThumbnailRequest() : base("cloudphoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request,options);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options=new InvokeOptions();options.RequestMarshaller=PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller=PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request,options);}
public OrdRange GetPrefixToOrdRange(string dim){return prefixToOrdRange.Get(dim);}
string symbol="";if(startIndex>=0&&startIndex<GetInputStream().Size()){symbol=Utils.EscapeWhitespace(GetText(Interval.Of(startIndex,startIndex)),false);}return string.Format(System.Globalization.CultureInfo.CurrentCulture,"{0}('{1}')",typeof(LexerNoViableAltException).Name,symbol);
{ return (E)peekFirstImpl(); }
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){request = BeforeClientExecution(request);return ExecuteCreateWorkspaces(request);}
public NumberFormatIndexRecord Copy(){return this;}
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request,options);}
public SparseIntArray(int initialCapacity){initialCapacity=ArrayUtils.IdealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
return new HyphenatedWordsFilter(input);
request = BeforeClientExecution(request); return ExecuteCreateDistributionWithTags(request);
public RandomAccessFile(string fileName, string mode){_ = new System.IO.FileInfo(fileName);}
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string ToString(long value){StringBuilder sb = new StringBuilder(16);WriteHex(sb,value,16,"");return sb.ToString();}
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
public HSSFColor GetColor(int index){if(index==HSSFColorPredefined.AUTOMATIC.GetIndex()){return HSSFColorPredefined.AUTOMATIC.GetColor();}HSSFColor b=_palette[index];return b==null?new CustomColor(index,b):b;}
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol){throw new NotImplementedFunctionException(_functionName);}
public void Serialize(LittleEndianOutput @out){@out.writeShort(field_1_number_crn_records);@out.writeShort(field_2_sheet_table_index);}
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
{ this._fontIndex = fontIndex; this._character = character; FormatRun(character, fontIndex); }
public static byte[] ToBytes(char[] chars, int offset, int length){byte[] result=new byte[2*length];int end=length+offset;int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options=new InvokeOptions();options.RequestMarshaller=UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller=UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request,options);}
public virtual List<Token> GetHiddenTokensToLeft(int tokenIndex){return GetHiddenTokensToLeft(tokenIndex - 1);}
public override bool Equals(object obj){if(this==obj)return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; int i = 0; foreach (KeyValuePair<SpanQuery, float> kvp in weightBySpanQuery) { SpanQuery sq = kvp.Key; float boost = kvp.Value; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries);
return new StashCreateCommand(repo);
public FieldInfo get(string fieldName){return byName.get(fieldName);}
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request,options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options=new InvokeOptions();options.RequestMarshaller=ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller=ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request,options);}
internal SetInstanceProtectionResult ExecuteSetInstanceProtection(SetInstanceProtectionRequest request){request = beforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
public virtual ModifyDBProxyResult ModifyDBProxy(ModifyDBProxyRequest request){request=BeforeClientExecution(request);return ExecuteModifyDBProxy(request);}
public void Add(CharsRefBuilder output,int offset,int len,int endOffset,int posLength){if(count==outputs.Length){outputs=ArrayUtil.Grow(outputs,count+1);}if(count==endOffsets.Length){int[] next=ArrayUtil.Oversize(count+1,Integer.BYTES);System.Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}if(count==posLengths.Length){int[] next=ArrayUtil.Oversize(count+1,Integer.BYTES);System.Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;++count;}
public FetchLibrariesRequest(): base("CloudPhoto","2017-07-11","FetchLibraries","cloudphoto"){Protocol = ProtocolType.HTTPS;}
bool Exists(){return fs.Exists(objects);}
public FilterOutputStream(Stream @out){this.@out = @out;}
public ScaleClusterRequest(): base("CS", "2015-12-15", "ScaleCluster", "csk"){UriPattern = "/clusters/[ClusterId]";Method = MethodType.PUT;}
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2){return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options=new InvokeOptions();options.RequestMarshaller=ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller=ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request,options);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request,options);}
public void SetSharedFormula(bool flag){field_5_options = sharedFormula.SetShortBoolean(field_5_options, flag);}
} ; reuseObjects return { ) (  bool
ErrorNode t = new ErrorNodeImpl((Token)badToken); AddAnyChild(t); t.SetParent(this); return t; }
public LatvianStemFilterFactory(IDictionary<string, string> args){if(args.Count!=0){throw new System.ArgumentException("Unknown parameters: "+args);}}
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory NewInstance(string name, IDictionary<string,string> args){return loader.NewInstance(name, args);}
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto"){Protocol = ProtocolType.HTTPS;}
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
public override RevFilter Clone(){return new Binary(a.Clone(), b.Clone());}
bool IsArmenianStemmer(object o){ return o is ArmenianStemmer; }
public bool HasArray(){return ProtectedHasArray();}
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance;return Invoke<UpdateContributorInsightsResponse>(request, options);}
void Method() { records.Remove(fileShare); records.Remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer){base(dedup, analyzer);this.expand = expand;}
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options=new InvokeOptions();options.RequestMarshaller=RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller=RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request,options);}
return FindObjectRecord().GetObjectData();
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller=GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request,options);}
public override string ToString(){return getKey() + ": " + getValue();}
public ListTextTranslationJobsResult ExecuteListTextTranslationJobs(ListTextTranslationJobsRequest request){request=BeforeClientExecution(request);return ExecuteListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller=GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request,options);}
public static int GetFunctionIndex(string name){FunctionMetadata fd=FunctionMetadata.GetInstance(GetFunctionByNameInternal(name));if(fd==null){fd=FunctionMetadata.GetInstanceCetab(GetFunctionByNameInternal(name));if(fd==null){return-1;}}return fd.GetIndex();}
public virtual DescribeAnomalyDetectorsResult DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
public static string InsertId(string message, ObjectId changeId){return InsertId(message, changeId, false);}
public virtual long GetObjectSize(AnyObjectId objectId, int typeHint){long sz=db.GetObjectSize(this,objectId,typeHint);if(0<sz)return sz;if(typeHint==OBJ_ANY)throw new MissingObjectException(objectId.Copy(),JGitText.Get().unknownObjectType2);throw new MissingObjectException(objectId.Copy(),typeHint);}
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options=new InvokeOptions();options.RequestMarshaller=ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller=ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request,options);}
public virtual PutLifecycleEventHookExecutionStatusResult PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){request = BeforeClientExecution(request);return ExecutePutLifecycleEventHookExecutionStatus(request);}
public static NumberPtg Read(LittleEndianInput in1){return new NumberPtg(in1.ReadDouble());}
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller=GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request,options);}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){request = BeforeClientExecution(request); return ExecuteDescribeDetector(request);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options=new InvokeOptions();options.RequestMarshaller=ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller=ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request,options);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
public override TokenStream Create(TokenStream input){return new PortugueseStemFilter(input);}
public FtCblsSubRecord(){reserved = new byte[ENCODED_SIZE];}
public override bool Remove(object @object){lock(mutex){return c.Remove(@object);}}
public GetDedicatedIpResult GetDedicatedIp(GetDedicatedIpRequest request){request = BeforeClientExecution(request);return ExecuteGetDedicatedIp(request);}
}; " >= _p" + precedence return { ) ( string
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options=new InvokeOptions();options.RequestMarshaller=ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller=ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request,options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName,string policyName){SetLoadBalancerName(loadBalancerName);SetPolicyName(policyName);}
public WindowProtectRecord(Options options){_options = options;}
public UnbufferedCharStream(int bufferSize){n=0;data=new char[bufferSize];}
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller=GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request,options);}
public static void WriteInts(byte[] b,int o,int w1,int w2,int w3,int w4,int w5){NB.EncodeInt32(b,o,w1);NB.EncodeInt32(b,o+4,w2);NB.EncodeInt32(b,o+8,w3);NB.EncodeInt32(b,o+12,w4);NB.EncodeInt32(b,o+16,w5);}
public WindowOneRecord(RecordInputStream @in){field_1_h_hold=@in.ReadShort();field_2_v_hold=@in.ReadShort();field_3_width=@in.ReadShort();field_4_height=@in.ReadShort();field_5_options=@in.ReadShort();field_6_active_sheet=@in.ReadShort();field_7_first_visible_tab=@in.ReadShort();field_8_num_selected_tabs=@in.ReadShort();field_9_tab_width_ratio=@in.ReadShort();}
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
public void Close(){if(isOpen){isOpen=false;try{Dump();}finally{try{channel.Truncate(fileLength);}finally{try{channel.Close();}finally{fos.Close();}}}}}
request = BeforeClientExecution(request); return ExecuteDescribeMatchmakingRuleSets(request);
string Surface(int wordId, char[] surface, int off, int len){return null;}
public virtual string GetPathStr(){return pathStr;}
public static double Variance(double[] v){double r=double.NaN;if(v!=null&&v.Length>=1){double m=0,s=0;int n=v.Length;for(int i=0;i<n;++i){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;++i){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request,options);}
public bool passedThroughNonGreedyDecision() { return false; }
() { return end(0); }
public virtual void TraverseCells(CellHandler handler){int firstRow=range.GetFirstRow();int lastRow=range.GetLastRow();int firstColumn=range.GetFirstColumn();int lastColumn=range.GetLastColumn();int width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext(null,null,null,width);Row currentRow=null;Cell currentCell=null;for(ctx.RowNumber=firstRow;ctx.RowNumber<=lastRow;ctx.RowNumber++){currentRow=sheet.GetRow(ctx.RowNumber);if(currentRow==null)continue;int rowSize=ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber,firstRow),width);for(ctx.ColNumber=firstColumn;ctx.ColNumber<=lastColumn;ctx.ColNumber++){currentCell=currentRow.GetCell(ctx.ColNumber);if(currentCell==null)continue;if(currentCell.IsEmpty()&&!traverseEmptyCells)continue;ctx.CurrentCell=currentCell;ctx.OrdinalNumber=ArithmeticUtils.AddAndCheck(rowSize,ctx.ColNumber-firstColumn+1);handler.OnCell(ctx);}}}
} ; pos return { ) (
public int CompareTo(ScoreTerm other){if(this.boost==other.boost)return other.GetBytes().CompareTo(this.GetBytes());else return other.boost.CompareTo(this.boost);}
public int Normalize(char[] s,int len){for(int i=0;i<len;++i){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;break;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=Delete(s,i,len);--i;break;}}return len;}
public void WriteShort(LittleEndianOutput output){output.WriteShort(_options);}
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
public KeySchemaElement(string attributeName, KeyType keyType){SetAttributeName(attributeName);SetKeyType(keyType);}
public virtual GetAssignmentResult GetAssignment(GetAssignmentRequest request){request = BeforeClientExecution(request); return ExecuteGetAssignment(request);}
public bool Contains(AnyObjectId id){return FindOffset(id) != -1;}
public virtual GroupingSearch AllGroups(bool allGroups){this.allGroups = allGroups;return this;}
public virtual void setMultiValued(string dimName,bool v){lock(this){DimConfig ft=!fieldTypes.ContainsKey(dimName)?null:fieldTypes[dimName];if(ft==null){ft=new DimConfig(dimName);fieldTypes[dimName]=ft;}ft.multiValued=v;}}
public int Size(){int size=0;IEnumerator<char> i=cells.Keys.GetEnumerator();for(;i.MoveNext();){char c=i.Current;Cell e=cmd.At(c);if(e>=0){size++;}}return size;}
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request,options);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request, options);}
