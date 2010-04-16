/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.chemistry.opencmis.commons.impl.dataobjects;

import java.math.BigInteger;

import org.apache.chemistry.opencmis.commons.api.PropertyIntegerDefinition;

/**
 * Boolean property definition data implementation.
 * 
 * @author <a href="mailto:fmueller@opentext.com">Florian M&uuml;ller</a>
 * 
 */
public class PropertyIntegerDefinitionImpl extends AbstractPropertyDefinition<BigInteger> implements
		PropertyIntegerDefinition {

	private static final long serialVersionUID = 1L;

	private BigInteger fMinValue;
	private BigInteger fMaxValue;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.opencmis.client.provider.PropertyIntegerDefinitionData#getMinValue
	 * ()
	 */
	public BigInteger getMinValue() {
		return fMinValue;
	}

	public void setMinValue(BigInteger minValue) {
		fMinValue = minValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.opencmis.client.provider.PropertyIntegerDefinitionData#getMaxValue
	 * ()
	 */
	public BigInteger getMaxValue() {
		return fMaxValue;
	}

	public void setMaxValue(BigInteger maxValue) {
		fMaxValue = maxValue;
	}
}
