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

import org.apache.hop.core.ICheckResult;
import org.apache.hop.core.annotations.Transform;
import org.apache.hop.core.exception.HopTransformException;
import org.apache.hop.core.row.IRowMeta;
import org.apache.hop.core.row.IValueMeta;
import org.apache.hop.core.row.value.ValueMetaString;
import org.apache.hop.core.variables.IVariables;
import org.apache.hop.metadata.api.HopMetadataProperty;
import org.apache.hop.metadata.api.IHopMetadataProvider;
import org.apache.hop.pipeline.PipelineMeta;
import org.apache.hop.pipeline.transform.BaseTransformMeta;
import org.apache.hop.pipeline.transform.TransformMeta;

import java.util.List;

/** Meta data for the sample transform. */
@Transform(
    id = "SampleTransform",
    name = "i18n::SampleTransform.Name",
    description = "i18n::SampleTransform.Description",
    image = "sample.svg",
    categoryDescription = "Sample.Category",
    documentationUrl = "" /*url to your documentation */)
public class SampleMeta extends BaseTransformMeta<Sample, SampleData> {

  private static final Class<?> PKG = SampleMeta.class; // Needed by Translator
  public static final String SAMPLE_TEXT_FIELD_NAME = "Value";

  @HopMetadataProperty(
      key = "sample_text",
      injectionKeyDescription = "SampleTransform.Injection.SampleText.Description")
  private String sampleText;

  public String getSampleText() {
    return sampleText;
  }

  public void setSampleText(String sampleText) {
    this.sampleText = sampleText;
  }

  @Override
  public void getFields(
      IRowMeta rowMeta,
      String name,
      IRowMeta[] info,
      TransformMeta nextTransform,
      IVariables variables,
      IHopMetadataProvider metadataProvider)
      throws HopTransformException {
    // Add new
    IValueMeta vm = new ValueMetaString(SAMPLE_TEXT_FIELD_NAME);
    vm.setOrigin(getParentTransformMeta().getName());

    rowMeta.addValueMeta(vm);
  }

  @Override
  public void check(
      List<ICheckResult> remarks,
      PipelineMeta pipelineMeta,
      TransformMeta transforminfo,
      IRowMeta prev,
      String[] input,
      String[] output,
      IRowMeta info,
      IVariables variables,
      IHopMetadataProvider metadataProvider) {
    // Checks to perform when validating a transform
  }

  @Override
  public void setDefault() {
    // Set default value for new sample text field
    sampleText = "Hello my name is Apache Hop!";
  }
}
