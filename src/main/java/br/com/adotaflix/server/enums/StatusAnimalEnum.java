package br.com.adotaflix.server.enums;

public enum StatusAnimalEnum {
	D("Disponível"),
	A("Adotado"),
	F("Falecido");
	
	
	private String descricao;
	
	private StatusAnimalEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
