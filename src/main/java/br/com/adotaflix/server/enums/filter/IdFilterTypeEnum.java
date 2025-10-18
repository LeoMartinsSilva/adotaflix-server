package br.com.adotaflix.server.enums.filter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public enum IdFilterTypeEnum {
	
	EQUALS("EQUALS"),
	NOTEQUALS("NOTEQUALS");
	
	private String codigo;
	
	private IdFilterTypeEnum(String codigo){
		this.codigo = codigo;
	}
	
	
	public String getCodigo() {
		return codigo;
	}

	public static IdFilterTypeEnum getEnum(String codigo) {

		List<IdFilterTypeEnum> enums = new ArrayList<IdFilterTypeEnum>(EnumSet.allOf(IdFilterTypeEnum.class));

		for (IdFilterTypeEnum enume : enums) {
			if (enume.getCodigo().equals(codigo)) {
				return enume;
			}
		}

		return null;
	}
}
