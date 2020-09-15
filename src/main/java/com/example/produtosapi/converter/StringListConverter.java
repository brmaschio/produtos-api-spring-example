package com.example.produtosapi.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {	
		return String.join(",", attribute);
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {

		if(dbData != null && !dbData.trim().isEmpty()) {
			
			return new ArrayList<>(Arrays.asList(dbData.split(",")));	
		}
		
		return new ArrayList<String>();

	}

}
