public void serialize(ILittleEndianOutput out1) { out1.writeShort(this.out1); }
if (tailBlkIdx.src != 0) list.addAll(tailBlock.src); for (int srcDirIdx = 0; srcDirIdx < tailDirIdx.src; srcDirIdx++) list.addAll(directory.src[srcDirIdx]); if (size.src == 0) return; BlockList.Util.NGit.AddAll(list, src);
}  public void WriteByte(byte b) {   if (upto.get() == blockSize.get()) {     if (currentBlock.get() != null) {       blocks.get().add(currentBlock.get());     }     currentBlock.set(new byte[blockSize.get()]);     upto.set(0);     blocks.get().add(blockEnd.get());   }   currentBlock.get()[upto.get()] = b;   upto.incrementAndGet(); }
public ObjectId getObjectId() { return objectId; }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.instance).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.instance);  DeleteDomainEntryResponse response = (DeleteDomainEntryResponse) client.invoke(DeleteDomainEntry.class, request, options);
public final long RamBytesUsed() { return GetSizeInBytes(fst) == 0 ? null : fst; }
return RawParseUtils.decode(enc, buffer, 0, msgB.length, TagMessage.Empty);
); null _root; ) (ArrayList _documents = new ArrayList(); ) (PropertyTable _property_table = new PropertyTable(); ) (HeaderBlock headerBlock = new HeaderBlock(); ) (POIFSFileSystem
assert Debug; int address = offset0; ByteBlockPool pool = Buffers.pool; assert null != slice; int upto = slice.length();
public void setPath(String path) { this.path = path; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {      request = beforeClientExecution(request);      return executeListIngestions(request);  }
public void SwitchTo(int lexState, ICharStream stream) { this.lexState = lexState; this.inputStream = stream; }
return getShardIteratorResponse(getShardIteratorRequest(options), new InvokeOptions().withRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()).withResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()));
public ModifyStrategyRequest post(MethodType method) { return new ModifyStrategyRequest("","","","",""); }
try {      synchronized (lock) {          if (in == null) {              throw new IOException("");          }          if (!in.hasRemaining() || in.available() > 0) {              return 0;          }      }  } catch (IOException e) {      throw new RuntimeException(e);  };  return
public EscherOptRecord getOptRecord() {
public int read(byte[] buffer, int offset, int length) throws IOException {      if (buffer == null) {          throw new NullPointerException();      }      checkOffsetAndCount(buffer.length, offset, length);      if (length == 0) {          return 0;      }      synchronized (this) {          int pos = this.pos;          int copylen = Math.min(length, size - pos);          if (copylen < 0) {              copylen = 0;          }          for (int i = 0; i < copylen; i++) {              buffer[offset + i] = (byte) (unchecked(buffer[pos + i]));          }          this.pos += copylen;          return copylen;      }  }
); sentenceOp sentenceOp . {  ) sentenceOp new OpenNLPSentenceBreakIterator(sentenceOp, new NLPSentenceDetectorOp
System.out.println(str != null ? str : "null");
public void functionName(String cause) throws NotImplementedException {      throw new NotImplementedFunctionException(cause);  }
return nextEntry.value();
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) {      int available = bufferPosition;      if(available <= 0) {          available = refill(useBuffer);      }      if(available > 0) {          int bufferLength = buffer.Length;          long after = bufferStart + bufferLength;          if(len > after - bufferPosition) {              throw new EndOfStreamException("End of stream reached");          }          if(bufferSize < len && useBuffer) {              BlockCopy.Buffer(buffer, bufferPosition, b, offset, len);              bufferPosition += len;          } else {              if(len < bufferLength) {                  BlockCopy.Buffer(buffer, bufferPosition, b, offset, len);                  bufferPosition += len;              } else {                  long afterCopy = bufferStart + bufferPosition + len;                  if(afterCopy > after) {                      throw new EndOfStreamException("End of stream reached");                  }                  BlockCopy.Buffer(buffer, bufferPosition, b, offset, len);                  bufferPosition = (int) (bufferPosition + len);              }          }      }  }
return new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.getInstance()).withResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()).withRequest(new TagQueueRequest()).invoke(TagQueueResponse.class);
throw new NotSupportedException();
return new ModifyCacheSubnetGroupResponse(ModifyCacheSubnetGroupResponseUnmarshaller.INSTANCE.unmarshall(this), new ModifyCacheSubnetGroupRequestMarshaller().marshall(request), options);
StringTokenizer st = new StringTokenizer(params, " "); SetParams(st);
return invoke(new DeleteDocumentationVersionRequest(), new DeleteDocumentationVersionResponse(), options, DeleteDocumentationVersionRequestMarshaller.getInstance(), DeleteDocumentationVersionResponseUnmarshaller.getInstance());
public boolean equals(Object obj) {     if (this == obj) return true;     if (obj == null || getClass() != obj.getClass()) return false;     FacetLabel facetLabel = (FacetLabel) obj;     if (facetLabel.Length != Length) return false;     for (int i = Length - 1; i >= 0; i--) {         if (Components[i].equals(facetLabel.Components[i], StringComparison.Ordinal)) return false;     }     return true; }
return GetInstanceAccessDetailsResponse.getInstance().invoke(GetInstanceAccessDetailsRequest.getInstance(), new InvokeOptions().withRequest(GetInstanceAccessDetailsRequestMarshaller.getInstance().marshall(request)).withResponse(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()));
); shape return; ) (onCreate; ) (add.shapes; anchor Anchor.shape; Parent.shape; ), (HSSFPolygon new = shape HSSFPolygon { ) anchor HSSFChildAnchor (createPolygon HSSFPolygon
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetName(); }
public GetDashboardResult getDashboard(GetDashboardRequest request) {      request = beforeClientExecution(request);      return executeGetDashboard(request);  }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.instance).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.instance); AssociateSigninDelegateGroupsWithAccountRequest request = new AssociateSigninDelegateGroupsWithAccountRequest(); return invoke(AssociateSigninDelegateGroupsWithAccount.class, request, options);
public void addMultipleBlanks() { for (int j = firstColumn.mbr; j < numColumns.mbr; j++) { BlankRecord br = new BlankRecord(); br.row = row.mbr; br.column = firstColumn.mbr + j; br.xfIndex = getXFAt(j); addRecord(br); } }
StringBuilder sb = new StringBuilder(); int apos = 0; while (apos < string.length()) { int k = string.indexOf('\\', apos); if (k == -1) { sb.append(string, apos, string.length()); break; } sb.append(string, apos, k); sb.append("\\\\"); apos = k + 1; } return sb.toString();
throw new ReadOnlyBufferException() { putInt(ByteBuffer.newInstance(), (int) value) };
int c = 0; for (; c < nColumns; c = 0) {} ; int r = 0; for (; r < nRows; r++) {} Object vv = new Object[_nRows * _nColumns]; Object[] values2d = (Object[]) vv; int nColumns = values2d.length; int nRows = values2d[0].length; short _nColumns = (short) nColumns; short _nRows = (short) nRows; int _reserved0Int = 0; short _reserved1Short = 0; int _reserved2Byte = 0; Object[] rowData = (Object[]) ArrayPtg.GetValueIndex(values2d);
return Invoke(new GetIceServerConfigRequest(), GetIceServerConfigResponse.class, options, RequestMarshaller.GetIceServerConfigRequestMarshaller(), ResponseUnmarshaller.GetIceServerConfigResponseUnmarshaller());
StringBuilder sb = new StringBuilder(); sb.Append(GetType().Name); sb.Append(" "); sb.Append(GetValueAsString()); return sb.ToString();
return "} " + _parentQuery + " " + field.toString();
public synchronized int incrementAndGet() { return refCount.incrementAndGet(); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.instance).withResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.instance);
return (int) (GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE + GetNextXBATChainOffset());
public void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) tp = GetInstance().TenPower(Math.Abs(pow10)); else if (pow10 > 0) tp = mulShift(tp, _multiplierShift.tp); }
StringBuilder builder = new StringBuilder();  int length = path.Length;  for (int i = 0; i < length; i++)  {      builder.Append(path[i] == DirectorySeparatorChar ? path[i] : path.GetComponent(i));      if (i < length - 1)      {          builder.Append(DirectorySeparatorChar);      }  }  return builder.ToString();
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; }
public void setProgressMonitor(ProgressMonitor pm) {}
public void reset() {      if (ptr == 0) {          if (first != null) {              if (!eof) {                  parseEntry();              }          }      }  }
throw new NoSuchElementException(); return previous(); if (previousIndex < start) throw new NoSuchElementException(); return previous();
return newPrefix.toString();
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1;
List<CharsRef> stems = new ArrayList<>(); for (CharsRef term : terms) { if (!stemmerDictionary.contains(term)) { stems.add(term); } } return stems.size() < 2 ? null : new CharArraySet(stems, false);
return (GetGatewayResponsesResponse)Invoke(new GetGatewayResponsesRequestMarshaller().options, new GetGatewayResponsesResponseUnmarshaller().options, request);
public void setPosition(long position) {      int currentBlockIndex = (int) (position >> blockBits.outerInstance);     int currentBlockUpto = (int) (currentBlockIndex + 1) * blockMask.outerInstance;     int currentBlock = blocks.outerInstance[currentBlockIndex]; }
long s = Math.max(Math.min(n - skip, max), 0);
public void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
public void serialize(ILittleEndianOutput out1) {      if (field_7_padding != null) {          out1.writeByte(Convert.ToInt32(field_7_padding, CultureInfo.InvariantCulture));      } else {          out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);      }      out1.writeShort(field_6_author.length);      StringUtil.putUnicodeLE(out1, field_6_author);      out1.writeShort(0);      out1.writeShort(0);      out1.writeShort(0);      StringUtil.putCompressedUnicode(out1, null);  }
return ((String)string).lastIndexOf();
addLastImpl((E) object, return);
public class ConfigSnapshot {     public void UnsetSection(String section, String subsection) {         while (!CompareAndSet.state(Get.state(src), res)) {             res = UnsetSection;         }     } }
public String getTagName() { return tagName; }
public void addSubRecord(int index, SubRecord element) { Insert.subrecords.add(index, element); }
synchronized (mutex) { return object; }
return new DoubleMetaphoneFilter(input, TokenStream.create());
return getInCoreLength() > 0 ? getInCoreLength() : getLength();
public void setValue(boolean newValue) {}
public Pair<ContentSource, ContentSource> newSource( ContentSource newSource, ContentSource oldSource ) {
if (i <= count) throw new IndexOutOfBoundsException();
public final void put(MethodType method, String uriPattern) { this.createRepoRequest = new CreateRepoRequest(method, uriPattern, " ", " ", " ", " ", " "); }
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
throw new ConcurrentModificationException(); else throw new InvalidOperationException();  ++modCount; --_size; ++expectedModCount;  Link lastLink = null;  Link previous_1 = link.previous;  Link next_1 = link.next;  previous_1.next = next_1;  next_1.previous = previous_1;  previous.lastLink = previous_1;  next.lastLink = next_1;  if (lastLink != null) {      if (modCount == expectedModCount) {          --pos;      }  }  void remove()
public MergeShardsResult mergeShards(MergeShardsRequest request) { request = beforeClientExecution(request); return executeMergeShards(request); }
AllocateHostedConnectionResponse response = new AllocateHostedConnectionResponse(Invoke(new AllocateHostedConnectionRequest(), options, AllocateHostedConnectionRequestMarshaller.Instance, AllocateHostedConnectionResponseUnmarshaller.Instance));
public int getBeginIndex() { return 0; }
public WeightedTerm[] getTerms() { return (WeightedTerm[]) query.getTerms(); }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) {      byte byte0 = blocks[blocksOffset++];      int value0 = (byte0 & 0x03) << 2 | (byte1 >> 6) & 0x03;      values[valuesOffset++] = value0;      byte1 = blocks[blocksOffset++];      int value1 = (byte1 & 0x0F) << 4 | (byte2 >> 4) & 0x0F;      values[valuesOffset++] = value1;      byte2 = blocks[blocksOffset++];      values[valuesOffset++] = ((byte2 >> 0) & 0x0F) | ((byte0 & 0x3C) << 0);  }
String getHumanishName(String s) {      if (s == null || s.equals("")) throw new ArgumentException();      String[] elements = s.split(" ");      if (elements.length == 1) return s;      if (elements[elements.length - 1].equals(".")) elements = Arrays.copyOf(elements, elements.length - 1);      String result = elements[elements.length - 1];      if (result.endsWith(".git")) result = result.substring(0, result.length() - ".git".length());      if (result.equals("")) result = elements[elements.length - 2] + " " + elements[elements.length - 1];      return result;  }
InvokeOptions invokeOptions = new InvokeOptions().withRequest(new DescribeNotebookInstanceLifecycleConfigRequest()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()).withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance());
public String getAccessKeySecret() {
return Invoke(CreateVpnConnectionRequest.class, request, options, CreateVpnConnectionResponse.class, CreateVpnConnectionRequestMarshaller.instance, CreateVpnConnectionResponseUnmarshaller.instance);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeVoicesRequestMarshaller.instance).withResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.instance);
return invoke(ListMonitoringExecutionsRequest.class, ListMonitoringExecutionsResponse.class, request, options, ListMonitoringExecutionsRequestMarshaller.INSTANCE, ListMonitoringExecutionsResponseUnmarshaller.INSTANCE);
public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
return getEscherRecord((int) index);
return Invoke(new GetApisRequest(), options, GetApisResponse.class, GetApisRequestMarshaller.INSTANCE, GetApisResponseUnmarshaller.INSTANCE);
return Invoke(new DeleteSmsChannelRequest(), new DeleteSmsChannelResponse(), options, DeleteSmsChannelRequestMarshaller.getInstance(), DeleteSmsChannelResponseUnmarshaller.getInstance());
return getTrackingRefUpdate();
System.out.println(toString());
return getChildren();
); workdirTreeIndex index = new workdirTreeIndex(NotIgnoredFilter.INSTANCE);
public AreaRecord() {}  public AreaRecord(RecordInputStream in1) {      field_1_formatFlags = in1.readShort();  }
public enum ProtocolType { HTTPS, Protocol; }  public class SomeClass {      public GetThumbnailRequest(String a, String b, String c, String d, String e) {} }
return invoke(DescribeTransitGatewayVpcAttachmentsRequest.class, DescribeTransitGatewayVpcAttachmentsResponse.class, request, options, DescribeTransitGatewayVpcAttachmentsRequestMarshaller.class, DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.class);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()).withResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance());
OrdRange ordRange; if (map.tryGetValue(prefix, ordRange)) { return ordRange; } return getOrdRange(dim);
throw new LexerNoViableAltException(this, getInputStream(), getCurrentToken(), String.Format(CultureInfo.CurrentCulture, "No viable alternative at input '%s'", getText(Interval.of(startIndex, getInputStream().size() - 1))), getTokenSource(), getInputStream(), getContext());
public E peekFirstImpl() { return peek(); }
return Invoke(CreateWorkspacesRequest.class, RequestMarshaller.CreateWorkspacesRequestMarshaller.getInstance(), CreateWorkspacesResponse.class, ResponseUnmarshaller.CreateWorkspacesResponseUnmarshaller.getInstance(), options);
return new NumberFormatIndexRecord((NumberFormatIndexRecord)rec).clone();
InvokeOptions options = new InvokeOptions().withRequest(DescribeRepositoriesRequest).withResponse(DescribeRepositoriesResponse.class).withRequestMarshaller(DescribeRepositoriesRequestMarshaller.instance).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.instance);
public SparseIntArray(int initialCapacity) { mKeys = new int[idealIntArraySize(initialCapacity)]; mValues = new int[idealIntArraySize(initialCapacity)]; mSize = 0; }
return new HyphenatedWordsFilter(input);
return new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()).withRequest(CreateDistributionWithTagsRequest.createDistributionWithTagsRequest(options));
throw new NotImplementedException();
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.instance).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.instance); DeleteWorkspaceImageRequest request = new DeleteWorkspaceImageRequest(); return invoke(DeleteWorkspaceImageResponse.class, request, options);
public long toHex(int value) { return Long.parseLong(Integer.toHexString(value), 16); }
return invoke(UpdateDistributionRequest.class, UpdateDistributionResponse.class, options, UpdateDistributionRequestMarshaller.instance, UpdateDistributionResponseUnmarshaller.instance);
return index == HSSFColor.Index.Automatic ? HSSFColor.getInstance().getAutomatic() : palette != null ? palette[(byte)index] : new CustomColor((short)index);
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) {out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index);}
public final DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest describeDBEngineVersionsRequest) { return new DescribeDBEngineVersionsResponse(); }
public FormatRun(short fontIndex, short character) { this.fontIndex = fontIndex; this.character = character; }
byte[] result = new byte[2 * length]; int resultIndex = 0; int offset = end - length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) ((ch >> 8) & 0xFF); result[resultIndex++] = (byte) (ch & 0xFF); }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {      request = beforeClientExecution(request);      return executeUploadArchive(request);  }
public IList<IToken> getHiddenTokensToLeft(int tokenIndex) { return ...; }
@Override public boolean equals(Object obj) {     if (this == obj) return true;     if (obj == null || getClass() != obj.getClass()) return false;     AutomatonQuery that = (AutomatonQuery) obj;     if (m_compiled != null) return m_compiled.equals(that.m_compiled);     if (m_term != null) return m_term.equals(that.m_term);     return true; }
List<SpanQuery> spanQueries = new ArrayList<>();  for (Map.Entry<String, Float> entry : wsq.entrySet()) {      SpanQuery spanQuery = MakeSpanClause(entry.getKey(), entry.getValue());      spanQueries.add(spanQuery);  }  if (spanQueries.size() == 1) {      return spanQueries.get(0);  }  return new SpanQuery[]{spanQueries.toArray(new SpanQuery[0])};
return new StashCreateCommand();
return fieldInfo != null ? fieldInfo : (FieldInfo) fieldInfosByName.tryGetValue(fieldName, out fieldInfo) ? fieldInfo : null;
return instance.invoke(DescribeEventSourceRequest.class, DescribeEventSourceResponse.class, options, DescribeEventSourceRequestMarshaller.INSTANCE, DescribeEventSourceResponseUnmarshaller.INSTANCE);
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) {      request = beforeClientExecution(request);      return executeGetDocumentAnalysis(request);  }
return this.invoke(CancelUpdateStackRequest.class, CancelUpdateStackResponse.class, options, RequestMarshaller.CancelUpdateStackRequestMarshaller.getInstance(), ResponseUnmarshaller.CancelUpdateStackResponseUnmarshaller.getInstance());
return new InvokeOptions().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()).withRequest(ModifyLoadBalancerAttributesRequest.class).withResponse(ModifyLoadBalancerAttributesResponse.class);
InvokeOptions options = new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance());
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) {request = beforeClientExecution(request);return executeModifyDBProxy(request);}
@Override public final void add(int offset, int len, int posLength, int endOffset, char[] output) {      if (outputs == null) outputs = new CharsRef[count + 1];      if (posLengths.length == count) posLengths = ArrayUtil.oversize(posLengths, NUM_BYTES_INT32, RamUsageEstimator);      if (endOffsets.length == count) endOffsets = ArrayUtil.oversize(endOffsets, NUM_BYTES_INT32, RamUsageEstimator);      if (outputs.length == count) outputs = ArrayUtil.oversize(outputs, NUM_BYTES_OBJECT_REF, RamUsageEstimator);      posLengths[count] = posLength;      endOffsets[count] = endOffset;      outputs[count] = new CharsRef(output, offset, len);      ++count;  }
public enum ProtocolType { HTTPS, Protocol, FetchLibrariesRequest }
public boolean exists() { return objects != null; }
public void serialize(LittleEndianOutput out) {out.writeInt(field_13_border_styles1);out.writeInt(field_14_border_styles2);}
public void put(MethodType method, String uriPattern, ScaleClusterRequest request)
public IDataValidationConstraint createTimeConstraint(String formula1, String formula2, int operatorType) { return new CreateTimeConstraint(formula1, formula2, operatorType); }
return new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()).withRequest(new ListObjectParentPathsRequest());
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); DescribeCacheSubnetGroupsRequest request = new DescribeCacheSubnetGroupsRequest(); DescribeCacheSubnetGroupsResponse response = client.invoke(DescribeCacheSubnetGroups.class, request, options).getResponse().getResult(DescribeCacheSubnetGroupsResponse.class);
public void setSharedFormula(short flag, boolean[] options) { this.field_5_options = flag; }
public boolean isReuseObjects() { return true; }
public void addErrorNode(IToken badToken) { t = new ErrorNodeImpl((Parent)t); addChild(t); return; }
if (args.size() > 0) { throw new ArgumentException(" " + args, null); }
return new InvokeOptions().withRequest(new RemoveSourceIdentifierFromSubscriptionRequest()).withResponse(new RemoveSourceIdentifierFromSubscriptionResponse()).withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.instance).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.instance).withOptions(options);
return new TokenFilterFactory((Map<String, String>) NewInstance.loader().forName(args).get(TokenFilterFactory.NAME));
}; HTTPS.ProtocolType protocol { ) " " , " " , " " , " " , " " ( : ) ( AddAlbumPhotosRequest
return this.invoke(new GetThreatIntelSetRequest(), GetThreatIntelSetRequest.getMarshaller(), GetThreatIntelSetResponse.getUnmarshaller(), options).getResponse();
return new AndTreeFilter((TreeFilter) a.clone(), (TreeFilter) b.clone());
@Override public boolean equals(Object o) {
protected boolean hasArray(HasArray return) {
return Invoke(new UpdateContributorInsightsRequest(), new UpdateContributorInsightsResponse(), options, RequestMarshaller.UpdateContributorInsightsRequestMarshaller(), ResponseUnmarshaller.UpdateContributorInsightsResponseUnmarshaller());
}; null writeProtect; null fileShare; ) (Remove.records; ) (Remove.records { ) (UnwriteProtectWorkbook void
public SolrSynonymParser(boolean analyzer, boolean expand, boolean dedup) { }
RequestSpotInstancesResponse response = new RequestSpotInstancesRequest().invoke(RequestSpotInstancesRequestMarshaller.options, RequestSpotInstancesResponseUnmarshaller.options);
return (ObjectData) findObjectRecord(getObjectData());
InvokeOptions options = new InvokeOptions().setRequestMarshaller(GetContactAttributesRequest.getMarshaller()).setResponseUnmarshaller(GetContactAttributesResponse.getUnmarshaller()); GetContactAttributesResponse response = client.invoke(GetContactAttributesRequest.class, options).getResponse().getResult(GetContactAttributesResponse.class);
return GetKey + " " + GetValue.toString();
return new ListTextTranslationJobsResponse(Invoke.OPTIONS, ListTextTranslationJobsRequestMarshaller.INSTANCE.marshall(request), ListTextTranslationJobsResponseUnmarshaller.INSTANCE.unmarshall(this));
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) {      request = beforeClientExecution(request);      return executeGetContactMethods(request);  }
public short GetInstance(FunctionMetadata fd) { return (null == fd) ? (short)-1 : GetFunctionByNameInternal(LookupIndexByName(fd.name)); }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
public InsertId insert(String message, ObjectId changeId) { return new InsertId(message, changeId); }
if (typeHint == OBJ_ANY) { if (sz > 0) { long db = GetObjectSize(objectId, typeHint); } else { throw new MissingObjectException(""); } } else { throw new MissingObjectException(""); }
return Invoke(ImportInstallationMediaRequestMarshaller.Instance.options, ImportInstallationMediaResponseUnmarshaller.Instance.options, new ImportInstallationMediaRequest());
return invoke(PutLifecycleEventHookExecutionStatusRequest.class, PutLifecycleEventHookExecutionStatusResponse.class, request, options, PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance(), PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance());
); ReadDouble.in1(field_1_value) { in1(ILittleEndianInput(NumberPtg
return this.invoke(GetFieldLevelEncryptionConfigRequest.getFieldLevelEncryptionConfigRequest(), GetFieldLevelEncryptionConfigResponse.getFieldLevelEncryptionConfigResponseUnmarshaller(), new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()));
return new InvokeOptions().withRequest(DescribeDetectorRequest.marshaller().marshall(request)).withResponse(DescribeDetectorResponse.unmarshaller()).withOptions(options);
ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request, InvokeOptions options)
InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.instance).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.instance); DeleteAlarmResponse response = (DeleteAlarmResponse) client.invoke(DeleteAlarm.class, request, options);
return new PortugueseStemFilter(input).createTokenStream();
public final class FtCblsSubRecord extends Structure { public static final int ENCODED_SIZE = 0; protected byte[] reserved = new byte[0]; }
public synchronized boolean remove(Object object) {      return mutex.lock().tryLock() ? false : (mutex.unlock(), true);  }
return invoke(GetDedicatedIpRequest.class, GetDedicatedIpResponse.class, request, options, GetDedicatedIpRequestMarshaller.class, GetDedicatedIpResponseUnmarshaller.class);
@Override public String toString() { return " "; }
return invoke(ListStreamProcessorsRequestMarshaller.options, ListStreamProcessorsResponseUnmarshaller.options, new ListStreamProcessorsRequest()).getListStreamProcessorsResponse();
public class DeleteLoadBalancerPolicyRequest { private String policyName; private String loadBalancerName; public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; } }
protected int[] options = new int[WindowProtectRecord];
public UnbufferedCharStream(char[] data, int n, int bufferSize) { this.data = data; this.n = n; this.bufferSize = bufferSize; }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); GetOperationsRequest request = new GetOperationsRequest(); GetOperationsResponse response = (GetOperationsResponse) client.invoke(Operation.getOperations, request, options).getResponse();
CopyRawTo(b, 0, EncodeInt32.NB); CopyRawTo(b, 4, EncodeInt32.NB); CopyRawTo(b, 8, EncodeInt32.NB); CopyRawTo(b, 12, EncodeInt32.NB); CopyRawTo(b, 16, EncodeInt32.NB);
public WindowOneRecord(RecordInputStream in1) {     field_1_h_hold = in1.readShort();     field_2_v_hold = in1.readShort();     field_3_width = in1.readShort();     field_4_height = in1.readShort();     field_5_options = in1.readShort();     field_6_active_sheet = in1.readShort();     field_7_first_visible_tab = in1.readShort();     field_8_num_selected_tabs = in1.readShort();     field_9_tab_width_ratio = in1.readShort(); }
InvokeOptions options = new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.instance).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.instance); StopWorkspacesResponse response = client.invoke(StopWorkspaces.class, request, options).getResponse();
try {      // dump  } finally {      try {          channel.truncate();      } finally {          try {              channel.close();          } finally {              fos.close();          }      }  }  if (isOpen())      throw new IOException();  void close()
DescribeMatchmakingRuleSetsResponse response = (DescribeMatchmakingRuleSetsResponse) new DescribeMatchmakingRuleSetsRequest().marshall().invoke(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance(), RequestMarshaller.getInstance().marshall(new DescribeMatchmakingRuleSetsRequest(), options));
char[] GetPronunciation(int wordId, int off, int len, char[] surface) { return null; }
public String getPath() { return pathStr; }
double r = Double.NaN;  double m = 0;  double s = 0;  int n = v.length;  if (n >= 1 && v != null) {      for (int i = 0; i < n; ++i) {          m += v[i];      }      m /= n;      for (int i = 0; i < n; ++i) {          s += Math.pow(v[i] - m, 2);      }      r = Math.sqrt(s / (n - 1));  }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {      request = beforeClientExecution(request);      return executeDescribeResize(request);  }
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }

public void traverse(ICellHandler handler) {      IRow currentRow = null;      ICell currentCell = null;      SimpleCellWalkContext ctx = new SimpleCellWalkContext();      ctx.FirstRow = firstRow;      ctx.LastRow = lastRow;      ctx.FirstColumn = firstColumn;      ctx.LastColumn = lastColumn;      int width = 1 + firstColumn - lastColumn;      for (int rowNumber = ctx.FirstRow; rowNumber <= ctx.LastRow; rowNumber++) {          if (null == currentRow) {              currentRow = sheet.GetRow(rowNumber);          }          if (null == currentRow) {              continue;          }          ctx.RowNumber = rowNumber;          for (int colNumber = ctx.FirstColumn; colNumber <= ctx.LastColumn; colNumber++) {              if (null == currentCell) {                  currentCell = currentRow.GetCell(colNumber);              }              if (null == currentCell) {                  continue;              }              ctx.ColumnNumber = colNumber;              ctx.OrdinalNumber = 1 + firstRow - rowNumber + (1 + firstColumn - colNumber) * width;              if (IsEmpty(currentCell)) {                  traverseEmptyCells(ctx, handler);              }              currentCell = null;          }          currentRow = null;      }  }
public int GetReadIndex() { return _ReadIndex; }
public int compareTo(Term other) {      if (this.boost == other.boost) {          return BytesEquals.bytesEquals(term, other.term) ? 0 : term.compareTo(other.term);      } else {          return boost < other.boost ? -1 : 1;      }  }
for (int i = 0; i < len; ++i) { switch (s[i]) { case 'ھ': case 'ﻫ': case 'ﻩ': case 'ﯿ': case 'ﻱ': case 'ﯾ': case 'ﻙ': case 'ﮎ': break; default: break; } }
public void serialize(ILittleEndianOutput out) { out.writeShort(this.out1); }
public DiagnosticErrorListener exactOnly(boolean exactOnly) { this.exactOnly = exactOnly; return this; }
public enum KeyType { KEY_TYPE; } ; KeyType keyType; String attributeName;
return invoke(GetAssignmentRequest.class, request, GetAssignmentResponse.class, options, GetAssignmentRequestMarshaller.class, GetAssignmentResponseUnmarshaller.class);
public FindOffsetResult findOffset(HasObject id) { return findOffset(id, null); }
return;
public void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { DimConfig newConfig; if (fieldTypes.tryGetValue(dimName, out newConfig)) { newConfig.isMultiValued = v; } } }
} ; size = return ; } } ; ++size { ) 0 >= cmd.e ; if ( ; ) At = e Cell { ) Keys.cells in c char ; for ( ; 0 == size int { ) ( getCellsVal int
return new Invoke<>(DeleteVoiceConnectorRequest.class, DeleteVoiceConnectorResponse.class, options, RequestMarshaller.DeleteVoiceConnectorRequestMarshaller.getInstance(), ResponseUnmarshaller.DeleteVoiceConnectorResponseUnmarshaller.getInstance());
return new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.instance).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.instance).invoke(new DeleteLifecyclePolicyRequest(), DeleteLifecyclePolicyResponse.class);
