package br.com.adotaflix.server.enums;

public enum RespostaSolicitacaoEnum {
	A("Aprovada"), R("Reprovada");
	
	private String descricao;
	
	private RespostaSolicitacaoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
