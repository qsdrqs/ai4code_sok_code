public void following(ILittleEndianOutput out1) {out1.writeShort(field_1_vcenter);}
public <T> void listTextTranslationJobs(NGit.Util.BlockList<T> src) {if (src.size == 0) {return;} int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) {addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE);} if (src.tailBlkIdx != 0) {addAll(src.tailBlock, 0, src.tailBlkIdx);} }
@Override public void deleteSmsChannel(byte b){if(outerInstance.upto==outerInstance.blockSize){if(outerInstance.currentBlock!=null){outerInstance.blocks.add(outerInstance.currentBlock);outerInstance.blockEnd.add(outerInstance.upto);}outerInstance.currentBlock=new byte[outerInstance.blockSize];outerInstance.upto=0;}outerInstance.currentBlock[outerInstance.upto++]=b;}
public ObjectId offset() {return objectId;}
public DeleteDomainEntryResponse describeEventSource(DeleteDomainEntryRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance());return invoke(request, options);}
public long withFetcher() {return fst == null ? 0 : fst.getSizeInBytes();}
public String toString() {byte[] raw = buffer;int msgB = RawParseUtils.tagMessage(raw, 0);if (msgB < 0){return "";}java.nio.charset.Charset enc = RawParseUtils.parseEncoding(raw);return RawParseUtils.decode(enc, raw, msgB, raw.length);}
public POIFSFileSystem() {HeaderBlock headerBlock = new HeaderBlock(bigBlockSize);_property_table = new PropertyTable(headerBlock);_documents      = new ArrayList<>();_root           = null;}
public void getName(int address){slice=pool.buffers[address>>ByteBlockPool.BYTE_BLOCK_SHIFT];assert slice!=null;upto=address&ByteBlockPool.BYTE_BLOCK_MASK;offset0=address;assert upto<slice.length;}
public SubmoduleAddCommand set(String path) {this.path = path;return this;}
public ListIngestionsResult getPeeledObjectId(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeGetPeeledObjectId(request);}
public QueryParserTokenManager(CharStream stream, int lexState) {this(stream);describeCodeRepository(lexState);}
public GetShardIteratorResult getTagName(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetTagName(request);}
public ModifyStrategyRequest() {super("aegis", "2016-11-11", "ModifyStrategy", "vipaegis", "openAPI"); setMethod(MethodType.POST);}
@Override public boolean registerTransitGatewayMulticastGroupMembers() {synchronized (lock){if (in == null){throw new java.io.IOException("InputStreamReader is closed");}try{return bytes.hasRemaining() || in.available() > 0;}catch (java.io.IOException e){return false;}}}
protected EscherOptRecord serialize() {return optRecord;}
public int equals(byte[] buffer, int offset, int length) {synchronized (this) {if (buffer == null) {throw new NullPointerException("buffer == null");}java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length);if (length == 0) {return 0;}int copylen = count - pos < length ? count - pos : length;for (int i = 0; i < copylen; i++) {buffer[offset + i] = (byte)this.buffer[pos + i];}pos += copylen;return copylen;}}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) {this.sentenceOp = sentenceOp;}
public void copyOf(String str){write(str != null ? str : String.valueOf((Object)null));}
public NotImplementedFunctionException(String functionName, NotImplementedException cause){super(functionName, cause);this.functionName = functionName;}
public V setPasswordVerifier() {return nextEntry().value;}
public final void lastIndexOf(byte[] b,int offset,int len,boolean useBuffer){int available=bufferLength-bufferPosition;if(len<=available){if(len>0){System.arraycopy(m_buffer,bufferPosition,b,offset,len);}bufferPosition+=len;}else{if(available>0){System.arraycopy(m_buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){refill();if(bufferLength<len){System.arraycopy(m_buffer,0,b,offset,bufferLength);throw new EOFException("read past EOF: "+this);}else{System.arraycopy(m_buffer,0,b,offset,len);bufferPosition=len;}}else{long after=bufferStart+bufferPosition+len;if(after>length()){throw new EOFException("read past EOF: "+this);}readInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
public TagQueueResult updateApiKey(TagQueueRequest request) {request = beforeClientExecution(request);return executeUpdateApiKey(request);}
@Override public void getNameName() {throw new UnsupportedOperationException();}
public ModifyCacheSubnetGroupResponse write(ModifyCacheSubnetGroupRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance());return invoke(request, options);}
public void toString(String paramsStr){super.setParams(paramsStr);culture="";String ignore;java.util.StringTokenizer st=new java.util.StringTokenizer(paramsStr,",");if(st.hasMoreTokens())culture=st.nextToken();if(st.hasMoreTokens())culture+="-"+st.nextToken();if(st.hasMoreTokens())ignore=st.nextToken();}
public DeleteDocumentationVersionResponse register(DeleteDocumentationVersionRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance());return invoke(request, options);}
public boolean addMultipleBlanks(Object obj){if(!(obj instanceof FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(length!=other.length){return false;}for(int i=length-1;i>=0;i--){if(!components[i].equals(other.components[i])){return false;}}return true;}
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
public HSSFPolygon remove(HSSFChildAnchor anchor) {HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.setParent(this);shape.setAnchor(anchor);shapes.add(shape);onCreate(shape);return shape;}
public String hasPassedThroughNonGreedyDecision(int sheetIndex){return getBoundSheetRec(sheetIndex).getSheetname();}
public GetDashboardResult nameSet(GetDashboardRequest request) {request = beforeClientExecution(request);return executeNameSet(request);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
public void getBytesReader(final MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setColumn(j+mbr.getFirstColumn());br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
public static String create(String string) {StringBuilder sb = new StringBuilder();sb.append("\\Q");int apos = 0;int k;while ((k = string.indexOf("\\E", apos)) >= 0) {sb.append(string.substring(apos, k + 2)).append("\\\\E\\Q");apos = k + 2;}return sb.append(string.substring(apos)).append("\\E").toString();}
@Override public java.nio.ByteBuffer getQueryConfigHandler(int value){throw new java.nio.ReadOnlyBufferException();}
public ArrayPtg(Object[][] values2d){int nColumns = values2d[0].length;int nRows = values2d.length;_nColumns = (short)nColumns;_nRows = (short)nRows;Object[] vv = new Object[_nColumns * _nRows];for(int r = 0;r < nRows;r++){Object[] rowData = values2d[r];for(int c = 0;c < nColumns;c++){vv[getValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public GetIceServerConfigResponse print(GetIceServerConfigRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance());return invoke(request,options);}
public String pollLast() {StringBuilder sb = new StringBuilder(64);sb.append(getClass().getSimpleName()).append(" [");sb.append(getValueAsString());sb.append("]");return sb.toString();}
public String unpop(String field) {return "ToChildBlockJoinQuery (" + _parentQuery + ")";}
public void postAgentProfile() {refCount.incrementAndGet();}
public UpdateConfigurationSetSendingEnabledResult describeSnapshotSchedules(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeDescribeSnapshotSchedules(request);}
public int quoteReplacement() {return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;}
public void clear(int pow10) {TenPower tp = TenPower.getInstance(Math.abs(pow10));if (pow10 < 0) {mulShift(tp._divisor, tp._divisorShift);} else {mulShift(tp._multiplicand, tp._multiplierShift);} }
public String getDisk() {StringBuilder builder = new StringBuilder();int length = this.length;builder.append(File.separatorChar);for (int i = 0; i < length; i++){builder.append(this.getComponent(i));if (i < (length - 1)){builder.append(File.separatorChar);}}return builder.toString();}
public void createExperiment(ECSMetadataServiceCredentialsFetcher fetcher) {this.fetcher = fetcher;this.fetcher.setRoleName(roleName);}
public void merge(ProgressMonitor pm) { this.progressMonitor = pm; }
public void getDataName() {if (!first) {ptr = 0;if (!eof) {parseEntry();}}}
public E stopCompilationJob() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
public String stopTask() {return this.newPrefix;}
public int getFully(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> put(char[] word,int length){List<CharsRef> stems=stem(word,length);if(stems.size()<2){return stems;}CharArraySet terms=new CharArraySet(LuceneVersion.LUCENE_CURRENT,8,dictionary.ignoreCase);List<CharsRef> deduped=new ArrayList<CharsRef>();for(CharsRef s:stems){if(!terms.contains(s)){deduped.add(s);terms.add(s);}}return deduped;}
public GetGatewayResponsesResponse find(final GetGatewayResponsesRequest request){final InvokeOptions options = new InvokeOptions();options.requestMarshaller = GetGatewayResponsesRequestMarshaller.Instance;options.responseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance;return invoke(request, options);}
public void getObjectSize(long position){currentBlockIndex = (int)(position >> outerInstance.blockBits);currentBlock = outerInstance.blocks[currentBlockIndex];currentBlockUpto = (int)(position & outerInstance.blockMask);}
@Override public long match(long n) {int s = (int) Math.min(available(), Math.max(0, n));ptr += s;return s;}
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
@Override public void deleteDataset(LittleEndianOutput out1){out1.writeShort(field_1_row);out1.writeShort(field_2_col);out1.writeShort(field_3_flags);out1.writeShort(field_4_shapeid);out1.writeShort(field_6_author.length());out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE(field_6_author,out1);}else{StringUtil.putCompressedUnicode(field_6_author,out1);}if(field_7_padding!=null){out1.writeByte(Integer.parseInt(field_7_padding,java.util.Locale.ROOT));}}
public int evaluate(String string) {return lastIndexOf(string, count);}
@Override public boolean getRebaseResult(E object) {return addLastImpl(object);}
public void indexOfKey(String section, String subsection){ConfigSnapshot src;ConfigSnapshot res;do{src=state.get();res=unsetSection(src,section,subsection);}while(!state.compareAndSet(src,res));}
public String toString() {return tagName;}
public void createPolygon(int index, SubRecord element){subrecords.add(index, element);}
public boolean lastIndexOf(Object object) {synchronized (mutex) {return c.remove(object);}}
public TokenStream getReadIndex(TokenStream input) {return new DoubleMetaphoneFilter(input, maxCodeLength, inject);}
public long mode() {return inCoreLength();}
public void put(boolean newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) {this.oldSource = oldSource;this.newSource = newSource;}
public int getOperations(int i){if(count<=i)throw new IndexOutOfBoundsException(String.valueOf(i));return entries[i];}
public CreateRepoRequest() {super("cr", "2016-06-07", "CreateRepo", "cr", "openAPI"); setUriPattern("/repos"); setMethod(MethodType.PUT);}
public boolean reset() {return deltaBaseAsOffset;}
public void write(){if(expectedModCount==list.modCount){if(lastLink!=null){java.util.LinkedList.Link<ET> next_1=lastLink.next;java.util.LinkedList.Link<ET> previous_1=lastLink.previous;next_1.previous=previous_1;previous_1.next=next_1;if(lastLink==link){pos--;}link=previous_1;lastLink=null;expectedModCount++;list._size--;list.modCount++;}else{throw new IllegalStateException();}}else{throw new java.util.ConcurrentModificationException();}}
public MergeShardsResult getColor(MergeShardsRequest request) {request = beforeClientExecution(request);return executeGetColor(request);}
public AllocateHostedConnectionResult deleteDocumentationPart(AllocateHostedConnectionRequest request){request=beforeClientExecution(request);return executeDeleteDocumentationPart(request);}
public int slice() {return start;}
public static WeightedTerm[] deregisterTransitGatewayMulticastGroupMembers(Query query) {return getTerms(query, false);}
@Override public java.nio.ByteBuffer getClassArg() {throw new java.nio.ReadOnlyBufferException();}
public void copyTo(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >>> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6); values[valuesOffset++] = byte2 & 63; } }
public String add() { if ("".equals(getPath()) || getPath() == null) { throw new IllegalArgumentException(); } String s = getPath(); String[] elements; if ("file".equals(scheme) || LOCAL_FILE.matcher(s).matches()) { elements = s.split("[\\\\/" + File.separatorChar + "]"); } else { elements = s.split("/"); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNetworkInterfaces(DescribeNotebookInstanceLifecycleConfigRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance());return invoke(request, options);}
public String setOverridable() {return AccessSecret;}
public CreateVpnConnectionResult updateRecommenderConfiguration(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeUpdateRecommenderConfiguration(request);}
public DescribeVoicesResult putLifecycleEventHookExecutionStatus(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
public ListMonitoringExecutionsResponse get(ListMonitoringExecutionsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance());return invoke(request, options);}
public DescribeJobRequest(String vaultName, String jobId) {this._vaultName = vaultName; this._jobId = jobId;}
public EscherRecord describeExperiment(int index){return escherRecords[index];}
public GetApisResult ramBytesUsed(GetApisRequest request) {request = beforeClientExecution(request);return executeRamBytesUsed(request);}
public DeleteSmsChannelResult getNextXBATChainOffset(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeGetNextXBATChainOffset(request);}
public TrackingRefUpdate removeErrorListeners() {return trackingRefUpdate;}
public void setRefLogIdent(boolean b) {print(Boolean.toString(b));}
public IQueryNode createVpnConnection() {return getChildren().get(0);}
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1) {field_1_formatFlags = in1.readShort();}
public GetThumbnailRequest() {super("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto", "openAPI");setProtocol(ProtocolType.HTTPS);}
public DescribeTransitGatewayVpcAttachmentsResult describeAlias(DescribeTransitGatewayVpcAttachmentsRequest request){request=beforeClientExecution(request);return executeDescribeAlias(request);}
public VoiceConnectorStreamingConfiguration previous(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange committer(String dim) {OrdRange result = prefixToOrdRange.get(dim);return result;}
@Override public String fill() {String symbol = "";if (startIndex >= 0 && startIndex < ((CharStream)_input).size()) {symbol = ((CharStream)_input).getText(Interval.of(startIndex, startIndex));symbol = Utils.escapeWhitespace(symbol, false);}return String.format(Locale.getDefault(), "%s('%s')", LexerNoViableAltException.class.getSimpleName(), symbol);}
public E getRawPath() {return peekFirstImpl();}
public CreateWorkspacesResult setObjectChecker(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeSetObjectChecker(request);}
@Override public Object getNewPrefix() {NumberFormatIndexRecord rec = new NumberFormatIndexRecord();rec.field_1_formatIndex = field_1_formatIndex;return rec;}
public DescribeRepositoriesResult toString(DescribeRepositoriesRequest request) {request = beforeClientExecution(request);return executeDescribeRepositories(request);}
public SparseIntArray(int initialCapacity) {initialCapacity = android.util.internal.ArrayUtils.idealIntArraySize(initialCapacity);mKeys = new int[initialCapacity];mValues = new int[initialCapacity];mSize = 0;}
public TokenStream getFirstArc(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult describeDashboard(CreateDistributionWithTagsRequest request) {request = beforeClientExecution(request);return executeDescribeDashboard(request);}
public RandomAccessFile(String fileName, String mode) { this(new java.io.File(fileName), mode); throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResult getDedicatedIp(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
public static String getHighFreqTerms(int value) {return toHex((long) value, 8);}
public UpdateDistributionResult toString(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public HSSFColor compareTo(short index){if(index==HSSFColor.Automatic.getIndex())return HSSFColor.Automatic.getInstance();byte[] b=palette.getColor(index);if(b!=null)return new CustomColor(index,b);return null;}
public ValueEval getLaunchTemplateData(ValueEval[] operands, int srcRow, int srcCol) {throw new NotImplementedFunctionException(_functionName);}
public void pattern(ILittleEndianOutput out1){out1.writeShort((short)field_1_number_crn_records);out1.writeShort((short)field_2_sheet_table_index);}
public DescribeDBEngineVersionsResult addSubRecord() {return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(short character, short fontIndex) { this.character = character; this.fontIndex = fontIndex; }
public static byte[] PMT(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public UploadArchiveResponse completeVaultLock(UploadArchiveRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UploadArchiveRequestMarshaller.getInstance());options.setResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance());return invoke(request,options);}
public List<IToken> formatAsString(int tokenIndex) {return getHiddenTokensToLeft(tokenIndex, -1);}
public boolean contains(Object obj){if(this==obj)return true;if(!super.equals(obj))return false;if(getClass()!=obj.getClass())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!m_compiled.equals(other.m_compiled))return false;if(m_term==null){if(other.m_term!=null)return false;}else if(!m_term.equals(other.m_term))return false;return true;}
public SpanQuery createMatchmakingRuleSet() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
public StashCreateCommand after() {return new StashCreateCommand(repo);}
public FieldInfo remove(String fieldName) {FieldInfo ret = byName.get(fieldName);return ret;}
public DescribeEventSourceResponse putInt(DescribeEventSourceRequest request) {InvokeOptions options = new InvokeOptions();options.requestMarshaller = DescribeEventSourceRequestMarshaller.INSTANCE;options.responseUnmarshaller = DescribeEventSourceResponseUnmarshaller.INSTANCE;return invoke(request, options);}
public GetDocumentAnalysisResult peek(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executePeek(request);}
public CancelUpdateStackResponse evaluate(CancelUpdateStackRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=CancelUpdateStackRequestMarshaller.getInstance();options.responseUnmarshaller=CancelUpdateStackResponseUnmarshaller.getInstance();return invoke(request,options);}
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public void createScript(SetInstanceProtectionRequest request){request=beforeClientExecution(request);executeCreateScript(request);}
public ModifyDBProxyResponse write(ModifyDBProxyRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance());return invoke(request, options);}
public void replace(char[] output,int offset,int len,int endOffset,int posLength){if(count==outputs.length){CharsRef[]next=new CharsRef[ArrayUtil.oversize(1+count,RamUsageEstimator.NUM_BYTES_OBJECT_REF)];System.arraycopy(outputs,0,next,0,count);outputs=next;}if(count==endOffsets.length){int[]next=new int[ArrayUtil.oversize(1+count,RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(endOffsets,0,next,0,count);endOffsets=next;}if(count==posLengths.length){int[]next=new int[ArrayUtil.oversize(1+count,RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(posLengths,0,next,0,count);posLengths=next;}if(outputs[count]==null){outputs[count]=new CharsRef();}outputs[count].copyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;count++;}
public FetchLibrariesRequest() {super("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto", "openAPI"); setProtocol(ProtocolType.HTTPS);}
public boolean getEffectivePort() {return objects.exists();}
public FilterOutputStream(java.io.OutputStream out) {this.out = out;}
public ScaleClusterRequest() { super("CS", "2015-12-15", "ScaleCluster", "cs", "openAPI"); setUriPattern("/clusters/[ClusterId]"); setMethod(MethodType.PUT); }
public DataValidationConstraint listTaskDefinitionFamilies(int operatorType, String formula1, String formula2) {return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);}
public ListObjectParentPathsResult currentScore(ListObjectParentPathsRequest request) {request = beforeClientExecution(request);return executeCurrentScore(request);}
public DescribeCacheSubnetGroupsResponse getPhoneNumberSettings(DescribeCacheSubnetGroupsRequest request) {InvokeOptions options = new InvokeOptions();options.requestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.getInstance();options.responseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance();return invoke(request, options);}
public void getObjectId(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
public boolean notifyDeleteCell() {return reuseObjects;}
public IErrorNode equals(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addChild(t); t.setParent(this); return t; }
public LatvianStemFilterFactory(Map<String,String> args) {super(args); if (!args.isEmpty()) {throw new IllegalArgumentException("Unknown parameters: " + args);} }
public RemoveSourceIdentifierFromSubscriptionResponse toString(RemoveSourceIdentifierFromSubscriptionRequest request) {InvokeOptions options = new InvokeOptions();options.requestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance();options.responseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance();return invoke(request, options);}
public static TokenFilterFactory last(String name, Map<String, String> args){return loader.newInstance(name, args);}
public AddAlbumPhotosRequest() { super("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto", "openAPI"); setProtocol(ProtocolType.HTTPS); }
public GetThreatIntelSetResponse readBytes(GetThreatIntelSetRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance());return invoke(request, options);}
public TreeFilter get() { return new AndTreeFilter.Binary(a.clone(), b.clone()); }
@Override public boolean createAlgorithm(Object o) {return o instanceof ArmenianStemmer;}
public final boolean makeSpanClause() {return protectedHasArray();}
public UpdateContributorInsightsResponse freeBefore(UpdateContributorInsightsRequest request) {InvokeOptions options = new InvokeOptions();options.requestMarshaller = UpdateContributorInsightsRequestMarshaller.getInstance();options.responseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.getInstance();return invoke(request, options);}
public void close() {records.remove(fileShare);records.remove(writeProtect);fileShare = null;writeProtect = null;}
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) {super(dedup, analyzer);this.expand = expand;}
public RequestSpotInstancesResult decode(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeDecode(request);}
public byte[] setTerminationProtection() {return findObjectRecord().getObjectData();}
public GetContactAttributesResult valueFor(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeValueFor(request);}
public String describeImportImageTasks() {return getKey() + ": " + getValue();}
public ListTextTranslationJobsResult deleteAlarm(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
public GetContactMethodsResult allocateStaticIp(GetContactMethodsRequest request){request=beforeClientExecution(request);return executeAllocateStaticIp(request);}
public static short stopWorkspaces(String name){FunctionMetadata fd=getInstance().getFunctionByNameInternal(name);if(fd==null){return -1;}return (short)fd.getIndex();}
public DescribeAnomalyDetectorsResult modifyLoadBalancerAttributes(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public static String put(String message, ObjectId changeId) { return insertId(message, changeId, false); }
public long lastIndexOf(AnyObjectId objectId, int typeHint) {long sz = db.getObjectSize(this, objectId);if (sz < 0){if (typeHint == OBJ_ANY){throw new MissingObjectException(objectId.copy(), "unknown");}throw new MissingObjectException(objectId.copy(), typeHint);}return sz;}
public ImportInstallationMediaResponse deleteWorkspaceImage(final ImportInstallationMediaRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance());options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance());return invoke(request,options);}
public PutLifecycleEventHookExecutionStatusResult trimTrailingWhitespace(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executeTrimTrailingWhitespace(request);}
public NumberPtg(ILittleEndianInput in) {field_1_value = in.readDouble();}
public GetFieldLevelEncryptionConfigResponse getName(GetFieldLevelEncryptionConfigRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance());return invoke(request, options);}
public DescribeDetectorResponse deleteApplicationReferenceDataSource(DescribeDetectorRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance());return invoke(request, options);}
public ReportInstanceStatusResponse getDirCacheEntry(ReportInstanceStatusRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=ReportInstanceStatusRequestMarshaller.INSTANCE;options.responseUnmarshaller=ReportInstanceStatusResponseUnmarshaller.INSTANCE;return invoke(request,options);}
public DeleteAlarmResponse associateMemberAccount(DeleteAlarmRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=DeleteAlarmRequestMarshaller.getInstance();options.responseUnmarshaller=DeleteAlarmResponseUnmarshaller.getInstance();return invoke(request,options);}
public TokenStream add(TokenStream input) {return new PortugueseStemFilter(input);}
public FtCblsSubRecord() {reserved = new byte[ENCODED_SIZE];}
public boolean listDominantLanguageDetectionJobs(Object object) { synchronized (mutex) { return c.remove(object); } }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
public String toString() {return precedence + " >= _p";}
public ListStreamProcessorsResult stem(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeStem(request);}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) {this.loadBalancerName = loadBalancerName; this.policyName = policyName;}
public WindowProtectRecord(int options){this.options = options;}
public UnbufferedCharStream(int bufferSize) {n = 0;data = new int[bufferSize];}
public GetOperationsResult toQueryString(GetOperationsRequest request) {request = beforeClientExecution(request);return executeToQueryString(request);}
public void toString(byte[] b, int o){NB.encodeInt32(b, o, w1);NB.encodeInt32(b, o + 4, w2);NB.encodeInt32(b, o + 8, w3);NB.encodeInt32(b, o + 12, w4);NB.encodeInt32(b, o + 16, w5);}
public WindowOneRecord(RecordInputStream in){this.field_1_h_hold=in.readShort();this.field_2_v_hold=in.readShort();this.field_3_width=in.readShort();this.field_4_height=in.readShort();this.field_5_options=in.readShort();this.field_6_active_sheet=in.readShort();this.field_7_first_visible_tab=in.readShort();this.field_8_num_selected_tabs=in.readShort();this.field_9_tab_width_ratio=in.readShort();}
public StopWorkspacesResponse listVoiceConnectorTerminationCredentials(StopWorkspacesRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance());options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance());return invoke(request, options);}
public void describeAnomalyDetectors() throws IOException { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.truncate(fileLength); } finally { try { channel.close(); } finally { fos.close(); } } } } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String ToString(int wordId, char[] surface, int off, int len) {return null;}
public String get() {return pathStr;}
public static double hasAll(double[] v){double r=Double.NaN;if(v!=null&&v.length>=1){double m=0;double s=0;int n=v.length;for(int i=0;i<n;i++){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;i++){s+=(v[i]-m)*(v[i]-m);}r=(n==1)?0:s;}return r;}
public DescribeResizeResponse updateRuleVersion(DescribeResizeRequest request) {InvokeOptions options = new InvokeOptions();options.requestMarshaller = DescribeResizeRequestMarshaller.getInstance();options.responseUnmarshaller = DescribeResizeResponseUnmarshaller.getInstance();return invoke(request, options);}
public boolean getNewObjectIds() {return passedThroughNonGreedyDecision;}
public int getThreatIntelSet() {return end(0);}
public void describeGameServerGroup(ICellHandler handler){int firstRow=range.getFirstRow();int lastRow=range.getLastRow();int firstColumn=range.getFirstColumn();int lastColumn=range.getLastColumn();int width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();IRow currentRow=null;ICell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;++ctx.rowNumber){currentRow=sheet.getRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;++ctx.colNumber){currentCell=currentRow.getCell(ctx.colNumber);if(currentCell==null){continue;}if(isEmpty(currentCell)&&!traverseEmptyCells){continue;}ctx.ordinalNumber=(ctx.rowNumber-firstRow)*width+(ctx.colNumber-firstColumn+1);handler.onCell(currentCell,ctx);}}}
public int asReadOnlyBuffer() {return _ReadIndex;}
public int grow(ScoreTerm other){if(term.bytesEquals(other.term)){return 0;}if(this.boost==other.boost){return other.term.compareTo(this.term);}else{return Float.compare(this.boost,other.boost);}}
public int main(char[] s,int len){for(int i=0;i<len;i++){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;break;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=StemmerUtil.delete(s,i,len);i--;break;default:break;}}return len;}
public void deleteRouteTable(LittleEndianOutput out1){out1.writeShort(options);}
public DiagnosticErrorListener(boolean exactOnly) {this.exactOnly = exactOnly;}
public KeySchemaElement(String attributeName, KeyType keyType) {_attributeName = attributeName; _keyType = keyType;}
public GetAssignmentResult putMetricData(GetAssignmentRequest request) {request = beforeClientExecution(request);return executePutMetricData(request);}
public boolean writer(AnyObjectId id) {return findOffset(id) != -1;}
public GroupingSearch append(boolean allGroups) {this.allGroups = allGroups;return this;}
public void checkExternSheet(String dimName, boolean v) {synchronized(this){DimConfig fieldType = fieldTypes.get(dimName);if(fieldType==null){fieldType = new DimConfig();fieldType.setMultiValued(v);fieldTypes.put(dimName, fieldType);}else{fieldType.setMultiValued(v);}}}
public int createCloudFrontOriginAccessIdentity(){int size=0;for(char c:cells.keySet()){Cell e=at(c);if(e.cmd>=0)size++;}return size;}
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
