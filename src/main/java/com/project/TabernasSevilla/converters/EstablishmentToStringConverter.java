package com.project.TabernasSevilla.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.TabernasSevilla.domain.Establishment;

@Component
@Transactional
public class EstablishmentToStringConverter implements Converter<Establishment, String>  {

	@Override
	public String convert(final Establishment c) {
		String result;

		if (c == null)
			result = null;
		else
			result = String.valueOf(c.getId());

		return result;
	}
}
