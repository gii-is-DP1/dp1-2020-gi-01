package com.project.TabernasSevilla.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

	// Constructors -----------------------------------------------------------

		private static final long	serialVersionUID	= 1L;


		public Authority() {
			super();
		}


		// Values -----------------------------------------------------------------

		public static final String	ADMIN		= "ADMIN";
		public static final String	COSTUMER		= "COSTUMER";


		// Attributes -------------------------------------------------------------

		private String				authority;


		@NotBlank
		@Pattern(regexp = "^" + Authority.ADMIN + "|" + Authority.COSTUMER + "$")
		@Override
		public String getAuthority() {
			return this.authority;
		}

		public void setAuthority(final String authority) {
			this.authority = authority;
		}

		public static Collection<Authority> listAuthorities() {
			Collection<Authority> result;
			Authority authority;

			result = new ArrayList<Authority>();

			authority = new Authority();
			authority.setAuthority(Authority.ADMIN);
			result.add(authority);

			authority = new Authority();
			authority.setAuthority(Authority.COSTUMER);
			result.add(authority);

			return result;
		}

		// Equality ---------------------------------------------------------------

		@Override
		public int hashCode() {
			return this.getAuthority().hashCode();
		}

		@Override
		public boolean equals(final Object other) {
			boolean result;

			if (this == other)
				result = true;
			else if (other == null)
				result = false;
			else if (!this.getClass().isInstance(other))
				result = false;
			else
				result = (this.getAuthority().equals(((Authority) other).getAuthority()));

			return result;
		}
}
