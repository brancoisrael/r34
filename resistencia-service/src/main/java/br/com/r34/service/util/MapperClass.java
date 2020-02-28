package br.com.r34.service.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class MapperClass {

	public static <D, T> D converter(final T entity, Class<D> outClass) {
		return new ModelMapper().map(entity, outClass);
	}
	
	public static <D, T> List<D> converter(final List<T> entity, Class<D> outClass) {
		ModelMapper mapper = new ModelMapper();
		List<D> l = new ArrayList<>();
		for(T en : entity) {
			l.add(mapper.map(en, outClass));
		}
		return l;
	}
}
