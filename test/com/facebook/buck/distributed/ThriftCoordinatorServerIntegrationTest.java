/*
 * Copyright 2016-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.distributed;

import com.facebook.buck.distributed.thrift.FinishedBuildingResponse;
import com.facebook.buck.distributed.thrift.GetTargetsToBuildAction;
import com.facebook.buck.distributed.thrift.GetTargetsToBuildResponse;
import com.facebook.buck.distributed.thrift.StampedeId;
import com.facebook.buck.parser.NoSuchBuildTargetException;
import java.io.IOException;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class ThriftCoordinatorServerIntegrationTest {
  public static final StampedeId STAMPEDE_ID = new StampedeId().setId("down the line");

  private static final String MINION_ID = "super cool minion";
  private static final int MAX_BUILD_NODES_PER_MINION = 42;

  @Test
  public void testMakingSimpleRequest() throws IOException {
    int port = findRandomOpenPortOnAllLocalInterfaces();
    try (ThriftCoordinatorServer server =
            createCoordinatorServer(port, BuildTargetsQueue.newEmptyQueue());
        ThriftCoordinatorClient client =
            new ThriftCoordinatorClient("localhost", port, STAMPEDE_ID)) {
      server.start();
      client.start();
      GetTargetsToBuildResponse response = client.getTargetsToBuild(MINION_ID);
      Assert.assertNotNull(response);
      Assert.assertEquals(GetTargetsToBuildAction.CLOSE_CLIENT, response.getAction());
    }
  }

  @Test
  public void testThriftServerWithDiamondGraph() throws IOException, NoSuchBuildTargetException {
    int port = findRandomOpenPortOnAllLocalInterfaces();
    BuildTargetsQueue diamondQueue = BuildTargetsQueueTest.createDiamondDependencyQueue();

    ThriftCoordinatorServer.EventListener eventListener =
        EasyMock.createMock(ThriftCoordinatorServer.EventListener.class);
    eventListener.onThriftServerStarted(EasyMock.anyString(), EasyMock.eq(port));
    EasyMock.expectLastCall().once();
    eventListener.onThriftServerClosing(EasyMock.eq(0));
    EasyMock.expectLastCall().once();
    EasyMock.replay(eventListener);

    try (ThriftCoordinatorServer server =
            createCoordinatorServer(port, diamondQueue, eventListener);
        ThriftCoordinatorClient client =
            new ThriftCoordinatorClient("localhost", port, STAMPEDE_ID)) {
      server.start();
      client.start();

      GetTargetsToBuildResponse targetsToBuildResponse = client.getTargetsToBuild(MINION_ID);
      Assert.assertEquals(
          GetTargetsToBuildAction.BUILD_TARGETS, targetsToBuildResponse.getAction());
      Assert.assertEquals(1, targetsToBuildResponse.getBuildTargetsSize());

      FinishedBuildingResponse finishedBuildingResponse = client.finishedBuilding(MINION_ID, 0);
      Assert.assertTrue(finishedBuildingResponse.continueBuilding);

      targetsToBuildResponse = client.getTargetsToBuild(MINION_ID);
      Assert.assertEquals(
          GetTargetsToBuildAction.BUILD_TARGETS, targetsToBuildResponse.getAction());
      Assert.assertEquals(2, targetsToBuildResponse.getBuildTargetsSize());
    }

    EasyMock.verify(eventListener);
  }

  public static int findRandomOpenPortOnAllLocalInterfaces() throws IOException {
    return DistBuildSlaveExecutor.getFreePortForCoordinator();
  }

  public static ThriftCoordinatorServer createServerOnRandomPort(BuildTargetsQueue queue)
      throws IOException {
    int port = findRandomOpenPortOnAllLocalInterfaces();
    return createCoordinatorServer(port, queue);
  }

  private static ThriftCoordinatorServer createCoordinatorServer(
      int port, BuildTargetsQueue queue) {
    ThriftCoordinatorServer.EventListener eventListener =
        EasyMock.createNiceMock(ThriftCoordinatorServer.EventListener.class);
    return createCoordinatorServer(port, queue, eventListener);
  }

  private static ThriftCoordinatorServer createCoordinatorServer(
      int port, BuildTargetsQueue queue, ThriftCoordinatorServer.EventListener eventListener) {
    return new ThriftCoordinatorServer(
        port, queue, STAMPEDE_ID, MAX_BUILD_NODES_PER_MINION, eventListener);
  }
}
