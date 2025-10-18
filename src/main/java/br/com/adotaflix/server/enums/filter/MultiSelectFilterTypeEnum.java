package br.com.adotaflix.server.enums.filter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum MultiSelectFilterTypeEnum {
	IN("Está em"),
	NOTEQUALS("Não está em");
	
private String codigo;
	
	private MultiSelectFilterTypeEnum(String codigo){
		this.codigo = codigo;
	}
	
	
	public String getCodigo() {
		return codigo;
	}

	public static MultiSelectFilterTypeEnum getEnum(String codigo) {

		List<MultiSelectFilterTypeEnum> enums = new ArrayList<MultiSelectFilterTypeEnum>(EnumSet.allOf(MultiSelectFilterTypeEnum.class));

		for (MultiSelectFilterTypeEnum enume : enums) {
			if (enume.getCodigo().equals(codigo)) {
				return enume;
			}
		}

		return null;
	}
}
