package br.com.uol.infraestructure.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.uol.domain.filter.CidadeFilter;
import br.com.uol.domain.model.Cidade;

public class CidadeSpecs {
	
	private CidadeSpecs() {}

	public static Specification<Cidade> filter(CidadeFilter filter) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (filter.getNome() != null) {
				predicates.add(builder.equal(builder.upper(root.get("nome")), filter.getNome().toUpperCase()));
			}
			
			if (filter.getEstado() != null) {
				predicates.add(builder.equal(builder.upper(root.get("estado")), filter.getEstado().toUpperCase()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
