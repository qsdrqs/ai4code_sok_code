void serialize(ILittleEndianOutput out1) { out1.writeShort((short) 0); }
public void addAll(T src) {      if (src.size() == 0) return;      int srcDirIdx = 0;      for (; srcDirIdx < src.size(); ++srcDirIdx) {          if (0 != src.get(srcDirIdx).getDirectory()) break;      }      int tailDirIdx = srcDirIdx;      int tailBlkIdx = 0;      for (; tailBlkIdx < src.get(tailDirIdx).getBlockList().size();) {          if (0 == src.get(tailDirIdx).getBlockList().get(tailBlkIdx).getBlock()) break;          ++tailBlkIdx;      }      Util.addAll(directory, src, srcDirIdx, tailDirIdx, tailBlkIdx);  }
public void writeByte(byte b) { if (b == 0) { if (++upto == blockSize) { currentBlock = new byte[blockSize]; blocks.add(currentBlock); upto = 0; } currentBlock[upto] = b; } }
return getObjectId();
return invoke(DeleteDomainEntryRequest.class, DeleteDomainEntryResponse.class, request, options, DeleteDomainEntryRequestMarshaller.class, DeleteDomainEntryResponseUnmarshaller.class);
return fst == null ? 0 : fst.GetSizeInBytes();
return RawParseUtils.ParseEncoding(TagMessage, RawParseUtils.enc, buffer, 0 < msgB.Length ? Decode.RawParseUtils(msgB) : Empty.string);
POIFSFileSystem fs = new POIFSFileSystem();  HeaderBlock headerBlock = new HeaderBlock(fs.getRoot());  PropertyTable _property_table = new PropertyTable(headerBlock);  ArrayList _documents = new ArrayList();
assert address >= 0 && address < ByteBlockPool.BYTE_BLOCK_MASK; assert slice != null && (address & ByteBlockPool.BYTE_BLOCK_MASK) == 0; int offset = address >> ByteBlockPool.BYTE_BLOCK_SHIFT; assert offset >= 0 && offset < Buffers.pool.Length; assert slice[offset] != null;
return new SubmoduleAddCommand().setPath(path).setApi(NGit.getPath(path));
return invoke(ListIngestionsRequest.class, ListIngestionsResponse.class, request, options, ListIngestionsRequestMarshaller.getInstance(), ListIngestionsResponseUnmarshaller.getInstance());
public void SwitchTo(int lexState, ICharStream stream) { this.stream = stream; this.lexState = lexState; }
return invoke(GetShardIteratorRequest.class, GetShardIteratorResponse.class, request, options, GetShardIteratorRequestMarshaller.getInstance(), GetShardIteratorResponseUnmarshaller.getInstance());
ModifyStrategyRequest.class.getMethod("Method", String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class);
try {    if (in != null) {      lock (lock) {        if (in.available() > 0 || in.hasRemaining()) {          return in.read();        }      }    }  } catch (IOException e) {    throw new IOException("IO Exception", e);  }
return GetOptRecord EscherOptRecord();
public int read(byte[] buffer, int offset, int length) {      if (buffer == null) throw new NullPointerException("buffer");      if (length < 0) throw new IllegalArgumentException("length < 0");      if (offset < 0) throw new IllegalArgumentException("offset < 0");      if (offset + length > buffer.length) throw new IllegalArgumentException("offset + length > buffer.length");      int copylen = Math.min(length, available());      if (copylen == 0) return 0;      for (int i = 0; i < copylen; i++) buffer[offset + i] = get(pos + i);      pos += copylen;      return copylen;  }
OpenNLPSentenceBreakIterator sentenceOp = new OpenNLPSentenceBreakIterator();
System.out.println(void.class);
throw new UnsupportedOperationException(functionName + " is not implemented") {     public UnsupportedOperationException(String functionName) {         super(functionName + " is not implemented");     } }
return nextEntry.value();
public void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) throws EndOfStreamException {     if (useBuffer) {         if (bufferPosition >= bufferLength) {             Refill();         }         int available = bufferLength - bufferPosition;         if (len > available) {             len = available;         }         if (len > 0) {             System.arraycopy(buffer, bufferPosition, b, offset, len);             bufferPosition += len;         }         if (len < bufferSize) {             throw new EndOfStreamException("End of stream");         }     } else {         long after = bufferStart + bufferPosition + len;         if (after > Length) {             throw new EndOfStreamException("End of stream");         }         if (len > bufferSize) {             System.arraycopy(buffer, bufferPosition, b, offset, len);             bufferPosition += len;         } else {             int available = bufferLength - bufferPosition;             if (len > available) {                 System.arraycopy(buffer, bufferPosition, b, offset, available);                 bufferPosition += available;                 len -= available;                 offset += available;                 if (len > 0) {                     ReadInternal(b, offset, len, false);                 }             } else {                 System.arraycopy(buffer, bufferPosition, b, offset, len);                 bufferPosition += len;             }         }     } }
return InvokeOptions.builder().request(new TagQueueRequest()).response(new TagQueueResponse(), TagQueueResponseUnmarshaller.instance).requestMarshaller(TagQueueRequestMarshaller.instance).options(options).build();
throw new UnsupportedOperationException();
ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {      return (ModifyCacheSubnetGroupResponse) new Instance().modifyCacheSubnetGroup(request,      new InvokeOptions().withRequestMarshaller(Instance.modifyCacheSubnetGroupRequestMarshaller).withResponseUnmarshaller(Instance.modifyCacheSubnetGroupResponseUnmarshaller));  }
StringTokenizer st = new StringTokenizer("@params string culture ignore", " ");  String culture = "";  while(st.hasMoreTokens()) {      String current = st.nextToken();      if(current.equals("culture")) {          culture = st.nextToken();      }  }
return invoke(DeleteDocumentationVersionRequest.class, request, options, DeleteDocumentationVersionResponse.class, DeleteDocumentationVersionRequestMarshaller.INSTANCE, DeleteDocumentationVersionResponseUnmarshaller.INSTANCE);
public boolean equals(Object obj) {      if (obj == this) return true;      if (!(obj instanceof FacetLabel)) return false;      FacetLabel other = (FacetLabel) obj;      if (other.Length != Length) return false;      for (int i = 0; i < Length; i++) {          if (!Components[i].equals(other.Components[i], StringComparison.Ordinal)) return false;      }      return true;  }
return invoke(GetInstanceAccessDetailsRequest.class, GetInstanceAccessDetailsResponse.class, request, options,      GetInstanceAccessDetailsRequestMarshaller.class, GetInstanceAccessDetailsResponseUnmarshaller.class);
CreatePolygon HSSFPolygon { } { return shape; } ; ; ; ; anchor HSSFChildAnchor OnCreate anchor HSSFPolygon ) ( ) ( Add . shapes Anchor . shape Parent . shape shape = new HSSFPolygon() ;
return getSheetName(getBoundSheetRec(sheetIndex).getSheetname());
return invoke(new GetDashboardRequest(), new GetDashboardResponseUnmarshaller(), options, GetDashboardRequestMarshaller.INSTANCE, GetDashboardResponseUnmarshaller.INSTANCE);
return invoke(AssociateSigninDelegateGroupsWithAccountRequest.class, AssociateSigninDelegateGroupsWithAccountResponse.class, options, RequestMarshaller.AssociateSigninDelegateGroupsWithAccountRequestMarshaller(), ResponseUnmarshaller.AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller());
AddMultipleBlanks void { for (int j = 0; j < NumColumns; j++) { BlankRecord mbr = new BlankRecord(); mbr.XFIndex = GetXFAt(j); mbr.Row = Row; mbr.Column = FirstColumn + j; InsertCell(mbr); } }
String string = ""; return; while (; ; ) ; int k = 0; StringBuilder sb = new StringBuilder(); sb.append("\\\""); sb.append(lang.java); sb.append("\\\\\\\""); sb.append(@string.substring(0, @string.indexOf("\"", 2 + k))); sb.append("\\\""); sb.append(lang.java); sb.append(@string.substring(@string.indexOf("\"", 2 + k) + 1));
throw new ReadOnlyBufferException() { };
for (int r = 0; r < _nRows; r++) { for (int c = 0; c < _nColumns; c++) { Object vv = _arrayValues[r * _nColumns + c]; rowData[r][c] = vv; } }
return invoke(new GetIceServerConfigRequest(), new GetIceServerConfigRequestMarshaller(), new InvokeOptions(), new GetIceServerConfigResponse(), new GetIceServerConfigResponseUnmarshaller(), new InvokeOptions());
StringBuilder sb = new StringBuilder(); sb.append(GetType().getName()); sb.append(" "); sb.append(GetValueAsString()); return sb.toString();
return field + " " + _parentQuery + " ";
synchronized void incrementAndGet() { refCount.incrementAndGet(); }
return invoke(new UpdateConfigurationSetSendingEnabledRequest(), UpdateConfigurationSetSendingEnabledRequest.class, UpdateConfigurationSetSendingEnabledResponse.class, RequestMarshaller.UpdateConfigurationSetSendingEnabledRequestMarshaller.INSTANCE, ResponseUnmarshaller.UpdateConfigurationSetSendingEnabledResponseUnmarshaller.INSTANCE, options);
return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock();
private void multiplyByPowerOfTen() {     if (pow10 > 0) {         int tp = TenPower.GetInstance().Abs(pow10);         _divisor = mulShift(_divisor, tp);         _divisorShift += tp;         _multiplicand = mulShift(_multiplicand, tp);         _multiplierShift += tp;     } }
StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; i++) { if (Path.getComponent(i) == DirectorySeparatorChar) { builder.append("/"); } else { builder.append(Path.getComponent(i)); } } return builder.toString();
withFetcher(void.class, (fetcher) -> { ((ECSMetadataServiceCredentialsFetcher)fetcher).setRoleName(); });
public void setProgressMonitor(ProgressMonitor pm) { }
void reset() { if (ptr != 0) { if (!eof) { parseEntry(); } } }
if (start >= previousIndex) throw new NoSuchElementException(); return previous(iterator);
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> uniqueStems(List<CharsRef> stems) {      Set<CharsRef> deduped = new HashSet<>();      for (CharsRef s : stems) {          if (s.length() > 2) {              deduped.add(s);          }      }      return new ArrayList<>(deduped);  }
return invoke(new GetGatewayResponsesRequest(), new GetGatewayResponsesRequestMarshaller(), new GetGatewayResponsesResponseUnmarshaller(), options);
public void setPosition(int currentBlockIndex, int currentBlock, long currentBlockUpto, long position) {     position = (position & ((long)blocks.outerInstance.blockMask)) >> blocks.outerInstance.blockBits; }
return Math.min(Math.max(n, s), ptr);
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
if (field_5_hasMultibyte) {     if (null != field_7_padding) {         out1.writeByte(0x01);     } else {         out1.writeByte(0x00);     }     out1.writeShort(field_6_author.length);     StringUtil.putCompressedUnicode(field_6_author, out1); } else {     out1.writeShort(0x00);     out1.writeShort(0x00);     out1.writeShort(0x00);     out1.writeShort(0x00);     out1.writeShort(0x00);     StringUtil.putUnicodeLE(field_6_author, out1); }
return string.lastIndexOf(int);
return object.addLastImpl();
public void unsetSection(String section, String subsection) {      ConfigSnapshot state;      ConfigSnapshot res;      do {          state = getState();      } while (!compareAndSet(state, res = state.unsetSection(section, subsection)));  }
public String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) { subrecords.insert(element, index); }
synchronized (mutex) {      return object.remove(c);  }
return new DoubleMetaphoneFilter(input, new TokenStream() {});
return getInCoreLength();
public void setValue(boolean newValue) { }
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource, oldSource);
throw new IndexOutOfBoundsException();
public class CreateRepoRequest {      public static final String METHOD = "PUT";      public static final String URI_PATTERN = "/";      public enum MethodType {          PUT      };  }
public boolean IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount != expectedModCount) throw new ConcurrentModificationException(); else if (lastLink != null) { Link<ET> link = lastLink; Link<ET> previous_1 = link.previous; Link<ET> next_1 = link.next; previous_1.next = next_1; next_1.previous = previous_1; --_size; ++modCount; --expectedModCount; lastLink = null; pos = -1; } else throw new InvalidOperationException();
return Invoke.instance.mergeShards(request, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.instance).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.instance));
return Invoke(new AllocateHostedConnectionRequest(), new AllocateHostedConnectionResponseUnmarshaller(), options, new AllocateHostedConnectionRequestMarshaller(), new AllocateHostedConnectionResponse());
public int getBeginIndex() { return start; }
getTerms() { return query.getTerms(); }
throw new java.nio.ReadOnlyBufferException();
public void decode() {     for (int i = 0; i < iterations; i++) {         int byte0 = (int) (0xFF & blocks[blocksOffset++]);         int byte1 = (int) (0xFF & blocks[blocksOffset++]);         int byte2 = (int) (0xFF & blocks[blocksOffset++]);         values[valuesOffset++] = (byte0 & 0xFF) | ((byte1 & 0xFF) << 8) | ((byte2 & 0xFF) << 16);         byte0 = (int) (0xFF & blocks[blocksOffset++]);         byte1 = (int) (0xFF & blocks[blocksOffset++]);         byte2 = (int) (0xFF & blocks[blocksOffset++]);         values[valuesOffset++] = (byte0 & 0xFF) | ((byte1 & 0xFF) << 8) | ((byte2 & 0xFF) << 16);         byte0 = (int) ((blocks[blocksOffset++] & 0xFF) >> 2);         byte1 = (int) ((blocks[blocksOffset++] & 0xFF) >> 2);         byte2 = (int) ((blocks[blocksOffset] & 0xFF) >> 2);         values[valuesOffset++] = ((byte0 & 0x3F) << 16) | ((byte1 & 0x3F) << 8) | (byte2 & 0x3F);     } }
String getHumanishName(String s) {      if (s == null) throw new ArgumentException("null");      if (s.length() == 0) throw new ArgumentException("empty");      if (s.equals(DOT_GIT)) return "";      if (s.endsWith(DOT_GIT_EXT)) s = s.substring(0, s.length() - DOT_GIT_EXT.length());      if (s.endsWith(DOT_GIT)) s = s.substring(0, s.length() - DOT_GIT.length());      String[] elements = s.split(File.separator);      if (elements.length == 1) return s;      String result = elements[elements.length - 1];      if (result.isEmpty()) result = elements[elements.length - 2];      if (result.equals(LOCAL_FILE)) result = elements[elements.length - 2] + " " + elements[elements.length - 1];      return result;  }
DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return (DescribeNotebookInstanceLifecycleConfigResponse) new DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller().unmarshall(new DescribeNotebookInstanceLifecycleConfigRequestMarshaller().marshall(request)); }
return GetAccessKeySecret();
return invoke(CreateVpnConnectionRequest.class, CreateVpnConnectionResponse.class, request, options, CreateVpnConnectionRequestMarshaller.class, CreateVpnConnectionResponseUnmarshaller.class);
return Invoke.Instance.invoke(DescribeVoicesRequest.class, RequestMarshaller.options(DescribeVoicesRequestMarshaller.INSTANCE), DescribeVoicesResponse.class, ResponseUnmarshaller.options(DescribeVoicesResponseUnmarshaller.INSTANCE));
return Invoke.invoke(Instance.ListMonitoringExecutionsRequestMarshaller.marshall(request), options, Instance.ListMonitoringExecutionsResponseUnmarshaller.instance, options);
public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
return escherRecords.get(index);
return invoke(GetApisRequest.class, GetApisResponse.class, request, options, GetApisRequestMarshaller.options(), GetApisResponseUnmarshaller.options());
return invoke(DeleteSmsChannelRequest.class, DeleteSmsChannelRequest.getMarshaller(), DeleteSmsChannelResponse.class, DeleteSmsChannelResponse.getUnmarshaller(), options);
return getTrackingRefUpdate();
System.out.println(void.class);  System.out.println(boolean.class);  System.out.println(Object.class.toString());
public IQueryNode getChild() { return getChildren().get(0); }
new NotIgnoredFilter().workdirTreeIndex(index);
} { ; AreaRecord field_1_formatFlags ) in1 = new RecordInputStream ( ) . readShort ( ) ;
public class GetThumbnailRequest extends Protocol { }
DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { return (DescribeTransitGatewayVpcAttachmentsResponse) new DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller().unmarshall(new DescribeTransitGatewayVpcAttachmentsRequestMarshaller().marshall(request)); }
return invoke(PutVoiceConnectorStreamingConfigurationRequest.class, PutVoiceConnectorStreamingConfigurationRequestMarshaller.options(), PutVoiceConnectorStreamingConfigurationResponse.class, PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.options());
return prefixToOrdRange.TryGetValue(dim, result) ? result : null;
return toString();  if (string != null) {      string = string.format("", new Object());  }  String symbol = "";  int startIndex = 0;  if (startIndex >= 0) {      symbol = symbol.substring(startIndex);  }  return symbol;  } catch (Exception e) {      throw new LexerNoViableAltException((ICharStream) input, (Runtime) antlr4, (Interval) interval);  }
return peekFirstImpl();
return invoke(new CreateWorkspacesRequest(), new CreateWorkspacesRequestMarshaller(), new CreateWorkspacesResponseUnmarshaller(), options);
return new NumberFormatIndexRecord(rec.getField_1_formatIndex());
return invoke(DescribeRepositoriesRequest.class, DescribeRepositoriesResponse.class, request, options, DescribeRepositoriesRequestMarshaller.INSTANCE, DescribeRepositoriesResponseUnmarshaller.INSTANCE);
SparseIntArray(int initialCapacity) { this(new int[idealIntArraySize(initialCapacity)], 0, 0); }
return new TokenStream(new HyphenatedWordsFilter(input));
return invoke(CreateDistributionWithTagsRequest.class, CreateDistributionWithTagsResponse.class, request, options, CreateDistributionWithTagsRequestMarshaller.class, CreateDistributionWithTagsResponseUnmarshaller.class);
throw new NotImplementedException("RandomAccessFile(" + fileName + ", " + mode + ")");
return invoke(DeleteWorkspaceImageRequest.class, DeleteWorkspaceImageRequestMarshaller.options(), DeleteWorkspaceImageResponse.class, DeleteWorkspaceImageResponseUnmarshaller.options());
public class Main {      public static String toHex(long value) { return Long.toHexString(value); } }
return invoke(new UpdateDistributionRequest(), new UpdateDistributionResponse(), UpdateDistributionRequest.class, UpdateDistributionResponse.class, RequestMarshaller.UpdateDistributionRequestMarshaller.INSTANCE, ResponseUnmarshaller.UpdateDistributionResponseUnmarshaller.INSTANCE, options);
return index == HSSFColor.AUTOMATIC.index ? HSSFColor.AUTOMATIC : palette.getColor(index);
throw new NotImplementedFunctionException("Not implemented", srcRow, srcCol, operands);
public void serialize(ILittleEndianOutput out1) {     out1.writeShort(field_1_number_crn_records);     out1.writeShort(field_2_sheet_table_index); }
DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest describeDBEngineVersionsRequest) { return new DescribeDBEngineVersions(); }
public class Main {      public static class FormatRun {         public short fontIndex;         public short character;         public FormatRun(short fontIndex, short character) {             this.fontIndex = fontIndex;             this.character = character;         }     }     public static void main(String[] args) {          FormatRun run = new FormatRun((short) 0, (short) 0);      }  }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {      int resultIndex = 0;      int end = offset + length;      byte[] result = new byte[length << 2];      for (int i = offset; i < end; i++) {          char ch = chars[i];          result[resultIndex++] = (byte) ((ch >> 8) & 0xFF);          result[resultIndex++] = (byte) (ch & 0xFF);      }      return result;  }
return invoke(new UploadArchiveRequest(), new UploadArchiveResponseUnmarshaller(), options, UploadArchiveRequestMarshaller.class, UploadArchiveResponse.class);
return getHiddenTokensToLeft(tokenIndex - 1);
public boolean equals(Object obj) {     if (obj == this) return true;     if (obj == null || getClass() != obj.getClass() || !(obj instanceof AutomatonQuery)) return false;     AutomatonQuery other = (AutomatonQuery) obj;     if (m_term == null) return false;     return m_compiled.equals(other.m_compiled) && m_term.equals(other.m_term); }
List<SpanQuery> spanQueries = new ArrayList<>();  for (WeightedSpanQuery wsq : weightBySpanQuery) {     spanQueries.add(new SpanOrQuery(wsq.getKey(), wsq.getBoost())); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand();
Field field = clazz.getDeclaredField(fieldName);
return invoke(DescribeEventSourceRequest.class, DescribeEventSourceResponse.class, request, options, DescribeEventSourceRequestMarshaller.class, DescribeEventSourceResponseUnmarshaller.class);
return invoke(GetDocumentAnalysisRequest.class, request, options, GetDocumentAnalysisResponse.class, GetDocumentAnalysisResponseUnmarshaller.class, GetDocumentAnalysisRequestMarshaller.class, RequestMarshaller.options, ResponseUnmarshaller.options);
return invoke(CancelUpdateStackRequest.class, CancelUpdateStackRequestMarshaller.options, CancelUpdateStackResponse.class, CancelUpdateStackResponseUnmarshaller.options, InvokeOptions.class);
ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request, InvokeOptions options) {      return new ModifyLoadBalancerAttributesResponse(          ResponseUnmarshaller.getInstance().unmarshall(              Invoke.getInstance().invoke(                  RequestMarshaller.getInstance().marshall(request),                  options              )          )      );  }
return invoke(SetInstanceProtectionRequest.class, request, SetInstanceProtectionResponse.class, options, SetInstanceProtectionRequestMarshaller.class, SetInstanceProtectionResponseUnmarshaller.class);
return invoke(ModifyDBProxyRequest.class, ModifyDBProxyRequestMarshaller.options(), ModifyDBProxyResponse.class, ModifyDBProxyResponseUnmarshaller.options());
public void add(int offset, int len, int endOffset, int posLength) {     if (outputs == null) outputs = new CharsRef[count + 1];     if (posLengths == null) posLengths = new int[count + 1];     if (endOffsets == null) endOffsets = new int[count + 1];     if (count == posLengths.length) {         posLengths = ArrayUtil.oversize(posLengths, NUM_BYTES_INT32);         endOffsets = ArrayUtil.oversize(endOffsets, NUM_BYTES_INT32);         outputs = ArrayUtil.oversize(outputs, NUM_BYTES_OBJECT_REF);     }     posLengths[count] = posLength;     endOffsets[count] = endOffset;     outputs[count] = new CharsRef(offset, len);     count++; }
public enum Protocol {     HTTPS }
return objects.exists(boolean.class);
FilterOutputStream out = (FilterOutputStream) out;
public class ScaleClusterRequest {      public static final String METHOD = "PUT";      public static final String URI_PATTERN = "/";      public enum MethodType {          PUT      }  }
public class CreateTimeConstraint implements IDataValidationConstraint { public CreateTimeConstraint(String formula1, String formula2, int operatorType) {} }
return invoke(ListObjectParentPathsRequest.class, ListObjectParentPathsResponse.class, request, options, ListObjectParentPathsRequestMarshaller.class, ListObjectParentPathsResponseUnmarshaller.class);
DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { return (DescribeCacheSubnetGroupsResponse) invoke(request, new InvokeOptions().withRequestHandler(DescribeCacheSubnetGroupsRequest::marshaller).withResponseHandler(DescribeCacheSubnetGroupsResponse::unmarshaller)); }
public void setSharedFormula(boolean flag) { setShortBoolean(field_5_options, flag); }
public boolean IsReuseObjects() { return reuseObjects; }
return addErrorNode(new ErrorNodeImpl((IToken) badToken, (Parent) t));
if (args.size() > 0) throw new ArgumentException("args" + " " + args, String.class, (Map<String, String>) null);
return invoke("RemoveSourceIdentifierFromSubscription", RemoveSourceIdentifierFromSubscriptionRequest.class, RemoveSourceIdentifierFromSubscriptionResponse.class,      new RemoveSourceIdentifierFromSubscriptionRequestMarshaller(options),      new RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller(options));
public class TokenFilterFactory { public static TokenFilterFactory newInstance(Map<String, String> args) { return new TokenFilterFactory(args); } }
public class AddAlbumPhotosRequest extends Protocol {     public static final ProtocolType PROTOCOL_TYPE = ProtocolType.HTTPS; }
return invoke(GetThreatIntelSetRequest.class, GetThreatIntelSetRequest.getMarshaller(), GetThreatIntelSetResponse.class, GetThreatIntelSetResponse.getUnmarshaller(), options);
return new Binary.AndTreeFilter((TreeFilter) a.clone(), (TreeFilter) b.clone());
return o instanceof Object;
protected boolean hasArray() { return hasArray; }
return invoke(new UpdateContributorInsightsRequest(), new UpdateContributorInsightsRequestMarshaller(), new UpdateContributorInsightsResponseUnmarshaller(), options);
UnwriteProtectWorkbook { void removeWriteProtect(); void removeFileShare(); void removeRecords(); }
public SolrSynonymParser(boolean expand, Analyzer analyzer, boolean dedup) { super(expand, analyzer, dedup); }
RequestSpotInstancesRequest request = new RequestSpotInstancesRequest();  InvokeOptions options = new InvokeOptions();  options.setResponseUnmarshaller(new RequestSpotInstancesResponseUnmarshaller());  options.setRequestMarshaller(new RequestSpotInstancesRequestMarshaller());  return client.invoke(RequestSpotInstances.class, request, options).getResponse().getResultAs( RequestSpotInstancesResponse.class );
return ObjectData.findObjectRecord((byte[]) data);
return invoke(GetContactAttributesRequest.class, GetContactAttributesResponse.class, request, options, GetContactAttributesRequestMarshaller.class, GetContactAttributesResponseUnmarshaller.class);
return GetKey() + " " + GetValue().ToString();
ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) {      return Invoke.invoke(InvokeOptions.builder()         .request(request, ListTextTranslationJobsRequestMarshaller.getInstance())         .responseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance())         .build());  }
return invoke(GetContactMethodsRequest.class, request, options, GetContactMethodsRequestMarshaller.class, GetContactMethodsResponse.class, GetContactMethodsResponseUnmarshaller.class);
return GetFunctionByNameInternal.getInstance().getFunctionMetadataIndex(name) != null ? GetFunctionByNameInternal.getInstance().getFunctionMetadataIndex(name) - 1 : -1;
return invoke(DescribeAnomalyDetectorsRequest.class, DescribeAnomalyDetectorsResponse.class, request, options, DescribeAnomalyDetectorsRequestMarshaller.class, DescribeAnomalyDetectorsResponseUnmarshaller.class);
return new InsertId(message, changeId, (ObjectId) id);
public long getObjectSize(int typeHint, AnyObjectId objectId) {      long sz = getObjectSize(db, objectId.copy(), "");      if (sz == 0) throw new MissingObjectException(objectId);      if (OBJ_ANY != typeHint) throw new MissingObjectException(objectId);      return sz;  }
ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { return new Invoke<>(request, ImportInstallationMediaRequest::marshaller, ImportInstallationMediaResponse::unmarshaller, options).execute(); }
return invoke(PutLifecycleEventHookExecutionStatusRequest.class, PutLifecycleEventHookExecutionStatusRequestMarshaller.options(), PutLifecycleEventHookExecutionStatusResponse.class, PutLifecycleEventHookExecutionStatusResponseUnmarshaller.options(), InvokeOptions.builder().build());
} { ;  double field_1_value = ((ILittleEndianInput) in1).readDouble();
return invoke(GetFieldLevelEncryptionConfigRequest.class, GetFieldLevelEncryptionConfigResponse.class, request, options, GetFieldLevelEncryptionConfigRequestMarshaller.getInstance(), GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance());
return invoke(DescribeDetectorRequest.class, DescribeDetectorResponse.class, request, DescribeDetectorRequestMarshaller.options(), DescribeDetectorResponseUnmarshaller.options());
ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { return new Invoke<>(request, Instance.ReportInstanceStatusRequestMarshaller.getInstance(), Instance.ReportInstanceStatusResponseUnmarshaller.getInstance(), new InvokeOptions()).execute(); }
return invoke(DeleteAlarmRequest.class, new DeleteAlarmRequestMarshaller(options), DeleteAlarmResponse.class, new DeleteAlarmResponseUnmarshaller(options));
return new PortugueseStemFilter(input).createTokenStream();
byte[] reserved = new byte[FtCblsSubRecord.ENCODED_SIZE];
synchronized (mutex) {      return object.remove(c);  }
return invoke(GetDedicatedIpRequest.class, GetDedicatedIpRequestMarshaller.options(), GetDedicatedIpResponse.class, GetDedicatedIpResponseUnmarshaller.options());
return precedence + " ";
return invoke(ListStreamProcessorsRequest.class, ListStreamProcessorsRequestMarshaller.options(), ListStreamProcessorsResponse.class, ListStreamProcessorsResponseUnmarshaller.options(), InvokeOptions.class);
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) {
public WindowProtectRecord(int options) { this._options = options; }
new int[bufferSize];
return invoke(GetOperationsRequest.class, GetOperationsRequest.getMarshaller(), GetOperationsResponse.class, GetOperationsResponse.getUnmarshaller(), options);
CopyRawTo void { ) , ( ; ; ; ; ; o int b byte ) , , ( ; EncodeInt32 . NB ) , , ( ; EncodeInt32 . NB ) , , ( ; EncodeInt32 . NB ) , , ( ; EncodeInt32 . NB ) , , ( ; EncodeInt32 . NB ] [ 16 + o 12 + o 8 + o 4 + o
public class WindowOneRecord {     private int field_1_h_hold;     private int field_2_v_hold;     private int field_3_width;     private int field_4_height;     private int field_5_options;     private int field_6_active_sheet;     private int field_7_first_visible_tab;     private int field_8_num_selected_tabs;     private int field_9_tab_width_ratio;      public WindowOneRecord(RecordInputStream in1) {         this.field_1_h_hold = in1.readShort();         this.field_2_v_hold = in1.readShort();         this.field_3_width = in1.readShort();         this.field_4_height = in1.readShort();         this.field_5_options = in1.readShort();         this.field_6_active_sheet = in1.readShort();         this.field_7_first_visible_tab = in1.readShort();         this.field_8_num_selected_tabs = in1.readShort();         this.field_9_tab_width_ratio = in1.readShort();     } }
return invoke(StopWorkspacesRequest.class, request, options, StopWorkspacesResponse.class, StopWorkspacesResponseUnmarshaller.instance, StopWorkspacesRequestMarshaller.instance);
try {    if (isOpen()) {      try {        truncate(channel);      } finally {        channel.close();      }      try {        dump();      } finally {        fos.close();      }    }  } catch (IOException e) {  }
DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { return (DescribeMatchmakingRuleSetsResponse) new Invoke.Instance().invoke(new DescribeMatchmakingRuleSetsRequestMarshaller().marshall(request), new DescribeMatchmakingRuleSetsResponseUnmarshaller().unmarshall, new InvokeOptions()); }
public String getPronunciation(int wordId, int[] charArray, int off, int len) { if (len == 0) return null; }
return pathStr;
double[] r = null; if (v != null) { double s = 0; for (int i = 0; i < n; i++) { s += v[i]; } double m = Double.NaN; if (n > 0) { m = s / n; } for (int i = 0; i < n; i++) { v[i] = v[i] - m; } r = v; } return r;
return invoke(DescribeResizeRequest.class, DescribeResizeResponse.class, request, options, DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance());
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int } { ) ( ; return end ) (
for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) {      IRow currentRow = ctx.getSheet().getRow(rowNumber);      if (currentRow == null) {          if (!traverseEmptyCells) {              continue;          }          currentRow = new SimpleCellWalkContext(ctx, rowNumber, null);      }      for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) {          ICell currentCell = currentRow.getCell(colNumber);          if (currentCell == null) {              if (!traverseEmptyCells) {                  continue;              }              currentCell = new SimpleCellWalkContext(ctx, rowNumber, colNumber);          }          handler.onCell(currentCell, new SimpleCellWalkContext(ctx, rowNumber, colNumber, currentRow, currentCell, width));      }  }
return _ReadIndex;
public int compareTo(ScoreTerm other) {      if (term.bytesEquals(other.term)) {          if (boost == other.boost) return 0;          else return boost < other.boost ? -1 : 1;      } else {          return term.compareTo(other.term);      }  }
int normalize(char[] s) { int len = s.length; for (int i = 0; i < len; ) { switch (s[i]) { case '\u064B': // HAMZA_ABOVE case '\u0650': // HEH_GOAL case '\u0647': // HEH case '\u064A': // YEH case '\u0643': // KAF case '\u064a': // FARSI_YEH case '\u062F': // DAL case '\u0630': // THAL case '\u0637': // TAH case '\u0636': // ZAH case '\u0638': // ZAH case '\u0639': // AYIN case '\u063a': // GHAIN case '\u0621': // HAMZA case '\u0629': // TAT MARBUKAH case '\u0626': // HAMZA_YEH case '\u0623': // ALEF_HAMZA_ABOVE case '\u0624': // HAMZA_WAW case '\u0628': // BAA case '\u062a': // TAA case '\u062b': // THA case '\u062c': // JIM case '\u062d': // HAA case '\u062d': // HAA case '\u062e': // KHA case '\u062f': // DAL case '\u0631': // RAA case '\u0633': // SEEN case '\u0634': // SHEEN case '\u0635': // SAD case '\u0636': // DAD case '\u0637': // TAH case '\u0638': // ZAH case '\u0639': // AYIN case '\u063a': // GHAIN case '\u0641': // FA case '\u0642': // QAF case '\u0643': // KAF case '\u0644': // LAM case '\u0645': // MEEM case '\u0646': // NOON case '\u0647': // HEH case '\u0648': // WAW case '\u064a': // YEH case '\u0651': // TASHKEEL case '\u0652': // TASHKEEL case '\u0653': // TASHKEEL case '\u0654': // TASHKEEL case '\u0655': // TASHKEEL case '\u0656': // TASHKEEL case '\u0657': // TASHKEEL case '\u0658': // TASHKEEL case '\u0659': // TASHKEEL case '\u065A': // TASHKEEL case '\u065B': // TASHKEEL case '\u065C': // TASHKEEL case '\u065D': // TASHKEEL case '\u065E': // TASHKEEL default: s[i] = StemmerUtil.delete(s, i, len); --len; break; } ++i; } return len; }
void serialize(ILittleEndianOutput out1) { out1.writeShort((short) 0); }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(KeyType keyType, String attributeName) { this.keyType = keyType; this.attributeName = attributeName; }
return invoke(GetAssignmentRequest.class, GetAssignmentRequest.getMarshaller(), GetAssignmentResponse.class, GetAssignmentResponse.getUnmarshaller(), options);
return id != -1 && findOffset(id) != null;
return allGroups;
synchronized void setMultiValued(String dimName, boolean v) {      if (fieldTypes.TryGetValue(dimName, out DimConfig fieldType)) {          if (v != fieldType.IsMultiValued) {              fieldTypes.put(dimName, new DimConfig(fieldType, v));          }      }  }
for (char c : e.cmd.toCharArray()) {      if (c >= '0' && c <= '9') {          size++;      }  } return size;
return invoke(new DeleteVoiceConnectorRequest(), new DeleteVoiceConnectorRequestMarshaller(), new DeleteVoiceConnectorResponseUnmarshaller(), options);
return invoke(new DeleteLifecyclePolicyRequest(), new DeleteLifecyclePolicyRequestMarshaller(), new DeleteLifecyclePolicyResponseUnmarshaller(), options);
