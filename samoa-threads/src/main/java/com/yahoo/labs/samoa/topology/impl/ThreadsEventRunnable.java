package com.yahoo.labs.samoa.topology.impl;

/*
 * #%L
 * SAMOA
 * %%
 * Copyright (C) 2013 Yahoo! Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.yahoo.labs.samoa.core.ContentEvent;
import com.yahoo.labs.samoa.topology.ProcessingItem;

/**
 * Runnable class where each object corresponds to a ContentEvent and an assigned PI. 
 * When a PI receives a ContentEvent, it will create a ThreadsEventRunnable with a replica of itself (a worker PI)
 * and the received ContentEvent. This runnable is then submitted to a thread queue waiting to be executed.
 * The worker PI will process the received event when the runnable object is executed/run.
 * @author Anh Thu Vu
 *
 */
public class ThreadsEventRunnable implements Runnable {

	private ProcessingItem pi;
	private ContentEvent event;
	
	public ThreadsEventRunnable(ProcessingItem pi, ContentEvent event) {
		this.pi = pi;
		this.event = event;
	}
	
	public ProcessingItem getProcessingItem() {
		return this.pi;
	}
	
	public ContentEvent getContentEvent() {
		return this.event;
	}
	
	@Override
	public void run() {
		ThreadsWorkerProcessingItem workerPi = (ThreadsWorkerProcessingItem) pi;
		workerPi.processEvent(event);
	}

}
