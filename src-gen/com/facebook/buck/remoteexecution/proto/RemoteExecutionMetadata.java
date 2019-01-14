// @generated
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/com/facebook/buck/remoteexecution/proto/metadata.proto

package com.facebook.buck.remoteexecution.proto;

/**
 * <pre>
 * This is propagated in GRPC header "re-metadata-bin".
 * </pre>
 *
 * Protobuf type {@code facebook.remote_execution.RemoteExecutionMetadata}
 */
@javax.annotation.Generated(value="protoc", comments="annotations:RemoteExecutionMetadata.java.pb.meta")
public  final class RemoteExecutionMetadata extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:facebook.remote_execution.RemoteExecutionMetadata)
    RemoteExecutionMetadataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RemoteExecutionMetadata.newBuilder() to construct.
  private RemoteExecutionMetadata(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RemoteExecutionMetadata() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RemoteExecutionMetadata(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            com.facebook.buck.remoteexecution.proto.RESessionID.Builder subBuilder = null;
            if (reSessionId_ != null) {
              subBuilder = reSessionId_.toBuilder();
            }
            reSessionId_ = input.readMessage(com.facebook.buck.remoteexecution.proto.RESessionID.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(reSessionId_);
              reSessionId_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            com.facebook.buck.remoteexecution.proto.BuckInfo.Builder subBuilder = null;
            if (buckInfo_ != null) {
              subBuilder = buckInfo_.toBuilder();
            }
            buckInfo_ = input.readMessage(com.facebook.buck.remoteexecution.proto.BuckInfo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(buckInfo_);
              buckInfo_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            com.facebook.buck.remoteexecution.proto.TraceInfo.Builder subBuilder = null;
            if (traceInfo_ != null) {
              subBuilder = traceInfo_.toBuilder();
            }
            traceInfo_ = input.readMessage(com.facebook.buck.remoteexecution.proto.TraceInfo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(traceInfo_);
              traceInfo_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_RemoteExecutionMetadata_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_RemoteExecutionMetadata_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata.class, com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata.Builder.class);
  }

  public static final int RE_SESSION_ID_FIELD_NUMBER = 1;
  private com.facebook.buck.remoteexecution.proto.RESessionID reSessionId_;
  /**
   * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
   */
  public boolean hasReSessionId() {
    return reSessionId_ != null;
  }
  /**
   * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
   */
  public com.facebook.buck.remoteexecution.proto.RESessionID getReSessionId() {
    return reSessionId_ == null ? com.facebook.buck.remoteexecution.proto.RESessionID.getDefaultInstance() : reSessionId_;
  }
  /**
   * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
   */
  public com.facebook.buck.remoteexecution.proto.RESessionIDOrBuilder getReSessionIdOrBuilder() {
    return getReSessionId();
  }

  public static final int BUCK_INFO_FIELD_NUMBER = 2;
  private com.facebook.buck.remoteexecution.proto.BuckInfo buckInfo_;
  /**
   * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
   */
  public boolean hasBuckInfo() {
    return buckInfo_ != null;
  }
  /**
   * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
   */
  public com.facebook.buck.remoteexecution.proto.BuckInfo getBuckInfo() {
    return buckInfo_ == null ? com.facebook.buck.remoteexecution.proto.BuckInfo.getDefaultInstance() : buckInfo_;
  }
  /**
   * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
   */
  public com.facebook.buck.remoteexecution.proto.BuckInfoOrBuilder getBuckInfoOrBuilder() {
    return getBuckInfo();
  }

  public static final int TRACE_INFO_FIELD_NUMBER = 3;
  private com.facebook.buck.remoteexecution.proto.TraceInfo traceInfo_;
  /**
   * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
   */
  public boolean hasTraceInfo() {
    return traceInfo_ != null;
  }
  /**
   * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
   */
  public com.facebook.buck.remoteexecution.proto.TraceInfo getTraceInfo() {
    return traceInfo_ == null ? com.facebook.buck.remoteexecution.proto.TraceInfo.getDefaultInstance() : traceInfo_;
  }
  /**
   * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
   */
  public com.facebook.buck.remoteexecution.proto.TraceInfoOrBuilder getTraceInfoOrBuilder() {
    return getTraceInfo();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (reSessionId_ != null) {
      output.writeMessage(1, getReSessionId());
    }
    if (buckInfo_ != null) {
      output.writeMessage(2, getBuckInfo());
    }
    if (traceInfo_ != null) {
      output.writeMessage(3, getTraceInfo());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (reSessionId_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getReSessionId());
    }
    if (buckInfo_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getBuckInfo());
    }
    if (traceInfo_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getTraceInfo());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata)) {
      return super.equals(obj);
    }
    com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata other = (com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata) obj;

    boolean result = true;
    result = result && (hasReSessionId() == other.hasReSessionId());
    if (hasReSessionId()) {
      result = result && getReSessionId()
          .equals(other.getReSessionId());
    }
    result = result && (hasBuckInfo() == other.hasBuckInfo());
    if (hasBuckInfo()) {
      result = result && getBuckInfo()
          .equals(other.getBuckInfo());
    }
    result = result && (hasTraceInfo() == other.hasTraceInfo());
    if (hasTraceInfo()) {
      result = result && getTraceInfo()
          .equals(other.getTraceInfo());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasReSessionId()) {
      hash = (37 * hash) + RE_SESSION_ID_FIELD_NUMBER;
      hash = (53 * hash) + getReSessionId().hashCode();
    }
    if (hasBuckInfo()) {
      hash = (37 * hash) + BUCK_INFO_FIELD_NUMBER;
      hash = (53 * hash) + getBuckInfo().hashCode();
    }
    if (hasTraceInfo()) {
      hash = (37 * hash) + TRACE_INFO_FIELD_NUMBER;
      hash = (53 * hash) + getTraceInfo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * This is propagated in GRPC header "re-metadata-bin".
   * </pre>
   *
   * Protobuf type {@code facebook.remote_execution.RemoteExecutionMetadata}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:facebook.remote_execution.RemoteExecutionMetadata)
      com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_RemoteExecutionMetadata_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_RemoteExecutionMetadata_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata.class, com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata.Builder.class);
    }

    // Construct using com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (reSessionIdBuilder_ == null) {
        reSessionId_ = null;
      } else {
        reSessionId_ = null;
        reSessionIdBuilder_ = null;
      }
      if (buckInfoBuilder_ == null) {
        buckInfo_ = null;
      } else {
        buckInfo_ = null;
        buckInfoBuilder_ = null;
      }
      if (traceInfoBuilder_ == null) {
        traceInfo_ = null;
      } else {
        traceInfo_ = null;
        traceInfoBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadataProto.internal_static_facebook_remote_execution_RemoteExecutionMetadata_descriptor;
    }

    @java.lang.Override
    public com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata getDefaultInstanceForType() {
      return com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata.getDefaultInstance();
    }

    @java.lang.Override
    public com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata build() {
      com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata buildPartial() {
      com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata result = new com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata(this);
      if (reSessionIdBuilder_ == null) {
        result.reSessionId_ = reSessionId_;
      } else {
        result.reSessionId_ = reSessionIdBuilder_.build();
      }
      if (buckInfoBuilder_ == null) {
        result.buckInfo_ = buckInfo_;
      } else {
        result.buckInfo_ = buckInfoBuilder_.build();
      }
      if (traceInfoBuilder_ == null) {
        result.traceInfo_ = traceInfo_;
      } else {
        result.traceInfo_ = traceInfoBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata) {
        return mergeFrom((com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata other) {
      if (other == com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata.getDefaultInstance()) return this;
      if (other.hasReSessionId()) {
        mergeReSessionId(other.getReSessionId());
      }
      if (other.hasBuckInfo()) {
        mergeBuckInfo(other.getBuckInfo());
      }
      if (other.hasTraceInfo()) {
        mergeTraceInfo(other.getTraceInfo());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.facebook.buck.remoteexecution.proto.RESessionID reSessionId_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.facebook.buck.remoteexecution.proto.RESessionID, com.facebook.buck.remoteexecution.proto.RESessionID.Builder, com.facebook.buck.remoteexecution.proto.RESessionIDOrBuilder> reSessionIdBuilder_;
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    public boolean hasReSessionId() {
      return reSessionIdBuilder_ != null || reSessionId_ != null;
    }
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    public com.facebook.buck.remoteexecution.proto.RESessionID getReSessionId() {
      if (reSessionIdBuilder_ == null) {
        return reSessionId_ == null ? com.facebook.buck.remoteexecution.proto.RESessionID.getDefaultInstance() : reSessionId_;
      } else {
        return reSessionIdBuilder_.getMessage();
      }
    }
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    public Builder setReSessionId(com.facebook.buck.remoteexecution.proto.RESessionID value) {
      if (reSessionIdBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        reSessionId_ = value;
        onChanged();
      } else {
        reSessionIdBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    public Builder setReSessionId(
        com.facebook.buck.remoteexecution.proto.RESessionID.Builder builderForValue) {
      if (reSessionIdBuilder_ == null) {
        reSessionId_ = builderForValue.build();
        onChanged();
      } else {
        reSessionIdBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    public Builder mergeReSessionId(com.facebook.buck.remoteexecution.proto.RESessionID value) {
      if (reSessionIdBuilder_ == null) {
        if (reSessionId_ != null) {
          reSessionId_ =
            com.facebook.buck.remoteexecution.proto.RESessionID.newBuilder(reSessionId_).mergeFrom(value).buildPartial();
        } else {
          reSessionId_ = value;
        }
        onChanged();
      } else {
        reSessionIdBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    public Builder clearReSessionId() {
      if (reSessionIdBuilder_ == null) {
        reSessionId_ = null;
        onChanged();
      } else {
        reSessionId_ = null;
        reSessionIdBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    public com.facebook.buck.remoteexecution.proto.RESessionID.Builder getReSessionIdBuilder() {
      
      onChanged();
      return getReSessionIdFieldBuilder().getBuilder();
    }
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    public com.facebook.buck.remoteexecution.proto.RESessionIDOrBuilder getReSessionIdOrBuilder() {
      if (reSessionIdBuilder_ != null) {
        return reSessionIdBuilder_.getMessageOrBuilder();
      } else {
        return reSessionId_ == null ?
            com.facebook.buck.remoteexecution.proto.RESessionID.getDefaultInstance() : reSessionId_;
      }
    }
    /**
     * <code>.facebook.remote_execution.RESessionID re_session_id = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.facebook.buck.remoteexecution.proto.RESessionID, com.facebook.buck.remoteexecution.proto.RESessionID.Builder, com.facebook.buck.remoteexecution.proto.RESessionIDOrBuilder> 
        getReSessionIdFieldBuilder() {
      if (reSessionIdBuilder_ == null) {
        reSessionIdBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.facebook.buck.remoteexecution.proto.RESessionID, com.facebook.buck.remoteexecution.proto.RESessionID.Builder, com.facebook.buck.remoteexecution.proto.RESessionIDOrBuilder>(
                getReSessionId(),
                getParentForChildren(),
                isClean());
        reSessionId_ = null;
      }
      return reSessionIdBuilder_;
    }

    private com.facebook.buck.remoteexecution.proto.BuckInfo buckInfo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.facebook.buck.remoteexecution.proto.BuckInfo, com.facebook.buck.remoteexecution.proto.BuckInfo.Builder, com.facebook.buck.remoteexecution.proto.BuckInfoOrBuilder> buckInfoBuilder_;
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    public boolean hasBuckInfo() {
      return buckInfoBuilder_ != null || buckInfo_ != null;
    }
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    public com.facebook.buck.remoteexecution.proto.BuckInfo getBuckInfo() {
      if (buckInfoBuilder_ == null) {
        return buckInfo_ == null ? com.facebook.buck.remoteexecution.proto.BuckInfo.getDefaultInstance() : buckInfo_;
      } else {
        return buckInfoBuilder_.getMessage();
      }
    }
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    public Builder setBuckInfo(com.facebook.buck.remoteexecution.proto.BuckInfo value) {
      if (buckInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        buckInfo_ = value;
        onChanged();
      } else {
        buckInfoBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    public Builder setBuckInfo(
        com.facebook.buck.remoteexecution.proto.BuckInfo.Builder builderForValue) {
      if (buckInfoBuilder_ == null) {
        buckInfo_ = builderForValue.build();
        onChanged();
      } else {
        buckInfoBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    public Builder mergeBuckInfo(com.facebook.buck.remoteexecution.proto.BuckInfo value) {
      if (buckInfoBuilder_ == null) {
        if (buckInfo_ != null) {
          buckInfo_ =
            com.facebook.buck.remoteexecution.proto.BuckInfo.newBuilder(buckInfo_).mergeFrom(value).buildPartial();
        } else {
          buckInfo_ = value;
        }
        onChanged();
      } else {
        buckInfoBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    public Builder clearBuckInfo() {
      if (buckInfoBuilder_ == null) {
        buckInfo_ = null;
        onChanged();
      } else {
        buckInfo_ = null;
        buckInfoBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    public com.facebook.buck.remoteexecution.proto.BuckInfo.Builder getBuckInfoBuilder() {
      
      onChanged();
      return getBuckInfoFieldBuilder().getBuilder();
    }
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    public com.facebook.buck.remoteexecution.proto.BuckInfoOrBuilder getBuckInfoOrBuilder() {
      if (buckInfoBuilder_ != null) {
        return buckInfoBuilder_.getMessageOrBuilder();
      } else {
        return buckInfo_ == null ?
            com.facebook.buck.remoteexecution.proto.BuckInfo.getDefaultInstance() : buckInfo_;
      }
    }
    /**
     * <code>.facebook.remote_execution.BuckInfo buck_info = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.facebook.buck.remoteexecution.proto.BuckInfo, com.facebook.buck.remoteexecution.proto.BuckInfo.Builder, com.facebook.buck.remoteexecution.proto.BuckInfoOrBuilder> 
        getBuckInfoFieldBuilder() {
      if (buckInfoBuilder_ == null) {
        buckInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.facebook.buck.remoteexecution.proto.BuckInfo, com.facebook.buck.remoteexecution.proto.BuckInfo.Builder, com.facebook.buck.remoteexecution.proto.BuckInfoOrBuilder>(
                getBuckInfo(),
                getParentForChildren(),
                isClean());
        buckInfo_ = null;
      }
      return buckInfoBuilder_;
    }

    private com.facebook.buck.remoteexecution.proto.TraceInfo traceInfo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.facebook.buck.remoteexecution.proto.TraceInfo, com.facebook.buck.remoteexecution.proto.TraceInfo.Builder, com.facebook.buck.remoteexecution.proto.TraceInfoOrBuilder> traceInfoBuilder_;
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    public boolean hasTraceInfo() {
      return traceInfoBuilder_ != null || traceInfo_ != null;
    }
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    public com.facebook.buck.remoteexecution.proto.TraceInfo getTraceInfo() {
      if (traceInfoBuilder_ == null) {
        return traceInfo_ == null ? com.facebook.buck.remoteexecution.proto.TraceInfo.getDefaultInstance() : traceInfo_;
      } else {
        return traceInfoBuilder_.getMessage();
      }
    }
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    public Builder setTraceInfo(com.facebook.buck.remoteexecution.proto.TraceInfo value) {
      if (traceInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        traceInfo_ = value;
        onChanged();
      } else {
        traceInfoBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    public Builder setTraceInfo(
        com.facebook.buck.remoteexecution.proto.TraceInfo.Builder builderForValue) {
      if (traceInfoBuilder_ == null) {
        traceInfo_ = builderForValue.build();
        onChanged();
      } else {
        traceInfoBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    public Builder mergeTraceInfo(com.facebook.buck.remoteexecution.proto.TraceInfo value) {
      if (traceInfoBuilder_ == null) {
        if (traceInfo_ != null) {
          traceInfo_ =
            com.facebook.buck.remoteexecution.proto.TraceInfo.newBuilder(traceInfo_).mergeFrom(value).buildPartial();
        } else {
          traceInfo_ = value;
        }
        onChanged();
      } else {
        traceInfoBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    public Builder clearTraceInfo() {
      if (traceInfoBuilder_ == null) {
        traceInfo_ = null;
        onChanged();
      } else {
        traceInfo_ = null;
        traceInfoBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    public com.facebook.buck.remoteexecution.proto.TraceInfo.Builder getTraceInfoBuilder() {
      
      onChanged();
      return getTraceInfoFieldBuilder().getBuilder();
    }
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    public com.facebook.buck.remoteexecution.proto.TraceInfoOrBuilder getTraceInfoOrBuilder() {
      if (traceInfoBuilder_ != null) {
        return traceInfoBuilder_.getMessageOrBuilder();
      } else {
        return traceInfo_ == null ?
            com.facebook.buck.remoteexecution.proto.TraceInfo.getDefaultInstance() : traceInfo_;
      }
    }
    /**
     * <code>.facebook.remote_execution.TraceInfo trace_info = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.facebook.buck.remoteexecution.proto.TraceInfo, com.facebook.buck.remoteexecution.proto.TraceInfo.Builder, com.facebook.buck.remoteexecution.proto.TraceInfoOrBuilder> 
        getTraceInfoFieldBuilder() {
      if (traceInfoBuilder_ == null) {
        traceInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.facebook.buck.remoteexecution.proto.TraceInfo, com.facebook.buck.remoteexecution.proto.TraceInfo.Builder, com.facebook.buck.remoteexecution.proto.TraceInfoOrBuilder>(
                getTraceInfo(),
                getParentForChildren(),
                isClean());
        traceInfo_ = null;
      }
      return traceInfoBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:facebook.remote_execution.RemoteExecutionMetadata)
  }

  // @@protoc_insertion_point(class_scope:facebook.remote_execution.RemoteExecutionMetadata)
  private static final com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata();
  }

  public static com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RemoteExecutionMetadata>
      PARSER = new com.google.protobuf.AbstractParser<RemoteExecutionMetadata>() {
    @java.lang.Override
    public RemoteExecutionMetadata parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RemoteExecutionMetadata(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RemoteExecutionMetadata> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RemoteExecutionMetadata> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.facebook.buck.remoteexecution.proto.RemoteExecutionMetadata getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

