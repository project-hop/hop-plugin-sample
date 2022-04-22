/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.project.hop.pipeline.transforms.sample;

import org.apache.hop.core.exception.HopException;
import org.apache.hop.core.exception.HopTransformException;
import org.apache.hop.pipeline.Pipeline;
import org.apache.hop.pipeline.PipelineMeta;
import org.apache.hop.pipeline.transform.BaseTransform;
import org.apache.hop.pipeline.transform.TransformMeta;

/** Transform That contains the basic skeleton needed to create your own plugin */
public class Sample extends BaseTransform<SampleMeta, SampleData> {

  private static final Class<?> PKG = Sample.class; // Needed by Translator

  public Sample(
      TransformMeta transformMeta,
      SampleMeta meta,
      SampleData data,
      int copyNr,
      PipelineMeta pipelineMeta,
      Pipeline pipeline) {
    super(transformMeta, meta, data, copyNr, pipelineMeta, pipeline);
  }

  @Override
  public boolean processRow() throws HopException {

    Object[] r = getRow(); // Get row from input rowset & set row busy!

    if (r == null) { // no more input to be expected...
      setOutputDone();
      return false;
    }

    if (first) { // use this block to do some processing that is only needed 1 time
      first = false;
    }

    data.outputRowMeta = getInputRowMeta().clone();
    meta.getFields(data.outputRowMeta, getTransformName(), null, null, this, metadataProvider);

    int fieldPos = data.outputRowMeta.indexOfValue(SampleMeta.SAMPLE_TEXT_FIELD_NAME);
    if (fieldPos < 0) {
      throw new HopTransformException(
          "Target field ["
              + SampleMeta.SAMPLE_TEXT_FIELD_NAME
              + "] couldn't be found in output stream!");
    }

    r[fieldPos] = meta.getSampleText();

    putRow(data.outputRowMeta, r); // return your data
    return true;
  }

  @Override
  public boolean init() {
    if (super.init()) {
      return true;
    }
    return false;
  }
}
