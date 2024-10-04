package br.com.jrm.test.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4009436503231460294L;

	@JsonProperty("min")
	private List<InformacaoPremiacaoVO> min;

	@JsonProperty("max")
	private List<InformacaoPremiacaoVO> max;

	public List<InformacaoPremiacaoVO> getMin() {
		return min;
	}

	public void setMin(List<InformacaoPremiacaoVO> min) {
		this.min = min;
	}

	public List<InformacaoPremiacaoVO> getMax() {
		return max;
	}

	public void setMax(List<InformacaoPremiacaoVO> max) {
		this.max = max;
	}
}
