/**
 * Copyright 2015, Emory University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.emory.mathcs.nlp.learning.optimization.method;

import edu.emory.mathcs.nlp.common.util.MathUtils;
import edu.emory.mathcs.nlp.learning.optimization.AdaptiveGradientDescentMiniBatch;
import edu.emory.mathcs.nlp.learning.optimization.reguralization.Regularizer;
import edu.emory.mathcs.nlp.learning.util.Instance;
import edu.emory.mathcs.nlp.learning.util.WeightVector;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class AdaGradMiniBatch extends AdaptiveGradientDescentMiniBatch
{
	private static final long serialVersionUID = -8912711630988471520L;

	public AdaGradMiniBatch(WeightVector vector, float learningRate, float bias)
	{
		this(vector, learningRate, bias, null);
	}
	
	public AdaGradMiniBatch(WeightVector vector, float learningRate, float bias, Regularizer rda)
	{
		super(vector, learningRate, bias, rda);
	}
	
	@Override
	public void trainAux(Instance instance)
	{
		trainClassification(instance);
	}
	
	@Override
	protected int getPredictedLabel(Instance instance)
	{
		return getPredictedLabelHingeLoss(instance);
	}
	
	@Override
	protected float getDiagonal(float previousDiagonal, float gradient)
	{
		return previousDiagonal + (float)MathUtils.sq(gradient);
	}
	
	@Override
	public String toString()
	{
		return "AdaGrad Mini-batch";
	}
}
