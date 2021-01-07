package com.project.TabernasSevilla.converters;

import java.time.Instant;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class StringToInstantConverter implements Converter<String, Instant> {

	@Override
	public Instant convert(final String text) {
		Instant result;
		try {
			String parse = text;
			if (!text.endsWith("Z")) {
				 parse = text + ":00.00Z";
			}
			result = Instant.parse(parse);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
