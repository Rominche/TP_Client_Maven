package com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ville {
	
	private String nomCommune;

	private String codeCommune;

	private String codePostal;

	private String ligne;

	private String libelleAcheminement;

	private String longitude;

	private String latitude;

}