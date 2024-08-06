package com.corso.vo;

import javax.validation.constraints.NotEmpty;

public class TrenoVO {
	private int idTreno;
    private String sigla;
    
	public int getIdTreno() {
		return idTreno;
	}
	public void setIdTreno(int idTreno) {
		this.idTreno = idTreno;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
