public void serialize(ILittleEndianOutput out1){out1.writeShort();}
public void addAll(NGit.Util.BlockList<T> src) {if(src.size == 0){return;}for(int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; ++srcDirIdx){addAll(src.directory[srcDirIdx], 0, src.directory[srcDirIdx].length);}if(src.tailBlkIdx != 0){addAll(src.tailBlock, 0, src.tailBlkIdx);}}
void writeByte(byte b){if(blockSize==upto){if(currentBlock!=null){blocks.add(currentBlock);}blockEnd.add(upto);currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId getObjectId(){return objectId;}
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance());return invoke(DeleteDomainEntryResponse.class,request,options);}
public long ramBytesUsed() {return fst == null ? 0 : fst.ramBytesUsed();}
public String getFullMessage(byte[] raw) {int msgB = RawParseUtils.tagMessage(raw, 0);if (msgB < 0) {return "";}Charset enc = RawParseUtils.parseEncoding(raw);return RawParseUtils.decode(enc, raw, msgB, raw.length);}
public POIFSFileSystem() {HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(headerBlock); _documents = new ArrayList<>(); _root = null;}
void init(int address) {slice = pool.buffers[address >> BYTE_BLOCK_SHIFT];assert slice != null;upto = address & BYTE_BLOCK_MASK;offset0 = address;assert upto < slice.length;}
public SubmoduleAddCommand setPath(String path) {this.path = path;return this;}
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(ICharStream stream, int lexState) {this(stream); SwitchTo(lexState);}
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance());return this.<GetShardIteratorResponse>invoke(request, options);}
public ModifyStrategyRequest() {super("", "", "", "", ""); setMethod(MethodType.POST);}
boolean ready() {synchronized(lock){if(in==null){throw new java.io.IOException(" ");}try{return bytes.hasRemaining()||in.available()>0;}catch(java.io.IOException e){return false;}}}
public EscherOptRecord getOptRecord() {return this._optRecord;}
public int read(byte[] buffer,int offset,int length){synchronized(this){if(buffer==null){throw new NullPointerException("buffer");}java.util.Arrays.checkOffsetAndCount(buffer.length,offset,length);if(length==0){return 0;}int copylen=length<count-pos?length:count-pos;for(int i=0;i<copylen;++i){buffer[offset+i]=(byte)this.buffer[pos+i];}pos+=copylen;return copylen;}}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp){this.sentenceOp=sentenceOp;}
void print(String str){write(str != null ? str : Sharpen.StringHelper.getValueOf((Object)null));}
public NotImplementedFunctionException(String functionName, NotImplementedException cause) {super(functionName, cause);} public NotImplementedFunctionException(String functionName) {super(functionName);}
V next() { return nextEntry().value; }
void readBytes(byte[] b,int offset,int len,boolean useBuffer){int available=bufferLength-bufferPosition;if(available>0){if(available>=len){System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;return;}System.arraycopy(buffer,bufferPosition,b,offset,available);bufferPosition+=available;offset+=available;len-=available;}if(len==0)return;if(useBuffer&&len<bufferSize){refill();if(bufferLength<len)throw new EOFException("read past EOF "+len);System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;return;}long after=bufferStart+bufferPosition+len;if(after>length)throw new EOFException("read past EOF "+len);readInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}
public TagQueueResult tagQueue(TagQueueRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(TagQueueRequestMarshaller.getInstance());options.setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance());return invoke(request, options);}
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
public void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " ", culture); if (st.moveNext()) { culture = st.current(); if (st.moveNext()) { culture = st.current() + " " + culture; if (st.moveNext()) ignore = st.current(); } } }
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
public boolean equals(Object obj) {if (!(obj instanceof FacetLabel)) return false;FacetLabel other = (FacetLabel) obj;if (length != other.length) return false;for (int i = length - 1; i >= 0; i--) if (!components[i].equals(other.components[i])) return false;return true;}
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request); return executeGetInstanceAccessDetails(request);}
public HSSFPolygon createPolygon(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(anchor);shape.setAnchor(anchor);shapes.add(shape);onCreate(shape);return shape;}
public String getSheetName(int sheetIndex) {return getBoundSheetRec(sheetIndex).getSheetname();}
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
public void addMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.NumColumns;++j){BlankRecord br=new BlankRecord();br.Row=mbr.Row;br.Column=mbr.FirstColumn+j;br.XFIndex=mbr.getXFAt(j);insertCell(br);}}
public static String quote(String string){StringBuilder sb=new StringBuilder();sb.append("\\");int apos=0;int k;while((k=string.indexOf("\\",apos))>=0){sb.append(string.substring(apos,k));sb.append("\\\\");apos=k+1;}sb.append(string.substring(apos));sb.append("\\");return sb.toString();}
public java.nio.ByteBuffer putInt(int value){throw new java.nio.ReadOnlyBufferException();}
public ArrayPtg(final Object[][] values2d) {final int nColumns = values2d[0].length;final int nRows = values2d.length;_nColumns = (short) nColumns;_nRows = (short) nRows;final Object[] vv = new Object[_nRows * _nColumns];for (int r = 0; r < nRows; ++r) {final Object[] rowData = values2d[r];for (int c = 0; c < nColumns; ++c) {vv[getValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
return " " + _parentQuery + " " + field.toString();
void incRef() { refCount.incrementAndGet(); }
return invoke(UpdateConfigurationSetSendingEnabledResponse.class, request, new InvokeOptions().withRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()));
public int getNextXBATChainOffset() {return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;}
void multiplyByPowerOfTen(int pow10){tp.TenPower=TenPower.getInstance(Math.abs(pow10));if(pow10<0){mulShift(_divisor.tp,_divisorShift.tp);}else{mulShift(_multiplicand.tp,_multiplierShift.tp);}}
public String toString(){StringBuilder builder=new StringBuilder();int length=getLength();for(int i=0;i<length;++i){builder.append(getComponent(i));if(i<length-1){builder.append(java.io.File.separatorChar);}}return builder.toString();}
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher){fetcher.setRoleName(fetcher);}
public void setProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void reset() { if (!first) { ptr = 0; if (!eof) { parseEntry(); } } }
public E previous() {if (iterator.previousIndex() >= start) {return iterator.previous();} else {throw new java.util.NoSuchElementException();}}
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(char[] word, int length) {List<CharsRef> stems = stem(word, length);if (stems.size() < 2) {return stems;}CharArraySet terms = new CharArraySet(dictionary, 8, true);List<CharsRef> deduped = new ArrayList<>();for (CharsRef s : stems) {if (!terms.contains(s)) {deduped.add(s);terms.add(s);}}return deduped;}
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) {request = beforeClientExecution(request);return executeGetGatewayResponses(request);}
public void setPosition(long position) { currentBlockIndex = (int) (position >> blockBits.outerInstance); currentBlock = blocks.outerInstance[currentBlockIndex]; currentBlockUpto = (int) (position & blockMask.outerInstance); }
public long skip(long n){int s=(int)Math.min(available(),Math.max(0L,n));ptr+=s;return s;}
private BootstrapActionConfig bootstrapActionConfig; public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig){this.bootstrapActionConfig=bootstrapActionConfig;} public BootstrapActionConfig getBootstrapActionConfig(){return bootstrapActionConfig;} public void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig){this.bootstrapActionConfig=bootstrapActionConfig;}
void serialize(ILittleEndianOutput out1) {out1.writeShort(field_1_firstRow);out1.writeShort(field_2_lastRow);out1.writeShort(field_3_firstCol);out1.writeShort(field_4_lastCol);out1.writeShort(field_6_author.length());out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE(field_6_author,out1);}else{StringUtil.putCompressedUnicode(field_6_author,out1);}if(field_7_padding!=null){out1.writeByte(Integer.parseInt(field_7_padding));}}
public int lastIndexOf(String string) {return lastIndexOf(string);}
public boolean add(E object) {return addLastImpl();}
public void unsetSection(String section, String subsection) { ConfigSnapshot src, res; do { src = getState(); res = src.unsetSection(section, subsection); } while (!compareAndSetState(src, res)); }
public String getTagName() {return tagName;}
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
boolean remove(Object object) { synchronized (mutex) { return c.remove(object); } }
public TokenStream create(TokenStream input){return new DoubleMetaphoneFilter(input,4,false);}
long length() { return inCoreLength(); }
public void setValue(boolean newValue) { value = newValue; }
Pair(ContentSource oldSource, ContentSource newSource) {this.oldSource = oldSource;this.newSource = newSource;}
int get(int i) {if (i <= count) {throw new IndexOutOfBoundsException();}return entries[i];}
public CreateRepoRequest() {super("cr","2016-06-07","CreateRepo","cr","openAPI");setUriPattern("/repos/[RepoNamespace]/[RepoName]");setMethod(MethodType.PUT);}
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (list.modCount == expectedModCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next = lastLink.next; java.util.LinkedList.Link<ET> previous = lastLink.previous; if (link == lastLink) { pos--; } list._size--; list.modCount++; expectedModCount++; lastLink = null; previous.next = next; next.previous = previous; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public Connection allocateHostedConnection(AllocateHostedConnectionRequest request){request=beforeClientExecution(request);return executeAllocateHostedConnection(request);}
int getBeginIndex(){return start;}
public WeightedTerm[] getTerms(Query query) { return getTerms(query); }
throw new java.nio.ReadOnlyBufferException();
static void decode(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;i++){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&0x03)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&0x0F)<<2)|(byte2>>6);values[valuesOffset++]=byte2&0x3F;}}
public String getHumanishName(String path){if(path==null||path.isEmpty())throw new IllegalArgumentException();String[] elements=path.split("[/\\\\]");if(elements.length==0)throw new IllegalArgumentException();String result=elements[elements.length-1];if(result.equals(Constants.DOT_GIT))result=elements[elements.length-2];else if(result.endsWith(Constants.DOT_GIT_EXT))result=result.substring(0,result.length()-Constants.DOT_GIT_EXT.length());return result;}
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=ListMonitoringExecutionsRequestMarshaller.getInstance();options.responseUnmarshaller=ListMonitoringExecutionsResponseUnmarshaller.getInstance();return invoke(request,options);}
public DescribeJobRequest(String jobId, String vaultName) {this.jobId = jobId; this.vaultName = vaultName;}
public EscherRecord getEscherRecord(int index) {return escherRecords[index];}
public GetApisResponse getApis(GetApisRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetApisRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance());return invoke(request,options);}
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
public TrackingRefUpdate getTrackingRefUpdate() {return trackingRefUpdate;}
void print(boolean b){print(Boolean.toString(b));}
public IQueryNode getChild() { return getChildren()[0]; }
NotIgnoredFilter(int workdirTreeIndex){this.workdirTreeIndex=workdirTreeIndex;}
public AreaRecord(RecordInputStream in1) {field_1_formatFlags = in1.readShort();}
public HTTPS.ProtocolType getProtocol() { return GetThumbnailRequest(" ", " ", " ", " ", " "); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); return invoke(request, options);
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange getOrdRange(String dim){OrdRange result=prefixToOrdRange.get(dim);return result;}
public String toString() {String symbol="";ICharStream inputStream=getInputStream();int startIndex=this.startIndex;if(startIndex>=0&&startIndex<inputStream.size()){symbol=inputStream.getText(Interval.of(startIndex,startIndex));}else{symbol="<EOF>";}return String.format(Locale.getDefault(),"%s('%s')",LexerNoViableAltException.class.getSimpleName(),Utils.escapeWhitespace(symbol,false));}
return peekFirstImpl((E) peek());
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance());return invoke(request, options);
public SparseIntArray(int initialCapacity) {initialCapacity = android.util.ArrayUtils.idealIntArraySize(initialCapacity);mKeys = new int[initialCapacity];mValues = new int[initialCapacity];mSize = 0;}
return new HyphenatedWordsFilter(createTokenStream(input));
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance());return invoke(request, options);}
public RandomAccessFile(String fileName, String mode){throw new UnsupportedOperationException();}
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public String toHex(int value) { return Long.toHexString((long) value); }
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public HSSFColor getColor(short index) {if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) {return HSSFColor.HSSFColorPredefined.AUTOMATIC.getInstance();} else {byte[] b = palette.getColor(index);if (b != null) {return new CustomColor(index, b);}return null;}}
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) {throw new NotImplementedFunctionException();}
public void serialize(LittleEndianOutput out){out.writeShort((short)field_1_number_crn_records);out.writeShort((short)field_2_sheet_table_index);}
public DescribeDBEngineVersionsResult describeDBEngineVersions(DescribeDBEngineVersionsRequest request) {request = beforeClientExecution(request);return executeDescribeDBEngineVersions(request);}
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {byte[] result = new byte[2 * length];int resultIndex = 0;int end = offset + length;for (int i = offset; i < end; ++i) {char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
public UploadArchiveResponse uploadArchive(UploadArchiveRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(UploadArchiveRequestMarshaller.getInstance());options.setResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance());return invoke(request, options);}
public List<IToken> getHiddenTokensToLeft(int tokenIndex) {return getHiddenTokensToLeft(tokenIndex, -1);}
@Override public boolean equals(Object obj) {if (this == obj) return true; if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) {if (other.m_term != null) return false;} else if (!m_term.equals(other.m_term)) return false; return true;}
private SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<SpanQuery>(); for (Map.Entry<SpanQuery,Float> wsqEntry : wsq.entrySet()) { SpanQuery key = wsqEntry.getKey(); key.setBoost(wsqEntry.getValue()); spanQueries.add(key); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
{ return new StashCreateCommand(stashCreate()); }
public Field fieldInfo(String fieldName) {Field ret = byName.get(fieldName); return ret;}
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance());return invoke(request, options);
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return invoke(request, options);
return invoke(request, new InvokeOptions(SetInstanceProtectionRequestMarshaller.getInstance(), SetInstanceProtectionResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return invoke(request, options);
public void add(char[] output,int offset,int len,int endOffset,int posLength){if(outputs==null){outputs=new CharsRef[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF)];}else if(outputs.length==count){CharsRef[] next=new CharsRef[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF)];System.arraycopy(outputs,0,next,0,outputs.length);outputs=next;}if(endOffsets==null){endOffsets=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];}else if(endOffsets.length==count){int[] next=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(endOffsets,0,next,0,endOffsets.length);endOffsets=next;}if(posLengths==null){posLengths=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];}else if(posLengths.length==count){int[] next=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(posLengths,0,next,0,posLengths.length);posLengths=next;}CharsRef t=outputs[count];if(t==null){t=new CharsRef();outputs[count]=t;}t.copyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;++count;}
private HTTPS.ProtocolType protocol;
boolean exists() { return objects.exists(); }
new java.io.FilterOutputStream(out);
setMethod(MethodType.PUT); setUriPattern("/clusters/{clusterId}/scale");
public DataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2){return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);}
InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance());return invoke(request, options);
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
public void setSharedFormula(boolean flag) {field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);}
