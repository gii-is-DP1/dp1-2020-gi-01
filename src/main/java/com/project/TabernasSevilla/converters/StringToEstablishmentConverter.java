package com.project.TabernasSevilla.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.repository.EstablishmentRepository;



@Component
@Transactional
public class StringToEstablishmentConverter implements Converter<String, Establishment> {

	@Autowired
	private EstablishmentRepository	establishmentRepository;


	@Override
	public Establishment convert(final String text) {
		Establishment result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.establishmentRepository.findById(id).get();
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
