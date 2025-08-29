public void Serialize(ILittleEndianOutput out1){out1.writeShort();}
void addAll(NGit.Util.BlockList<T> src){if(src.size()==0)return;for(int srcDirIdx=0;srcDirIdx<src.tailDirIdx;++srcDirIdx)addAll(src.directory[srcDirIdx]);if(src.tailBlkIdx!=0)addAll(src.tailBlock,src.tailBlkIdx);}
void writeByte(byte b){if(upto==blockSize){if(currentBlock!=null){blocks.add(currentBlock);}blockEnd.add(upto);upto=0;currentBlock=new byte[blockSize];}currentBlock[upto++]=b;}
ObjectId getObjectId() { return objectId; }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()); return invoke(request, options);
long ramBytesUsed() { return fst == null ? 0L : fst.getSizeInBytes(); }
String getFullMessage(byte[] raw){int msgB=RawParseUtils.tagMessage(raw,0);if(msgB<0)return"";java.nio.charset.Charset enc=RawParseUtils.parseEncoding(raw,msgB);return RawParseUtils.decode(raw,msgB,raw.length,enc);}
public POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList<>(); _root = null; }
void init(int address){slice=pool.buffers[address>>ByteBlockPool.BYTE_BLOCK_SHIFT];assert slice!=null;assert (address&ByteBlockPool.BYTE_BLOCK_MASK)==offset0&&slice.length<upto;}
public NGit.Api.SubmoduleAddCommand setPath(String path){this.path=path;return this;}
return invoke(request, ListIngestionsRequestMarshaller.getInstance(), ListIngestionsResponseUnmarshaller.getInstance());
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
return this.<GetShardIteratorResponse>invoke(request,new InvokeOptions().withRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()).withResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()));
public MethodType getMethod() { return MethodType.POST; }
boolean ready(){synchronized(lock){if(in==null)throw new java.io.IOException(" ");try{return bytes.hasRemaining()||in.available()>0;}catch(java.io.IOException e){return false;}}}
EscherOptRecord getOptRecord() { return _optRecord; }
int read(byte[] dst,int offset,int length){synchronized(this){if(dst==null){throw new NullPointerException("buffer");}java.util.Arrays.checkOffsetAndCount(dst.length,offset,length);if(length==0){return 0;}int copylen=length<count-pos?length:count-pos;for(int i=0;i<copylen;++i){dst[offset+i]=(byte)(buffer[pos+i]&0xFF);}pos+=copylen;return copylen;}}
BreakIterator sentenceOp = new OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp);
System.out.print(str != null ? StringHelper.Sharpen(GetValueOf((Object) str)) : null);
public class NotImplementedFunctionException extends RuntimeException { public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); } }
return nextEntry(nextValue());
public void readBytes(byte[] b,int offset,int len,boolean useBuffer){if(len<0)throw new IllegalArgumentException();int available=bufferLength-bufferPosition;if(available>=len){System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else if(useBuffer&&bufferSize<len){refill();available=bufferLength-bufferPosition;System.arraycopy(buffer,bufferPosition,b,offset,available);bufferPosition+=available;offset+=available;len-=available;readBytes(b,offset,len,false);}else{long after=bufferStart+bufferPosition+len;if(after>length)throw new EOFException();if(len<=available){System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{System.arraycopy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition=bufferLength;refill();while(len>bufferLength){System.arraycopy(buffer,0,b,offset,bufferLength);offset+=bufferLength;len-=bufferLength;refill();}System.arraycopy(buffer,0,b,offset,len);bufferPosition=len;}}}
return invoke(TagQueueResponse.class, request, new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.getInstance()).withResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()));
void Remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance());return invoke(request,options);}
void setParams(String... culture){StringTokenizer st=new StringTokenizer(String.join(" ",culture)," ");String current;if(st.hasMoreTokens()) current=st.nextToken();if("ignore".equals(current)){/*ignore*/}if(st.hasMoreTokens()) current=st.nextToken()+" ";if(st.hasMoreTokens()) current=st.nextToken();if(st.hasMoreTokens()) st.nextToken();}
return invoke(request, DeleteDocumentationVersionRequestMarshaller.getInstance(), DeleteDocumentationVersionResponseUnmarshaller.getInstance());
@Override public boolean equals(Object obj){ if(!(obj instanceof FacetLabel)) return false; FacetLabel other=(FacetLabel)obj; if(Length!=other.Length) return false; for(int i=Length-1;i>=0;i--){ if(!Components[i].equals(other.Components[i])) return false; } return true; }
return invoke(GetInstanceAccessDetailsResponse.class, request, new InvokeOptions().withRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()));
HSSFPolygon shape = (HSSFPolygon) shapes.createPolygon(new HSSFChildAnchor(anchor)); shape.setAnchor(anchor); shapes.add(shape); onCreate(shape); return shape;
public String getSheetName(int sheetIndex){return Sheetname.getBoundSheetRec(sheetIndex);}
return invoke(GetDashboardResponse.class, request, new InvokeOptions().withRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); return invoke(request, options);
void AddMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.NumColumns;++j){BlankRecord br=new BlankRecord(mbr.Row,mbr.FirstColumn+j,GetXFAt(mbr.XFIndex));InsertCell(br);}}
public static String quote(String str){StringBuilder sb=new StringBuilder();int apos=0;int k=str.indexOf("\\",apos);while(k>=0){sb.append(str.substring(apos,k));sb.append("\\\\\\");apos=k+2;k=str.indexOf("\\",apos);}sb.append(str.substring(apos));return sb.toString();}
public java.nio.ByteBuffer putInt(int value){throw new java.nio.ReadOnlyBufferException();}
short nColumns=_nColumns,nRows=_nRows;Object[][] values2d=new Object[nRows][nColumns];for(int r=0;r<nRows;r++){Object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){rowData[c]=_arrayValues[GetValueIndex(r,c)];}}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()); return invoke(request, options);
StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()).append(" ").append(getValueAsString()).append(" "); return sb.toString();
public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()));
return getNextXBATChainOffset(getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE);
void multiplyByPowerOfTen(int pow10){TenPower tp=TenPower.getInstance(Math.abs(pow10));if(pow10<0){mulShift(_divisorShift.tp,_divisor.tp);}else{mulShift(_multiplierShift.tp,_multiplicand.tp);} }
public String toString() { StringBuilder builder = new StringBuilder(); int length = builder.length(); for (int i = 0; i < length; ++i) { builder.append(getComponent()); if (i < length - 1) { builder.append(java.io.File.separatorChar); } } return builder.toString(); }
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; fetcher.setRoleName(); }
void setProgressMonitor(ProgressMonitor pm){progressMonitor=pm;}
void Reset() { if (!First) { ptr = 0; if (!Eof) ParseEntry(); } }
E previous() { if (iterator.previousIndex() >= start) return iterator.previous(); else throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value){for(int i=0;i<mSize;i++){if(mValues[i]==value)return i;}return -1;}
List<CharsRef> uniqueStems(char[] word, int length, List<CharsRef> stems) { if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(dictionary, 8); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { terms.add(s); deduped.add(s); } } return deduped; }
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=GetGatewayResponsesRequestMarshaller.getInstance();options.responseUnmarshaller=GetGatewayResponsesResponseUnmarshaller.getInstance();return invoke(request,options);}
public void SetPosition(long position){currentBlockIndex=(int)(position>>outerInstance.blockBits);currentBlockUpto=(int)(position&outerInstance.blockMask);currentBlock=outerInstance.blocks[currentBlockIndex];}
long Skip(long n){int s=(int)Math.min(available(),Math.max(0L,n));ptr+=s;return s;}
private BootstrapActionConfig<BootstrapActionDetail> bootstrapActionConfig;
public void serialize(LittleEndianOutput out1){out1.writeShort(field_6_author.length());out1.writeShort(0);out1.writeShort(0);out1.writeShort(0);out1.writeShort(0);out1.writeByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE(field_7_padding,out1);}else{StringUtil.putCompressedUnicode(field_7_padding,out1);}if(field_7_padding!=null){out1.writeByte(Integer.parseInt(field_7_padding,java.util.Locale.ROOT));}}
int lastIndexOf(String string){return string.lastIndexOf(',');}
boolean add(E object){return addLastImpl(object);}
void unsetSection(String section, String subsection) { ConfigSnapshot res; do { ConfigSnapshot src = state.get(); } while (!state.compareAndSet(src, res)); }
String GetTagName() { return tagName; }
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
boolean remove(Object object){ synchronized(mutex){ return c.remove(object); } }
public TokenStream create(TokenStream input){return new DoubleMetaphoneFilter(input);}
long Length(){return InCoreLength();}
void setValue(boolean newValue){ value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i){if(i<=count){throw new IndexOutOfBoundsException();}return entries[i];}
public CreateRepoRequest(){super("","","","","");setMethod(MethodType.PUT);setUriPattern("");}
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
void remove() { if (list.modCount == expectedModCount) { if (lastLink != null) { Link<E> next = lastLink.next; Link<E> previous = lastLink.previous; if (link == lastLink) { link = next; } pos--; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()); return invoke(request, options);
return invoke(request, AllocateHostedConnectionRequestMarshaller.getInstance(), AllocateHostedConnectionResponseUnmarshaller.getInstance());
int getBeginIndex() { return start; }
public WeightedTerm[] getTerms(Query query) { return getTerms(query); }
public java.nio.ByteBuffer compact(){throw new java.nio.ReadOnlyBufferException();}
static void Decode(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;i++){int byte0=blocks[blocksOffset++]&0xFF;int byte1=blocks[blocksOffset++]&0xFF;int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>>2;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>>4);values[valuesOffset++]=((byte1&15)<<2)|(byte2>>>6);values[valuesOffset++]=byte2&63;}}
public static String getHumanishName(String path){if(path==null||path.equals(""))throw new IllegalArgumentException();String[]elements=path.split("[/\\\\]");if(elements.length==0)throw new IllegalArgumentException();String result=elements[elements.length-1];if(elements.length>1&&result.equals(Constants.DOT_GIT)){result=elements[elements.length-2];}if(result.endsWith(Constants.DOT_GIT_EXT)){result=result.substring(0,result.length()-Constants.DOT_GIT_EXT.length());}return result;}
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()));
String GetAccessKeySecret() { return AccessSecret; }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()); return invoke(request, options);
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance());return invoke(DescribeVoicesResponse.class,request,options);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return this.<ListMonitoringExecutionsResponse>invoke(request, options);
public DescribeJobRequest(String jobId,String vaultName){this._jobId=jobId;this._vaultName=vaultName;}
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse getApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetApisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); return invoke(request, options); }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()));
TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b){print(Boolean.toString(b));}
IQueryNode getChild() { return getChildren().get(0); }
NotIgnoredFilter(int workdirTreeIndex){this.index=workdirTreeIndex;}
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
public class GetThumbnailRequest{private ProtocolType protocol=ProtocolType.HTTPS;public ProtocolType getProtocol(){return protocol;}public void setProtocol(ProtocolType protocol){this.protocol=protocol;}}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); return invoke(request, options);
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance());options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance());return invoke(request,options);
OrdRange getOrdRange(String dim){OrdRange result=prefixToOrdRange.get(dim);return result;}
return String.format(Locale.getDefault()," %s",org.antlr.v4.runtime.LexerNoViableAltException.class.getSimpleName())+org.antlr.v4.runtime.misc.Utils.escapeWhitespace(((ICharStream)inputStream).getText(Interval.of(startIndex,((ICharStream)inputStream).size())),false);
E peek(){return peekFirstImpl();}
return invoke(request, new InvokeOptions().withRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()), CreateWorkspacesResponse.class);
public Object clone(){NumberFormatIndexRecord rec=new NumberFormatIndexRecord();rec.field_1_formatIndex=field_1_formatIndex;return rec;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); return invoke(request, options);
public SparseIntArray(int initialCapacity){initialCapacity=com.android.internal.util.ArrayUtils.idealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
return invoke(request, new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()));
throw new UnsupportedOperationException();
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance());return invoke(request,options,DeleteWorkspaceImageResponse.class);
static String toHex(int value){return Long.toHexString((long)value).toUpperCase();}
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance());options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance());return invoke(request,options);}
public HSSFColor getColor(short index){if(HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()==index){return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor();}byte[] b=palette.getColor(index);if(b!=null){return new CustomColor(b);}else{return null;}}
public ValueEval evaluate(ValueEval[] operands,int srcRow,int srcCol){throw new NotImplementedFunctionException();}
public void serialize(ILittleEndianOutput out1){WriteShort(out1,field_1_number_crn_records);WriteShort(out1,field_2_sheet_table_index);}
DescribeDBEngineVersionsResponse describeDBEngineVersions(){return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
FormatRun(short fontIndex, short character){this._fontIndex=fontIndex;this._character=character;}
public static byte[] toBigEndianUtf16Bytes(char[] chars,int offset,int length){byte[] result=new byte[2*length];int resultIndex=0;int end=offset+length;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
return invoke(UploadArchiveResponse.class, request, new InvokeOptions().withRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()).withResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()));
List<IToken> getHiddenTokensToLeft(int tokenIndex){return getHiddenTokensToLeft(tokenIndex-1);}
@Override public boolean equals(Object obj){ if(obj==this) return true; if(obj==null||getClass()!=obj.getClass()) return false; AutomatonQuery other=(AutomatonQuery)obj; if(!m_compiled.equals(other.m_compiled)) return false; if(m_term==null){ if(other.m_term!=null) return false; }else if(!m_term.equals(other.m_term)) return false; return true; }
List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(wsq.getKey().boost(wsq.getValue())); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand(StashCreate);
FieldInfo getFieldInfo(String fieldName){FieldInfo ret=byName.get(fieldName);return ret;}
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()); return invoke(GetDocumentAnalysisResponse.class, request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(CancelUpdateStackResponse.class, request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return invoke(request, options, ModifyLoadBalancerAttributesResponse.class);
return invoke(request, new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()));
void add(char[] output,int offset,int len,int endOffset,int posLength){if(posLengths.length==count){int newSize=ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32);posLengths=Arrays.copyOf(posLengths,newSize);}if(endOffsets.length==count){int newSize=ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32);endOffsets=Arrays.copyOf(endOffsets,newSize);}if(outputs.length==count){int newSize=ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF);outputs=Arrays.copyOf(outputs,newSize);}CharsRef ref=new CharsRef();CopyChars(ref,output,offset,len);outputs[count]=ref;endOffsets[count]=endOffset;posLengths[count]=posLength;++count;}
public class FetchLibrariesRequest { public HTTPS.ProtocolType protocol; }
boolean exists() { return objects.exists(); }
FilterOutputStream out=new FilterOutputStream(System.out);
public ScaleClusterRequest() { super("EHPC", "2018-04-12", "ScaleCluster", "ehs", "openAPI"); setMethod(MethodType.PUT); setUriPattern("/clusters/{clusterId}/scaling"); }
IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2){return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);}
return invoke(ListObjectParentPathsResponse.class, request, new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()));
return invoke(DescribeCacheSubnetGroupsResponse.class, request, new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()));
void SetSharedFormula(boolean flag) { field_5_options.SetShortBoolean(sharedFormula, flag); }
boolean IsReuseObjects() { return reuseObjects; }
IErrorNode t = new ErrorNodeImpl((IToken) badToken); AddErrorNode((IToken) badToken, t); AddChild(t); return t.getParent();
public LatvianStemFilterFactory(Map<String,String> args){super(args);if(args.size()>0){throw new IllegalArgumentException("Unknown parameters: "+args);}}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()); return invoke(request, options);
public static TokenFilterFactory forName(String name, Map<String,String> args){ return loader.newInstance(name, args); }
public final HTTPS.ProtocolType protocol = HTTPS.ProtocolType.AddAlbumPhotosRequest;
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance());return invoke(request,options);}
return TreeFilter.clone(new Binary.AndTreeFilter(Clone.a(), Clone.b()));
boolean equals(Object o){return o instanceof Object;}
boolean hasArray() { return protectedHasArray(); }
return invoke(request, InvokeOptions.builder().requestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).responseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()).build());
void UnwriteProtectWorkbook() { Remove.records(); Remove.records(); fileShare = null; writeProtect = null; }
SolrSynonymParser parser = new SolrSynonymParser(dedup, expand, analyzer);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()); return invoke(request, options);
byte[] getObjectData() { return findObjectRecord().getObjectData(); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()); return invoke(request, options);
public String toString() { return GetKey() + " " + GetValue(); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); return invoke(request, ListTextTranslationJobsResponse.class, options);
return invoke(request, new InvokeOptions().withRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()));
short lookupIndexByName(String name){FunctionMetadata fd=GetInstance.getFunctionByNameInternal(name);if(fd==null){return -1;}return (short)fd.Index;}
InvokeOptions options=new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); return invoke(DescribeAnomalyDetectorsResponse.class, request, options);
String InsertId(String message, ObjectId changeId) { return InsertId; }
long getObjectSize(AnyObjectId objectId,int typeHint){long sz=db.getObjectSize(objectId,typeHint);if(sz<0){throw new MissingObjectException(objectId.copy(),"object");}if(typeHint==OBJ_ANY){throw new MissingObjectException(objectId.copy()," ");}return sz;}
return invoke(request,new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()),ImportInstallationMediaResponse.class);
return invoke(request, PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance(), PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance());
NumberPtg(ILittleEndianInput in1){field_1_value=in1.readDouble();}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); return invoke(request, options);
return invoke(DescribeDetectorResponse.class, request, new InvokeOptions().withRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()); return invoke(request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return invoke(DeleteAlarmResponse.class, request, options);
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
byte[] reserved = new byte[ENCODED_SIZE];
boolean remove(Object object){ synchronized(mutex){ return c.remove(object); } }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()));
return string.toString() + " " + precedence;
return invoke(request, new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()));
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
public WindowProtectRecord(int options){this._options = options;}
public UnbufferedCharStream(int bufferSize){n=0;data=new int[bufferSize];}
return invoke(GetOperationsResponse.class, request, new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()));
public static void copyRawTo(byte[] b, int o) { EncodeInt32.NB(b, o + 16); EncodeInt32.NB(b, o + 12); EncodeInt32.NB(b, o + 8); EncodeInt32.NB(b, o + 4); }
public WindowOneRecord(RecordInputStream in1){field_1_h_hold=in1.readShort();field_2_v_hold=in1.readShort();field_3_width=in1.readShort();field_4_height=in1.readShort();field_5_options=in1.readShort();field_6_active_sheet=in1.readShort();field_7_first_visible_tab=in1.readShort();field_8_num_selected_tabs=in1.readShort();field_9_tab_width_ratio=in1.readShort();}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()); return invoke(StopWorkspacesResponse.class, request, options);
void close() throws IOException { if (isOpen()) { try { dump(); try { channel.truncate(0); } finally { channel.close(); } } finally { fos.close(); } } }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); return invoke(request, DescribeMatchmakingRuleSetsResponse.class, options);
String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
String getPath() { return pathStr; }
public static double devsq(double[] v){double r=Double.NaN;if(v!=null&&v.length>=1){double m=0;double s=0;int n=v.length;for(int i=0;i<n;++i){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;++i){s+=(v[i]-m)*(v[i]-m);}r=(n==1?0:s);}return r;}
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()));
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
void traverse(ICellHandler handler){int firstRowRange=firstRow,lastRowRange=lastRow,firstColumnRange=firstColumn,lastColumnRange=lastColumn,width=lastColumnRange-firstColumnRange+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();IRow currentRow=null;ICell currentCell=null;for(ctx.rowNumber=firstRowRange;ctx.rowNumber<=lastRowRange;ctx.rowNumber++){currentRow=sheet.getRow(ctx.rowNumber);if(currentRow==null)continue;for(ctx.colNumber=firstColumnRange;ctx.colNumber<=lastColumnRange;ctx.colNumber++){currentCell=currentRow.getCell(ctx.colNumber);if(currentCell==null||isEmpty(currentCell)&&!traverseEmptyCells)continue;ctx.ordinalNumber=(ctx.rowNumber-firstRowRange)*width+(ctx.colNumber-firstColumnRange)+1;handler.onCell(ctx,currentCell);}}}
int GetReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other){if(term.bytesEquals(other.term)){return 0;}if(boost==other.boost){return term.compareTo(other.term);}else{return Float.compare(boost,other.boost);}}
static int Normalize(char[] s,int len){for(int i=0;i<len;++i){switch(s[i]){case HAMZA_ABOVE:len=StemmerUtil.delete(s,i,len);--i;break;case HEH:case HEH_GOAL:case HEH_YEH:len=StemmerUtil.delete(s,i,len);--i;break;case KAF:case KEHEH:s[i]=KAF;break;case YEH:case YEH_BARREE:case FARSI_YEH:s[i]=YEH;break;default:break;}}return len;}
void serialize(ILittleEndianOutput out1){out1.writeShort();}
}; exactOnly exactOnly . { ) exactOnly boolean ( DiagnosticErrorListener
private KeyType _keyType; private String _attributeName; public KeySchemaElement(String attributeName, KeyType keyType){ this._attributeName = attributeName; this._keyType = keyType; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()).withResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()), GetAssignmentResponse.class);
boolean HasObject(AnyObjectId id) { return -1 != FindOffset(id); }
boolean setAllGroups(boolean allGroups){return allGroups;}
void setMultiValued(String dimName, boolean v){synchronized(this){DimConfig fieldType=fieldTypes.get(dimName);if(fieldType==null){fieldType=new DimConfig();fieldTypes.put(dimName,fieldType);}fieldType.setMultiValued(v);}}
int GetCellsVal(){int size=0;for(char c:cells.keySet()){Cell e=At(c);if(e.cmd>=0){size++;}}return size;}
return invoke(DeleteVoiceConnectorResponse.class, request, new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()), DeleteLifecyclePolicyResponse.class);
