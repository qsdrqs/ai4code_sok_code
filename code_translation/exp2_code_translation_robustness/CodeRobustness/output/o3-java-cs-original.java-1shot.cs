public void Serialize(LittleEndianOutput out) {out.WriteShort(field_1_vcenter);}
public void AddAll(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) AddAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); if (src.tailBlkIdx != 0) AddAll(src.tailBlock, 0, src.tailBlkIdx); }
public void WriteByte(byte b){if(upto==blockSize){if(currentBlock!=null){AddBlock(currentBlock);}currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId GetObjectId(){return objectId;}
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request,options);}
public long RamBytesUsed(){return (termOffsets != null ? termOffsets.RamBytesUsed() : 0) + (termsDictOffsets != null ? termsDictOffsets.RamBytesUsed() : 0);}
public virtual string GetFullMessage(){byte[] raw = buffer;int msgB = NGit.Util.RawParseUtils.tagMessage(raw, 0);if(msgB < 0){return "";}return NGit.Util.RawParseUtils.decode(guessEncoding(), raw, msgB, raw.Length);}
public POIFSFileSystem() : this(true) {_header.SetBATCount(1);_header.SetBATArray(new int[] { 1 });BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false);bb.SetOurBlockIndex(1);_bat_blocks.Add(bb);SetNextBlock(0, POIFSConstants.END_OF_CHAIN);SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK);_property_table.SetStartBlock(0);}
public virtual void Init(int address){slice=pool.buffers[address>>ByteBlockPool.BYTE_BLOCK_SHIFT];System.Diagnostics.Debug.Assert(slice!=null);upto=address&ByteBlockPool.BYTE_BLOCK_MASK;offset0=address;System.Diagnostics.Debug.Assert(upto<slice.Length);}
public SubmoduleAddCommand SetPath(string path){this.path = path;return this;}
public ListIngestionsResult ListIngestions(ListIngestionsRequest request){request = BeforeClientExecution(request);return ExecuteListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResult GetShardIterator(GetShardIteratorRequest request){request = BeforeClientExecution(request); return ExecuteGetShardIterator(request);}
public ModifyStrategyRequest() : base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { Method = MethodType.POST; }
public bool Ready(){lock(@lock){if(@in==null){throw new IOException("InputStreamReader is closed");}try{return bytes.HasRemaining()||@in.Available()>0;}catch(IOException){return false;}}}
public EscherOptRecord GetOptRecord(){return _optRecord;}
public int Read(byte[] buffer, int offset, int length){lock(this){if(buffer==null)throw new ArgumentNullException(nameof(buffer));if(offset<0||length<0||offset+length>buffer.Length)throw new ArgumentOutOfRangeException();if(length==0)return 0;int copylen=count-pos<length?count-pos:length;for(int i=0;i<copylen;i++){buffer[offset+i]=(byte)this.buffer[pos+i];}pos+=copylen;return copylen;}}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp){this.sentenceOp = sentenceOp;}
public void Print(string str){Write(str != null ? str : "null");}
public NotImplementedFunctionException(string functionName, NotImplementedException cause) : base(functionName, cause) { this.functionName = functionName; }
public virtual V Next(){return base.NextEntry().GetValue();}
public void ReadBytes(byte[] b, int offset, int len, bool useBuffer){int available = bufferLength - bufferPosition;if(len <= available){if(len>0) Array.Copy(buffer, bufferPosition, b, offset, len);bufferPosition+=len;}else{if(available>0){Array.Copy(buffer, bufferPosition, b, offset, available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer && len<bufferSize){Refill();if(bufferLength<len){Array.Copy(buffer, 0, b, offset, bufferLength);throw new EndOfStreamException("read past EOF: " + this);}else{Array.Copy(buffer, 0, b, offset, len);bufferPosition=len;}}else{long after = bufferStart + bufferPosition + len;if(after > Length())throw new EndOfStreamException("read past EOF: " + this);ReadInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}}
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
public void Remove() { throw new NotSupportedException(); }
public CacheSubnetGroup modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
public override void setParams(string @params){base.setParams(@params);language = country = variant = "";java.util.StringTokenizer st = new java.util.StringTokenizer(@params, ",");if(st.hasMoreTokens())language = st.nextToken();if(st.hasMoreTokens())country = st.nextToken();if(st.hasMoreTokens())variant = st.nextToken();}
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj){if(!(obj is FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].Equals(other.components[i])){return false;}}return true;}
public GetInstanceAccessDetailsResult GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){request = BeforeClientExecution(request); return ExecuteGetInstanceAccessDetails(request);}
public HSSFPolygon createPolygon(HSSFChildAnchor anchor){HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.setParent(this);shape.setAnchor(anchor);shapes.add(shape);onCreate(shape);return shape;}
public string GetSheetName(int sheetIndex){return GetBoundSheetRec(sheetIndex).SheetName;}
public GetDashboardResult GetDashboard(GetDashboardRequest request){request = BeforeClientExecution(request);return ExecuteGetDashboard(request);}
public virtual AssociateSigninDelegateGroupsWithAccountResult AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
public void AddMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.GetNumColumns();j++){var br=new BlankRecord();br.Column=(short)(j+mbr.GetFirstColumn());br.Row=mbr.Row;br.XFIndex=mbr.GetXFAt(j);InsertCell(br);}}
public static string Quote(string str){StringBuilder sb=new StringBuilder();sb.Append("\\Q");int apos=0,k;while((k=str.IndexOf("\\E",apos,StringComparison.Ordinal))>=0){sb.Append(str.Substring(apos,k+2-apos)).Append("\\\\E\\Q");apos=k+2;}return sb.Append(str.Substring(apos)).Append("\\E").ToString();}
public virtual ByteBuffer putInt(int value){throw new ReadOnlyBufferException();}
public ArrayPtg(object[][] values2d){int nColumns=values2d[0].Length;int nRows=values2d.Length;_nColumns=(short)nColumns;_nRows=(short)nRows;object[] vv=new object[_nColumns*_nRows];for(int r=0;r<nRows;r++){object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[getValueIndex(c,r)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public virtual GetIceServerConfigResult GetIceServerConfig(GetIceServerConfigRequest request){request = BeforeClientExecution(request);return ExecuteGetIceServerConfig(request);}
public override string ToString() { return GetType().FullName + " [" + GetValueAsString() + "]"; }
public string ToString(string field){return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";}
public void IncRef(){refCount.IncrementAndGet();}
public UpdateConfigurationSetSendingEnabledResult UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = BeforeClientExecution(request); return ExecuteUpdateConfigurationSetSendingEnabled(request); }
public int GetNextXBATChainOffset(){return GetXBATEntriesPerBlock()*LittleEndianConsts.INT_SIZE;}
public virtual void MultiplyByPowerOfTen(int pow10){TenPower tp = TenPower.GetInstance(Math.Abs(pow10));if (pow10 < 0){MulShift(tp._divisor, tp._divisorShift);}else{MulShift(tp._multiplicand, tp._multiplierShift);}}
public override string ToString(){var b=new System.Text.StringBuilder();int l=Length();b.Append(System.IO.Path.DirectorySeparatorChar);for(int i=0;i<l;i++){b.Append(GetComponent(i));if(i<l-1){b.Append(System.IO.Path.DirectorySeparatorChar);}}return b.ToString();}
public InstanceProfileCredentialsProvider WithFetcher(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this;}
public void SetProgressMonitor(ProgressMonitor pm){progressMonitor = pm;}
public void Reset(){if(!First()){ptr=0;if(!Eof())ParseEntry();}}
public override E previous(){if(iterator.previousIndex()>=start){return iterator.previous();}throw new java.util.NoSuchElementException();}
public string GetNewPrefix() { return this.newPrefix; }
public int IndexOfValue(int value){for(int i=0;i<mSize;i++)if(mValues[i]==value)return i;return -1;}
public List<CharsRef> UniqueStems(char[] word, int length){List<CharsRef> stems=Stem(word,length);if(stems.Count<2){return stems;}CharArraySet terms=new CharArraySet(8,dictionary.ignoreCase);List<CharsRef> deduped=new List<CharsRef>();foreach(CharsRef s in stems){if(!terms.Contains(s)){deduped.Add(s);terms.Add(s);}}return deduped;}
public GetGatewayResponsesResult GetGatewayResponses(GetGatewayResponsesRequest request){request = BeforeClientExecution(request);return ExecuteGetGatewayResponses(request);}
public void SetPosition(long pos){currentBlockIndex=(int)(pos>>blockBits);currentBlock=blocks[currentBlockIndex];currentBlockUpto=(int)(pos&blockMask);}
public long skip(long n){int s=(int)Math.Min(available(), Math.Max(0, n));ptr+=s;return s;}
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig){SetBootstrapActionConfig(bootstrapActionConfig);}
public void Serialize(LittleEndianOutput @out){@out.WriteShort(field_1_row);@out.WriteShort(field_2_col);@out.WriteShort(field_3_flags);@out.WriteShort(field_4_shapeid);@out.WriteShort(field_6_author.Length);@out.WriteByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.PutUnicodeLE(field_6_author,@out);}else{StringUtil.PutCompressedUnicode(field_6_author,@out);}if(field_7_padding!=null){@out.WriteByte(field_7_padding.Value);}}
public int lastIndexOf(string str){return lastIndexOf(str, count);}
public bool add(E @object){return addLastImpl(@object);}
public void UnsetSection(string section, string subsection){ConfigSnapshot src,res;do{src=state.Get();res=UnsetSection(src,section,subsection);}while(!state.CompareAndSet(src,res));}
public string GetTagName(){return tagName;}
public void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
public bool Remove(object o){lock(mutex){return Delegate().Remove(o);}}
public DoubleMetaphoneFilter Create(TokenStream input){return new DoubleMetaphoneFilter(input, maxCodeLength, inject);}
public virtual long Length(){return inCoreLength();}
public void SetValue(bool newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource){this.oldSource = oldSource;this.newSource = newSource;}
public int Get(int i){if(count<=i)throw new IndexOutOfRangeException(i.ToString());return entries[i];}
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { SetUriPattern("/repos"); SetMethod(MethodType.PUT); }
public virtual bool IsDeltaBaseAsOffset(){return deltaBaseAsOffset;}
public void Remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); } }
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public AllocateHostedConnectionResult AllocateHostedConnection(AllocateHostedConnectionRequest request){request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
public int GetBeginIndex() { return start; }
public static WeightedTerm[] getTerms(Query query){return getTerms(query,false);}
public ByteBuffer Compact() { throw new ReadOnlyBufferException(); }
public void Decode(byte[] blocks,int blocksOffset,long[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;++i){long byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;long byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);long byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public string GetHumanishName(){string s=GetPath();if("/".Equals(s)||"".Equals(s))s=GetHost();if(s==null)throw new ArgumentException();string[]elements;if("file".Equals(scheme)||LOCAL_FILE.IsMatch(s))elements=System.Text.RegularExpressions.Regex.Split(s,@"[\\/]+");else elements=System.Text.RegularExpressions.Regex.Split(s,@"/+");if(elements.Length==0)throw new ArgumentException();string result=elements[elements.Length-1];if(Constants.DOT_GIT.Equals(result))result=elements[elements.Length-2];else if(result.EndsWith(Constants.DOT_GIT_EXT))result=result.Substring(0,result.Length-Constants.DOT_GIT_EXT.Length);return result;}
public virtual DescribeNotebookInstanceLifecycleConfigResult DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){request = BeforeClientExecution(request);return ExecuteDescribeNotebookInstanceLifecycleConfig(request);}
public string GetAccessKeySecret() { return this.accessKeySecret; }
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options=new InvokeOptions();options.RequestMarshaller=CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller=CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request,options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request,options);}
public ListMonitoringExecutionsResult ListMonitoringExecutions(ListMonitoringExecutionsRequest request){request = BeforeClientExecution(request);return ExecuteListMonitoringExecutions(request);}
public DescribeJobRequest(string vaultName, string jobId){setVaultName(vaultName);setJobId(jobId);}
public EscherRecord GetEscherRecord(int index){return escherRecords[index];}
public GetApisResult getApis(GetApisRequest request){request = beforeClientExecution(request);return executeGetApis(request);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
public TrackingRefUpdate GetTrackingRefUpdate() { return trackingRefUpdate; }
public void Print(bool b){Print(b.ToString());}
public virtual QueryNode GetChild() { return GetChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex){this.index = workdirTreeIndex;}
public AreaRecord(RecordInputStream inStream){field_1_formatFlags = inStream.ReadShort();}
public GetThumbnailRequest() : base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { Protocol = ProtocolType.HTTPS; }
public virtual DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){request = BeforeClientExecution(request);return ExecutePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange GetOrdRange(string dim){return prefixToOrdRange[dim];}
public override string ToString() { string symbol = ""; if (startIndex >= 0 && startIndex < GetInputStream().Size) { symbol = GetInputStream().GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); } return string.Format(System.Globalization.CultureInfo.CurrentCulture, "{0}('{1}')", typeof(LexerNoViableAltException).Name, symbol); }
public E Peek(){return PeekFirstImpl();}
public CreateWorkspacesResult CreateWorkspaces(CreateWorkspacesRequest request){request = BeforeClientExecution(request); return ExecuteCreateWorkspaces(request);}
public NumberFormatIndexRecord Clone() { return Copy(); }
public DescribeRepositoriesResult DescribeRepositories(DescribeRepositoriesRequest request){request = BeforeClientExecution(request);return ExecuteDescribeRepositories(request);}
public SparseIntArray(int initialCapacity){initialCapacity=ArrayUtils.IdealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
public virtual HyphenatedWordsFilter Create(TokenStream input){return new HyphenatedWordsFilter(input);}
public CreateDistributionWithTagsResult CreateDistributionWithTags(CreateDistributionWithTagsRequest request){request = BeforeClientExecution(request);return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(String fileName, String mode) : this(new File(fileName), mode) {}
public DeleteWorkspaceImageResult DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){request=BeforeClientExecution(request);return ExecuteDeleteWorkspaceImage(request);}
public static string toHex(long value){StringBuilder sb=new StringBuilder(16);writeHex(sb,value,16,"");return sb.ToString();}
public UpdateDistributionResult UpdateDistribution(UpdateDistributionRequest request){request = BeforeClientExecution(request);return ExecuteUpdateDistribution(request);}
public HSSFColor GetColor(short index){ if(index == HSSFColorPredefined.AUTOMATIC.Index){ return HSSFColorPredefined.AUTOMATIC.Color; } byte[] b = _palette.GetColor(index); return (b == null) ? null : new CustomColor(index, b); }
public ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol){throw new NotImplementedFunctionException(_functionName);}
public void Serialize(ILittleEndianOutput output){output.WriteShort((short)field_1_number_crn_records);output.WriteShort((short)field_2_sheet_table_index);}
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(){return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] ToBigEndianUtf16Bytes(char[] chars, int offset, int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public override UploadArchiveResult UploadArchive(UploadArchiveRequest request){request = BeforeClientExecution(request);return ExecuteUploadArchive(request);}
public List<Token> GetHiddenTokensToLeft(int tokenIndex){return GetHiddenTokensToLeft(tokenIndex, -1);}
public override bool Equals(object obj){if(ReferenceEquals(this,obj))return true;if(!base.Equals(obj))return false;if(GetType()!=obj.GetType())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!compiled.Equals(other.compiled))return false;if(term==null){if(other.term!=null)return false;}else if(!term.Equals(other.term))return false;return true;}
public SpanQuery MakeSpanClause(){SpanQuery[] spanQueries=new SpanQuery[Size()];int i=0;foreach(var kvp in weightBySpanQuery){SpanQuery sq=kvp.Key;float boost=kvp.Value;if(boost!=1f){sq=new SpanBoostQuery(sq,boost);}spanQueries[i++]=sq;}return spanQueries.Length==1?spanQueries[0]:new SpanOrQuery(spanQueries);}
public StashCreateCommand StashCreate(){return new StashCreateCommand(repo);}
public virtual FieldInfo FieldInfo(string fieldName){return byName[fieldName];}
public DescribeEventSourceResult DescribeEventSource(DescribeEventSourceRequest request){request = BeforeClientExecution(request);return ExecuteDescribeEventSource(request);}
public GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request){request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
public virtual CancelUpdateStackResult CancelUpdateStack(CancelUpdateStackRequest request){request = BeforeClientExecution(request);return ExecuteCancelUpdateStack(request);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public SetInstanceProtectionResult SetInstanceProtection(SetInstanceProtectionRequest request){request=BeforeClientExecution(request);return ExecuteSetInstanceProtection(request);}
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options=new InvokeOptions();options.RequestMarshaller=ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller=ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request,options);}
public void Add(char[] output, int offset, int len, int endOffset, int posLength){if(count==outputs.Length){outputs=ArrayUtil.Grow(outputs,count+1);}if(count==endOffsets.Length){var next=new int[ArrayUtil.Oversize(1+count,sizeof(int))];Array.Copy(endOffsets,0,next,0,count);endOffsets=next;}if(count==posLengths.Length){var next=new int[ArrayUtil.Oversize(1+count,sizeof(int))];Array.Copy(posLengths,0,next,0,count);posLengths=next;}if(outputs[count]==null){outputs[count]=new CharsRefBuilder();}outputs[count].CopyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public FetchLibrariesRequest() : base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto"){SetProtocol(ProtocolType.HTTPS);}
public virtual bool exists(){return fs.exists(objects);}
public FilterOutputStream(Stream output){ this.out = output; }
public ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { UriPattern = "/clusters/[ClusterId]"; Method = MethodType.PUT; }
public DataValidationConstraint CreateTimeConstraint(int operatorType, string formula1, string formula2){return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);}
public ListObjectParentPathsResult ListObjectParentPaths(ListObjectParentPathsRequest request){request = BeforeClientExecution(request); return ExecuteListObjectParentPaths(request);}
public virtual DescribeCacheSubnetGroupsResult DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){request=BeforeClientExecution(request);return ExecuteDescribeCacheSubnetGroups(request);}
public void setSharedFormula(bool flag){field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);}
public bool isReuseObjects(){return reuseObjects;}
public ErrorNode AddErrorNode(Token badToken){ ErrorNodeImpl t = new ErrorNodeImpl(badToken); AddAnyChild(t); t.SetParent(this); return t; }
public LatvianStemFilterFactory(Dictionary<string,string> args) : base(args) {if (args.Count != 0){throw new ArgumentException("Unknown parameters: " + args);}}
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory ForName(string name, Dictionary<string,string> args){return loader.NewInstance(name, args);}
public AddAlbumPhotosRequest() : base("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto") { SetProtocol(ProtocolType.HTTPS); }
public GetThreatIntelSetResult GetThreatIntelSet(GetThreatIntelSetRequest request){request = BeforeClientExecution(request);return ExecuteGetThreatIntelSet(request);}
public override RevFilter Clone() { return new Binary(a.Clone(), b.Clone()); }
public override bool Equals(object o){return o is ArmenianStemmer;}
public bool HasArray() { return ProtectedHasArray(); }
public UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
public void UnwriteProtectWorkbook(){records.Remove(fileShare);records.Remove(writeProtect);fileShare=null;writeProtect=null;}
public SolrSynonymParser(bool dedup, bool expand, Analyzer analyzer) : base(dedup, analyzer) { this.expand = expand; }
public RequestSpotInstancesResult RequestSpotInstances(RequestSpotInstancesRequest request){request = BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
public virtual byte[] GetObjectData(){return FindObjectRecord().GetObjectData();}
public GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request){request=BeforeClientExecution(request);return ExecuteGetContactAttributes(request);}
public override string ToString(){return GetKey() + ": " + GetValue();}
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){request = BeforeClientExecution(request);return ExecuteListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller=GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static short LookupIndexByName(string name){FunctionMetadata fd=GetInstance().GetFunctionByNameInternal(name);if(fd==null){fd=GetInstanceCetab().GetFunctionByNameInternal(name);if(fd==null){return-1;}}return(short)fd.GetIndex();}
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string InsertId(string message, ObjectId changeId){ return InsertId(message, changeId, false); }
public long getObjectSize(AnyObjectId objectId, int typeHint){long sz = db.getObjectSize(this, objectId);if (sz < 0){if (typeHint == OBJ_ANY)throw new MissingObjectException(objectId.copy(), JGitText.get().unknownObjectType2);throw new MissingObjectException(objectId.copy(), typeHint);}return sz;}
public ImportInstallationMediaResult ImportInstallationMedia(ImportInstallationMediaRequest request){request = BeforeClientExecution(request);return ExecuteImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){request = this.beforeClientExecution(request);return this.executePutLifecycleEventHookExecutionStatus(request);}
public NumberPtg(LittleEndianInput input) : this(input.readDouble()) {}
public GetFieldLevelEncryptionConfigResult GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){request = BeforeClientExecution(request);return ExecuteGetFieldLevelEncryptionConfig(request);}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options=new InvokeOptions();options.RequestMarshaller=DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
public virtual ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request){request = BeforeClientExecution(request);return ExecuteReportInstanceStatus(request);}
public DeleteAlarmResult DeleteAlarm(DeleteAlarmRequest request) { request = BeforeClientExecution(request); return ExecuteDeleteAlarm(request); }
public TokenStream Create(TokenStream input){return new PortugueseStemFilter(input);}
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public override bool remove(object obj){lock(mutex){return c.remove(obj);}}
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options=new InvokeOptions();options.RequestMarshaller=GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller=GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request,options);}
public override string ToString() { return precedence + " >= _p"; }
public ListStreamProcessorsResult ListStreamProcessors(ListStreamProcessorsRequest request){request = BeforeClientExecution(request);return ExecuteListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { SetLoadBalancerName(loadBalancerName); SetPolicyName(policyName); }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize){n=0;data=new int[bufferSize];}
public GetOperationsResult GetOperations(GetOperationsRequest request){request = BeforeClientExecution(request);return ExecuteGetOperations(request);}
public void CopyRawTo(byte[] b, int o){NB.EncodeInt32(b, o, w1);NB.EncodeInt32(b, o + 4, w2);NB.EncodeInt32(b, o + 8, w3);NB.EncodeInt32(b, o + 12, w4);NB.EncodeInt32(b, o + 16, w5);}
public WindowOneRecord(RecordInputStream inStream){field_1_h_hold=inStream.ReadShort();field_2_v_hold=inStream.ReadShort();field_3_width=inStream.ReadShort();field_4_height=inStream.ReadShort();field_5_options=inStream.ReadShort();field_6_active_sheet=inStream.ReadShort();field_7_first_visible_tab=inStream.ReadShort();field_8_num_selected_tabs=inStream.ReadShort();field_9_tab_width_ratio=inStream.ReadShort();}
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
public void Close(){if(isOpen){isOpen=false;try{Dump();}finally{try{channel.SetLength(fileLength);}finally{try{channel.Close();}finally{fos.Close();}}}}}
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
public virtual string GetPronunciation(int wordId, char[] surface, int off, int len){return null;}
public string GetPath(){return pathStr;}
public static double Devsq(double[] v){double r = double.NaN;if (v!=null && v.Length >= 1){double m = 0;double s = 0;int n = v.Length;for (int i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for (int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public bool HasPassedThroughNonGreedyDecision() => passedThroughNonGreedyDecision;
public int End(){return End(0);}
public virtual void Traverse(CellHandler handler){int firstRow=range.GetFirstRow();int lastRow=range.GetLastRow();int firstColumn=range.GetFirstColumn();int lastColumn=range.GetLastColumn();int width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();Row currentRow=null;Cell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;++ctx.rowNumber){currentRow=sheet.GetRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;++ctx.colNumber){currentCell=currentRow.GetCell(ctx.colNumber);if(currentCell==null){continue;}if(IsEmpty(currentCell)&&!traverseEmptyCells){continue;}long rowSize=ArithmeticUtils.MulAndCheck((long)ArithmeticUtils.SubAndCheck(ctx.rowNumber,firstRow),(long)width);ctx.ordinalNumber=ArithmeticUtils.AddAndCheck(rowSize,(ctx.colNumber-firstColumn+1));handler.OnCell(currentCell,ctx);}}}
public int getReadIndex(){return _pos;}
public virtual int compareTo(ScoreTerm other){if(this.boost==other.boost)return other.bytes.get().CompareTo(this.bytes.get());else return this.boost.CompareTo(other.boost);}
public int Normalize(char[] s,int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;break;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=delete(s,i,len);i--;break;default:break;}}return len;}
public void Serialize(LittleEndianOutput output){output.WriteShort(_options);}
public DiagnosticErrorListener(bool exactOnly){this.exactOnly = exactOnly;}
public KeySchemaElement(string attributeName, KeyType keyType){AttributeName=attributeName;KeyType=keyType;}
public GetAssignmentResult getAssignment(GetAssignmentRequest request){request = beforeClientExecution(request);return executeGetAssignment(request);}
public bool HasObject(AnyObjectId id){return FindOffset(id) != -1;}
public GroupingSearch SetAllGroups(bool allGroups){ this.allGroups = allGroups; return this; }
public void SetMultiValued(string dimName, bool v){DimConfig ft;fieldTypes.TryGetValue(dimName,out ft);if(ft==null){ft=new DimConfig();fieldTypes[dimName]=ft;}ft.multiValued=v;}
public int getCellsVal(){int size=0;foreach(var c in cells.Keys){Cell e=at(c);if(e.cmd>=0)size++;}return size;}
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options=new InvokeOptions();options.RequestMarshaller=DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller=DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request,options);}
public DeleteLifecyclePolicyResult DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){request = BeforeClientExecution(request);return ExecuteDeleteLifecyclePolicy(request);}
