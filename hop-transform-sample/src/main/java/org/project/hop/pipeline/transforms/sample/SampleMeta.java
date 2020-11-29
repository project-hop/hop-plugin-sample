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
import org.apache.hop.core.exception.HopXmlException;
import org.apache.hop.core.row.IRowMeta;
import org.apache.hop.core.variables.IVariables;
import org.apache.hop.core.xml.XmlHandler;
import org.apache.hop.metadata.api.IHopMetadataProvider;
import org.apache.hop.pipeline.Pipeline;
import org.apache.hop.pipeline.PipelineMeta;
import org.apache.hop.pipeline.transform.BaseTransformMeta;
import org.apache.hop.pipeline.transform.ITransformMeta;
import org.apache.hop.pipeline.transform.TransformMeta;
import org.w3c.dom.Node;

import java.util.List;

/**
 * Meta data for the sample transform.
 */
@Transform(
        id = "Sample",
        i18nPackageName = "org.project.hop.pipeline.transforms.sample",
        name = "Sample.Name",
        description = "Sample.Description",
        image = "sample.svg",
        categoryDescription = "i18n:org.apache.hop.pipeline.transform:BaseTransform.Category.Flow",
        documentationUrl = "" /*url to your documentation */
)
public class SampleMeta extends BaseTransformMeta implements ITransformMeta<Sample, SampleData> {

  private static final Class<?> PKG = SampleMeta.class; // Needed by Translator


  @Override
  public void getFields( IRowMeta inputRowMeta, String name, IRowMeta[] info, TransformMeta nextTransform,
                         IVariables variables, IHopMetadataProvider metadataProvider ) throws HopTransformException {
    // Default: no values are added to the row in the transform
  }

  @Override
  public void check( List<ICheckResult> remarks, PipelineMeta pipelineMeta, TransformMeta transforminfo,
                     IRowMeta prev, String[] input, String[] output, IRowMeta info, IVariables variables,
                     IHopMetadataProvider metadataProvider ) {
    // Checks to perform when validating a transform
  }

  @Override
  public Sample createTransform(TransformMeta transformMeta, SampleData data, int copyNr,
                                PipelineMeta pipelineMeta, Pipeline pipeline ) {
    return new Sample( transformMeta, this, data, copyNr, pipelineMeta, pipeline );
  }

  @Override
  public SampleData getTransformData() {
    return new SampleData();
  }

  @Override
  public void loadXml( Node transformNode, IHopMetadataProvider metadataProvider ) throws HopXmlException {
    //load the saved values from the transformnode
    String sampleValue = XmlHandler.getTagValue( transformNode, "sampleValue" );

  }

  @Override
  public void setDefault() {
    //default values when creating a new transform
  }

  @Override
  public String getXml() {
    //save your metadata values to the transform xml
    StringBuilder retval = new StringBuilder( 200 );
    retval.append( "      " ).append( XmlHandler.addTagValue( "sampleValue", "this is a value" ) );
    return retval.toString();
  }

}
