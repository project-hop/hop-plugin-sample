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

package org.project.hop.workflow.actions.sample;

import org.apache.hop.core.ICheckResult;
import org.apache.hop.core.Result;
import org.apache.hop.core.annotations.Action;
import org.apache.hop.core.exception.HopXmlException;
import org.apache.hop.core.variables.IVariables;
import org.apache.hop.core.xml.XmlHandler;
import org.apache.hop.i18n.BaseMessages;
import org.apache.hop.metadata.api.IHopMetadataProvider;
import org.apache.hop.workflow.WorkflowMeta;
import org.apache.hop.workflow.action.ActionBase;
import org.apache.hop.workflow.action.IAction;
import org.w3c.dom.Node;

import java.util.List;

@Action(
    id = "ACTIONSAMPLE",
    name = "i18n::ActionSample.Name",
    description = "i18n::ActionSample.Description",
    image = "sample.svg",
    categoryDescription = "Sample.Category",
    documentationUrl = "" /*url to your documentation */)
public class ActionSample extends ActionBase implements Cloneable, IAction {
  private static final Class<?> PKG = ActionSample.class; // Needed by Translator

  public ActionSample(String name) {
    super(name, "");
  }

  public ActionSample() {
    this("");
  }

  public Object clone() {
    ActionSample c = (ActionSample) super.clone();
    return c;
  }

  /**
   * Save values to XML
   *
   * @return
   */
  @Override
  public String getXml() {
    StringBuilder retval = new StringBuilder();

    retval.append(super.getXml());
    // example value to xml
    retval.append(XmlHandler.addTagValue("example", "value"));

    return retval.toString();
  }

  /**
   * Read the XML and get the values needed for the acton
   *
   * @param entrynode
   * @param metadataProvider
   * @throws HopXmlException
   */
  @Override
  public void loadXml(Node entrynode, IHopMetadataProvider metadataProvider, IVariables variables)
      throws HopXmlException {
    try {
      super.loadXml(entrynode);
      // message = XmlHandler.getTagValue( entrynode, "message" );
    } catch (Exception e) {
      throw new HopXmlException(
          BaseMessages.getString(PKG, "ActionSample.UnableToLoadFromXml.Label"), e);
    }
  }

  /**
   * Execute this action and return the result. In this case it means, just set the result boolean
   * in the Result class.
   *
   * @param result The result of the previous execution
   * @return The Result of the execution.
   */
  @Override
  public Result execute(Result result, int nr) {

    return result;
  }

  /**
   * Add checks to report warnings
   *
   * @param remarks
   * @param workflowMeta
   * @param variables
   * @param metadataProvider
   */
  @Override
  public void check(
      List<ICheckResult> remarks,
      WorkflowMeta workflowMeta,
      IVariables variables,
      IHopMetadataProvider metadataProvider) {}
}
