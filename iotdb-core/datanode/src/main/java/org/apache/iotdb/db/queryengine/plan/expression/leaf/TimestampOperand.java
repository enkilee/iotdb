/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.iotdb.db.queryengine.plan.expression.leaf;

import org.apache.iotdb.db.queryengine.plan.expression.ExpressionType;
import org.apache.iotdb.db.queryengine.plan.expression.visitor.ExpressionVisitor;
import org.apache.iotdb.db.queryengine.plan.planner.plan.parameter.InputLocation;
import org.apache.iotdb.db.queryengine.transformation.dag.memory.LayerMemoryAssigner;

import org.apache.tsfile.utils.RamUsageEstimator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class TimestampOperand extends LeafOperand {

  private static final long INSTANCE_SIZE =
      RamUsageEstimator.shallowSizeOfInstance(TimeSeriesOperand.class);

  public static final String TIMESTAMP_EXPRESSION_STRING = "Time";

  public TimestampOperand() {
    // do nothing
  }

  public TimestampOperand(ByteBuffer byteBuffer) {
    // do nothing
  }

  @Override
  public <R, C> R accept(ExpressionVisitor<R, C> visitor, C context) {
    return visitor.visitTimeStampOperand(this, context);
  }

  @Override
  public void bindInputLayerColumnIndexWithExpression(
      Map<String, List<InputLocation>> inputLocations) {
    // do nothing
  }

  @Override
  public void updateStatisticsForMemoryAssigner(LayerMemoryAssigner memoryAssigner) {
    memoryAssigner.increaseExpressionReference(this);
  }

  @Override
  protected boolean isConstantOperandInternal() {
    return false;
  }

  @Override
  protected String getExpressionStringInternal() {
    return TIMESTAMP_EXPRESSION_STRING;
  }

  @Override
  public ExpressionType getExpressionType() {
    return ExpressionType.TIMESTAMP;
  }

  @Override
  protected void serialize(ByteBuffer byteBuffer) {
    // do nothing
  }

  @Override
  protected void serialize(DataOutputStream stream) throws IOException {
    // do nothing
  }

  @Override
  public long ramBytesUsed() {
    return INSTANCE_SIZE;
  }
}
