void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public void addAll(BlockList<T> src){if(src.size==0)return;for(int srcDirIdx=0;srcDirIdx<src.tailDirIdx;++srcDirIdx)addAll((T[])src.directory[srcDirIdx],0,BLOCK_SIZE);if(src.tailBlkIdx!=0)addAll((T[])src.tailBlock,0,src.tailBlkIdx);}
void writeByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { blocks.add(currentBlock); blockEnd.add(upto); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { return this.<DeleteDomainEntryResponse>invoke(request, InvokeOptions.builder().requestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).responseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()).build()); }
long RamBytesUsed() { return fst == null ? 0L : fst.GetSizeInBytes(); }
} ; ) raw.length , , , ( RawParseUtils.decode return ; ) ( RawParseUtils.parseEncoding = enc Charset } ; "" return { ) 0 < msgB ( if ; ) , ( RawParseUtils.tagMessage = msgB int ; raw = buffer [] byte { ) ( getFullMessage String
public POIFSFileSystem() { this.headerBlock = new HeaderBlock(); this._property_table = new PropertyTable(); this._documents = new ArrayList(); this._root = null; }
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; offset0 = address & ByteBlockPool.BYTE_BLOCK_MASK; upto = offset0; assert upto < slice.length; }
submoduleAddCommand.setPath(path);
return invoke(request, new InvokeOptions().withRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()));
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
return invoke(request, new InvokeOptions().setRequestMarshaller(GetShardIteratorRequestMarshaller.INSTANCE).setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.INSTANCE));
new ModifyStrategyRequest(" ", " ", " ", " ", " ").setMethod(MethodType.POST);
public boolean ready() throws java.io.IOException { synchronized (lock) { if (in == null) { throw new java.io.IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
EscherOptRecord getOptRecord() { return _optRecord; }
if (buffer == null) throw new NullPointerException("buffer"); if (offset < 0 || length < 0 || length > buffer.length - offset) throw new IndexOutOfBoundsException(); if (length == 0) return 0; int copylen = (count - pos < length) ? count - pos : length; for (int i = 0; i < copylen; i++) buffer[offset + i] = buffer[pos + i]; pos += copylen; return copylen;
NLPSentenceDetectorOp sentenceOp = new NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
void print(String str) { write(String.valueOf(str)); }
public NotImplementedFunctionException(String message, Throwable cause) { super(message, cause); }
} ; value . ) ( nextEntry . return { ) ( next V
void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new java.io.EOFException(" "); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) { throw new java.io.EOFException(" "); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResponse TagQueue(TagQueueRequest request)     {         var options = new InvokeOptions         {             RequestMarshaller = TagQueueRequestMarshaller.Instance,             ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance         };         return Invoke<TagQueueResponse>(request, options);     }
} ; ) ( UnsupportedOperationException new throw { ) ( Remove void
} ; ) , ( > ModifyCacheSubnetGroupResponse < Invoke return ; Instance . ModifyCacheSubnetGroupResponseUnmarshaller ResponseUnmarshaller . options ; Instance . ModifyCacheSubnetGroupRequestMarshaller RequestMarshaller . options ; ) ( InvokeOptions new = options { ) request ModifyCacheSubnetGroupRequest ( ModifyCacheSubnetGroup ModifyCacheSubnetGroupResponse
void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture += " " + st.nextToken(); if (st.hasMoreTokens()) ignore = st.nextToken(); }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance())); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (Length != other.Length) { return false; } for (int i = Length - 1; i >= 0; --i) { if (!Components[i].equals(other.Components[i])) { return false; } } return true; }
return Invoke<GetInstanceAccessDetailsRequest, GetInstanceAccessDetailsResponse>(request, new InvokeOptions     {         RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance,         ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance     });
HSSFPolygon shape = shapes.createPolygon(anchor);
String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
} ; ) , ( > GetDashboardResponse < Invoke return ; Instance . GetDashboardResponseUnmarshaller ResponseUnmarshaller . options ; Instance . GetDashboardRequestMarshaller RequestMarshaller . options ; ) ( InvokeOptions new = options { ) request GetDashboardRequest ( GetDashboard GetDashboardResponse
return invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()));
void addMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.numColumns;++j){BlankRecord br=new BlankRecord();br.column=mbr.firstColumn+j;br.row=mbr.row;br.xfIndex=mbr.getXfAt(mbr.row);insertCell(br);}}
public static String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\"", apos)) != -1) { sb.append(string.substring(apos, k)); sb.append("\\\""); apos = k + 1; } sb.append(string.substring(apos)); sb.append("\""); return sb.toString(); }
} ; ) ( ReadOnlyBufferException . nio . java new throw { ) value int ( putInt ByteBuffer . nio . java
public ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
return client.getIceServerConfig(request);
} ; ) ( toString . sb return ; ) " " ( append . sb ; ) ) ( getValueAsString ( append . sb ; ) " " ( append . ) ) ( getSimpleName ( ) getClass ( append . sb ; ) ( StringBuilder new = sb StringBuilder { ) ( toString String public
@Override public String toString() { return " " + _parentQuery + " "; }
void IncRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { return invoke(request, UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance(), UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); }
} ; INT_SIZE . LittleEndianConsts * ) ( GetXBATEntriesPerBlock return { ) ( GetNextXBATChainOffset int
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 > 0) { mulShift(_multiplicand.tp, _multiplierShift.tp); } else { mulShift(_divisor.tp, _divisorShift.tp); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = getLength(); builder.append(java.io.File.separator); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < length - 1) { builder.append(java.io.File.separator); } } return builder.toString(); }
void withFetcher(EcsCredentialsProvider fetcher) { fetcher.setRoleName(); }
void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
} } } ; ) ( ParseEntry { ) Eof ! ( if ; 0 ptr { ) First ! ( if { ) ( Reset void
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
} ; deduped return } } ; ) s ( add . terms ; ) s ( add . deduped { ) ) s ( contains . terms ! ( if { ) stems : s CharsRef ( for ; ) ( > < ArrayList new = deduped > CharsRef < List ; ) true , 8 ( CharArraySet new = terms CharArraySet } ; stems return { ) 2 < ) ( size . stems ( if ; ) length , word ( stem = stems > CharsRef < List { ) length int , word ] [ char ( uniqueStems > CharsRef < List public
return invoke(request, new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()));
void SetPosition(long position) { currentBlockIndex = (int)(position >> blockBits.outerInstance); currentBlock = blocks.outerInstance[currentBlockIndex]; currentBlockUpto = (int)(position & blockMask.outerInstance); }
} ; s return ; s ptr ; ) ) , ( max . Math , ) ( Available ( min . Math ) int ( = s int { ) n long ( Skip long
public BootstrapActionConfig(BootstrapActionDetail bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
public void serialize(LittleEndianOutput out1) { out1.writeShort(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } if (field_7_padding != null) { out1.write(field_7_padding); } }
int lastIndexOf(String string) { return lastIndexOf( , ); }
boolean add(E object) { return addLastImpl(object); }
} ; ) ) , ( CompareAndSet . state ! ( while } ; ) , , ( UnsetSection res ; ) ( Get . state src { do ; ConfigSnapshot ; ConfigSnapshot { ) subsection String , section String ( UnsetSection void
String GetTagName() { return tagName; }
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
boolean remove(Object obj) { synchronized(mutex) { return c.remove(obj); } }
public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input); }
long Length() { return InCoreLength; }
void SetValue(boolean newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i) { if (count <= i) { throw new IndexOutOfBoundsException(); } return entries[i]; }
@PutMapping(value = uriPattern) public void createRepo(CreateRepoRequest request);
} ; deltaBaseAsOffset return { ) ( IsDeltaBaseAsOffset boolean
void remove() { if (list.modCount == expectedModCount) { if (lastLink != null) { Link<E> next_1 = lastLink.next; Link<E> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } link = previous_1; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); } }
public MergeShardsResponse mergeShards(MergeShardsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance())); }
return invoke(request, new InvokeOptions() {{ setRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()); setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); }});
int getBeginIndex() { return start; }
} ; ) , ( getTerms return { ) query Query ( getTerms ] [ WeightedTerm
java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations){for(int i=0;i<iterations;i++){int byte0=blocks[++blocksOffset]&0xFF;values[++valuesOffset]=byte0>>2;int byte1=blocks[++blocksOffset]&0xFF;values[++valuesOffset]=((byte0&3)<<4)|(byte1>>4);int byte2=blocks[++blocksOffset]&0xFF;values[++valuesOffset]=((byte1&15)<<2)|(byte2>>6);values[++valuesOffset]=byte2&63;}}
String getHumanishName(String s) { if (s == null || s.isEmpty()) throw new IllegalArgumentException(); String[] elements = s.split("[/\\\\]"); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) result = elements[elements.length - 2]; if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); return result; }
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()));
} ; AccessSecret return { ) ( GetAccessKeySecret String
return invoke(request, new InvokeOptions().withRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()));
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { return invoke(request, new InvokeOptions(), DescribeVoicesRequestMarshaller.getInstance(), DescribeVoicesResponseUnmarshaller.getInstance()); }
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance())); }
public DescribeJobRequest(String vaultName, String jobId) { this._vaultName = vaultName; this._jobId = jobId; }
EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
return invoke(request, new InvokeOptions(GetApisRequestMarshaller.getInstance(), GetApisResponseUnmarshaller.getInstance()));
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); return this.<DeleteSmsChannelResponse>invoke(request, options); }
UpdateRefTracking GetTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) { print(Boolean.toString(b)); }
IQueryNode GetChild() { return GetChildren()[0]; }
void notIgnoredFilter(int workdirTreeIndex) { index.workdirTreeIndex; }
AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
new GetThumbnailRequest(" ", " ", " ", " ", " ").setProtocol(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(), DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); }
return invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance(), new InvokeOptions());
OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
} ; ) , getName() . ) LexerNoViableAltException . Runtime . Antlr4 ( .class , " " , Locale.getDefault() ( String.format return } ; ) , ( Utils.escapeWhitespace symbol ; ) ) , ( Interval.of ( getText . ) inputStream ) ICharStream ( ( symbol { ) size() . ) inputStream ) ICharStream ( ( < startIndex && 0 >= startIndex ( if ; "" = symbol String { ) ( toString String
E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance())); }
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex;
public DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request)     {         return Invoke<DescribeRepositoriesResponse>(request, new InvokeOptions         {             RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance,             ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance         });     }
public SparseIntArray(int initialCapacity) { initialCapacity = com.android.internal.util.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
} ; ) ( HyphenatedWordsFilter new return { ) input TokenStream ( create TokenStream
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance())); }
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.Instance); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.Instance); return this.<DeleteWorkspaceImageResponse>invoke(request, options); }
public String ToHex(int value) { return ToHex((long)value); }
return this.<UpdateDistributionResponse>invoke(request, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.INSTANCE).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.INSTANCE));
if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor(); return palette.getColor(index);
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedException(); }
void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
DescribeDBEngineVersionsResponse response = client.describeDBEngineVersions(DescribeDBEngineVersionsRequest.builder().build());
public FormatRun(short character, short fontIndex) { this.character = character; this.fontIndex = fontIndex; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return invoke(request, new InvokeOptions().withRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()).withResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()));
List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_compiled == null) { if (other.m_compiled != null) return false; } else if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; return true; }
{ java.util.List<SpanQuery> spanQueries = new java.util.ArrayList<>(); for (java.util.Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
StashCreateCommand StashCreate() { return new StashCreateCommand(); }
public FieldInfo fieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { return invoke(request, InvokeOptions.builder().requestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).responseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()).build()); }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()));
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { return this.<CancelUpdateStackResponse>invoke(request, new InvokeOptions().withRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()).withResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance())); }
return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()));
return this.<SetInstanceProtectionResponse>invoke(request, new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyDBProxyRequestMarshaller.INSTANCE).withResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.INSTANCE));
void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
new FetchLibrariesRequest(" ", " ", " ", " ", " ", ProtocolType.HTTPS);
boolean Exists() { return objects.Exists(); }
new FilterOutputStream(out) {};
new ScaleClusterRequest(MethodType.PUT, uriPattern, " ", " ", " ", " ", " ");
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return new DVConstraint(operatorType, formula1, formula2); }
return invoke(request, new InvokeOptions.Builder().requestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).responseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()).build());
return invoke(request, new InvokeOptions(), DescribeCacheSubnetGroupsRequestMarshaller.getInstance(), DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());
void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(flag); }
} ; reuseObjects return { ) ( isReuseObjects boolean
ErrorNodeImpl t = new ErrorNodeImpl(badToken); addChild(t.getParent()); return t;
public LatvianStemFilterFactory(java.util.Map<String,String> args){super(args);if(args.size()>0){throw new IllegalArgumentException(" "+args);}}
return invoke(request, new InvokeOptions().withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()));
public TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(); }
} ; ProtocolType.HTTPS protocol { ) " " , " " , " " , " " , " " ( extends ) ( AddAlbumPhotosRequest
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { return this.<GetThreatIntelSetResponse>invoke(request, new InvokeOptions().withRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).withResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance())); }
} ; ) ) ( clone . b , ) ( clone . a ( Binary . AndTreeFilter new return { ) ( clone TreeFilter
public boolean equals(Object o) { return o instanceof Object; }
protected boolean HasArray() { return hasArray; }
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()), UpdateContributorInsightsResponse.class);
void unwriteProtectWorkbook() { records.remove(); records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) { super(dedup, analyzer); this.expand = expand; }
return ec2Client.requestSpotInstances(request);
byte[] getObjectData() { return findObjectRecord().objectData; }
return invoke(request, InvokeOptions.builder().requestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).responseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()).build());
@Override public String toString() { return getKey() + " " + getValue(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()));
} ; index . fd ) short ( return } ; 1 - return { ) null == fd ( if ; ) name ( getFunctionByNameInternal . ) ( getInstance = fd FunctionMetadata { ) name String ( lookupIndexByName short
return invoke(request, new InvokeOptions.Builder().requestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()).responseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()).build());
public String insertId(ObjectId changeId, String message, String insertId) { return insertId; }
long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.copy(), " "); throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
return invoke(request, new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()));
return client.putLifecycleEventHookExecutionStatus(request);
field_1_value = in1.readDouble();
return invoke(request, new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()));
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { return this.<DescribeDetectorResponse>invoke(request, new InvokeOptions(), DescribeDetectorRequestMarshaller.getInstance(), DescribeDetectorResponseUnmarshaller.getInstance()); }
return invoke(request, new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.INSTANCE).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.INSTANCE));
return Invoke<DeleteAlarmRequest, DeleteAlarmResponse>(request, new InvokeOptions     {         RequestMarshaller = DeleteAlarmRequestMarshaller.Instance,         ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance     });
} ; ) input ( PortugueseStemFilter new return { ) input TokenStream ( create TokenStream
FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
boolean remove(Object obj) { synchronized(mutex) { return c.remove(obj); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { return invoke(request, new InvokeOptions(), GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance()); }
return "(" + precedence.toString() + ")";
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance())); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this._loadBalancerName = loadBalancerName; this._policyName = policyName; }
public WindowProtectRecord(int options) { this.options = options; }
public UnbufferedCharStream(int bufferSize) { data = new int[bufferSize]; n = 0; }
public GetOperationsResponse getOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(o); NB.EncodeInt32(o + 4); NB.EncodeInt32(o + 8); NB.EncodeInt32(o + 12); NB.EncodeInt32(o + 16); }
} ; ) ( readShort . in1 field_9_tab_width_ratio ; ) ( readShort . in1 field_8_num_selected_tabs ; ) ( readShort . in1 field_7_first_visible_tab ; ) ( readShort . in1 field_6_active_sheet ; ) ( readShort . in1 field_5_options ; ) ( readShort . in1 field_4_height ; ) ( readShort . in1 field_3_width ; ) ( readShort . in1 field_2_v_hold ; ) ( readShort . in1 field_1_h_hold {  ) in1 RecordInputStream ( WindowOneRecord
return invoke(request, InvokeOptions.builder().requestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).responseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()).build());
void close() throws java.io.IOException { if (isOpen) { try { dump(); } finally { try { channel.truncate(0); } finally { try { channel.close(); } finally { fos.close(); } } } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { return invoke(request, new InvokeOptions(), DescribeMatchmakingRuleSetsRequestMarshaller.getInstance(), DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); }
String GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
String GetPath() { return pathStr; }
public double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; ++i) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResponse describeResize(DescribeResizeRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()); return this.<DescribeResizeResponse>invoke(request, options); }
} ; passedThroughNonGreedyDecision return { ) ( hasPassedThroughNonGreedyDecision boolean
} ; ) ( end return { ) ( end int
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null || (currentCell.getCellType() == CellType.BLANK && !traverseEmptyCells)) { continue; } ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1); handler.onCell(ctx, currentCell); } } }
int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) { int c = Term.compareTo(other.Term); if (c != 0) return c; return Boost.compareTo(other.Boost); }
int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
} ; exactOnly exactOnly . {  ) exactOnly boolean ( DiagnosticErrorListener
} ; keyType this._keyType ; attributeName this._attributeName { ) keyType KeyType , attributeName String ( KeySchemaElement
return invoke(request, new InvokeOptions.Builder().requestMarshaller(GetAssignmentRequestMarshaller.getInstance()).responseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()).build());
boolean HasObject(AnyObjectId id) { return FindOffset(id) != -1; }
public void setAllGroups(boolean allGroups) { this.allGroups = allGroups; return; }
public void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { fieldTypes.computeIfAbsent(dimName, k -> new DimConfig()).setIsMultiValued(v); } }
int getCellsVal() { int size = 0; for (Character c : cells.keySet()) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
return invoke(request, InvokeOptions.builder().requestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).responseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()).build());
return invoke(request, InvokeOptions.builder().marshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).unmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()).build());
