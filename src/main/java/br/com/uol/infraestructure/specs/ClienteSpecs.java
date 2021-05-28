package br.com.uol.infraestructure.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.uol.domain.filter.ClienteFilter;
import br.com.uol.domain.model.Cliente;

public class ClienteSpecs {

	public static Specification<Cliente> filter(ClienteFilter filter) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (filter.getNome() != null) {
				predicates.add(builder.equal(root.get("nome"), filter.getNome()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
