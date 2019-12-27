/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.rocketmq.spring.boot.consumer.pull;

import org.apache.rocketmq.client.consumer.MQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.PullTaskCallback;
import org.apache.rocketmq.client.consumer.PullTaskContext;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.boot.annotation.RocketmqPullTopic;

@RocketmqPullTopic("xxx")
public class PullTaskCallbackImpl implements PullTaskCallback {

	@Override
	public void doPullTask(MessageQueue mq, PullTaskContext context) {

		MQPullConsumer consumer = context.getPullConsumer();

		try {

			// 获取从哪里拉取
			long offset = consumer.fetchConsumeOffset(mq, false);
			if (offset < 0) {
				offset = 0;
			}
			
			//String subExpression, long offset, int maxNums
			
			PullResult pullResult = consumer.pull(mq, "*", offset, 32);

			System.out.println(offset + "\t" + mq + "\t" + pullResult);

			switch (pullResult.getPullStatus()) {

				case FOUND:
					break;
				case NO_MATCHED_MSG:
					break;
				case NO_NEW_MSG:
				case OFFSET_ILLEGAL:
					break;
				default:
					break;

			}

			// 存储Offset，客户端每隔5s会定时刷新到Broker
			consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());

			//设置隔多长时间进行下次拉去 （100ms后重新拉取）
			context.setPullNextDelayTimeMillis(100);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
